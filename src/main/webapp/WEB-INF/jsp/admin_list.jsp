<%--
  Created by IntelliJ IDEA.
  User: Wee Kim Wee
  Date: 2017/12/28
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>首页 - BookStore后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- basic styles -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="assets/css/font-awesome.min.css"/>

    <!--[if IE 7]>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->

    <!-- page specific plugin styles -->

    <!-- ace styles -->

    <link rel="stylesheet" href="assets/css/ace.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-skins.min.css"/>

    <!--[if lte IE 8]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->

    <script src="assets/js/ace-extra.min.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<jsp:include page="/common/header.jsp"></jsp:include>
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>
        <jsp:include page="/common/menu.jsp"></jsp:include>

        <div class="main-content">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="icon-home home-icon"></i>
                        <a href="index">首页</a>
                    </li>

                </ul>
                <!-- .breadcrumb -->
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h3>
                        管理员管理
                        <small>
                            <i class="icon-double-angle-right"></i>
                        </small>
                        列表
                        <small>
                            <i class="icon-double-angle-right"></i>
                        </small>
                        <a href="toSignup">+新增</a>
                    </h3>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="page-content">
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="table-header">
                                        管理员列表
                                        <input id="query" type="search" name="query" placeholder="查询条件"
                                               value="${query }">
                                        <select id="jurisdiction" name="jurisdiction">
                                            <option value="">请选择</option>
                                            <option value="1">用户管理员</option>
                                            <option value="2">书籍管理员</option>
                                            <option value="3">订单管理员</option>
                                            <option value="4">报表管理员</option>
                                        </select>
                                        <button onclick="search()" class="btn help-btn">查询</button>
                                        <a id="hidden_jurisdiction" style="display: none">${jurisdiction}</a>
                                    </div>

                                    <div class="table-responsive">
                                        <table id="sample-table-2"
                                               class="table table-striped table-bordered table-hover">
                                            <thead>
                                            <tr>
                                                <th class="center">
                                                    ID
                                                </th>
                                                <th>登录名</th>
                                                <th>状态</th>
                                                <th class="hidden-480">
                                                    <i class="icon-time bigger-110 hidden-480"></i>创建时间
                                                </th>
                                                <th>权限</th>
                                                <th>
                                                    <i class="icon-time bigger-110 hidden-480"></i> 上一次登录时间
                                                </th>

                                                <th>操作</th>
                                            </tr>
                                            </thead>

                                            <tbody id="dataTbody">
                                            <c:forEach items="${pageInfo.list }" var="admin">
                                                <tr>
                                                    <th>${admin.id }</th>
                                                    <th>${admin.name }</th>
                                                    <c:if test="${admin.isDelete==0}">
                                                        <th>
                                                            正常
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${admin.isDelete==99}">
                                                        <th>
                                                            冻结
                                                        </th>
                                                    </c:if>
                                                    <th><fmt:setLocale value="zh"/>
                                                        <fmt:formatDate value="${admin.createTime}"
                                                                        pattern="yyyy-MM-d HH:mm:ss EEEE"/></th>
                                                    <c:if test="${admin.jurisdiction==0}">
                                                        <th>
                                                            超级管理员
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${admin.jurisdiction==1}">
                                                        <th>
                                                            用户管理员
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${admin.jurisdiction==2}">
                                                        <th>
                                                            图书管理员
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${admin.jurisdiction==3}">
                                                        <th>
                                                            订单管理员
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${admin.jurisdiction==4}">
                                                        <th>
                                                            报表管理员
                                                        </th>
                                                    </c:if>
                                                    <th><fmt:setLocale value="zh"/>
                                                        <fmt:formatDate value="${admin.lastLoginTime}"
                                                                        pattern="yyyy-MM-d HH:mm:ss EEEE"/></th>

                                                    <c:if test="${admin.isDelete==0}">
                                                        <th>
                                                            <a onclick="del(${admin.id})"> 禁用 </a>
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${admin.isDelete==1}">
                                                        <th>
                                                            <a onclick="del(${admin.id})"> 禁用 </a>
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${admin.isDelete==99}">
                                                        <th>
                                                            <a onclick="jiechu(${admin.id})"> 解除 </a>
                                                        </th>
                                                    </c:if>

                                                </tr>
                                            </c:forEach>

                                            </tbody>
                                        </table>

                                        <div class="row">
                                            <!--分页文字信息  -->
                                            <div class="col-md-6">当前 ${pageInfo.pageNum }页,总${pageInfo.pages }
                                                页,总 ${pageInfo.total } 条记录
                                            </div>
                                            <!-- 分页条信息 -->
                                            <div class="col-md-6">
                                                <nav aria-label="Page navigation">
                                                    <ul class="pagination">
                                                        <li><a onclick="_firstPage()">首页</a>
                                                        </li>
                                                        <c:if test="${pageInfo.hasPreviousPage }">
                                                            <li>
                                                                <a onclick="_orderPage()"
                                                                   aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                                                                </a></li>
                                                        </c:if>


                                                        <c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
                                                            <c:if test="${page_Num == pageInfo.pageNum }">
                                                                <li class="active"><a href="#">${page_Num }</a></li>
                                                            </c:if>
                                                            <c:if test="${page_Num != pageInfo.pageNum }">
                                                                <li>
                                                                    <a onclick="_page_Num(this)">${page_Num }</a>
                                                                </li>
                                                            </c:if>

                                                        </c:forEach>
                                                        <c:if test="${pageInfo.hasNextPage }">
                                                            <li>
                                                                <a onclick="_nextPage()"
                                                                   aria-label="Next"> <span
                                                                        aria-hidden="true">&raquo;</span>
                                                                </a></li>
                                                        </c:if>
                                                        <li>
                                                            <a onclick="_lastPage()">末页</a>
                                                        </li>
                                                    </ul>
                                                </nav>
                                            </div>
                                        </div>
                                    </div><!-- /main-container -->
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /.page-content -->
    </div>
    <!-- /.main-content -->
    <%--<jsp:include page="/common/skin.jsp"></jsp:include>--%>
