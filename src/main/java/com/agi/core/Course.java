package com.agi.core;

import com.agi.core.user.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NotNull
    @Column(name = "course_name")
    private String name;

    @NotNull
    @Column(name = "course_desc")
    private String desc;

    @NotNull
    @Column(name = "course_img")
    private String img;

    @NotNull
    @Column(name = "course_duration")
    private String duration;

    @NotNull
    @Column(name = "course_price")
    private String price;

    @NotNull
    @Column(name = "course_original_price")
    private String original_price;

}