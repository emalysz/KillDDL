package com.example.emmamalysz.killddl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.KillDDLController;
import Model.User;

public class SignupActivity extends LoginActivity{

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mNameView;
    private EditText mPhonenumberView;
    private FirebaseAuth mAuth;
    private KillDDLController killDDl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("myTag", "In sign up method");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = super.getAuth();

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mNameView = (EditText) findViewById(R.id.name);
        mPhonenumberView = (EditText) findViewById(R.id.phoneNumber);

        Button mSignUp = (Button) findViewById(R.id.button);
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignUp();
            }
        });
    }

    private void attemptSignUp() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mPhonenumberView.setError(null);
        mNameView.setError(null);

        // Store values at the time of the login attempt.
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();
        final String name = mNameView.getText().toString();
        final String phoneNumber = mPhonenumberView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            Log.d("myTag", "Password bad");
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email, 0)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (TextUtils.isEmpty(name)) {
            mNameView.setError(getString(R.string.error_field_required));
            focusView = mNameView;
            cancel = true;
        }

        if (TextUtils.isEmpty(phoneNumber)) {
            mPhonenumberView.setError(getString(R.string.error_field_required));
            focusView = mPhonenumberView;
            cancel = true;
        } else if (!isPhoneNumberValid(phoneNumber)) {
            mPhonenumberView.setError("This phone number is invalid");
            focusView = mPhonenumberView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            Log.d("myTag", "error");
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            Log.d("checker", "else");
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("checker", "uh");
                            if (task.isSuccessful()) {
                                Log.d("checker", "successful??");
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                User user2 = new User(name, email, password, Long.parseLong(phoneNumber));
                                Intent intent = new Intent(SignupActivity.this, CalendarActivity.class);
                                startActivity(intent);
//                                User currentUser = new User(name, email, password, Integer.parseInt(phoneNumber));
//                                killDDl.setCurrentUser(currentUser);

                            } else {
                                Log.d("checker", "other??");
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignupActivity.this, "Authentication failed. Username is already taken.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    }

    public static boolean isEmailValid(String email, int num) {
        Log.d("checker", "hello" + email);
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber.length() == 10 || phoneNumber.length() == 11) {
            return true;
        } else {
            return false;
        }
    }

}
