package Entities;

import java.time.LocalDate;

public class Guest {
    private String TC;
    private String Name;
    private String Surname;
    private LocalDate CheckIn;
    private LocalDate CheckOut;

    public LocalDate getCheckIn() {
        return CheckIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        CheckIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return CheckOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        CheckOut = checkOut;
    }

    public String getTC() {
        return TC;
    }

    public void setTC(String tC) {
        TC = tC;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    @Override
    public String toString() {
        return """
                TC: %s
                Name: %s %s"""
                .formatted(getTC(), getName(), getSurname());
    }
}
