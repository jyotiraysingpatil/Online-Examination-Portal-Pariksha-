package com.app.services;

import java.util.List;

import com.app.entities.Admin;

public interface AdminService {
Admin addnewAdmin(Admin admin);
Admin updateAdminDetails(Admin admin);
List<Admin> getAllAdmins();
void deleteAdminDetails(Long adminId);
Admin getById(Long adminId);





}
