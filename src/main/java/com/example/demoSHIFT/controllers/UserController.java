package com.example.demoSHIFT.controllers;
import com.example.demoSHIFT.models.Interval;
import com.example.demoSHIFT.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/api/v1/intervals/")
    public String intervals(Model model) {
        model.addAttribute("intervals", service.json());
        return "intervals";
    }

    @PostMapping("/api/v1/intervals/min")
    public String minInterval(@RequestParam String kind) {
        if (kind.equals("digits")) {
            System.out.println(service.getMinIntervalNumber());
        }
        else if (kind.equals("letters")) {
            System.out.println(service.getMinIntervalLetter());
        }
        return "redirect:/api/v1/intervals/";
    }

    @PostMapping("/api/v1/intervals/add")
    public String addInterval(Interval interval) {
        if (service.isValidInterval(interval)) {
            service.addInterval(interval);
        }
        return "redirect:/api/v1/intervals/";
    }

    @PostMapping("/api/v1/intervals/merge")
    public String mergeInterval(@RequestParam("kind")String kind) {
        List<Interval> subList = service.createSubList(kind);
        service.sendToDB(service.mergeIntervals(subList), kind);
        return "redirect:/api/v1/intervals/";
    }

    @PostMapping("/api/v1/intervals/clear")
    public String clear() {
        service.clear();
        return "redirect:/api/v1/intervals/";
    }

}
