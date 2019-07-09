<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no, shrink-to-fit=no">
    <title>vivo自拍活动管理</title>

    <jsp:include page="jsp/common/js_include.jsp" />
</head>

<body>
    <div class="app">
        <div class="layout">
            <jsp:include page="jsp/common/menu_include.jsp" />

            <!-- Page Container START -->
            <div class="page-container">
                <jsp:include page="jsp/common/nav_include.jsp" />

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
                        <div class="row" style="visibility: hidden">
                            <div class="col-lg-4 col-md-6">
                                <div class="card">
                                    <div class="card-block">
                                        <div class="inline-block">
                                            <h1 class="no-mrg-vertical">$168.90</h1>
                                            <p>This Month</p>
                                        </div>
                                        <div class="pdd-top-25 inline-block pull-right">
                                            <span class="label label-success label-lg mrg-left-5">+18%</span>
                                        </div>
                                        <div class="mrg-top-25">
                                            <div id="bar-config"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-block">
                                        <p class="mrg-btm-5">This Quarter</p>
                                        <h1 class="no-mrg-vertical font-size-35">$3,936<b class="font-size-16">.80</b></h1>
                                        <p class="text-semibold">Total Revenue</p>
                                        <div class="mrg-top-10">
                                            <h2 class="no-mrg-btm">88</h2>
                                            <span class="inline-block mrg-btm-10 font-size-13 text-semibold">Online Revenue</span>
                                            <span class="pull-right pdd-right-10 font-size-13">70%</span>
                                            <div class="progress progress-primary">
                                                <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:70%">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="mrg-top-10">
                                            <h2 class="no-mrg-btm">69</h2>
                                            <span class="inline-block mrg-btm-10 font-size-13 text-semibold">Offline Revenue</span>
                                            <span class="pull-right pdd-right-10 font-size-13">50%</span>
                                            <div class="progress progress-success">
                                                <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:50%">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-6">
                                <div class="card">
                                    <div class="card-heading border bottom">
                                        <h4 class="card-title">Latest Feed</h4>
                                    </div>
                                    <div class="widget-feed">
                                        <ul class="list-info overflow-y-auto relative scrollable" style="max-height: 340px">
                                            <li class="border bottom mrg-btm-10">
                                                <div class="pdd-vertical-10">
                                                    <span class="thumb-img bg-primary">
															<span class="text-white">JH</span>
                                                    </span>
                                                    <div class="info">
                                                        <a href="" class="text-link"><span class="title"><b class="font-size-15">Jordan Hurst</b></span></a>
                                                        <span class="sub-title">5 mins ago</span>
                                                    </div>
                                                    <div class="mrg-top-10">
                                                        <p class="no-mrg-btm">Remember, a Jedi can feel the Force flowing through him. You mean it controls your actions? Partially.</p>
                                                    </div>
                                                    <ul class="feed-action">
                                                        <li>
                                                            <a href="">
                                                                <i class="ti-heart text-danger pdd-right-5"></i>
                                                                <span>168</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="">
                                                                <i class="ti-comments text-primary pdd-right-5"></i>
                                                                <span>18</span>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </li>
                                            <li class="border bottom mrg-btm-10">
                                                <div class="pdd-vertical-10">
                                                    <span class="thumb-img bg-success">
															<span class="text-white">JW</span>
                                                    </span>
                                                    <div class="info">
                                                        <a href="" class="text-link"><span class="title"><b class="font-size-15">Jennifer Watkins</b></span></a>
                                                        <span class="sub-title">5 mins ago</span>
                                                    </div>
                                                    <div class="mrg-top-15">
                                                        <p>What good's a reward if you ain't around to use it?</p>
                                                    </div>
                                                    <ul class="feed-action">
                                                        <li>
                                                            <a href="">
                                                                <i class="ti-heart text-danger pdd-right-5"></i>
                                                                <span>168</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="">
                                                                <i class="ti-comments text-primary pdd-right-5"></i>
                                                                <span>18</span>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </li>
                                            <li class="border bottom">
                                                <div class="pdd-vertical-10">
                                                    <span class="thumb-img bg-warning">
															<span class="text-white">MB</span>
                                                    </span>
                                                    <div class="info">
                                                        <a href="" class="text-link"><span class="title"><b class="font-size-15">Michael Birch</b></span></a>
                                                        <span class="sub-title">5 mins ago</span>
                                                    </div>
                                                    <div class="mrg-top-15">
                                                        <p>What good's a reward if you ain't around to use it? Besides, attacking that battle station ain't my idea of courage.</p>
                                                    </div>
                                                    <ul class="feed-action">
                                                        <li>
                                                            <a href="">
                                                                <i class="ti-heart text-danger pdd-right-5"></i>
                                                                <span>168</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="">
                                                                <i class="ti-comments text-primary pdd-right-5"></i>
                                                                <span>18</span>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-6">
                                <div class="card">
                                    <div class="card-heading border bottom">
                                        <h4 class="card-title">Project</h4>
                                    </div>
                                    <div class="card-block">
                                        <div class="pdd-vertical-10">
                                            <ul class="list-info">
                                                <li>
                                                    <img class="thumb-img img-circle" src="assets/images/others/thumb-1.jpg" alt="">
                                                    <div class="info">
                                                        <span class="title"><a href="" class="text-link text-dark"><b class="font-size-15">Devolopment - Android App</b></a></span>
                                                        <span class="sub-title">Android App</span>
                                                        <div class="float-object dropdown right">
                                                            <i class="ti-android-o"></i>
                                                            <a href="" class="btn btn-icon btn-flat btn-rounded dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                                                <i class="ti-more"></i>
                                                            </a>
                                                            <ul class="dropdown-menu">
                                                                <li>
                                                                    <a href="">
                                                                        <i class="ti-files pdd-right-10"></i>
                                                                        <span>Duplicate</span>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="">
                                                                        <i class="ti-smallcap pdd-right-10"></i>
                                                                        <span>Edit</span>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="">
                                                                        <i class="ti-image pdd-right-10"></i>
                                                                        <span>Add Images</span>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <div class="mrg-top-20">
                                                        <p>All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary.</p>
                                                    </div>
                                                    <div class="mrg-top-30">
                                                        <b class="pull-left lh-2-5 pdd-right-15">Team: </b>
                                                        <ul class="list-members list-inline">
                                                            <li>
                                                                <a href="">
                                                                    <img src="assets/images/avatars/thumb-1.jpg" alt="">
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a href="">
                                                                    <img src="assets/images/avatars/thumb-2.jpg" alt="">
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a href="">
                                                                    <img src="assets/images/avatars/thumb-3.jpg" alt="">
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a href="">
                                                                    <img src="assets/images/avatars/thumb-4.jpg" alt="">
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a href="">
                                                                    <img src="assets/images/avatars/thumb-5.jpg" alt="">
                                                                </a>
                                                            </li>
                                                            <li class="all-members">
                                                                <a href="">
                                                                    <span>+2</span>
                                                                </a>
                                                            </li>
                                                            <li class="add-member">
                                                                <a href="">
                                                                    <span>+</span>
                                                                </a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                    <div class=" mrg-top-30">
                                                        <span>Due date: <span class="text-success text-semibold">23/7/2017</span></span>
                                                    </div>
                                                    <div class="mrg-top-30">
                                                        <p class="mrg-btm-5">Task completed: <span class="text-dark text-semibold">7/10</span></p>
                                                        <div class="progress progress-info">
                                                            <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:70%">
                                                            </div>
                                                        </div>
                                                    </div>

                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Content Wrapper END -->

                <!-- Footer START -->
                <jsp:include page="jsp/common/footer_include.jsp" />
                <!-- Footer END -->

            </div>
            <!-- Page Container END -->

        </div>
    </div>

    <!-- build:js assets/js/vendor.js -->
    <jsp:include page="jsp/common/js_footer.jsp" />

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