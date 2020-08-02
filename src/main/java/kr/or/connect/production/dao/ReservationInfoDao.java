package kr.or.connect.production.dao;

import static kr.or.connect.production.dao.ReservationInfoDaoSqls.SELECT_RESERVATION_INFO_BY_EMAIL;
import static kr.or.connect.production.dao.ReservationInfoDaoSqls.SELECT_RESERVATION_INFO_BY_ID;
import static kr.or.connect.production.dao.ReservationInfoDaoSqls.UPDATE_RESERVATION_CANCLEFLAG;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.production.dto.ReservationInfo;
import kr.or.connect.production.dto.ReservationInfoPrice;

@Repository
public class ReservationInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ReservationInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfo.class);
	
	public ReservationInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_info")
			.usingGeneratedKeyColumns("id");
	}
	
	public Long insertReservationInfo(ReservationInfo reservationInfo) {
			SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfo);
			return insertAction.executeAndReturnKey(params).longValue();
	}
	
	public Long insertReservationInfoPrice(ReservationInfoPrice reservationInfoPrice) {
			SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfoPrice);
			return insertAction.executeAndReturnKey(params).longValue();
	}
	
	public int updateReservationCancleFlag(Integer reservationId) {
		Map<String, ?> params = Collections.singletonMap("reservationId", reservationId);
			return jdbc.update(UPDATE_RESERVATION_CANCLEFLAG,params);
	}
	
	public List<ReservationInfo> selectMyReservationByEmail(String reservationEmail){
		Map<String, String> params = new HashMap<>();
		params.put("reservationEmail", reservationEmail);
		return jdbc.query(SELECT_RESERVATION_INFO_BY_EMAIL, params, rowMapper);
	}
	
	public List<ReservationInfo> selectReservationById(Long reservationId){
		Map<String, Long> params = new HashMap<>();
		params.put("reservationId", reservationId);
		
		return jdbc.query(SELECT_RESERVATION_INFO_BY_ID, params, rowMapper);
	}
}