package usjt.graincare.application;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import usjt.graincare.R;
import usjt.graincare.fragments.SiloAddFragment;
import usjt.graincare.fragments.SilosFragment;
import usjt.graincare.util.GrainDialog;

public class MainActivity extends AppCompatActivity {
    //@BindView(R.id.) FloatingActionButton floatButtonSilo;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private GrainDialog pDialog;
    private ImageView ivArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

       /* floatButtonSilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add Silo, Alter Silo, Delete Silo", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set a Toolbar to replace the ActionBar.
        setSupportActionBar(toolbar);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);

        // Find our drawer view
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nav_view);

        // Setup drawer view
        setupDrawerContent(nvDrawer);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.frameLayout_content, new SilosFragment());
        tx.commit();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        boolean isFrag = false;
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.silo_item:
                //fragmentClass = GraphFragment.class;
                fragment = new SilosFragment();
                isFrag = true;
                break;
            case R.id.nav_grafic_temp_time:
                fragment = new SiloAddFragment();
                isFrag = true;
                break;
            case R.id.nav_prevision:
                //String message = getPrediction(); Fazer chamada web e pegar data de previsão
                break;
            case R.id.silo_capacity:
                //String message = getPrediction(); Fazer chamada web e pegar data de previsão
                 break;
            default:
                isFrag = false;
                break;
        }

        if (isFrag) {
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frameLayout_content, fragment).commit();

            // Highlight the selected item has been done by NavigationView
            menuItem.setChecked(true);
            // Set action bar title
        }

        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    //Caso o drawer esteja aberto, fecha o drawer antes de sair da aplicação.
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public void switchContent(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout_content, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
