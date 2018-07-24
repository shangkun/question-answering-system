$(function () {
    loadMenu();
});
/*个人信息*/
function mineInfo() {
    layer.open({
        type: 1,
        area: ['300px', '200px'],
        fix: false, //不固定
        maxmin: true,
        shade: 0.4,
        title: '查看信息',
        content: '<div>管理员信息</div>'
    });
}

/*资讯-添加*/
function article_add(title, url) {
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}
/*图片-添加*/
function picture_add(title, url) {
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}
/*产品-添加*/
function product_add(title, url) {
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}
/*用户-添加*/
function member_add(title, url, w, h) {
    layer_show(title, url, w, h);
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
            html += '<dt><i class="Hui-iconfont">&#xe616;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>';
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
            html += '<dt><i class="Hui-iconfont">&#xe613;</i> 系统配置<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>';
            html += '<dd>';
            html += '<ul>';
            html += '<li><a data-href="picture-list.html" data-title="敏感词配置" href="javascript:void(0)">敏感词配置</a></li>';
            html += '<li><a data-href="picture-list.html" data-title="问答配置" href="javascript:void(0)">问答配置</a></li>';
            html += '</ul>';
            html += '</dd>';
            html += '</dl>';
            $("#menu").append(html);
        }
        if(v=="3"){
            var html = "";
            html += '<dl id="menu-product">';
            html += '<dt><i class="Hui-iconfont">&#xe620;</i> 知识管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>';
            html += '<dd>';
            html += '<ul>';
            html += '<li><a data-href="product-brand.html" data-title="词库管理" href="javascript:void(0)">词库管理</a></li>';
            html += '<li><a data-href="product-category.html" data-title="分类管理" href="javascript:void(0)">分类管理</a></li>';
            html += '<li><a data-href="product-list.html" data-title="知识管理" href="javascript:void(0)">知识管理</a></li>';
            html += '<li><a data-href="product-list.html" data-title="寒暄知识管理" href="javascript:void(0)">寒暄知识管理</a></li>';
            html += '<li><a data-href="product-list.html" data-title="问答测试" href="javascript:void(0)">问答测试</a></li>';
            html += '</ul>';
            html += '</dd>';
            html += '</dl>';
            $("#menu").append(html);
        }
        if(v=="4"){
            var html = "";
            html += '<dl id="menu-tongji">';
            html += '<dt><i class="Hui-iconfont">&#xe622;</i> 统计分析<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>';
            html += '<dd>';
            html += '<ul>';
            html += '<li><a data-href="charts-1.html" data-title="折线图" href="javascript:void(0)">折线图</a></li>';
            html += '<li><a data-href="charts-2.html" data-title="时间轴折线图" href="javascript:void(0)">时间轴折线图</a></li>';
            html += '<li><a data-href="charts-3.html" data-title="区域图" href="javascript:void(0)">区域图</a></li>';
            html += '<li><a data-href="charts-4.html" data-title="柱状图" href="javascript:void(0)">柱状图</a></li>';
            html += '<li><a data-href="charts-6.html" data-title="3D柱状图" href="javascript:void(0)">3D柱状图</a></li>';
            html += '<li><a data-href="charts-7.html" data-title="3D饼状图" href="javascript:void(0)">3D饼状图</a></li>';
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