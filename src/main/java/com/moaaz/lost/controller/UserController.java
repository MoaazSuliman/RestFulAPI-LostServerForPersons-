package com.moaaz.lost.controller;

import com.moaaz.lost.model.Post;
import com.moaaz.lost.model.User;
import com.moaaz.lost.service.UserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /*Login*/

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {

        return new ResponseEntity<>(userService.login(email, password), HttpStatus.ACCEPTED);
    }

    /*SignUp*/
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid User user) throws IOException {
//        userService.throwExceptionIfPhoneAlreadyExist(user.getPhone());

        return new ResponseEntity<>(userService.signUp(user),
                HttpStatus.OK);
    }


    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable long userId) {
        return new ResponseEntity<>(userService.updateUser(user, userId),
                HttpStatus.ACCEPTED);
    }

    @GetMapping("/getUserPostsById/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getUserPosts(@PathVariable long userId) {
        User user = userService.getUserByIdOrElseThrowException(userId);
        return user.getPosts();
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUserById(userId);
    }

    @PostMapping("/updatePassword/{userId}")
    public ResponseEntity<?> updateUserPassword(@PathVariable long userId,
                                                @RequestParam
                                                String password)throws MethodArgumentNotValidException {

        userService.updatePassword(userId, password);
        return new ResponseEntity<>("Password Updated Success...", HttpStatus.ACCEPTED);
    }
}
