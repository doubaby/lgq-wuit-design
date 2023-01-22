import request from "@/utils/request";

export default {
    getHotHouseListQuery(page, size, houseVo) {
        return request({
            url: `/House/houselist/getHotHouseQuery/${page}/${size}`,
            method: 'POST',
            data:houseVo
        })
    },
    getHouseDetail(houseId) {
        return request({
            url: `/House/houselist/details/${houseId}`,
            method: `POST`
        })
    },
    wholeList() {
        return request({
            url: '/House/houselist/list/whole',
            method: `GET`
        })
    },
    togetherList() {
        return request({
            url: '/House/houselist/list/together',
            method: `GET`
        })
    },
    wholeListPage(page, size) {
        return request({
            url: `/House/houselist/whole/pages/${page}/${size}`,
            method: `GET`
        })
    },
    togetherListPage(page, size) {
        return request({
            url: `/House/houselist/together/pages/${page}/${size}`,
            method: `GET`
        })
    }
}
