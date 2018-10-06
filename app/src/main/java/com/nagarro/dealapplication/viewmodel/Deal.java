package com.nagarro.dealapplication.viewmodel;

public class Deal {

    private String name;
    private String description;
    private String category;
    private String icon;
    private String location;
    private String validity;

    public Deal(String name , String description , String category , String icon , String location , String validity){
        this.name = name;
        this.description = description;
        this.category = category;
        this.icon = icon;
        this.location = location;
        this.validity = validity;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
