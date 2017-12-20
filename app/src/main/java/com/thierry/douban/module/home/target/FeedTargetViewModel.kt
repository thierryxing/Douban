package com.thierry.douban.module.home.target

import com.github.salomonbrys.kotson.fromJson
import com.thierry.douban.model.Note
import com.thierry.douban.module.common.FetchDataViewModel
import com.thierry.douban.service.NetService
import com.thierry.douban.util.Constants
import com.thierry.douban.util.FeedTargetType
import com.thierry.douban.util.FetchDataResult

/**
 * Created by Thierry on 2017/7/22.
 */
class FeedTargetViewModel : FetchDataViewModel() {

    var note: Note? = null
    var kind: Int = 0
    var id: String = ""
    var title: String = ""

    fun fetchNote() {
        fetchDataResult.set(FetchDataResult.Normal)
        when (kind) {
            FeedTargetType.Note.type ->
                api = Constants.API.noteDetail(id)
            FeedTargetType.GroupTopic.type ->
                api = Constants.API.groupTopicDetail(id)
            FeedTargetType.Review.type ->
                api = Constants.API.reviewDetail(id)
            FeedTargetType.PhotoAlbum.type ->
                api = Constants.API.photoAlbumDetail(id)
        }
        if (!api.isEmpty()) {
            NetService.instance().doGet(api, success = { json: String? ->
                note = json?.let { gson().fromJson<Note>(it) }
                fetchDataResult.set(FetchDataResult.Success)
            }, failed = { error ->
                error?.let {
                    errorMessage = it
                }
                fetchDataResult.set(FetchDataResult.Failed)
            })
        }
    }

    fun donateWrapperVisible() = note?.let { it.donateCount > 0 } ?: false

    fun donateText() = "${note?.donateCount}"

    fun likeWrapperVisible() = note?.let { it.likersCount > 0 } ?: false

    fun likeText() = "${note?.likersCount}"

    fun commentWrapperVisible() = note?.let { it.commentsCount > 0 } ?: false

    fun commentText() = "${note?.commentsCount}"

    fun titleText() = when (kind) {
        FeedTargetType.Note.type ->
            "日记"
        FeedTargetType.GroupTopic.type ->
            "小组话题"
        FeedTargetType.Review.type ->
            note?.typeName
        FeedTargetType.PhotoAlbum.type ->
            "相册"
        FeedTargetType.Page.type ->
            title
        else -> ""
    }
}
