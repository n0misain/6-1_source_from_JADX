package com.cranevalley.dontendword.features.main.chat;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;
import butterknife.OnTouch;
import butterknife.Unbinder;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.features.bases.BaseFragment;
import com.cranevalley.dontendword.helpers.AudioHelper;
import com.cranevalley.dontendword.helpers.FirebaseHelper;
import com.cranevalley.dontendword.helpers.Utility;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;

public class ChatFragment extends BaseFragment {
    private static final String MESSAGE_INFOS_KEY = "MESSAGE_INFOS_KEY";
    private static final String OPPONENT_DISPLAY_NAME_KEY = "OPPONENT_DISPLAY_NAME_KEY";
    private static final String OPPONENT_PHOTO_URL_KEY = "OPPONENT_PHOTO_URL_KEY";
    private static final String OPPONENT_USER_ID_KEY = "OPPONENT_USER_ID_KEY";
    @BindView(2131820698)
    Toolbar actionToolbar;
    private boolean mInsideClickDistance;
    private MessageInfosAdapter mMessageInfosAdapter;
    private ArrayList<MessageInfo> mMessageInfosList;
    private String mOpponentDisplayName;
    private String mOpponentPhotoUrl;
    private String mOpponentUserId;
    private ValueEventListener mPlayerChatWithOpponentEventListener;
    private DatabaseReference mPlayerChatWithOpponentReference;
    private float mTouchStartX;
    private float mTouchStartY;
    private Unbinder mUnbinder;
    @BindView(2131820701)
    AppCompatEditText messageEditText;
    @BindView(2131820700)
    RecyclerView messagesRecyclerView;

    /* renamed from: com.cranevalley.dontendword.features.main.chat.ChatFragment$1 */
    class C05431 implements ValueEventListener {
        C05431() {
        }

