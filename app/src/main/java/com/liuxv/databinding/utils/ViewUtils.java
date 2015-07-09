package com.liuxv.databinding.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.DynamicDrawableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;

/**
 * Some useful methods for views.
 *
 * @author liuxu@gmail.com (Liu Xu)
 */
public class ViewUtils {

  private static final int MAX_SMOOTH_SCROLL_POSITION = 5;
  private static final String REDIRECT_URL_PREFIX = "url=";
  private static final String ELLIPSIS = "...";
  private static final int TOAST_LENGTH = 25;
  private static final long TOAST_TIME = 500L;

  private ViewUtils() {}

  /**
   * Changes the size of a view.
   *
   * @param view the view to change size
   * @param width the new width
   * @param height the new height
   */
  public static void setViewSize(View view, int width, int height) {
    LayoutParams layoutParams = view.getLayoutParams();
    layoutParams.width = width;
    layoutParams.height = height;
    view.setLayoutParams(layoutParams);
  }


  /**
   * Sets the background of view.
   *
   * @param view view
   * @param background background drawable
   */
  @SuppressWarnings("deprecation")
  @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
  public static void setBackground(View view, Drawable background) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
      view.setBackground(background);
    } else {
      view.setBackgroundDrawable(background);
    }
  }

  /**
   * Creates a view.
   *
   * @param parent parent view
   * @param resId resource id
   * @return view
   */
  public static View newInstance(ViewGroup parent, int resId) {
    return LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
  }

  /**
   * Creates a view.
   *
   * @param context context
   * @param resId resource id
   * @return view
   */
  public static View newInstance(Context context, int resId) {
    return LayoutInflater.from(context).inflate(resId, null);
  }



  /**
   * get a String of which with a Drawable in front.
   *
   * @param textSize the size of text
   * @param text the content of text
   * @param drawable the drawable you want to put in front of text
   * @return String with drawable
   */
  public static CharSequence getDrawableTextSpan(final int textSize,
      String text, final Drawable drawable) {
    SpannableStringBuilder sb = new SpannableStringBuilder(" ");
    sb.append(text);
    DynamicDrawableSpan drawableSpan = new DynamicDrawableSpan() {
      @Override
      public Drawable getDrawable() {
        float height = textSize;
        float width =
            drawable.getIntrinsicWidth() * height / drawable.getIntrinsicHeight();
        drawable.setBounds(0, 0, (int) width, (int) height);
        return drawable;
      }
    };
    sb.setSpan(drawableSpan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
    return sb;
  }

  public static boolean isViewAttachedToDecorView(View view) {
    if (!(view.getContext() instanceof Activity)) {
      return true;
    }
    View decorView = ((Activity) view.getContext()).getWindow().getDecorView();
    if (view == decorView) {
      return true;
    }
    if (view.getWindowToken() != null && view.getWindowToken() != decorView.getWindowToken()) {
      // The view is not in the same window with activity. It's probably in a Dialog.
      return true;
    }
    ViewParent parent = view.getParent();
    while (parent != null) {
      if (parent == decorView) {
        return true;
      }
      parent = parent.getParent();
    }
    return false;
  }

}
