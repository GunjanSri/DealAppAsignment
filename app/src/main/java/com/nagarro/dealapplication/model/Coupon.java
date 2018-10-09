package com.nagarro.dealapplication.model;

public class Coupon {
    private String name;
    private String description;
    private String icon;
    private String location;
    private String validity;
    private String code;

    public Coupon() {
    }

    public Coupon(String name, String description, String icon, String location, String validity, String code) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.location = location;
        this.validity = validity;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }
}
