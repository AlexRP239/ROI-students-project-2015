package edu.roi.playbox.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author apavelchuk
 * @since 20.07.2015.
 */
public class ConcurrentPitfallsTest {

    private int counter = 0;
    // volatile - чтение/запись данных всегда в/из памяти. То есть всегда самая актуальная информация (без кэша)
    private volatile int counter2 = 0;

    private int counter3 = 0;
    // синхронизация на объекте - код внутри блока syncronized не может выполняться параллельно
    private final Object counter3lock = new Object();

    // Объект thread-safe. Можно использовать без синхронизации
    private AtomicInteger counter4 = new AtomicInteger(0);


    @Test
    public void testSyncronizedAndNot() throws InterruptedException {
        final CyclicBarrier onstart = new CyclicBarrier(10);
        final CountDownLatch onfinish = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            // создаем 10 потоков
            new Thread(() -> {
                try {
                    // уменьшаем счетчик на 1
                    // ждем старта остальных потоков (значения счетчика = 0)
                    onstart.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    // никак не обрабатываем
                    e.printStackTrace();
                }

                // модифицируем counter (shared state) с синхронизацией и без
                for (int j = 0; j < 1000; j++) {

                    // это отличный пример inconsistency read + race conditions,
                    // так как есть локальный кэш и операция не атомарная
                    // 1. читаем значение counter (возможно из локального кэша)
                    // 2. увеличиваем локальную копию (в кэше) на один
                    // 3. записываем значение в локальную копию (кэш)
                    // 4. когда обновление попадет из кэша в память и станет досупным другим потокам - неизвестно
                    counter++;

                    // это отличный пример race conditions, так как операция не атомарная
                    // проблема с кэшем решена за счет использования volatile
                    // 1. читаем значение counter из памяти
                    // Но второй поток в этот же самым момент может прочитать то же значение
                    // 2. увеличиваем локальную копию на один
                    // Но второй поток в этот же самым момент делает то же самое
                    // 3. записываем значение в память
                    // Но второй поток в этот же самым момент делает то же самое и затирает наше изменение
                    counter2++;

                    // код не может выполняться параллельно, так что никаких проблем нет
                    // код становится "атомарным"
                    synchronized (counter3lock) {
                        counter3++;
                    }
                    // AtomicInteger - theadsafe объект, явная синхронизация не нужна. Так как операция ++ для него атомарная
                    counter4.getAndIncrement();
                }
                // сообщаем о завершении работы потока (уменьшаем счетчик на 1)
                onfinish.countDown();
            }).start();
        }
        // ждем завершения всех потоков (значение счетчика = 0)
        onfinish.await();
        System.out.println("Без синхронизации: counter=" + counter);
        System.out.println("С модификатором volatile counter2=" + counter2);
        System.out.println(counter3);
        System.out.println(counter4.get());
    }

    @Test(timeout = 30000L)
    public void testSynchronized() throws InterruptedException {

        class Object1 {
            // shared state
            private int counter = 0;
            private int counter2 = 0;

            private final Object lock = new Object();
            private final Object lock2 = new Object();

            public void inc1() {
                synchronized (lock) {
                    counter++;
                }
                if (counter % 100 == 0) {
                    System.out.println("inc1 counter=" + counter);
                }
            }


            public void dec1() {
                synchronized (lock) {
                    counter--;
                }
                if (counter % 100 == 0) {
                    System.out.println("dec1 counter=" + counter);
                }
            }
            public void inc2() {
                synchronized (lock2) {
                    counter2++;
                }
                if (counter2 % 100 == 0) {
                    System.out.println("inc2 counter=" + counter2);
                }
            }

            public void dec2() {
                synchronized (lock2) {
                    counter2--;
                }
                if (counter2 % 100 == 0) {
                    System.out.println("dec2 counter2=" + counter2);
                }
            }
        }

        final Object1 a = new Object1();
        final Object1 b = new Object1();
        // (a.inc1 и a.dec1), (a.inc2 и a.dec2) не могут выполняться одновременно так как синхронизированы, но это не даст нам потерять изменения в счетчике из-за race conditions
        // Ничто не мешает выполняться одновременно (a.inc1 и b.dec1), (a.inc1 и b.inc1), (a.inc2 и b.inc1), (a.inc2 и b.inc2) и т.п.,
        // так как это разные объекты. У разных объектов - разные состояния (синхронизация на уровне объектов a.lock, b.lock, a.lock2, b.lock2 - все разные объекты).
        // (a.inc1 и a.inc2) (a.inc1 и a.dec2) и т.п. - могут выполняться одновременно (по той же причине a.lock, a.lock2 - разные объекты)

        final CountDownLatch onfinish = new CountDownLatch(8);
        ExecutorService parallerExecutorService = Executors.newFixedThreadPool(8);

        try {
            parallerExecutorService.execute(() -> {
                for (int i = 0; i < 1000; i++) a.inc1();
                onfinish.countDown();
            });
            parallerExecutorService.execute(() -> {
                for (int i = 0; i < 1000; i++) a.dec1();
                onfinish.countDown();
            });
            parallerExecutorService.execute(() -> {
                for (int i = 0; i < 1000; i++) a.inc2();
                onfinish.countDown();
            });
            parallerExecutorService.execute(() -> {
                for (int i = 0; i < 1000; i++) a.dec2();
                onfinish.countDown();
            });

            parallerExecutorService.execute(() -> {
                for (int i = 0; i < 1000; i++) b.inc1();
                onfinish.countDown();
            });
            parallerExecutorService.execute(() -> {
                for (int i = 0; i < 1000; i++) b.dec1();
                onfinish.countDown();
            });
            parallerExecutorService.execute(() -> {
                for (int i = 0; i < 1000; i++) b.inc2();
                onfinish.countDown();
            });
            parallerExecutorService.execute(() -> {
                for (int i = 0; i < 1000; i++) b.dec2();
                onfinish.countDown();
            });

            // ждем выполнения всех
            onfinish.await();

            // как бы параллельно они не вызывались, благодаря синхронизации все ОК
            Assert.assertEquals(0, a.counter);
            System.out.println("a.counter = " + a.counter);
            Assert.assertEquals(0, a.counter2);
            System.out.println("a.counter2 = " + a.counter2);
            Assert.assertEquals(0, b.counter);
            System.out.println("b.counter = " + b.counter);
            Assert.assertEquals(0, b.counter2);
            System.out.println("b.counter2 = " + b.counter2);
        } finally {
            parallerExecutorService.shutdown();
        }
    }
}

class SingletonLazyInitialization {
    private static SingletonLazyInitialization instance = null;

    public static SingletonLazyInitialization getInstance() {
        if (instance == null) {
            synchronized (SingletonLazyInitialization.class) {
                if (instance == null) {
                    instance = new SingletonLazyInitialization();
                }
            }
        }
        return instance;
    }
}