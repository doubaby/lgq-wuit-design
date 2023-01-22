import request from "@/utils/request";
export default{
login(userDto){
    return request({
        url:'/House/user/login',
        method:'POST',
        data:userDto
    })
},
    getUserInfo(token){
        return request({
            url:`/House/user/me/${token}`,
            method:'GET',
        })
    },
    UserInfoShow(id){
        return request({
            url:`/House/user/userInfo/${id}`,
            method:'GET'
        })
    },
    EditUserInfo(user){
        return request({
            url:`/House/user/userInfoEdit`,
            method:'POST',
            data:user
        })
    }
}
