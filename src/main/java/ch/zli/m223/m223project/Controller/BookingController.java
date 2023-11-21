package ch.zli.m223.m223project.Controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    @ResponseStatus(code = HttpStatus.FOUND, reason = "Bookings found!")
    public List<Booking> getBookings() {
        try{
            return bookingRepo.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Bookings found!");
        }
        
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping(value = "/bookings/{id}", consumes = "application/json", name = "updateBookingById")
    @ResponseStatus(code = HttpStatus.OK, reason = "Booking updated successfully!")
    public String approveBooking(@PathVariable("id") Long id, @RequestBody BookingForm bookingForm) {

        Optional<Booking> booking = bookingRepo.findById(id);

        if (booking.isPresent()) {
            convertDate(bookingForm, booking.get());
            booking.get().setIsApproved(bookingForm.getIsApproved());
            booking.get().setApplicationUser(bookingForm.getApplicationUser());
            booking.get().setSpace(bookingForm.getSpace());
            bookingRepo.save(booking.get());
            
            return "Booking updated successfully!";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found!");
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/booking", consumes = "application/json", produces = "application/json", name = "createBooking")
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Booking created successfully!")
    public Booking createBooking(@RequestBody BookingForm bookingForm) {
        try{
            Booking booking = new Booking();

            convertDate(bookingForm, booking);

            booking.setIsApproved(false);
            booking.setApplicationUser(bookingForm.getApplicationUser());
            booking.setSpace(bookingForm.getSpace());

            bookingRepo.save(booking);

            return booking;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "EXPECTATION FAILED(CODE 417)\n");
        }
        
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/bookings/{id}", name = "deleteBookingById")
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Booking deleted successfully!")
    public String deleteBookingById(@PathVariable("id") Long id) {

        Optional<Booking> booking = bookingRepo.findById(id);

        if(booking.isPresent()){
            bookingRepo.deleteById(id);
            return "Deleted booking with id: " + id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found!");
    }


    public void convertDate(BookingForm bookingForm, Booking booking){
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
    }
}
