package com.example.theatre.controller;

import com.example.theatre.entity.Client;
import com.example.theatre.entity.Role;
import com.example.theatre.service.ClientService;
import com.example.theatre.service.RoleService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String authView(Model model) {
        model.addAttribute("client", new Client());
        return "login";
    }

    @GetMapping( "/login")
    public String loginView() {
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerView(Model model) {
        model.addAttribute("client", new Client());
        return "register";
    }

    @RequestMapping("/register-client")
    public String registerClient(@Valid @ModelAttribute("client") Client client, BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            if (client.getPhone().startsWith("8")) {
                client.setPhone("+7" + client.getPhone().substring(1));
            }
            Role role = roleService.findByRoleName("USER");
            client.setRole(role);
            Client clientDb = clientService.addClient(client);
            session.setAttribute("client", clientDb);
            model.addAttribute("client", clientDb);
            return "events";
        } catch (DataIntegrityViolationException e) {
            handleDbUniqueErrors(e, bindingResult);
            return "register";
        }
    }

    @RequestMapping("/login-client")
    public String loginClient(@Valid @ModelAttribute("client") Client client,
                              BindingResult bindingResult,
                              Model model,
                              @RequestParam("loginByPhone") boolean loginByPhone,
                              HttpSession session) {

        if (loginByPhone) {
            if (bindingResult.hasFieldErrors("phone") || bindingResult.hasFieldErrors("password")) {
                return "login";
            }

            if (client.getPhone().startsWith("8")) {
                client.setPhone("+7" + client.getPhone().substring(1));
            }
            Client clientCheck = clientService.checkLoginByPhone(client.getPhone(), client.getPassword());
            if (clientCheck == null) {
                model.addAttribute("error", "Неверный email или пароль. Попробуйте снова.");
                System.out.println("Неудачный вход");
                return "login";
            }
            session.setAttribute("client", clientCheck);
            model.addAttribute("client", clientCheck);
            if (clientCheck.getRole().getName().equals("USER")) {
                return "redirect:/events";
            } else {
                return "admin";
            }
        } else {
            // Вход по email
            if (bindingResult.hasFieldErrors("email") || bindingResult.hasFieldErrors("password")) {
                return "login";
            }
            Client clientCheck = clientService.checkLoginByEmail(client.getEmail(), client.getPassword());
            if (clientCheck == null) {
                model.addAttribute("error", "Неверный email или пароль. Попробуйте снова.");
                System.out.println("Неудачный вход");
                return "login";
            }

            session.setAttribute("client", clientCheck);
            model.addAttribute("client", clientCheck);
            if (clientCheck.getRole().getName().equals("USER")) {
                return "redirect:/events";
            } else {
                return "admin";
            }
        }
    }


    private void handleDbUniqueErrors(
            DataIntegrityViolationException e,
            BindingResult bindingResult
    ) {
        String errorMsg = e.getMostSpecificCause().getMessage();

        if (errorMsg.contains("email")) {
            bindingResult.rejectValue("email", "email.unique",
                    "Этот email уже занят");
        } else if (errorMsg.contains("phone")) {
            bindingResult.rejectValue("phone", "phone.unique",
                    "Этот телефон уже занят");
        }
    }

}
