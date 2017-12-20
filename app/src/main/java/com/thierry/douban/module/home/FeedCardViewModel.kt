package com.thierry.douban.module.home

import android.graphics.drawable.Drawable
import com.thierry.douban.R
import com.thierry.douban.model.Feed
import com.thierry.douban.module.common.BaseViewModel
import com.thierry.douban.util.CardLayout
import com.thierry.douban.util.ResUtils

/**
 * Created by Thierry on 2017/7/13.
 */
class FeedCardViewModel(val feed: Feed) : BaseViewModel() {

    fun sourceText() = feed.sourceCn

    fun topHidden() = feed.sourceCn.isEmpty()

    fun title(): String = feed.title

    fun summary(): String = feed.target?.desc ?: ""

    fun coverUrl(): String = feed.target?.coverUrl ?: ""

    fun authorImageUrl(): String = feed.target?.coverUrl ?: ""

    fun verifyImageHidden(): Boolean = feed.target?.author?.verifyType == 1

    fun authorText(): String = feed.target?.author?.name ?: ""

    fun sourceImage(): Drawable = ResUtils.getDrawableByName("ic_feed_${if (feed.sourceCn.equals("今日热点")) "hot" else "vline"}")

    fun summaryMaxLines(): Int {
        if (feed.layout == CardLayout.HomeFeedNormalPortrait.type) {
            return 3
        } else {
            return 2
        }
    }

    fun coverHeight(): Float {
        if (feed.layout == CardLayout.HomeFeedNormalPortrait.type) {
            return ResUtils.getDimen(R.dimen.feed_image_portrait_height)
        } else {
            return ResUtils.getDimen(R.dimen.feed_image_normal_height)
        }
    }

    fun coverWidth(): Float {
        feed.target?.coverUrl?.let {
            if (it.isEmpty()) {
                return ResUtils.getDimen(R.dimen.feed_image_zero_width)
            } else {
                return ResUtils.getDimen(R.dimen.feed_image_normal_width)
            }
        }
        return ResUtils.getDimen(R.dimen.feed_image_zero_width)
    }

}