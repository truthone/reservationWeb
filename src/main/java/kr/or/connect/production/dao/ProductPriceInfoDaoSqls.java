package kr.or.connect.production.dao;

public class ProductPriceInfoDaoSqls {
	public static final String SELECT_PRODUCT_PRICE =
	"SELECT product_price.product_id AS productId, \r\n" +
	"product_price.id AS id, \r\n" +
	"product_price.price_type_name AS priceTypeName, \r\n" +
	"product_price.price , product_price.discount_rate AS discountRate \r\n" +

	"FROM product_price, product , display_info \r\n" +

	"WHERE display_info.id = :displayInfoId \r\n" + 
	"AND display_info.product_id = product.id \r\n" +
	"AND product.id = product_price.product_id \r\n"
	;
}
