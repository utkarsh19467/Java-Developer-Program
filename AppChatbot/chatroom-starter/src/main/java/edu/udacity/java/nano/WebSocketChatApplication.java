package edu.udacity.java.nano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

/**
 * The Class WebSocketChatApplication.
 */
/**
 * @author utkarsh
 *
 */
@SpringBootApplication
@RestController
public class WebSocketChatApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(WebSocketChatApplication.class, args);
	}

	/**
	 * Login Page.
	 *
	 * @return the model and view
	 */
	@GetMapping("/")
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	/**
	 * Index.
	 *
	 * @param userName the user name
	 * @param request the request
	 * @return the model and view
	 * @throws UnknownHostException the unknown host exception
	 */
	@GetMapping("/index")
	public ModelAndView index(String userName, HttpServletRequest request) throws UnknownHostException {
		ModelAndView chatModelAndView = new ModelAndView("chat");
		chatModelAndView.addObject("userName", userName);
		return chatModelAndView;
	}
}
