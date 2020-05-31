package kr.or.connect.production.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.or.connect.production.dto.ReservationInfo;
import kr.or.connect.production.service.ReservationService;

@Controller

public class ReservationController {
	@Autowired
	ReservationService reservationService;
	
	// post api 예약하기
	@PostMapping(path="api/reservations")
	public String reservation(@ModelAttribute ReservationInfo reservationInfo,
				HttpServletRequest request) {
			    
		Map<String,Object> map = new HashMap<>();
		
		ReservationInfo resultInfo = reservationService.addReservation(reservationInfo);
		
		map.put("resultInfo",resultInfo);
		System.out.println(resultInfo);
		return "redirect:../mainpage";
	}
}
