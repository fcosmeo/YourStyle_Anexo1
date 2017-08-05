package pe.com.yourstyleapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Frank on 05/08/2017.
 */
public class PeopleEntity  extends BaseEntity{

    private GeneralEntity generalEntity;
    private StatusEntity statusEntity;

    public PeopleEntity() {
        super("people");
    }

    private List<Person> findByCriteria(String sql) {
        List<Person> people = new ArrayList<>();
        try {
            ResultSet rs = getConnection().createStatement().executeQuery(sql);
            while(rs.next()) {
                Person person = Person.build(rs,getStatusEntity());
                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    private int updateByCriteria(String sql) {
        try {
            return getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Person> findAll() {
        String statement = getDefaultStatement() + getTableName();
        return findByCriteria(statement);
    }

    public Person findById(int id) {
        String statement = "Select id,first_name,last_name,dni,email,status_id,registration_date,modification_date,send_mail From People " +
                " Where id = " +String.valueOf(id);
        List<Person> people = findByCriteria(statement);
        return people != null ? people.get(0) : null;
    }

    public Person findByEmail(String email) {
        String statement = "Select id,first_name,last_name,dni,email,status_id,registration_date,modification_date,send_mail From people " +
                " Where email = '" +String.valueOf(email)+"'";
        List<Person> people = findByCriteria(statement);
        return people.size()>0 ? people.get(0) : null;
        //return null;
    }

    public Person create(String first_name, String last_name, String dni, Date birth_date, String email, int status_id, int send_mail) {
        int id= getGeneralEntity().getIdTable(getTableName());
        String sql = "Insert Into people(id, first_name, last_name, dni, birth_date, email, status_id, registration_date, send_mail) " +
                " Values(" + String.valueOf( id) + ", '" + first_name + "', '"+ last_name + "','"+ dni +"','"+String.valueOf(birth_date)+"','"+ email   +"',"+String.valueOf( status_id )+
                ",'"+String.valueOf(getGeneralEntity().getCurrentDate())+"',"+String.valueOf( send_mail )+")";
        return updateByCriteria(sql) > 0 ? new Person(id, first_name, last_name,dni, birth_date,email, getStatusEntity().findById(status_id), getGeneralEntity().getCurrentDate(),null,send_mail) : null;
    }

    public StatusEntity getStatusEntity() {
        return statusEntity;
    }

    public void setStatusEntity(StatusEntity statesEntity) {
        this.statusEntity = statesEntity;
    }

    public GeneralEntity getGeneralEntity() {
        return generalEntity;
    }

    public void setGeneralEntity(GeneralEntity generalEntity) {
        this.generalEntity = generalEntity;
    }
}