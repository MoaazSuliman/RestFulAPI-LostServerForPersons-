System For LostPersons.

User: 

1- Signup : by Name , Email , Phone , Password. ==> System send OTP For Email.
2- Verify OTP : user verify Enter His OTP That Sent To His Email When He Signedup. 
	if veify ==> true ==> welcome and go to login.
	else ==> delete user now.==> he is laying.
3- Login : Be Email And Password.==> System Check That Email And Password Are In Our Database And This User Verifyed OTP.

4- User Can Add Finder Post ==> user can say i found this boy or girl ==> Enter (phone , Picture* , Details* , ClothesColor* , AgeForChild).

5- User Can Add Searcher Post ==> user can say i can't find my little girl if any one see it call me. ==> Enter (pictureForHisLittleGirl , Some Details , herName).

#Posts have likes from users.#
#Returned Posts Ordered By Likes.#
#System Take Pictures Base64 Then Send It To S3 To Convert It To Url Before Saving It In Database#

6- User Can Put Like Or Delete His Like For Any Post. 

7- User Can Search By Text ==> Enter His Little Boy Or Girl Name ==> System Have A Good Search ==> Return Any Post Has Name Or Details Like his Little Boy Or Little Girl.

8- User Can Search By Picture :-) ==> Enter His Child Picture ==> And His Child Post If Any One Found Him.
	==> My System Connect By RestTemplate To Another Python Model AI.
	1- I Send All Posts Pictures For Model AI Every 3 Hours.
	2- When User Search By Picture I Send It To Model AI And Get To Me Post Id==> Then I Get All Details About Post And The User He Posts It.
		And Return It.
 

Another Functions :
	 get , update , delete users
	 get , update , delete posts.
	

#I Built This RestAPI Alone In 1 Week.#

Cloud : AWS (Ec2 ==> For My API) , (S3  ==> To Convert Pictures) , (RDS ==> For Database )

Technology : Spring Boot  , Hibernate Jpa , MySQL   + (Single responsaplity   , Interface segregation   , Clean Code).

Another Develoepr : Bulit Python Model AI And Created End Points.
Another Developers : Workink To Make It Mobile Application For Flutter.
