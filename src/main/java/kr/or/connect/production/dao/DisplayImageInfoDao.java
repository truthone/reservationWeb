package kr.or.connect.production.dao;

import static kr.or.connect.production.dao.DisplayImageInfoDaoSqls.SELECT_DISPLAYINFOIMAGE_INFO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.production.dto.DisplayImageInfo;

@Repository
public class DisplayImageInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<DisplayImageInfo> rowMapper = BeanPropertyRowMapper.newInstance(DisplayImageInfo.class);
	
	public DisplayImageInfoDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("display_info_image")
                .usingGeneratedKeyColumns("id");
    }

	public List<DisplayImageInfo> selectDisplayImageInfo(Long displayInfoId){
		Map<String, Long> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_DISPLAYINFOIMAGE_INFO, params,rowMapper);
	}
	
}