$(function(){
    var menuList = new Array();

    var id = get_page_param('id');

    $("#modifierId").val(getUserId());
    if(id!=""){
        $("#greeting-form").attr("action",greetingUrl+"update");
        $("#id").val(id);
        loadKnowledge(id);
    }else{
        $("#greeting-form").attr("action",greetingUrl+"add");
    }

    var rules = {
        title:{
            required:true,
            minlength:1,
            maxlength:300,
            isKnowledgeTitleRepeat: true
        },
        greetingAnswerList:{
            required:true
        }
    };

    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });

    $("#greeting-form").validate({
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
                    var contentList = $("textarea[name$='answer']");
                    if(channelList.length==1 || greetingAnswerList.length==1){
                        layer.msg("请至少添加一个答案",{icon: 5,time:2000});
                        return false;
                    }
                    return true;
                },
                success: function(data){
                    if(data.statusCode==200){
                        layer.msg(data.message,{icon: 1,time:1000});
                        if(parent.table!=undefined){
                            parent.load_greeting();
                        }
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
    GET(greetingUrl+"get/"+id,null,function(data){
        if(data.statusCode==200){
            var greeting = data.data;
            $("#title").val(greeting.title);

            var extensionQuestionList = greeting.extensionQuestionList;
            if(extensionQuestionList!=null && extensionQuestionList.length>0){
                $.each(extensionQuestionList,function(i,v){
                    $("#greetingExtensionQuestionTitleList").append('<input type="text" class="input-text" style="margin-top:10px;width:95%;" value="'+ v.title+'" name="greetingExtensionQuestionTitleList" readonly="readonly"><i onclick="removePre(this)" style="margin-left: 5px;" class="Hui-iconfont">&#xe609;</i>');
                })
            }
            var greetingAnswerList = greeting.greetingAnswerList;
            if(greetingAnswerList!=null && greetingAnswerList.length>0){
                $.each(greetingAnswerList,function(i,v){
                    var html = "";
                    html+='<span class="select-box" style="margin-top:10px;width:95%;">';
                    html+='<select name="greetingAnswerList['+i+'].channelId" class="select" value="'+v.channelId+'">';
                    html+=channelHtml(v.channelId);
                    html+='</select>';
                    html+='</span>';
                    html+='<textarea class="textarea" style="width:95%;" name="greetingAnswerList['+i+'].answer">'+ v.answer+'</textarea>';
                    html+='<i onclick="removePreAndPre(this)" style="margin-left: 5px;" class="Hui-iconfont">&#xe609;</i>';
                    $("#greetingAnswerList").append(html);
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
    var titleList = $("input[name='greetingExtensionQuestionTitleList']");
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
        $("#greetingExtensionQuestionTitleList").append('<input type="text" class="input-text" style="margin-top:10px;width:95%;" value="'+title+'" name="greetingExtensionQuestionTitleList" readonly="readonly"><i onclick="removePre(this)" style="margin-left: 5px;" class="Hui-iconfont">&#xe609;</i>');
        $("#firstExtensionQuestion").val('');
    }
}
function addAnswer(){
    var flag = true;
    var channelList = $("select[name$='channelId']");
    var greetingAnswerList = $("textarea[name$='answer']");
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
    if($(greetingAnswerList[0]).val()==""){
        layer.msg("答案内容不能为空",{icon: 5,time:1000});
        flag = false;
    }
    var html = "";
    html+='<span class="select-box" style="margin-top:10px;width:95%;">';
    html+='<select name="greetingAnswerList['+(channelList.length-1)+'].channelId" class="select" value="'+$(channelList[0]).val()+'">';
    html+=channelHtml($(channelList[0]).val());
    html+='</select>';
    html+='</span>';
    html+='<textarea class="textarea" style="width:95%;" name="greetingAnswerList['+(greetingAnswerList.length-1)+'].answer">'+$(greetingAnswerList[0]).val()+'</textarea>';
    html+='<i onclick="removePreAndPre(this)" style="margin-left: 5px;" class="Hui-iconfont">&#xe609;</i>';
    if(flag){
        $("#greetingAnswerList").append(html);
        $("#channelId").val(100);
        $("#answer").val('');
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