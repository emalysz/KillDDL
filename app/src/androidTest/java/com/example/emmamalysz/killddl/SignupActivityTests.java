package com.example.emmamalysz.killddl;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SignupActivityTests {

    @Test
    public void emailValidator_isEmailValidTest() {

        assertTrue(SignupActivity.isEmailValid("emmamalysz@gmail.com", 0));

        //invalid because of two ..
        assertFalse(SignupActivity.isEmailValid("emmamalysz@gmail..com", 0));

        //invalid bedcause no @ symbol
        assertFalse(SignupActivity.isEmailValid("emmamalyszgmail.com", 0));
    }

    @Test
    public void phoneNumberValidator_isPhoneNumberValidTest() {

        assertTrue(SignupActivity.isPhoneNumberValid("4087812704"));
        assertTrue(SignupActivity.isPhoneNumberValid("7873923408"));

        //false because too many digits
        assertFalse(SignupActivity.isPhoneNumberValid("102394810234812"));

        //false because too little digits
        assertFalse(SignupActivity.isPhoneNumberValid("10"));
    }
    
}
