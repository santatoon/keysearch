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
import santatoon.wand.domain.Category;

@Repository
public class CategoryDaoJdbc implements CategoryDao {
	private SimpleJdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert userInsert;
	private RowMapper<Category> userMapper = new RowMapper<Category>() {
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			Category category = new Category();
			category.setId(rs.getInt("id"));
			category.setName(rs.getString("name"));
			category.setCreated(rs.getDate("created"));
			category.setModified(rs.getDate("modified"));
			return category;
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
		this.userInsert = new SimpleJdbcInsert(dataSource).withTableName("categories").usingGeneratedKeyColumns("id");
		;
	}

	public int add(final Category category) {

		int generatedId = this.userInsert.executeAndReturnKey(new MappedBeanPropertySqlParameterSource(category))
				.intValue();

		return generatedId;
	}

	public Category get(String name) {
		try {
			return this.jdbcTemplate.queryForObject(this.sqlService.getSql("categoryGet"), this.userMapper, name);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void update(Category category) {
		this.jdbcTemplate.update(this.sqlService.getSql("categoryUpdate"),
				new MappedBeanPropertySqlParameterSource(category));

	}

	public List<Category> getAll() {
		return this.jdbcTemplate.query(this.sqlService.getSql("categoryGetAll"), this.userMapper);
	}

	public void deleteAll() {
		this.jdbcTemplate.update(this.sqlService.getSql("categoryDeleteAll"));
	}

	public void delete(int id) {
		this.jdbcTemplate.update(this.sqlService.getSql("categoryDelete"), id);
	}

	public int getCount() {

		return this.jdbcTemplate.queryForInt(this.sqlService.getSql("categoryGetCount"));
	}
}
