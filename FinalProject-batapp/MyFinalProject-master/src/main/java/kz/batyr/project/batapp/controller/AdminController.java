package kz.batyr.project.batapp.controller;

import kz.batyr.project.batapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final CategoryService categoryService;
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/admin-panel")
    public String adminPage(Model model){
        model.addAttribute("categories", categoryService.getCategories());
        return "adminPage";
    }

}
