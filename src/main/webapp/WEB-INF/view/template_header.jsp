<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="row border-bottom">
    <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <a class="navbar-minimalize minimalize-styl-2 btn btn-primary" href="#"><i class="fa fa-bars"></i></a>
        </div>
        <ul class="nav navbar-top-links navbar-right">
            <li>
                <span class="m-r-sm text-muted welcome-message">欢迎使用</span>
            </li>
            <li>
                <a href="${ctx}/logout"> <i class="fa fa-sign-out"></i> 注销</a>
            </li>
        </ul>
    </nav>
</div>
<div class="row wrapper border-bottom white-bg page-heading" >
    <div class="col-lg-10">
        <h2>
            <ol class="breadcrumb" id="header_level_nav" v-cloak>
                <li v-for="level in levels"><a :href="level.url">{{level.text}}</a></li>
            </ol>
        </h2>
    </div>
</div>

<script type="text/javascript">

    // level1Name / level2Name / funcName
    function initHeaderNav(menuDatas){
        new Vue({
            el:'#header_level_nav',
            computed:{
                levels: function () {
                    for (var i = 0; i < menuDatas.length; i++) {
                        var menu = menuDatas[i];

                        for (var j = 0; j < menu.children.length; j++) {
                            var childMenu = menu.children[j];
                            if (childMenu.attributes.url == JS_PAGE_NAME || childMenu.attributes.url == JS_FROM_URL) {
                                var navList =  [
                                    {url: "#", text: menu.text},
                                    {url: '${ctx}'+childMenu.attributes.url, text: childMenu.text}
                                ];

                                // append func name if have : 新建/编辑 等
                                if(JS_PAGE_PARAMS["funcId"]){
                                    $.each(JS_FUNC_LIST, function (index, func) {
                                        if ((func.fun_id & JS_PAGE_PARAMS["funcId"]) == func.fun_id) {
                                            navList = navList.concat({url: "#", text: func.btn_name});
                                            return false;
                                        }
                                    });
                                }

                                return navList;
                            }
                        }
                    }
                }
            }
        });
    }

</script>