# LiaoLibrary
Liao's Android development library
## 依赖
请在项目build.gradle中添加以下内容，gradle7.0+的请在settings.gradle中添加
```
repositories {
        //添加JitPack
        maven { url "https://jitpack.io" }
    }
```

请在app的build.gradle根据情况添加以下依赖
```
//通用库，其它LiaoLibrary依赖使用的前提都必须依赖此库
implementation 'com.github.liao10108180.LiaoLibrary:library-common:lastVersion'

//工具库
implementation 'com.github.liao10108180.LiaoLibrary:library-utils:lastVersion'

//Http请求库
implementation 'com.github.liao10108180.LiaoLibrary:library-http:lastVersion'

//Base库，适用时必须依赖以上3个库(common,utils,http)
implementation 'com.github.liao10108180.LiaoLibrary:library-base:lastVersion'

```
