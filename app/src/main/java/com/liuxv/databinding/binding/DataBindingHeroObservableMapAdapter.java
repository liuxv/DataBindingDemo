package com.liuxv.databinding.binding;

import android.databinding.ObservableArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuxv.databinding.DataBindingApplication;
import com.liuxv.databinding.adapter.DataAdapter;
import com.liuxv.databinding.config.Constants;
import com.liuxv.databinding.databinding.HeroObservableMapItemViewBinding;
import com.liuxv.databinding.mvc.model.HeroModel;


/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public class DataBindingHeroObservableMapAdapter extends DataAdapter<HeroModel> {

  private LayoutInflater inflater;
  private ObservableArrayMap<String, Constants.HeroStatus> heroStatus;

  public DataBindingHeroObservableMapAdapter() {
    inflater = LayoutInflater.from(DataBindingApplication.getAppContext());
  }

  public void setHeroStatus(ObservableArrayMap<String, Constants.HeroStatus> heroStatus) {
    this.heroStatus = heroStatus;
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {

    HeroObservableMapItemViewBinding binding;

    if (convertView == null) {
      binding = HeroObservableMapItemViewBinding.inflate(inflater, parent, false);
      binding.getRoot().setTag(binding);
    } else {
      binding = (HeroObservableMapItemViewBinding) convertView.getTag();
    }

    binding.setHero(getItem(position));
    binding.setHeroStatus(heroStatus);
    binding.heroAvatar.setImageResource(getItem(position).getAvatarRes());

    return binding.getRoot();
  }
}
