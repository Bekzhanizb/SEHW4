package bekezhan.io.lab4.controller;

import bekezhan.io.lab4.entity.ApplicationRequest;
import bekezhan.io.lab4.service.ApplicationRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/requests")
public class ApplicationRequestController {

    private final ApplicationRequestService service;

    public ApplicationRequestController(ApplicationRequestService service) {
        this.service = service;
    }

    @GetMapping("")
    public String allRequests(Model model) {
        model.addAttribute("requests", service.getAllRequests());
        return "requests";
    }

    @GetMapping("/new")
    public String newRequestForm(Model model) {
        model.addAttribute("request", new ApplicationRequest());
        return "new-request";
    }

    @PostMapping("")
    public String saveRequest(@ModelAttribute ApplicationRequest request) {
        service.save(request);
        return "redirect:/requests";
    }

    @GetMapping("/view/{id}")
    public String detailRequest(@PathVariable Long id, Model model) {
        model.addAttribute("request", service.getRequest(id).orElseThrow());
        return "detail";
    }

    @PostMapping("/{id}/process")
    public String processRequest(@PathVariable Long id) {
        ApplicationRequest req = service.getRequest(id).orElseThrow();
        req.setHandled(true);
        service.save(req);
        return "redirect:/requests/view/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteRequest(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/requests";
    }

    @GetMapping("/handled")
    public String handled(Model model) {
        model.addAttribute("requests", service.getRequestsByHandled(true));
        return "requests";
    }

    @GetMapping("/pending")
    public String pending(Model model) {
        model.addAttribute("requests", service.getRequestsByHandled(false));
        return "requests";
    }
}
