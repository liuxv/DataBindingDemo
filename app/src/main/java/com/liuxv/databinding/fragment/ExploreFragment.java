package com.liuxv.databinding.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import com.liuxv.databinding.R;
import com.liuxv.databinding.activity.HeroListActivity;

/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public class ExploreFragment extends BaseFragment {

  private FloatingActionButton fabNormal;
  private FloatingActionButton fabObservable;
  private FloatingActionButton fabObservableMap;

  private Interpolator interpolator;


  @Override
  protected int getLayoutResId() {
    return R.layout.explore_fragment;
  }

  @Override
  protected void onInflated(View contentView, Bundle savedInstanceState) {
    initViews();
    initListener();
  }

  @Override
  public void onResume() {
    super.onResume();
    showAllButton();
  }

  private void initViews() {
    fabNormal = (FloatingActionButton) mContentView.findViewById(R.id.floating_btn_normal);
    fabObservable = (FloatingActionButton) mContentView.findViewById(R.id.floating_btn_observer);
    fabObservableMap =
        (FloatingActionButton) mContentView.findViewById(R.id.floating_btn_observer_map);
    interpolator = AnimationUtils.loadInterpolator(mContentView.getContext(),
        android.R.interpolator.accelerate_cubic);
  }


  private void initListener() {
    fabNormal.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        HeroListActivity.launch(v.getContext(), HeroListActivity.DemoType.NORMAL);
      }
    });
    fabObservable.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        HeroListActivity.launch(v.getContext(), HeroListActivity.DemoType.OBSERVABLE);
      }
    });
    fabObservableMap.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        HeroListActivity.launch(v.getContext(), HeroListActivity.DemoType.OBSERVABLE_MAP);
      }
    });

  }

  private void showAllButton() {
    showRefreshButton(fabNormal);
    showRefreshButton(fabObservable);
    showRefreshButton(fabObservableMap);
  }

  private void hideAllButton() {
    hideRefreshButton(fabNormal, true);
    hideRefreshButton(fabObservable, true);
    hideRefreshButton(fabObservableMap, true);
  }


  private void showRefreshButton(FloatingActionButton button) {
    button.setVisibility(View.VISIBLE);
    button.setScaleX(0);
    button.setScaleY(0);
    button.animate().scaleX(1).scaleY(1).setInterpolator(interpolator);
  }

  private void hideRefreshButton(FloatingActionButton button, boolean needFinish) {
    ViewPropertyAnimator animator =
        button.animate().scaleX(0).scaleY(0).setInterpolator(interpolator);
    if (needFinish) {
      animator.setListener(new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
          if (isAdded()) {
            getActivity().finish();
          }
          super.onAnimationEnd(animation);
        }
      });
    }
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      hideAllButton();
      return true;
    }
    return super.onKeyDown(keyCode, event);
  }
}
