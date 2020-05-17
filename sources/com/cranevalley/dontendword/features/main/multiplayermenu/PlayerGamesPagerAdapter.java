package com.cranevalley.dontendword.features.main.multiplayermenu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.cranevalley.dontendword.features.main.multiplayermenu.currentgames.CurrentGamesFragment;
import com.cranevalley.dontendword.features.main.multiplayermenu.finishedgames.FinishedGamesFragment;

class PlayerGamesPagerAdapter extends FragmentStatePagerAdapter {
    private static final String CURRENT_GAMES_TITLE = "Current Games";
    private static final String FINISHED_GAMES_TITLE = "Finished Games";
    private CurrentGamesFragment mCurrentGamesFragment = CurrentGamesFragment.newInstance();
    private FinishedGamesFragment mFinishedGamesFragment = FinishedGamesFragment.newInstance();

    PlayerGamesPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public int getCount() {
        return 2;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return this.mCurrentGamesFragment;
            case 1:
                return this.mFinishedGamesFragment;
            default:
                return null;
        }
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return CURRENT_GAMES_TITLE;
            case 1:
                return FINISHED_GAMES_TITLE;
            default:
                return null;
        }
    }
}
