package com.blogapp12.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "roles")
@Entity
@Getter
@Setter
public class Roles
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;


}
