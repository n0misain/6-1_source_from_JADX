package com.cranevalley.dontendword.features.main.chat;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.cranevalley.dontendword.C0521R;
import java.util.List;

class MessageInfosAdapter extends Adapter<ViewHolder> {
    private static final int OPPONENT_MESSAGE_TYPE = 2;
    private static final int PLAYER_MESSAGE_TYPE = 1;
    private List<MessageInfo> mMessageInfosList;
    private String mOpponentPhotoUrl;

    MessageInfosAdapter(String opponentPhotoUrl, List<MessageInfo> messageInfosList) {
        this.mOpponentPhotoUrl = opponentPhotoUrl;
        this.mMessageInfosList = messageInfosList;
    }

    public int getItemCount() {
        return this.mMessageInfosList.size();
    }

    public int getItemViewType(int position) {
        MessageInfo messageInfo = (MessageInfo) this.mMessageInfosList.get(position);
        if (messageInfo.receiver == null || !messageInfo.receiver.booleanValue()) {
            return 1;
        }
        return 2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == 1) {
            return new PlayerMessageViewHolder(inflater.inflate(C0521R.layout.player_message_info_view_holder, parent, false));
        }
        if (viewType == 2) {
            return new OpponentMessageViewHolder(inflater.inflate(C0521R.layout.opponent_message_info_view_holder, parent, false));
        }
        return null;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof PlayerMessageViewHolder) {
            ((PlayerMessageViewHolder) holder).bindMessageInfo((MessageInfo) this.mMessageInfosList.get(position));
        } else if (holder instanceof OpponentMessageViewHolder) {
            OpponentMessageViewHolder opponentMessageViewHolder = (OpponentMessageViewHolder) holder;
            opponentMessageViewHolder.bindMessageInfo((MessageInfo) this.mMessageInfosList.get(position));
            opponentMessageViewHolder.bindPhoto(this.mOpponentPhotoUrl);
        }
    }

    void setMessageInfosList(List<MessageInfo> messageInfosList) {
        this.mMessageInfosList = messageInfosList;
    }
}
