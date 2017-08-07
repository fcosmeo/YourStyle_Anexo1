package pe.com.yourstyleapp.beans;

import pe.com.yourstyleapp.models.*;
import pe.com.yourstyleapp.services.YourstyleService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Frank on 05/08/2017.
 */
@ManagedBean(name = "service", eager = true)
@SessionScoped
public class SuscriptionServiceBean {
    private YourstyleService service;
    private Person person;
    private String message;

    public SuscriptionServiceBean() {
        try {
            InitialContext ctx = new InitialContext();
            Connection connection = ((DataSource) ctx
                    .lookup("jdbc/SqlServerDataSource_YourStyle"))
                    .getConnection();
            service = new YourstyleService();
            service.setConnection(connection);
            person=new Person();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getPeople() { return service.findAllPeople(); }

    public String listPeople() { return "success";}


    public String registerPerson(){
        Person personaAuxiliary = new Person();
        personaAuxiliary=service.addPerson(person);
        if( personaAuxiliary.getId() > 0 ){
            person=personaAuxiliary;
            this.message="";
            return "success";
        }else{
            this.message="Incorrect data";
            return "error";
        }
    }

    /*Menu*/
    public String homeDefault() {
        return "success";
    }

    /*Current data*/
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
