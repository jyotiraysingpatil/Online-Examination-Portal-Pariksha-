package com.app.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adminId;

   
    @Column(name = "first_name")
    private String firstName;
  
    @Column(name = "last_name")
    private String lastName;

    
    @Column(name = "username", unique = true)
    private String username;

   
    private String password;

    @Column(name = "is_active")
    private boolean isActive = true;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Categories> categories = new ArrayList<>();
}
