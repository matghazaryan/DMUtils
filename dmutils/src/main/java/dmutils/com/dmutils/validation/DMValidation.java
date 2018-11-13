package dmutils.com.dmutils.validation;

import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

public class DMValidation {

    public static boolean isEmptyFields(final String emptyText, final EditText... editTexts) {
        boolean isHaveError = false;
        EditText editTextForFocus = null;
        for (final EditText editText : editTexts) {
            if (TextUtils.isEmpty(editText.getText().toString())) {
                editText.setError(emptyText);
                isHaveError = true;

                if (editTextForFocus == null) {
                    editTextForFocus = editText;
                    if (editTextForFocus.isFocusableInTouchMode()) {
                        editText.clearFocus();
                        editTextForFocus.requestFocus();
                    } else {
                        editTextForFocus.setFocusableInTouchMode(true);
                        editTextForFocus.requestFocus();
                        editTextForFocus.setFocusableInTouchMode(false);
                        editTextForFocus.setInputType(InputType.TYPE_NULL);
                    }
                }
            }
        }

        return isHaveError;
    }

    public static boolean isMatchFields(final String errorMessage, final EditText editText1, final EditText editText2) {
        boolean isHaveError = false;

        if (!TextUtils.equals(editText1.getText(), editText2.getText())) {

            editText2.setError(errorMessage);
            isHaveError = true;

            if (editText2.isFocusableInTouchMode()) {
                editText2.clearFocus();
                editText2.requestFocus();
            } else {
                editText2.setFocusableInTouchMode(true);
                editText2.requestFocus();
                editText2.setFocusableInTouchMode(false);
                editText2.setInputType(InputType.TYPE_NULL);
            }
        }

        return isHaveError;
    }

    public static boolean isValidEmail(final String errorMessage, final EditText editText) {
        boolean isHaveError = false;
        final CharSequence target = editText.getText();

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()) {

            editText.setError(errorMessage);
            isHaveError = true;

            if (editText.isFocusableInTouchMode()) {
                editText.clearFocus();
                editText.requestFocus();
            } else {
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
                editText.setFocusableInTouchMode(false);
                editText.setInputType(InputType.TYPE_NULL);
            }
        }

        return isHaveError;
    }

    public static boolean isValidUrl(String potentialUrl) {
        return Patterns.WEB_URL.matcher(potentialUrl).matches();
    }
}
