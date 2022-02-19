package com.example.L8mysqldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootApplication
public class L8MysqlDemoApplication {

	public static void main(String[] args) throws SQLException {

		SpringApplication.run(L8MysqlDemoApplication.class, args);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_system","app1","jbdl26");
		PreparedStatement preparedStatement = con.prepareStatement("update seat set status='C' where id=?");
		PreparedStatement preparedStatementRead = con.prepareStatement("Select * from seat where id=? for update");
		preparedStatementRead.setInt(1,1);
		preparedStatement.setInt(1,1);
		boolean autoCommit = con.getAutoCommit();
		try {
			con.setAutoCommit(false);
			preparedStatementRead.executeQuery();
			preparedStatement.executeUpdate();
			con.commit();
		} catch (SQLException exc) {
			con.rollback();
		} finally {
			con.setAutoCommit(autoCommit);
		}


	}

}
