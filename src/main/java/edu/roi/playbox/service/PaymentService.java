package edu.roi.playbox.service;

import edu.roi.playbox.domain.dto.PaymentDetailsDto;
import edu.roi.playbox.domain.dto.ResultStatus;

/**
 * @author apavelchuk
 * @since 20.07.2015.
 */
public interface PaymentService<T extends PaymentDetailsDto> {

    ResultStatus create(T paymentDetails);
}
