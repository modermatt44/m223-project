package ch.zli.m223.m223project.Controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.m223project.Model.Booking;
import ch.zli.m223.m223project.Model.BookingForm;
import ch.zli.m223.m223project.Repository.BookingRepository;

@RestController
public class BookingController implements Serializable {

    private final BookingRepository bookingRepo;

    public BookingController(BookingRepository bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/bookings", name = "getAllBookings")
    public List<Booking> getBookings() {
        return bookingRepo.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(value = "/bookings/{id}", consumes = "application/json", name = "updateBooking")
    public String approveBooking(@PathVariable("id") Long id, Booking booking, @RequestBody Boolean approval) {
        Optional<Booking> bookingData = bookingRepo.findById(id);

        if (bookingData.isPresent()) {
            Booking _booking = bookingData.get();
            _booking.setIsApproved(approval);
            bookingRepo.save(_booking);
            return "Booking updated successfully!";
        } else {
            return "Booking not found!";
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/booking", consumes = "application/json", produces = "application/json", name = "createBooking")
    public Booking createBooking(@RequestBody BookingForm bookingForm, Booking booking) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String dateString = dateFormat.format(bookingForm.getDate());

        LocalDate localDate = LocalDate.parse(dateString);

        LocalDateTime bookingStartMorning = LocalDateTime.of(localDate,
                LocalTime.parse("08:00"));
        LocalDateTime bookingStartAfternoon = LocalDateTime.of(localDate,
                LocalTime.parse("13:00"));

        if (bookingForm.getScope().equals("morning")) {
            booking.setBookingStart(bookingStartMorning);
            booking.setBookingEnd(bookingStartMorning.plusHours(5));
        } else if (bookingForm.getScope().equals("afternoon")) {
            booking.setBookingStart(bookingStartAfternoon);
            booking.setBookingEnd(bookingStartAfternoon.plusHours(5));
        } else if (bookingForm.getScope().equals("full")) {
            booking.setBookingStart(bookingStartMorning);
            booking.setBookingEnd(bookingStartMorning.plusHours(10));
        }

        booking.setIsApproved(false);

        bookingRepo.save(booking);

        return booking;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/bookings/{id}", name = "deleteBookingById")
    public String deleteBookingById(@PathVariable("id") Long id) {
        bookingRepo.deleteById(id);
        return "Deleted booking with id: " + id;
    }
}
