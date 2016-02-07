package com.joebentley.lifecounter;

import android.support.v4.app.Fragment;

public class LifeCounterActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new LifeCounterFragment();
    }
}
