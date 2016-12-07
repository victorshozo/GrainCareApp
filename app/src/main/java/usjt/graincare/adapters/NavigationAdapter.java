package usjt.graincare.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import usjt.graincare.R;
import usjt.graincare.application.DrawerInteraction;
import usjt.graincare.application.GrainCareTextView;
import usjt.graincare.application.MainActivity;
import usjt.graincare.navigation.NavigationItem;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.NavigationHolder>{

    private Context context;
    private NavigationItem[] items = NavigationItem.values();
    private DrawerInteraction drawerInteraction;

    public NavigationAdapter(MainActivity mainActivity) {
        this.context = mainActivity;
        this.drawerInteraction = mainActivity;
    }

    @Override
    public NavigationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NavigationHolder(LayoutInflater.from(context).inflate(R.layout.navigation_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NavigationHolder holder, final int position) {
        holder.tvItemName.setText(items[position].getValue());
        holder.ivNavigationIcon.setImageDrawable(context.getResources().getDrawable(items[position].getIcon()));
        holder.ltNavigationItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = items[position].getFragment(drawerInteraction);
                drawerInteraction.changeFragment(newFragment, newFragment.getTag());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    class NavigationHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.lt_navigation_item)
        LinearLayout ltNavigationItem;
        @BindView(R.id.iv_navigation_icon)
        ImageView ivNavigationIcon;
        @BindView(R.id.tv_navigation_item)
        GrainCareTextView tvItemName;

        NavigationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
}
