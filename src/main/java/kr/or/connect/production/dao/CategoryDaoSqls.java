package kr.or.connect.production.dao;

public class CategoryDaoSqls {
	public static final String SELECT_CATEGORIES = 
			"SELECT id, name FROM category";
	
	public static final String SELECT_COUNT_ALL = 
			"SELECT count(*) count\r\n"+
			"FROM category,"+
			   "(SELECT product.category_id, display_info_id , product.id productId, description productDescription,\r\n"+ 
			   "place_name placeName , content productContent\r\n"+
			   "FROM product, display_info , display_info_image , file_info\r\n"+
			   "WHERE product.id = display_info.product_id\r\n"+
			   "ANd display_info.id = display_info_image.display_info_id\r\n"+ 
			   "AND display_info_image.file_id = file_info.id)bookable_product\r\n"+
			"WHERE category.id = bookable_product.category_id"
			;

public static final String SELECT_COUNT_BY_CATEGORY = 			
        "SELECT  category_id, category.name, count(*) count\r\n"+
		"FROM category,"+
        		
		   "(SELECT product.category_id, display_info_id , product.id productId, description productDescription,\r\n"+ 
		   "place_name placeName , content productContent\r\n"+
		   "FROM product, display_info , display_info_image , file_info\r\n"+
		   "WHERE product.id = display_info.product_id\r\n"+
		   "AND display_info.id = display_info_image.display_info_id\r\n"+ 
		   "AND display_info_image.file_id = file_info.id)bookable_product\r\n"+
		   
		"WHERE category.id = bookable_product.category_id\r\n"+
		"GROUP BY category_id"
		;
}
