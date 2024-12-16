package org.mdasolutions.web.Controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.mdasolutions.web.Entities.Message;
import org.mdasolutions.web.Entities.Services;
import org.mdasolutions.web.Entities.User;
import org.mdasolutions.web.Repos.MessageRepo;
import org.mdasolutions.web.Services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PageController {

    @Autowired
    private ServicesService servicesService;

    @Autowired
    private final MessageRepo messageRepo;

    @GetMapping("/index")
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("/feedback")
    public String showFeedbackPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth";
        }

        List<Message> userMessages = messageRepo.findAllByAuthor(user.getLogin());
        model.addAttribute("messages", userMessages);

        return "feedback";
    }

    @PostMapping("/feedback")
    public String saveMessage(
            @RequestParam("content") String content,
            @RequestParam("recipient") String recipient,
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth";
        }

        Message message = new Message();
        message.setContent(content);
        message.setRecipient(recipient);
        message.setAuthor(user.getLogin());
        message.setCreatedAt(LocalDateTime.now());

        messageRepo.save(message);
        return "redirect:/feedback";
    }

    @PostMapping("/services/add")
    public String addService(Services service) {
        servicesService.addService(service);
        return "redirect:/services";
    }

    @GetMapping("/reviews")
    public String showReviewsPage() {
        return "reviews";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }

    @GetMapping("/contacts")
    public String showContactsPage() {
        return "contacts";
    }

    @GetMapping("/reg")
    public String showRegPage() {
        return "reg";
    }

    @GetMapping("/auth")
    public String showAuthPage() {
        return "auth";
    }

    @GetMapping("/health")
    public ResponseEntity<?> check() {
        return ResponseEntity.ok().body(ResponseEntity.ok().build());
    }

    @GetMapping("/teapot")
    public ResponseEntity<String> getTeapotResponse() {
        return ResponseEntity
                .status(HttpStatus.I_AM_A_TEAPOT)
                .body(HttpStatus.I_AM_A_TEAPOT.getReasonPhrase());
    }
}
