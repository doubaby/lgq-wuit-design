import request from "@/utils/request";

export default {
    pageNews(page,size){
        return request({
            url: `/House/news/newsPage/${page}/${size}`,
            method:'POST',
        })
    }
}
