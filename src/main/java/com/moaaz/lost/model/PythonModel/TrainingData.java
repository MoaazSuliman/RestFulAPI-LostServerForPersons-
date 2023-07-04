package com.moaaz.lost.model.PythonModel;



import lombok.ToString;

import java.util.List;

@ToString
public class TrainingData {

    private List<TrainingInstances> training_data;

    public List<TrainingInstances> getTraining_data() {
        return training_data;
    }

    public void setTrainingInstances(List<TrainingInstances> trainingInstances) {
        this.training_data = trainingInstances;
    }


}
