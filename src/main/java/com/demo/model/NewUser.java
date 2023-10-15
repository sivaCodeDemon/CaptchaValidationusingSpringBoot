package com.demo.model;




import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jakarta.persistence.Transient;
import lombok.Data;

@Data
public class NewUser {



    @NotEmpty(message = "Email should not be empty.")
    @Email(message = "email should be valid", regexp = "^[a-zA-z0-9_\\-\\.]+[@][a-z]+[\\.][a-z]{2,5}$")
    @NotNull(message = "email may not be empty")
    private String email;

    @NotNull(message = "Password must be less than 20 and more than 8 characters in length.\r\n"
	    + "Password must have atleast one uppercase character\r\n"
	    + "Password must have atleast one lowercase character\r\n" + "Password must have atleast one number\r\n"
	    + "Password must have atleast one special character among @#$%")
    @Size(min = 8, message = "password must be 8 characters")
    @NotNull(message = "password must be valid")
    private String password;


    public void setPassword(String password) {

	String str = "paSs9wo)rd";
	Boolean haveCap = false, haveSmall = false, haveSpeChar = false, haveNum = false;

	for (int i = 0; i < str.length(); i++) {
	    char ch = str.charAt(i);
	    if (ch >= 'A' && ch <= 'Z') {
		haveCap = true;
	    } else if (ch >= 'a' && ch <= 'z') {
		haveSmall = true;
	    } else if (ch >= '0' && ch <= '9') {
		haveNum = true;
	    } else {
		haveSpeChar = true;
	    }
	}

	if (haveCap && haveSmall && haveSpeChar && haveNum) {
	    System.out.println("password is very strength");
	    this.password = password;
	} else {
	    System.out.println("weak password");
	    this.password = null;
	}

    }
	
    @Transient
    private String captcha; //value entered by user
    
    @Transient
    private String hidden; 
    
    @Transient
    private String image;
	
}
