package ch.zli.m223.m223project.Model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private ApplicationUser applicationUser;

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

    public Object getUser() {
        return applicationUser;
    }

    public void setUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }
}
