package edu.roi.playbox.domain;


import javax.persistence.*;
import java.util.Date;


/**
 * @author apavelchuk
 * @since 06.07.2015.
 */
@Entity
@Table(name = "User", uniqueConstraints={@UniqueConstraint(columnNames={"firstName", "lastName"})})
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;
    @Column(name = "lastName", nullable = false, length = 50)
    private String lastName;
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "githubLogin", length = 20)
    private String githubLogin;
    @Column(name = "skypeLogin", length = 20)
    private String skypeLogin;
    @Column(name = "description", length = 4096)
    private String description;
    @Column(name = "creationDate")
    private Date creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithubLogin() {
        return githubLogin;
    }

    public void setGithubLogin(String githubLogin) {
        this.githubLogin = githubLogin;
    }

    public String getSkypeLogin() {
        return skypeLogin;
    }

    public void setSkypeLogin(String skypeLogin) {
        this.skypeLogin = skypeLogin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
