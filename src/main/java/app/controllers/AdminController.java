package app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import app.model.Admin;
import app.service.AdminService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService)
    {
        this.adminService = adminService;
    }


    @GetMapping(value = "/signin/{login}/{password}")
    public String  signin(@PathVariable String login,
                           @PathVariable String password) {
        Admin admin =  adminService.singin(login, password);
        if(admin != null){
            return "{\"status\": \"true\"}";
        }
        return "{\"status\": \"false\"}";
    }

}
