package ch.zli.m223.m223project.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookingController {

    @GetMapping(value = "/booking")
    public String getBooking() {
        return "booking";
    }

    @PostMapping(value = "/booking")
    public String postBooking() {
        return new String();
    }
}
