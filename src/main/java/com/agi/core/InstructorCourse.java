package com.agi.core;

import com.agi.core.user.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "instructor_courses")
public class InstructorCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "instructor_id")
    private User instructor;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "course_id")
    private Course course;

    public InstructorCourse(User instructor, Course course) {
        this.instructor = instructor;
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        InstructorCourse that = (InstructorCourse) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
