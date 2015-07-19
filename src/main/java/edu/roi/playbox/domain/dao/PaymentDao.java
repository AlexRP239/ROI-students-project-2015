package edu.roi.playbox.domain.dao;

import edu.roi.playbox.domain.Payment;

/**
 * Created by AlexRP239 on 19.07.2015.
 */
public interface PaymentDao {
    /**
     * Сохраняет нового (если id == null) или обновляет существующего
     * В этом же методе указываются даты created и modified!
     */
    Payment saveOrUpdate(Payment customer);

    //todo: alexrp239 define other required methods
}
