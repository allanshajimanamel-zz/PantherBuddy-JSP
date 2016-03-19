package testcases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pantherbuddy.business.controller.utility.UpdateColumn;
import pantherbuddy.business.model.UserModel;
import pantherbuddy.datastore.dao.UserDAO;

public class Test_03_SubsystemTest_UserDAO {
	private Connection conn;
	private UserDAO userdao;

	@Before
	public void setUp() throws Exception {
		userdao = new UserDAO();
		conn = DriverManager
				.getConnection("jdbc:mysql://localhost/pantherbuddy?"
						+ "user=allanshaji&password=o1pktp");

		PreparedStatement preparedStatement = conn
				.prepareStatement("INSERT INTO pantherbuddy.user VALUES (149, 'John', 'Doe', 'dddd', '1234567890', '123test@mail.com', FALSE, NOW())");
		preparedStatement.executeUpdate();
	}

	@After
	public void tearDown() throws Exception {

		if (conn != null) {
			PreparedStatement preparedStatement = conn
					.prepareStatement("DELETE FROM pantherbuddy.user WHERE email = 'qiu15@gmail.com'");
			preparedStatement.executeUpdate();
			PreparedStatement ps = conn
					.prepareStatement("DELETE FROM pantherbuddy.user WHERE userid = 149");
			ps.executeUpdate();
		}

		if (!conn.isClosed())
			conn.close();
	}

	@Test
	public void testCreateUserPositive() throws SQLException {
		UserModel newuser = new UserModel();
		newuser.setFname("qiu");
		newuser.setLname("qiu");
		newuser.setPassword("123");
		newuser.setPhoneNumber(7869256598L);
		newuser.setEmailId("qiu15@gmail.com");

		try {
			userdao.createUser(newuser);
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test(expected = Exception.class)
	public void testCreateUserNegative() throws SQLException,
	InstantiationException, IllegalAccessException,
	ClassNotFoundException {
		UserModel newuser = new UserModel();
		newuser.setFname("qiu");
		newuser.setLname("qiu");
		newuser.setPassword("123");
		newuser.setPhoneNumber(7869256598L);
		newuser.setEmailId("qiu@gmail.com");

		userdao.createUser(newuser);
	}

	@Test
	public void testDeleteUserPositive() throws SQLException,
	InstantiationException, IllegalAccessException,
	ClassNotFoundException {
		Integer userid = 149;
		try {
			userdao.deleteUser(userid);
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test(expected = Exception.class)
	public void testDeleteUserNegative() throws SQLException,
	InstantiationException, IllegalAccessException,
	ClassNotFoundException {
		Integer userid = 9999;
		userdao.deleteUser(userid);
	}

	@Test
	public void testUpdateUserPositive() throws SQLException,
	InstantiationException, IllegalAccessException,
	ClassNotFoundException {
		UserModel newuser = new UserModel();
		Integer uid = 1;
		newuser.setUserId(uid);

		userdao.updateUser(newuser, UpdateColumn.STATUS);

		String sql = "select status from user where userid = " + uid;
		ResultSet rs = conn.createStatement().executeQuery(sql);
		rs.next();
		int newstatus = rs.getInt("status");
		boolean success = (newstatus == 1);
		Assert.assertTrue(success);
	}

	@Test
	public void testUpdateUserNegative() throws SQLException,
	InstantiationException, IllegalAccessException,
	ClassNotFoundException {
		UserModel newuser = new UserModel();
		Integer uid = 1;
		newuser.setUserId(uid);

		userdao.updateUser(newuser, UpdateColumn.STATUS);

		String sql = "select status from user where userid = " + uid;
		ResultSet rs = conn.createStatement().executeQuery(sql);
		rs.next();
		int newstatus = rs.getInt("status");
		boolean success = (newstatus == 1);
		Assert.assertTrue(success);
	}

	@Test
	public void testGetUserPositive() throws SQLException,
	InstantiationException, IllegalAccessException,
	ClassNotFoundException {
		Integer uid = 1;
		UserModel user = userdao.getUser(uid);
		boolean success = (user != null);
		Assert.assertTrue(success);
	}

	@Test
	public void testGetUserNegative() throws SQLException,
	InstantiationException, IllegalAccessException,
	ClassNotFoundException {
		Integer uid = 9999;
		UserModel user = userdao.getUser(uid);
		boolean success = (user == null);
		Assert.assertTrue(success);
	}

	@Test
	public void testGetUserByEmailPositive() throws SQLException,
	InstantiationException, IllegalAccessException,
	ClassNotFoundException {
		UserModel user = new UserModel();
		user.setEmailId("qiu@gmail.com");
		UserModel result = userdao.getUserByEmail(user);
		boolean success = (result != null);
		Assert.assertTrue(success);
	}

	@Test
	public void testGetUserByEmailNegative() throws SQLException,
	InstantiationException, IllegalAccessException,
	ClassNotFoundException {
		UserModel user = new UserModel();
		user.setEmailId("xxxxxx@gmail.com");
		UserModel result = userdao.getUserByEmail(user);
		boolean success = (result == null);
		Assert.assertTrue(success);
	}
}
