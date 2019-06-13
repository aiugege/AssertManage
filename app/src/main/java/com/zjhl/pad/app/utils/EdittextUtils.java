package com.zjhl.pad.app.utils;

import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;

/**
 * 将光标移至Edittext的最后
 * 
 * @author lzl
 * @version 1.0,
 */
public class EdittextUtils {
	
	public static void setSelectionToEnd(EditText editText) {
		CharSequence text = editText.getText();
		if (text instanceof Spannable) {
			Spannable spanText = (Spannable) text;
			Selection.setSelection(spanText, text.length());
		}
	}
	
	
	/**
	 * 设置密码是否可见
	 * @author lzl
	 * @param flag Activity中进行密码是否可见的标记， true为不可见，false可见
	 * @param edit 密码输入框
	 * @return 返回改变后的状态 
	 */
	public static boolean setVisiblePassword(boolean flag , EditText edit){
		if (!flag) {//
			edit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
		} else {
			edit.setTransformationMethod(PasswordTransformationMethod.getInstance());
		}
		flag = !flag;//状态取反
		setSelectionToEnd(edit);
		return flag;
	}
	
}
