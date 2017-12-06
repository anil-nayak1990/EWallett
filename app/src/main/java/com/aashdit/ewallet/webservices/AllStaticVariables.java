package com.aashdit.ewallet.webservices;




public class AllStaticVariables {
	
	public static int splashTine=2000;
	public static String loginState="0";
	public static String name="name";
	public static String username="username";
	public static String sendername="sendername";
	public static String recievername="recievername";
	public static String venue="venue";
	public static String date="date";
	public static String time="time";
	public static String no_of_attendees="no_of_attendnees";
	public static String link = "LINK";
	public static String TAG = "Seeking_Daddie";
	public static String errorcode = "errorcode";
	public static String message = "message";
	public static String action = "action";
	public static String loginStatus = "loginStatus";
	public static String valueHAsh = "valueHAsh";
	public static String email = "email";
	public static String phone = "phone";
	public static String mobile = "mobile";
	public static String rating = "rating";
	public static String membershiplevel = "membershiplevel";
	public static String member_id = "member_id";
	public static String user_id = "user_id";
	public static String meeting_id = "meeting_id";
	public static String meeting_list="meeting_list";
	public static String notification_list="notification_list";
	public static String  memberList="memberList";
	public static String sender_id = "sender_id";
	public static String image="image";
	public static String receiver_id = "receiver_id";
	public static String[] activityList = { "MessageScreen", "BrowseScreen",
			"SearchScreen", "NearMeScreen", "MeetMeScreen", "EventsScreen",
			"EditProfileScreen", "FriendsScreen", "ForumScreen" };
	public static String notificationdetails_list="notificationdetails_list";
	public static String creditPlanList="creditPlanList";

	public static String paymentHistoryList="paymentHistoryList";
	public static String plan_datetime="plan_datetime";
	public static String contact_list="contact_list";
	public static String contact_sender_id="contact_sender_id";
	public static String meeting_data_show_user="meeting_data_show_user";
	

	

	
	public static class loginVariable {

		public static String loginStatus = "loginStatus";
		// passing parameters
		public static String login_usernm_edt = "username";
		public static String login_pass_edt = "password";

		// json value
		public static String id = "registration_id";

	}
	public static class CreditVariable {

		public static String plan_name = "plan_name";
		public static String plan_amount = "plan_amount";
		public static String plan_datetime = "plan_datetime";
		public static String method = "method";
		public static String membership_plans_id="membership_plans_id";
		
		

	}
	public static class CreateMeeting {

		public static String meeting_title = "meeting_title";
		
		public static String location_name = "location_name";
		public static String startdate = "startdate";
		public static String starttime = "starttime";
		public static String enddate = "enddate";
		public static String endtime = "endtime";
		public static String meeting_description = "meeting_description";
		public static String count_of_user="count_of_user";
		public static String meeting_image = "meeting_image";

		public static String meeting_id = "meeting_id";
		public static String member_id = "member_id";
		public static String id = "registration_id";

		public static String attendence_name="attendence_name";

		public static String latitude="latitude";

		public static String longnitude="longnitude";

		

	}
	// registerscreenVariables

	public static class registerVariable {
		// passing parameteres
	
		public static String user_login = "user_login";
		
		
		public static String user_pass = "password";
		public static String user_email = "email";
		public static String username = "username";
		public static String mobile = "mobile";
		public static String image = "image";
		public static String membershipstatus = "membershipstatus";
		public static String status = "status";
		public static String dateadded = "dateadded";
		public static String datemodified = "datemodified";
		
		///////////////////////////
		public static String maritalstatus = "maritalstatus";
		public static String country = "country";
		public static String state = "state";
		public static String city = "city";
		public static String interestin = "interestin";


		

	}

	public static class Setting_Variable {

		public static String time_interval_for_notification = "time_interval_for_notification";
		// passing parameters
		public static String tracking_start_before = "tracking_start_before";
		public static String set_grace_period = "set_grace_period";

		// json value
		public static String notification_send_to_attendance = "notification_send_to_attendance";

	}

	public static class paypalintegrationVariable {
		//
		// "user_id,
		// code,//
		// session_id,///
		// membership_id,
		// billing_country,//
		// subtotal,total,
		// payment_type,
		// expirationmonth
		// ,expirationyear,
		// status_order,
		// status_user,
		// gateway,
		// gateway_environment,
		// payment_transaction_id,
		// initial_payment,
		//
		// status,
		// startdate,enddate"

		public static String user_id = "user_id"; //
		public static String code = "code";
		public static String session_id = "session_id";
		public static String membership_id = "membership_id"; // /
		public static String billing_country = "billing_country";
		public static String subtotal = "subtotal";
		public static String total = "total"; // /
		public static String payment_type = "payment_type"; // /
		public static String expirationmonth = "expirationmonth";
		public static String expirationyear = "expirationyear";
		public static String active = "active";
		public static String gateway = "gateway";
		public static String gateway_environment = "gateway_environment";
		public static String payment_transaction_id = "payment_transaction_id"; // /
		public static String initial_payment = "initial_payment";
		public static String success = "success"; // /
		public static String startdate = "startdate"; //
		public static String enddate = "enddate"; //
		public static String billing_amount = "billing_amount"; //

	}

