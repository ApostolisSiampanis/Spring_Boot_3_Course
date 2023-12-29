package com.example.demosecurity.controller;

import com.example.demosecurity.dto.UserDto;
import com.example.demosecurity.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Logger;

@Controller
public class AuthController {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

    @GetMapping("/register/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {
        theModel.addAttribute("userDto", new UserDto());
        return "registration-form";
    }

    @PostMapping("/register/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("userDto") UserDto userDto,
            BindingResult bindingResult,
            HttpSession session, Model model) {

        String userName = userDto.getUserName();
        logger.info("Processing registration form for: " + userName);

        // form validation
        if (bindingResult.hasErrors()) {
            return "registration-form";
        }

        // check the database if user already exists
        UserDto existing = userService.findByUserName(userName);
        if (existing != null) {
            model.addAttribute("userDto", new UserDto());
            model.addAttribute("registrationError", "User name already exists.");

            logger.warning("User name already exists.");
            return "registration-form";
        }

        // create user account and store in the database
        userService.save(userDto);

        logger.info("Successfully created user: " + userName);

        // place user in the web http session for later use
        session.setAttribute("userDto", userDto);

        return "registration-confirmation";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
}
