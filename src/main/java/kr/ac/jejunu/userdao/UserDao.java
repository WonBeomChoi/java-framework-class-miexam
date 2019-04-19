package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final Context context;
    UserDao(DataSource dataSource){
        context = new Context(dataSource);
    }
    public User get(Long id) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
        return context.getContext(statementStrategy);
    }
    public Long add(User user) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo(name,password) values (?,?)");
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2, user.getPassword());
            return preparedStatement;
        };
        return context.addContext(statementStrategy);
    }
    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("update userinfo set name =?, password=? where id =?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());

            return preparedStatement;
        };
        context.updateContext(statementStrategy);
    }
    public void delete(Long id) throws SQLException{
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from userinfo where id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
        context.updateContext(statementStrategy);
    }
}
