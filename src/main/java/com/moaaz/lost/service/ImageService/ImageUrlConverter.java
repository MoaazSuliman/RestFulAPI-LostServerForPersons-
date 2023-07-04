package com.moaaz.lost.service.ImageService;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImageUrlConverter {


    public static String convertToImageUrl(String filePath) {
        System.out.println("CONVERT IMAGE TO URL...");
        // Generate the URL based on the file path
        // For example, if the images are served from a web server, you can prepend the server URL
        // Or if the images are stored locally, you can generate a local file URL
//        String projectPath = System.getProperty("user.dir") + "\\";
        return "/home/ec2-user/images/" + filePath;
    }
}
