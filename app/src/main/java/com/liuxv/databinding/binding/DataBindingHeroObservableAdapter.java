package com.liuxv.databinding.binding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuxv.databinding.DataBindingApplication;
import com.liuxv.databinding.adapter.DataAdapter;
import com.liuxv.databinding.databinding.HeroObservableItemViewBinding;
import com.liuxv.databinding.model.ObservableHeroModel;


/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public class DataBindingHeroObservableAdapter extends DataAdapter<ObservableHeroModel> {

  private LayoutInflater inflater;

  public DataBindingHeroObservableAdapter() {
    inflater = LayoutInflater.from(DataBindingApplication.getAppContext());
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {

    HeroObservableItemViewBinding binding;

    if (convertView == null) {
      binding = HeroObservableItemViewBinding.inflate(inflater, parent, false);
      binding.getRoot().setTag(binding);
    } else {
      binding = (HeroObservableItemViewBinding) convertView.getTag();
    }

    binding.setHero(getItem(position));
    return binding.getRoot();
  }
}
