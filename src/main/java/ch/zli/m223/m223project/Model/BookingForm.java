package ch.zli.m223.m223project.Model;

import java.util.Date;

public class BookingForm {

    private Date date;
    private String scope;
    private Boolean isApproved;
    private ApplicationUser applicationUser;
    private Space space;

    public BookingForm() {
    }

    public BookingForm(Date date, String scope, Boolean isApproBoolean, ApplicationUser applicationUser, Space space) {
        this.date = date;
        this.scope = scope;
        this.isApproved = isApproBoolean;
        this.applicationUser = applicationUser;
        this.space = space;
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

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }
}
