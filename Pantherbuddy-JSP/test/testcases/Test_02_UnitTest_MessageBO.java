package testcases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import pantherbuddy.business.controller.exceptions.InvalidInputException;
import pantherbuddy.business.controller.message.MessageBO;
import pantherbuddy.business.model.MessageModel;

public class Test_02_UnitTest_MessageBO {

	@Before
	public void setUp() throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager
				.getConnection("jdbc:mysql://localhost/pantherbuddy?"
						+ "user=allanshaji&password=o1pktp");

		PreparedStatement preparedStatement = conn
				.prepareStatement("DELETE FROM pantherbuddy.message");
		int rs = preparedStatement.executeUpdate();

		MessageModel message = new MessageModel();
		message.setMessage("1 Message from test");
		message.setUserId(1);

		PreparedStatement preparedStatement2 = conn
				.prepareStatement("INSERT INTO pantherbuddy.message values (100,?,?,NOW())");
		preparedStatement2.setInt(1, message.getUserId());
		preparedStatement2.setString(2, message.getMessage());
		int rs2 = preparedStatement2.executeUpdate();

		conn.close();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveMessagePositive() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	InvalidInputException {
		MessageModel message = new MessageModel();
		message.setMessage("2 Message from test");
		message.setUserId(1);

		MessageBO bo = Mockito.mock(MessageBO.class);
		Mockito.when(bo.saveMessage(Mockito.any())).thenCallRealMethod();

		boolean success = bo.saveMessage(message);
		Assert.assertTrue(success);
	}

	@Test(expected = InvalidInputException.class)
	public void testSaveMessageNegative() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	InvalidInputException {
		MessageModel message = new MessageModel();
		message.setMessage("<script>");
		message.setUserId(1);

		MessageBO bo = Mockito.mock(MessageBO.class);
		Mockito.when(bo.saveMessage(Mockito.any())).thenCallRealMethod();

		bo.saveMessage(message);
	}

	@Test
	public void testGetMessagesToShowPositive() {
		MessageBO bo = Mockito.mock(MessageBO.class);

		Mockito.when(bo.getMessagesToShow(Mockito.any())).thenCallRealMethod();

		List<MessageModel> list = bo.getMessagesToShow(0);

		Assert.assertTrue(list != null);
		boolean found = false;
		for(MessageModel model : list){
			if(model.getMessageId() == 100){
				found = true;
			}
		}
		if(!found){
			Assert.fail();
		}
	}

	@Test
	public void testGetMessagesToShowNegative() {
		MessageBO bo = Mockito.mock(MessageBO.class);

		Mockito.when(bo.getMessagesToShow(Mockito.any())).thenCallRealMethod();

		List<MessageModel> list = bo.getMessagesToShow(20);

		Assert.assertTrue(list != null && list.isEmpty());
	}

	@Test
	public void testDeleteMessagePositive() {
		MessageBO bo = Mockito.mock(MessageBO.class);

		Mockito.when(bo.deleteMessage(Mockito.any())).thenCallRealMethod();

		boolean success = bo.deleteMessage(100);

		Assert.assertTrue(success);
	}

	@Test
	public void testDeleteMessageNegative() {
		MessageBO bo = Mockito.mock(MessageBO.class);

		Mockito.when(bo.deleteMessage(Mockito.any())).thenCallRealMethod();

		boolean success = bo.deleteMessage(200);

		Assert.assertTrue(!success);
	}

}
