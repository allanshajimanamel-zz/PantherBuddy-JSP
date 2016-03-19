package testcases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pantherbuddy.business.model.MessageModel;
import pantherbuddy.datastore.dao.MessageDAO;

public class Test_04_SubsystemTest_MessageDAO {
	private Connection conn;
	private MessageDAO messagedao;
	@Before
	public void setUp() throws Exception{
		messagedao = new MessageDAO();
		conn = DriverManager.getConnection("jdbc:mysql://localhost/pantherbuddy?"
				+ "user=allanshaji&password=o1pktp");

		int id = 0;
		PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM pantherbuddy.message WHERE messageid = 109");
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
			id = rs.getInt("messageid");
		}
		if(id != 109){
			PreparedStatement ps = conn.prepareStatement("INSERT INTO pantherbuddy.message values (109,1, 'test message',NOW())");
			ps.executeUpdate();
		}
	}

	@After
	public void tearDown() throws Exception{
		conn.close();
	}

	@Test
	public void testCreateMessagePositive() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		MessageModel message = new MessageModel();
		Integer uid = 1;
		String content = "hello world";
		message.setUserId(uid);
		message.setMessage(content);
		try{
			messagedao.createMessage(message);
		}catch(Exception e){
			Assert.fail();
		}
	}

	@Test(expected = Exception.class)
	public void testCreateMessageNegative() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		MessageModel message = new MessageModel();
		Integer uid = 9999;
		String content = "hello world";
		message.setUserId(uid);
		message.setMessage(content);
		messagedao.createMessage(message);
	}
	@Test
	public void testDeleteMessagePositive() throws SQLException{
		Integer mid = 109;
		try{
			messagedao.deleteMessage(mid);
		}catch(Exception e){
			Assert.fail();
		}
	}

	@Test(expected = Exception.class)
	public void testDeleteMessageNegative() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Integer mid=1;
		messagedao.deleteMessage(mid);
	}
	@Test
	public void testGetMessagePositive() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Integer rf = 1;
		List<MessageModel> mm = messagedao.getMessages(rf);
		boolean success = (mm.size()>0);
		Assert.assertTrue(success);
	}

	@Test
	public void testGetMessageNegative() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Integer rf = 9999;
		List<MessageModel> mm = messagedao.getMessages(rf);
		boolean success = (mm.size()==0);
		Assert.assertTrue(success);
	}
}
