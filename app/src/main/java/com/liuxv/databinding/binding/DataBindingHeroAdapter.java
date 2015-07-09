package com.liuxv.databinding.binding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuxv.databinding.DataBindingApplication;
import com.liuxv.databinding.adapter.DataAdapter;
import com.liuxv.databinding.databinding.HeroItemViewBinding;
import com.liuxv.databinding.mvc.model.HeroModel;


/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public class DataBindingHeroAdapter extends DataAdapter<HeroModel> {

  private LayoutInflater inflater;

  public DataBindingHeroAdapter() {
    inflater = LayoutInflater.from(DataBindingApplication.getAppContext());
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {

    HeroItemViewBinding binding;

    if (convertView == null) {
      binding = HeroItemViewBinding.inflate(inflater, parent, false);
      binding.getRoot().setTag(binding);
    } else {
      binding = (HeroItemViewBinding) convertView.getTag();
    }

    binding.setHero(getItem(position));
    return binding.getRoot();
  }
}
