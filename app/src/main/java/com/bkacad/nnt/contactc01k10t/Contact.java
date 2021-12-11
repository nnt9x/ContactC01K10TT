package com.bkacad.nnt.contactc01k10t;

import java.io.Serializable;

public class Contact implements Serializable {
    private int id;
    private String name;
    private String phone;
    private String urlAvatar;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Contact(int id, String name, String phone, String urlAvatar) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.urlAvatar = urlAvatar;
    }

    public Contact() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", urlAvatar='" + urlAvatar + '\'' +
                '}';
    }
}
