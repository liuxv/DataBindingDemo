package com.liuxv.databinding.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.liuxv.databinding.BR;
import com.liuxv.databinding.config.Constants.HeroStatus;
import com.liuxv.databinding.mvc.base.BaseModel;

import java.io.Serializable;

/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public class ObservableHeroModel extends BaseObservable implements BaseModel, Serializable {
  private final ObservableField<String> name = new ObservableField<>();
  private final ObservableField<String> detail = new ObservableField<>();
  private final ObservableInt avatarRes = new ObservableInt();
  private final ObservableField<HeroStatus> status = new ObservableField<>();

  public ObservableHeroModel(String name, String detail, int avatarRes, HeroStatus status) {
    this.name.set(name);
    this.detail.set(detail);
    this.avatarRes.set(avatarRes);
    this.status.set(status);
  }

  @Bindable
  public String getName() {
    return name.get();
  }

  public void setName(String name) {
    this.name.set(name);
    notifyPropertyChanged(BR.name);
  }

  @Bindable
  public String getDetail() {
    return detail.get();
  }

  public void setDetail(String detail) {
    this.detail.set(detail);
    notifyPropertyChanged(BR.detail);
  }

  @Bindable
  public int getAvatarRes() {
    return avatarRes.get();
  }

  public void setAvatarRes(int avatarRes) {
    this.avatarRes.set(avatarRes);
    notifyPropertyChanged(BR.avatarRes);
  }

  @Bindable
  public HeroStatus getStatus() {
    return status.get();
  }

  public void setStatus(HeroStatus status) {
    this.status.set(status);
    notifyPropertyChanged(BR.status);
  }
}
