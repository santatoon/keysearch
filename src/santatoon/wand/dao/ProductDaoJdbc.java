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
import santatoon.wand.domain.Product;

@Repository
public class ProductDaoJdbc implements ProductDao {
	private SimpleJdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert userInsert;
	private RowMapper<Product> userMapper = new RowMapper<Product>() {
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setCategoryid(rs.getInt("categoryid"));
			product.setManufacturerid(rs.getInt("manufacturerid"));
			product.setName(rs.getString("name"));
			product.setCreated(rs.getDate("created"));
			product.setModified(rs.getDate("modified"));
			return product;
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
		this.userInsert = new SimpleJdbcInsert(dataSource).withTableName("products").usingGeneratedKeyColumns("id");
		;
	}

	public int add(final Product product) {

		int generatedId = this.userInsert.executeAndReturnKey(new MappedBeanPropertySqlParameterSource(product))
				.intValue();

		return generatedId;
	}

	public Product get(String name) {
		try {
			return this.jdbcTemplate.queryForObject(this.sqlService.getSql("productGet"), this.userMapper, name);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void update(Product product) {
		this.jdbcTemplate.update(this.sqlService.getSql("productUpdate"),
				new MappedBeanPropertySqlParameterSource(product));

	}

	public List<Product> getAll() {
		return this.jdbcTemplate.query(this.sqlService.getSql("productGetAll"), this.userMapper);
	}

	public void deleteAll() {
		this.jdbcTemplate.update(this.sqlService.getSql("productDeleteAll"));
	}

	public void delete(int id) {
		this.jdbcTemplate.update(this.sqlService.getSql("productDelete"), id);
	}

	public int getCount() {

		return this.jdbcTemplate.queryForInt(this.sqlService.getSql("productGetCount"));
	}
}
