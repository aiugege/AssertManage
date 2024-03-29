package com.zjhl.pad.app.utils;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;

/**
 * @desc: SkxDrawableHelper
 * @version: v1.0
 * @packagename: com.zjhl.pad.app.utils
 * @author: pluto
 * @create: 2018/6/13 11:59
 * @projectname: nnkj
 **/
public class SkxDrawableHelper {
    /**
     * 对目标Drawable 进行着色
     *
     * @param drawable 目标Drawable
     * @param color    着色的颜色值
     * @return 着色处理后的Drawable
     */
    public static Drawable tintDrawable(@NonNull Drawable drawable, int color) {
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrappedDrawable, color);
        return wrappedDrawable;
    }
    /**
     * 对目标Drawable 进行着色
     *
     * @param drawable 目标Drawable
     * @param colors   着色值
     * @return 着色处理后的Drawable
     */
    public static Drawable tintListDrawable(@NonNull Drawable drawable, ColorStateList colors) {
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        // 进行着色
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }
}
