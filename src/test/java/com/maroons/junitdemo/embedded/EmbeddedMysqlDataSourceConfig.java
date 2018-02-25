package com.maroons.junitdemo.embedded;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
@Configuration
public class EmbeddedMysqlDataSourceConfig {
	private MysqlDataSource dataSource;
	
	@Bean
	public DataSource getDataSource() {
		if (dataSource == null) {
			EmbeddedMysqlManager mysqlManager = EmbeddedMysqlManager.getInstance();
			dataSource = new MysqlDataSource();
			dataSource.setURL(mysqlManager.getUrl());
			dataSource.setUser(mysqlManager.getUsername());
			dataSource.setPassword(mysqlManager.getPassword());
		}
		return dataSource;
	}
}
