package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final DataSource connetionMaker;
    UserDao(DataSource dataSource){
        this.connetionMaker = dataSource;
    }

    public User get(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = connetionMaker.getConnection();

            StatementStrategy statementStrategy = new GetStatementStrategy(id);
            preparedStatement = statementStrategy.makePrepareStatement(connection);

            resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }

        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }


    public Long add(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Long id = null;
        try {
            connection = connetionMaker.getConnection();

            StatementStrategy statementStrategy = new AddStatmentStrategy(user);
            preparedStatement = statementStrategy.makePrepareStatement(connection);
            preparedStatement.executeUpdate();


//            id = resultSet.getLong(1);
            id = getLastInsertId(connection);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        return id;

    }

    private Long getLastInsertId(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long id = null;
        try {
            preparedStatement = connection.prepareStatement("select last_insert_id()");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            id = resultSet.getLong(1);
        } finally {
            preparedStatement.close();
            resultSet.close();
        }

        return id;
    }

    public void update(User user) throws SQLException {

        Connection connection= null;
        PreparedStatement preparedStatement=null;
        try {
            connection = connetionMaker.getConnection();
            StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
            preparedStatement = statementStrategy.makePrepareStatement(connection);
            preparedStatement.executeUpdate();
        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    public void delete(Long id) throws SQLException{
        Connection connection= null;
        PreparedStatement preparedStatement=null;
        try {
            connection = connetionMaker.getConnection();
            StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
            preparedStatement = statementStrategy.makePrepareStatement(connection);
            preparedStatement.executeUpdate();
        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
