package com.blogapp12.entity;


import javax.persistence.*;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class User
{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;


    private String email;

    @Column(name = "user_name")
    private  String userName;


    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Set<Roles> roles;


}
