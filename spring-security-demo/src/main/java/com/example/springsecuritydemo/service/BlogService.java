package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.entity.Blog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService implements IblogService{



    private List<Blog> list=new ArrayList<>();

    public BlogService() {
     list.add(new Blog(1L,"test1","content1"));
     list.add(new Blog(2L,"test2","content2"));
    }

    @Override
    public List<Blog> getBlog() {
        return list;
    }

    @Override
    public Boolean deleteBlog(long id) {

        for (Blog blog:list) {
            if(blog.getId().equals(id)){
                list.remove(blog);
                return true;
            }
        }

        return false;

    }
}
