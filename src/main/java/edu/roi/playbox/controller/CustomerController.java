package edu.roi.playbox.controller;

import edu.roi.playbox.domain.Customer;
import edu.roi.playbox.domain.NotificationMethod;
import edu.roi.playbox.domain.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author apavelchuk
 * @since 21.07.2015.
 */
@Controller
@SessionAttributes("customer")
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

    @RequestMapping(value = "edit_customer", method = RequestMethod.GET)
    public String viewAddCustomerForm(Model model, @RequestParam(value = "customerId", required = false) Long customerId) {
        Customer customer = customerId == null ? new Customer() : customerDao.findById(customerId);
        model.addAttribute("customer", customer);
        model.addAttribute("notificationMethods", NotificationMethod.values());
        return "edit_customer";
    }

    @RequestMapping(value = "edit_customer", method = RequestMethod.POST)
    public String submitCustomerForm(@ModelAttribute Customer customer, Model model) {
        customer = customerDao.saveOrUpdate(customer);
        model.addAttribute("customer", customer);
        return "edit_customer";
    }

    @RequestMapping(value = "/list_customers", method = RequestMethod.GET)
    public String listCustomers(Model model) {
        final List<Customer> customerList = customerDao.findAll();
        assert customerList != null;
        model.addAttribute("customerList", customerList);
        return "list_customers";
    }

//    @RequestMapping(value = "/list_customers", method = RequestMethod.GET, params = "block")
//    public String block(Model model, @RequestParam("block") Long customerId) {
//        Customer customer  = customerDao.findById(customerId);
//        assert customer != null;
//        customer.setBlocked(true);
//        customerDao.saveOrUpdate(customer);
//        return "redirect:/list_customers";
//    }
//
//    @RequestMapping(value = "//list_customers", method = RequestMethod.GET, params = "expire")
//    public String expire(Model model, @RequestParam("expire") Long customerId) {
//        Customer customer  = customerDao.findById(customerId);
//        assert customer != null;
//        customer.setExpired(new Date());
//        customerDao.saveOrUpdate(customer);
//        return "redirect:/list_customers";
//    }

//    @ModelAttribute("customerList")
//    public List<Customer> findAll() {
//        return customerDao.findAll();
//    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
}
