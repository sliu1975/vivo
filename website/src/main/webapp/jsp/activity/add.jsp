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


    <script>
        function validate(){
            if($("#name").val().length == 0){
                alert("名称不能为空.");
                return false;
            }

            if($("#title").val().length == 0){
                alert("标语不能为空.");
                return false;
            }

            var code=$("#name").val();

            var values="0";

            $.ajax({
                type: 'Post',
                dataType: 'json',
                data: {code:code},
                async:false,
                url: "${pageContext.request.contextPath}/goods/checkgoods",
                success: function (data) {
                    values = data.exist;

                    if(values!="1")
                    {
                        //alert("该用户已经存在，请选择输入别的用户编号.");
                        return false;
                    }
                }
            });

            return true;
        }
    </script>

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
                                        <form action="${pageContext.request.contextPath}/activity/addActivity" method="post" enctype="multipart/form-data">
                                            <c:if test="${not empty message}">
                                                <div class="row">
                                                    <h4 class="card-title">${message}</h4>
                                                </div>
                                            </c:if>

                                            <div class="row">
                                                <div class="col-md-7">
                                                    <h4 class="card-title">活动信息</h4>

                                                    <div class="form-group row">
                                                        <label for="name" class="col-md-2 control-label">活动名称</label>
                                                        <div class="col-md-10">
                                                            <input type="hidden" name="status" id="status" value="1" class="form-control"  placeholder="Name">
                                                            <input type="hidden" name="participant" id="participant" value="0" class="form-control"  placeholder="Name">

                                                            <input type="text" name="name" class="form-control" id="name" required placeholder="名称">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label for="title" class="col-md-2 control-label">标题</label>
                                                        <div class="col-md-10">
                                                            <input type="text" name="title" class="form-control" id="title" required placeholder="标语">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label for="x" class="col-md-2 control-label">统计数的位置</label>
                                                        <div class="col-md-5">
                                                            X坐标<input type="text" name="x" class="form-control" id="x" required placeholder="X坐标">
                                                        </div>
                                                        <div class="col-md-5">
                                                            Y坐标<input type="text" name="y" class="form-control" id="y" required placeholder="Y坐标">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label for="color" class="col-md-2 control-label">颜色</label>
                                                        <div class="col-md-10">
                                                            <select name="color" id="color" class="form-control">
                                                                <option value="#000000">黑色</option>
                                                                <option value="#FF0000">红色</option>
                                                                <option value="#00FF00">绿色</option>
                                                                <option value="#0000FF">蓝色</option>
                                                                <option value="#FFFF00">黄色</option>
                                                                <option value="#C0C0C0">灰色</option>
                                                                <option value="#FFFFFF">白色</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label for="fontName" class="col-md-2 control-label">字体</label>
                                                        <div class="col-md-10">
                                                            <select name="fontName" id="fontName" class="form-control">
                                                                <option value="HYLingXinTiJ"><span style="font-family: 'HYLingXinTiJ'">HYLingXinTiJ</span></option>
                                                                <option value="HYYaKuHei-55J"><span style="font-family: 'HYYaKuHei-55J'">HYYaKuHei-55J</span></option>
                                                                <option value="vivo type CN繁 Bold"><span style="font-family: 'vivo type CN繁 Bold'">vivo type CN繁 Bold</span></option>
                                                                <option value="vivo type CN繁 Light"><span style="font-family: 'vivo type CN繁 Light'">vivo type CN繁 Light</span></option>
                                                                <option value="vivo type CN繁 Regular"><span style="font-family: 'vivo type CN繁 Regular'">vivo type CN繁 Regular</span></option>
                                                                <option value="vivo type CN简 Bold"><span style="font-family: 'vivo type CN简 Bold'">vivo type CN简 Bold</span></option>
                                                                <option value="vivo type CN简 Light"><span style="font-family: 'vivo type CN简 Light'">vivo type CN简 Light</span></option>

                                                                <option value="vivo type CN简 Regular"><span style="font-family: 'vivo type CN简 Regular'">vivo type CN简 Regular</span></option>
                                                                <option value="vivo-Bold"><span style="font-family: 'vivo-Bold'">vivo-Bold</span></option>
                                                                <option value="vivo-BoldExtended"><span style="font-family: 'vivo-BoldExtended'">vivo-BoldExtended</span></option>
                                                                <option value="vivo-Light"><span style="font-family: 'vivo-Light'">vivo-Light</span></option>
                                                                <option value="vivo-LightExtended"><span style="font-family: 'vivo-LightExtended'">vivo-LightExtended</span></option>
                                                                <option value="vivo-Regular"><span style="font-family: 'vivo-Regular'">vivo-Regular</span></option>
                                                                <option value="vivo-RegularExtended"><span style="font-family: 'vivo-RegularExtended'">vivo-RegularExtended</span></option>

                                                                <option value="造字工房凌黑"><span style="font-family: '造字工房凌黑'">造字工房凌黑</span></option>
                                                                <option value="造字工房明黑"><span style="font-family: '造字工房明黑'">造字工房明黑</span></option>
                                                                <option value="造字工房哲黑体"><span style="font-family: '造字工房哲黑体'">造字工房哲黑体</span></option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label for="fontSize" class="col-md-2 control-label">字体大小和风格</label>
                                                        <div class="col-md-5">
                                                            <input type="text" name="fontSize" class="form-control" id="fontSize" required value="140" placeholder="字体大小">
                                                        </div>
                                                        <div class="col-md-5">
                                                            <select name="fontStyle" id="fontStyle" class="form-control">
                                                                <option value="0">正常</option>
                                                                <option value="1">粗体</option>
                                                                <option value="2">斜体</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label for="type" class="col-md-2 control-label">活动类型</label>
                                                        <div class="col-md-10">
                                                            <select name="type" id="type" class="form-control">
                                                                <option value="1">自拍</option>
                                                                <option value="2">其他</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-md-2 control-label">起止时间</label>
                                                        <div class="col-md-5">
                                                            <input type="text" name="startDate" id="startDate" class="form-control datepicker-1" placeholder="开始日期" data-provide="datepicker">
                                                        </div>
                                                        <div class="col-md-5">
                                                            <input type="text" name="endDate" id="endDate" class="form-control datepicker-1" placeholder="结束日期" data-provide="datepicker">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label for="description" class="col-md-2 control-label">描述</label>
                                                        <div class="col-md-10">
                                                            <textarea name="description" class="form-control" rows="3" id="description"></textarea>
                                                        </div>
                                                    </div>

                                                </div>
                                                <div class="col-md-5">
                                                    <h4 class="card-title">活动图片</h4>
                                                    <p>请选择活动图片上传</p>
                                                    <div class="row mrg-top-20">
                                                        <div class="col-md-10">
                                                            <div class="form-group">
                                                                <div>
                                                                    <input id="picture" name="picture" class="file" type="file" multiple data-min-file-count="1" data-theme="fas">
                                                                </div>
                                                            </div>

                                                        </div>
                                                    </div>

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
            allowedPreviewTypes : ['image'],
            allowedFileExtensions :['jpg', 'jpeg','png']
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