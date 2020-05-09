package no.message.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@Component
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "index";
    }
}
