package com.liuxv.databinding.adapter;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author liuxu@gmail.com (Liu Xu)
 *
 * @param <T>
 */
public abstract class DataAdapter<T> extends BaseAdapter {

  protected List<T> mData;

  public void setData(List<T> data) {
    this.mData = data;
    notifyDataSetChanged();
  }


  public List<T> getData() {
    return mData;
  }

  @Override
  public T getItem(int position) {
    if (getCount() <= position) {
      return null;
    }
    return mData.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
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
}
