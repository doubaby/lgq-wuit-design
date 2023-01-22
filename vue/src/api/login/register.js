import request from "@/utils/request";
export default{
    register(userDto){
        return request({
            url:'/House/user/register',
            method:'POST',
            data:userDto
        })
    }
}
