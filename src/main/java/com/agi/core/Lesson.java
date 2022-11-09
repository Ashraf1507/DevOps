package com.agi.core;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "lesson_name")
    private String name;

    @Column(name = "lesson_desc")
    private String desc;

    @Column(name = "lesson_url")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
}