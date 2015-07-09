package com.liuxv.databinding.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public class AsyncImageView extends ImageView {
  public AsyncImageView(Context context) {
    super(context);
  }

  public AsyncImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public AsyncImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public void loadNetWorkImage(String url, Drawable error) {
    System.out.println(url);
    setImageDrawable(error);
  }
}
