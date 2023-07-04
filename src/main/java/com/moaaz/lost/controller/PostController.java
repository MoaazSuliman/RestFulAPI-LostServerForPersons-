package com.moaaz.lost.controller;

import com.moaaz.lost.model.Post;
import com.moaaz.lost.model.User;
import com.moaaz.lost.model.request.FinderPost;
import com.moaaz.lost.model.request.SearcherPost;
import com.moaaz.lost.service.ImageService.ImageUrlConverter;
import com.moaaz.lost.service.ImageService.ImageUtils;
import com.moaaz.lost.service.PostService;
import com.moaaz.lost.service.PythonAPIService.PythonModelService;
import com.moaaz.lost.service.S3Service.ImageS3UploadService;
import com.moaaz.lost.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private ImageS3UploadService imageS3UploadService;
    @Autowired
    private PythonModelService pythonModelService;

    @PostMapping("/addFinderPost/user/{userId}")
    public ResponseEntity<?> addPost(@RequestBody @Valid FinderPost finderPost, @PathVariable int userId) throws IOException {
        User user = userService.getUserByIdOrElseThrowException(userId);
        Post post = new Post(finderPost);
        post.setPoster(user);
        return new ResponseEntity<>(postService.addPost(post), HttpStatus.ACCEPTED);
    }

    @PostMapping("/addSearcherPost/user/{userId}")
    public ResponseEntity<?> addPost(@RequestBody @Valid SearcherPost searcherPost, @PathVariable int userId) throws IOException {
        User user = userService.getUserByIdOrElseThrowException(userId);
        Post post = new Post(searcherPost);
        post.setPoster(user);
        return new ResponseEntity<>(postService.addPost(post), HttpStatus.ACCEPTED);
    }

    @PostMapping("/searchByPicture")
    public ResponseEntity<?> getPostByPicture(@RequestParam String pictureBase64) throws IOException {

        String imageUrl= imageS3UploadService.uploadImageToS3AndGetImageUrl(pictureBase64);

        long postId = pythonModelService.getPostIdFromPythonModel(imageUrl);
        System.out.println("Return Value From Model Python is + " + postId + "        **************************************");
        if (postId == -1) {
            // make python model do training again.
            pythonModelService.sendTrainingDataToModel();
            return new ResponseEntity<>("Not Found:_)", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postService.getPostByIdOrElseTHrowException(postId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllPosts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }


    @GetMapping("/searchByText")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> searchForPost(@RequestParam String text) {
        return postService.search(text);
    }


    @DeleteMapping("/delete/{postId}")
    public void deletePost(@PathVariable long postId) {
        postService.deletePost(postId);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?>deleteAllPosts(){
        postService.deleteAll();
        return new ResponseEntity<>("Deleted Succes.." , HttpStatus.OK);
    }


    @GetMapping("/sendTrainingData")
    public ResponseEntity <?> sendTrainingDataToModel(){
        pythonModelService.sendTrainingDataToModel();
        return  new ResponseEntity<>("Sended Success..." , HttpStatus.OK);
    }
}
