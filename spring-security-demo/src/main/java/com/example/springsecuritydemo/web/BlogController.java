package com.example.springsecuritydemo.web;

import com.example.springsecuritydemo.entity.Blog;
import com.example.springsecuritydemo.service.IblogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    IblogService blogService;

    @GetMapping
    public ModelAndView list(ModelAndView modelAndView){

        List<Blog> list = blogService.getBlog();

        modelAndView.setViewName("blog/lists");

        modelAndView.addObject("list",list);

        return modelAndView;
    }


    @GetMapping("/{id}/deletion")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id, Model model){

        blogService.deleteBlog(id);

        model.addAttribute("list",blogService.getBlog());

        return "blog/lists";

    }

}
