$(function () {
    loadMenu();
});

function reloadUserAndRole(){
    GET(userUrl2+"get/"+getUserId(),null,function(data){
        if(data.statusCode==200){
            var user = data.data;
            var role = data.data.role;
            setCookie("user",JSON.stringify(user),1);
            setCookie("role",JSON.stringify(role),1);
        }
    },true);
}
/**
 * 加载菜单
 */
function loadMenu(){
    var roleJson = getCookie("role");
    var role = JSON.parse(roleJson);
    var userJson = getCookie("user");
    var user = JSON.parse(userJson);
    $("#roleName").html(role.name);
    $("#adminName").html(user.account);
    $("#menu").empty();
    $.each(role.menuList,function(i,v){
        if(v=="1"){
            var html = "";
            html += '<dl id="menu-admin">';
            html += '<dt><i class="Hui-iconfont">&#xe62d;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>';
            html += '<dd>';
            html += '<ul>';
            html += '<li><a data-href="admin/role.html" data-title="角色管理" href="javascript:void(0)">角色管理</a></li>';
            html += '<li><a data-href="admin/user.html" data-title="管理员列表" href="javascript:void(0)">管理员列表</a></li>';
            html += '</ul>';
            html += '</dd>';
            html += '</dl>';
            $("#menu").append(html);
        }
        if(v=="2"){
            var html = "";
            html += '<dl id="menu-system">';
            html += '<dt><i class="Hui-iconfont">&#xe62e;</i> 系统配置<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>';
            html += '<dd>';
            html += '<ul>';
            html += '<li><a data-href="configuration/sensitive-words.html" data-title="敏感词配置" href="javascript:void(0)">敏感词配置</a></li>';
            html += '<li><a data-href="configuration/configuration.html" data-title="问答配置" href="javascript:void(0)">问答配置</a></li>';
            html += '</ul>';
            html += '</dd>';
            html += '</dl>';
            $("#menu").append(html);
        }
        if(v=="3"){
            var html2 = "";
            html2+='<li class="dropDown dropDown_hover"><a href="javascript:;" class="dropDown_A">';
            html2+='<i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>';
            html2+='<ul class="dropDown-menu menu radius box-shadow">';
            html2+='<li><a href="javascript:;" onclick="layer_show(\'添加词库\',\'knowledge/lexicon-add-update.html\',\'510\',\'400\')">';
            html2+='<i class="Hui-iconfont">&#xe616;</i> 词库</a></li>';
            html2+='<li><a href="javascript:;" onclick="layer_show(\'添加分类\',\'knowledge/classification-add.html\',\'510\',\'400\')">';
            html2+='<i class="Hui-iconfont">&#xe681;</i> 分类</a></li>';
            html2+='<li><a href="javascript:;" onclick="layer_show(\'添加知识\',\'knowledge/knowledge-add-update.html\')">';
            html2+='<i class="Hui-iconfont">&#xe620;</i> 知识</a></li>';
            html2+='<li><a href="javascript:;" onclick="layer_show(\'添加寒暄知识\',\'knowledge/greeting-add-update.html\')">';
            html2+='<i class="Hui-iconfont">&#xe60d;</i> 寒暄知识</a></li>';
            html2+='</ul>';
            html2+='</li>';
            $("#quick-access").append(html2);

            var html = "";
            html += '<dl id="menu-product">';
            html += '<dt><i class="Hui-iconfont">&#xe60c;</i> 知识管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>';
            html += '<dd>';
            html += '<ul>';
            html += '<li><a id="lexicon-quick" data-href="knowledge/lexicon.html" data-title="词库管理" href="javascript:void(0)">词库管理</a></li>';
            html += '<li><a data-href="knowledge/classification.html" data-title="分类管理" href="javascript:void(0)">分类管理</a></li>';
            html += '<li><a data-href="knowledge/knowledge.html" data-title="知识管理" href="javascript:void(0)">知识管理</a></li>';
            html += '<li><a data-href="knowledge/greeting.html" data-title="寒暄知识管理" href="javascript:void(0)">寒暄知识管理</a></li>';
            html += '<li><a data-href="knowledge/chat_test.html" data-title="问答测试" href="javascript:void(0)">问答测试</a></li>';
            html += '</ul>';
            html += '</dd>';
            html += '</dl>';
            $("#menu").append(html);
        }
        if(v=="4"){
            var html = "";
            html += '<dl id="menu-tongji">';
            html += '<dt><i class="Hui-iconfont">&#xe61a;</i> 统计分析<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>';
            html += '<dd>';
            html += '<ul>';
            html += '<li><a data-href="statistics/response_type_trend.html" data-title="问答回复类型趋势图" href="javascript:void(0)">问答回复类型趋势图</a></li>';
            html += '<li><a data-href="statistics/response_type.html" data-title="问答回复类型占比" href="javascript:void(0)">问答回复类型占比</a></li>';
            html += '<li><a data-href="statistics/charts-2.html" data-title="时间轴折线图" href="javascript:void(0)">时间轴折线图</a></li>';
            html += '<li><a data-href="statistics/charts-3.html" data-title="区域图" href="javascript:void(0)">区域图</a></li>';
            html += '<li><a data-href="statistics/charts-4.html" data-title="柱状图" href="javascript:void(0)">柱状图</a></li>';
            html += '<li><a data-href="statistics/charts-5.html" data-title="饼状图" href="javascript:void(0)">饼状图</a></li>';
            html += '<li><a data-href="statistics/charts-6.html" data-title="3D柱状图" href="javascript:void(0)">3D柱状图</a></li>';
            html += '</ul>';
            html += '</dd>';
            html += '</dl>';
            $("#menu").append(html);
        }
    });
}
/**
 * 登出
 */
function logout(){
    delCookie("access_token");
    delCookie("user");
    delCookie("role");
    window.location.href="./../../login.html";
}
/**
 * 打开词库界面
 */
function openLexicon(){
    console.log("=============");
    $("#lexicon-quick").trigger("click");
}