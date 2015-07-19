package edu.roi.playbox.domain.dao;

import edu.roi.playbox.domain.DestinationAccount;

import java.util.List;

/**
 * Created by AlexRP239 on 19.07.2015.
 */
public interface DestinationAccountDao {

    /**
     * ��������� ������ (���� id == null) ��� ��������� �������������
     */
    DestinationAccount saveOrUpdate(DestinationAccount customer);

    /**
     * ���������� ������ ���� ��������� � ������� enabled = true
     */
    List<DestinationAccount> findEnabled();
}
