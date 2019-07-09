<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no, shrink-to-fit=no">
    <title>vivo自拍管理-登录</title>

    <!-- Favicon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/logo/favicon.png">

    <!-- plugins css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugin/bootstrap/dist/css/bootstrap.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugin/PACE/themes/blue/pace-theme-minimal.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugin/perfect-scrollbar/css/perfect-scrollbar.min.css" />

    <!-- page plugins css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugin/bower-jvectormap/jquery-jvectormap-1.2.2.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugin/nvd3/build/nv.d3.min.css" />

    <!-- core css -->
    <link href="${pageContext.request.contextPath}/assets/css/ei-icon.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/themify-icons.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/app.css" rel="stylesheet">
</head>

<body>
    <div class="app">
        <div class="authentication">
            <div class="sign-in">
                <div class="row no-mrg-horizon">
                    <div class="col-md-8 no-pdd-horizon d-none d-md-block">
                        <div class="full-height bg" style="background-image: url('${pageContext.request.contextPath}/assets/images/others/logo.jpg')">
                            <div class="img-caption">
                                <h1 class="caption-title">VIVO</h1>
                                <p class="caption-text">自拍</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 no-pdd-horizon">
                        <div class="full-height bg-white height-100">
                            <div class="vertical-align full-height pdd-horizon-70">
                                <div class="table-cell">
                                    <div class="pdd-horizon-15">
                                        <h2>登录</h2>
                                        <p class="mrg-btm-15 font-size-13">请输入用户名和密码</p>
                                        <form method="post" action="${pageContext.request.contextPath}/dologin">
                                            <div class="form-group">
                                                <input type="text" name="userid" id="userid" class="form-control" placeholder="User name">
                                            </div>
                                            <div class="form-group">
                                                <input type="password" name="password" id="password" class="form-control" placeholder="Password">
                                            </div>
                                            <div class="checkbox font-size-12" style="visibility: hidden;">
                                                <input id="agreement" name="agreement" type="checkbox">
                                                <label for="agreement">Keep Me Signed In</label>
                                            </div>
                                            <button type="submit" class="btn btn-info">登录</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="login-footer" style="visibility: hidden;">
                                <img class="img-responsive inline-block" src="assets/images/logo/logo.png" width="100" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- build:js assets/js/vendor.js -->
    <!-- plugins js -->
    <script src="${pageContext.request.contextPath}/assets/plugin/jquery/dist/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/popper.js/dist/umd/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/bootstrap/dist/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/PACE/pace.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/perfect-scrollbar/js/perfect-scrollbar.jquery.js"></script>
    <!-- endbuild -->

    <!-- build:js assets/js/app.min.js -->
    <!-- core js -->
    <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
    <!-- endbuild -->

    <!-- page js -->

</body>

</html>