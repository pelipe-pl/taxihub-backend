package com.herokuapp.backend.corporation;

import com.herokuapp.backend.driver.DriverDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class SignUpController {

    @RequestMapping("/corporation/signup")
    public String signUp (WebRequest request, Model model){
        DriverDto driver = new DriverDto();
        model.addAttribute("driver", driver);
        return "signup";
    }
}
