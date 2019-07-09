<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no, shrink-to-fit=no">
    <title>Espire - Bootstrap 4 Admin Template</title>

    <!-- Favicon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/logo/favicon.png">

    <!-- plugins css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugin/bootstrap/dist/css/bootstrap.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugin/PACE/themes/blue/pace-theme-minimal.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugin/perfect-scrollbar/css/perfect-scrollbar.min.css" />

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
            <div class="sign-up">
                <div class="row no-mrg-horizon">
                    <div class="col-md-4 no-pdd-horizon d-none d-md-block">
                        <div class="full-height bg" style="background-image: url('assets/images/others/img-31.jpg')">
                            <div class="vertical-align full-height pdd-horizon-70">
                                <div class="table-cell">
                                    <div class="row">
                                        <div class="mr-auto ml-auto col-md-10">
                                            <div class="text-right">
                                                <img class="img-responsive mrg-left-auto mrg-btm-15" src="assets/images/logo/logo-white.png" alt="" width="170">
                                                <p class="text-white lead text-opacity lh-1-7">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-8 bg-white no-pdd-horizon">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="full-height height-100">
                                    <div class="vertical-align full-height pdd-horizon-70">
                                        <div class="table-cell">
                                            <div class="pdd-horizon-15">
                                                <h1 class="mrg-btm-30">Create Your account</h1>
                                                <form>
                                                    <div class="form-group">
                                                        <label class="text-normal text-dark">User Name</label>
                                                        <input type="text" class="form-control">
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="text-normal text-dark">Email Address</label>
                                                        <input type="email" class="form-control">
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="text-normal text-dark">Password</label>
                                                        <input type="password" class="form-control" placeholder="Password">
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="text-normal text-dark">Confirm Password</label>
                                                        <input type="password" class="form-control" placeholder="Password">
                                                    </div>
                                                    <div class="checkbox font-size-13 mrg-btm-20">
                                                        <input id="agreement" name="agreement" type="checkbox" checked="">
                                                        <label for="agreement">Remember Me</label>
                                                    </div>
                                                    <div class="form-group">
                                                        <button type="button" class="btn btn-primary btn-block border-radius-6">Create New Account</button>
                                                    </div>
                                                </form>
                                                <p>Already have an account? <a href="">Sign In</a></p>
                                                <hr>
                                                <small>By signing up you agree to out <a href="">Terms & Policy</a></small>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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