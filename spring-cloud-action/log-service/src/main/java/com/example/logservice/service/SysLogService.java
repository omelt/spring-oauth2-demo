package com.example.logservice.service;

import org.springframework.stereotype.Service;

@Service
public class SysLogService {

    public void outLog(String msg){
        System.out.println("save log"+msg);
    }
}
