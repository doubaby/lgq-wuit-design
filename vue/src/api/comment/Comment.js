import request from "@/utils/request";
export default{
    getAllComment() {
        return request({
            url: '/House/comments/commentList/',
            method: 'GET'
        })
    },
    addComment(commentVo){
        return request({
            url: "/House/comments/addComment",
            method: "POST",
            data:commentVo
        })
    },
    replay(replay){
        return request({
            url: "/House/replays/replay",
            method: "POST",
            data:replay
        })
    }

}
