package dmutils.com.dmutils.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Show view with animation from bottom to top
 * Usage of code
 * DMTranslateViewAnimation.upView(mActivity, mView, 3000, () -> {})
 */

public final class DMUtilTranslateViewAnimation {

    public static void upView(final Activity activity, final View view, final int duration, final DMUtilIAnimationEndListener listener) {
        if (view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
            final DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            final int height = dm.heightPixels;

            final ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, height, 0);
            animator.setDuration(duration);

            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(final Animator animation) {
                    super.onAnimationEnd(animation);
                    listener.onAnimationEnd();
                }
            });

            animator.start();
        }
    }
}
