package com.zhpan.library.model

class ArticleResponse() {

    var datas: List<Article>? = emptyList()
    var curPage: Int? = 0
    var offset: Int? = 0
    var over: Boolean? = false
    var pageCount: Int? = 0
    var size: Int? = 0
    var total: Int? = 0
}