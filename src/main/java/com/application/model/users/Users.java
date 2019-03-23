package com.application.model.users;

import com.application.model.lesson.Lesson;
import com.application.model.lesson.LessonScore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class Users {
    public Users() {
    }

    public Users(Users users) {
        this.active = users.getActive();
        this.email = users.getEmail();
        this.login = users.getLogin();
        this.roles = users.getRoles();
        this.name = users.getName();
        this.lastName =users.getLastName();
        this.id = users.getId();
        this.password = users.getPassword();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name="users_seq", sequenceName = "USERS_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Column(name = "login" ,nullable = false,unique = true)
    private String login;
    @Column(name = "password" ,nullable = false)
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "active")
    private int active;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "user_lessons", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "lesson_id"))
    private Set<Lesson> lessons;
    @OneToOne( cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_score_id")
    private LessonScore lessonScore;

    //Geters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @JsonIgnore
    public String getPassword() {
        return password;
    }
    @JsonProperty
    public void setPassword(String password) {
        this.password = password ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public Users setLogin(String login) {
        this.login = login;
        return this;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public Users setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
        return this;
    }

    public LessonScore getLessonScore() {
        return lessonScore;
    }

    public Users setLessonScore(LessonScore lessonScore) {
        this.lessonScore = lessonScore;
        return this;
    }
}
