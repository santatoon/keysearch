package santatoon.wand.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import santatoon.wand.domain.Admin;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="file:WebContent/WEB-INF/applicationContext.xml")
public class AdminDaoTest {
	@Autowired
	private AdminDao dao;

	private Admin user1;
	private Admin user2;
	private Admin user3;

	@Before
	public void setUp() {
		
		this.user1 = new Admin("id1", "password1", "name1", "phone1", "email1");
		this.user2 = new Admin("id2", "password2", "name2", "phone2", "email2");
		this.user3 = new Admin("id3", "password3", "name3", "phone3", "email3");

	}

	@Test
	public void add() throws SQLException {

		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		Admin userget1 = dao.get(user1.getId());
		checkSameUser(userget1, user1);

		Admin userget2 = dao.get(user2.getId());
		checkSameUser(userget2, user2);

	}
	
	@Test
	public void update(){
		dao.deleteAll();
		
		dao.add(user1);
		
		user1.setPassword("updated1");
		
		dao.update(user1);
		
		Admin user1update = dao.get(user1.getId());
		checkSameUser(user1, user1update);
	}

	@Test
	public void getAll() throws SQLException {
		dao.deleteAll();

		List<Admin> users0 = dao.getAll();
		assertThat(users0.size(), is(0));

		dao.add(user1);
		List<Admin> users1 = dao.getAll();
		assertThat(users1.size(), is(1));

		dao.add(user2);
		List<Admin> users2 = dao.getAll();
		assertThat(users2.size(), is(2));

		dao.add(user3);
		List<Admin> users3 = dao.getAll();
		assertThat(users3.size(), is(3));
	}


	@Test
	public void count() throws SQLException {

		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		dao.add(user1);
		assertThat(dao.getCount(), is(1));

		dao.add(user2);
		assertThat(dao.getCount(), is(2));

		dao.add(user3);
		assertThat(dao.getCount(), is(3));
	}
	
	private void checkSameUser(Admin user1, Admin user2) {
		assertThat(user1.getId(), is(user2.getId()));
		assertThat(user1.getPassword(), is(user2.getPassword()));
	}
}
