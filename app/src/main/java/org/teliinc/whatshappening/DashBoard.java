package org.teliinc.whatshappening;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseUser;

// TODO : Create listview for on going events
// TODO : Create SearchView to search for view
public class DashBoard extends BaseClass {

    // TODO : Create call to Parse Server to check games in progress
    String[] gamecasts = new String [ ] {"Legends Girls U10",
            "Legends Girls U13",
            "Todo's Birthday",
            "Concert of the doggies",
            "Little piggies dancing",
            "Babies on paradae",
            "Designed showes walking in circles"
    };
    ArrayAdapter<String> gamecastAdapter;
    ListView gamecast_list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        gamecastAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, gamecasts);
        gamecast_list_view = (ListView)findViewById(R.id.gamecastListView);
        gamecast_list_view.setAdapter(gamecastAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
