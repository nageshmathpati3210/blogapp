package com.blogapp12.entity;


import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String message;

    @ManyToOne
    @JoinColumn(name = "PostId")
    private  Post post;

}
