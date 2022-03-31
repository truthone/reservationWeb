package kr.or.connect.production.dao;

public class CategoryDaoSqls {
  public static final String SELECT_CATEGORIES_NAME =
    "SELECT  category_id AS id, category.name AS name, count(category_id) AS count\r\n" +
    "FROM product, display_info , display_info_image , file_info, category \r\n" +
    "WHERE product.id = display_info.product_id\r\n" +
    "AND display_info.id = display_info_image.display_info_id\r\n" +
    "AND display_info_image.file_id = file_info.id\r\n" +
    "AND category.id = product.category_id\r\n" +
    "GROUP BY category_id";

  public static final String COUNT_PRODUCTS_ALL =
    "SELECT count(*) AS count\r\n" +
    "FROM product, display_info , display_info_image , file_info \r\n" +
    "WHERE product.id = display_info.product_id\r\n" +
    "AND display_info.id = display_info_image.display_info_id\r\n" +
    "AND display_info_image.file_id = file_info.id\r\n";

  public static final String COUNT_PRODUCTS_BY_CATEGORY =
    "SELECT count(category_id) AS count\r\n" +
    "FROM product, display_info , display_info_image , file_info \r\n" +
    "WHERE product.id = display_info.product_id\r\n" +
    "AND display_info.id = display_info_image.display_info_id\r\n" +
    "AND display_info_image.file_id = file_info.id\r\n" +
    "AND category_id = :categoryId";
}
