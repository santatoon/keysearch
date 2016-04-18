package santatoon.sqlservice;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.Unmarshaller;

import santatoon.sqlservice.jaxb.SqlType;
import santatoon.sqlservice.jaxb.Sqlmap;
import santatoon.wand.dao.CustomerDao;

public class OxmSqlService implements SqlService {
	private final BaseSqlService baseSqlService = new BaseSqlService();
	
	private final OxmSqlReaderWAND oxmSqlReader = new OxmSqlReaderWAND();

	private SqlRegistry sqlRegistry = new HashMapSqlRegistry();

	public void setSqlRegistry(SqlRegistry sqlRegistry) {
		this.sqlRegistry = sqlRegistry;
	}

	public void setSqlmap(Resource sqlmap){
		this.oxmSqlReader.setSqlmap(sqlmap);
	}
	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.oxmSqlReader.setUnmarshaller(unmarshaller);
	}

	@PostConstruct
	public void loadSql() {
		this.baseSqlService.setSqlReader(this.oxmSqlReader);
		this.baseSqlService.setSqlRegistry(this.sqlRegistry);
		
		this.baseSqlService.loadSql();
	}

	public String getSql(String key) throws SqlRetrievalFailureException {
		return this.baseSqlService.getSql(key);
	}

	private class OxmSqlReaderWAND implements SqlReader {
		private Unmarshaller unmarshaller;
		private Resource sqlmap = new ClassPathResource("sqlmap.xml", CustomerDao.class);

		public void setUnmarshaller(Unmarshaller unmarshaller) {
			this.unmarshaller = unmarshaller;
		}

		public void setSqlmap(Resource sqlmap) {
			this.sqlmap = sqlmap;
		}

		public void read(SqlRegistry sqlRegistry) {
			try {
				Source source = new StreamSource(sqlmap.getInputStream());
				Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(source);

				for (SqlType sql : sqlmap.getSql()) {
					sqlRegistry.registerSql(sql.getKey(), sql.getValue());
				}
			} catch (IOException e) {
				throw new IllegalArgumentException(this.sqlmap.getFilename()
						+ "을 가져올 수 없습니다.", e);
			}
		}
	}

}
