package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
    public PreparedStatement makePrepareStatement(Connection connection) throws SQLException;
}
