package com.zjhl.pad.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* File: DataVerifyUtils.java
* Author: DELL
* Version: V1.0
* Create: 2018/4/4 18:24
* Changes (from 2018/4/4)
*/
public class DataVerifyUtils {

	/**
	 * @Title: verifyUserName
	 * @Description: TODO 验证用户名仅允许输入4-16位字符， 包含中文、英文数字和"_"
	 * @author 刘子龙
	 * @param @param
	 *            username
	 * @param @return
	 * @return boolean
	 * @throws @date
	 *             2015-3-24 下午4:12:57
	 */
	// public static boolean verifyUserName(String username){
	// if(username != null && username.length() == 11){
	// Pattern pattern = Pattern.compile("/^[0-9a-zA-Z\u4e00-\u9fa5_-]*$/");
	// Matcher matcher = pattern.matcher(username);
	// return matcher.matches();
	// }
	// return false;
	// }
	/**
	 * 检查字符串是否为空
	 * 
	 * @param str
	 * @return boolean
	 * @author Wall
	 */
	public static boolean verifyStringNoEmpty(String str) {
		return str != null && !str.equals("");
	}

	/**
	 * 检查字符串数组是否为空
	 * 
	 * @return boolean
	 * @author 刘子龙
	 */
	public static boolean verifyStringNoEmpty(String... str) {
		for (String s : str) {
			if (!verifyStringNoEmpty(s))
				return false;
		}
		return true;
	}

	/**
	 * @Title: verifyUserName
	 * @Description: TODO 验证用户长度
	 * @author 刘子龙
	 * @param @param
	 *            username
	 * @param @return
	 * @return boolean
	 * @throws @date
	 *             2015-3-24 下午4:26:39
	 */
	/**
	 * 正则表达式：验证身份证
	 */
	public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

	public static boolean verifyUserName(String username) {
		if (username != null) {
			if (username.length() >= 4 && username.length() <= 16) {
				return true;
			}
		}
		return false;
	}

	/**
	 * TODO 验证用户输入的手机号:11位数字组成
	 * 
	 * @author 刘子龙
	 * @date 2013-9-26 上午8:52:01     M
	 *            需要验证的手机号
	 * @return true 验证通过 ； false 验证未通过
	 */
	public static boolean verifyTelNum(String telNum) {
		if (telNum != null && telNum.length() == 11) {
			Pattern pattern = Pattern.compile("[0-9]{11}");
			Matcher matcher = pattern.matcher(telNum);
			return matcher.matches();
		}
		return false;
	}

	/**
	 * 验证用户输入的邮箱:11位数字组成
	 * 
	 * @return
	 */

	public static boolean emailValidation(String email) {
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		return email.matches(regex);
	}

	/**
	 * 验证用户输入的密码：区分大小写，长度满足8-16位
	 * 
	 * @author 刘子龙
	 * @date 2013-9-26 上午8:48:02
	 * @param password
	 *            需要验证的密码
	 * @return true表示通过，false表示未通过
	 */
	public static boolean verifyPassword(String password) {
		if (password != null) {
			if (password.length() >= 8 && password.length() <= 16) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 验证两次输入的密码是否一致
	 * 
	 * @author 刘子龙
	 * @date 2013-9-26 上午9:55:44
	 * @param newPwd
	 * @param newPwdTwo
	 * @return true表示两次输入一致，否则两次输入不一致
	 */
	public static boolean verifyTwicePassword(String newPwd, String newPwdTwo) {
		if (newPwd != null && newPwdTwo != null && newPwd.equals(newPwdTwo)) {
			return true;
		}
		return false;
	}

	/**
	 * 校验身份证 刘子龙
	 * 
	 * @param idCard
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isIDCard(String idCard) {
		return Pattern.matches(REGEX_ID_CARD, idCard);
	}

}
