package com.cranevalley.dontendword.features.main.multiplayergameplay;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;
import com.makeramen.roundedimageview.RoundedImageView;

public class MultiplayerGamePlayFragment_ViewBinding<T extends MultiplayerGamePlayFragment> implements Unbinder {
    protected T target;
    private View view2131820690;
    private View view2131820721;
    private View view2131820748;
    private View view2131820797;
    private View view2131820798;
    private View view2131820799;
    private View view2131820800;
    private View view2131820801;
    private View view2131820802;

    @UiThread
    public MultiplayerGamePlayFragment_ViewBinding(final T target, View source) {
        this.target = target;
        target.opponentDisplayNameTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.opponentDisplayNameTextView, "field 'opponentDisplayNameTextView'", AppCompatTextView.class);
        target.statusTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.statusTextView, "field 'statusTextView'", AppCompatTextView.class);
        target.timeSinceNudgeTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.timeSinceNudgeTextView, "field 'timeSinceNudgeTextView'", AppCompatTextView.class);
        target.reportTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.reportTextView, "field 'reportTextView'", AppCompatTextView.class);
        target.opponentPhotoImageView = (RoundedImageView) Utils.findRequiredViewAsType(source, C0521R.id.opponentPhotoImageView, "field 'opponentPhotoImageView'", RoundedImageView.class);
        target.friendIndicatorImageView = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0521R.id.friendIndicatorImageView, "field 'friendIndicatorImageView'", AppCompatImageView.class);
        target.gamePlayProgressBar = (ProgressBar) Utils.findRequiredViewAsType(source, C0521R.id.gamePlayProgressBar, "field 'gamePlayProgressBar'", ProgressBar.class);
        View view = Utils.findRequiredView(source, C0521R.id.addLetterButton, "field 'addLetterButton' and method 'onClickAddLetter'");
        target.addLetterButton = (AppCompatButton) Utils.castView(view, C0521R.id.addLetterButton, "field 'addLetterButton'", AppCompatButton.class);
        this.view2131820721 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickAddLetter(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.challengeButton, "field 'challengeButton' and method 'onClickChallenge'");
        target.challengeButton = (AppCompatButton) Utils.castView(view, C0521R.id.challengeButton, "field 'challengeButton'", AppCompatButton.class);
        this.view2131820799 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickChallenge(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.defendButton, "field 'defendButton' and method 'onClickDefend'");
        target.defendButton = (AppCompatButton) Utils.castView(view, C0521R.id.defendButton, "field 'defendButton'", AppCompatButton.class);
        this.view2131820800 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickDefend(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.nudgeButton, "field 'nudgeButton' and method 'onClickNudge'");
        target.nudgeButton = (AppCompatButton) Utils.castView(view, C0521R.id.nudgeButton, "field 'nudgeButton'", AppCompatButton.class);
        this.view2131820797 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickNudge(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.claimWinButton, "field 'claimWinButton' and method 'onClickClaimWin'");
        target.claimWinButton = (AppCompatButton) Utils.castView(view, C0521R.id.claimWinButton, "field 'claimWinButton'", AppCompatButton.class);
        this.view2131820801 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickClaimWin(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.rematchButton, "field 'rematchButton' and method 'onClickRematch'");
        target.rematchButton = (AppCompatButton) Utils.castView(view, C0521R.id.rematchButton, "field 'rematchButton'", AppCompatButton.class);
        this.view2131820798 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickRematch(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.shareResultButton, "field 'shareResultButton' and method 'onClickShareResult'");
        target.shareResultButton = (AppCompatButton) Utils.castView(view, C0521R.id.shareResultButton, "field 'shareResultButton'", AppCompatButton.class);
        this.view2131820802 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickShareResult(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.addFriendButton, "field 'addFriendButton' and method 'onClickAddFriend'");
        target.addFriendButton = (AppCompatImageButton) Utils.castView(view, C0521R.id.addFriendButton, "field 'addFriendButton'", AppCompatImageButton.class);
        this.view2131820690 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickAddFriend(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.chatButton, "field 'chatButton' and method 'onClickChat'");
        target.chatButton = (AppCompatImageButton) Utils.castView(view, C0521R.id.chatButton, "field 'chatButton'", AppCompatImageButton.class);
        this.view2131820748 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickChat(p0);
            }
        });
        target.lettersRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0521R.id.lettersRecyclerView, "field 'lettersRecyclerView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.opponentDisplayNameTextView = null;
        target.statusTextView = null;
        target.timeSinceNudgeTextView = null;
        target.reportTextView = null;
        target.opponentPhotoImageView = null;
        target.friendIndicatorImageView = null;
        target.gamePlayProgressBar = null;
        target.addLetterButton = null;
        target.challengeButton = null;
        target.defendButton = null;
        target.nudgeButton = null;
        target.claimWinButton = null;
        target.rematchButton = null;
        target.shareResultButton = null;
        target.addFriendButton = null;
        target.chatButton = null;
        target.lettersRecyclerView = null;
        this.view2131820721.setOnClickListener(null);
        this.view2131820721 = null;
        this.view2131820799.setOnClickListener(null);
        this.view2131820799 = null;
        this.view2131820800.setOnClickListener(null);
        this.view2131820800 = null;
        this.view2131820797.setOnClickListener(null);
        this.view2131820797 = null;
        this.view2131820801.setOnClickListener(null);
        this.view2131820801 = null;
        this.view2131820798.setOnClickListener(null);
        this.view2131820798 = null;
        this.view2131820802.setOnClickListener(null);
        this.view2131820802 = null;
        this.view2131820690.setOnClickListener(null);
        this.view2131820690 = null;
        this.view2131820748.setOnClickListener(null);
        this.view2131820748 = null;
        this.target = null;
    }
}
