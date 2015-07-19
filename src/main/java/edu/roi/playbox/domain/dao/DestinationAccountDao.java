package edu.roi.playbox.domain.dao;

import edu.roi.playbox.domain.DestinationAccount;

import java.util.List;

/**
 * Created by AlexRP239 on 19.07.2015.
 */
public interface DestinationAccountDao {

    /**
     * Сохраняет нового (если id == null) или обновляет существующего
     */
    DestinationAccount saveOrUpdate(DestinationAccount customer);

    /**
     * Возврашает спосок всех аккаунтов у который enabled = true
     */
    List<DestinationAccount> findEnabled();
}
