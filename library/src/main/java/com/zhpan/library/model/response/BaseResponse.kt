package com.zhpan.library.model.response

/**
 *
 * @author zhangpan
 * @date 2021/3/23
 */
class BaseResponse<T> {
    val data: T? = null
    val errorCode = 0
    val errorMsg = ""
}