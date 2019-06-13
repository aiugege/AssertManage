package com.zjhl.pad.utils;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.atguigu.android.R;


public class PermissionDialog extends AlertDialog implements View.OnClickListener {
	private TextView title_text;
	private TextView message_text;
	private TextView cancel_text;
	private TextView ok_text;
	private PermissionCheckListener listener;

	public PermissionDialog(Context context, PermissionCheckListener listener) {
		super(context, R.style.MyLoadDialog);
		this.listener = listener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_permission);
		title_text = (TextView) findViewById(R.id.permission_titel);
		message_text = (TextView) findViewById(R.id.permission_message);
		cancel_text = (TextView) findViewById(R.id.cancel);
		ok_text = (TextView) findViewById(R.id.ok);
		cancel_text.setOnClickListener(this);
		ok_text.setOnClickListener(this);
		title_text.setText(R.string.nopermission);
		message_text.setText(R.string.nopermission_tips);
	}

	public void setTitle(String title) {
		title_text.setText(title);
	}

	public void setMessage(String message) {
		message_text.setText(message);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cancel:
			if (listener != null)
				listener.userChoosed(false);
			break;
		case R.id.ok:
			if (listener != null)
				listener.userChoosed(true);
			break;
		}
		dismiss();
	}

	@Override
	public void show() {
		if (!isShowing())
			super.show();
	}

	@Override
	public void dismiss() {
		if (isShowing())
			super.dismiss();
	}

	/**
	 * 用户权限选择监听
	 * 
	 * @author Wall
	 *
	 */
	public interface PermissionCheckListener {
		public void userChoosed(boolean isChooseOk);
	}
}