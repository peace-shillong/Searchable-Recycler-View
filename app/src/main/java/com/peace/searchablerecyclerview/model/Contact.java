package com.peace.searchablerecyclerview.model;

public class Contact {
    String name;
    String phone;

    public Contact(String name,String phone) {
        this.name=name;
        this.phone=phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
