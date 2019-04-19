package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final Context context;
    UserDao(DataSource dataSource){
        context = new Context(dataSource);
    }
    public User get(Long id) throws SQLException {
        String sql = "select * from userinfo where id = ?";
        Object[] params = new Object[] {id};
        return context.get(sql, params);
    }
    public Long add(User user) throws SQLException {
        String sql = "insert into userinfo(name,password) values (?,?)";
        Object[] params = new Object[] {user.getName(), user.getPassword()};
        return context.add(sql, params);
    }
    public void update(User user) throws SQLException {
        String sql = "update userinfo set name =?, password=? where id =?";
        Object[] params = new Object[] {user.getName(), user.getPassword(), user.getId()};
        context.update(sql, params);
    }
    public void delete(Long id) throws SQLException{
        String sql = "delete from userinfo where id = ?";
        Object[] params = new Object[] {id};
        context.update(sql, params);
    }


}
