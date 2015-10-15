package org.teliinc.whatshappening;

import java.util.Locale;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.net.NetworkInfo;

//TODO : Integrate ParseLoginUI
public class LoginActivity extends Activity implements OnClickListener{
    private TextView mEmailEditText;
    private TextView mPasswordEditText;
    private Button mCreateAccountButton;

    private String mEmail;
    private String mUsername;
    private String mPassword;
    private String mConfirmPassword;

    // flag for Internet connection status
    Boolean isInternetPresent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // creating connection detector class instance
        mEmailEditText = (TextView) findViewById(R.id.email);
        mPasswordEditText = (TextView) findViewById(R.id.password);

        mCreateAccountButton = (Button) findViewById(R.id.email_sign_in_button);
        mCreateAccountButton.setOnClickListener(this);

        mCreateAccountButton = (Button) findViewById(R.id.email_sign_up_button);
        mCreateAccountButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.email_sign_in_button:
                // get Internet status
                isInternetPresent = isNetworkAvailable();
                // check for Internet status
                if (isInternetPresent) {
                    // Internet Connection is Present
                    // make HTTP requests
                    attemptLogin();
                } else {
                    // Internet connection is not present
                    // Ask user to connect to Internet
                    showAlertDialog(LoginActivity.this, "No Internet Connection",
                            "You don't have internet connection.", false);
                }
                break;
            case R.id.email_sign_up_button:
                // get Internet status
                isInternetPresent = isNetworkAvailable();
                // check for Internet status
                if (isInternetPresent) {
                    // Internet Connection is Present
                    // make HTTP requests
                    createAccount();
                } else {
                    // Internet connection is not present
                    // Ask user to connect to Internet
                    showAlertDialog(LoginActivity.this, "No Internet Connection",
                            "You don't have internet connection.", false);
                }
                break;
            default:
                break;
        }
    }

    private void createAccount(){
        clearErrors();

        boolean cancel = false;
        View focusView = null;

        // Store values at the time of the login attempt.
        mEmail = mEmailEditText.getText().toString();
        mUsername = mEmailEditText.getText().toString();
        mPassword = mPasswordEditText.getText().toString();

        // Check for a valid password.
        if (TextUtils.isEmpty(mPassword)) {
            mPasswordEditText.setError(getString(R.string.error_field_required));
            focusView = mPasswordEditText;
            cancel = true;
        } else if (mPassword.length() < 4) {
            mPasswordEditText.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordEditText;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(mEmail)) {
            mEmailEditText.setError(getString(R.string.error_field_required));
            focusView = mEmailEditText;
            cancel = true;
        } else if (!mEmail.contains("@")) {
            mEmailEditText.setError(getString(R.string.error_invalid_email));
            focusView = mEmailEditText;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            Toast.makeText(getApplicationContext(), "signUp", Toast.LENGTH_SHORT).show();
            signUp(mUsername.toLowerCase(Locale.getDefault()), mEmail, mPassword);

        }

    }

    private void signUp(final String mUsername, String mEmail, String mPassword) {
        Toast.makeText(getApplicationContext(), mUsername + " - " + mEmail, Toast.LENGTH_SHORT).show();
        ParseUser user = new ParseUser();
        user.setUsername(mUsername);
        user.setPassword(mPassword);
        user.setEmail(mEmail);

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    signUpMsg("Account Created Successfully");
                    Intent in = new Intent(getApplicationContext(), DashBoard.class);
                    startActivity(in);
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    signUpMsg("Account already taken.");
                }
            }
        });
    }

    protected void signUpMsg(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void clearErrors(){
        mEmailEditText.setError(null);
        mPasswordEditText.setError(null);
    }

    @SuppressWarnings("deprecation")
    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting alert dialog icon
        if(status)
            alertDialog.setIcon(R.drawable.ic_action_fail);
        else
            alertDialog.setIcon(R.drawable.ic_action_ok);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void attemptLogin() {

        clearErrors();

        // Store values at the time of the login attempt.
        String username = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password.
        if (TextUtils.isEmpty(password)) {
            mPasswordEditText.setError(getString(R.string.error_field_required));
            focusView = mPasswordEditText;
            cancel = true;
        } else if (password.length() < 4) {
            mPasswordEditText.setError(getString(R.string.error_invalid_password));
            focusView =mPasswordEditText;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            mEmailEditText.setError(getString(R.string.error_field_required));
            focusView = mEmailEditText;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // perform the user login attempt.
            login(username.toLowerCase(Locale.getDefault()), password);
        }
    }

    private void login(String lowerCase, String password) {
        ParseUser.logInInBackground(lowerCase, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null)
                    loginSuccessful();
                else
                    loginUnSuccessful();
            }
        });

    }

    protected void loginSuccessful() {
        Intent in =  new Intent(getApplicationContext(),DashBoard.class);
        startActivity(in);
    }
    protected void loginUnSuccessful() {
        Toast.makeText(getApplicationContext(), "Login", Toast.LENGTH_SHORT).show();
        showAlertDialog(LoginActivity.this, "Login", "Username or Password is invalid.", false);
    }
}