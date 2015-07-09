package com.liuxv.databinding.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.liuxv.databinding.R;
import com.liuxv.databinding.fragment.BaseFragment;
import com.liuxv.databinding.fragment.HeroFragment;
import com.liuxv.databinding.fragment.HeroObservableFragment;
import com.liuxv.databinding.fragment.HeroObservableMapFragment;

/**
 *
 * @author liuxu@gmail.com (Liu Xu)
 */
public class HeroListActivity extends BaseTitleActivity {

  private static DemoType demoType;

  public enum DemoType {
    NORMAL, OBSERVABLE, OBSERVABLE_MAP
  }

  public static void launch(Context context, DemoType demoType) {
    final Intent intent = new Intent(context, HeroListActivity.class);
    HeroListActivity.demoType = demoType;
    if (!(context instanceof Activity)) {
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
    context.startActivity(intent);
  }

  @Override
  protected String getTitleText() {
    return getString(R.string.hero_title);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    switch (demoType) {
      case NORMAL:
        mFragment =
            (BaseFragment) Fragment.instantiate(this,
                HeroFragment.class.getName(), null);
        break;
      case OBSERVABLE:
        mFragment =
            (BaseFragment) Fragment.instantiate(this,
                HeroObservableFragment.class.getName(), null);
        break;
      case OBSERVABLE_MAP:
        mFragment =
            (BaseFragment) Fragment.instantiate(this,
                HeroObservableMapFragment.class.getName(), null);
        break;
      default:
        mFragment =
            (BaseFragment) Fragment.instantiate(this,
                HeroFragment.class.getName(), null);
        break;
    }

    replaceFragment(mFragment);
  }

}
