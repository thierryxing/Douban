# Douban
> 基于Kotlin的豆瓣Android App

## 关于Douban
15年初Kotlin出了Beta后，尝试使用Kotlin写了一个完整的应用，Sentry的Android客户端[Sentry-Horn](https://github.com/thierryxing/sentry-horn)。写完之后，感觉到了和在iOS平台中使用Swift一样的便捷。

今年Kotlin成为Android官方语言及Android Studio3.0推出后，Kotlin有了更好的支持，时机已经成熟，于是开始在团队内部进行推广和培训，为了方便大家更好的理解Kotlin这门语言，边培训边带大家完成了一款高仿豆瓣App的应用（本人是豆瓣重度用户）

## 功能及开发模式
* 包含登录，及5个一级页面，及二级内容详情页
* 包含豆瓣Hybrid框架(https://github.com/douban/rexxar-android)[Rexxar]的使用：一个通用的RexxarPartialFragment
* 包含一个通用的RecycleView组件RecyclerFragment及通用Feed流适配器CardAdapter
* 采用Databinding+MVVM模式

## Kotlin版本及三方框架
* Kotlin已经升级到了1.1.60版本，1.2.0版本正在适配过程中
* 使用[anko](https://github.com/Kotlin/anko)框架简化Android开发
* 使用[Fuel](https://github.com/kittinunf/Fuel)作为网络层框架
* 使用[Kotson](https://github.com/SalomonBrys/Kotson)作为json解析框架


## 项目结构

```
.
├── App.vue
├── assets
│   └── logo.png
├── components
│   ├── dashboard
│   │   ├── Aggregate.vue
│   │   └── WeeklyChart.vue
│   ├── global
│   │   ├── Alert.vue
│   │   ├── ConfirmModal.vue
│   │   ├── ContentFooter.vue
│   │   ├── ContentHeader.vue
│   │   ├── LoadingOverlay.vue
│   │   ├── NavBar.vue
│   │   ├── Pagination.vue
│   │   ├── SideBar.vue
│   │   ├── SideBarItem.vue
│   │   ├── TableBox.vue
│   │   └── UserPanel.vue
│   └── projects
│       ├── Dependency.vue
│       ├── EnvForm.vue
│       ├── Form.vue
│       ├── Info.vue
│       └── SideBar.vue
├── constants
│   ├── api.js
│   └── enum.js
├── main.js
├── router
│   ├── index.js
│   └── routes.js
├── store
│   ├── actions.js
│   ├── index.js
│   ├── modules
│   │   ├── alert.js
│   │   ├── current-platform.js
│   │   ├── current-project.js
│   │   └── current-user.js
│   └── mutation-types.js
├── utils
│   ├── networking.js
│   └── storage.js
└── views
    ├── Layout.vue
    ├── account
    │   └── Login.vue
    ├── activity
    │   └── List.vue
    ├── dashboard
    │   └── List.vue
    ├── jobs
    │   └── Index.vue
    ├── members
    │   └── List.vue
    └── projects
        ├── Edit.vue
        ├── Home.vue
        ├── Layout.vue
        ├── List.vue
        ├── New.vue
        ├── builds
        │   ├── Detail.vue
        │   ├── List.vue
        │   ├── PreBuildBeta.vue
        │   ├── PreBuildLib.vue
        │   ├── PreBuildProd.vue
        │   └── PreBuildTest.vue
        ├── environments
        │   ├── Clone.vue
        │   ├── List.vue
        │   ├── New.vue
        │   └── configs
        │       ├── Fastlane.vue
        │       ├── GitClone.vue
        │       ├── Info.vue
        │       └── List.vue
        └── services
            ├── Edit.vue
            └── List.vue

21 directories, 60 files
```

## License
MIT licensed.