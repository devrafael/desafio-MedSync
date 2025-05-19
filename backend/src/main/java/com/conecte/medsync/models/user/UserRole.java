package com.conecte.medsync.models.user;

public enum UserRole {

    DOCTOR("doctor"),
    PATIENT("patient");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }


}
