$(function(){
    var menuList = new Array();

    loadTree();

    var id = get_page_param('id');

    $("#modifierId").val(getUserId());
    if(id!=""){
        $("#knowledge-form").attr("action",knowledgeUrl+"update");
        $("#id").val(id);
        loadKnowledge(id);
    }else{
        $("#knowledge-form").attr("action",knowledgeUrl+"add");
    }

    var rules = {
        title:{
            required:true,
            minlength:1,
            maxlength:300,
            isKnowledgeTitleRepeat: true
        },
        classificationId:{
            required:true,
            minlength:1,
            maxlength:20
        },
        answerList:{
            required:true
        }
    };

    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });

    $("#knowledge-form").validate({
        rules:rules,
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            $(form).ajaxSubmit({
                headers: {
                    'access_token': access_token
                },
                beforeSubmit:function(){
                    var channelList = $("select[name$='channelId']");
                    var contentList = $("textarea[name$='content']");
                    if(channelList.length==1 || contentList.length==1){
                        layer.msg("请至少添加一个答案",{icon: 5,time:2000});
                        return false;
                    }
                    if($("#classificationId").val()==""){
                        layer.msg("请选择一个分类",{icon: 5,time:2000});
                        return false;
                    }
                    return true;
                },
                success: function(data){
                    if(data.statusCode==200){
                        layer.msg(data.message,{icon: 1,time:1000});
                        parent.load_knowledge();
                    }else{
                        layer.msg(data.info,{icon: 5,time:1000});
                    }
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                },
                resetForm: true,
                dataType: "json"
            });
        }
    });
});
function loadKnowledge(id){
    GET(knowledgeUrl+"get/"+id,null,function(data){
        if(data.statusCode==200){
            var knowledge = data.data;
            $("#title").val(knowledge.title);
            $("#classificationId").val(knowledge.classificationId);

            //使当前节点处于选中状态
            var node = classificationTree.getNodeByParam("id",knowledge.classificationId);
            $("#classificationName").val(node.name);
            classificationTree.checkNode(node, true, true);

            var extensionQuestionList = knowledge.extensionQuestionList;
            if(extensionQuestionList!=null && extensionQuestionList.length>0){
                $.each(extensionQuestionList,function(i,v){
                    $("#extensionQuestionTitleList").append('<input type="text" class="input-text" style="margin-top:10px;width:95%;" value="'+ v.title+'" name="extensionQuestionTitleList" readonly="readonly"><i onclick="removePre(this)" style="margin-left: 5px;" class="Hui-iconfont">&#xe609;</i>');
                })
            }
            var answerList = knowledge.answerList;
            if(answerList!=null && answerList.length>0){
                $.each(answerList,function(i,v){
                    var html = "";
                    html+='<span class="select-box" style="margin-top:10px;width:95%;">';
                    html+='<select name="answerList['+i+'].channelId" class="select" value="'+v.channelId+'">';
                    html+=channelHtml(v.channelId);
                    html+='</select>';
                    html+='</span>';
                    html+='<textarea class="textarea" style="width:95%;" name="answerList['+i+'].content">'+ v.content+'</textarea>';
                    html+='<i onclick="removePreAndPre(this)" style="margin-left: 5px;" class="Hui-iconfont">&#xe609;</i>';
                    $("#answerList").append(html);
                })
            }
        }
    });
}
function channelHtml(channelId){
    var html="";
    if(channelId=="100"){
        html+='<option value=100 selected>全部渠道</option>';
    }else{
        html+='<option value=100>全部渠道</option>';
    }
    if(channelId=="101"){
        html+='<option value=101 selected>网页</option>';
    }else{
        html+='<option value=101>网页</option>';
    }
    if(channelId=="102"){
        html+='<option value=102 selected>APP</option>';
    }else{
        html+='<option value=102>APP</option>';
    }
    if(channelId=="103"){
        html+='<option value=103 selected>微信</option>';
    }else{
        html+='<option value=103>微信</option>';
    }
    return html;
}
function addExtension(){
    var flag = true;
    var titleList = $("input[name='extensionQuestionTitleList']");
    var title = $("#firstExtensionQuestion").val();
    if(title==""){
        layer.msg("扩展问不能为空",{icon: 5,time:1000});
        flag = false;
    }
    $.each(titleList,function(i,v){
        if($(v).val()==title){
            layer.msg("扩展问:"+title+"重复",{icon: 5,time:1000});
            flag = false;;
        }
    });
    if(flag){
        $("#extensionQuestionTitleList").append('<input type="text" class="input-text" style="margin-top:10px;width:95%;" value="'+title+'" name="extensionQuestionTitleList" readonly="readonly"><i onclick="removePre(this)" style="margin-left: 5px;" class="Hui-iconfont">&#xe609;</i>');
        $("#firstExtensionQuestion").val('');
    }
}
function addAnswer(){
    var flag = true;
    var channelList = $("select[name$='channelId']");
    var contentList = $("textarea[name$='content']");
    $.each(channelList,function(i,v){
        console.log($(v).val());
        if(i!=0 && $(v).val()==100){
            layer.msg("全部渠道已选",{icon: 5,time:1000});
            flag = false;
        }
        if(i!=0 && $(v).val()!=100 && $(v).val()==$(channelList[0]).val()){
            layer.msg("渠道已选",{icon: 5,time:1000});
            flag = false;
        }
        if(i!=0 && ($(v).val()==101 || $(v).val()==102 || $(v).val()==103) && $(channelList[0]).val()==100){
            layer.msg("所有渠道已选",{icon: 5,time:1000});
            flag = false;
        }
    });
    if($(contentList[0]).val()==""){
        layer.msg("答案内容不能为空",{icon: 5,time:1000});
        flag = false;
    }
    var html = "";
    html+='<span class="select-box" style="margin-top:10px;width:95%;">';
    html+='<select name="answerList['+(channelList.length-1)+'].channelId" class="select" value="'+$(channelList[0]).val()+'">';
    html+=channelHtml($(channelList[0]).val());
    html+='</select>';
    html+='</span>';
    html+='<textarea class="textarea" style="width:95%;" name="answerList['+(contentList.length-1)+'].content">'+$(contentList[0]).val()+'</textarea>';
    html+='<i onclick="removePreAndPre(this)" style="margin-left: 5px;" class="Hui-iconfont">&#xe609;</i>';
    if(flag){
        $("#answerList").append(html);
        $("#channelId").val(100);
        $("#content").val('');
    }
}
function removePre(obj){
    $(obj).prev().remove();
    $(obj).remove();
}
function removePreAndPre(obj){
    $(obj).prev().prev().remove();
    $(obj).prev().remove();
    $(obj).remove();
}
var setting = {
    check: {
        enable: true,
        chkStyle: "radio",
        radioType: "all"
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onCheck: onCheck
    }
};

function onCheck(e, treeId, treeNode) {
    if(treeNode.checked){
        $("#classificationId").val(treeNode.id);
        $("#classificationName").val(treeNode.name);
    }else{
        $("#classificationId").val('');
        $("#classificationName").val('');
    }
}
function loadTree(){
    var request = new Object();
    POST(classificationUrl+"get",request,function(data){
        if(data.statusCode==200){
            var classificationArray = data.data.data;
            classificationTree = $.fn.zTree.init($("#classificationTree"), setting, classificationArray);
        }else{
            layer.msg(data.info,{icon: 5,time:1000});
        }
    },true);
}