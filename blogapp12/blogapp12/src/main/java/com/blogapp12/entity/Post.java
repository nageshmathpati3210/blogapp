package com.blogapp12.entity;


import javax.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Post
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String name;

    private String message;

    private String description;

    @OneToMany(mappedBy ="post",orphanRemoval = true,cascade = CascadeType.ALL)
    List<Comment> commentList=new ArrayList<>();

}
