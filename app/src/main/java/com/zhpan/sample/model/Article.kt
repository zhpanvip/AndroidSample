package com.zhpan.sample.model


/**
 * <pre>
 *   Created by zhpan on 2020/7/11.
 *   Description:
 * </pre>
 */
 class Article {
    var title: String? = null
    var link: String? = null
    var author: String? = null
    var publishTime: Long = 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Article) return false

        if (title != other.title) return false
        if (link != other.link) return false
        if (author != other.author) return false
        if (publishTime != other.publishTime) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title?.hashCode() ?: 0
        result = 31 * result + (link?.hashCode() ?: 0)
        result = 31 * result + (author?.hashCode() ?: 0)
        result = 31 * result + publishTime.hashCode()
        return result
    }


}