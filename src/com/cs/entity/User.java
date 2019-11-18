package com.cs.entity;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String sex;
    private String hobbies;
    private String phone;
    private String email;
    private String address;

    public User() {
    }

    public User(Integer id, String username, String password, String sex, String hobbies, String phone, String email, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.hobbies = hobbies;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public User(String username, String password, String sex, String hobbies, String phone, String email, String address) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.hobbies = hobbies;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", hobbies='" + hobbies + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
