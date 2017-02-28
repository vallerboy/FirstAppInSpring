package pl.oskarpolak.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;

/**
 * Created by OskarPraca on 2017-02-18.
 */
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.NONE)
public class Email {


    @XmlAttribute
    private int id;


    @XmlElement
    @Size(min = 4, max = 50, message = "Email musi być od {min} do {max}")
    @NotNull(message = "Email nie może być pusty")
  //  @Pattern(regexp = "asdasdasdas", message = "Hasła się nie zgadzają")
    private String email;

    @XmlElement
    @NotNull(message = "Wiadomość nie może być pusta")
    @Size(min = 1, message = "Wiadomość musi być dłuższa!")
    private String message;

    public Email() { }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(int id) {
        this.id = id;
    }
}
