package com.app.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ResourceNotFoundException.ResourceNotFoundException;
import com.app.entities.Admin;
import com.app.entities.Categories;
import com.app.repository.AdminRepository;
import com.app.services.AdminService;

@Service
@Transactional
public class AdminImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin addnewAdmin(Admin admin) {
       
        for (Categories category : admin.getCategories()) {
            category.setAdmin(admin);  
        }
        return adminRepository.save(admin);
    }

    @Override
    public Admin updateAdminDetails(Admin admin) {
       
        for (Categories category : admin.getCategories()) {
            category.setAdmin(admin);  
        }
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public void deleteAdminDetails(Long adminId) {
        adminRepository.deleteById(adminId);
    }

    @Override
    public Admin getById(Long adminId) {
        Optional<Admin> o = adminRepository.findById(adminId);
        return o.orElseThrow(() -> new ResourceNotFoundException("Admin ID not found"));
    }
}
