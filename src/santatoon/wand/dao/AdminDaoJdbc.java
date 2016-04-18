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
import santatoon.wand.domain.Admin;

@Repository
public class AdminDaoJdbc implements AdminDao{

	private SimpleJdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert userInsert;
	private RowMapper<Admin> userMapper = new RowMapper<Admin>() {
		public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
			Admin admin = new Admin();
			admin.setId(rs.getString("id"));
			admin.setPassword(rs.getString("password"));
			admin.setName(rs.getString("name"));
			admin.setPhone(rs.getString("phone"));
			admin.setEmail(rs.getString("email"));
			return admin;
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
		this.userInsert = new SimpleJdbcInsert(dataSource)
				.withTableName("admin");
	}

	public void add(final Admin admin) {
		this.userInsert.execute(new MappedBeanPropertySqlParameterSource(
				admin));
	}

	public Admin get(String id) {
		try {
			return this.jdbcTemplate.queryForObject(
					this.sqlService.getSql("adminGet"), this.userMapper, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void update(Admin admin) {
		this.jdbcTemplate.update(this.sqlService.getSql("adminUpdate"),
				new MappedBeanPropertySqlParameterSource(admin));

	}

	public List<Admin> getAll() {
		return this.jdbcTemplate.query(
				this.sqlService.getSql("adminGetAll"), this.userMapper);
	}

	public void deleteAll() {
		this.jdbcTemplate.update(this.sqlService.getSql("adminDeleteAll"));
	}

	public void delete(String id) {
		this.jdbcTemplate.update(this.sqlService.getSql("adminDelete"), id);
	}

	public int getCount() {

		return this.jdbcTemplate.queryForInt(this.sqlService
				.getSql("adminGetCount"));
	}

}
