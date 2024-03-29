package com.zjhl.pad.app.utils;

import java.util.Hashtable;

import android.util.Log;

/**
 * The class for print log
 *
 * @author kesenhoo
 */
public class MyLogger {
    private final static boolean logFlag = true;

    public final static String TAG = "[AppName]";
    private final static int logLevel = Log.VERBOSE;
    private static Hashtable<String, MyLogger> sLoggerTable = new Hashtable<String, MyLogger>();
    private String mClassName;
    // 不同开发人员的日志使用对象
    private static MyLogger plog;
    private static MyLogger klog;
    // 开发人员的名字
    private static final String PLUTO = "@pluto@ ";
    private static final String KESEN = "@kesen@ ";

    private MyLogger(String name) {
        mClassName = name;
    }

    /**
     * @param className
     * @return
     */
    @SuppressWarnings("unused")
    private static MyLogger getLogger(String className) {
        MyLogger classLogger = (MyLogger) sLoggerTable.get(className);
        if (classLogger == null) {
            classLogger = new MyLogger(className);
            sLoggerTable.put(className, classLogger);
        }
        return classLogger;
    }

    /**
     * Purpose:Mark user one
     *
     * @return
     */
    public static MyLogger kLog() {
        if (klog == null) {
            klog = new MyLogger(KESEN);
        }
        return klog;
    }

    /**
     * Purpose:Mark user two
     *
     * @return
     */
    public static MyLogger pLog() {
        if (plog == null) {
            plog = new MyLogger(PLUTO);
        }
        return plog;
    }

    /**
     * Get The Current Function Name
     *
     * @return
     */
    private String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(this.getClass().getName())) {
                continue;
            }
            return mClassName + "[ " + Thread.currentThread().getName() + ": " + st.getFileName() + ":" + st.getLineNumber() + " " + st.getMethodName() + " ]";
        }
        return null;
    }

    /**
     * The Log Level:i
     *
     * @param str
     */
    public void i(Object str) {
        if (logFlag) {
            if (logLevel <= Log.INFO) {
                String name = getFunctionName();
                if (name != null) {
                    Log.i(TAG, name + " - " + str);
                } else {
                    Log.i(TAG, str.toString());
                }
            }
        }

    }

    /**
     * The Log Level:d
     *
     * @param str
     */
    public void d(Object str) {
        if (logFlag) {
            if (logLevel <= Log.DEBUG) {
                String name = getFunctionName();
                if (name != null) {
                    Log.d(TAG, name + " - " + str);
                } else {
                    Log.d(TAG, str.toString());
                }
            }
        }
    }

    /**
     * The Log Level:V
     *
     * @param str
     */
    public void v(Object str) {
        if (logFlag) {
            if (logLevel <= Log.VERBOSE) {
                String name = getFunctionName();
                if (name != null) {
                    Log.v(TAG, name + " - " + str);
                } else {
                    Log.v(TAG, str.toString());
                }
            }
        }
    }

    /**
     * The Log Level:w
     *
     * @param str
     */
    public void w(Object str) {
        if (logFlag) {
            if (logLevel <= Log.WARN) {
                String name = getFunctionName();
                if (name != null) {
                    Log.w(TAG, name + " - " + str);
                } else {
                    Log.w(TAG, str.toString());
                }
            }
        }
    }

    /**
     * The Log Level:e
     *
     * @param str
     */
    public void e(Object str) {
        if (logFlag) {
            if (logLevel <= Log.ERROR) {
                String name = getFunctionName();
                if (name != null) {
                    Log.e(TAG, name + " - " + str);
                } else {
                    Log.e(TAG, str.toString());
                }
            }
        }
    }

    /**
     * The Log Level:e
     *
     * @param ex
     */
    public void e(Exception ex) {
        if (logFlag) {
            if (logLevel <= Log.ERROR) {
                Log.e(TAG, "error", ex);
            }
        }
    }

    /**
     * The Log Level:e
     *
     * @param log
     * @param tr
     */
    public void e(String log, Throwable tr) {
        if (logFlag) {
            String line = getFunctionName();
            Log.e(TAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + line + ":] " + log + "\n", tr);
        }
    }
}