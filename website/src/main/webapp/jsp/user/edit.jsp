<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no, shrink-to-fit=no">
    <title>vivo自拍活动管理</title>

    <jsp:include page="../common/js_include.jsp" />

    <!--
    <link href="${pageContext.request.contextPath}/assets/plugin/fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/plugin/fileinput/themes/explorer-fas/theme.css" media="all" rel="stylesheet" type="text/css"/>
    -->

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/plugin/bootstrap-fileinput/css/fileinput.min.css"/>

</head>

<body>
    <div class="app">
        <div class="layout">
            <jsp:include page="../common/menu_include.jsp" />

            <!-- Page Container START -->
            <div class="page-container">
                <jsp:include page="../common/nav_include.jsp" />

                <!-- Content Wrapper START -->
                <div class="main-content">
                    <div class="container-fluid">

                        <div class="row">

                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-block">
                                        <form action="${pageContext.request.contextPath}/user/editUser" method="post" enctype="multipart/form-data">
                                            <c:if test="${not empty message}">
                                                <div class="row">
                                                    <h4 class="card-title">${message}</h4>
                                                </div>
                                            </c:if>

                                            <div class="row">
                                                <div class="col-md-7">
                                                    <h4 class="card-title">用户信息</h4>

                                                    <div class="form-group row">
                                                        <label for="name" class="col-md-2 control-label">用户ID</label>
                                                        <div class="col-md-10">
                                                            <input type="hidden" name="id" id="id" value="${user.id}" class="form-control"  placeholder="Name">

                                                            <input type="text" name="name" class="form-control" id="name" required value="${user.userName }" placeholder="用户ID">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label for="displayname" class="col-md-2 control-label">姓名</label>
                                                        <div class="col-md-10">
                                                            <input type="text" name="displayname" class="form-control" required id="displayname" value="${user.displayname }" placeholder="姓名">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label for="department" class="col-md-2 control-label">部门</label>
                                                        <div class="col-md-10">
                                                            <input type="text" name="department" class="form-control" id="department" value="${user.displayname }" placeholder="部门">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label for="mobile" class="col-md-2 control-label">联系电话</label>
                                                        <div class="col-md-10">
                                                            <input type="text" name="mobile" class="form-control" id="mobile" value="${user.displayname }" placeholder="联系电话">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label for="category" class="col-md-2 control-label">类型</label>
                                                        <div class="col-md-10">
                                                            <select name="category" id="category" class="form-control">
                                                                <option value="admin" <c:if test="${user.category eq 'admin'}">selected="selected"</c:if>>管理员</option>
                                                                <option value="user" <c:if test="${user.category eq 'user'}">selected="selected"</c:if>>用户</option>
                                                            </select>
                                                        </div>
                                                    </div>

                                                </div>
                                                <div class="col-md-5">

                                                </div>
                                            </div>

                                            <div class="row">
                                                <button type="submit" class="btn btn-primary">保存</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <!-- Content Wrapper END -->

                <!-- Footer START -->
                <jsp:include page="../common/footer_include.jsp" />
                <!-- Footer END -->

            </div>
            <!-- Page Container END -->

        </div>
    </div>

    <!-- build:js assets/js/vendor.js -->
    <jsp:include page="../common/js_footer.jsp" />

    <!-- page plugins js -->
    <script src="${pageContext.request.contextPath}/assets/plugin/bower-jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/maps/jquery-jvectormap-us-aea.js"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/d3/d3.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/nvd3/build/nv.d3.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/jquery.sparkline/index.js"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/chart.js/dist/Chart.min.js"></script>

    <script src="${pageContext.request.contextPath}/assets/plugin/selectize/dist/js/standalone/selectize.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/moment/min/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/bootstrap-daterangepicker/daterangepicker.js"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/bootstrap-datepicker/dist/js/bootstrap-datepicker.js"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>

    <!--
    <script src="${pageContext.request.contextPath}/assets/plugin/fileinput/js/plugins/piexif.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/fileinput/js/plugins/sortable.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/fileinput/js/fileinput.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/fileinput/js/locales/zh.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/fileinput/themes/fas/theme.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/fileinput/themes/explorer-fas/theme.js" type="text/javascript"></script>
    -->

    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugin/bootstrap-fileinput/js/fileinput.js"></script>

    <!-- build:js assets/js/app.min.js -->
    <!-- core js -->
    <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
    <!-- endbuild -->

    <!-- page js -->
    <script>
        $('.datepicker-1').datepicker({
            language: 'zh-CN',
            autoclose: true,
            todayHighlight: true,
            format: 'yyyy-mm-dd',
        });
        $('.datepicker-2').datepicker({
            language: 'zh-CN',
            autoclose: true,
            todayHighlight: true,
            format: 'yyyy-mm-dd',
        });

    </script>

    <script>

        //图片上传
        $("#picture").fileinput({
            uploadUrl: '',
            showCaption:false,
            showRemove:false,
            showUpload:false,
            initialPreviewAsData: true,
            allowedPreviewTypes : ['image'],
            allowedFileExtensions :['jpg', 'jpeg','png'],
            initialPreview:["${pageContext.request.contextPath}/image/get?filename=${activity.picture }"]
        });
        $('.inputfile').on('fileloaded', function(event, file, previewId, index, reader) {
            var id = ('#'+previewId);
            $(id).closest('.file-input').find('.btn-file').css({width:'110px',padding:'7px 12px'});
            $(id).closest('.file-input').find('.btn-file span').text('重新选择');
        });
        $('.file-input').on('click','.fileinput-remove',function(){
            $(this).closest('.file-input').find('.btn-file span').text('选择图片');
        });
    </script>

</body>

</html>