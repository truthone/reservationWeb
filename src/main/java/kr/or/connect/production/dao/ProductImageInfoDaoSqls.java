package kr.or.connect.production.dao;

public class ProductImageInfoDaoSqls {
	public static final String SELECT_PRODUCTIMAGE_INFO =
			"SELECT \r\n" + 
			"file_info.content_type AS contentType,\r\n" + 
			"file_info.create_date AS createDate,\r\n" + 
			"file_info.delete_flag AS deleteFlag, \r\n" + 
			"file_info.id AS fileInfoId, \r\n" + 
			"file_info.file_name AS fileName,\r\n" + 
			"file_info.modify_date AS modifyDate,\r\n" + 
			"product_image.product_id AS productId,\r\n" + 
			"product_image.id AS productImageId,\r\n" + 
			"file_info.save_file_name AS saveFileName,\r\n" + 
			"product_image.type\r\n" + 
			
			"FROM file_info, product_image ,display_info\r\n" + 
			
			"WHERE display_info.id = :displayInfoId\r\n" + 
			"AND display_info.product_id = product_image.product_id\r\n" + 
			"AND product_image.file_id = file_info.id\r\n"
			;
	
	public static final String SELECT_PRODUCTIMAGE_ONLY_MAINIMAGE =
			"SELECT \r\n" + 
			"file_info.file_name AS fileName,\r\n" + 
			"product_image.product_id AS productId,\r\n" + 
			"product_image.id AS productImageId,\r\n" + 
			"file_info.save_file_name AS saveFileName,\r\n" + 
			"product_image.type\r\n" + 
			
			"FROM file_info, product_image ,display_info\r\n" + 
			
			"WHERE display_info.id = :displayInfoId\r\n" + 
			"AND display_info.product_id = product_image.product_id\r\n" + 
			"AND product_image.file_id = file_info.id\r\n" +
			"AND type = 'ma' \r\n"
			;

}
