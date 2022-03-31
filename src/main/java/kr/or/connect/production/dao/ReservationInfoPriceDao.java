package kr.or.connect.production.dao;

import static kr.or.connect.production.dao.ReservationInfoPriceDaoSqls.SELECT_RESERVATION_INFO_PRICE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import kr.or.connect.production.dto.MyReservationPriceInfoAndCount;
import kr.or.connect.production.dto.ReservationInfoPrice;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationInfoPriceDao {
  private NamedParameterJdbcTemplate jdbc;
  private SimpleJdbcInsert insertAction;
  private RowMapper<MyReservationPriceInfoAndCount> rowMapper = BeanPropertyRowMapper.newInstance(
    MyReservationPriceInfoAndCount.class
  );

  public ReservationInfoPriceDao(DataSource dataSource) {
    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    this.insertAction =
      new SimpleJdbcInsert(dataSource)
        .withTableName("reservation_info_price")
        .usingGeneratedKeyColumns("id");
  }

  public Long insertReservationInfoPrice(
    ReservationInfoPrice reservationInfoPrice
  ) {
    SqlParameterSource params = new BeanPropertySqlParameterSource(
      reservationInfoPrice
    );
    return insertAction.executeAndReturnKey(params).longValue();
  }

  public List<MyReservationPriceInfoAndCount> selectMyReservationPrice(
    Long reservationId
  ) {
    Map<String, Long> params = new HashMap<>();
    params.put("reservationId", reservationId);

    return jdbc.query(SELECT_RESERVATION_INFO_PRICE, params, rowMapper);
  }
}