	public static class eventVariable {

		// json parameters

		public static String eventid = "eventid";
		public static String img = "img";
		public static String title = "title";
		public static String content = "content";
		public static String startdate = "startdate";
		public static String starttime = "starttime";
		public static String enddate = "enddate";
		public static String endtime = "endtime";

	}

	public static class searchVariable {

		// passing parameters
		public static String i_am = "i_am";
		public static String looking_for = "looking_for";
		public static String country = "country";
		public static String state = "state";
		public static String city = "city";
		public static String marital_status = "marital_status";
		public static String minage = "minage";
		public static String maxage = "maxage";
		public static String minheight = "minheight";
		public static String maxheight = "maxheight";

		public static String ethnicity = "ethnicity";
		public static String language = "language";
		public static String body_type = "body_type";
		public static String hair_color = "hair_color";
		public static String yearly_income = "yearly_income";
		public static String drink = "drink";
		public static String smoking = "smoking";
		public static String education = "education";
		public static String interests_in = "interests_in";
		public static String profile_pic = "profile_pic";
		public static String action = "action";

		// json elements

		public static String records = "records";
		public static String profileimg = "profileimg";
		public static String username = "username";
		public static String age = "age";
		public static String location = "location";
		public static String looking_for_j = "looking_for";
		public static String status = "status";

		public static String searcResulthArray = "searcResulthArray";

		public static String[] ethencity_array = { "White/Caucasian", "Black",
				"Hispanic/Latino", "Middle Eastern", "Native American",
				"Asian", "Mixed Race", "Islander", "Other Ethnicity"

		};

		public static String[] language_array = { "English", "Spanish",
				"French", "Bengali", "Cantonese", "Dutch/Africaans", "Farsi",
				"German", "Italian", "Javanese", "Korean", "Malay", "Punjabi",
				"Polish", "Portuguese", "Russian", "Swahili", "Tamil", "Thai",
				"Turkish", "Vietnamese" };

		public static String[] bodytype_array = { "Average", "Athletic",
				"Thin", "Muscular", "Overweight", " Few extra pounds", "Apple",
				"Pear", "Hourglass", "Curvy" };

		public static String[] haircolor_array = { "Auburn/Red", "Black",
				"Blonde", "Brown", "Various/Multi", "Gray/White", "Bald" };

		public static String[] income_array = { "less than 100K", "100K-500K",
				"500K-1 million", "over 1 million" };

		public static String[] drink_array = { "I do not drink",
				"I drink socially", "I drink regularly", "I have a problem" };

		public static String[] smoke_array = { "Non smoker", "Casual smoker",
				"Daily smoker" };

		public static String[] edu_array = { "High School", "Associate",
				"Some College", "Bachelor", "Graduate", "PhD/Doctorate",
				"University" };

		public static String[] child_array = { "Do not have children",
				"Have children at home", "Have children, but not at home",
				"Have children, sometimes at home" };

		public static String[] pet_array = { "Dog", "Cat", "Fish", "Reptile",
				"Horses", "Not sure what it is" };

		public static String[] eye_array = { "Black", "Blue", "Brown", "Hazel",
				"Gray", "Green", "Other" };

		public static String[] interest_array = { "Dancing", "Golf", "Movies",
				"Travel", "Dining", "Exercise", "Other" };

	}

	public static class browseVariable {

		// json element

		public static String profilename = "profilename";
		public static String profileimg = "profileimg";

	}

	public static class nearmeVariable {

		// current location
		public static String ucountry = "ucountry";
		public static String ustate = "ustate";
		public static String ucity = "ucity";

		// json parameters

		public static String profileimg = "profileimg";
		public static String username = "username";
		public static String country = "country";
		public static String state = "state";
		public static String city = "city";
		public static String location = "location";

	}

	public static class profileviewVariable {

		//

		// passing parameter

		public static String to_user_id = "to_user_id";

		public static String fieldid = "fieldid";
		public static String fieldname = "fieldname";
		public static String fieldvalue = "fieldvalue";
		public static String winkflag = "winkflag";

		public static String First_Name = "First Name";
		public static String My_Birthdate = "My Birthdate";
		public static String I_am = "I am";
		public static String Looking_for_a = "Looking for a";
		public static String Marital_status = "Marital status";
		public static String Country = "Country";
		public static String State = "State";

