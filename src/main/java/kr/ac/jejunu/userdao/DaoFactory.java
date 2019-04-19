package kr.ac.jejunu.userdao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DaoFactory {

    @Value("${db.classname}")
    String className = "com.mysql.cj.jdbc.Driver";
    @Value("${db.url}")
    String url = "jdbc:mysql://localhost/jeju?serverTimezone=UTC";
    @Value("${db.username}")
    String userName = "root";
    @Value("${db.password}")
    String password = "dnjs2310!@";

    @Bean
    public UserDao getUserDao() throws ClassNotFoundException {
        return new UserDao(jdbcTemplate());
    }

    @Bean
    public JejuJdbcTemplate jdbcTemplate() throws ClassNotFoundException {
        return new JejuJdbcTemplate(getDataSource());
    }
    @Bean
    public DataSource getDataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }
    }
