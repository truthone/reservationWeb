package kr.or.connect.production.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.production.service.ReservationService;

@RestController
@RequestMapping(path="api/reservations")
public class ReservationApiController {

	@Autowired
	ReservationService reservationService;
	
	

	
	// get api 예약 조회 
	
}
