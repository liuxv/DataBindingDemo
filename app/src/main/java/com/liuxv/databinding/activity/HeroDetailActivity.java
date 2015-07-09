package com.liuxv.databinding.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import com.liuxv.databinding.R;
import com.liuxv.databinding.databinding.HeroDetailActivityBinding;
import com.liuxv.databinding.model.ObservableHeroModel;
import com.liuxv.databinding.mvc.model.HeroModel;
import com.liuxv.databinding.utils.DataUtils;

/**
 *
 * @author liuxu@gmail.com (Liu Xu)
 */
public class HeroDetailActivity extends AppCompatActivity {

  private static HeroModel heroModel;

  private HeroDetailActivityBinding binding;
  private Interpolator interpolator;

  public static void launch(Context context, HeroModel heroModel) {
    final Intent intent = new Intent(context, HeroDetailActivity.class);
    HeroDetailActivity.heroModel = heroModel;
    if (!(context instanceof Activity)) {
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
    context.startActivity(intent);
  }

  public static void launch(Context context, ObservableHeroModel heroModel) {
    final Intent intent = new Intent(context, HeroDetailActivity.class);
    HeroDetailActivity.heroModel = DataUtils.convertToHeroModel(heroModel);
    if (!(context instanceof Activity)) {
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.hero_detail_activity);
    binding.setHero(heroModel);
    interpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.accelerate_cubic);
    initToolbar();
    initViews();
  }

  private void initToolbar() {
    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.explore_back);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void initViews() {
    binding.fabFavorite.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        binding.fabFavorite.setSelected(!binding.fabFavorite.isSelected());
      }
    });

    binding.collapsingToolbar.setTitle(heroModel.getName());
    binding.backdrop.setImageResource(heroModel.getAvatarRes());
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void hideRefreshButton(boolean needFinish) {
    ViewPropertyAnimator animator =
        binding.fabFavorite.animate().scaleX(0).scaleY(0).setInterpolator(interpolator);
    if (needFinish) {
      animator.setListener(new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
          finish();
          super.onAnimationEnd(animation);
        }
      });
    }
  }

  @Override
  public void onBackPressed() {
    hideRefreshButton(true);
  }
}
