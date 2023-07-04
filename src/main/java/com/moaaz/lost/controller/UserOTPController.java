package com.moaaz.lost.controller;

import com.moaaz.lost.model.User;
import com.moaaz.lost.service.MailSenderService;
import com.moaaz.lost.service.UserOTPService;
import com.moaaz.lost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/moaazOTP")
public class UserOTPController {

    @Autowired
    private UserOTPService userOTPService;
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private UserService userService;

    @PostMapping("/sendOtp/{userId}")
    public ResponseEntity<?> sendOtpForUser(@PathVariable long userId) {
        User user = userService.getUserByIdOrElseThrowException(userId);
        mailSenderService.sendMessage("Your Otp To Verify Is " + user.getOtp() +
                        " And We Hope To Lost Your Friends To Know How Much We Are Useful For You"
                , user.getEmail());
        return new ResponseEntity<>("Done...", HttpStatus.ACCEPTED);
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<?> verifyOtp(@RequestParam long userId, @RequestParam String otp) {
        return userOTPService.verifyOTP(userId, otp);
    }
}
