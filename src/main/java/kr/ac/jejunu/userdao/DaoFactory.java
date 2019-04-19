package kr.ac.jejunu.userdao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao getUserDao() throws SQLException, ClassNotFoundException {
        return new UserDao(getDataSource());
    }

    @Bean
    public DataSource getDataSource() throws SQLException, ClassNotFoundException {
        ConnectionMaker connectionMaker = new JejuConnectionMaker();
        return connectionMaker.getConnection();
    }
    }
