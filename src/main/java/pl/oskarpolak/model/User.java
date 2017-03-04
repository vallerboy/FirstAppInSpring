package pl.oskarpolak.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 * Created by OskarPraca on 2017-02-28.
 */
@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class User {

    @XmlAttribute
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @XmlElement
    private String username;
    @XmlElement
    private String password;
    @XmlElement
    private String role;


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
