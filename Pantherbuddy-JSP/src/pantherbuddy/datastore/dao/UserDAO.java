package pantherbuddy.datastore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pantherbuddy.business.controller.utility.UpdateColumn;
import pantherbuddy.business.model.UserModel;

/**
 * This class represents the data access object pertaining to user entity.
 *
 * @author ALLAN
 *
 */
public class UserDAO {

	/**
	 * Gets the user by email id.
	 *
	 * @param user
	 *            {@link UserModel}
	 * @return {@link UserModel}
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public UserModel getUserByEmail(UserModel user)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		UserModel model = null;
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement("SELECT userid, email, password, status FROM pantherbuddy.user WHERE email = ?");
			preparedStatement.setString(1, user.getEmailId());
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				model = new UserModel();
				model.setUserId(resultSet.getInt("userid"));
				model.setEmailId(resultSet.getString("email"));
				model.setPassword(resultSet.getString("password"));
				model.setStatus(resultSet.getBoolean("status"));
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return model;
	}

	public void updateUser(UserModel user, UpdateColumn column)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = getConnection();
			if (column.equals(UpdateColumn.STATUS)) {
				PreparedStatement preparedStatement = conn
						.prepareStatement("UPDATE pantherbuddy.user SET status = TRUE WHERE userid = ?;");
				preparedStatement.setInt(1, user.getUserId());
				preparedStatement.executeUpdate();
			} else if (column.equals(UpdateColumn.PASSWORD)) {
				PreparedStatement preparedStatement = conn
						.prepareStatement("UPDATE pantherbuddy.user SET password = ? WHERE userid = ?;");
				preparedStatement.setString(1, user.getPassword());
				preparedStatement.setInt(2, user.getUserId());
				preparedStatement.executeUpdate();
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public UserModel getUser(Integer id) throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = null;
		UserModel model = null;
		try {
			conn = getConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement("SELECT * FROM pantherbuddy.user WHERE userid = ?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				model = new UserModel();
				model.setFname(rs.getString("fname"));
				model.setLname(rs.getString("lname"));
				model.setEmailId(rs.getString("email"));
				model.setPhoneNumber(rs.getLong("phonenumber"));
				model.setUserId(rs.getInt("userid"));
				model.setPassword(rs.getString("password"));
				model.setStatus(rs.getBoolean("status"));
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return model;
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
	public Connection getConnection() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager
				.getConnection("jdbc:mysql://localhost/pantherbuddy?"
						+ "user=allanshaji&password=o1pktp");
		return conn;
	}

	public void createUser(UserModel user) throws SQLException,
	InstantiationException, IllegalAccessException,
	ClassNotFoundException {
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement("INSERT INTO pantherbuddy.user VALUES (DEFAULT, ?, ?, ?, ?, ?, FALSE, NOW())");
			preparedStatement.setString(1, user.getFname());
			preparedStatement.setString(2, user.getLname());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setLong(4, user.getPhoneNumber());
			preparedStatement.setString(5, user.getEmailId());

			int rs = preparedStatement.executeUpdate();

			if (rs == 0) {
				throw new SQLException();
			}

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void deleteUser(Integer userid) throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement("DELETE FROM pantherbuddy.user WHERE userid = ?");
			preparedStatement.setInt(1, userid);
			int rs = preparedStatement.executeUpdate();
			if (rs == 0) {
				throw new SQLException("Could not delete created user");
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
