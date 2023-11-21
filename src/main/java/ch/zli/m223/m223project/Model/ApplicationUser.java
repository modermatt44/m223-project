package ch.zli.m223.m223project.Model;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ApplicationUser")
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String prename;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true, unique = true)
    private String username;

    @Column(nullable = true)
    private String roles;

    @OneToMany(mappedBy = "applicationUser")
    @JsonIgnoreProperties("applicationUser")
    private Set<Booking> bookings;

    public ApplicationUser() {
    }

    public ApplicationUser(String prename, String surname, String password, String username, String roles) {
        this.prename = prename;
        this.surname = surname;
        this.password = password;
        this.username = username;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "ApplicationUser [id=" + id + ", prename=" + prename + ", surname=" + surname + ", password=" + password
                + ", username=" + username + ", roles=" + roles + ", bookings=" + bookings + "]";
    }

}
