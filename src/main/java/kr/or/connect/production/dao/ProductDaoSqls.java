package kr.or.connect.production.dao;

public class ProductDaoSqls {
    
	public static final String SELECT_PRODUCT_ALL = 
			"SELECT display_info_id displayInfoId, product.id productId, description productDescription,\n\r" +
			"place_name placeName , content productContent , file_info.file_name productImageUrl\r\n" +

			"FROM product, display_info , display_info_image , file_info, product_image\r\n"+
			
			"WHERE product.id = display_info.product_id\r\n"+
			    "AND product.id = product_image.product_id\r\n"+
				"AND display_info.id = display_info_image.display_info_id\r\n"+
				"AND product_image.file_id = file_info.id\r\n" + 
				"AND product_image.type = 'th'\r\n" +
			
			"LIMIT :start, :limit";
	
	public static final String SELECT_PRODUCT_BY_CATEGORY = 
			"SELECT category_Id AS category_id, display_info_id AS displayInfoId, product.id AS productId, description AS productDescription,\n\r" +
			"place_name AS placeName , content AS productContent , file_info.file_name AS productImageUrl\r\n" +

			"FROM product, display_info , display_info_image , file_info, product_image\r\n"+
			
			"WHERE product.id = display_info.product_id\r\n"+
			    "AND product.id = product_image.product_id\r\n"+
				"AND display_info.id = display_info_image.display_info_id\r\n"+
				"AND product_image.file_id = file_info.id\r\n" + 
				"AND product_image.type = 'th'\r\n" +
				"AND category_id= :categoryId\r\n" +
			"LIMIT :start, :limit";
	
	
	public static final String SELECT_FILENAME_ALL = 
			"SELECT save_file_name " +
		    "FROM product_image, file_info " +
			"WHERE product_image.file_id = file_info.id  And product_image.type = 'th' LIMIT :start, :limit";
	
	public static final String SELECT_FILENAME_BY_CATEGORY =
			"SELECT file_info.save_file_name " +
			"FROM (SELECT product_image.file_id  FROM product , product_image\r\n" + 
			"WHERE product.id = product_image.product_id AND category_id= :categoryId AND product_image.type = 'th') sbc, file_info \r\n" + 
			"WHERE sbc.file_id  = file_info.id \r\n" +
			"LIMIT :start, :limit";
			
}
