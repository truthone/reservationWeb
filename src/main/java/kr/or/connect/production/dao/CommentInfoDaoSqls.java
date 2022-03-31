package kr.or.connect.production.dao;

public class CommentInfoDaoSqls {
  public static final String SELECT_COMMENT_INFO =
    "SELECT commentInfo.id , commentInfo.description, commentInfo.comment , commentInfo.score , commentInfo.reservationEmail ,\r\n" +
    "commentInfo.reservationDate, commentImage.save_file_name \r\n" +
    "FROM\r\n" +
    "(SELECT  reservation_user_comment.id , product.description, comment, score , reservation_email AS reservationEmail ,\r\n" +
    "DATE_FORMAT(reservation_date, '%Y.%m.%d') AS reservationDate \r\n" +
    "FROM reservation_info, reservation_user_comment, product \r\n" +
    "WHERE reservation_user_comment.reservation_info_id = reservation_info.id\r\n" +
    "AND reservation_info.product_id = product.id\r\n" +
    "AND reservation_info.display_info_id = :displayInfoId) AS commentInfo\r\n" +
    "LEFT JOIN \r\n" +
    "(SELECT reservation_user_comment.id , file_info.save_file_name \r\n" +
    "FROM reservation_user_comment , reservation_user_comment_image , file_info \r\n" +
    "WHERE reservation_user_comment_image.reservation_user_comment_id = reservation_user_comment.id \r\n" +
    "AND reservation_user_comment_image.file_id =  file_info.id  ) AS commentImage \r\n" +
    "ON commentInfo.id = commentImage.id";

  public static final String COUNT_COMMENT_INFO =
    "SELECT count(*) \r\n" +
    "FROM reservation_info, reservation_user_comment\r\n" +
    "WHERE reservation_user_comment.reservation_info_id = reservation_info.id \r\n" +
    "AND reservation_info.display_info_id = :displayInfoId";
		
  public static final String TOTAL_SCORE_OF_COMMENT =
    "SELECT sum(score) \r\n" +
    "FROM reservation_info, reservation_user_comment\r\n" +
    "WHERE reservation_user_comment.reservation_info_id = reservation_info.id \r\n" +
    "AND reservation_info.display_info_id = :displayInfoId";
}
