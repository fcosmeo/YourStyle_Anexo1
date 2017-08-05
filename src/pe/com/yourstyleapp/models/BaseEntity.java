package pe.com.yourstyleapp.models;

import java.sql.Connection;

/**
 * Created by Frank on 05/08/2017.
 */
public class BaseEntity {
    private Connection connection;
    private String tableName;
    private String defaultStatement = "SELECT * FROM ";

    public BaseEntity() {
    }

    public BaseEntity(String tableName) {
        this.tableName = tableName;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDefaultStatement() {
        return defaultStatement;
    }

    public void setDefaultStatement(String defaultStatement) {
        this.defaultStatement = defaultStatement;
    }

}
