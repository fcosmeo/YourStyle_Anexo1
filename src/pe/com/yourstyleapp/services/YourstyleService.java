package pe.com.yourstyleapp.services;

import pe.com.yourstyleapp.models.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frank on 05/08/2017.
 */
public class YourstyleService {
    private Connection connection;
    private PeopleEntity peopleEntity;
    private StatusEntity statusEntity;
    private GeneralEntity generalEntity;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public PeopleEntity getPeopleEntity() {
        if(connection != null) {
            if(peopleEntity == null) {
                peopleEntity = new PeopleEntity();
                peopleEntity.setConnection(getConnection());
                peopleEntity.setStatusEntity(getStatusEntity());
                peopleEntity.setGeneralEntity(getGeneralEntity());
            }
        }
        return peopleEntity;
    }

    public StatusEntity getStatusEntity() {
        if(connection != null) {
            if(statusEntity == null) {
                statusEntity = new StatusEntity();
                statusEntity.setConnection(getConnection());
            }
        }
        return statusEntity;
    }

    public GeneralEntity getGeneralEntity() {
        if(connection != null) {
            if(generalEntity == null) {
                generalEntity =new GeneralEntity();
                generalEntity.setConnection(getConnection());
            }
        }
        return generalEntity;
    }

    public List<Person> findAllPeople() {
        return getPeopleEntity().findAll();
    }

    public Person findPersonByEmail(String email) {
        return getPeopleEntity().findByEmail(email);
    }

    public Person findPersonById(int id) {
        return getPeopleEntity().findById(id);
    }

    public List<Status> findAllStatus() {
        return getStatusEntity().findAll();
    }

    public Status findStatusById(int id) {
        return getStatusEntity().findById(id);
    }

    public Person addPerson(Person person){
        return getPeopleEntity().create(person.getFirstName(),person.getLastName(),person.getDni(),person.getBirthDate(),person.getEmail(),person.getCellPhone(),1,1);
    }
}