</div>
<!-- /.main-container-inner -->

<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="icon-double-angle-up icon-only bigger-110"></i>
</a>
</div>

<!-- basic scripts -->

<!--[if !IE]> -->
<!--confirm-delete指向HTML中的模式框(modal)代码 -->


<jsp:include page="/common/commonjs.jsp"></jsp:include>

</body>

</html>
<script>
    function search() {
        var $query = $('input[name=query]').val();
        var $encodeQuery = encodeURI(encodeURI($query));
        var obj = document.getElementById("jurisdiction");
        for (i = 0; i < obj.length; i++) {//下拉框的长度就是它的选项数.

            if (obj[i].selected == true) {
                var text = obj[i].value;//获取当前选择项的值.
            }
        }

        window.location.href = 'listAdmins?pn=${page_Num }&query=' + $encodeQuery + '&jurisdiction=' + text;
    }
</script>

<!--显示末页-->
<script>
    function _lastPage() {
        var $query = $('input[name=query]').val();
        var encodeQuery = encodeURI(encodeURI($query));
        var $jurisdiction= $('#hidden_jurisdiction').text();
        location.href = 'listAdmins?pn=${pageInfo.pages}&query=' + encodeQuery + '&jurisdiction='+$jurisdiction;
    }
</script>
<!--显示首页-->
<script>
    function _firstPage() {
        var $query = $('input[name=query]').val();
        var encodeQuery = encodeURI(encodeURI($query));
        var $jurisdiction= $('#hidden_jurisdiction').text();
        location.href = 'listAdmins?pn=1&query=' + encodeQuery + '&jurisdiction='+$jurisdiction;
    }

</script>
<!--显示123456页-->
<script>
    function _orderPage() {
        var $query = $('input[name=query]').val();
        var encodeQuery = encodeURI(encodeURI($query));
        var $jurisdiction= $('#hidden_jurisdiction').text();
        location.href = 'listAdmins?pn=${pageInfo.pageNum-1}&query=' + encodeQuery + '&jurisdiction=' +$jurisdiction;
    }

</script>
<!--活跃的页码页-->
<script>
    function _page_Num(page) {
        var $page_num = page.innerHTML;
        var $query = $('input[name=query]').val();
        var encodeQuery = encodeURI(encodeURI($query));
        var $jurisdiction= $('#hidden_jurisdiction').text();
        location.href = 'listAdmins?pn=' + $page_num + '&query=' + encodeQuery + '&jurisdiction=' +$jurisdiction;
    }
</script>

<!--下一页 -->
<script>
    function _nextPage() {
        var $query = $('input[name=query]').val();
        var encodeQuery = encodeURI(encodeURI($query));
        var $jurisdiction= $('#hidden_jurisdiction').text();
        location.href = 'listAdmins?pn=${pageInfo.pageNum+1}&query=' + encodeQuery + '&jurisdiction=' +$jurisdiction;
    }
</script>


<script>
    function del(aid) {
        if (aid == 1) {
            alert("您没有权限");
            return;
        }
        var del = window.confirm("您确定要禁用这个管理员吗？");
        if (del) {
            $.ajax({
                type: "GET",
                url: "banAdmin" + "/" + aid,
                dataType: "json",
                success: function (data) {
                    alert(data.message);
                    location.reload();
                }
            });
        }
    }

    function jiechu(aid) {
        var flag = window.confirm("您确定要解禁这个管理员吗？");
        if (del) {
            $.ajax({
                type: "GET",
                url: "unbanAdmin" + "/" + aid,
                dataType: "json",
                success: function (data) {
                    alert(data.message);
                    location.reload();
                }
            });
        }
    }
</script>


