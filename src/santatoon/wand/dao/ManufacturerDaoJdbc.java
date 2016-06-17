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
import santatoon.wand.domain.Manufacturer;

@Repository
public class ManufacturerDaoJdbc implements ManufacturerDao {
	private SimpleJdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert userInsert;
	private RowMapper<Manufacturer> userMapper = new RowMapper<Manufacturer>() {
		public Manufacturer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Manufacturer manufacturer = new Manufacturer();
			manufacturer.setId(rs.getInt("id"));
			manufacturer.setName(rs.getString("name"));
			manufacturer.setCreated(rs.getDate("created"));
			manufacturer.setModified(rs.getDate("modified"));
			return manufacturer;
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
		this.userInsert = new SimpleJdbcInsert(dataSource).withTableName("manufacturers").usingGeneratedKeyColumns("id");
		;
	}

	public int add(final Manufacturer manufacturer) {

		int generatedId = this.userInsert.executeAndReturnKey(new MappedBeanPropertySqlParameterSource(manufacturer))
				.intValue();

		return generatedId;
	}

	public Manufacturer get(String name) {
		try {
			return this.jdbcTemplate.queryForObject(this.sqlService.getSql("manufacturerGet"), this.userMapper, name);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void update(Manufacturer manufacturer) {
		this.jdbcTemplate.update(this.sqlService.getSql("manufacturerUpdate"),
				new MappedBeanPropertySqlParameterSource(manufacturer));

	}

	public List<Manufacturer> getAll() {
		return this.jdbcTemplate.query(this.sqlService.getSql("manufacturerGetAll"), this.userMapper);
	}

	public void deleteAll() {
		this.jdbcTemplate.update(this.sqlService.getSql("manufacturerDeleteAll"));
	}

	public void delete(int id) {
		this.jdbcTemplate.update(this.sqlService.getSql("manufacturerDelete"), id);
	}

	public int getCount() {

		return this.jdbcTemplate.queryForInt(this.sqlService.getSql("manufacturerGetCount"));
	}
}
