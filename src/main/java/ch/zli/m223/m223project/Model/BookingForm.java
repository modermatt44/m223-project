package ch.zli.m223.m223project.Model;

import java.sql.Date;
import java.time.LocalDate;

public class BookingForm {

    private String date;
    private String scope;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

}
