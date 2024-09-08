package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Admin;
import com.app.services.AdminService;
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/insert")
    public ResponseEntity<String> addAdmin(@RequestBody Admin admin) {
        Admin a = adminService.addnewAdmin(admin);
        return ResponseEntity.ok("Admin details added successfully: " + a);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAdmin(@RequestBody Admin admin) {
        Admin a = adminService.updateAdminDetails(admin);
        return ResponseEntity.ok("Admin details updated successfully: " + a);
    }

    @GetMapping("/getAllAdmins")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> a = adminService.getAllAdmins();
        return ResponseEntity.ok(a);
    }

    @DeleteMapping("/delete/{adminId}")
    public ResponseEntity<String> deleteAdminDetails(@PathVariable Long adminId) {
        adminService.deleteAdminDetails(adminId);
        return ResponseEntity.ok("Deleted details successfully");
    }

    @GetMapping("/GetByAdminId/{adminId}")
    public Admin getAdminById(@PathVariable Long adminId) {
        return adminService.getById(adminId);
    }
}
