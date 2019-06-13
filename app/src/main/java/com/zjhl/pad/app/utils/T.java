package com.zjhl.pad.app.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Toast统一管理 */
@SuppressLint("ShowToast")
public class T {

  private static T instanse = null;
  public static boolean isShow = true;

  public static T getInstanse(Context context) {
    if (instanse == null) {
      synchronized (T.class) {
        if (instanse == null) {
          instanse = new T(context);
        }
      }
    }
    return instanse;
  }

  private Toast toast;

  private Context applicationContext;

  private T(Context context) {
    applicationContext = context.getApplicationContext();
  }

  private Toast createToast(CharSequence message, int duration) {
    if (toast == null) {
      toast = Toast.makeText(applicationContext, message, duration);
    } else {
      toast.setText(message);
    }
    toast.setGravity(Gravity.CENTER, 0, 0);
    return toast;
  }

  private Toast createToast(int message, int duration) {
    if (toast == null) {
      toast = Toast.makeText(applicationContext, message, duration);
    } else {
      toast.setText(message);
    }
    toast.setGravity(Gravity.CENTER, 0, 0);
    return toast;
  }

  /**
   * 自定义显示Toast时间
   *
   * @param message
   * @param duration
   */
  public void show(CharSequence message, int duration) {
    if (isShow) {
      createToast(message, duration).show();
    }
  }

  /**
   * 自定义显示Toast时间
   *
   * @param message
   * @param duration
   */
  public void show(int message, int duration) {
    if (isShow) {
      createToast(message, duration).show();
    }
  }

  /**
   * 长时间显示Toast
   *
   * @param message
   */
  public void showLong(CharSequence message) {
    if (isShow) {
      createToast(message, Toast.LENGTH_LONG).show();
    }
  }

  /**
   * 长时间显示Toast
   *
   * @param message
   */
  public void showLong(int message) {
    if (isShow) {
      createToast(message, Toast.LENGTH_LONG).show();
    }
  }

  /**
   * 短时间显示Toast
   *
   * @param message
   */
  public void showShort(CharSequence message) {
    if (isShow) {
      createToast(message, Toast.LENGTH_SHORT).show();
    }
  }

  /**
   * 短时间显示Toast
   *
   * @param message
   */
  public void showShort(int message) {
    if (isShow) {
      createToast(message, Toast.LENGTH_SHORT).show();
    }
  }
}
