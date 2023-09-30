package net.terurion.core;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.terurion.api.SpaceAPI;

import java.sql.SQLException;

public class SQL {
    private String host, database, user, pass;
    private int port;
    private boolean useSSL;
    private HikariDataSource hikari;

    public SQL(String host, String database, String user, String pass, int port, boolean useSSL) {
        this.host = host;
        this.database = database;
        this.user = user;
        this.pass = pass;
        this.port = port;
        this.useSSL = useSSL;
    }

    public void connect() throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database);
        config.setUsername(user);
        config.setPassword(pass);
        config.addDataSourceProperty("useSSL", useSSL);
        hikari = new HikariDataSource(config);

        SpaceAPI.setServerConnection(hikari.getConnection());
    }
}
