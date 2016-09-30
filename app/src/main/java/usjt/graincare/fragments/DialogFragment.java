package usjt.graincare.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import usjt.graincare.R;
import usjt.graincare.adapters.SiloAdapter;
import usjt.graincare.models.Grao;
import usjt.graincare.models.Silo;
import usjt.graincare.util.PrevisionDialog;

/**
 * Created by shozo on 29/09/16.
 */
public class DialogFragment  {
    // @BindView (R.id.RecyclerListSilos) RecyclerView recyclerView;

    private View rootView;
    private SiloAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog alert;
        alert.show();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_silos, container, false);
        return rootView;
    }
}