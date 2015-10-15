package org.teliinc.whatshappening;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseUser;

//TODO : Create listview for on going events
//TODO : Creat SearchView to search for view
public class DashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
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


}
