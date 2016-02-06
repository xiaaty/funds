<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>字典--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <%@include file="../../include/common_css_js.jsp"%>
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
    </style>

</head>

<body>

 <%@include file="../../include/menu.jsp"%>
    <div id="main" role="main">

        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
                <li>系统管理</li>
                <li>辅助功能列表</li>
                <li>字典</li>
            </ol>
            <!-- end breadcrumb -->
        </div>

        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="jarviswidget" id="wid-id-71"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                           
                                <form class="smart-form" id="dictionaryForm" action="/sys/workAssist/dictionary" method="post" >
                              
                                    <!-- widget edit box -->
                                    <div class="jarviswidget-editbox">
                                        <!-- This area used as dropdown edit box -->
                                    </div>
                                    <!-- end widget edit box -->
                                    <!-- widget content -->
                                    <div class="widget-body no-padding">
                                        <div class="mt10 mb10">
                                            <table class="table lh32">
                                                <col width="100" />
                                                <col width="220" />
                                                <col width="100" />
                                                <col width="220" />
                                                <col width="100" />
                                                <col />
                                                <tbody>
                                                    <tr></tr>
                                                    <tr>
                                                        <td class="tr" nowrap="nowrap">标识符：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="IDNumbers" value="0000">
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">代表含义：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input" style="width:210px" >
                                                                <input type="text" name="meaning" value="成功">
                                                            </label>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer>
                                            <!-- <button class="btn btn-default" onclick="window.history.back();" type="button">重&nbsp;&nbsp;&nbsp;置</button> -->
                                            <button class="btn btn-primary" onclick="javascript:void(0);">查&nbsp;&nbsp;&nbsp;询</button>
                                        </footer>
                                    </div>
                                    <!-- end widget content -->
                                </form>
                            </div>


                        </div>
                        <!-- 
							 -->
                        <!-- NEW WIDGET START -->
                        <!-- 	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
                        <div class="jarviswidget jarviswidget-color-darken" id="wid-id-71"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <span class="widget-icon"> <i class="fa-fw fa fa-table"></i> </span>
                                <h2>字典列表</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                                <form class="smart-form">
                                    <!-- widget edit box -->
                                    <div class="jarviswidget-editbox">
                                        <!-- This area used as dropdown edit box -->
                                    </div>
                                    <!-- end widget edit box -->
                                    <!-- widget content -->
                                    <div class="widget-body">
                                        <table id="borrow-rep-table1" class="table table-bordered" style="text-align:center;">
                                            <col width="270" />
                                            <col />
                                            <thead>
                                                <tr class="fb">
                                                
                                                    <td nowrap="nowrap">标识符</td>
                                                    <td nowrap="nowrap">所代表字段</td>
                                                    <td nowrap="nowrap">含义</td>
                                                </tr>
                                            </thead>
                                            <tbody>
                                        <c:forEach var="dictionary" items="${page.list}">
                                            <tr>
                                                <td class="tl">${user.employeeNo}</td>
                                                <td>${dictionary.IDNumber}</td>
                                                <td>${dictionary.type}</td>                                          
                                                <td>${dictionary.meaning}</td>                                          
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

    </div>


    <%@include file="../../include/common_footer_css_js.jsp"%>

    <script>
        $(document).ready(function () {
        	pageSetUp();

            DT_page("borrow-rep-table1",true,'${page.JSON}',$("#dictionaryForm"));
            
        });
    </script>
 <%@include file="../../include/foot.jsp"%>
</body>

</html>
