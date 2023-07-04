package com.moaaz.lost.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SearcherPost {


    @NotEmpty(message = "Name Must Not Be Empty...")
    @NotNull(message = "Name Must Not Be Null...")
    private String name;

    @NotEmpty(message = "Phone Must Not Be Empty...")
    @NotNull(message = "Phone Must Not Be Null...")
    private String phone;

    private String details;

//    @NotEmpty(message = "Location Must Not Be Empty...")
//    @NotNull(message = "Location Must Not Be Null...")
    private String location;

//    @NotNull(message = "Age Must Not Be Null...")
//    @Size(max = 100, min = 1, message = "Age Must Be Greater Than 1  And Less Than 100.")
    private int age;
    @NotEmpty(message = "Picture Must Not Be Empty...")
    @NotNull(message = "Picture Must Not Be Null...")
    private String picture;

    private String clothesColor;


}
