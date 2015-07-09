package com.liuxv.databinding.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liuxv.databinding.R;
import com.liuxv.databinding.activity.HeroDetailActivity;
import com.liuxv.databinding.adapter.DataAdapter;
import com.liuxv.databinding.binding.DataBindingHeroAdapter;
import com.liuxv.databinding.config.Constants;
import com.liuxv.databinding.model.ObservableHeroModel;
import com.liuxv.databinding.mvc.model.HeroModel;
import com.liuxv.databinding.utils.HeroUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public class HeroFragment extends BaseFragment {

  private ListView listView;
  private DataAdapter<HeroModel> adapter;
  private final List<HeroModel> dataList = new ArrayList<>();

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
    adapter = new DataBindingHeroAdapter();
    adapter.setData(dataList);
    listView.setAdapter(adapter);
  }

  private void initListener() {
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HeroDetailActivity.launch(view.getContext(),adapter.getItem(position));
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

}
