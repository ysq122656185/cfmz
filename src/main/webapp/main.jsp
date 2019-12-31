<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<html lang="en">
<head>
    <%--引入bootStrap的css样式--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/boot/css/bootstrap.min.css">
    <%--引入jqgrid与bootstrap整合的css样式--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入jquery的js文件--%>
    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-2.2.1.min.js"></script>
    <%--引入bootStrap的js文件--%>
    <script src="${pageContext.request.contextPath}/statics/boot/js/bootstrap.min.js"></script>
    <%--引入jqgrid的js文件--%>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/ajaxfileupload.js"></script>
    <title>驰名法洲后台管理系统</title>
</head>
<body>
<%--导航条--%>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">驰名法洲后台管理系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎光临：${loginAdmin.nickname}</a></li>
                <li><a href="#">安全退出</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<%--栅格系统--%>
<div class="container-fluid">
    <div class="row">
        <%--手风琴--%>
        <div class="col-sm-2 text-center">
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title text-center">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                               aria-expanded="true" aria-controls="collapseOne">
                                <h3>轮播图管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
                         aria-labelledby="headingOne">
                        <div class="panel-body text-center">
                            <a class="btn btn-success"
                               href="javascript:$('#content').load('${pageContext.request.contextPath}/banner/banner-show.jsp')">展示轮播图</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title text-center">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                <h3>专辑模块</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body text-center">
                            <a class="btn btn-success"
                               href="javascript:$('#content').load('${pageContext.request.contextPath}/album/album-show.jsp')">展示专辑</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                <h3>文章模块</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingThree">
                        <div class="panel-body">
                            <a class="btn btn-success"
                               href="javascript:$('#content').load('${pageContext.request.contextPath}/article/article-show.jsp')">展示文章</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFour">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                <h3>用户模块</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingFour">
                        <div class="panel-body">
                            <a class="btn btn-success"
                               href="javascript:$('#content').load('${pageContext.request.contextPath}/user/user-show.jsp')">展示用户</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--巨幕和图片--%>
        <div class="col-sm-10" id="content">
            <div class="jumbotron">
                <h2>欢迎光临驰名法洲后台管理系统</h2>
            </div>
            <img src="${pageContext.request.contextPath}/img/shouye.jpg" style="width: 100%">
        </div>
    </div>
</div>
<%--页脚--%>
<div class="panel panel-footer text-center" style="position: absolute;bottom: 0px;width: 100%">
    驰名法洲后台管理系统 @百知教育2019-12-18
</div>
</body>
</html>