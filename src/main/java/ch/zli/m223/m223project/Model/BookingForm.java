package ch.zli.m223.m223project.Model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public class BookingForm {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String scope;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

}
