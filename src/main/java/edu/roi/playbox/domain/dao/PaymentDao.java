package edu.roi.playbox.domain.dao;

import edu.roi.playbox.domain.Payment;

/**
 * Created by AlexRP239 on 19.07.2015.
 */
public interface PaymentDao {
    /**
     * ��������� ������ (���� id == null) ��� ��������� �������������
     * � ���� �� ������ ����������� ���� created � modified!
     */
    Payment saveOrUpdate(Payment customer);

    //todo: alexrp239 define other required methods
}
