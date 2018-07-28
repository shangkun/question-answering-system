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

jQuery.validator.addMethod("isSensitiveWordRepeat", function(value, element) {
    var request = new Object();
    request.topic = value;
    if($("#id").val()){
        request.id = $("#id").val();
    }
    var flag = true;
    POST(sensitiveWordUrl+"repeat",request,function(data){
        if(data.statusCode==500){
            flag = false;
        }
    },false);
    return this.optional(element) || flag;
}, "敏感词已存在");

jQuery.validator.addMethod("isLexiconRepeat", function(value, element) {
    var request = new Object();
    request.topic = value;
    if($("#id").val()){
        request.id = $("#id").val();
    }
    var flag = true;
    POST(lexiconUrl+"repeat",request,function(data){
        if(data.statusCode==500){
            flag = false;
        }
    },false);
    return this.optional(element) || flag;
}, "该词已存在");

jQuery.validator.addMethod("isClassificationRepeat", function(value, element) {
    var request = new Object();
    request.name = value;
    if($("#id").val()){
        request.id = $("#id").val();
    }
    request.pId=$("#pId").val();
    var flag = true;
    POST(classificationUrl+"repeat",request,function(data){
        if(data.statusCode==500){
            flag = false;
        }
    },false);
    return this.optional(element) || flag;
}, "该分类已存在");

jQuery.validator.addMethod("isKnowledgeTitleRepeat", function(value, element) {
    var request = new Object();
    request.title = value;
    if($("#id").val()){
        request.id = $("#id").val();
    }
    var flag = true;
    POST(knowledgeUrl+"repeat",request,function(data){
        if(data.statusCode==500){
            flag = false;
        }
    },false);
    return this.optional(element) || flag;
}, "该知识标题已存在");