/**
 * Created by shangkun on 2018/7/13.
 */
var timeout=60000;
var async=false;
var day_format="yyyy-MM-dd";
var second_format="yyyy-MM-dd HH:mm:ss";
/**
 * ajax数据请求
 * @param url
 * @param data
 * @param callback
 * @param async
 */
var GET = function (url, data, callback, async) {
    $.ajax({
        url: url,
        type: "get",
        async: async,
        contentType: "application/json",
        dataType: "json",
        timeout: timeout,
        data: data,
        success: function (data) {
            callback(data);
        }
    });
};

/**
 * ajax post数据请求
 * @param url
 * @param data
 * @param callback
 * @param async
 */
var POST = function (url, data, callback,async) {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: url,
        type: "post",
        async: async,
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(data),
        timeout: timeout,
        success: function (msg) {
            callback(msg);
        },
        error: function (xhr, textstatus, thrown) {
            console.error("request method:"+url,thrown);
        }
    });
};
/**
 * 获取页面传参
 * @returns {Object}
 */
var get_page_param = function(key) {

    //decodeURI()解码  解决中文乱码
    var url = decodeURI(location.search); //获取url中"?"符后的字串
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        var strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            if(key==strs[i].split("=")[0]){
                return unescape(strs[i].split("=")[1]);
            }
        }
    }
    return "";
}
/**
 * 时间格式化
 * @param format
 * @returns {*}
 */
var date_format = function (data_format,timestamp) {
    var newDate = new Date();
    newDate.setTime(timestamp);
    return newDate.format(data_format);
}
Date.prototype.format = function (format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "H+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
}