package edu.roi.playbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Alexey Pavelchuk
 */
@Controller
public class WelcomeController {

    // Если внутри сервисного метода (singleton) хранится состояние, то данные должны быть должным образом сихнронизированы
    // Просто private int visitorCount; visitorCouтt++ не подходит, потому что операция ++ не атомарная
    private final AtomicInteger visitorCount = new AtomicInteger(0);

    @RequestMapping(value = "welcome")
    public String welcome(Model model) {
        model.addAttribute("visitorCount", visitorCount.incrementAndGet());
        return "welcome"; // /WEB-INF/templates/welcome.html
    }
}
