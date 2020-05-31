package kr.or.connect.production.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.production.dao.ReservationInfoDao;
import kr.or.connect.production.dao.ReservationInfoPriceDao;
import kr.or.connect.production.dto.ReservationInfo;
import kr.or.connect.production.dto.ReservationInfoPrice;
import kr.or.connect.production.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationInfoDao reservationInfoDao;
	@Autowired
	private ReservationInfoPriceDao reservationInfoPriceDao;
	
	@Override
	@Transactional(readOnly = false)
	public ReservationInfo addReservation(ReservationInfo reservationInfo) {
		int cancleFlag = 0;
		reservationInfo.setCreateDate(new Date());
		reservationInfo.setModifyDate(new Date());
		reservationInfo.setReservationDate(new Date());
		reservationInfo.setCancelFlag(cancleFlag);
		Long reservationInfoId = reservationInfoDao.insertReservationInfo(reservationInfo);
		reservationInfo.setReservationInfoId(reservationInfoId);
		return reservationInfo;
	}
	
	@Override
	@Transactional(readOnly = false)
	public ReservationInfoPrice addReservationInfoPrice(ReservationInfoPrice reservationInfoPrice ) {
		Long reservationInfoPriceId = reservationInfoPriceDao.insertReservationInfoPrice(reservationInfoPrice);
		reservationInfoPrice.setReservationInfoId(reservationInfoPriceId);
		return reservationInfoPrice;
	}

	@Override
	public List<ReservationInfo> getReservationInfoes(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteReservationInfo() {
		// TODO Auto-generated method stub
		return 0;
	}

}
