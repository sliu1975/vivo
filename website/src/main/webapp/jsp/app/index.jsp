<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="format-detection" content="telephone=no"/>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/logo/favicon.png">

    <title>vivo自拍</title>
    <script src="${pageContext.request.contextPath}/assets/app/js/flexible.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/app/css/index.css"/>
</head>
<body>
    <!--音乐 Start
    <aside id="music" class="music-pulse">
        <audio id="audio" src="music.mp3" type="audio/mpeg"></audio>
    </aside>
	-->
    <!--音乐 End-->
    <!--加载效果 Start-->
    <div class="ui-loading-wrap" id="loading">
        <i class="ui-loading"></i>
        <p>加载中...</p>
    </div>
    <!--加载效果 End-->
    <!--第一屏 Start-->
    <section class="section" id="first" style="display:none">
        <!--<img src="img/blank.gif" data-src="img/slogan.png" class="slogan"/>
        <span style="position: relative; left: 50px; top: 3px;z-index:100">${count}</span>
        -->

		<div class="frame">
			<img src="${pageContext.request.contextPath}/assets/app/img/blank.gif" data-src="${pageContext.request.contextPath}/image/getTextImage?app=${app }&&count=${count }" id="pic" class="father-img"/>
		</div>

        <button type="button" id="btnGenerate" class="btn-large-x" data-track="生成合照">
            <input id="upload" name="upload" type="file" accept="image/*" onfocus="this.blur()"/>
            拍照或者上传照片
        </button>

    </section>
	
    <!--第一屏 End-->
    <!--第二屏 Start-->
    <section class="section section-top" style="display:none" id="second">
        <div class="frame" id="frame">
            <img src="${pageContext.request.contextPath}/assets/app/img/blank.gif" data-src="${pageContext.request.contextPath}/image/getTextImage?app=${app }&&count=${count }" class="word" id="word"/>
            <img src="${pageContext.request.contextPath}/assets/app/img/blank.gif" id="frameImg" class="frame-img"/>
        </div>
        <div class="ui-justify">
            <button type="button" id="btnConfirm" class="btn-middle" data-track="确认">确认</button>
            <button type="button" id="btnRotate" class="btn-middle">旋转</button>
            <button type="button" id="btnCancel" class="btn-middle" data-track="取消">取消</button>
        </div>
    </section>
    <!--第二屏 End-->
    <!--第三屏 Start-->
    <section class="section section-top2" style="display: none" id="third">
        <div class="frame">
            <img src="${pageContext.request.contextPath}/assets/app/img/blank.gif" class="db-img" id="compose"/>
        </div>
        <div class="ui-justify">
            <button type="button" id="btnShare" class="btn-large" data-track="分享现在">
                <h1>分享现在</h1>
                <h2>朋友圈晒图片</h2>
            </button>
            <button type="button" id="btnBack" class="btn-large">
                <h1>回到过去</h1>
                <h2>重新上传照片</h2>
            </button>
        </div>
    </section>
    <!--第三屏 End-->
    <!--底部花纹 Start
    <footer class="footer">
    </footer>
	-->
    <!--底部花纹 End-->
    <!--弹出框 Start-->
    <aside class="dialog" style="display: none" id="dialog">
        <p>长按即可将图片保存至手机</p>
        <button type="button" class="btn-large-x">我知道了</button>
    </aside>
    <!--弹出框 End-->
    <script src="${pageContext.request.contextPath}/assets/app/js/touch.js"></script>
    <script src="${pageContext.request.contextPath}/assets/app/js/hidpi-canvas.js"></script>
    <script src="${pageContext.request.contextPath}/assets/app/js/index.bundle.js"></script>
</body>
</html>