/* important links
https://stackoverflow.com/questions/31367599/how-to-update-recyclerview-adapter-data
https://stackoverflow.com/questions/21974361/which-java-collection-should-i-use
https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android -> Bundle method (y)
https://developer.android.com/training/data-storage
https://developer.android.com/training/data-storage/room
https://www.androidauthority.com/how-to-store-data-locally-in-android-app-717190/
https://stackoverflow.com/questions/36095691/android-navigationdrawer-multiple-activities-same-menu

*/
package com.example.lifeassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class BaseDrawerActivity extends AppCompatActivity { // drawer and navigationView code here
    private Button goToNotesActivity;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.item1:
                        Toast.makeText(BaseDrawerActivity.this, "Hehe dziala", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item2:
                        break;//....

                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.start_screen_layout, contentFrameLayout);
        goToNotesActivity = (Button) findViewById(R.id.startToNotesButton);
        goToNotesActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),NoteListActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            finish();
        }
    }
}
