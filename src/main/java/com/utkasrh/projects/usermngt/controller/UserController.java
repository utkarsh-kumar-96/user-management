package com.utkasrh.projects.usermngt.controller;

import com.utkasrh.projects.usermngt.entity.User;
import com.utkasrh.projects.usermngt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String homePage(Model model) {
    	return getAll(model);
    }

    @GetMapping("/users/all")
    public String getAll(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/users/add-user-form")
    public String saveUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "add-user";
    }

    @GetMapping("/users/edit-user-form/{id}")
    public String editUserForm(@PathVariable(value = "id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/users/add-user")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users/all";
    }

    @GetMapping("/user/remove/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/users/all";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam(value = "sortField") String sortField, @RequestParam(value = "sortDir") String sortDir, Model model) {
        int pageSize = 10;
        Page<User> userPage = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<User> userList = userPage.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("totalItems", userPage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listUsers", userList);
        return "index";
    }
}