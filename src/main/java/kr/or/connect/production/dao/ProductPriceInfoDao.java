package kr.or.connect.production.dao;

import static kr.or.connect.production.dao.ProductPriceInfoDaoSqls.SELECT_PRODUCT_PRICE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import kr.or.connect.production.dto.ProductPriceInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ProductPriceInfoDao {
  private NamedParameterJdbcTemplate jdbc;
  private SimpleJdbcInsert insertAction;
  private RowMapper<ProductPriceInfo> rowMapper = BeanPropertyRowMapper.newInstance(
    ProductPriceInfo.class
  );

  public ProductPriceInfoDao(DataSource dataSource) {
    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    this.insertAction =
      new SimpleJdbcInsert(dataSource)
        .withTableName("product_price")
        .usingGeneratedKeyColumns("id");
  }

  public List<ProductPriceInfo> selectProductPriceInfo(Long displayInfoId) {
    Map<String, Long> params = new HashMap<>();
    params.put("displayInfoId", displayInfoId);
    return jdbc.query(SELECT_PRODUCT_PRICE, params, rowMapper);
  }
}
