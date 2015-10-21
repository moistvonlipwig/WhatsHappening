package org.teliinc.whatshappening;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class Follow extends BaseClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getData()
    {
        try {

            ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> userList, ParseException e) {
                    if (e == null) {
                        if (userList.size() > 0) {
                            String playerName;
                            int score;

                            for (int i = 0; i < userList.size(); i++) {
                                ParseObject p = userList.get(i);
                                playerName = p.getString("playerName");
                                score = p.getInt("score");
                                Log.d(playerName, Integer.toString(score));
                            }
                        }
                    } else {
                        Log.d("score", "Error: " + e.getMessage());
                        // Alert.alertOneBtn(getActivity(),"Something went wrong!");
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_follow, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case R.id.action_gamecast:
                onGamecastClicked();
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

    // [START onSignoutClicked]
    private void onSignoutClicked() {
        ParseUser.logOut();
        Toast.makeText(getApplicationContext(), "Disconnected...", Toast.LENGTH_LONG).show();

        Intent in = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(in);

    }
}
