package kr.or.connect.production.dao;

import static kr.or.connect.production.dao.ProductDaoSqls.SELECT_PRODUCT_INFO_ALL;
import static kr.or.connect.production.dao.ProductDaoSqls.SELECT_PRODUCT_INFO_BY_CATEGORY;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import kr.or.connect.production.dto.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
  private NamedParameterJdbcTemplate jdbc;
  private SimpleJdbcInsert insertAction;
  private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(
    Product.class
  );

  public ProductDao(DataSource dataSource) {
    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    this.insertAction =
      new SimpleJdbcInsert(dataSource)
        .withTableName("product")
        .usingGeneratedKeyColumns("id");
  }

  public List<Product> selectProductAll(Integer start, Integer limit) {
    Map<String, Integer> params = new HashMap<>();
    params.put("start", start);
    params.put("limit", limit);

    return jdbc.query(SELECT_PRODUCT_INFO_ALL, params, rowMapper);
  }

  public List<Product> selectProductByCategory(
    Integer start,
    Integer limit,
    Integer categoryId
  ) {
    Map<String, Integer> params = new HashMap<>();
    params.put("start", start);
    params.put("categoryId", categoryId);
    params.put("limit", limit);

    return jdbc.query(SELECT_PRODUCT_INFO_BY_CATEGORY, params, rowMapper);
  }
}
