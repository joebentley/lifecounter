package com.joebentley.lifecounter;

import android.support.v4.app.Fragment;

/**
 * Created by joe on 07/02/2016.
 */
public class ScoreBoardActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ScoreBoardFragment();
    }
}
