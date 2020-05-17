package com.cranevalley.dontendword.features.main.creategame;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;
import com.makeramen.roundedimageview.RoundedImageView;

public class CreateGameDialogFragment_ViewBinding<T extends CreateGameDialogFragment> implements Unbinder {
    protected T target;
    private View view2131820687;
    private View view2131820721;
    private View view2131820722;

    @UiThread
    public CreateGameDialogFragment_ViewBinding(final T target, View source) {
        this.target = target;
        target.opponentDisplayNameTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.opponentDisplayNameTextView, "field 'opponentDisplayNameTextView'", AppCompatTextView.class);
        target.reportTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.reportTextView, "field 'reportTextView'", AppCompatTextView.class);
        target.opponentPhotoImageView = (RoundedImageView) Utils.findRequiredViewAsType(source, C0521R.id.opponentPhotoImageView, "field 'opponentPhotoImageView'", RoundedImageView.class);
        target.createGameProgressBar = (ProgressBar) Utils.findRequiredViewAsType(source, C0521R.id.createGameProgressBar, "field 'createGameProgressBar'", ProgressBar.class);
        View view = Utils.findRequiredView(source, C0521R.id.addLetterButton, "field 'addLetterButton' and method 'onClickAddLetter'");
        target.addLetterButton = (AppCompatButton) Utils.castView(view, C0521R.id.addLetterButton, "field 'addLetterButton'", AppCompatButton.class);
        this.view2131820721 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickAddLetter(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.openGameButton, "field 'openGameButton' and method 'onClickOpenGame'");
        target.openGameButton = (AppCompatButton) Utils.castView(view, C0521R.id.openGameButton, "field 'openGameButton'", AppCompatButton.class);
        this.view2131820722 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickOpenGame(p0);
            }
        });
        target.lettersRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0521R.id.lettersRecyclerView, "field 'lettersRecyclerView'", RecyclerView.class);
        view = Utils.findRequiredView(source, C0521R.id.closeButton, "method 'onClickClose'");
        this.view2131820687 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickClose(p0);
            }
        });
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.opponentDisplayNameTextView = null;
        target.reportTextView = null;
        target.opponentPhotoImageView = null;
        target.createGameProgressBar = null;
        target.addLetterButton = null;
        target.openGameButton = null;
        target.lettersRecyclerView = null;
        this.view2131820721.setOnClickListener(null);
        this.view2131820721 = null;
        this.view2131820722.setOnClickListener(null);
        this.view2131820722 = null;
        this.view2131820687.setOnClickListener(null);
        this.view2131820687 = null;
        this.target = null;
    }
}
