package ca.judacribz.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import ca.judacribz.navigationdrawer.actionbar_activities.CalculatorActivity;
import ca.judacribz.navigationdrawer.actionbar_activities.TakePhotoActivity;
import ca.judacribz.navigationdrawer.drawer_fragments.ArmstrongFragment;
import ca.judacribz.navigationdrawer.drawer_fragments.EMIFragment;
import ca.judacribz.navigationdrawer.drawer_fragments.PersonsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    private PersonsFragment perFrag = null;
    private EMIFragment emiFrag = null;
    private ArmstrongFragment armFrag = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer = findViewById(R.id.drawer_layout),
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_calculator:
                startActivity(new Intent(this, CalculatorActivity.class));
                break;
            case R.id.action_take_picture:
                startActivity(new Intent(this, TakePhotoActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flMainContainer, fragment)
                .addToBackStack("stack")
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_add_persons:
                goToFragment(perFrag = new PersonsFragment());
                break;
            case R.id.nav_emi_calculator:
                goToFragment(emiFrag = new EMIFragment());
                break;
            case R.id.nav_armstrong_num:
                goToFragment(armFrag = new ArmstrongFragment());
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void enterInfo(View view) {
        perFrag.enterInfo(view);
    }

    /* Persons Fragment */
    public void displayPersons(View view) {
        perFrag.displayPersons();
    }

    /* EMI Fragment */
    public void calculateEMI(View view) {
        emiFrag.calculateEMI(view);
    }


    /* Armstrong Fragment */
    public void checkArmstrong(View view) {
        armFrag.checkArmstrong();
    }
}
