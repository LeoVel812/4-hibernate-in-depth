package com.jpa.hibernate.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    public String line1;
    public String line2;
    public String city;

    protected Address() {
    }

    public Address(String line1, String line2, String city) {
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("Student[line1=%s, line2=%s, city=%s]", line1, line2, city);
    }
}
