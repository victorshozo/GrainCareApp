package usjt.graincare.application;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import usjt.graincare.R;
import usjt.graincare.adapters.NavigationAdapter;
import usjt.graincare.adapters.SiloAdapter;
import usjt.graincare.fragments.FarmFragment;
import usjt.graincare.fragments.SilosFragment;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.SiloHistory;
import usjt.graincare.rest.GrainCareRestGenerator;
import usjt.graincare.util.FontsOverride;
import usjt.graincare.util.GrainDialog;

public class MainActivity extends AppCompatActivity implements DrawerInteraction {

    private ActionBarDrawerToggle drawerToggle;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.rv_navigation)
    RecyclerView rvNavigation;
    @BindView(R.id.lt_drawer_content)
    LinearLayout lt_drawer_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Método que sobrepõe a fonte do sistema pela fonte customizada
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/museo-sans.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/museo-sans.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/museo-sans.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/museo-sans.ttf");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        // Set a Toolbar to replace the ActionBar.
        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        NavigationAdapter navigationAdapter = new NavigationAdapter(this);
        rvNavigation.setHasFixedSize(true);
        rvNavigation.setLayoutManager(new LinearLayoutManager(this));
        rvNavigation.setAdapter(navigationAdapter);
        rvNavigation.setBackgroundColor(getResources().getColor(R.color.white));


        FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
        fragTransaction.replace(R.id.frameLayout_content, new FarmFragment());
        fragTransaction.commit();
        if (!isNetworkStatusAvailable(getApplicationContext())) {
            GrainDialog.showDialog(getApplicationContext(), "Conexão", "Habilite a internet para poder usar o aplicativo.");
        }
    }

    @Override
    public void onBackPressed() {
        FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayout_content);
        if (fl.getChildCount() == 1) {
            super.onBackPressed();
            if (fl.getChildCount() == 0) {
                new AlertDialog.Builder(this)
                        .setTitle("Close App?")
                        .setMessage("Do you really want to close this beautiful app?")
                        .setPositiveButton("YES",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        finish();
                                    }
                                })
                        .setNegativeButton("NO",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                    }
                                }).show();
                // load your first Fragment here
            }
        } else if (fl.getChildCount() == 0) {

            FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
            fragTransaction.replace(R.id.frameLayout_content, new SilosFragment());
            fragTransaction.commit();
        } else {
            super.onBackPressed();
        }
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

    public static boolean isNetworkStatusAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if (netInfos != null)
                if (netInfos.isConnected())
                    return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout:
                new android.app.AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_question_green_64x64)
                        .setMessage("Deseja realmente sair?")
                        .setTitle("Logout")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                GrainCareApi api = GrainCareRestGenerator.create(GrainCareApi.class);
                                api.logout().enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        if (response.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                    }
                                });

                            }
                        })
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .show();
            return true;
            case R.id.help:
                GrainDialog.showDialog(getApplicationContext(), "Ajuda?", "Caso tenha encontrado alguma problema com nosso app. Por favor, contate-nos pelo graincareapp1@gmail.com e entraremos em contato o mais rápido possível. :)");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void changeFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout_content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        //toolbarTitle.setText(title);
        drawerLayout.closeDrawer(lt_drawer_content);
    }
}
