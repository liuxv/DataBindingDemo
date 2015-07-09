package com.liuxv.databinding.utils;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;

import com.liuxv.databinding.view.AsyncImageView;

/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public class ImageUtils {

  @BindingAdapter({"bind:imageUrl", "bind:error"})
  public static void loadImage(AsyncImageView view, String url, Drawable error) {
    view.loadNetWorkImage(url, error);
  }

}
