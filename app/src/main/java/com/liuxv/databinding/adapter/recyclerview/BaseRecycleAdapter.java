package com.liuxv.databinding.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.liuxv.databinding.mvc.base.BaseController;
import com.liuxv.databinding.mvc.base.BaseModel;
import com.liuxv.databinding.mvc.base.BaseView;


/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public abstract class BaseRecycleAdapter<M extends BaseModel>
    extends DataRecycleViewAdapter<BaseRecycleAdapter.BaseViewHolder, M> {


  @Override
  public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    BaseController controller;
    BaseView baseView;
    controller = newController(viewType);
    baseView = newView(parent, viewType);
    return new BaseViewHolder(baseView.getView(), controller);
  }


  @Override
  public void onBindViewHolder(BaseViewHolder viewHolder, int position) {
    doBind(viewHolder.mController, (BaseView) viewHolder.itemView, getItem(position));
  }


  public final static class BaseViewHolder extends RecyclerView.ViewHolder {
    public final BaseController mController;

    public BaseViewHolder(View itemView, BaseController controller) {
      super(itemView);
      this.mController = controller;
    }
  }

  protected void doBind(BaseController controller, BaseView baseView, M baseModel) {
    controller.bind(baseView, baseModel);
  }


  protected abstract BaseView newView(ViewGroup parent, int type);

  protected abstract BaseController newController(int type);

}
