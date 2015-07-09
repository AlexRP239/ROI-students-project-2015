package edu.roi.playbox.domain.dao;

import org.junit.*;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Пример unit теста с испоользованием junit framework
 *
 * Если у вас есть класс some.package.SomeClass в src/main/java и вы хотите его протестировать -
 * надо создать класс some.package.SomeClassTest в src/test/java
 * В таком случае все public, protected, package-private доступно тесту, только private метожы и поля недоступны
 *
 * Юнит тесты запускаются сборкой автоматически командой gradle build ну или gradle test
 * Если надо запусить сборку без тестов - gradle assemble
 * Отчет о результатах будет лежать в папке build\reports\tests и build\test-results
 *
 * Также их можно запускать через IDE (конкретный тест или даже конкретный метод в тесте)
 * В нем нет main() метода, если хотите понять, а кто же именно его запускает - см. stacktrace
 * com.intellij.rt.execution.application.AppMain.main
 *
 * В тестах опять же используются аннотации.
 * @Test - тестовый метод. Если он бросает exception, не указанный как expected - значит тест failed.
 * Каждый тестовый метод запускается независимо от других. Вы не можете
 * @BeforeClass - запускатеся один раз перед всеми тестами
 * @Before - запускается перед каждым тестом
 * @AfterClass запускатеся один раз после всех тестов
 * @After - запускается после каждого теста
 * @Ignore - не запускать тест
 * Методы помеченные этими аннотациями должны быть public void
 * Для @BeforeClass, @AfterClass - public void static
 * В них не надо отлавливать никакие ошибки, если надо, иожно использовать throws ...
 *
 *
 * @RunWith - тоже часто используется. Позводяет встроится в процесс запуска теста и сделать какие-то доп. действия
 * Нам придется использовать
 * @RunWith(SpringJUnit4ClassRunner.class) - если мы хотим запускать контектс со всеми нашими реаьлными классами
 * (обычно это называетися интеграционный тест, когда тестируется не один класс)
 * @RunWith(MockitoJUnitRunner.class) - если мы хотим использовать заглушки (моки) вместо реальных объектов
 * и классов, от которых зависит тестируемый класс (собственно юнит тест)
 *
 * Есть и другие аннотации, у некоторых есть параметры. Об этом можно почитать в офиц. документации
 * А здесь только совсем базовые
 *
 * @author apavelchuk
 * @since 07.07.2015.
 */

public class ReadFileTest {
    private static List<String> messages;


    @Ignore("Хватит его уже запускать. Это просто пример")
    @Test(expected = CharacterCodingException.class)
    public void readDarkenery() throws Exception {
        System.out.println("Java 8 style круче");
        final List<String> lines = Files.readAllLines(Paths.get("src/test/resources/Darkenery.txt"), Charset.forName("windows-1251"));
        lines.forEach(System.out::println);
    }

    @Ignore
    @Test
    public void readDarkeneryFailed() throws Exception {
        System.out.println("Java 6 style проще");
        final List<String> lines = Files.readAllLines(Paths.get("src/test/resources/Darkenery.txt"));
        for (String line : lines) {
            System.out.println(line);
        }
    }

    @BeforeClass
    public static void beforeClass() {
        messages = Collections.synchronizedList(new ArrayList<>());
        messages.add("beforeClass должен запуститься первым.");
    }

    @AfterClass
    public static void  afterClass() {
        messages.add("afterClass должен запуститься последним.");
        System.out.println("Реальный порядок событий:");
        messages.forEach(System.out::println);
        messages = null;
    }

    @Before
    public void before() {
        printTestNameAndTestClassInstance("before test");
    }

    @After
    public void after() {
        printTestNameAndTestClassInstance("after test");
    }

    // имя теста должно явно говорит о том, что вы тестируете. Оно может быть достаточно длинным
    @Test
    public void doubleArithmeticIsNotPrecise() {
        printTestNameAndTestClassInstance("doubleArithmeticIsNotPrecise");

        // типичный простой тест состоит из 3х пунктов
        // подготовить входной набор данных
        double one = 1.0 / 3.0;
        double two = 2.0 / 3.0;


        class ClassToTest {
            public double sum(double x, double y) {
                return x + y;
            }
        }
        // вызвать тестируемый метод
        double three = new ClassToTest().sum(one, two);


        // проверить результат. Для этого используется утилитарных класс Assert
        Assert.assertEquals(1.0, three, 1E-10);

        // ну а если условие не выполнено, то тест упадет
        boolean expectedExceptionThrown = false;
        try {
            Assert.assertEquals(1.0, three);
        } catch (AssertionError e) {
            // стоило ожидать...
            expectedExceptionThrown = true;

        }
        Assert.assertTrue(expectedExceptionThrown);
    }

    // имя теста должно явно говорит о том, что вы тестируете.
    @Test
    public void testNothing() {
        printTestNameAndTestClassInstance("testNothing");
    }

    // имя теста должно явно говорит о том, что вы тестируете.
    @Test
    public void printStackTraceToKnowWhoRunsMe()  {
        printTestNameAndTestClassInstance("printStackTraceToKnowWhoRunsMe");

        try {
            throw new Exception("Exception thrown and catched");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private void printTestNameAndTestClassInstance(String testName) {
        messages.add(testName + " " + System.identityHashCode(this));
    }

}
