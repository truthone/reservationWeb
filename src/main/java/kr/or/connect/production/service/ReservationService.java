package kr.or.connect.production.service;

import java.util.List;

import kr.or.connect.production.dto.MyReservationPriceInfoAndCount;
import kr.or.connect.production.dto.ReservationInfo;
import kr.or.connect.production.dto.ReservationInfoPrice;

public interface ReservationService {
	boolean hasReservationInfoByEmail(String reservationEmail);
	Long addReservation(ReservationInfo reservationInfo);
	ReservationInfoPrice addReservationInfoPrice(ReservationInfoPrice reservationInfoPrice);
	List<ReservationInfo> getMyReservationInfo(String reservationEmail); 
	List<MyReservationPriceInfoAndCount>getMyReservationPriceInfo(Long reservationId);
	Integer cancelReservation(Integer reservationId);
	List<ReservationInfo> getReservationInfo(Long reservationId);
}
