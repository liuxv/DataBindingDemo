package com.liuxv.databinding.mvc.model;

import com.liuxv.databinding.config.Constants.HeroStatus;
import com.liuxv.databinding.mvc.base.BaseModel;

import java.io.Serializable;

/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public class HeroModel implements BaseModel, Serializable {
  private String name;
  private String detail;
  private int avatarRes;
  private HeroStatus status;

  public HeroModel(String name, String detail, int avatarRes, HeroStatus status) {
    this.name = name;
    this.detail = detail;
    this.avatarRes = avatarRes;
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public int getAvatarRes() {
    return avatarRes;
  }

  public void setAvatarRes(int avatarRes) {
    this.avatarRes = avatarRes;
  }

  public HeroStatus getStatus() {
    return status;
  }

  public void setStatus(HeroStatus status) {
    this.status = status;
  }
}
