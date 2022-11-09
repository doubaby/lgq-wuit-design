import request from "@/utils/request";

export default {
    getHotHouseList(house) {
        return request({
            url: '/House/houselist/getHotHouse',
            method: 'post',
            data: house
        })
    }
}
