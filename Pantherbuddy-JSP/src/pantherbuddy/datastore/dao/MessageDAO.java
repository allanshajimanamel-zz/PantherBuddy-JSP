package pantherbuddy.datastore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pantherbuddy.business.model.MessageModel;

/**
 * This class represents the data access object pertaining to message entity.
 *
 * @author ALLAN
 *
 */
public class MessageDAO {

	public void createMessage(MessageModel message) throws SQLException,
	InstantiationException, IllegalAccessException,
	ClassNotFoundException {
		Connection conn = null;
		try {

			conn = getConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement("INSERT INTO pantherbuddy.message values (DEFAULT,?,?,NOW())");
			preparedStatement.setInt(1, message.getUserId());
			preparedStatement.setString(2, message.getMessage());
			int rs = preparedStatement.executeUpdate();
			if (rs == 0) {
				throw new SQLException("Could not save the message");
			}

		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public List<MessageModel> getMessages(Integer rangeFrom)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		Connection conn2 = null;
		Connection conn = null;
		List<MessageModel> models = new ArrayList<>();
		try {
			conn = getConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement("SELECT * FROM pantherbuddy.message ORDER BY messagedate DESC LIMIT ?,20");
			preparedStatement.setInt(1, rangeFrom);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				MessageModel messageModel = new MessageModel();
				messageModel.setMessage(rs.getString("message"));
				messageModel.setMessageDate(rs.getDate("messagedate"));
				messageModel.setMessageId(rs.getInt("messageid"));
				messageModel.setUserId(rs.getInt("userid"));
				conn2 = getConnection();
				PreparedStatement statement = conn2
						.prepareStatement("SELECT fname, lname FROM pantherbuddy.user WHERE userid = ?");
				statement.setInt(1, rs.getInt("userid"));
				ResultSet resultSet = statement.executeQuery();
				if (resultSet.next()) {
					String fname = resultSet.getString("fname");
					String lname = resultSet.getString("lname");
					messageModel.setUsername(fname + " " + lname);
				}
				models.add(messageModel);
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (conn2 != null) {
				conn2.close();
			}
		}
		return models;
	}

	public void deleteMessage(Integer id) throws SQLException,
	InstantiationException, IllegalAccessException,
	ClassNotFoundException {
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement("DELETE FROM pantherbuddy.message WHERE messageid = ?");
			preparedStatement.setInt(1, id);
			int rs = preparedStatement.executeUpdate();
			if (rs == 0) {
				throw new SQLException("Could not delete message.");
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	/**
	 * Gets the connection to database.
	 *
	 * @return an instance of {@link Connection}.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection getConnection() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager
				.getConnection("jdbc:mysql://localhost/pantherbuddy?"
						+ "user=allanshaji&password=o1pktp");
		return conn;
	}
}
