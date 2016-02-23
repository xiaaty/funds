<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
   <%@include file="../../../../view/include/common_css_js.jsp"%>
    <style>
        .table-nobg-btn {
            font: 15/29px;
            height: 31px;
            line-height: 31px;
            margin: 7px 7px 7px 0;
            padding: 0 22px;
        }
        .dt-wrapper {
            overflow: auto;
        }
        .button-icon i{
            line-height:32px;
        }
        #footer{position: absolute;bottom: 10px;z-index: 100px;}
        .footer-bottom{font-size:13px}
        .footer-bottom ul>li{padding:0}
        .footer-bottom ul>li+li:before{padding:0 10px;color:#ccc;content:"|"}
    </style>

</head>

<body>
<%@include file="../../../../view/include/menu.jsp"%>


<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>商户管理</li>
            <li>api列表</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-30"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>api列表</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="apiForm">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                <div class="mb20" id="wid-id-713">
                                            <button class="btn btn-default table-nobg-btn" type="button" onclick="location.href='${contextPath}/fss/api/toAddApi'" >添加</button>
                                      </div>
                                    <table id="borrow-rep-table12" class="table table-bordered mt15" style="text-align:center;">
                                        <thead>
                                        <tr>
                                            <td>api名称</td>
                                            <td>api地址</td>
                                            <td>是否公共API</td>
                                            <td>api的类名</td>
                                            <td>api的方法名</td>
                                            <td>创建日期</td>
                                            <td>创建人</td>
                                            <td>修改时间</td>
                                            <td>修改人</td>
                                            <td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${page.list}" var="t">
                                                <tr>
                                                    <td>${t.apiName}</td>
                                                    <td>${t.apiUrl}</td>
                                                    <td>${t.pulic=='0'?"否":"是"}</td>
                                                    <td>${t.className}</td>
                                                    <td>${t.methodName}</td>
                                                    <td> <fmt:formatDate value="${t.createTime}" pattern="yyyy-MM--dd HH:mm:ss"/></td>
                                                    <td>${t.createUserId}</td>
                                                    <td> <fmt:formatDate value="${t.modifyTime}" pattern="yyyy-MM--dd HH:mm:ss"/></td>
                                                    <td>${t.modifyId}</td>
                                                    <td style="text-align:left;">
                                                        <a href="${contextPath}/fss/api/selectApi/${t.id}">修改</a>
                                                        <a href="${contextPath}/fss/api/deleteApi/${t.id}">删除</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </form>
                        </div>
                    </div>
                </article>
            </div>

        </section>
    </div>
<%@include file="../../../../view/include/common_footer_css_js.jsp"%>
</div>


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#apiForm"));
    });

</script>

<%@include file= "../../../../view/include/foot.jsp"%>
</body>

</html>