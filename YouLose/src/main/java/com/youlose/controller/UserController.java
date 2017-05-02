package com.youlose.controller;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.youlose.dao.UserDAO;
import com.youlose.dao.VideoDAO;
import com.youlose.model.User;
import com.youlose.model.Video;

@Controller
public class UserController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showMainPage() {
		return "main";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogInForm() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String logIn(HttpServletRequest req, HttpSession s) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		if (UserDAO.getInstance().loginValid(email, password)) {
			s.setAttribute("user", UserDAO.getInstance().getAllUsers().get(email));
			return "redirect:/index";
		}
		return "invalidLogin";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegisterForm() {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(HttpServletRequest req, HttpSession s) {
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String confPassword = req.getParameter("password2");
		System.out.println(username);
		System.out.println(email);
		System.out.println(password);
		System.out.println(confPassword);

		String msg = UserDAO.getInstance().validateRegistration(email, username, password, confPassword);
		if (msg.equals("Registration successful")) {
			User newUser = new User();
			newUser.setEmail(email);
			newUser.setName(username);
			newUser.setPassword(password);
			newUser.setProfilePicture(User.DEFAULT_PROFILE_PIC);
			UserDAO.getInstance().save(newUser);
			return "main";
		}

		return "register";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logOut(HttpSession s) {
		s.invalidate();
		return "main";
	}

	@RequestMapping(value = "/subscribers", method = RequestMethod.GET)
	public String getSubscribers(HttpSession s) {
		
		if ( s.getAttribute("logged")!=null) {
			String user = (String) s.getAttribute("name");
			String link = "/" + user + "/subscribers";
			return "redirect:" + link;
		}
		return "login";
	}

	@RequestMapping(value = "/subscriptions", method = RequestMethod.GET)
	public String getSubscribtions(HttpSession s) {
		if (s.getAttribute("logged")!=null) {
			String user = (String) s.getAttribute("name");
			String link = "/" + user + "/subscriptions";
			return "redirect:" + link;
		}
		return "login";
	}

	@RequestMapping(value = "/playlist/{playlistName}", method = RequestMethod.GET)
	public String getLikedVideos(HttpSession s, @PathVariable(value = "playlistName") String playlistName) {
		if (s.getAttribute("logged")!=null) {
			String user = (String) s.getAttribute("name");
			String link = "/" + user + "/" + playlistName;
			// TODO restrict user from registering with name="playlist"
			return "redirect:" + link;
		}
		return "login";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String viewProfile(HttpSession s) {
		if (s.getAttribute("user") == null) {
			return "main";
		}

		return "profile";
	}

	@RequestMapping(value = "/subscribe", method = RequestMethod.POST)
	public void subscribeUser(Model model, HttpSession session) {
		User currentUser = (User) session.getAttribute("user");
		User subscribUser = null;
		subscribUser = UserDAO.getInstance().getUser((User) session.getAttribute("usernameSubscribe"));
		if (UserDAO.getInstance().subscribeUser(currentUser.getUserID(), subscribUser.getUserID())) {
			System.out.println("subscribe is done");
		}

	}

	@RequestMapping(value = "/unSubscribe", method = RequestMethod.POST)
	public void unSubscribeUser(Model model, HttpSession session) {
		User currentUser = (User) session.getAttribute("user");
		User unSubscribUser = null;
		unSubscribUser = UserDAO.getInstance().getUser((User) session.getAttribute("usernameSubscribe"));
		if (UserDAO.getInstance().unSubscribeUser(currentUser.getUserID(), unSubscribUser.getUserID())) {
			System.out.println("unsubsribe is done is done");
		}

	}

	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(HttpSession s,
			@RequestParam("serchWord") String searchWord,
			@RequestParam("searched") String searched){
		if(searched.equals("Videos")){
			HashMap<String, Video> results = new HashMap<>();
			s.setAttribute("searched", searchWord);
			try{
				results = VideoDAO.getInstance().searchAllByString(searchWord);
				if(results.isEmpty()){
					s.setAttribute("results", "none");
				}
				else{
					s.setAttribute("results", results.keySet());
				}
			}
			catch(SQLException e){
				System.out.println("Greshka pri tyrsene");
			}
		}
		else if(searched.equals("Users)")){
			if(UserDAO.getInstance().getAllUsers().containsKey(searchWord)){
				s.setAttribute("results", UserDAO.getInstance().getAllUsers().get(searchWord));
			}
			else{
				s.setAttribute("results", "none");
			}
		}
		
		else if(searched.equals("Playlists")){
			
		}
		
		
		return "search";
	}
	
}
