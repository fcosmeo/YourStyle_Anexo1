package pe.com.yourstyleapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frank on 05/08/2017.
 */
public class StatusEntity  extends BaseEntity{
    public StatusEntity() {
        super("status");
    }

    private List<Status> findByCriteria(String sql) {
        List<Status> statuss = new ArrayList<>();
        try {
            ResultSet rs = getConnection().createStatement().executeQuery(sql);
            while(rs.next()) {
                Status status = Status.build(rs);
                statuss.add(status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statuss;
    }

    public List<Status> findAll() {
        String statement = getDefaultStatement() + getTableName();
        return findByCriteria(statement);
    }

    public Status findById(int id) {
        String statement = "select * from status where id = " +
                String.valueOf(id);
        List<Status> statuss = findByCriteria(statement);
        return statuss != null ? statuss.get(0) : null;
    }
}
