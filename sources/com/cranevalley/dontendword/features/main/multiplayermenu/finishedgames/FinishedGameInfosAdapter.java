package com.cranevalley.dontendword.features.main.multiplayermenu.finishedgames;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.features.shared.GameInfo;
import java.util.List;

class FinishedGameInfosAdapter extends Adapter<FinishedGameInfoViewHolder> implements FinishedGameInfoViewHolderListener {
    FinishedGameInfosAdapterListener adapterListener;
    private List<GameInfo> mFinishedGameInfosList;

    FinishedGameInfosAdapter(List<GameInfo> finishedGameInfosList) {
        this.mFinishedGameInfosList = finishedGameInfosList;
    }

    public int getItemCount() {
        return this.mFinishedGameInfosList.size();
    }

    public FinishedGameInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FinishedGameInfoViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0521R.layout.finished_game_info_view_holder, parent, false));
    }

    public void onBindViewHolder(FinishedGameInfoViewHolder holder, int position) {
        holder.bindFinishedGameInfo((GameInfo) this.mFinishedGameInfosList.get(position));
        holder.viewHolderListener = this;
    }

    public void onFinishedGameInfoViewHolderClickItem(int index) {
        GameInfo gameInfo = (GameInfo) this.mFinishedGameInfosList.get(index);
        if (this.adapterListener != null) {
            this.adapterListener.onFinishedGameInfosAdapterSelectGame(gameInfo);
        }
    }

    void setFinishedGameInfosList(List<GameInfo> finishedGameInfosList) {
        this.mFinishedGameInfosList = finishedGameInfosList;
    }
}
