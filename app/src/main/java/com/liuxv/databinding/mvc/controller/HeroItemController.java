package com.liuxv.databinding.mvc.controller;

import com.liuxv.databinding.mvc.base.BaseController;
import com.liuxv.databinding.mvc.model.HeroModel;
import com.liuxv.databinding.mvc.view.HeroView;

/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public class HeroItemController extends BaseController<HeroView, HeroModel> {
  @Override
  public void bind(HeroView view, HeroModel model) {
    view.getTitle().setText(model.getName());
    view.getDetail().setText(model.getDetail());
    view.getAvatar().setImageResource(model.getAvatarRes());

  }
}
