
I am a Junior Java Developer with 1 year of experience, and I have recently worked on developing a system for LostPersons. The system includes the following functionalities:

1. User Management:
   - Users can sign up with their name, email, phone, and password. The system sends an OTP to their email for verification.
   - Users verify their OTP to proceed with the signup process. If verified, they are welcomed and can proceed to login. Otherwise, the user is deleted as a security measure.

2. User Authentication:
   - Users can log in using their email and password. The system checks if the email and password match the records in the database and verifies that the user has completed the OTP verification process.

3. Finder and Searcher Posts:
   - Users can create finder posts to report that they have found a missing person. They provide details such as phone number, picture, additional information, clothes color, and age for child posts.
   - Users can create searcher posts to seek help in finding a missing person. They provide a picture of the missing person, additional details, and the person's name.
   - Posts can be liked by other users, and the system returns the posts ordered by the number of likes received.

4. Image Processing and Storage:
   - The system converts uploaded pictures to Base64 format and sends them to an AWS S3 bucket to convert them to URLs. These URLs are then saved in the database for efficient retrieval.

5. User Interaction with Posts:
   - Users can like posts or remove their like from a post.

6. Search Functionality:
   - Users can search for posts by entering text related to the missing person's name or details. The system performs a thorough search and returns any posts matching the search criteria.

7. Image Recognition:
   - Users can search for posts by uploading a picture of a missing person. The system uses RestTemplate to connect to a Python AI model. The system periodically sends all post pictures to the AI model for processing.
   - When a user searches by picture, the system sends the picture to the AI model, retrieves the corresponding post ID, and provides all the details about the post and the user who posted it.

Other Features:
   - The system allows CRUD operations for users and posts.
   - The project is hosted on AWS, utilizing EC2 for the API, S3 for picture conversion, and RDS for the database.

Technology Stack: Spring Boot, Hibernate JPA, MySQL, and following clean code principles such as Single Responsibility and Interface Segregation.

This project was a solo endeavor, and I successfully completed it within one week. Additionally, the project involves collaboration with Python developers who built the AI model, and there are plans to develop a mobile application using Flutter.
