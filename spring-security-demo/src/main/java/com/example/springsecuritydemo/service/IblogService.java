package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.entity.Blog;

import java.util.List;

public interface IblogService {

    List<Blog> getBlog();

    Boolean deleteBlog(long id);
}
