package com.revature.models.DTOs;

public class OutgoingUserDTO {

    private int user_Id;
    private String username;
    private String role;

    public OutgoingUserDTO() {
    }

    public OutgoingUserDTO(int user_Id, String username, String role) {
        this.user_Id = user_Id;
        this.username = username;
        this.role = role;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "OutgoingUserDTO{" +
                "user_Id=" + user_Id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
