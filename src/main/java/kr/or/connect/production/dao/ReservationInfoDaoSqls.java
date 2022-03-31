package kr.or.connect.production.dao;

public class ReservationInfoDaoSqls {
  public static final String SELECT_RESERVATION_INFO_BY_EMAIL =
    "SELECT id AS reservationInfoId, product_id, display_info_id , reservation_name , reservation_tel , reservation_date, cancel_flag, create_date, modify_date FROM reservation_info WHERE reservation_email = :reservationEmail";

  public static final String SELECT_RESERVATION_INFO_BY_ID =
    "SELECT id AS reservationInfoId, product_id, display_info_id , reservation_name , reservation_tel , reservation_date, cancel_flag, create_date, modify_date FROM reservation_info WHERE reservation_info.id = :reservationId";

  public static final String UPDATE_RESERVATION_CANCLEFLAG =
    "UPDATE reservation_info SET cancel_flag = 1, modify_date = now() WHERE reservation_info.id = :reservationId";
}
