package kr.or.connect.production.dao;

public class ProductDaoSqls {
    
	public static final String SELECT_PRODUCT_ALL = 
			"SELECT display_info_id displayInfoId, product.id productId, description productDescription,\n\r" +
			"place_name placeName , content productContent , file_info.file_name productImageUrl\r\n" +

			"FROM product, display_info , display_info_image , file_info,\r\n"+
			
            "(SELECT product_image.file_id ,product_image.product_id\r\n" + 
			"FROM product , product_image\r\n"+
			"WHERE product.id = product_image.product_id AND product_image.type = 'th')bookable_product\r\n"+
			
			"WHERE product.id = display_info.product_id\r\n"+
			"AND display_info.id = display_info_image.display_info_id\r\n"+
			"AND bookable_product.file_id = file_info.id\r\n" + 
			"AND product.id = bookable_product.product_id\r\n" +
			
			"LIMIT :start, :limit";
	
	public static final String SELECT_PRODUCT_BY_CATEGORY = 
			"SELECT category_Id category_id, display_info_id displayInfoId, product.id productId, description productDescription,\n\r" +
			"place_name placeName , content productContent , file_info.file_name productImageUrl\r\n" +

			"FROM product, display_info , display_info_image , file_info,\r\n"+
			
            "(SELECT product_image.file_id ,product_image.product_id\r\n" + 
			"FROM product , product_image\r\n"+
			"WHERE product.id = product_image.product_id AND product_image.type = 'th' AND category_id= :categoryId)s\r\n"+
			
			"WHERE product.id = display_info.product_id\r\n"+
			"AND display_info.id = display_info_image.display_info_id\r\n"+
			"AND s.file_id = file_info.id\r\n" + 
			"AND product.id = s.product_id\r\n" +
			
			"LIMIT :start, :limit";
	
	
	public static final String SELECT_COUNT_ALL = "SELECT count(*) FROM product";
	
	public static final String SELECT_COUNT_BY_CATEGORY = 
			"SELECT  count(*)\r\n"+
	        "FROM product \r\n"+ 
		    "WHERE category_id = :categoryId\r\n" 
			;
	
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
