package santatoon.wand.dao;

import java.net.URLDecoder;
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
import santatoon.wand.domain.Scrapbook;

@Repository
public class ScrapbookDaoJdbc implements ScrapbookDao {
	private SimpleJdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert userInsert;
	private RowMapper<Scrapbook> userMapper = new RowMapper<Scrapbook>() {
		public Scrapbook mapRow(ResultSet rs, int rowNum) throws SQLException {
			Scrapbook scrapbook = new Scrapbook();
			scrapbook.setId(rs.getInt("id"));
			scrapbook.setCustomerid(rs.getInt("customerid"));
			scrapbook.setLink(rs.getString("link"));
			scrapbook.setTags(URLDecoder.decode(rs.getString("tags")));
			scrapbook.setCaption(URLDecoder.decode(rs.getString("caption")));
			scrapbook.setImageurl(rs.getString("imageurl"));
			scrapbook.setCreated(rs.getDate("created"));
			scrapbook.setModified(rs.getDate("modified"));
			return scrapbook;
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
		this.userInsert = new SimpleJdbcInsert(dataSource).withTableName("scrapbooks").usingGeneratedKeyColumns("id");
		;
	}

	public int add(final Scrapbook scrapbook) {

		int generatedId = this.userInsert.executeAndReturnKey(new MappedBeanPropertySqlParameterSource(scrapbook))
				.intValue();

		return generatedId;
	}

	public Scrapbook get(int id) {
		try {
			return this.jdbcTemplate.queryForObject(this.sqlService.getSql("scrapbookGet"), this.userMapper, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void update(Scrapbook scrapbook) {
		this.jdbcTemplate.update(this.sqlService.getSql("scrapbookUpdate"),
				new MappedBeanPropertySqlParameterSource(scrapbook));

	}

	public List<Scrapbook> getAll(int customerid) {
		return this.jdbcTemplate.query(this.sqlService.getSql("scrapbookGetAll"), this.userMapper, customerid);
	}

	public void deleteAll() {
		this.jdbcTemplate.update(this.sqlService.getSql("scrapbookDeleteAll"));
	}

	public void delete(int id) {
		this.jdbcTemplate.update(this.sqlService.getSql("scrapbookDelete"), id);
	}

	public int getCount() {

		return this.jdbcTemplate.queryForInt(this.sqlService.getSql("scrapbookGetCount"));
	}
}
