package kr.or.connect.production.dao;

public class PromotionDaoSqls {
	public static final String SELELCT_PROMOTION = "SELECT promotion.id AS id, promotion.product_id AS productId, file_info.file_name AS productImageUrl\r\n" + 
			"FROM promotion, product_image , file_info\r\n" + 
			"WHERE promotion.product_id = product_image.product_id\r\n" + 
				"AND product_image.file_id = file_info.id\r\n" + 
				"AND product_image.type = \"th\";";

}
