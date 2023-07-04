package com.moaaz.lost.service;

import com.moaaz.lost.model.User;
import com.moaaz.lost.repository.UserRepository;
import com.moaaz.lost.service.ImageService.ImageUrlConverter;
import com.moaaz.lost.service.ImageService.ImageUtils;
import com.moaaz.lost.service.S3Service.ImageS3UploadService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private ImageS3UploadService imageS3UploadService;

    public User signUp(User user) throws DataIntegrityViolationException, IOException {
        setOTP(user);
//        setImageUrl(user);
        user.setPicture(setImageUrlByS3(user.getPicture()));
        return userRepository.save(user);
    }

//    @Transactional
    public User updateUser( User user, long userId) {

        User existingUser = getUserByIdOrElseThrowException(userId);

        if (user.getPhone() != null)
            existingUser.setPhone(user.getPhone());

        if (user.getPicture() != null)
            existingUser.setPicture(setImageUrlByS3(user.getPicture()));

        if (user.getName() != null)
            existingUser.setName(user.getName());

        if (user.getEmail() != null)
            existingUser.setEmail(user.getEmail());

//        if(user.getPassword()!=null)
//            existingUser.setPassword(user.getPassword());

        System.out.println(existingUser.toString());
        return userRepository.save(existingUser);
    }

    public void updatePassword(long userId, String password) {
        User user = getUserByIdOrElseThrowException(userId);
        user.setPassword(password);
        userRepository.save(user);
    }

    public User getUserByIdOrElseThrowException(long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new NoSuchElementException("There Are No User With Id= " + userId));
    }

    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    @SneakyThrows
    public User login(String email, String password) {


        User user = userRepository.findByEmailAndPassword(email, password).orElseThrow(
                () ->
                        new NoSuchElementException("Error In Email Or Password"));
        if (!user.getCheckOtpVerify())
            throw new Exception("User Is Exist But OTP Not Verify Yet.");
        return user;
    }

    public void makeUserVerifyTrue(long userId) {
        User user = getUserByIdOrElseThrowException(userId);
        user.setCheckOtpVerify(true);
        updateUser(user, user.getId());
    }
    //    public User getUserByPhone(String phone) {
//        return userRepository.findByPhone(phone).orElseThrow(
//                () -> new NoSuchElementException("There Are Not User With Phone '" + phone + "'")
//        );
//    }

    @SneakyThrows
    public void throwExceptionIfPhoneAlreadyExist(String email) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null) {
            System.out.println("IF IS TRUE...........................................................");
            throw new DataIntegrityViolationException("This Email Already Exist In Our Database...");
        }
    }


    private void setOTP(User user) {
        // Create a new Random object
        Random rand = new Random();
        // Generate a random number between 1000 and 9999
        int randomNum = rand.nextInt(9000) + 1000;
        System.out.println(randomNum + " This Is The True");
        user.setOtp(String.valueOf(randomNum));
    }

    private void setImageUrl(User user) throws IOException {
        byte[] bytes = new ImageUtils().decodeBase64(user.getPicture());
        System.out.println("First Is Done");
        String filePath = new ImageUtils().saveImage(bytes);
        System.out.println("Second Is Done");
        String imageUrl = new ImageUrlConverter().convertToImageUrl(filePath);
        System.out.println("Third Is Done");
        user.setPicture(imageUrl);

    }

    private String setImageUrlByS3(String base64) {
        // if ==> if it's already converted to aws s3 url
        if(base64!=null &&base64.contains("https://lostserver-images.s3.amazonaws.com"))
            return base64;
        return (base64 == null || base64.isBlank() || base64.isEmpty()) ? "" : imageS3UploadService.uploadImageToS3AndGetImageUrl(base64);
    }

}
