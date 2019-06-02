package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    @Column(length = 500)
    String content;

    Date regDate;

    protected Post() {}

    public Post(String title, String content){
        this.title = title;
        this.content = content;
        this.regDate = new Date();
    }
}
