// package com.kodnest.music.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.kodnest.music.entity.Song;
//import com.kodnest.music.entity.User;
//import com.kodnest.music.model.LoginData;
//import com.kodnest.music.service.SongService;
//import com.kodnest.music.service.UserService;
//
//import jakarta.servlet.http.HttpSession;
//@CrossOrigin("*")
//@Controller
//public class UserController {
//	@Autowired	
//	UserService	us;
//	@Autowired	
//	SongService	ss;
//	@PostMapping(value="/registration")	
//	public String postUser(@ModelAttribute User user) {
//		User existDetails=us.emailExist(user);
//
//		if(existDetails==null) {
//			us.postUser(user);
//			System.err.println("user added successfull");
//		}
//		else {
//			System.out.println("duplicate records");
//		}
//		return "login";
//
//	}
//	@PostMapping(value="/validate")
//public String addUser(@RequestBody LoginData logindata) {
//		System.out.println(logindata+">>>");
//	return "customerhome";
//	//@PostMapping(value="/validate")
////	public String addUser(@RequestBody LoginData logindata, HttpSession session,Model model) {
////		String email=logindata.getEmail();
////		String password=logindata.getPassword();
////		System.out.println(logindata.getEmail()+"  "+ logindata.getPassword()+"  login");
////
////if(us.validUser(email, password)==true) {
////			
////			session.setAttribute("email", email);
////			
////			String role=us.getRole(logindata.getEmail());
////			User userstatus=us.isPremium(email);
////			if(role.equals("Admin")) {
////				return "adminhome";
////			}
////			else if(userstatus.isPremium()) {
////			
////				List<Song>songslist = ss.fetchAllSongs();
////				model.addAttribute("songs",songslist);
////		        model.addAttribute("isPremium", userstatus);
////				return "viewsongs";
////			}
////			else {
////				return "customerhome";
////			}
////		}
////		else {
////			return "login";
////		}
////		
//	}
//	@GetMapping("/logout")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "login";
//	}
//	@PostMapping("/updatepassword")
//	public String updatePassword(@RequestParam ("email") String email,@RequestParam("password")
//	                String password,@RequestParam ("password") String confirmPassword ) {
//		if(password.equals(confirmPassword)) {
//			us.updatePassword(email,password);
//		}
//		return "login";
//	}
//		
//}

 package com.kodnest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.entity.Song;
import com.kodnest.entity.User;
import com.kodnest.service.SongService;
import com.kodnest.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	SongService songService;

	@PostMapping("/register")
	public String addUser( @ModelAttribute User user) {
		//		System.out.println("User Data");
		//		System.out.println(user);

		//		String email = user.getEmail();//we are fetching email from the form which we are created

		//		Checking user is present or not
		boolean userExists = userService.emailExists(user);

		if(userExists==false) {
			userService.saveUser(user);

			System.out.println("User added successfully");
		}

		else {
			
			System.out.println("Duplicate User");
			
		}
		return "login";
		


	}

	@PostMapping("/validate")

	public String validate( @RequestParam("email") String email, 
			@RequestParam("password") String password,HttpSession session ,Model model) {

		if(userService.validUser(email,password)==true) {
			
			session.setAttribute("email", email);
			
			String role= userService.getRole(email);
			if(role.equals("admin")) {
				
			
			return "adminhome";
		}
		else {
			
			User user = userService.getUser(email);
			boolean userstatus = user.isPremium();
			
			List<Song> fetchAllSongs = songService.fetchAllSongs();
			model.addAttribute("songs", fetchAllSongs);
			
			model.addAttribute("ispremium", userstatus);
			
			return "customerhome";
		}
	}
		else {
			
		return "login";
	}

	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
		
	}
	
	
	@PostMapping("/googleregister")
	public String addGoogleUser( @ModelAttribute User user) {
		//		System.out.println("User Data");
		//		System.out.println(user);

		//		String email = user.getEmail();//we are fetching email from the form which we are created

		//		Checking user is present or not
		boolean userExists = userService.emailExists(user);

		if(userExists==false) {
			userService.saveUser(user);

			System.out.println("User added successfully");
		}

		else {
			
			System.out.println("Duplicate User");
			
		}
		return "login";
		


	}
	
	
	 @PostMapping("/resetpassword")
	    public String resetPassword(@RequestParam("email") String email,
	                                @RequestParam("password") String password,
	                                Model model) {
	        // Check if email is empty
	        if (email.isEmpty()) {
	            model.addAttribute("error", "Email is empty");
	            return "forgotpassword"; // Show an error message on the forgot password page
	        }

	        // Check if the password is empty
	        if (password.isEmpty()) {
	            model.addAttribute("error", "Password cannot be empty");
	            return "forgotpassword"; // Show an error message on the forgot password page
	        }

	        // Check if the user with the provided email exists
	        User user = userService.getUser(email);
	        if (user == null) {
	            model.addAttribute("error", " Email does not exist! Enter valid Email");
	            return "forgotpassword"; // Show an error message on the forgot password page
	        }

	        // Reset the user's password
	        userService.resetPassword(email, password);
	        return "redirect:/login?reset=success"; // Redirect to the login page with success message
	    }
	
	
	
	
	
	 
}