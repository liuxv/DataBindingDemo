package com.liuxv.databinding.mvc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liuxv.databinding.R;
import com.liuxv.databinding.mvc.base.BaseView;
import com.liuxv.databinding.utils.ViewUtils;

/**
 * @author liuxu@gmail.com (Liu Xu)
 */
public class HeroView extends LinearLayout implements BaseView {

  private TextView title;
  private TextView detail;
  private ImageView avatar;

  public HeroView(Context context) {
    super(context);
  }

  public HeroView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }


  public static HeroView newInstance(ViewGroup parent) {
    return (HeroView) ViewUtils.newInstance(parent, R.layout.hero_item_view);
  }

  public static HeroView newInstance(Context context) {
    return (HeroView) ViewUtils.newInstance(context, R.layout.hero_item_view);
  }


  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    title = (TextView) findViewById(R.id.hero_name);
    detail = (TextView) findViewById(R.id.hero_detail);
    avatar = (ImageView) findViewById(R.id.hero_avatar);
  }

  public TextView getTitle() {
    return title;
  }

  public TextView getDetail() {
    return detail;
  }

  public ImageView getAvatar() {
    return avatar;
  }

  @Override
  public View getView() {
    return this;
  }

}
