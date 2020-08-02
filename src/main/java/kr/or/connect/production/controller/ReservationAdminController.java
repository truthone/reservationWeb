package kr.or.connect.production.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.connect.production.service.ReservationService;

@Controller
public class ReservationAdminController {
	@Autowired
	ReservationService reservationService; 
	
	@GetMapping(path="/bookinglogin")
	public String loginform() {
		return "bookinglogin";
	}

	@PostMapping(path="/login")
	public String login(@RequestParam(name="reservationEmail",required=true)String reservationEmail,
			HttpSession session, RedirectAttributes redirectAttr) {
		if(reservationService.hasReservationInfoByEmail(reservationEmail)) {
			session.setAttribute("reservationEmail", reservationEmail);
			return "redirect:/myreservation";
		}else {
			redirectAttr.addFlashAttribute("errorMessage","예매자정보가 없습니다.");
			return "redirect:/bookinglogin";
		}
	}
	
	@GetMapping(path="/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("reservationEmail");
		return "redirect:/mainpage";
	}
}
