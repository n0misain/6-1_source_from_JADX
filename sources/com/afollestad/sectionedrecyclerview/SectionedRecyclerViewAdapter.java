package com.afollestad.sectionedrecyclerview;

import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager.LayoutParams;
import java.util.List;

public abstract class SectionedRecyclerViewAdapter<VH extends ViewHolder> extends Adapter<VH> {
    protected static final int VIEW_TYPE_HEADER = -2;
    protected static final int VIEW_TYPE_ITEM = -1;
    private final ArrayMap<Integer, Integer> mHeaderLocationMap = new ArrayMap();
    private GridLayoutManager mLayoutManager;
    private boolean mShowHeadersForEmptySections;
    private ArrayMap<Integer, Integer> mSpanMap;

    /* renamed from: com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter$1 */
    class C04401 extends SpanSizeLookup {
        C04401() {
        }

        public int getSpanSize(int position) {
            if (SectionedRecyclerViewAdapter.this.isHeader(position)) {
                return SectionedRecyclerViewAdapter.this.mLayoutManager.getSpanCount();
            }
            int[] sectionAndPos = SectionedRecyclerViewAdapter.this.getSectionIndexAndRelativePosition(position);
            return SectionedRecyclerViewAdapter.this.getRowSpan(SectionedRecyclerViewAdapter.this.mLayoutManager.getSpanCount(), sectionAndPos[0], sectionAndPos[1], position - (sectionAndPos[0] + 1));
        }
    }

    public abstract int getItemCount(int i);

    public abstract int getSectionCount();

    public abstract void onBindHeaderViewHolder(VH vh, int i);

    public abstract void onBindViewHolder(VH vh, int i, int i2, int i3);

    public final boolean isHeader(int position) {
        return this.mHeaderLocationMap.get(Integer.valueOf(position)) != null;
    }

    public final void shouldShowHeadersForEmptySections(boolean show) {
        this.mShowHeadersForEmptySections = show;
    }

    public final void setLayoutManager(@Nullable GridLayoutManager lm) {
        this.mLayoutManager = lm;
        if (lm != null) {
            lm.setSpanSizeLookup(new C04401());
        }
    }

    protected int getRowSpan(int fullSpanSize, int section, int relativePosition, int absolutePosition) {
        return 1;
    }

    private int[] getSectionIndexAndRelativePosition(int itemPosition) {
        int[] iArr;
        synchronized (this.mHeaderLocationMap) {
            Integer lastSectionIndex = Integer.valueOf(-1);
            for (Integer sectionIndex : this.mHeaderLocationMap.keySet()) {
                if (itemPosition <= sectionIndex.intValue()) {
                    break;
                }
                lastSectionIndex = sectionIndex;
            }
            iArr = new int[]{((Integer) this.mHeaderLocationMap.get(lastSectionIndex)).intValue(), (itemPosition - lastSectionIndex.intValue()) - 1};
        }
        return iArr;
    }

    public final int getItemCount() {
        int count = 0;
        this.mHeaderLocationMap.clear();
        for (int s = 0; s < getSectionCount(); s++) {
            int itemCount = getItemCount(s);
            if (this.mShowHeadersForEmptySections || itemCount > 0) {
                this.mHeaderLocationMap.put(Integer.valueOf(count), Integer.valueOf(s));
                count += itemCount + 1;
            }
        }
        return count;
    }

    @Deprecated
    public final int getItemViewType(int position) {
        if (isHeader(position)) {
            return getHeaderViewType(((Integer) this.mHeaderLocationMap.get(Integer.valueOf(position))).intValue());
        }
        int[] sectionAndPos = getSectionIndexAndRelativePosition(position);
        return getItemViewType(sectionAndPos[0], sectionAndPos[1], position - (sectionAndPos[0] + 1));
    }

    @IntRange(from = 0, to = 2147483647L)
    public int getHeaderViewType(int section) {
        return -2;
    }

    @IntRange(from = 0, to = 2147483647L)
    public int getItemViewType(int section, int relativePosition, int absolutePosition) {
        return -1;
    }

    @Deprecated
    public final void onBindViewHolder(VH holder, int position) {
        LayoutParams layoutParams = null;
        if (holder.itemView.getLayoutParams() instanceof GridLayoutManager.LayoutParams) {
            layoutParams = new LayoutParams(-1, -2);
        } else if (holder.itemView.getLayoutParams() instanceof LayoutParams) {
            layoutParams = (LayoutParams) holder.itemView.getLayoutParams();
        }
        if (isHeader(position)) {
            if (layoutParams != null) {
                layoutParams.setFullSpan(true);
            }
            onBindHeaderViewHolder(holder, ((Integer) this.mHeaderLocationMap.get(Integer.valueOf(position))).intValue());
        } else {
            if (layoutParams != null) {
                layoutParams.setFullSpan(false);
            }
            int[] sectionAndPos = getSectionIndexAndRelativePosition(position);
            onBindViewHolder(holder, sectionAndPos[0], sectionAndPos[1], position - (sectionAndPos[0] + 1));
        }
        if (layoutParams != null) {
            holder.itemView.setLayoutParams(layoutParams);
        }
    }

    @Deprecated
    public final void onBindViewHolder(VH holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }
}
