package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStatmentStrategy implements StatementStrategy {
    User user;
    AddStatmentStrategy(User user){
        this.user = user;
    }
    @Override
    public PreparedStatement makePrepareStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo(name,password) values (?,?)");
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2, user.getPassword());
        return preparedStatement;
    }
}
