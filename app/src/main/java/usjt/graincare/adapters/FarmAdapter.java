package usjt.graincare.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import usjt.graincare.R;
import usjt.graincare.application.DrawerInteraction;
import usjt.graincare.application.MainActivity;
import usjt.graincare.fragments.SensorsFragment;
import usjt.graincare.fragments.SilosFragment;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.Farm;
import usjt.graincare.rest.GrainCareRestGenerator;
import usjt.graincare.util.GrainCareConfig;

public class FarmAdapter extends RecyclerView.Adapter<FarmAdapter.ViewHolderFarm> {

    private DrawerInteraction drawerInteraction;
    private List<Farm> farms = Collections.emptyList();
    private Context context;
    private Long farmId;

    public FarmAdapter(DrawerInteraction drawerInteraction, List<Farm> farms, Context context) {
        this.drawerInteraction = drawerInteraction;
        this.farms = farms;
        this.context = context;
    }

    @Override
    public FarmAdapter.ViewHolderFarm onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_farms_details, parent, false);
        ButterKnife.bind(this, view);
        return new FarmAdapter.ViewHolderFarm(view);
    }

    @Override
    public void onBindViewHolder(final FarmAdapter.ViewHolderFarm holderFarm, final int position) {

        holderFarm.icon.setImageResource(R.drawable.tractor_64x64);
        holderFarm.id.setText(farms.get(position).getId().toString());
        holderFarm.name.setText(farms.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return farms.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    class ViewHolderFarm extends RecyclerView.ViewHolder {
        @BindView(R.id.farmsCardViews)
        CardView cv;
        @BindView(R.id.iv_farm_icon)
        ImageView icon;
        @BindView(R.id.tv_farm_id)
        TextView id;
        @BindView(R.id.tv_farm_name)
        TextView name;

        ViewHolderFarm(View itemView) {

            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    farmId = Long.parseLong(id.getText().toString());
                    changeFragment(farmId);
                }
            });
            ButterKnife.bind(this, itemView);
        }
    }

    private void changeFragment(Long farmId) {
        SilosFragment fragment = new SilosFragment(drawerInteraction);
        Bundle args = new Bundle();
        args.putLong("farmId", farmId);
        fragment.setArguments(args);

        drawerInteraction.changeFragment(fragment, GrainCareConfig.FARM_TAG);
    }
}
