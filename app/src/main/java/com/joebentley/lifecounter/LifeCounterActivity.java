package com.joebentley.lifecounter;

import android.support.v4.app.Fragment;

/**
 * Created by joe on 06/02/2016.
 */
public class LifeCounterActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new LifeCounterFragment();
    }
}
