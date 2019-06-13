package com.zjhl.pad.utils;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.atguigu.android.R;


/**
 *
 * @author Wall
 *
 */
public class PermissionsManager {
	public static final String EXCEPTION_NO_PERMISSION = "EXCEPTION_NO_PERMISSION";
	private static final int REQUEST_PERMISSION = 121;
	private Activity context;
	private PermissionListener perListener;
	private String[] permission;

	public PermissionsManager(Activity context, PermissionListener listener) {
		this.context = context;
		this.perListener = listener;
	}

	/**
	 *
	 * @author Wall
	 */
	public boolean checkPermission(String... permission) {
		this.permission = permission;
		boolean isHadAll = true;
		for (String p : this.permission) {
			if (ContextCompat.checkSelfPermission(context, p) != PackageManager.PERMISSION_GRANTED) {
				isHadAll = false;
				break;
			}
		}
		if (!isHadAll) {
			try {
				ActivityCompat.requestPermissions(context, permission, REQUEST_PERMISSION);
				return false;
			} catch (Exception e) {
				context.finish();
			}
		}
		return true;
	}

	/**
	 *
	 * @param requestcode
	 * @param permissions
	 * @param grantResults
	 * @author Wall
	 */
	public void permissionsResult(int requestcode, String[] permissions, int[] grantResults) {
		if (REQUEST_PERMISSION == requestcode) {
			boolean isHadAll = true;
			for (int grant : grantResults) {
				if (grant != PackageManager.PERMISSION_GRANTED)
					isHadAll = false;
			}
			if (isHadAll) {
				if (perListener != null)
					perListener.getRequestPermissionCallBack(permissions, true);
			} else {
				if (perListener != null)
					perListener.getRequestPermissionCallBack(permissions, false);
			}
		}
	}

	/**
	 *
	 * @param permission
	 * @return
	 * @author Wall
	 */
	public boolean checkShouldShowPermission(String... permission) {
		this.permission = permission;
		boolean isNeedShow = false;
		for (String p : this.permission) {
			/**
			 *
			 * 3.
			 */
			if (!ActivityCompat.shouldShowRequestPermissionRationale(context, p))
				isNeedShow = true;
		}
		return isNeedShow;
	}

	/**
	 *
	 * @author Wall
	 */
	public void showMessageOKCancel() {
		new AlertDialog.Builder(context).setMessage(context.getResources().getString(R.string.nopermission_tips)).setPositiveButton("OK", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				ActivityCompat.requestPermissions((Activity) context, permission, REQUEST_PERMISSION);
			}
		}).setNegativeButton("Cancel", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				context.finish();
			}
		}).create().show();
	}

	/**
	 *
	 * @author Wall
	 *
	 */
	public interface PermissionListener {
		/**
		 *
		 * @param isAgree
		 * @author Wall
		 */
		public void getRequestPermissionCallBack(String[] permissions, boolean isAgree);
	}
}
