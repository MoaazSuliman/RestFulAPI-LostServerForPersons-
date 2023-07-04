package com.moaaz.lost.service.PythonAPIService;


import com.moaaz.lost.model.PythonModel.RequestPostId;
import com.moaaz.lost.model.PythonModel.SendImageUrl;
import com.moaaz.lost.model.PythonModel.TrainingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PythonModelService {

    @Value("${python.model.predict.url}")
    private String predictUrl;
    @Value("${python.model.train.url}")
    private String trainUrl;

    @Autowired
    private RestTemplate predictTemplate;

    @Autowired
    private RestTemplate trainTemplate;

    @Autowired
    private PostsConverterService postsConverterService;

    public String sendTrainingDataToModel() {
        System.out.println("Before Calling Training Method Model ==> ");
        TrainingData trainingData = new TrainingData();
        trainingData.setTrainingInstances(postsConverterService.getAllTrainingInstances());
        trainingData.getTraining_data().forEach(e -> System.out.println(e.getImage_path() + "   " + e.getLabel()));

        System.out.println("Training Data That Will Be Sended ");
        System.out.println(trainingData.toString());

        System.out.println("JSON String==> ");
        String jsonString=postsConverterService.getJsonString(trainingData);
        System.out.println(jsonString);

        String response  = trainTemplate.postForObject(trainUrl, jsonString, String.class);
        System.out.println("Response From Training Method Is ===> "+response);
        System.out.println("After Calling Training Method Model ==> ");

        return "DONE";

    }

    public long getPostIdFromPythonModel(String pictureUrl) {
        System.out.println("Before Calling Python API Model Predict....");
        SendImageUrl imageUrl = new SendImageUrl(pictureUrl);
        RequestPostId requestPostId = predictTemplate.postForObject(predictUrl, imageUrl, RequestPostId.class);
        System.out.println("Request PostID =====> "+requestPostId);
        System.out.println("After Calling Python API Model...");


        return convertPostIdToLong(requestPostId.getPost_id());
    }

    private long convertPostIdToLong(String string ){
        long postId;
        try{
             postId= Long.parseLong(string);
        }catch (Exception exception){
            postId=-1;
        }
        System.out.println("Id After Converted ====> "+postId);
        return postId;
    }
}