		public static String[] keys = { "First Name", "My Birthdate", "I am",
				"Looking for a", "Marital status", "Country", "State",
				"Interests In", "About me",
				"The one thing I am most passionate about:",
				"Things I am looking for in a person are:",
				"Favorite Vacations Spot", "Ideal First Date", "Looking for",
				"Smoking", "Drink", "Language", "Hair Color", "Eye Color",
				"Body Type", "Ethnicity", "Best Feature", "Occupation",
				"Education", "Yearly Income", "Estimated Net worth",
				"Headline", "Pets", "Male length", "Male width",
				"Male circumcised", "Female band", "Female cup", "Hobbies",
				"Height", "Weight (in lbs)", "Age"

		};

	}

	public static class eventdetailsVariable {
		// pass
		public static String event_id = "event_id";

		// json
		public static String img = "img";
		public static String title = "title";
		public static String content = "content";
		public static String startdate = "startdate";
		public static String starttime = "starttime";
		public static String enddate = "enddate";
		public static String endtime = "endtime";
		public static String venuename = "venuename";
		public static String address = "address";
		public static String phone = "phone";
		public static String website = "website";
		public static String totLike = "totLike";
		public static String attenedUser = "attenedUser";
		public static String organisherId = "organisherId";
		public static String organisherName = "organisherName";
		public static String organisherLink = "organisherLink";

		// atain mem

		public static String userid = "userid";
		public static String username = "username";
		public static String userimg = "userimg";

	}

	public static class meetmeVariable {
		// json obj

		public static String profilename = "profilename";
		public static String profileimg = "profileimg";
		public static String age = "age";
		public static String location = "location";

		// send wink paramenters
		public static String from_user_id = "from_user_id";
		public static String to_user_id = "to_user_id";
		public static String massage = "massage";

		// add friend
		public static String initiator_user_id = "initiator_user_id";
		public static String friend_user_id = "friend_user_id";

	}

	public static class menuVariable {
		public static String Upgrade = "Upgrade";
		public static String Logout = "Logout";
		public static String FAQ = "FAQ";
		public static String Settings = "Settings";

	}

	public static class friendVariable {

		public static String age = "age";
		public static String location = "location";
		public static String lookingfor = "lookingfor";
		public static String friendsname = "friendsname";
		public static String friendsEmail = "friendsEmail";
		public static String friendsprofileimage = "friendsprofileimage";
		public static String status = "status";

	}

	public static class editprofileVariable {
		public static String p_image = "p_image";

	}

	public static class receivewinksVariable {
		public static String from_user_id = "from_user_id";
		public static String senderName = "senderName";
		public static String date_time = "date_time";
		public static String image = "image";
		public static String wink_id = "wink_id";

	}

	public static class receivefriendVariable {
		public static String requestids = "requestids";
		public static String name = "name";
		public static String image = "image";

		// add friend
		public static String initiator_user_id = "initiator_user_id";
		public static String friend_user_id = "friend_user_id";

	}

	public static class myphotoVariable {
		public static String myphotopath = "myphotopath";

		public static String a_image = "a_image";
		public static String title = "title";
		public static String description = "description";

	}

	public static class eventuploadVariable {
		public static String eventtitle = "eventtitle";
		public static String eventcontent = "eventcontent";
		public static String eventimage = "eventimage";
		public static String eventcategory = "eventcategory";
		public static String event_upload_user_id = "event_upload_user_id";
		public static String startdate = "startdate";
		public static String starttime = "starttime";
		public static String enddate = "enddate";
		public static String endtime = "endtime";
		public static String venuename = "venuename";
		public static String address = "address";
		public static String phone = "phone";
		public static String website = "website";

	}

	public static class messageVariable {

		// json parameters inbox

		public static String sender_id = "sender_id";
		public static String sendername = "sendername";
		public static String senderimage = "senderimage";
		public static String senderstatus = "senderstatus";
		public static String subject = "subject";
		public static String msg = "msg";
		public static String date_sent = "date_sent";
		public static String atttached_file = "atttached_file";

		// hashmap parameter

		public static String id = "id";
		public static String name = "name";
		public static String image = "image";
		public static String status = "status";

		// json parameter outbox
		public static String thread_id = "thread_id";
		public static String threadName = "threadName";

	}

	public static class settingsVariable {
		// passing parameteres
		public static String user_pass = "user_pass";
	}

	public static class messagethreadVariable {
		// log_id
		// view_id
		//
		// viewOfAllSentReceiveEmailByUserId

		// passing parameteres
		public static String log_id = "log_id";
		public static String view_id = "view_id";

		
//json parameter
		public static String thread_id = "thread_id";
		public static String sender_id = "sender_id";
		public static String threadName = "threadName";
		public static String senderimage = "senderimage";
		public static String senderstatus = "senderstatus";
		public static String subject = "subject";
		public static String atttached_file = "atttached_file";
		public static String msg = "msg";
		public static String date_sent = "date_sent";
		public static String message = "message";

	}
	

}