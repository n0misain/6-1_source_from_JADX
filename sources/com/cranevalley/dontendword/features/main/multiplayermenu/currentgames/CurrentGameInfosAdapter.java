package com.cranevalley.dontendword.features.main.multiplayermenu.currentgames;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.features.shared.GameInfo;
import java.util.List;

class CurrentGameInfosAdapter extends SectionedRecyclerViewAdapter<ViewHolder> implements CurrentGameInfoViewHolderListener {
    private static final int OPPONENT_TURN_GAME_TYPE = 2;
    private static final int PLAYER_TURN_GAME_TYPE = 1;
    CurrentGameInfosAdapterListener adapterListener;
    private List<GameInfo> mOpponentTurnGameInfosList;
    private List<GameInfo> mPlayerTurnGameInfosList;

    CurrentGameInfosAdapter(List<GameInfo> playerTurnGameInfosList, List<GameInfo> opponentTurnGameInfosList) {
        shouldShowHeadersForEmptySections(true);
        this.mPlayerTurnGameInfosList = playerTurnGameInfosList;
        this.mOpponentTurnGameInfosList = opponentTurnGameInfosList;
    }

    public int getSectionCount() {
        return 2;
    }

    public int getItemCount(int section) {
        if (section == 0) {
            return this.mPlayerTurnGameInfosList.size();
        }
        if (section == 1) {
            return this.mOpponentTurnGameInfosList.size();
        }
        return 0;
    }

    public int getItemViewType(int section, int relativePosition, int absolutePosition) {
        if (section == 0) {
            return 1;
        }
        if (section == 1) {
            return 2;
        }
        return super.getItemViewType(section, relativePosition, absolutePosition);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == -2) {
            return new CurrentGamesHeaderViewHolder(layoutInflater.inflate(C0521R.layout.current_games_header_view_holder, parent, false));
        }
        if (viewType == 1) {
            return new CurrentGameInfoViewHolder(layoutInflater.inflate(C0521R.layout.player_turn_game_info_view_holder, parent, false));
        }
        if (viewType == 2) {
            return new CurrentGameInfoViewHolder(layoutInflater.inflate(C0521R.layout.opponent_turn_game_info_view_holder, parent, false));
        }
        return null;
    }

    public void onBindHeaderViewHolder(ViewHolder holder, int section) {
        if (holder instanceof CurrentGamesHeaderViewHolder) {
            CurrentGamesHeaderViewHolder currentGamesHeaderViewHolder = (CurrentGamesHeaderViewHolder) holder;
            if (section == 0) {
                currentGamesHeaderViewHolder.setTitle("Your Turn");
            } else if (section == 1) {
                currentGamesHeaderViewHolder.setTitle("Their Turn");
            }
        }
    }

    public void onBindViewHolder(ViewHolder holder, int section, int relativePosition, int absolutePosition) {
        if (holder instanceof CurrentGameInfoViewHolder) {
            GameInfo gameInfo = null;
            if (section == 0) {
                gameInfo = (GameInfo) this.mPlayerTurnGameInfosList.get(relativePosition);
            } else if (section == 1) {
                gameInfo = (GameInfo) this.mOpponentTurnGameInfosList.get(relativePosition);
            }
            CurrentGameInfoViewHolder currentGameInfoViewHolder = (CurrentGameInfoViewHolder) holder;
            currentGameInfoViewHolder.setSection(section);
            currentGameInfoViewHolder.setRelativeIndex(relativePosition);
            currentGameInfoViewHolder.bindGameInfo(gameInfo);
            currentGameInfoViewHolder.viewHolderListener = this;
        }
    }

    public void onCurrentGameInfoViewHolderClickItem(int section, int relativeIndex) {
        GameInfo gameInfo = null;
        if (section == 0) {
            gameInfo = (GameInfo) this.mPlayerTurnGameInfosList.get(relativeIndex);
        } else if (section == 1) {
            gameInfo = (GameInfo) this.mOpponentTurnGameInfosList.get(relativeIndex);
        }
        if (this.adapterListener != null) {
            this.adapterListener.onCurrentGameInfosAdapterSelectGame(gameInfo);
        }
    }

    void setPlayerTurnGameInfosList(List<GameInfo> playerTurnGameInfosList) {
        this.mPlayerTurnGameInfosList = playerTurnGameInfosList;
    }

    void setOpponentTurnGameInfosList(List<GameInfo> opponentTurnGameInfosList) {
        this.mOpponentTurnGameInfosList = opponentTurnGameInfosList;
    }
}
