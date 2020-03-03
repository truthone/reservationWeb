package kr.or.connect.production.dao;

public class DisplayInfoDaoSqls {
	public static final String SELECT_DISPLAYINFO = 
			"SELECT category.id AS categoryId ,\r\n"+
			"category.name AS categoryName,\r\n"+
			"display_info.create_date AS createDate ,\r\n"+
			"display_info.modify_date AS modifyDate,\r\n"+
			"display_info.opening_hours AS openingHours,\r\n"+
			"display_info.place_lot AS placeLot,\r\n"+
			"display_info.place_name AS placeName,\r\n"+
			"display_info.place_street AS placeStreet,\r\n"+
			"product.content AS productContent,\r\n"+
			"product.description AS productDescription,\r\n"+
			"display_info.product_id AS productId,\r\n"+
			"display_info.tel AS tel\r\n"+

			"FROM category, display_info,product\r\n"+

			"WHERE display_info.id = :displayInfoId\r\n"+
			"AND display_info.product_id = product.id\r\n"+
		    "AND category.id = product.category_id"
				;
}
