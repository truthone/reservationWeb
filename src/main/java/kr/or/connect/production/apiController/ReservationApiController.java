package kr.or.connect.production.apiController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.production.dto.DisplayInfo;
import kr.or.connect.production.dto.MyReservationPriceInfoAndCount;
import kr.or.connect.production.dto.ReservationInfo;
import kr.or.connect.production.service.ProductDetailService;
import kr.or.connect.production.service.ReservationService;

@RestController
@RequestMapping(path="/api/reservations")
public class ReservationApiController {

	@Autowired
	ReservationService reservationService;
	@Autowired
	ProductDetailService productDetailService;
	
	
	@GetMapping
	public Map<String, Object> MyReservations(
			@RequestParam(name="reservationEmail", required = true) String reservationEmail){
		
		Map<String, Object> map = new HashMap<>();
		List<ReservationInfo> myReservaitionList = reservationService.getMyReservationInfo(reservationEmail);
				
		ArrayList<Object> list = new ArrayList<Object>();
		
		for(int i = 0 ; i < myReservaitionList.size(); i++) {
			Map<String,Object> reservationMap = new HashMap<>();
			List<MyReservationPriceInfoAndCount> InfoPrice = reservationService.getMyReservationPriceInfo(myReservaitionList.get(i).getReservationInfoId());
			Long productId = myReservaitionList.get(i).getProductId();
			List<DisplayInfo> productInfo = productDetailService.getDisplayInfo(productId);
			
			reservationMap.put("reservationInfo", myReservaitionList.get(i));
			reservationMap.put("reservationInfoPrice",InfoPrice);
			reservationMap.put("productInfo", productInfo.get(0));
			list.add(reservationMap);
			
		}
			map.put("reservation", list);
		return map;		
	}
	
	@PutMapping("/{reservationId}")
	public Map<String,Object> cancelReservation(@PathVariable(name="reservationId", required = true) Integer reservationId){
		Map<String,Object> map = new HashMap<>();
		Integer updateCount = reservationService.cancelReservation(reservationId);
		
		return Collections.singletonMap("success", updateCount > 0 ?"true" : "false");
	}
}
