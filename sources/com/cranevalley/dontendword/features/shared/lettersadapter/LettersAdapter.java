package com.cranevalley.dontendword.features.shared.lettersadapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import com.cranevalley.dontendword.C0521R;

public class LettersAdapter extends Adapter<ViewHolder> implements EditableLetterViewHolderListener {
    private static final int EDITABLE_TYPE = 1;
    public LettersAdapterListener adapterListener;
    private boolean mAddEnabled;
    private String mBackLetter;
    private String mFixedLetters;
    private String mFrontLetter;
    private RecyclerView mRecyclerView;

    public LettersAdapter() {
        this.mFixedLetters = "";
        this.mFrontLetter = "";
        this.mBackLetter = "";
        this.mAddEnabled = false;
    }

    public LettersAdapter(String fixedLetters, String frontLetter, String backLetter, boolean addEnabled) {
        if (fixedLetters == null) {
            fixedLetters = "";
        }
        this.mFixedLetters = fixedLetters;
        if (frontLetter == null) {
            frontLetter = "";
        }
        this.mFrontLetter = frontLetter;
        if (backLetter == null) {
            backLetter = "";
        }
        this.mBackLetter = backLetter;
        this.mAddEnabled = addEnabled;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    public int getItemCount() {
        return (this.mAddEnabled ? 2 : 0) + this.mFixedLetters.length();
    }

    public int getItemViewType(int position) {
        if (this.mAddEnabled && (position == 0 || position == this.mFixedLetters.length() + 1)) {
            return 1;
        }
        return super.getItemViewType(position);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == 1) {
            return new EditableLetterViewHolder(inflater.inflate(C0521R.layout.editable_letter_view_holder, parent, false));
        }
        return new FixedLetterViewHolder(inflater.inflate(C0521R.layout.fixed_letter_view_holder, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof EditableLetterViewHolder) {
            EditableLetterViewHolder editableLetterViewHolder = (EditableLetterViewHolder) holder;
            editableLetterViewHolder.bindLetter(position == 0 ? this.mFrontLetter : this.mBackLetter);
            editableLetterViewHolder.viewHolderListener = this;
        } else if (holder instanceof FixedLetterViewHolder) {
            FixedLetterViewHolder fixedLetterViewHolder = (FixedLetterViewHolder) holder;
            String str = this.mFixedLetters;
            if (this.mAddEnabled) {
                position--;
            }
            fixedLetterViewHolder.bindLetter(String.valueOf(str.charAt(position)));
        }
    }

    public void onEditableLetterViewHolderClickEditText(int index, EditText editText) {
        if (this.adapterListener != null) {
            this.adapterListener.onLettersAdapterClickEditText(editText);
        }
    }

    public void onEditableLetterViewHolderChangeText(int index, String text) {
        EditableLetterViewHolder oppositeViewHolder = (EditableLetterViewHolder) this.mRecyclerView.findViewHolderForAdapterPosition(index == 0 ? getItemCount() - 1 : 0);
        if (text.length() > 0) {
            oppositeViewHolder.clearLetter();
        }
        if (index == 0) {
            this.mFrontLetter = text;
            this.mBackLetter = oppositeViewHolder.getLetter();
        } else {
            this.mBackLetter = text;
            this.mFrontLetter = oppositeViewHolder.getLetter();
        }
        if (this.adapterListener != null) {
            this.adapterListener.onLettersAdapterChangeLetters(this.mFrontLetter, this.mBackLetter);
        }
    }

    public void setLetters(String fixedLetters, String frontLetter, String backLetter) {
        this.mFixedLetters = fixedLetters;
        this.mFrontLetter = frontLetter;
        this.mBackLetter = backLetter;
    }

    public void setAddEnabled(boolean addEnabled) {
        this.mAddEnabled = addEnabled;
    }
}
