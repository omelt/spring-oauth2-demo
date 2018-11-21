package com.example.springsecuritydemo.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;


@Entity
public class Role implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return name;
    }

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String name;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
