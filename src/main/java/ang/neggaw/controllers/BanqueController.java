package ang.neggaw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// @Controller
public class BanqueController {

    @GetMapping("/login")
    public String login() {
        return "seConnecter";
    }

    @PostMapping("/index")
    public String index() { return "index"; }
}
