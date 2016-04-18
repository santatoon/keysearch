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
import santatoon.wand.domain.Customer;

@Repository
public class CustomerDaoJdbc implements CustomerDao {
	private SimpleJdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert userInsert;
	private RowMapper<Customer> userMapper = new RowMapper<Customer>() {
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setId(rs.getInt("id"));
			customer.setName(rs.getString("name"));
			customer.setPhone(rs.getString("phone"));
			customer.setRef(rs.getString("ref"));
			customer.setCreated(rs.getDate("created"));
			customer.setModified(rs.getDate("modified"));
			return customer;
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
				.withTableName("customers").usingGeneratedKeyColumns("id");;
	}

	public int add(final Customer customer) {
		
		
		int generatedId = this.userInsert.executeAndReturnKey(new MappedBeanPropertySqlParameterSource(
				customer)).intValue();

		return generatedId;
	}

	public Customer get(int id) {
		try {
			return this.jdbcTemplate.queryForObject(
					this.sqlService.getSql("customerGet"), this.userMapper, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void update(Customer customer) {
		this.jdbcTemplate.update(this.sqlService.getSql("customerUpdate"),
				new MappedBeanPropertySqlParameterSource(customer));

	}

	public List<Customer> getAll() {
		return this.jdbcTemplate.query(
				this.sqlService.getSql("customerGetAll"), this.userMapper);
	}

	public void deleteAll() {
		this.jdbcTemplate.update(this.sqlService.getSql("customerDeleteAll"));
	}

	public void delete(int id) {
		this.jdbcTemplate.update(this.sqlService.getSql("customerDelete"), id);
	}

	public int getCount() {

		return this.jdbcTemplate.queryForInt(this.sqlService
				.getSql("customerGetCount"));
	}


}
