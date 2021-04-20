package com.fortunes.commonsdk.bean

import android.os.Parcelable
import com.fortunes.commonsdk.utils.sp.PreferenceRename
import com.fortunes.commonsdk.utils.sp.PreferenceSupport
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @auther:MR-Cheng
 * @date: 2019/1/2
 * @description:  统一查询个人信息 common/userInfo/queryUserInfo
 * @parameter:
 */
@PreferenceRename(("user_info"))
class UserInfoBean: PreferenceSupport(){
    var mobile: String = ""
    var name: String = ""
}

