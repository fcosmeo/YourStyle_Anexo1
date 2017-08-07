package pe.com.yourstyleapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Frank on 05/08/2017.
 */
public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String dni;
    private Date birthDate;
    private String email;
    private String cellPhone;
    private Status status;
    private Date registrationDate;
    private Date modificationDate;
    private int sendMail;

    public Person(int id, String firstName, String lastName, String dni, Date birthDate, String email, String cellPhone, Status status, Date registrationDate, Date modificationDate, int sendMail) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.birthDate = birthDate;
        this.email = email;
        this.cellPhone=cellPhone;
        this.status = status;
        this.registrationDate = registrationDate;
        this.modificationDate = modificationDate;
        this.sendMail = sendMail;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public int getSendMail() {
        return sendMail;
    }

    public void setSendMail(int sendMail) {
        this.sendMail = sendMail;
    }

    public static Person build(ResultSet resultSet, StatusEntity statusEntity) {
        try {
            return new Person(  resultSet.getInt("id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                resultSet.getString("dni"),
                                resultSet.getDate("birth_date"),
                                resultSet.getString("email"),
                                resultSet.getString("cell_phone"),
                                statusEntity.findById(resultSet.getInt("status_id")),
                                resultSet.getDate("registration_date"),
                                resultSet.getDate("modification_date"),
                                resultSet.getInt("send_mail")
                            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
