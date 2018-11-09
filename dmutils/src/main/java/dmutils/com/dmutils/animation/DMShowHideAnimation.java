package dmutils.com.dmutils.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;


public class DMShowHideAnimation {

    private int mViewHeight;
    private ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener;
    private View mView;
    private int mShowDuration;
    private int mHideDuration;
    private int mViewVisibleStatus;

    public DMShowHideAnimation initValuesForAnimation(final View view, final int showDuration, final int hideDuration) {
        this.mShowDuration = showDuration;
        this.mHideDuration = hideDuration;
        this.mView = view;

        mViewVisibleStatus = view.getVisibility();
        mView.setVisibility(View.VISIBLE);

        mOnGlobalLayoutListener = () -> {
            mViewHeight = mView.getMeasuredHeight();
            mView.setVisibility(mViewVisibleStatus);
            mView.getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayoutListener);
        };
        mView.getViewTreeObserver().addOnGlobalLayoutListener(mOnGlobalLayoutListener);

        return this;
    }

    public void show() {
        if (mView.getVisibility() == View.GONE || mView.getVisibility() == View.INVISIBLE) {
            final ValueAnimator animatorContentShow = ObjectAnimator.ofInt(0, mViewHeight);
            animatorContentShow.addUpdateListener(valueAnimator -> {
                final int val = (Integer) valueAnimator.getAnimatedValue();
                final ViewGroup.LayoutParams layoutParams = mView.getLayoutParams();
                layoutParams.height = val;
                mView.setLayoutParams(layoutParams);
            });

            animatorContentShow.setDuration(mShowDuration);
            animatorContentShow.start();

            mView.setVisibility(View.VISIBLE);
        }
    }

    public void hide() {
        if (mView.getVisibility() == View.VISIBLE) {
            final ValueAnimator animatorContentHide = ObjectAnimator.ofInt(mViewHeight, 0);
            animatorContentHide.addUpdateListener(valueAnimator -> {
                final int val = (Integer) valueAnimator.getAnimatedValue();
                final ViewGroup.LayoutParams layoutParams = mView.getLayoutParams();
                layoutParams.height = val;
                mView.setLayoutParams(layoutParams);
            });

            animatorContentHide.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(final Animator animation) {
                    super.onAnimationEnd(animation);
                    mView.setVisibility(View.GONE);
                    final ViewGroup.LayoutParams layoutParams = mView.getLayoutParams();
                    layoutParams.height = mViewHeight;
                    mView.setLayoutParams(layoutParams);
                }
            });

            animatorContentHide.setDuration(mHideDuration);
            animatorContentHide.start();
        }
    }

    public static void hide(final View view, final int duration, final IAnimationEndListener listener) {
        if (view.getVisibility() == View.VISIBLE) {
            final int height = view.getMeasuredHeight();
            final ValueAnimator animatorContentHide = ObjectAnimator.ofInt(height, 0);
            animatorContentHide.addUpdateListener(valueAnimator -> {
                final int val = (Integer) valueAnimator.getAnimatedValue();
                final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = val;
                view.setLayoutParams(layoutParams);
            });

            animatorContentHide.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(final Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setVisibility(View.GONE);
                    final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    layoutParams.height = height;
                    view.setLayoutParams(layoutParams);

                    listener.onAnimationEnd();
                }
            });

            animatorContentHide.setDuration(duration);
            animatorContentHide.start();
        }
    }
}
