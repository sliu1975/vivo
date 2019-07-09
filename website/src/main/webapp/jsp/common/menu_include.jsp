<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Side Nav START -->
<div class="side-nav">
    <div class="side-nav-inner">
        <div class="side-nav-logo">
            <a href="${pageContext.request.contextPath}/home">
                <div class="logo logo-dark" style="background-image: url('${pageContext.request.contextPath}/assets/images/logo/logo.png')"></div>

                <div class="logo logo-white" style="background-image: url('${pageContext.request.contextPath}/assets/images/logo/logo-white.png')"></div>

            </a>
            <div class="mobile-toggle side-nav-toggle">
                <a href="">
                    <i class="ti-arrow-circle-left"></i>
                </a>
            </div>
        </div>
        <ul class="side-nav-menu scrollable">
            <li class="nav-item active">
                <a class="mrg-top-30" href="${pageContext.request.contextPath}/home">
                    <span class="icon-holder">
                        <i class="ti-home"></i>
                    </span>
                    <span class="title">首页</span>
                </a>
            </li>
            <li class="nav-item dropdown">
                <a class="dropdown-toggle" href="javascript:void(0);">
                    <span class="icon-holder">
                        <i class="ti-package"></i>
                    </span>
                    <span class="title">活动</span>
                    <span class="arrow">
                        <i class="ti-angle-right"></i>
                    </span>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="${pageContext.request.contextPath}/activity/index">最新活动</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/activity/history">历史活动</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/activity/summary">活动分析</a>
                    </li>
                </ul>
            </li>

            <li class="nav-item dropdown">
                <a class="dropdown-toggle" href="javascript:void(0);">
                    <span class="icon-holder">
                        <i class="ti-file"></i>
                    </span>
                    <span class="title">系统设置</span>
                    <span class="arrow">
                        <i class="ti-angle-right"></i>
                    </span>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="${pageContext.request.contextPath}/user/index">用户</a>
                    </li>
                    <!--
                    <li>
                        <a href="${pageContext.request.contextPath}/point/index">网点</a>
                    </li>
                    -->
                </ul>
            </li>
            <li class="nav-item dropdown">
                <a class="dropdown-toggle" href="javascript:void(0);">
                    <span class="icon-holder">
                        <i class="ti-layout-media-overlay"></i>
                    </span>
                    <span class="title">营销</span>
                    <span class="arrow">
                        <i class="ti-angle-right"></i>
                    </span>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="basic-table.html">微信参与者</a>
                    </li>
                    <li>
                        <a href="data-table.html">活动数据</a>
                    </li>
                </ul>
            </li>

        </ul>
    </div>
</div>
<!-- Side Nav END -->