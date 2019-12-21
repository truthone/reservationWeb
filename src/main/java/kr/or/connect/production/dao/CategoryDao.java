package kr.or.connect.production.dao;


import static kr.or.connect.production.dao.CategoryDaoSqls.SELECT_COUNT_ALL;
import static kr.or.connect.production.dao.CategoryDaoSqls.SELECT_COUNT_BY_CATEGORY;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.production.dto.Category;

@Repository
public class CategoryDao {
	 private NamedParameterJdbcTemplate jdbc;
	    private SimpleJdbcInsert insertAction;
	    private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

	    public CategoryDao(DataSource dataSource) {
	        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	        this.insertAction = new SimpleJdbcInsert(dataSource)
	                .withTableName("category")
	                .usingGeneratedKeyColumns("id");
	    }
	    
	    public int CountAll() {
	        return jdbc.queryForObject(SELECT_COUNT_ALL, Collections.emptyMap(), Integer.class);
	    }
		
		public List<Category> countByCategory() {
			Map<String, Integer> params = new HashMap<>();
			return jdbc.query(SELECT_COUNT_BY_CATEGORY, params, rowMapper);
		}
}