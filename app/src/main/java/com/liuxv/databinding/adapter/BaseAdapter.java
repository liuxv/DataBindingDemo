package com.liuxv.databinding.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.liuxv.databinding.R;
import com.liuxv.databinding.mvc.base.BaseController;
import com.liuxv.databinding.mvc.base.BaseModel;
import com.liuxv.databinding.mvc.base.BaseView;


/**
 * @author liuxu@gmail.com (Liu Xu)
 * 
 * @param <M> model type.
 */
public abstract class BaseAdapter<M extends BaseModel> extends DataAdapter<M> {


  private static final int TAG_KEY_CONTROLLER = R.id.card_controller;
  private static final int TAG_KEY_TYPE = R.id.card_type;

  private static final int NO_TYPE = -1;


  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    BaseController controller;
    BaseView baseView;
    View itemView;
    int viewType = getItemViewType(position);
    int convertViewType;

    if (convertView != null) {
      convertViewType = (int) convertView.getTag(TAG_KEY_TYPE);
    } else {
      convertViewType = NO_TYPE;
    }

    if (convertView instanceof BaseView && viewType == convertViewType) {
      baseView = (BaseView) convertView;
      itemView = baseView.getView();
      controller = (BaseController) itemView.getTag(TAG_KEY_CONTROLLER);
    } else {
      baseView = newView(parent, viewType);
      itemView = baseView.getView();
      controller = newController(viewType);
      itemView.setTag(TAG_KEY_CONTROLLER, controller);
      itemView.setTag(TAG_KEY_TYPE, viewType);
    }
    doBind(controller, baseView, getItem(position));

    return itemView;
  }

  protected void doBind(BaseController controller, BaseView baseView, M baseModel) {
    controller.unbind();
    controller.bind(baseView, baseModel);
  }


  protected abstract BaseView newView(ViewGroup parent, int type);

  protected abstract BaseController newController(int type);
}
