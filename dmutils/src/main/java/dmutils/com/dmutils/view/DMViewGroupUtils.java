package dmutils.com.dmutils.view;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class DMViewGroupUtils {

    public static ViewGroup getParent(final View view) {
        return (ViewGroup) view.getParent();
    }

    public static void removeView(final View view) {
        if (view != null) {
            final ViewGroup parent = getParent(view);
            if (parent != null) {
                parent.removeView(view);
            }
        }
    }

    public static void replaceView(final View currentView, final View newView) {
        final ViewGroup parent = getParent(currentView);
        if (parent == null) {
            return;
        }
        final int index = parent.indexOfChild(currentView);
        removeView(currentView);
        removeView(newView);
        parent.addView(newView, index);
    }

    public static void setMargins(final View v, final int l, final int t, final int r, final int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            final ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    public static void disableView(final EditText editText, final String text) {
        final boolean isEmpty = TextUtils.isEmpty(text);
        editText.setFocusable(isEmpty);
        editText.setFocusableInTouchMode(isEmpty);
        editText.setCursorVisible(isEmpty);
        editText.setLongClickable(false);
    }

    public static void disableView(final EditText editText) {
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        editText.setCursorVisible(false);
        editText.setOnClickListener(null);
        editText.setLongClickable(false);
    }

    public static void enableView(final EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.setCursorVisible(true);
        editText.setLongClickable(true);
    }

    public static void disableFocusable(final EditText editText) {
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        editText.setCursorVisible(false);
    }
}
