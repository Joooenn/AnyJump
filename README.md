# AnyJump
a Activity jump demo
# Useage
1. 在工程的 Application 类初始化
```
    JActivityManager.getInstance().init(this);
```
2. 返回任意 Activity
```
    public void backTwo(View view) {
        JActivityManager.getInstance().popToActivity(TwoActivity.class);
    }
    public void backOne(View view) {
        JActivityManager.getInstance().popToActivity(OneActivity.class);
    }
    public void backHome(View view) {
        JActivityManager.getInstance().popToActivityAtIndex(0);
    }
```

3. 代码实现待上传

