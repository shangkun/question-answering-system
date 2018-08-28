$(function(){
    refresh();
    var rules = {
        account:{
            required:true,
            minlength:3,
            maxlength:50
        },
        password:{
            required:true,
            minlength:1,
            maxlength:50
        },
        validateCode:{
            required:true,
            minlength:4,
            maxlength:4
        }
    };
    $("#login-form").validate({
        rules:rules,
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            $(form).ajaxSubmit({
                success: function(data){
                    if(data.statusCode==200){
                        layer.msg(data.message,{icon: 1,time:1000});
                        setCookie("access_token",data.data.accessToken,1);
                        setCookie("user",JSON.stringify(data.data.user),1);
                        setCookie("role",JSON.stringify(data.data.role),1);
                        window.location.href="./views/static/index.html";
                    }else{
                        layer.msg(data.info,{icon: 5,time:1000});
                    }
                },
                resetForm: false,
                dataType: "json"
            });
        }
    });
});
function refresh(){
    $("#code").attr("src","./api/common/validate/code/generate?random="+randomCode());
}