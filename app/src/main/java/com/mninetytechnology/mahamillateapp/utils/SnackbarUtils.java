package com.mninetytechnology.mahamillateapp.utils;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class SnackbarUtils {
    private final int BACKGROUND_COLOR;
    private final int TEXT_COLOR;
    private final int BUTTON_COLOR;
    private final String TEXT;


    public SnackbarUtils(String aText, int aBgColor, int aTextColor, int aButtonColor){
        this.TEXT = aText;
        this.BACKGROUND_COLOR = aBgColor;
        this.TEXT_COLOR = aTextColor;
        this.BUTTON_COLOR = aButtonColor;
    }

    public Snackbar snackieBar(Activity activity){
        Snackbar snackie = Snackbar.make(activity.findViewById(android.R.id.content), TEXT, Snackbar.LENGTH_LONG);
        View snackView = snackie.getView();
        TextView snackViewText = (TextView) snackView.findViewById(com.google.android.material.R.id.snackbar_text);
        Button snackViewButton = (Button) snackView.findViewById(com.google.android.material.R.id.snackbar_action);
        snackView.setBackgroundColor(BACKGROUND_COLOR);
        snackViewText.setTextColor(TEXT_COLOR);
        snackViewButton.setTextColor(BUTTON_COLOR);
        return snackie;
    }
}
