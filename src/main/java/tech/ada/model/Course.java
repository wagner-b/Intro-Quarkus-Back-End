package tech.ada.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.inject.Inject;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "courses")
public class Course extends PanacheEntity {
    private String name;

    @Inject
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons;

    protected Course() {}

    public Course(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Lesson> getLessons() {
        return Collections.unmodifiableList(this.lessons);
    }

    public void changeName(String name) {
        this.name = Objects.requireNonNull(name, "name must not be null");
    }

    public void addLessons(Lesson lesson) {
        Lesson validatedLesson = Objects.requireNonNull(lesson,
                "lesson must not be null");
        this.lessons.add(validatedLesson);
    }
}
