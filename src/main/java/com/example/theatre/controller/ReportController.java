package com.example.theatre.controller;

import com.example.theatre.entity.Client;
import com.example.theatre.service.EventService;
import com.example.theatre.service.ReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private EventService eventService;

    @GetMapping("/preferences")
    public String preferences(Model model, HttpSession session) {
        Client client = (Client) session.getAttribute("client");
        if (client == null || !client.isAdmin()) {
            return "redirect:/access-denied";
        }
        List<Object[]> preferences = reportService.getPopularPlaces();
        model.addAttribute("preferences", preferences);
        return "preferences";
    }

    @GetMapping("/popular")
    public String popular(Model model, HttpSession session) {
        Client client = (Client) session.getAttribute("client");
        if (client == null || !client.isAdmin()) {
            return "redirect:/access-denied";
        }
        List<Object[]> popular = eventService.getPopularEvents();
        model.addAttribute("populars", popular);
        return "popular";
    }

}
