package ch.zli.m223.m223project.Model;

import java.util.Date;

public class BookingForm {

    private Date date;
    private String scope;

    public BookingForm() {
    }

    public BookingForm(Date date, String scope) {
        this.date = date;
        this.scope = scope;
    }

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
