package com.liuxv.databinding.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public abstract class DataRecycleViewAdapter<VH extends RecyclerView.ViewHolder, T>
    extends RecyclerView.Adapter<VH> {

  /**
   * equals NO_POSITION in RecyclerView.Adapter
   */
  public static final int LAST_POSITION = -1;

  protected List<T> mDataList;


  /**
   * method area
   */

  public void add(T model, int position) {
    position = position == LAST_POSITION ? getItemCount() : position;
    mDataList.add(position, model);
    notifyItemInserted(position);
    notifyItemPositionChange(position);
  }

  public void remove(int position) {
    if (position == LAST_POSITION && getItemCount() > 0) {
      position = getItemCount() - 1;
    }

    if (position > LAST_POSITION && position < getItemCount()) {
      mDataList.remove(position);
      notifyItemRemoved(position);
      notifyItemPositionChange(position);

    }
  }

  public void setData(List<T> dataList) {
    if (dataList == null) {
      throw new IllegalArgumentException("modelData must not be null");
    }
    this.mDataList = dataList;
    notifyDataSetChanged();
  }

  public List<T> getData() {
    return mDataList;
  }

  public T getItem(int position) {
    if (position >= mDataList.size()) {
      return null;
    }
    return mDataList.get(position);
  }

  public void clear() {
    if (mDataList != null) {
      mDataList.clear();
    }
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return mDataList == null ? 0 : mDataList.size();
  }


  private void notifyItemPositionChange(int position) {
    for (int i = position; i < getItemCount(); i++) {
      notifyItemChanged(i);
    }
  }


}
