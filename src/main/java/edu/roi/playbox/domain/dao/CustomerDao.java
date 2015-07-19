package edu.roi.playbox.domain.dao;

import edu.roi.playbox.domain.Customer;

import java.util.List;

/**
 * Created by AlexRP239 on 19.07.2015.
 */
public interface CustomerDao {

    /**
     * Сохраняет нового (если id == null) или обновляет существующего
     */
    Customer saveOrUpdate(Customer customer);

    /**
     * Возвращает существующего кастомера если есть (не важно с каким статусом)
     */
    Customer findById(Long customerId);

    /**
     * Возвращает существующего кастомера только если expired < текущего времени и blocked = null или blocked = false
     */
    Customer findActive(Long customerId);

    List<Customer> findAll();

//    на будущее
//    List<Customer> findActive();
//    List<Customer> findBlocked();
//    List<Customer> findExpired();
}
