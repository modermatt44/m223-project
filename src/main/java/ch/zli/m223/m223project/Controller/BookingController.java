package ch.zli.m223.m223project.Controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import ch.zli.m223.m223project.Model.Booking;
import ch.zli.m223.m223project.Model.BookingForm;
import ch.zli.m223.m223project.Repository.BookingRepository;

@Controller
public class BookingController implements Serializable {

    private final BookingRepository bookingRepo;

    public BookingController(BookingRepository bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    @PreAuthorize("permitAll()")
    @GetMapping(value = "/booking")
    public String getBooking(@ModelAttribute("bookingForm") BookingForm bookingForm) {
        return "booking";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/booking")
    public String postBooking(@ModelAttribute("booking") Booking booking,
            @ModelAttribute("bookingForm") BookingForm bookingForm) {

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

        bookingRepo.save(booking);

        return "redirect:/booking";
    }
}
