package kr.or.connect.production.service;

import java.util.List;

import kr.or.connect.production.dto.ReservationInfo;
import kr.or.connect.production.dto.ReservationInfoPrice;

public interface ReservationService {
	public List<ReservationInfo> getReservationInfoes(String email);
	public ReservationInfo addReservation(ReservationInfo reservationInfo);
	public ReservationInfoPrice addReservationInfoPrice(ReservationInfoPrice reservationInfoPrice );
	public int deleteReservationInfo(); 
}
