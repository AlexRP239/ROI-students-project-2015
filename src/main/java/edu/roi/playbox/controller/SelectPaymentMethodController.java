package edu.roi.playbox.controller;

import edu.roi.playbox.domain.Customer;
import edu.roi.playbox.domain.Payment;
import edu.roi.playbox.domain.dao.CustomerDao;
import edu.roi.playbox.domain.dao.PaymentDao;
import org.hibernate.jpa.criteria.expression.function.CurrentDateFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

/**
 * Created by AlexRP239 on 19.07.2015.
 */
@Controller
public class SelectPaymentMethodController {

    private static final Logger LOG = LoggerFactory.getLogger(SelectPaymentMethodController.class);

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private PaymentDao paymentDao;

    // пример как можно вернуть http 403
    @RequestMapping("example/403")
    public String showHttp403(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
        return null;
    }

    @RequestMapping("payment/{customerId}")
    public String payment(
            @RequestParam("secretKey") String secretKey,
            @RequestParam("invoiceId") String invoiceId,
            @RequestParam("amount") BigDecimal amount,
            @PathVariable("customerId") Long customerId,
            HttpServletResponse response,
            Model model) throws IOException {
        //Проверка что customer существует
        Customer customer = null;
        try {
            customer = customerDao.findById(customerId);
        } catch (NoResultException ex) {
            LOG.debug("Customer not found by id {}", customerId);
        }
        // Проверяем secretKey
        if (customer == null
                || !customer.getSecretKey().equals(secretKey)
                || (customer.getExpired() != null && customer.getExpired().before(new Date()))
                || Boolean.TRUE.equals(customer.getBlocked())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
            return null;
        }
        Optional<Payment> paymentOptional = paymentDao.findByInvoiceAndCustomer(customer, invoiceId);
        if (paymentOptional.isPresent()) {
            model.addAttribute("errorDescription", "Такой платеж уже сущестует");
            return "errordescription";
        }
        Payment payment = new Payment();
        payment.setCustomer(customer);
        payment.setInvoiceId(invoiceId);
        payment.setAmount(amount);
        payment = paymentDao.saveOrUpdate(payment);
        model.addAttribute("payment", payment);
        // todo: заполнить модель данными
        return "payment";
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void setPaymentDao(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }
}
