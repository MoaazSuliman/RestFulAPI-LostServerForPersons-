package com.moaaz.lost.service;

import com.moaaz.lost.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserOTPService {
    @Autowired
    private UserService userService;

    public ResponseEntity<?> verifyOTP(long userId, String otp) {
        User user = userService.getUserByIdOrElseThrowException(userId);
        if (user.getOtp().equals(otp)) {
            userService.makeUserVerifyTrue(userId);
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        }
        userService.deleteUserById(userId);
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
}
