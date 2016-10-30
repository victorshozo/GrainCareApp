package usjt.graincare.application;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import usjt.graincare.application.util.Font;

public class GrainCareEditText extends EditText {

    private static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public GrainCareEditText(Context context) {
        super(context);
    }

    public GrainCareEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context, attrs);
    }

    public GrainCareEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        String style = "0x0";
        try {
            style = attrs.getAttributeValue(ANDROID_SCHEMA, "textStyle");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int textStyle;
        if (style != null && style.equals("0x1")) {
            textStyle = Typeface.BOLD;
        } else {
            textStyle = Typeface.NORMAL;
        }
        this.setTypeface(Font.museoSans(context, textStyle));
    }
}