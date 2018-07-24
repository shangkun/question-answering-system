jQuery.validator.addMethod("isEmail", function(value, element) {
    var zip = /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;
    return this.optional(element) || (zip.test(value));
}, "邮箱格式不正确");

jQuery.validator.addMethod("isAccountRepeat", function(value, element) {
    var request = new Object();
    request.account = value;
    if($("#id").val()){
        request.id = $("#id").val();
    }
    var flag = true;
    POST(userUrl+"repeat",request,function(data){
        if(data.statusCode==500){
            flag = false;
        }
    },false);
    return this.optional(element) || flag;
}, "账号已存在");