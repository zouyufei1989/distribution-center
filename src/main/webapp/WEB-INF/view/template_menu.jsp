<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav metismenu" id="side-menu" v-cloak>
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <span> <img alt="image" class="img-rounded" style="width:50px;height:50px" src="${ctx}/resource/picture/avatar.png"/></span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear">
                                <span class="block m-t-xs"><strong class="font-bold" id="loginUserName">{{loginUserName}}</strong></span>
                                <span class="text-muted text-xs block" id="loginUserRole">{{loginUserRole}} <b class='caret'></b> </span>
                            </span>
                        </a>

                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a href="${ctx}/logout">退出登录</a></li>
                        </ul>
                    </div>
                </li>

                <li v-for="menu in menuData" :class="{active:hasChildActive(menu)}">
                    <a href="#">
                        <i :class="['fa', menu.iconCls]"></i>
                        <span class="nav-label">{{menu.text}}</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level collapse">
                        <li v-for="child in menu.children" :class="{active:isActive(child)}">
                            <a v-if="isActive(child)" href="#">{{child.text}}</a>
                            <a v-else :href="'${ctx}'+child.attributes.url">{{child.text}}</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</div>

<script type="text/javascript">

    function initMenuList() {

        $.ajax({
            url: '${ctx}/tree/getCategorys',
            type: 'post',
            async: false,
            cache: false,
            success: function (data) {
                new Vue({
                    el: '#side-menu',
                    data: {
                        loginUserName: '${sessionScope.user.nickName}',
                        loginUserRole: '${sessionScope.user.roleName}',
                        menuData: data.rows
                    },
                    methods:{
                        isActive:function(menu){
                            return menu.attributes.url == JS_PAGE_NAME || menu.attributes.url == JS_FROM_URL;
                        },
                        hasChildActive:function(menu){
                            for(var i=0;i<menu.children.length;i++){
                                if(menu.children[i].attributes.url == JS_PAGE_NAME || menu.children[i].attributes.url == JS_FROM_URL){
                                    return true;
                                }
                            }
                            return false;
                        }
                    }
                });
                initHeaderNav(data.rows);

                initInspina();
            }
        });
    }

    $(document).ready(function () {
        // 加载菜单栏
        initMenuList();

        $(".select2_demo_3[search-param]").select2({
            dropdownAutoWidth: true
        });
    });
</script>