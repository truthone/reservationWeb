package kr.or.connect.production.controller;

import java.util.List;
import kr.or.connect.production.dto.ReservationInfo;
import kr.or.connect.production.dto.ReservationInfoPrice;
import kr.or.connect.production.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservationController {
  @Autowired
  ReservationService reservationService;

  @PostMapping(path = "/api/reservations")
  public String reservation(@ModelAttribute ReservationInfo reservationInfo) {
    Long reservationInfoId = reservationService.addReservation(reservationInfo);
    List<ReservationInfoPrice> reservationInfoPriceList = reservationInfo.getReservationInfoPrices();

    if (reservationInfoPriceList != null) {
      for (int i = 0; i < reservationInfoPriceList.size(); i++) {
        ReservationInfoPrice reservationInfoPrice = new ReservationInfoPrice();
        reservationInfoPrice.setReservationInfoId(reservationInfoId);
        reservationInfoPrice.setProductPriceId(
          reservationInfoPriceList.get(i).getProductPriceId()
        );
        reservationInfoPrice.setCount(
          reservationInfoPriceList.get(i).getCount()
        );

        reservationService.addReservationInfoPrice(reservationInfoPrice);
      }
    } else System.out.println("null 입니다");

    return "redirect:../mainpage";
  }
}
