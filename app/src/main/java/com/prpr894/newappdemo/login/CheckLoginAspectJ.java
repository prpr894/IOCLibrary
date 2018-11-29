package com.prpr894.newappdemo.login;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.prpr894.newappdemo.Main2Activity;
import com.prpr894.newappdemo.MyApp;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by prpr894 on 2018/11/26 0026
 * Description:
 */
@Aspect
public class CheckLoginAspectJ {
    @Pointcut("execution(@com.prpr894.newappdemo.login.CheckLogin  * *(..))")
    public void executionCheckLogin() {
    }

    @Around("executionCheckLogin()")
    public Object CheckLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.d("flag", "走了 CheckLogin  ================");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        CheckLogin checkLogin = signature.getMethod().getAnnotation(CheckLogin.class);
        if (checkLogin != null) {
            if (MyApp.isLogin) {
                return joinPoint.proceed();
            } else {
                Object object = joinPoint.getThis();// View Activity Fragment ； getThis() 当前切点方法所在的类
                final Context context = getContext(object);
                if (context != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setMessage("要想使用此功能，请先登陆！");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton("登陆", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            context.startActivity(new Intent(context, Main2Activity.class));
                        }
                    });
                    builder.show();

                }
                return null;
            }
        }
        return joinPoint.proceed();
    }

    /**
     * 通过对象获取上下文
     *
     * @param object
     * @return
     */
    private Context getContext(Object object) {
        if (object instanceof Activity) {
            return (Activity) object;
        } else if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            return fragment.getActivity();
        } else if (object instanceof View) {
            View view = (View) object;
            return view.getContext();
        }
        return null;
    }
}
