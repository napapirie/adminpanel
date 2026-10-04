package com.example.corporate.controller;
import com.example.corporate.service.UserService; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.GetMapping;
@Controller public class AdminPageController {private final UserService service; public AdminPageController(UserService s){service=s;} @GetMapping("/") String root(){return "redirect:/admin";} @GetMapping("/admin") String admin(Model m){m.addAttribute("users",service.all());return "admin";} }
