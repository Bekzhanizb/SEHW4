package bekezhan.io.lab4.controller;

import bekezhan.io.lab4.entity.Request;
import bekezhan.io.lab4.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RequestController {

    @Autowired
    private RequestService service;

    @GetMapping("/")
    public String listAll(Model model) {
        model.addAttribute("requests", service.findAll());
        return "index";
    }

    @GetMapping("/pending")
    public String listPending(Model model) {
        model.addAttribute("requests", service.findByHandled(false));
        return "pending";
    }

    @GetMapping("/processed")
    public String listProcessed(Model model) {
        model.addAttribute("requests", service.findByHandled(true));
        return "processed";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("request", new Request());
        return "add";
    }

    @PostMapping("/save")
    public String saveRequest(@ModelAttribute Request request) {
        service.save(request);
        return "redirect:/";
    }

    @GetMapping("/view/{id}")
    public String viewRequest(@PathVariable Long id, Model model) {
        model.addAttribute("request", service.findById(id).orElse(null));
        return "view";
    }

    @PostMapping("/process/{id}")
    public String processRequest(@PathVariable Long id) {
        service.processRequest(id);
        return "redirect:/view/" + id;
    }

    @PostMapping("/delete/{id}")
    public String deleteRequest(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/";
    }
}