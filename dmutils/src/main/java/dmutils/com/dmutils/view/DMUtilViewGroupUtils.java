package dmutils.com.dmutils.view;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public final class DMUtilViewGroupUtils {

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

    public static void setViewMargins(final View v, final int l, final int t, final int r, final int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            final ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    public static void disableEditText(final EditText editText, final String text) {
        final boolean isEmpty = TextUtils.isEmpty(text);
        editText.setFocusable(isEmpty);
        editText.setFocusableInTouchMode(isEmpty);
        editText.setCursorVisible(isEmpty);
        editText.setLongClickable(false);
    }

    public static void disableEditText(final EditText editText) {
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        editText.setCursorVisible(false);
        editText.setOnClickListener(null);
        editText.setLongClickable(false);
    }

    public static void enableEditText(final EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.setCursorVisible(true);
        editText.setLongClickable(true);
    }

    public static void disableFocusableOfEdittext(final EditText editText) {
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        editText.setCursorVisible(false);
    }

    public static void setRoundedBackground(final View view, final int fillColor, final int strokeColor, final int corner) {
        final RoundRectShape rs = new RoundRectShape(new float[]{corner, corner, corner, corner, corner, corner, corner, corner}, null, null);
        final ShapeDrawable sd = new DMUtilCustomShapeDrawable(rs, fillColor, strokeColor, 0);
        sd.setPadding(corner, corner, corner, corner);
        view.setBackgroundDrawable(sd);
    }
}
