package kr.or.connect.production.apiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.production.service.ProductDetailService;
import kr.or.connect.production.service.ReservationService;

@RestController
@RequestMapping(path="/api/reservations/comments")
public class CommentApiController {

	@Autowired
	ReservationService reservationService; 
	@Autowired
	ProductDetailService productDetailService;
	
}
