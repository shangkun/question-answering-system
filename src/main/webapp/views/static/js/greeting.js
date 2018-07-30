$(function(){
    load_greeting();
});

var request = new Object();

function load_greeting(){
    table = $("#greeting").DataTable({
        autoWidth:false,
        autoHeight:false,
        stripeClasses: ["odd", "even"], //为奇偶行加上样式，兼容不支持CSS伪类的场合
        processing: true, //隐藏加载提示,自行处理
        serverSide: true, //启用服务器端分页
        searching: false, //禁用原生搜索
        destroy: true,
        ordering: false, //启用多列排序
        order: [], //取消默认排序查询,否则复选框一列会出现小箭头
        info: true,
        "PaginationType" : "full_numbers",
        "ajax": function (data, callback) {
            request.index=data.start;
            request.pageSize=data.length;
            if($("#datemin").val() && $("#datemax").val()){
                request.startTime=$("#datemin").val()+hour_start;
                request.endTime=$("#datemax").val()+hour_end;
            }else{
                request.startTime=null;
                request.endTime=null;
            }
            if($("#key_words").val()){
                request.title = $("#key_words").val();
            }else{
                request.title = null;
            }
            POST(greetingUrl+"get",request,function(result){
                //封装返回数据
                var returnData = {};
                $("#greeting_total").html(result.data.total);
                returnData.recordsTotal = result.data.total;
                returnData.iTotalDisplayRecords = result.data.total;
                returnData.data = result.data.data;

                callback(returnData);
            },false);

        },
        "columns":[
            {
                "data" : "id",
                "render" : function(data,type,row) {
                    var html = '<input class="text-c" type="checkbox" value="'+data+'" name="sid"/>';
                    return html;
                }
            },
            {"data":"title"},
            {
                "data":"greetingExtensionQuestionList",
                "render" : function(data,type,row) {
                    return extension_question_parse(data);
                }
            },
            {
                "data":"greetingAnswerList",
                "render" : function(data,type,row) {
                    return greeting_answer_parse(data);
                }
            },
            {
                "data":"modifyTime",
                "render" : function(data,type,row) {
                    return date_format(second_format,data);
                }
            },
            {"data":"modifierName"},
            {
                "data" : "id",
                "render" : function(data,type,row) {
                    var html = '<a style="text-decoration:none" onClick="open_full(\'寒暄知识编辑\',\'greeting-add-update.html?id='+data+'\')" href="javascript:;" title="编辑">' +
                        '<i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="greeting_del(this,\''+data+'\')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>';
                    return html;
                }
            }
        ]
    });
}
function greeting_del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
        GET(greetingUrl+"delete/"+id,null,function(data){
            if(data.statusCode==200){
                table.ajax.reload();
                layer.msg(data.message,{icon:1,time:1000});
            }else{
                layer.msg(data.info,{icon:5,time:1000});
            }
        },false);
    });
}
function batch_del(){
    var id_array = $("input[name='sid']");
    var idList = new Array();
    $.each(id_array,function(i,v){
        if(v.checked){
            console.log(v.value);
            idList.push(v.value);
        }
    });
    if(idList.length==0){
        layer.msg("请至少选择一条记录",{icon:5,time:1000});
        return;
    }

    layer.confirm('确认要删除吗？',function(index){
        POST(greetingUrl+"batch/delete/",idList,function(data){
            if(data.statusCode==200){
                table.ajax.reload();
                layer.msg(data.message,{icon:1,time:1000});
            }else{
                layer.msg(data.info,{icon:5,time:1000});
            }
        },false);
    });
}