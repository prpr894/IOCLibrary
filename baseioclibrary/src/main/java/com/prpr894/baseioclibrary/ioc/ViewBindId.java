package com.prpr894.baseioclibrary.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by prpr894 on 2018/11/22 0022
 * Description: View注解的Annotation
 */

//@Target(ElementType.FIELD) Annotation的作用位置
// FIELD是属性， @ViewBindId(R.id.tv_main)
// METHOD是方法，@Override
// TYPE是类
// CONSTRUCTOR是构造函数
//@Retention(RetentionPolicy.CLASS) 什么时候生效
// CLASS是编译时 RUNTIME是运行时 SOURCE是源码
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewBindId {
    int value();
}
