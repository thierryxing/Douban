# Douban
> 基于 Kotlin 的豆瓣 Android App

## 关于Douban
15年初 Kotlin 出了 Beta 后，尝试使用 Kotlin 写了一个完整的应用，Sentry 的Android 客户端 [Sentry-Horn](https://github.com/thierryxing/sentry-horn)。写完之后，感觉到了和在iOS平台中使用Swift一样的便捷。

今年 Kotlin 成为 Android 官方语言及 Android Studio3.0 推出后，Kotlin 有了更好的支持，时机已经成熟，于是开始在团队内部进行推广和培训，为了方便大家更好的理解 Kotlin 这门语言，边培训边带大家完成了一款高仿豆瓣App的应用（本人是豆瓣重度用户）

## 功能及开发模式
* 包含登录，及 5 个一级页面，及二级内容详情页
* 包含豆瓣 Hybrid 框架[Rexxar](https://github.com/douban/rexxar-android) 的使用：一个通用的 RexxarPartialFragment
* 包含一个通用的 RecycleView 组件 RecyclerFragment 及通用 Feed 流适配器 CardAdapter
* 采用 Databinding+MVVM 模式

## Kotlin版本及三方框架
* Kotlin 已经升级到了 1.2.31 版本
* 使用 [anko](https://github.com/Kotlin/anko) 框架简化 Android 开发
* 使用 [Fuel](https://github.com/kittinunf/Fuel) 作为网络层框架
* 使用 [Kotson](https://github.com/SalomonBrys/Kotson) 作为 json 解析框架


## 项目结构

```
.
├── App.kt
├── component
│   ├── CardAdapter.kt
│   ├── CardViewModel.kt
│   ├── RecyclerFragment.kt
│   ├── RecyclerViewModel.kt
│   └── RexxarPartialFragment.kt
├── extension
│   ├── FragmentExtension.kt
│   └── ImageViewExtension.kt
├── model
│   ├── BaseModel.kt
│   ├── Card.kt
│   ├── Feed.kt
│   ├── Group.kt
│   ├── LoginUser.kt
│   ├── Note.kt
│   ├── Promo.kt
│   ├── SettingItem.kt
│   ├── SimpleItem.kt
│   ├── Timeline.kt
│   └── User.kt
├── module
│   ├── TabActivity.kt
│   ├── TabViewModel.kt
│   ├── account
│   │   ├── LoginActivity.kt
│   │   └── LoginViewModel.kt
│   ├── common
│   │   ├── BaseActivity.kt
│   │   ├── BaseFragment.kt
│   │   ├── BaseViewModel.kt
│   │   └── FetchDataViewModel.kt
│   ├── group
│   │   ├── GroupFragment.kt
│   │   ├── GroupViewModel.kt
│   │   ├── joined
│   │   │   ├── GroupJoinedCardViewModel.kt
│   │   │   └── GroupJoinedItemViewModel.kt
│   │   ├── rec
│   │   │   ├── GroupRecCardViewModel.kt
│   │   │   └── GroupRecItemViewModel.kt
│   │   └── top
│   │       └── GroupTopCardViewModel.kt
│   ├── home
│   │   ├── FeedCardViewModel.kt
│   │   ├── FeedFragment.kt
│   │   ├── FeedViewModel.kt
│   │   └── target
│   │       ├── FeedTargetActivity.kt
│   │       └── FeedTargetViewModel.kt
│   ├── profile
│   │   ├── ProfileFragment.kt
│   │   ├── ProfileViewModel.kt
│   │   ├── menu
│   │   │   ├── ProfileMenuCardViewModel.kt
│   │   │   └── ProfileMenuItemViewModel.kt
│   │   ├── noti
│   │   │   └── ProfileNotiCardViewModel.kt
│   │   ├── setting
│   │   │   ├── SettingActivity.kt
│   │   │   ├── SettingCardViewModel.kt
│   │   │   └── SettingViewModel.kt
│   │   └── top
│   │       └── ProfileTopCardViewModel.kt
│   ├── status
│   │   ├── home
│   │   │   ├── StatusFragment.kt
│   │   │   ├── StatusViewModel.kt
│   │   │   └── card
│   │   │       ├── StatusCardActionViewModel.kt
│   │   │       ├── StatusCardCardViewModel.kt
│   │   │       ├── StatusCardFindViewModel.kt
│   │   │       ├── StatusCardNormalViewModel.kt
│   │   │       └── StatusCardTopViewModel.kt
│   │   └── recommend
│   │       ├── RecommendStatusFragment.kt
│   │       ├── RecommendStatusViewModel.kt
│   │       └── card
│   │           ├── StatusCardRecTopViewModel.kt
│   │           └── StatusCardRecViewModel.kt
│   └── subject
│       ├── SubjectFragment.kt
│       └── SubjectViewModel.kt
├── service
│   ├── NetService.kt
│   └── UserService.kt
└── util
    ├── BindAdapter.kt
    ├── Constants.kt
    ├── Enum.kt
    ├── ResUtils.kt
    └── VersionUtils.kt

25 directories, 68 files
```

## License
MIT licensed.
