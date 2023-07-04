package com.moaaz.lost.controller;

import com.moaaz.lost.model.PythonModel.RequestPostId;

import com.moaaz.lost.model.PythonModel.SendImageUrl;
import com.moaaz.lost.service.PythonAPIService.PythonModelService;
import jakarta.servlet.ServletContext;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private PythonModelService pythonModelService;



    @PostMapping("/predict")
    public RequestPostId testPredict(@RequestBody SendImageUrl imageUrl) {
        System.out.println("From Test Controller : ");
        System.out.println(imageUrl);
        return new RequestPostId(String.valueOf(1));
    }


}