        public void onDataChange(DataSnapshot dataSnapshot) {
            ChatFragment.this.mMessageInfosList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    MessageInfo messageInfo = new MessageInfo();
                    messageInfo.text = (String) messageSnapshot.child("text").getValue(String.class);
                    messageInfo.receiver = (Boolean) messageSnapshot.child("receiver").getValue(Boolean.class);
                    ChatFragment.this.mMessageInfosList.add(messageInfo);
                }
            }
            ChatFragment.this.handleMessageInfosChange();
        }

        public void onCancelled(DatabaseError databaseError) {
            Logger.m1220e(databaseError.getMessage(), new Object[0]);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.chat.ChatFragment$2 */
    class C05442 extends ItemDecoration {
        C05442() {
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            int offset = (int) Utility.getPxFromDp(5.0f);
            outRect.set(offset, offset, offset, offset);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.chat.ChatFragment$3 */
    class C05453 implements Runnable {
        C05453() {
        }

        public void run() {
            ChatFragment.this.mMessageInfosAdapter.setMessageInfosList(ChatFragment.this.mMessageInfosList);
            ChatFragment.this.mMessageInfosAdapter.notifyDataSetChanged();
            ChatFragment.this.messagesRecyclerView.smoothScrollToPosition(ChatFragment.this.mMessageInfosAdapter.getItemCount());
        }
    }

    public static ChatFragment newInstance(String opponentUserId, String opponentDisplayName, String opponentPhotoUrl) {
        Bundle bundle = new Bundle();
        bundle.putString(OPPONENT_USER_ID_KEY, opponentUserId);
        bundle.putString(OPPONENT_DISPLAY_NAME_KEY, opponentDisplayName);
        bundle.putString(OPPONENT_PHOTO_URL_KEY, opponentPhotoUrl);
        ChatFragment chatFragment = new ChatFragment();
        chatFragment.setArguments(bundle);
        return chatFragment;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mInsideClickDistance = false;
        this.mTouchStartX = 0.0f;
        this.mTouchStartY = 0.0f;
        this.mMessageInfosList = new ArrayList();
        Bundle bundleReceived = getArguments();
        if (savedInstanceState != null) {
            this.mOpponentUserId = savedInstanceState.getString(OPPONENT_USER_ID_KEY);
            this.mOpponentDisplayName = savedInstanceState.getString(OPPONENT_DISPLAY_NAME_KEY);
            this.mOpponentPhotoUrl = savedInstanceState.getString(OPPONENT_PHOTO_URL_KEY);
            this.mMessageInfosList = (ArrayList) savedInstanceState.getSerializable(MESSAGE_INFOS_KEY);
        } else if (bundleReceived != null) {
            this.mOpponentUserId = bundleReceived.getString(OPPONENT_USER_ID_KEY);
            this.mOpponentDisplayName = bundleReceived.getString(OPPONENT_DISPLAY_NAME_KEY);
            this.mOpponentPhotoUrl = bundleReceived.getString(OPPONENT_PHOTO_URL_KEY);
        }
        this.mPlayerChatWithOpponentReference = FirebaseHelper.getPlayerChatWithOpponentReference(this.mOpponentUserId);
        this.mPlayerChatWithOpponentEventListener = new C05431();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(OPPONENT_USER_ID_KEY, this.mOpponentUserId);
        outState.putString(OPPONENT_DISPLAY_NAME_KEY, this.mOpponentDisplayName);
        outState.putString(OPPONENT_PHOTO_URL_KEY, this.mOpponentPhotoUrl);
        outState.putSerializable(MESSAGE_INFOS_KEY, this.mMessageInfosList);
    }

    public void onDestroy() {
        this.mMessageInfosList.clear();
        this.mMessageInfosList = null;
        this.mMessageInfosAdapter = null;
        this.mPlayerChatWithOpponentReference = null;
        this.mPlayerChatWithOpponentEventListener = null;
        super.onDestroy();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(C0521R.layout.chat_fragment, container, false);
        this.mUnbinder = ButterKnife.bind((Object) this, rootView);
        this.actionToolbar.setTitle(this.mOpponentDisplayName);
        this.mMessageInfosAdapter = new MessageInfosAdapter(this.mOpponentPhotoUrl, this.mMessageInfosList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setStackFromEnd(true);
        this.messagesRecyclerView.setLayoutManager(linearLayoutManager);
        this.messagesRecyclerView.setHasFixedSize(true);
        this.messagesRecyclerView.addItemDecoration(new C05442());
        this.messagesRecyclerView.setAdapter(this.mMessageInfosAdapter);
        return rootView;
    }

    public void onDestroyView() {
        this.mUnbinder.unbind();
        super.onDestroyView();
    }

    public void onResume() {
        super.onResume();
        this.mPlayerChatWithOpponentReference.addValueEventListener(this.mPlayerChatWithOpponentEventListener);
    }

    public void onPause() {
        this.mPlayerChatWithOpponentReference.removeEventListener(this.mPlayerChatWithOpponentEventListener);
        super.onPause();
    }

    private void handleMessageInfosChange() {
        getActivity().runOnUiThread(new C05453());
    }

    private boolean validateText() {
        String text = this.messageEditText.getText().toString();
        if (text.isEmpty()) {
            this.messageEditText.setError("Can't be empty");
            return false;
        } else if (text.length() > Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
            this.messageEditText.setError("Too many characters");
            return false;
        } else {
            this.messageEditText.setError(null);
            return true;
        }
    }

    @OnFocusChange({2131820701})
    void onFocusChangeMessage(View view, boolean hasFocus) {
        if (!hasFocus) {
            Utility.hideKeyboard(view);
        }
    }

    @OnTouch({2131820700})
    boolean onTouchMessages(View view, MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                this.mInsideClickDistance = true;
                this.mTouchStartX = event.getX();
                this.mTouchStartY = event.getY();
                break;
            case 1:
                if (this.mInsideClickDistance && event.getEventTime() - event.getDownTime() < 200) {
                    view.requestFocus();
                    break;
                }
            case 2:
                float touchEndX = event.getX();
                float touchEndY = event.getY();
                if (Utility.getDpFromPx(Math.abs(touchEndX - this.mTouchStartX)) > 5.0f || Utility.getDpFromPx(Math.abs(touchEndY - this.mTouchStartY)) > 5.0f) {
                    this.mInsideClickDistance = false;
                    break;
                }
        }
        return false;
    }

    @OnEditorAction({2131820701})
    boolean onEditorActionMessage(TextView textView, int actionId, KeyEvent event) {
        if (actionId != 4) {
            return false;
        }
        if (validateText()) {
            FirebaseHelper.sendMessage(this.mOpponentUserId, this.messageEditText.getText().toString());
            this.messageEditText.getText().clear();
        }
        return true;
    }

    @OnClick({2131820702})
    void onClickSendMessage(View view) {
        AudioHelper.playClickEffect();
        if (validateText()) {
            FirebaseHelper.sendMessage(this.mOpponentUserId, this.messageEditText.getText().toString());
            this.messageEditText.getText().clear();
        }
    }
}
