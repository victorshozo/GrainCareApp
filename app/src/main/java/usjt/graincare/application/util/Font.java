package usjt.graincare.application.util;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import static android.graphics.Typeface.BOLD;

public class Font {

    private static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    private final static String FONTS_PATH = "fonts/";

    private static Typeface museoSans;
    private static Typeface museoSansBold;

    public static Typeface museoSans(Context context, int...textStyle) {
        if (museoSans == null) {
            museoSans = Typeface.createFromAsset(context.getAssets(), FONTS_PATH + "museo-sans.ttf");
        }
        if (museoSansBold == null) {
            museoSansBold = Typeface.createFromAsset(context.getAssets(), FONTS_PATH + "museo-sans-bold.ttf");
        }
        if (textStyle.length != 0 && textStyle[0] == BOLD) {
            return museoSansBold;
        }
        return museoSans;
    }

    public static void applyCustomFont(TextView textView, Context context, AttributeSet attrs) {
        String style = "0x0";
        try {
            style = attrs.getAttributeValue(ANDROID_SCHEMA, "textStyle");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int textStyle;
        if (style != null && style.equals("0x1")) {
            textStyle = Typeface.BOLD;
        }else{
            textStyle = Typeface.NORMAL;
        }
        textView.setTypeface(Font.museoSans(context, textStyle));
    }


}
