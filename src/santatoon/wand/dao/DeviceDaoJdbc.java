package santatoon.wand.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import santatoon.wand.domain.Device;
import santatoon.wand.domain.Status;

@Repository
public class DeviceDaoJdbc implements DeviceDao {
	private SimpleJdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert userInsert;
	private RowMapper<Device> userMapper = new RowMapper<Device>() {
		public Device mapRow(ResultSet rs, int rowNum) throws SQLException {
			Device device = new Device();
			device.setId(rs.getInt("id"));
			device.setPushid(rs.getString("pushid"));
			device.setCustomerid(rs.getInt("customerid"));
			device.setStatus(Status.valueOf(rs.getBoolean("status")));
			device.setCreated(rs.getDate("created"));
			device.setModified(rs.getDate("modified"));
			return device;
		}
	};

	/*
	 * private RowMapper<Device> mobileMapper = new RowMapper<Device>() { public
	 * Device mapRow(ResultSet rs, int rowNum) throws SQLException { Device
	 * device = new Device(); event.setId(rs.getInt("id"));
	 * event.setName(rs.getString("name"));
	 * event.setImage(rs.getString("image"));
	 * event.setIntroduce(rs.getString("introduce"));
	 * event.setDateStart(rs.getDate("dateStart"));
	 * event.setDateEnd(rs.getDate("dateEnd"));
	 * event.setIsuse(rs.getBoolean("isuse")); return event; } };
	 * 
	 */

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
		this.userInsert = new SimpleJdbcInsert(dataSource).withTableName("devices").usingGeneratedKeyColumns("id");
	}

	public int add(final Device device) {

		int generatedId = this.userInsert.executeAndReturnKey(new DeviceBeanPropertySqlParameterSource(device))
				.intValue();

		return generatedId;

	}

	public Device get(int id) {
		try {
			return this.jdbcTemplate.queryForObject(this.sqlService.getSql("deviceGet"), this.userMapper, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void update(Device device) {
		this.jdbcTemplate.update(this.sqlService.getSql("deviceUpdate"),
				new DeviceBeanPropertySqlParameterSource(device));

	}

	public List<Device> getAll() {
		return this.jdbcTemplate.query(this.sqlService.getSql("deviceGetAll"), this.userMapper);
	}

	public void deleteAll() {
		this.jdbcTemplate.update(this.sqlService.getSql("deviceDeleteAll"));
	}

	public void delete(int id) {
		this.jdbcTemplate.update(this.sqlService.getSql("deviceDelete"), id);
	}

	public int getCount() {

		return this.jdbcTemplate.queryForInt(this.sqlService.getSql("deviceGetCount"));
	}

	private static class DeviceBeanPropertySqlParameterSource extends MappedBeanPropertySqlParameterSource {
		public DeviceBeanPropertySqlParameterSource(Object object) {
			super(object);
			map("status", "status.value");
		}
	}

	@Override
	public List<Device> getAllForMobile(int id) {
		
		try {
			List<Device> devices = new ArrayList<Device>();
			
			
			for (Device device : this.jdbcTemplate.query(this.sqlService.getSql("deviceGetAllForMobile"), this.userMapper,
					id)) {
				devices.add(device);
			}
			
			return devices;

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		
		
		
	}
}
