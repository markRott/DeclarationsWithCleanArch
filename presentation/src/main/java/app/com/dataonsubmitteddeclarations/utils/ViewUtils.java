package app.com.dataonsubmitteddeclarations.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class ViewUtils {

    private ViewUtils() {
    }

    public static void hideKeyboardFrom(Context context, View view) {
        if (context != null && view != null) {
            InputMethodManager imm =
                    (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
