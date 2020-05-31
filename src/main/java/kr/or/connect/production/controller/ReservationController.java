package kr.or.connect.production.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.or.connect.production.dto.ReservationInfo;
import kr.or.connect.production.dto.ReservationInfoPrice;
import kr.or.connect.production.service.ReservationService;

@Controller

public class ReservationController {
	@Autowired
	ReservationService reservationService;
	
	// post api 예약하기
	@PostMapping(path="api/reservations")
	public String reservation(@ModelAttribute ReservationInfo reservationInfo,
				HttpServletRequest request) {
		
		System.out.println(reservationInfo.toString());
		Long reservationInfoId = reservationService.addReservation(reservationInfo);
		List<ReservationInfoPrice> a = reservationInfo.getReservationInfoPrices();
		System.out.println(reservationInfo.getReservationEmail());
		if (a != null) {
			for(int i =0; i < a.size(); i++) {
				ReservationInfoPrice reservationInfoPrice = new ReservationInfoPrice();
				reservationInfoPrice.setReservationInfoId(reservationInfoId);
				reservationInfoPrice.setProductPriceId(a.get(i).getProductPriceId());
				reservationInfoPrice.setCount(a.get(i).getCount());
				
				reservationService.addReservationInfoPrice(reservationInfoPrice);
			}
		}else System.out.println("null 입니다");
//			a.get(1).toString();
		
		return "redirect:../mainpage";
	}
}
