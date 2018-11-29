# IOCLibrary
自用简单的ico框架


## 引用：

Maven
>>
<dependency>
  <groupId>com.prpr894.baseioclibrary</groupId>
  <artifactId>baseioclibrary</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>

Gredle
>>
implementation 'com.prpr894.baseioclibrary:baseioclibrary:1.0.0'

## 使用：<br>
```
public class MainActivity extends AppCompatActivity {
    @ViewBindId(R.id.tv_main)
    private TextView mTvMain;
    @ViewBindId(R.id.img_main)
    private ImageView mImgMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
    }
   @OnClick(R.id.tv_main)
    private void tvMainClick(TextView tvMain) {

    }
}

```

可以使用Demo根目录下的名为“DarrenTranslate.jar”的AndroidStudio插件一键生成注解（光标放在R.layout.activity_main等你需要一键生成的布局的引用的Id上，快捷键Ctrl+Alt+H）。

# 参考资料：

### ICO注解：

[自己动手打造一套IOC注解框架](https://www.jianshu.com/p/2570c2de028b)

[Android Studio插件开发之 - 基础入门篇](https://www.jianshu.com/p/a41d32930e2d)

[Android Studio插件开发之 - IOC注解生成器](https://www.jianshu.com/p/efd0d28a48a3)

### 发布Jcenter

[Android Studio发布项目到jcenter,一行代码引入Module](https://www.cnblogs.com/yishaochu/p/7495703.html)


