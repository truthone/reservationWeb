package kr.or.connect.production.dao;

import static kr.or.connect.production.dao.ProductImageInfoDaoSqls.SELECT_PRODUCTIMAGE_INFO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.production.dto.ProductImageInfo;

@Repository
public class ProductImageInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ProductImageInfo> rowMapper = BeanPropertyRowMapper.newInstance(ProductImageInfo.class);
	
	public ProductImageInfoDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("display_info_image")
                .usingGeneratedKeyColumns("id");
    }
	public List<ProductImageInfo> selectProductImageInfo(Integer displayInfoId){
	Map<String, Integer> params = new HashMap<>();
	params.put("displayInfoId", displayInfoId);

	return jdbc.query(SELECT_PRODUCTIMAGE_INFO, params,rowMapper);
	}
}
