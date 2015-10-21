package org.teliinc.whatshappening;

import android.os.Bundle;
import android.provider.Contacts;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.List;

public class Gamecast extends BaseClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamecast);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Invite Friends to your broadcast", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                // TODO : Invoke implicit email to invite friends
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // TODO : Create singleton Game Objecct
    }

    void saveGameInformation()
    {
        // TODO : Setting to include home team names
        try {
            // TODO : Create GameCast Object
            /*
                - Start Time
                - Unique ID
                - End Time
                - Game
             */
            ParseObject gameCast = new ParseObject("GameCast");
            gameCast.put("goal_scored_for", 0);
            gameCast.put("goal_scored_against", 0);
            gameCast.put("gamecase_start_time", 1337);
            gameCast.put("game_start_time", 1337);
            gameCast.put("game_end_time", 1337);
            gameCast.put("done", false);
            gameCast.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_gamecast, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case R.id.action_follow:
                onFollowClicked();
                return true;
            case R.id.action_signout:
                onSignoutClicked();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
