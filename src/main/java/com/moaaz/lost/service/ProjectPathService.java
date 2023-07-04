package com.moaaz.lost.service;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectPathService {

    @Autowired
    private  ServletContext servletContext;
    public  String getProjectPath(){
        String contextPath = servletContext.getContextPath();
        String realPath = servletContext.getRealPath("/");
        return realPath.replace(contextPath, "");
    }
}
