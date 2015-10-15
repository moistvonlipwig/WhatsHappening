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
import android.view.View;
import android.content.Intent;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Gamecast extends AppCompatActivity {

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
                //TODO:Invoke implicit email to invite friends
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void saveGameInformation()
    {
        // TODO : Setting to include home team names
        try {
            // TODO : Create GameCase Object
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
}
