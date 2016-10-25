package usjt.graincare.application;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import usjt.graincare.application.util.Font;

public class GrainCareTextView extends TextView {

    public GrainCareTextView(Context context) {
        super(context);
    }

    public GrainCareTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Font.applyCustomFont(this, context, attrs);
    }

    public GrainCareTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Font.applyCustomFont(this, context, attrs);
    }
}
