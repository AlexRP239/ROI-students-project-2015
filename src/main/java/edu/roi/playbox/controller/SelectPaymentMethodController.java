package edu.roi.playbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by AlexRP239 on 19.07.2015.
 */
@Controller
public class SelectPaymentMethodController {

    // todo: inject PaymentDao, CustomerDao, DestinationAccountDao beans (не знаю как правильно по-русски inject перевести - вставить из контекста :)

    // todo: Необходимо реализовать метод, который будет обрабатывать пост запросы по адресу payment/{customerId}
    // todo: метод должен получить 3 доп. параметра из запроса - String secretKey, Long invoiceId, BigDecimal amount

    // пример как можно вернуть http 403
    @RequestMapping("example/403")
    public String showHttp403(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
        return null;
    }

    // пример как передавать параметры через адресную строку
    @RequestMapping("example/{customerId}")
    @ResponseBody
    public String parseParametersExample(
            @RequestParam("secretKey") String secretKey,
            @RequestParam("invoiceId") Long invoiceId,
            @RequestParam("amount") BigDecimal amount) {
        return "secretKey=" + secretKey + " invoiceId=" + invoiceId + " amount=" + amount;
    }
}