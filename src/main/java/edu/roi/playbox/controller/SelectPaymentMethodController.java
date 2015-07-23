package edu.roi.playbox.controller;

import edu.roi.playbox.domain.Customer;
import edu.roi.playbox.domain.dao.CustomerDao;
import org.hibernate.jpa.criteria.expression.function.CurrentDateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by AlexRP239 on 19.07.2015.
 */
@Controller
public class SelectPaymentMethodController {

    @Autowired
    private CustomerDao CustomerDao;

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
    public void parseParametersExample(
            @RequestParam("secretKey") String secretKey,
            @RequestParam("invoiceId") Long invoiceId,
            @RequestParam("amount") BigDecimal amount,
            @PathVariable("customerId") Long customerId,
            HttpServletResponse response) throws IOException {
        //Проверка CustomerDao
        Customer customerExample = CustomerDao.findById(customerId);
        if (customerExample == null) response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
        else if (customerExample.getSecretKey() != secretKey ||
                customerExample.getExpired().before(new Date()) == true ||
                customerExample.getBlocked() == true) response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
        Date d = new Date();
        //Проверка PaymentDao



    }
}
