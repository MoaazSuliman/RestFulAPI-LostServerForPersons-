package com.moaaz.lost.service.ImageService;

import com.moaaz.lost.service.ProjectPathService;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

public class ImageUtils {


    public static byte[] decodeBase64(String base64Image) {
        System.out.println("DECODE BASE64");
        return Base64.getDecoder().decode(base64Image);
    }


    public static String saveImage(byte[] imageBytes) throws IOException {
        System.out.println("SAVE IMAGE TO FILE");
        System.out.println("Image Bytes ==>  "+imageBytes);
        String fileName = UUID.randomUUID().toString() + ".png";


//        String home = "http://16.16.141.48:8081/moaaz/images/";
//        String localHome="H:\\Spring Projects\\lost\\src\\moaaz\\images\\";
//    String projectPath=System.getProperty("user.dir")+"\\";
        String filePath ="/home/ec2-user/images/"+ fileName;

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(imageBytes);
        }
        return filePath;
    }



}
