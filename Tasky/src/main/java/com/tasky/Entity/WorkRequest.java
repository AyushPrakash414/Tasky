package com.tasky.Entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkRequest {
    private String userName;
    private String work;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}

