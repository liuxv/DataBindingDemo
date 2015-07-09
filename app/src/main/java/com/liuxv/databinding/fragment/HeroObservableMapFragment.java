package com.liuxv.databinding.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.databinding.ObservableArrayMap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liuxv.databinding.R;
import com.liuxv.databinding.activity.HeroDetailActivity;
import com.liuxv.databinding.binding.DataBindingHeroObservableMapAdapter;
import com.liuxv.databinding.config.Constants;
import com.liuxv.databinding.config.Constants.HeroStatus;
import com.liuxv.databinding.mvc.model.HeroModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public class HeroObservableMapFragment extends BaseFragment {

  private ListView listView;
  private FloatingActionButton floatingActionButton;

  private Interpolator interpolator;

  private DataBindingHeroObservableMapAdapter adapter;

  private final List<HeroModel> dataList = new ArrayList<>();
  private final ObservableArrayMap<String, HeroStatus> heroStatus = new ObservableArrayMap<>();

  @Override
  protected int getLayoutResId() {
    return R.layout.hero_fragment;
  }

  @Override
  protected void onInflated(View contentView, Bundle savedInstanceState) {
    initViews();
    initList();
    initListener();
    fakeLoadData();
    showRefreshButton();
  }

  private void initViews() {
    listView = (ListView) mContentView.findViewById(R.id.list_view);
    floatingActionButton = (FloatingActionButton) mContentView.findViewById(R.id.floating_btn);
    interpolator = AnimationUtils.loadInterpolator(mContentView.getContext(),
        android.R.interpolator.accelerate_cubic);
  }

  private void initList() {
    adapter = new DataBindingHeroObservableMapAdapter();
    adapter.setData(dataList);
    adapter.setHeroStatus(heroStatus);
    listView.setAdapter(adapter);
  }

  private void initListener() {
    floatingActionButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        syncHeroStatus();
      }
    });

    listView.setOnScrollListener(new AbsListView.OnScrollListener() {
      @Override
      public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (SCROLL_STATE_IDLE == scrollState) {
          showRefreshButton();
        } else {
          floatingActionButton.setVisibility(View.GONE);
        }
      }

      @Override
      public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
          int totalItemCount) {}
    });

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HeroDetailActivity.launch(view.getContext(), adapter.getItem(position));
      }
    });
  }

  private void fakeLoadData() {
    loadNormalData();
  }

  private void loadNormalData() {
    for (int i = 0; i < Constants.TITLE_LIST.length; i++) {
      HeroModel model =
          new HeroModel(Constants.TITLE_LIST[i], Constants.DETAIL_LIST[i],
              Constants.AVATAR_LIST[i], Constants.STATUS_LIST[i]);
      dataList.add(model);
    }
    adapter.notifyDataSetChanged();
  }

  private void syncHeroStatus() {
    for (int i = 0; i < adapter.getCount(); i++) {
      HeroModel model = adapter.getItem(i);
      heroStatus.put(model.getName(), model.getStatus());
    }
  }


  private void showRefreshButton() {
    floatingActionButton.setVisibility(View.VISIBLE);
    floatingActionButton.setScaleX(0);
    floatingActionButton.setScaleY(0);
    floatingActionButton.animate().scaleX(1).scaleY(1).setInterpolator(interpolator);
  }

  private void hideRefreshButton(boolean needFinish) {
    ViewPropertyAnimator animator =
        floatingActionButton.animate().scaleX(0).scaleY(0).setInterpolator(interpolator);
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
      hideRefreshButton(true);
      return true;
    }
    return super.onKeyDown(keyCode, event);
  }
}
