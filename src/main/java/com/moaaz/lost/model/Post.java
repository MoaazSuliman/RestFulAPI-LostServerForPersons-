package com.moaaz.lost.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moaaz.lost.model.request.FinderPost;
import com.moaaz.lost.model.request.SearcherPost;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity(name = "user_posts")
@Table(name = "user_posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String phone;
    private String location;
    private String details;
    private int age;
    private boolean finder;

    private String clothesColor;
    @Column(columnDefinition = "longtext")// Image Url.
    private String picture;// Image Url.
    private long likes;

    private String date;
    private String time;
    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH}

    )

    private User poster;


    public Post(FinderPost finderPost) {
        this.setName(finderPost.getName());
        this.setDetails(finderPost.getDetails());
        this.setLocation(finderPost.getLocation());
        this.setPhone(finderPost.getPhone());
        this.setAge(finderPost.getAge());
        this.setPicture(finderPost.getPicture());
        this.setClothesColor(finderPost.getClothesColor());
        this.setFinder(true);
        this.setDate(String.valueOf(LocalDate.now()));
        this.setTime(String.valueOf(LocalTime.now()));

    }

    public Post (SearcherPost searcherPost){
        this.setName(searcherPost.getName());
        this.setDetails(searcherPost.getDetails());
        this.setLocation(searcherPost.getLocation());
        this.setPhone(searcherPost.getPhone());
        this.setAge(searcherPost.getAge());
        this.setPicture(searcherPost.getPicture());
        this.setClothesColor(searcherPost.getClothesColor());
        this.setFinder(false);
        this.setDate(String.valueOf(LocalDate.now()));
        this.setTime(String.valueOf(LocalTime.now()));
    }



}
