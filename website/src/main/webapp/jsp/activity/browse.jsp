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

                                            <div class="row">
                                                <div class="col-md-7">
                                                    <h4 class="card-title">活动信息</h4>

                                                    <div class="form-group row">
                                                        <label class="col-md-2 control-label">活动名称：${activity.name } - ${activity.title }</label>
                                                    </div>

                                                </div>
                                            </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-heading">
                                        <h4 class="card-title inline-block">18 图片</h4>
                                        <div class="pull-right mrg-top-10">
                                            <span class="pdd-right-10">排序 : </span>
                                            <ul class="list-unstyled list-inline inline-block">
                                                <li class="list-inline-item pdd-right-10">
                                                    <a href="" class="text-gray text-semibold active">名称</a>
                                                </li>
                                                <li class="list-inline-item pdd-right-10">
                                                    <a href="" class="text-gray text-semibold">日期</a>
                                                </li>
                                                <li class="list-inline-item pdd-right-10">
                                                    <a href="" class="text-gray text-semibold">浏览数</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="row masonry-grid">
                                            <figure class="col-md-3 masonry-brick mrg-btm-30">
                                                <a href="${pageContext.request.contextPath}/assets/images/others/img-13.jpg" class="gallery-item" data-size="700x500">
                                                    <img class="img-fluid" src="${pageContext.request.contextPath}/assets/images/others/img-13.jpg" alt="">
                                                    <div class="overlay">
                                                        <div class="overlay-content">
                                                            <div class="inline-block">
                                                                <h4 class="caption-title">New Seat</h4>
                                                                <span class="caption-date">27/6/2017</span>
                                                            </div>
                                                            <div class="inline-block pull-right pdd-top-20 font-size-16">
                                                                <i class="ti-heart text-white"></i>
                                                                <span class="text-white">18</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </figure>
                                            <figure class="col-md-3 masonry-brick mrg-btm-30">
                                                <a href="${pageContext.request.contextPath}/assets/images/others/img-14.jpg" class="gallery-item" data-size="700x1000">
                                                    <img class="img-fluid" src="${pageContext.request.contextPath}/assets/images/others/img-14.jpg" alt="">
                                                    <div class="overlay">
                                                        <div class="overlay-content">
                                                            <div class="inline-block">
                                                                <h4 class="caption-title">Time For Sweet</h4>
                                                                <span class="caption-date">25/6/2017</span>
                                                            </div>
                                                            <div class="inline-block pull-right pdd-top-20 font-size-16">
                                                                <i class="ti-heart text-white"></i>
                                                                <span class="text-white">32</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </figure>
                                            <figure class="col-md-3 masonry-brick mrg-btm-30">
                                                <a href="${pageContext.request.contextPath}/assets/images/others/img-15.jpg" class="gallery-item" data-size="700x500">
                                                    <img class="img-fluid" src="${pageContext.request.contextPath}/assets/images/others/img-15.jpg" alt="">
                                                    <div class="overlay">
                                                        <div class="overlay-content">
                                                            <div class="inline-block">
                                                                <h4 class="caption-title">The Higlight</h4>
                                                                <span class="caption-date">17/6/2017</span>
                                                            </div>
                                                            <div class="inline-block pull-right pdd-top-20 font-size-16">
                                                                <i class="ti-heart text-white"></i>
                                                                <span class="text-white">65</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </figure>
                                            <figure class="col-md-3 masonry-brick mrg-btm-30">
                                                <a href="${pageContext.request.contextPath}/assets/images/others/img-16.jpg" class="gallery-item" data-size="700x1000">
                                                    <img class="img-fluid" src="${pageContext.request.contextPath}/assets/images/others/img-16.jpg" alt="">
                                                    <div class="overlay">
                                                        <div class="overlay-content">
                                                            <div class="inline-block">
                                                                <h4 class="caption-title">Corner Art</h4>
                                                                <span class="caption-date">1/6/2017</span>
                                                            </div>
                                                            <div class="inline-block pull-right pdd-top-20 font-size-16">
                                                                <i class="ti-heart text-white"></i>
                                                                <span class="text-white">21</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </figure>
                                            <figure class="col-md-3 masonry-brick mrg-btm-30">
                                                <a href="${pageContext.request.contextPath}/assets/images/others/img-17.jpg" class="gallery-item" data-size="700x500">
                                                    <img class="img-fluid" src="${pageContext.request.contextPath}/assets/images/others/img-17.jpg" alt="">
                                                    <div class="overlay">
                                                        <div class="overlay-content">
                                                            <div class="inline-block">
                                                                <h4 class="caption-title">Stay tall</h4>
                                                                <span class="caption-date">29/5/2017</span>
                                                            </div>
                                                            <div class="inline-block pull-right pdd-top-20 font-size-16">
                                                                <i class="ti-heart text-white"></i>
                                                                <span class="text-white">12</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </figure>
                                            <figure class="col-md-3 masonry-brick mrg-btm-30">
                                                <a href="${pageContext.request.contextPath}/assets/images/others/img-18.jpg" class="gallery-item" data-size="700x500">
                                                    <img class="img-fluid" src="${pageContext.request.contextPath}/assets/images/others/img-18.jpg" alt="">
                                                    <div class="overlay">
                                                        <div class="overlay-content">
                                                            <div class="inline-block">
                                                                <h4 class="caption-title">Rooftop alone</h4>
                                                                <span class="caption-date">23/5/2017</span>
                                                            </div>
                                                            <div class="inline-block pull-right pdd-top-20 font-size-16">
                                                                <i class="ti-heart text-white"></i>
                                                                <span class="text-white">54</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </figure>
                                            <figure class="col-md-3 masonry-brick mrg-btm-30">
                                                <a href="${pageContext.request.contextPath}/assets/images/others/img-19.jpg" class="gallery-item" data-size="700x500">
                                                    <img class="img-fluid" src="${pageContext.request.contextPath}/assets/images/others/img-19.jpg" alt="">
                                                    <div class="overlay">
                                                        <div class="overlay-content">
                                                            <div class="inline-block">
                                                                <h4 class="caption-title">Macarons</h4>
                                                                <span class="caption-date">21/5/2017</span>
                                                            </div>
                                                            <div class="inline-block pull-right pdd-top-20 font-size-16">
                                                                <i class="ti-heart text-white"></i>
                                                                <span class="text-white">38</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </figure>
                                            <figure class="col-md-3 masonry-brick mrg-btm-30">
                                                <a href="${pageContext.request.contextPath}/assets/images/others/img-20.jpg" class="gallery-item" data-size="700x500">
                                                    <img class="img-fluid" src="${pageContext.request.contextPath}/assets/images/others/img-20.jpg" alt="">
                                                    <div class="overlay">
                                                        <div class="overlay-content">
                                                            <div class="inline-block">
                                                                <h4 class="caption-title">Take a break</h4>
                                                                <span class="caption-date">20/5/2017</span>
                                                            </div>
                                                            <div class="inline-block pull-right pdd-top-20 font-size-16">
                                                                <i class="ti-heart text-white"></i>
                                                                <span class="text-white">38</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </figure>
                                            <figure class="col-md-3 masonry-brick mrg-btm-30">
                                                <a href="${pageContext.request.contextPath}/assets/images/others/img-21.jpg" class="gallery-item" data-size="700x1000">
                                                    <img class="img-fluid" src="${pageContext.request.contextPath}/assets/images/others/img-21.jpg" alt="">
                                                    <div class="overlay">
                                                        <div class="overlay-content">
                                                            <div class="inline-block">
                                                                <h4 class="caption-title">Stay Slim</h4>
                                                                <span class="caption-date">18/5/2017</span>
                                                            </div>
                                                            <div class="inline-block pull-right pdd-top-20 font-size-16">
                                                                <i class="ti-heart text-white"></i>
                                                                <span class="text-white">18</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </figure>
                                            <figure class="col-md-3 masonry-brick mrg-btm-30">
                                                <a href="${pageContext.request.contextPath}/assets/images/others/img-22.jpg" class="gallery-item" data-size="700x500">
                                                    <img class="img-fluid" src="${pageContext.request.contextPath}/assets/images/others/img-22.jpg" alt="">
                                                    <div class="overlay">
                                                        <div class="overlay-content">
                                                            <div class="inline-block">
                                                                <h4 class="caption-title">Summer 2016</h4>
                                                                <span class="caption-date">17/5/2017</span>
                                                            </div>
                                                            <div class="inline-block pull-right pdd-top-20 font-size-16">
                                                                <i class="ti-heart text-white"></i>
                                                                <span class="text-white">18</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </figure>
                                            <figure class="col-md-3 masonry-brick mrg-btm-30">
                                                <a href="${pageContext.request.contextPath}/assets/images/others/img-23.jpg" class="gallery-item" data-size="700x1000">
                                                    <img class="img-fluid" src="${pageContext.request.contextPath}/assets/images/others/img-23.jpg" alt="">
                                                    <div class="overlay">
                                                        <div class="overlay-content">
                                                            <div class="inline-block">
                                                                <h4 class="caption-title">Yummy</h4>
                                                                <span class="caption-date">16/5/2017</span>
                                                            </div>
                                                            <div class="inline-block pull-right pdd-top-20 font-size-16">
                                                                <i class="ti-heart text-white"></i>
                                                                <span class="text-white">18</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </figure>
                                            <figure class="col-md-3 masonry-brick mrg-btm-30">
                                                <a href="${pageContext.request.contextPath}/assets/images/others/img-24.jpg" class="gallery-item" data-size="700x500">
                                                    <img class="img-fluid" src="${pageContext.request.contextPath}/assets/images/others/img-24.jpg" alt="">
                                                    <div class="overlay">
                                                        <div class="overlay-content">
                                                            <div class="inline-block">
                                                                <h4 class="caption-title">My sliky hair</h4>
                                                                <span class="caption-date">15/5/2017</span>
                                                            </div>
                                                            <div class="inline-block pull-right pdd-top-20 font-size-16">
                                                                <i class="ti-heart text-white"></i>
                                                                <span class="text-white">18</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </figure>
                                            <figure class="col-md-3 masonry-brick mrg-btm-30">
                                                <a href="${pageContext.request.contextPath}/assets/images/others/img-25.jpg" class="gallery-item" data-size="700x500">
                                                    <img class="img-fluid" src="${pageContext.request.contextPath}/assets/images/others/img-25.jpg" alt="">
                                                    <div class="overlay">
                                                        <div class="overlay-content">
                                                            <div class="inline-block">
                                                                <h4 class="caption-title">My sliky hair</h4>
                                                                <span class="caption-date">15/5/2017</span>
                                                            </div>
                                                            <div class="inline-block pull-right pdd-top-20 font-size-16">
                                                                <i class="ti-heart text-white"></i>
                                                                <span class="text-white">18</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </figure>
                                            <figure class="col-md-3 masonry-brick mrg-btm-30">
                                                <a href="${pageContext.request.contextPath}/assets/images/others/img-26.jpg" class="gallery-item" data-size="700x500">
                                                    <img class="img-fluid" src="${pageContext.request.contextPath}/assets/images/others/img-26.jpg" alt="">
                                                    <div class="overlay">
                                                        <div class="overlay-content">
                                                            <div class="inline-block">
                                                                <h4 class="caption-title">Amazing hand</h4>
                                                                <span class="caption-date">10/5/2017</span>
                                                            </div>
                                                            <div class="inline-block pull-right pdd-top-20 font-size-16">
                                                                <i class="ti-heart text-white"></i>
                                                                <span class="text-white">18</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </figure>
                                            <figure class="col-md-3 masonry-brick mrg-btm-30">
                                                <a href="${pageContext.request.contextPath}/assets/images/others/img-27.jpg" class="gallery-item" data-size="700x500">
                                                    <img class="img-fluid" src="${pageContext.request.contextPath}/assets/images/others/img-27.jpg" alt="">
                                                    <div class="overlay">
                                                        <div class="overlay-content">
                                                            <div class="inline-block">
                                                                <h4 class="caption-title">Lonely breakfast</h4>
                                                                <span class="caption-date">9/5/2017</span>
                                                            </div>
                                                            <div class="inline-block pull-right pdd-top-20 font-size-16">
                                                                <i class="ti-heart text-white"></i>
                                                                <span class="text-white">18</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </figure>

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