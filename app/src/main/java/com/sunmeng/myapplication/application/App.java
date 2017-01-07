package com.sunmeng.myapplication.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;


public class App extends Application {

    //两个资源，生命周期贯穿整个APP的创建到销毁
    private static Context content;
    private static App myApplication;

    //定义sharepreferences保存全局变量
    private static String SETTING_INFO = "setting_info";
    private static SharedPreferences settingPreference;
    private static SharedPreferences.Editor settingEditor;

    @Override
    public void onCreate(){
        super.onCreate();
        content = getApplicationContext();
        myApplication = new App();

        if (getApplicationInfo().packageName
                .equals(getCurProcessName(getApplicationContext()))
                || "io.rong.push"
                .equals(getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第一步 初始化
             */
            RongIM.init(this);
            connect("B++VZ5ZUZnFhb702CFCZrwToNJeJ6EysrYcLW5ruCDA/yPhe5GzM0HRvZu44+9UjRBtP5mNZkBvTrWD/tSjKpQ==");
        }

    }

    public static App getAppInstance(){
        return myApplication;
    }

    public static Context getContent(){
        return content ;
    }

    public static void saveToken(String token) {
        settingEditor = settingPreference.edit();
        settingEditor.putString("token", token);
        settingEditor.apply();
    }

    public static String getToken(){
        return settingPreference.getString("token", "");
    }

    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    private void connect(String token) {

        if (getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {

                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.d("LoginActivity", "--onSuccess" + userid);


                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.e("rerror",errorCode.toString());
                }
            });
        }
    }

}
