package com.cranevalley.dontendword.features.main.chat;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;

public class ChatFragment_ViewBinding<T extends ChatFragment> implements Unbinder {
    protected T target;
    private View view2131820700;
    private View view2131820701;
    private View view2131820702;

    @UiThread
    public ChatFragment_ViewBinding(final T target, View source) {
        this.target = target;
        target.actionToolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0521R.id.actionToolbar, "field 'actionToolbar'", Toolbar.class);
        View view = Utils.findRequiredView(source, C0521R.id.messageEditText, "field 'messageEditText', method 'onEditorActionMessage', and method 'onFocusChangeMessage'");
        target.messageEditText = (AppCompatEditText) Utils.castView(view, C0521R.id.messageEditText, "field 'messageEditText'", AppCompatEditText.class);
        this.view2131820701 = view;
        ((TextView) view).setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView p0, int p1, KeyEvent p2) {
                return target.onEditorActionMessage(p0, p1, p2);
            }
        });
        view.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View p0, boolean p1) {
                target.onFocusChangeMessage(p0, p1);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.messagesRecyclerView, "field 'messagesRecyclerView' and method 'onTouchMessages'");
        target.messagesRecyclerView = (RecyclerView) Utils.castView(view, C0521R.id.messagesRecyclerView, "field 'messagesRecyclerView'", RecyclerView.class);
        this.view2131820700 = view;
        view.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View p0, MotionEvent p1) {
                return target.onTouchMessages(p0, p1);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.sendMessageButton, "method 'onClickSendMessage'");
        this.view2131820702 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickSendMessage(p0);
            }
        });
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.actionToolbar = null;
        target.messageEditText = null;
        target.messagesRecyclerView = null;
        ((TextView) this.view2131820701).setOnEditorActionListener(null);
        this.view2131820701.setOnFocusChangeListener(null);
        this.view2131820701 = null;
        this.view2131820700.setOnTouchListener(null);
        this.view2131820700 = null;
        this.view2131820702.setOnClickListener(null);
        this.view2131820702 = null;
        this.target = null;
    }
}
