package org.teliinc.whatshappening;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity
{

    private Button new_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Parse.initialize(this, getString(R.string.parse_app_id),
                getString(R.string.parse_client_key));
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // A objects private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);


        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

        Log.d("Parse Debugging", "On Created creation");
        if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
            // If user is anonymous, send the user to LoginSignupActivity.class
            Intent intent = new Intent(MainActivity.this,
                    LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            // If current user is NOT anonymous user
            // Get current user data from Parse.com
            ParseUser currentUser = ParseUser.getCurrentUser();
            if (currentUser != null) {
                // Send logged in users to DashBoard.class
                Intent intent = new Intent(MainActivity.this, DashBoard.class);
                startActivity(intent);
                finish();
            } else {
                // Send user to LoginSignupActivity.class
                Intent intent = new Intent(MainActivity.this,
                        LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
