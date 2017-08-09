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
        Person personaAuxiliary1 = new Person();
        if(person.getFirstName()==null || person.getFirstName().equals("")) {
            this.message = "El Nombre es requerido.";
            return "error";
        }
        if(person.getLastName()==null || person.getLastName().equals("")) {
            this.message = "El apellido es requerido.";
            return "error";
        }
        if(person.getDni()==null || person.getDni().equals("")) {
            this.message = "El número de DNI es requerido.";
            return "error";
        }
        if(person.getBirthDate()==null || String.valueOf(person.getBirthDate()).equals("")) {
            this.message = "La fecha de nacimiento es requerido.";
            return "error";
        }
        if(person.getEmail()==null || person.getEmail().equals("")) {
            this.message = "El correo es requerido.";
            return "error";
        }

        personaAuxiliary1=service.findPersonByEmail(person.getEmail());

        if(personaAuxiliary1 != null){
            this.message="El correo ya se encuentra registrado";
            return "error";
        }

        personaAuxiliary=service.addPerson(person);
        if( personaAuxiliary.getId() > 0 ){
            person=null;
            person=new Person();
            this.message="Registro correcto.";
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
