<%--
  Created by IntelliJ IDEA.
  User: Wee Kim Wee
  Date: 2017/12/28
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar" id="sidebar">
    <script type="text/javascript">
        try { ace.settings.check('sidebar', 'fixed') } catch(e) {}
    </script>
    <ul class="nav nav-list">
        <li class="active">
            <a href="index">
                <i class="icon-dashboard"></i>
                <span class="menu-text">首页 </span>
            </a>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-desktop"></i>
                <span class="menu-text"> 管理员管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">

                <li>
                    <!--查看所有的管理员-->
                    <a href="listAdmins">
                        <i class="icon-double-angle-right"></i> 管理员列表
                    </a>
                </li>
                <!--<li>
                    <a href="#" class="dropdown-toggle">
                        <i class="icon-double-angle-right"></i> 管理员
                    </a>

                </li>-->
            </ul>
        </li>

        <li>
            <a class="dropdown-toggle">
                <i class="icon-list"></i>
                <span class="menu-text"> 用户管理 </span>
                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <!--普通用户管理-->
                    <a href="#">
                        <i class="icon-double-angle-right"></i> 普通用户管理
                    </a>
                </li>
                <li>
                    <!--vip用户管理-->
                    <a href="#" class="dropdown-toggle">
                        <i class="icon-pencil"></i> VIP用户管理
                    </a>
                </li>
                <li>
                    <!--统计-->
                    <a href="#" class="dropdown-toggle">
                        <i class="icon-pencil"></i> 统计
                    </a>
                </li>
            </ul>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-tag"></i>
                <span class="menu-text"> 书籍管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="#">
                        <i class="icon-double-angle-right"></i> 书籍列表
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-double-angle-right"></i> 统计
                    </a>
                </li>
            </ul>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-tag"></i>
                <span class="menu-text"> 订单管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="#">
                        <i class="icon-double-angle-right"></i> 订单列表
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-double-angle-right"></i> 统计
                    </a>
                </li>
            </ul>
        </li>
    </ul>
    <!-- /.nav-list -->

    <div class="sidebar-collapse" id="sidebar-collapse">
        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
    </div>

    <script type="text/javascript">
        try { ace.settings.check('sidebar', 'collapsed') } catch(e) {}
    </script>
</div>