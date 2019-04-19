package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final Context context;
    UserDao(DataSource dataSource){
        context = new Context(dataSource);
    }
    public User get(Long id) throws SQLException {
        StatementStrategy statementStrategy = new GetStatementStrategy(id);
        return context.getContext(statementStrategy);
    }
    public Long add(User user) throws SQLException {
        StatementStrategy statementStrategy = new AddStatmentStrategy(user);
        return context.addContext(statementStrategy);
    }
    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        context.updateContext(statementStrategy);
    }
    public void delete(Long id) throws SQLException{
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        context.updateContext(statementStrategy);
    }
}
