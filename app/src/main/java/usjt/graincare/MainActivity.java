package usjt.graincare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.test.ViewAsserts;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_new_silo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add Silo, Alter Silo, Delete Silo", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerListSilos);
        CardView silosCardView = (CardView) findViewById(R.id.silosCardView);

        List<Silo> silos = new ArrayList<>();
        ArrayList<Grao> graos = new ArrayList<>();
        graos.add(new Grao(1, "Milho", 30));
        graos.add(new Grao(2, "Soja", 43));
        graos.add(new Grao(3, "Sordo", 56));

        silos.add(new Silo(1,1, 1000.10 ,"10/03/2016", "20/06/2016", "Noroeste"));
        silos.add(new Silo(2,1, 10000.0 ,"10/02/2016", "20/05/2016", "Agreste"));
        silos.add(new Silo(3,3, 100000.0 ,"10/01/2016", "20/04/2016", "Inferno"));
        silos.add(new Silo(4,2, 15000.0 ,"10/04/2016", "20/07/2016", "Satan"));

        SiloAdapter siloAdapter = new SiloAdapter(silos, graos, getApplication());
        recyclerView.setAdapter(siloAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //View view=getCurrentFocus() ;
        //int id = item.getItemId();

        // else if (id == R.id.nav_grafic_temp_time)
        //{
        //    Snackbar.make(item.getActionView(),"TELA GRAFICO", Snackbar.LENGTH_LONG)
        //            .setAction("Action", null).show();
        //} else if (id == R.id.nav_prevision)
        //{
        //    Snackbar.make(item.getActionView(), "TELA PREVISÃO", Snackbar.LENGTH_LONG)
        //            .setAction("Action", null).show();
        //}
        //} else if (id == R.id.nav_share) {

        //} else if (id == R.id.nav_send) {

        //}

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
