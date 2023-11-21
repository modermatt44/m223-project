package ch.zli.m223.m223project.Model;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime bookingStart;

    @Column(nullable = false)
    private LocalDateTime bookingEnd;

    @Column(nullable = false)
    private Boolean isApproved;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JsonIgnoreProperties("bookings")
    private ApplicationUser applicationUser;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JsonIgnoreProperties("bookings")
    private Space space;

    public Booking() {
    }

    public Booking(LocalDateTime bookingStart, LocalDateTime bookingEnd, Boolean isApproved,
            ApplicationUser applicationUser, Space space) {
        this.bookingStart = bookingStart;
        this.bookingEnd = bookingEnd;
        this.isApproved = isApproved;
        this.applicationUser = applicationUser;
        this.space = space;
    } 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getBookingStart() {
        return bookingStart;
    }

    public void setBookingStart(LocalDateTime bookingStart) {
        this.bookingStart = bookingStart;
    }

    public LocalDateTime getBookingEnd() {
        return bookingEnd;
    }

    public void setBookingEnd(LocalDateTime bookingEnd) {
        this.bookingEnd = bookingEnd;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    

}
