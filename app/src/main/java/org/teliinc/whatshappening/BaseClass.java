package org.teliinc.whatshappening;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.ParseUser;

/**
 * Created by Chris Teli on 10/21/2015.
 */
public class BaseClass extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case R.id.action_gamecast:
                onGamecastClicked();
                return true;
            case R.id.action_follow:
                onFollowClicked();
                return true;
            case R.id.action_signout:
                onSignoutClicked();
                return true;
            case R.id.action_about:
                onAboutClicked();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // [START onGamecastClicked]
    private void onGamecastClicked() {
        Intent in = new Intent(getApplicationContext(), Gamecast.class);
        startActivity(in);
    }
    // [END onGamecastClicked]

    // [START onFollowClicked]
    private void onFollowClicked() {
        Intent in = new Intent(getApplicationContext(), Follow.class);
        startActivity(in);
    }
    // [END onFollowClicked]

    // [START onSignoutClicked]
    private void onSignoutClicked() {
        ParseUser.logOut();
        Toast.makeText(getApplicationContext(), "Disconnected...", Toast.LENGTH_LONG).show();

        Intent in = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(in);

    }
    // [END onSignoutClicked]

    // [Start onAboutClicked]
    private void onAboutClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.about_title);
        builder.setMessage(R.string.about_message);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.about_ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface,int i) {
                        // Nothing
                    }
                }
        );
        AlertDialog aboutDialog = builder.create();
        aboutDialog.show();

    }
}
