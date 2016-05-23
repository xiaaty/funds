<%@ page import="java.io.PrintWriter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>错误页面--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <%@include file="../../view/include/common_css_js.jsp"%>
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
<%@include file="../../view/include/menu.jsp"%>
<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>资金结算</li>
            <li>错误页面</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget" id="main_001"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <h2>错误信息</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <!-- widget edit box -->
                            <div class="jarviswidget-editbox">
                                <!-- This area used as dropdown edit box -->
                            </div>
                            <!-- end widget edit box -->
                            <!-- widget content -->
                            <div class="widget-body no-padding">
                                <div class="mt10 mb10">
                                    <table class="table">
                                        <col />
                                        <tbody>
                                        <tr class="lh32">
                                            <td  align="left">
                                                出错了.....:
                                            </td>
                                            <td  align="left">
                                                ${errorMsg}
                                            </td>
                                        </tr>
                                        <tr class="lh32">
                                            <td  align="left">

                                            </td>
                                            <td  align="left">
                                                异常信息如下:请截图发给系统管理员,以便进一步处理并解决问题<br>
                                                ${errorMsg}<br>
                                                <%
                                                    Exception ex = (Exception) request.getAttribute("errorException");
                                                    StackTraceElement[] etrace = ex.getStackTrace();
                                                    ex.printStackTrace();
                                                    for (StackTraceElement se:etrace){
                                                        int line = se.getLineNumber();
                                                        String className = se.getClassName();
                                                        String method = se.getMethodName();
                                                        String fileName = se.getFileName();
                                                %>
                                                <p>
                                                    <%=className%>.<%=method%>(<%=fileName%>:<%=line%>)
                                                </p>
                                                <%
                                                    }


                                                %>
                                            </td>
                                        </tr>

                                        </tbody>
                                    </table>
                                </div>
                                <%-- <footer>
                                     <button class="btn btn-primary" onclick="javascript:void(0);">确认</button>
                                 </footer>--%>
                            </div>
                            <!-- end widget content -->
                        </div>


                    </div>
                    <!--
                         -->
                    <!-- NEW WIDGET START -->
                    <!-- 	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->

                    <!--/article>
                        </div>
                        <div class="row"-->
                    <!-- NEW WIDGET START -->
                    <!--article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"-->

                </article>
            </div>

        </section>
    </div>
    <%@include file="../../view/include/common_footer_css_js.jsp"%>
</div>


<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();

        DT_page("borrow-rep-table12",true,'',$("#logListForm"));

    });



</script>

</body>

</html>