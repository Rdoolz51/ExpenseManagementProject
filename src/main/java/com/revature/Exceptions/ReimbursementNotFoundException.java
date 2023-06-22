package com.revature.Exceptions;

public class ReimbursementNotFoundException extends RuntimeException{

    public ReimbursementNotFoundException (String message){
        super(message);
    }

}
