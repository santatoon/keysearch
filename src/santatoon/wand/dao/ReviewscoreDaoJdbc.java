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
import santatoon.wand.domain.Reviewscore;

@Repository
public class ReviewscoreDaoJdbc implements ReviewscoreDao {
	private SimpleJdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert userInsert;
	private RowMapper<Reviewscore> userMapper = new RowMapper<Reviewscore>() {
		public Reviewscore mapRow(ResultSet rs, int rowNum) throws SQLException {
			Reviewscore reviewscore = new Reviewscore();
			reviewscore.setId(rs.getInt("id"));
			reviewscore.setWord(rs.getString("word"));
			reviewscore.setScore(rs.getInt("score"));
			reviewscore.setCreated(rs.getDate("created"));
			reviewscore.setModified(rs.getDate("modified"));
			return reviewscore;
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
		this.userInsert = new SimpleJdbcInsert(dataSource).withTableName("reviewscores").usingGeneratedKeyColumns("id");
		;
	}

	public int add(final Reviewscore reviewscore) {

		int generatedId = this.userInsert.executeAndReturnKey(new MappedBeanPropertySqlParameterSource(reviewscore))
				.intValue();

		return generatedId;
	}

	public Reviewscore get(String name) {
		try {
			return this.jdbcTemplate.queryForObject(this.sqlService.getSql("reviewscoreGet"), this.userMapper, name);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void update(Reviewscore reviewscore) {
		this.jdbcTemplate.update(this.sqlService.getSql("reviewscoreUpdate"),
				new MappedBeanPropertySqlParameterSource(reviewscore));

	}

	public List<Reviewscore> getAll() {
		return this.jdbcTemplate.query(this.sqlService.getSql("reviewscoreGetAll"), this.userMapper);
	}

	public void deleteAll() {
		this.jdbcTemplate.update(this.sqlService.getSql("reviewscoreDeleteAll"));
	}

	public void delete(int id) {
		this.jdbcTemplate.update(this.sqlService.getSql("reviewscoreDelete"), id);
	}

	public int getCount() {

		return this.jdbcTemplate.queryForInt(this.sqlService.getSql("reviewscoreGetCount"));
	}
}
