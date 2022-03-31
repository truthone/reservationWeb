package kr.or.connect.production.dao;

import static kr.or.connect.production.dao.PromotionDaoSqls.SELELCT_PROMOTION;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import kr.or.connect.production.dto.Promotion;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PromotionDao {
  private NamedParameterJdbcTemplate jdbc;
  private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(
    Promotion.class
  );

  public PromotionDao(DataSource dataSource) {
    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
  }

  public List<Promotion> selectAll() {
    Map<String, Integer> params = new HashMap<>();
    return jdbc.query(SELELCT_PROMOTION, params, rowMapper);
  }
}
