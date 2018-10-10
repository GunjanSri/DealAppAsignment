package com.nagarro.dealapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.util.AttributeSet;

public class ToolBar extends Toolbar {

    private String font;

    public ToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            final TypedArray themeAttributes = context.obtainStyledAttributes(
                    attrs,
                    new int[]{R.attr.colorPrimary, R.attr.colorSecondary, R.attr.toolBarType, android.R.attr.title}
            );
            final int colorPrimary = themeAttributes.getColor(0,
                    ContextCompat.getColor(context, R.color.colorPrimary)
            );
            final int colorSecondary = themeAttributes.getColor(1,
                    ContextCompat.getColor(context, R.color.colorPrimaryDark)
            );

            final int toolBarType = themeAttributes.getInteger(2, 0);
            switch (toolBarType) {
                case 1:
                    setBackground(null);
                    break;
                case 2:
                    final int[] gradientColors = new int[]{
                            ContextCompat.getColor(context, android.R.color.transparent),colorSecondary};
                    setGradientColors(gradientColors);
                    break;
                default:
                    final ShapeDrawable background = new ShapeDrawable(new RectShape());
                    background.getPaint().setColor(colorPrimary);
                    setBackground(background);
                    break;
            }

            setTitle(themeAttributes.getResourceId(3, R.string.empty));
            themeAttributes.recycle();
        }
    }

    private void setGradientColors(int[] gradientColors){

        final GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColors(gradientColors);
        gradientDrawable.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
        setBackground(gradientDrawable);
    }

    @Override
    public void setTitle(CharSequence title) {
        CharSequence titleName = "";
        if(title != null){
            titleName = title;
        }
        final SpannableString styledFontString = new SpannableString(titleName);
        styledFontString.setSpan(
                new TitleSpan(getContext(), font),
                0,
                styledFontString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        super.setTitle(styledFontString);
    }

    @Override
    public void setTitle(int resId) {
        final String title = getContext().getResources().getString(resId);
        setTitle(title);
    }

    private static class TitleSpan extends MetricAffectingSpan {

        //private final Typeface mTypeface;

        public TitleSpan(Context context, String fontPath) {
            // mTypeface = FontHelper.get(context, fontPath);
        }

        @Override
        public void updateMeasureState(TextPaint p) {
            // p.setTypeface(mTypeface);
            p.setFlags(p.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }

        @Override
        public void updateDrawState(TextPaint tp) {
            // tp.setTypeface(mTypeface);
            tp.setFlags(tp.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
    }
}
