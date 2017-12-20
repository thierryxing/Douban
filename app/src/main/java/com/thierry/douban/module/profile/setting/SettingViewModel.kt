package com.thierry.douban.module.profile.setting

import com.thierry.douban.R
import com.thierry.douban.component.RecyclerViewModel
import com.thierry.douban.model.*
import com.thierry.douban.module.common.BaseViewModel
import com.thierry.douban.service.UserService
import com.thierry.douban.util.CardLayout

/**
 * Created by Thierry on 2017/7/13.
 */
class SettingViewModel : RecyclerViewModel() {
    override val isLocalData = true

    private var settingItems: MutableList<SettingItem> = mutableListOf(
            SettingItem("推送", isFirstItem = true), SettingItem("兴趣标签"),
            SettingItem("绑定微信", "未绑定", isFirstItem = true), SettingItem("绑定手机号", "已绑定"), SettingItem("修改密码"),
            SettingItem("清理存储空间", "9.9 MB", isFirstItem = true),
            SettingItem("将[小组]放在桌面", isFirstItem = true, showRightArrow = false),
            SettingItem("帮助与反馈", "来尽情吐槽吧", isFirstItem = true), SettingItem("网络诊断"),
            SettingItem("新功能介绍", isFirstItem = true), SettingItem("关于"), SettingItem("开源许可")
    )

    override fun handelJSON(json: String?) {}

    override fun buildCardList() {
        if (UserService.instance().isLoggedIn()) {
            settingItems.add(SettingItem("退出登录", isFirstItem = true, layout = CardLayout.SettingsButton.type))
        }
        cardList.addAll(settingItems)
    }

    override fun getCardViewModel(position: Int): BaseViewModel {
        return SettingCardViewModel(cardList[position] as SettingItem)
    }

    override fun getCardLayoutId(viewType: Int): Int {
        var layoutId: Int = R.layout.card_setting_normal
        when (viewType) {
            CardLayout.SettingsNormal.type ->
                layoutId = R.layout.card_setting_normal
            CardLayout.SettingsButton.type ->
                layoutId = R.layout.card_setting_button
        }
        return layoutId
    }

}