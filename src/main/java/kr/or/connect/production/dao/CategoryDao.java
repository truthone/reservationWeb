package kr.or.connect.production.dao;

import static kr.or.connect.production.dao.CategoryDaoSqls.COUNT_PRODUCTS_ALL;
import static kr.or.connect.production.dao.CategoryDaoSqls.COUNT_PRODUCTS_BY_CATEGORY;
import static kr.or.connect.production.dao.CategoryDaoSqls.SELECT_CATEGORIES_NAME;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import kr.or.connect.production.dto.Category;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao {
  private NamedParameterJdbcTemplate jdbc;
  private SimpleJdbcInsert insertAction;
  private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(
    Category.class
  );

  public CategoryDao(DataSource dataSource) {
    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    this.insertAction =
      new SimpleJdbcInsert(dataSource)
        .withTableName("category")
        .usingGeneratedKeyColumns("id");
  }

  public List<Category> categories() {
    Map<String, Integer> params = new HashMap<>();
    return jdbc.query(SELECT_CATEGORIES_NAME, params, rowMapper);
  }

  public int countProductsAll() {
    return jdbc.queryForObject(
      COUNT_PRODUCTS_ALL,
      Collections.emptyMap(),
      Integer.class
    );
  }

  public List<Category> countProductsByCategory(Integer categoryId) {
    Map<String, Integer> params = new HashMap<>();
    params.put("categoryId", categoryId);
    return jdbc.query(COUNT_PRODUCTS_BY_CATEGORY, params, rowMapper);
  }
}
