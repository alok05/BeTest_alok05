package com.example.creditlimit.controller;

import com.example.creditlimit.model.SourceEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping(path = "/records")
    public String showSourceList(Model model) {
        model.addAttribute("sourceList", SourceEnum.values());
        return "index";
    }
}
