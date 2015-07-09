package com.liuxv.databinding.utils;

import com.liuxv.databinding.model.ObservableHeroModel;
import com.liuxv.databinding.mvc.model.HeroModel;

/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public class DataUtils {

  public static HeroModel convertToHeroModel(ObservableHeroModel model) {
    return new HeroModel(model.getName(), model.getDetail(), model.getAvatarRes(),
        model.getStatus());
  }

}
