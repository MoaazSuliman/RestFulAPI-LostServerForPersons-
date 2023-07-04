package com.moaaz.lost.service.PythonAPIService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moaaz.lost.model.Post;
import com.moaaz.lost.model.PythonModel.TrainingData;
import com.moaaz.lost.model.PythonModel.TrainingInstances;
import com.moaaz.lost.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostsConverterService {
    @Autowired
    private PostService postService;

    public List<TrainingInstances> getAllTrainingInstances() {
        List<Post>posts = postService.getAllPosts();
        List<TrainingInstances> trainingInstances= new ArrayList<>();
        for(Post post : posts){
            trainingInstances.add(new TrainingInstances(post.getPicture() , String.valueOf(post.getId())));
        }
        return trainingInstances;

    }


    public String getJsonString(TrainingData trainingData) {
        // Convert the object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(trainingData);
            System.out.println(json);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
