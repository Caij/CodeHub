package com.caij.codehub.present;

import com.caij.codehub.present.ui.BaseUi;
import com.caij.lib.utils.VolleyManager;

import java.lang.ref.WeakReference;

/**
 * Created by Caij on 2015/11/16.
 */
public abstract class Present<UI extends BaseUi> {

    public UI mUi;

    public Present(UI ui) {
        this.mUi = ui;
        if (ui == null) throw new RuntimeException("ui is null");
    }

    public void onDeath() {
        VolleyManager.cancelRequestByTag(this);
        mUi = null;
    }

}
