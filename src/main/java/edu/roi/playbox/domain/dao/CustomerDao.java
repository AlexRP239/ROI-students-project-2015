package edu.roi.playbox.domain.dao;

import edu.roi.playbox.domain.Customer;

import java.util.List;

/**
 * Created by AlexRP239 on 19.07.2015.
 */
public interface CustomerDao {

    /**
     * ��������� ������ (���� id == null) ��� ��������� �������������
     */
    Customer saveOrUpdate(Customer customer);

    /**
     * ���������� ������������� ��������� ���� ���� (�� ����� � ����� ��������)
     */
    Customer findById(Long customerId);

    /**
     * ���������� ������������� ��������� ������ ���� expired < �������� ������� � blocked = null ��� blocked = false
     */
    Customer findActive(Long customerId);

    List<Customer> findAll();

//    �� �������
//    List<Customer> findActive();
//    List<Customer> findBlocked();
//    List<Customer> findExpired();
}
