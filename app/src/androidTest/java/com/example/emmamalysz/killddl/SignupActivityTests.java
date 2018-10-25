package com.example.emmamalysz.killddl;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SignupActivityTests {
    //TESTS:
    //1. Check to see if email hasn't been taken yet
    //3. Check to see if all fields have been submitted

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

        //false because invalid area code
        assertFalse(SignupActivity.isPhoneNumberValid("5678729234"));
    }
    
}
