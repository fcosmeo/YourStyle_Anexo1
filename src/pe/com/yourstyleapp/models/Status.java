package pe.com.yourstyleapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Frank on 05/08/2017.
 */
public class Status {
    private int id;
    private String description;

    public Status(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Status() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Status build(ResultSet resultSet) {
        try {
            return new Status(
                    resultSet.getInt("id"),
                    resultSet.getString("description"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
