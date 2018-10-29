package com.example.emmamalysz.killddl;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginActivityTests {

    @Test
    public void firebaseAuthenticatorTest() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        assertTrue(LoginActivity.getAuth().equals(mAuth));
    }
}
