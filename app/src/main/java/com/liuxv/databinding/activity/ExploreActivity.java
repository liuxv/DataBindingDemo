package com.liuxv.databinding.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.liuxv.databinding.R;
import com.liuxv.databinding.fragment.BaseFragment;
import com.liuxv.databinding.fragment.ExploreFragment;

/**
 *
 * @author liuxu@gmail.com (Liu Xu)
 */
public class ExploreActivity extends BaseTitleActivity {

  @Override
  protected String getTitleText() {
    return getString(R.string.app_name);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mFragment =
        (BaseFragment) Fragment.instantiate(this,
            ExploreFragment.class.getName(), null);
    replaceFragment(mFragment);
  }

}
