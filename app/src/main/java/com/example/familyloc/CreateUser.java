package com.example.familyloc;

public class CreateUser {

    public CreateUser(){}
    public String name;
    public String email;

    public CreateUser(String name, String email, String password, String code, String isSharing, String lat, String lon, String imageUrl) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.code = code;
        this.isSharing = isSharing;
        this.lat = lat;
        this.lon = lon;
        this.imageUrl = imageUrl;
    }

    public String password;
    public String code;
    public String isSharing;
    public String lat;
    public String lon;
    public String imageUrl;

}
