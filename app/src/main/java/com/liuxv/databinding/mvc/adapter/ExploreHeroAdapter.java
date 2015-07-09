package com.liuxv.databinding.mvc.adapter;

import android.view.ViewGroup;

import com.liuxv.databinding.adapter.BaseAdapter;
import com.liuxv.databinding.mvc.base.BaseController;
import com.liuxv.databinding.mvc.base.BaseView;
import com.liuxv.databinding.mvc.controller.HeroItemController;
import com.liuxv.databinding.mvc.model.HeroModel;
import com.liuxv.databinding.mvc.view.HeroView;

/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public class ExploreHeroAdapter extends BaseAdapter<HeroModel> {
  @Override
  protected BaseView newView(ViewGroup parent, int type) {
    return HeroView.newInstance(parent);
  }

  @Override
  protected BaseController newController(int type) {
    return new HeroItemController();
  }
}
