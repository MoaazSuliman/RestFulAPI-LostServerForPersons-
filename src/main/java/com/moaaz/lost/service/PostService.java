package com.moaaz.lost.service;

import com.moaaz.lost.model.Post;
import com.moaaz.lost.repository.PostRepository;
import com.moaaz.lost.service.ImageService.ImageUrlConverter;
import com.moaaz.lost.service.ImageService.ImageUtils;
import com.moaaz.lost.service.PythonAPIService.PythonModelService;
import com.moaaz.lost.service.S3Service.ImageS3UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PythonModelService  pythonModelService;

    @Autowired
    private ImageS3UploadService imageS3UploadService;
    public Post addPost(Post post) throws IOException{
//        post.setPicture(convertPictureToUrl(post.getPicture()));
        // upload image to s3 and get url for image
        String imageUrl =imageS3UploadService.uploadImageToS3AndGetImageUrl(post.getPicture());
        post.setPicture(imageUrl);

        return postRepository.save(post);


    }

    //    public List<Post> getAllPosts() {
//
//        return postRepository.findAllByFinderOrderByLikesDesc(true);
//
//    }
    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByLikesDesc();
    }
    public List<Post>justGetAllPosts(){
        return  postRepository.findAll();
    }
    public Post getPostByIdOrElseTHrowException(long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new NoSuchElementException("There Are NO Post With Id = " + postId)
        );
    }

    public Post addLikeForPost(Post post) {
        post.setLikes(post.getLikes() + 1);
        return postRepository.save(post);
    }

    public Post deleteLikeForPost(Post post) {
        post.setLikes(post.getLikes() - 1);
        return postRepository.save(post);
    }

    public void deleteAll(){
        postRepository.deleteAll();
    }

    public List<Post> search(String text) {
        String[] strings = text.split(" ");
        List<Post> posts = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            posts.addAll(postRepository.findByNameContainingOrDetailsContaining(strings[i], strings[i]));
        }
        return removeRepeatedPosts(posts);
    }

    public void deletePost(long postId) {
        postRepository.deleteById(postId);
    }

    private List<Post> removeRepeatedPosts(List<Post> posts) {
        System.out.println(posts.size());
        for (int i = 0; i < posts.size() - 1; i++) {
            for (int j = i + 1; j < posts.size(); j++) {
                if (posts.get(i).getId() == (posts.get(j).getId())) {
                    posts.remove(j);
                    i = 0;
                }
            }
        }
        return posts;
    }

    private void checkIfPostIsExistOrElseThrowException(long postId) {
        getPostByIdOrElseTHrowException(postId);
    }

    private String convertPictureToUrl(String base64) throws IOException {

        byte[] bytes = new ImageUtils().decodeBase64(base64);
        String filePath = new ImageUtils().saveImage(bytes);
        return new ImageUrlConverter().convertToImageUrl(filePath);


    }
}
