package com.example.dreeki.projectleerlingenapp.Models;

import com.example.dreeki.projectleerlingenapp.Interfaces.LockType;

/**
 * Created by dreeki on 30/10/17.
 */

public class Lock {
    private String code;
    private LockType lockType;

    public Lock(LockType lockType){
        this.lockType = lockType;
    }

    public void setCode(String code){
        this.code = code;
    }
}
