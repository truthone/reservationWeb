package kr.or.connect.production.dao;

import static kr.or.connect.production.dao.DisplayInfoDaoSqls.SELECT_DISPLAYINFO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import kr.or.connect.production.dto.DisplayInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class DisplayInfoDao {
  private NamedParameterJdbcTemplate jdbc;
  private SimpleJdbcInsert insertAction;
  private RowMapper<DisplayInfo> rowMapper = BeanPropertyRowMapper.newInstance(
    DisplayInfo.class
  );

  public DisplayInfoDao(DataSource dataSource) {
    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    this.insertAction =
      new SimpleJdbcInsert(dataSource)
        .withTableName("display_info")
        .usingGeneratedKeyColumns("id");
  }

  public List<DisplayInfo> selectDisplayInfo(Long displayInfoId) {
    Map<String, Long> params = new HashMap<>();
    params.put("displayInfoId", displayInfoId);
    return jdbc.query(SELECT_DISPLAYINFO, params, rowMapper);
  }
}
