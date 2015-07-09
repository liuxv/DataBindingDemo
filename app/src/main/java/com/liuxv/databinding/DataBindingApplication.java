package com.liuxv.databinding;

import android.app.Application;
import android.content.Context;

/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public class DataBindingApplication extends Application {

  private static Context appContext;

  @Override
  public void onCreate() {
    super.onCreate();
    appContext = this;
  }

  public static Context getAppContext() {
    return appContext;
  }
}
