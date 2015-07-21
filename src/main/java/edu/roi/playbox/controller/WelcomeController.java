package edu.roi.playbox.controller;

import edu.roi.playbox.domain.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Alexey Pavelchuk
 */
@Controller
public class WelcomeController {

    @Autowired
    private UserDao userDao;

    // Если внутри сервисного метода (singleton) хранится состояние, то данные должны быть должным образом сихнронизированы
    // Просто private int visitorCount; visitorCouтt++ не подходит, потому что операция ++ не атомарная
    private final AtomicInteger visitorCount = new AtomicInteger(0);

    @RequestMapping(value = "welcome")
    public String welcome(Model model, @RequestParam String userName) {
        model.addAttribute("visitorCount", visitorCount.incrementAndGet());
        model.addAttribute("userName", userName);
        return "welcome"; // /WEB-INF/templates/welcome.html
    }

    @RequestMapping("mvc/credentials")
    public ModelAndView credentials() {
        final ModelAndView modelAndView = new ModelAndView("credentials"); // /WEB-INF/templates/credentials.html
        modelAndView.addObject("users", userDao.findAll());
        return modelAndView;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
