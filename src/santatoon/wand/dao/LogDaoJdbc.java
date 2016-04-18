package santatoon.wand.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import santatoon.sqlservice.SqlService;
import santatoon.support.MappedBeanPropertySqlParameterSource;
import santatoon.wand.domain.Log;
import santatoon.wand.domain.Status;

@Repository
public class LogDaoJdbc implements LogDao {
	private SimpleJdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert userInsert;
	private RowMapper<Log> userMapper = new RowMapper<Log>() {
		public Log mapRow(ResultSet rs, int rowNum) throws SQLException {
			Log log = new Log();
			log.setId(rs.getInt("id"));
			log.setCustomerid(rs.getInt("customerid"));
			log.setDeviceid(rs.getInt("deviceid"));
			log.setStatus(Status.valueOf(rs.getBoolean("status")));
			log.setRef(rs.getString("ref"));
			log.setOperated(rs.getDate("operated"));
			return log;
		}
	};

	private Map<String, String> sqlMap;

	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}

	private SqlService sqlService;

	@Autowired
	public void setSqlService(SqlService sqlService) {
		this.sqlService = sqlService;
	}

	@Autowired
	public void init(DataSource dataSource) {
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
		this.userInsert = new SimpleJdbcInsert(dataSource).withTableName("logs").usingGeneratedKeyColumns("id");
	}

	public int add(final Log log) {

		int generatedId = this.userInsert.executeAndReturnKey(new LogBeanPropertySqlParameterSource(log)).intValue();

		return generatedId;
	}

	public Log get(int id) {
		try {
			return this.jdbcTemplate.queryForObject(this.sqlService.getSql("logGet"), this.userMapper, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void update(Log log) {
		this.jdbcTemplate.update(this.sqlService.getSql("logUpdate"), new MappedBeanPropertySqlParameterSource(log));

	}

	public List<Log> getAll() {
		return this.jdbcTemplate.query(this.sqlService.getSql("logGetAll"), this.userMapper);
	}

	public void deleteAll() {
		this.jdbcTemplate.update(this.sqlService.getSql("logDeleteAll"));
	}

	public void delete(int id) {
		this.jdbcTemplate.update(this.sqlService.getSql("logDelete"), id);
	}

	public int getCount() {

		return this.jdbcTemplate.queryForInt(this.sqlService.getSql("logGetCount"));
	}

	private static class LogBeanPropertySqlParameterSource extends MappedBeanPropertySqlParameterSource {
		public LogBeanPropertySqlParameterSource(Object object) {
			super(object);
			map("status", "status.value");
		}
	}

}
