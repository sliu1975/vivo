<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no, shrink-to-fit=no">
    <title>vivo自拍活动管理</title>

    <jsp:include page="../common/js_include.jsp" />
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

                            <div class="col-lg-12 col-md-12">
                                <div class="card">
                                    <div class="card-heading">
                                        <h4 class="card-title inline-block pdd-top-5">最新活动</h4>
                                        <a href="" class="btn btn-default pull-right no-mrg">全部活动</a>
                                        <a href="${pageContext.request.contextPath}/activity/toadd" class="btn btn-default pull-right no-mrg">新增活动</a>
                                    </div>
                                    <div class="pdd-horizon-20 pdd-vertical-5">
                                        <div class="overflow-y-auto relative scrollable" style="max-height: 381px">
                                            <table class="table table-lg table-hover">
                                                <tbody>
                                                    <c:if test="${not empty data.results }">
                                                        <c:forEach items="${data.results}" var="activity">
                                                            <tr>
                                                                <td>
                                                                    <div class="list-info">
                                                                        <img class="thumb-img" src="${pageContext.request.contextPath}/image/get?filename=${activity.picture }" alt="">
                                                                        <div class="info">
                                                                            <span class="title">${activity.name}</span>
                                                                            <span class="sub-title">${activity.title}</span>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="mrg-top-10">
                                                                        <span>参与人数:${activity.participant } </span>
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="mrg-top-10">
                                                                        <span>开始日期:${activity.startDate}  结束日期:${activity.endDate}</span>
                                                                    </div>
                                                                </td>


                                                                <td>
                                                                    <div class="relative mrg-top-10">
                                                                        <c:if test="${activity.status eq '1'}">
                                                                            <span class="status online"> </span>
                                                                            <span class="pdd-left-20">进行中</span>
                                                                        </c:if>
                                                                        <c:if test="${activity.status eq '4'}">
                                                                            <span class="status offline"> </span>
                                                                            <span class="pdd-left-20">结束</span>
                                                                        </c:if>
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="mrg-top-10">
                                                                        <c:if test="${activity.status eq '1'}">
                                                                            <a href="${pageContext.request.contextPath}/activity/toedit?id=${activity.id}" class="btn btn-default btn-sm">修改</a>
                                                                            <a href="${pageContext.request.contextPath}/app/${activity.id}" class="btn btn-primary btn-sm" target="_blank" >预览</a>
                                                                            <a href="${pageContext.request.contextPath}/activity/tostop?id=${activity.id}" class="btn btn-warning btn-sm">结束活动</a>
                                                                        </c:if>

                                                                        <a href="${pageContext.request.contextPath}/activity/browse?id=${activity.id}" class="btn btn-info btn-sm">浏览照片</a>

                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </c:if>

                                                </tbody>
                                            </table>
                                        </div>
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

    <!-- build:js assets/js/app.min.js -->
    <!-- core js -->
    <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
    <!-- endbuild -->

    <!-- page js -->
    <script src="${pageContext.request.contextPath}/assets/js/dashboard/dashboard.js"></script>

</body>

</html>