package com.liuxv.databinding.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


import com.liuxv.databinding.R;
import com.liuxv.databinding.mvc.base.BaseController;
import com.liuxv.databinding.mvc.base.BaseModel;
import com.liuxv.databinding.mvc.base.BaseView;

import java.util.List;

/**
 * @author liuxu@gmail.com (Liu Xu)
 * 
 */
public abstract class BasePagerAdapter<M extends BaseModel> extends PagerAdapter {

  private static final int TAG_KEY_CONTROLLER = R.id.card_controller;

  protected List<M> mData;

  public void setData(List<M> data) {
    this.mData = data;
    notifyDataSetChanged();
  }


  public List<M> getData() {
    return mData;
  }

  public M getItem(int position) {
    if (getCount() <= position) {
      return null;
    }
    return mData.get(position);
  }

  public int getItemType(int position) {
    return 0;
  }

  @Override
  public int getCount() {
    return mData == null ? 0 : mData.size();
  }


  public void clear() {
    if (mData != null) {
      mData.clear();
    }
    notifyDataSetChanged();
  }


  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    if (object instanceof BaseView) {
      Object controller = ((BaseView) object).getView().getTag(TAG_KEY_CONTROLLER);
      if (controller instanceof BaseController) {
        ((BaseController) controller).unbind();
      }
    }
    container.removeView((View) object);
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    BaseController controller;
    BaseView baseView;

    baseView = newView(container, getItemType(position));
    controller = newController(getItemType(position));

    baseView.getView().setTag(TAG_KEY_CONTROLLER, controller);

    doBind(controller, baseView, getItem(position));

    container.addView(baseView.getView());
    return baseView.getView();
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return object.equals(view);
  }

  protected void doBind(BaseController controller, BaseView baseView, M baseModel) {
    controller.bind(baseView, baseModel);
  }


  protected abstract BaseView newView(ViewGroup parent, int type);

  protected abstract BaseController newController(int type);
}
