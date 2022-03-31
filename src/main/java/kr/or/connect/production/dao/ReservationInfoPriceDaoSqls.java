package kr.or.connect.production.dao;

public class ReservationInfoPriceDaoSqls {
  public static final String SELECT_RESERVATION_INFO_PRICE =
    "SELECT reservation_info_id , count, product_id, price_type_name, price,discount_rate\r\n" +
    "FROM reservation_info_price , product_price\r\n" +
    "WHERE reservation_info_id = :reservationId And reservation_info_price.product_price_id = product_price.id ;";
}
