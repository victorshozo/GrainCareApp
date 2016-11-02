package usjt.graincare.navigation;

import android.support.v4.app.Fragment;

import usjt.graincare.R;
import usjt.graincare.application.DrawerInteraction;
import usjt.graincare.fragments.ReportFragment;
import usjt.graincare.fragments.SiloCloseFragment;
import usjt.graincare.fragments.SilosFragment;

public enum NavigationItem {

    HOME("Inicio", R.mipmap.ic_silo) {
        @Override
        public Fragment getFragment(DrawerInteraction drawerInteraction) {
            return new SilosFragment();
        }
    },
    CLOSE_SILO("Fechar silo", R.mipmap.ic_add_new_silo) {
        @Override
        public Fragment getFragment(DrawerInteraction drawerInteraction) {
            return new SiloCloseFragment(drawerInteraction);
        }
    },
    REPORT("Relatorio", R.mipmap.ic_beacon_final) {
        @Override
        public Fragment getFragment(DrawerInteraction drawerInteraction) {
            return new ReportFragment();
        }
    },
    ;

    private String value;
    private int icon;

    NavigationItem(String value, int icon) {
        this.value = value;
        this.icon = icon;
    }

    public String getValue() {
        return value;
    }

    public int getIcon() {
        return icon;
    }

    public abstract Fragment getFragment(DrawerInteraction drawerInteraction);
}
