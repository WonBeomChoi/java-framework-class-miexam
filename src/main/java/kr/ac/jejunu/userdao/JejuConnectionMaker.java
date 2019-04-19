package kr.ac.jejunu.userdao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;
public class JejuConnectionMaker implements ConnectionMaker {
    @Value("${db.classname}")
    String className = "com.mysql.cj.jdbc.Driver";
    @Value("${db.url}")
    String url = "jdbc:mysql://localhost/jeju?serverTimezone=UTC";
    @Value("${db.username}")
    String userName = "root";
    @Value("${db.password}")
    String password = "dnjs2310!@";
    @Override
    public DataSource getConnection() throws ClassNotFoundException{
        SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
        simpleDriverDataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        simpleDriverDataSource.setUsername(userName);
        simpleDriverDataSource.setPassword(password);
        simpleDriverDataSource.setUrl(url);

        return simpleDriverDataSource;
    }
}
