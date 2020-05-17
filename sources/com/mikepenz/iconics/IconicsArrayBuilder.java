package com.mikepenz.iconics;

import com.mikepenz.iconics.typeface.IIcon;
import java.util.ArrayList;

public class IconicsArrayBuilder {
    private IconicsDrawable mIconBase;
    private ArrayList<Object> mIcons = new ArrayList();

    public IconicsArrayBuilder(IconicsDrawable iconicsDrawable) {
        this.mIconBase = iconicsDrawable;
    }

    public IconicsArrayBuilder add(IIcon icon) {
        this.mIcons.add(icon);
        return this;
    }

    public IconicsArrayBuilder add(String icon) {
        this.mIcons.add(icon);
        return this;
    }

    public IconicsArrayBuilder add(Character icon) {
        this.mIcons.add(icon);
        return this;
    }

    public IconicsDrawable[] build() {
        IconicsDrawable[] iconicsDrawables = new IconicsDrawable[this.mIcons.size()];
        for (int i = 0; i < this.mIcons.size(); i++) {
            if (this.mIcons.get(i) instanceof IIcon) {
                iconicsDrawables[i] = this.mIconBase.clone().icon((IIcon) this.mIcons.get(i));
            } else if (this.mIcons.get(i) instanceof Character) {
                iconicsDrawables[i] = this.mIconBase.clone().icon((Character) this.mIcons.get(i));
            } else if (this.mIcons.get(i) instanceof String) {
                iconicsDrawables[i] = this.mIconBase.clone().iconText((String) this.mIcons.get(i));
            }
        }
        return iconicsDrawables;
    }
}
