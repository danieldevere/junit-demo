package com.maroons.junitdemo.embedded;

import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.config.SchemaConfig.aSchemaConfig;
import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.distribution.Version.v5_6_23;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.wix.mysql.config.Charset.UTF8;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.SqlScriptSource;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.config.SchemaConfig;

public class EmbeddedMysqlManager {
	private EmbeddedMysqlManager() {
		startDatabase();
	}
	
	private static EmbeddedMysqlManager instance;
	
	private EmbeddedMysql mysqld;
	
	private final String username = "testUser";
	
	private final String password = "testPassword";
	
	private int dbPort = 4408;
	
	private String schemaName = "junitTest";
	
	private static final String SCHEMA = "db/migration/V1__junitTest.sql";
	
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getUrl() {
		return "jdbc:mysql://localhost:" + dbPort + "/" + schemaName;
	}
	
	public static EmbeddedMysqlManager getInstance() {
		if (instance == null) {
			instance = new EmbeddedMysqlManager();
		}
		return instance;
	}
	
	private void startDatabase() {
		MysqldConfig config = aMysqldConfig(v5_6_23)
				.withCharset(UTF8)
				.withPort(dbPort)
				.withUser(username, password)
				.withTimeZone("USA/Central")
				.withTimeout(2, TimeUnit.MINUTES)
				.withServerVariable("max_connect_errors", 700)
				.build();
		SchemaConfig schemaConfig = aSchemaConfig(schemaName)
				.withCharset(UTF8)
				.build();
		mysqld = anEmbeddedMysql(config)
				.addSchema(schemaConfig)
				.start();
	}
	
	public void reloadSchema(String... dataTables) {
		List<SqlScriptSource> scripts = new LinkedList<SqlScriptSource>();
		for (String dataFile : dataTables) {
			scripts.add(classPathScript(dataFile));
		}
		SchemaConfig schemaConfig = aSchemaConfig(schemaName)
				.withScripts(classPathScript(SCHEMA))
				.withCharset(UTF8)
				.build();
		mysqld.reloadSchema(schemaConfig);
	}
}
