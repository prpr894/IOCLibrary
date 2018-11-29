package com.prpr894.baseioclibrary.ioc;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by prpr894 on 2018/11/22 0022
 * Description:
 */
public class ViewUtils {

    //Activity注解
    public static void inject(Activity activity) {
        inject(new ViewFinder(activity), activity);

    }

    //View注解
    public static void inject(View view) {
        inject(new ViewFinder(view), view);
    }

    //Fragment注解等
    public static void inject(View view, Object object) {
        inject(new ViewFinder(view), object);
    }

    /**
     * Description: 兼容上面三个方法
     *
     * @param viewFinder View.findViewById的辅助类
     * @param object     反射需要执行的类
     */
    public static void inject(ViewFinder viewFinder, Object object) {
        injectFiled(viewFinder, object);
        injectEvent(viewFinder, object);
    }

    private static void injectFiled(ViewFinder viewFinder, Object object) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ViewBindId viewBindId = field.getAnnotation(ViewBindId.class);
            if (viewBindId != null) {
                int viewId = viewBindId.value();
                View view = viewFinder.findViewById(viewId);
                if (view != null) {
                    field.setAccessible(true);
                    try {
                        field.set(object, view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("ViewUtils", "无法找到 ViewId:" + viewId + " 所对应的View");
                }
            }
        }

    }

    private static void injectEvent(ViewFinder viewFinder, Object object) {
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            OnClick onClick = method.getAnnotation(OnClick.class);
            if (onClick != null) {
                method.setAccessible(true);
                int[] viewIds = onClick.value();
                for (int viewId : viewIds) {
                    View view = viewFinder.findViewById(viewId);
                    if (view != null) {
                        view.setOnClickListener(new DeclaredOnClickListener(method, object));
                    }
                }

            }
        }
    }


    private static class DeclaredOnClickListener implements View.OnClickListener {
        private Object mObject;
        private Method mMethod;

        DeclaredOnClickListener(Method method, Object object) {
            mMethod = method;
            mObject = object;
        }

        @Override
        public void onClick(View v) {
            try {
                mMethod.invoke(mObject, v);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    //兼容无参
                    mMethod.invoke(mObject, null);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
