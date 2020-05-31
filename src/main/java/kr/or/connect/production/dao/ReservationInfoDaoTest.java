package kr.or.connect.production.dao;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.production.config.ApplicationConfig;
import kr.or.connect.production.dto.ReservationInfo;
import kr.or.connect.production.dto.ReservationInfoPrice;

public class ReservationInfoDaoTest {

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		ReservationInfoDao reservationInfoDao = ac.getBean(ReservationInfoDao.class);
		
		ReservationInfo reservationInfo  = new ReservationInfo();
		reservationInfo.setReservationTel("010-6393-9404");
		reservationInfo.setReservationName("한진실");
		reservationInfo.setReservationEmail("truth.h.406@gamil.com");
		reservationInfo.setReservationDate(new Date());
		reservationInfo.setModifyDate(new Date());
		reservationInfo.setCreateDate(new Date());
		reservationInfo.setDisplayInfoId((long) 1);
		reservationInfo.setProductId((long)7);
		//reservationInfo.setCancelFlag(0);
		
		Long id = reservationInfoDao.insertReservationInfo(reservationInfo);
		
		reservationInfo.toString();
		
		ReservationInfoPriceDao reservationInfoPriceDao = ac.getBean(ReservationInfoPriceDao.class);
		ReservationInfoPrice RIP = new ReservationInfoPrice();
		RIP.setProductPriceId((long) 1);
		RIP.setReservationInfoId(id);
		RIP.setCount(1);
		reservationInfoPriceDao.insertReservationInfoPrice(RIP);
	}
}
