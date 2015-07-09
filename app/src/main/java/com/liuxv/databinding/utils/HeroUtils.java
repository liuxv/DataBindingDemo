package com.liuxv.databinding.utils;

import com.liuxv.databinding.config.Constants;

/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public class HeroUtils {
  public static final String HERO_PREFIX = "HERO ";

  public static boolean getHeroStatus(Constants.HeroStatus status) {
    if (status == Constants.HeroStatus.ALIVE) {
      return true;
    }
    return false;
  }


  public static String getHeroTitle(String name) {
    return HERO_PREFIX + name;
  }
}
