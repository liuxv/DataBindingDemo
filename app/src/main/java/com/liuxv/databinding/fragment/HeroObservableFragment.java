package com.liuxv.databinding.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liuxv.databinding.R;
import com.liuxv.databinding.activity.HeroDetailActivity;
import com.liuxv.databinding.adapter.DataAdapter;
import com.liuxv.databinding.binding.DataBindingHeroObservableAdapter;
import com.liuxv.databinding.config.Constants;
import com.liuxv.databinding.model.ObservableHeroModel;
import com.liuxv.databinding.utils.HeroUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public class HeroObservableFragment extends BaseFragment {

  private ListView listView;
  private DataAdapter<ObservableHeroModel> adapter;
  private final List<ObservableHeroModel> observableDataList = new ArrayList<>();

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
  }

  private void initViews() {
    listView = (ListView) mContentView.findViewById(R.id.list_view);
  }

  private void initList() {
    adapter = new DataBindingHeroObservableAdapter();
    adapter.setData(observableDataList);
    listView.setAdapter(adapter);
  }

  private void initListener() {
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ObservableHeroModel model = adapter.getItem(position);
        if (!TextUtils.isEmpty(model.getName())
            && !model.getName().startsWith(HeroUtils.HERO_PREFIX)) {
          model.setName(HeroUtils.getHeroTitle(model.getName()));
        }
        HeroDetailActivity.launch(view.getContext(), adapter.getItem(position));
      }
    });
  }

  private void fakeLoadData() {
    loadObservableData();
  }

  private void loadObservableData() {
    for (int i = 0; i < Constants.TITLE_LIST.length; i++) {
      ObservableHeroModel model =
          new ObservableHeroModel(Constants.TITLE_LIST[i], Constants.DETAIL_LIST[i],
              Constants.AVATAR_LIST[i], Constants.STATUS_LIST[i]);
      observableDataList.add(model);
    }
    adapter.notifyDataSetChanged();
  }

}
