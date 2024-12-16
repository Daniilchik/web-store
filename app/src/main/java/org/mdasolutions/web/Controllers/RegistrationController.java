package org.mdasolutions.web.Controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.mdasolutions.web.Entities.User;
import org.mdasolutions.web.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    @Autowired
    private final UserRepo userRepository;

    @PostMapping("/auth")
    public String login(
            @RequestParam("login") String login,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        System.out.println("Получен запрос на авторизацию: login=" + login);

        User user = userRepository.findByLoginAndPassword(login, password);
        if (user == null) {
            model.addAttribute("error", "Неверный логин или пароль");
            System.out.println("Ошибка: Неверный логин или пароль");
            return "auth";
        }

        session.setAttribute("user", user);
        System.out.println("Пользователь авторизован: " + user);

        return "redirect:/services";
    }

    @PostMapping("/reg")
    @Transactional
    public String registerUser(
            @RequestParam("login") String login,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirm-password") String confirmPassword,
            Model model) {

        System.out.println("Получен запрос на регистрацию: login=" + login + ", email=" + email);

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Пароли не совпадают");
            System.out.println("Ошибка: Пароли не совпадают");
            return "reg";
        }

        if (userRepository.existsByLogin(login) || userRepository.existsByEmail(email)) {
            model.addAttribute("error", "Пользователь с таким логином или email уже существует");
            System.out.println("Ошибка: Пользователь с таким логином или email уже существует");
            return "reg";
        }

        User newUser = new User();
        newUser.setLogin(login);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setCreated(LocalDateTime.now());

        userRepository.save(newUser);
        System.out.println("Пользователь успешно сохранен: " + newUser);

        return "redirect:/auth";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }
}