package com.zjhl.pad.view.views;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import com.zjhl.pad.view.R;

/**
 * @说明： 自定义带删除按钮的EditText<br>
 * 提供焦点及输入监听器：ClearEditFouseChangeListener
 * 
 */
@SuppressLint("AppCompatCustomView")
public class ClearEditText extends EditText implements TextWatcher {
	/**
	 * 删除按钮的引用
	 */
	private Drawable mClearDrawable;

	public ClearEditText(Context context) {
		this(context, null);
		init();
	}

	public ClearEditText(Context context, AttributeSet attrs) {
		// 这里构造方法也很重要，不加这个很多属性不能再XML里面定义
		this(context, attrs, android.R.attr.editTextStyle);
		init();
	}

	public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		// 获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
		mClearDrawable = getCompoundDrawables()[2];
		if (mClearDrawable == null) {
			// throw new NullPointerException("You can add drawableRight
			// attribute in XML");
			mClearDrawable = getResources().getDrawable(R.drawable.edit_delete);
		}

		mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
		// 默认设置隐藏图标
		setClearIconVisible(false);
		// 设置焦点改变的监听
		setOnFocusChangeListener(new OnFocusChangeListener() {
			/**
			 * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
			 * 
			 * 通过isFocus()获取的结果有延时，会出现相反的情况，所以这里用全局变量来记录
			 */
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				isHasFouce = hasFocus;
				refreshClearButtonState();
			}
		});
		// 设置输入框里面内容发生改变的监听
		addTextChangedListener(this);
	}

	/**
	 * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件 当我们按下的位置 在 EditText的宽度 -
	 * 图标到控件右边的间距 - 图标的宽度 和 EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (getCompoundDrawables()[2] != null) {

				boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight()) && (event.getX() < ((getWidth() - getPaddingRight())));

				if (touchable) {
					// 里面写上自己想做的事情，也就是DrawableRight的触发事件
					this.setText("");
				}
			}
		}

		return super.onTouchEvent(event);
	}

	// 后面的代码无需更改，只需要粘贴进去即可，如果有不需要的可以删除，当然不删除也不会出错。
	/**
	 * 当输入框里面内容发生变化的时候回调的方法
	 */
	@Override
	public void onTextChanged(CharSequence s, int start, int count, int after) {
		refreshClearButtonState();
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	private ClearEditFouseChangeListener listener;

	public void setFouseListener(ClearEditFouseChangeListener listener) {
		this.listener = listener;
	}

	/**
	 * 设置晃动动画
	 */
	public void setShakeAnimation() {
		this.setAnimation(shakeAnimation(5));
	}

	/**
	 * 晃动动画
	 * 
	 * @param counts
	 *            1秒钟晃动多少下
	 * @return
	 */
	public static Animation shakeAnimation(int counts) {
		Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
		translateAnimation.setInterpolator(new CycleInterpolator(counts));
		translateAnimation.setDuration(1000);
		return translateAnimation;
	}

	private boolean isHasFouce = false;

	private void refreshClearButtonState() {
		if (isHasFouce) {
			String text = getText().toString();
			setClearIconVisible(!TextUtils.isEmpty(text));
		} else {
			setClearIconVisible(false);
		}
		// 给额外的焦点监听提供结果
		if (listener != null)
			listener.getChange(isHasFouce, getText().length() <= 0);
	}

	/**
	 * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
	 * 
	 * @param visible
	 */
	protected void setClearIconVisible(boolean visible) {
		// 如果你想让它一直显示DrawableRight的图标，并且还需要让它触发事件，直接把null改成mClearDrawable即可
		Drawable right = visible ? mClearDrawable : null;
		setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
	}

	/**
	 * 编辑器的焦点监听器
	 * 
	 * @author Wall
	 *
	 */
	public interface ClearEditFouseChangeListener {
		/**
		 * 获取变化信息
		 * 
		 * @param hasFouse
		 *            是否获取焦点
		 * @param isEmpty
		 *            是否内容为空
		 * @author Wall
		 */
		public void getChange(boolean hasFouse, boolean isEmpty);
	}
}