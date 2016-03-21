<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资金清结算系统--银行列表--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <%@include file= "../../../view/include/common_css_js.jsp"%>
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
<%@include file= "../../../view/include/menu.jsp"%>


<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>客户信息管理</li>
            <li>银行列表</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
    <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                        <div class="jarviswidget" id="wid-id-71"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                                <form class="smart-form" id="Form" action="" method="post" >
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
                                                <col/>
                                                <tbody>
                                                    <tr>
                                                        <td class="tr" nowrap="nowrap">银行代码:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:210px" >
                                                                <input type="text" name="bankCode" value="${bankinfo.bankCode}">
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">银行名称:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:210px" >
                                                                <input type="text" name="bankName" value="${bankinfo.bankName}">
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">银行短名称:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:210px" >
                                                                <input type="text" name="sortName" value="${bankinfo.sortName}">
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
                        </div>
    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-30"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>银行列表</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                 <div class="widget-body-nobg-toolbar" style="overflow:hidden;">
                                        <button type="button" class="btn btn-default fl table-nobg-btn" id="btn_add"><i class="fa fa-plus"></i>&nbsp;新增银行</button>
                                </div> 
                                <div class="widget-body">
                                     <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2300px;">
                                    	<col width="150" />
                                    	<col width="350" />
                                    	<col width="200" />
                                    	<col width="200" />
                                    	<col width="200" />
                                    	<col width="300" />
                                    	<col width="300" />
                                    	<col width="" />
                                    	<thead>
                                        <tr>
                                            <td>编号</td>
                                            <td>银行名称</td>
                                            <td>银行短名称</td>
                                            <td>银行代码</td>
                                            <td>银行图标</td>
                                            <td>创建时间</td>
                                            <td>修改时间</td>
                                            <td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t">
                                                <tr>
                                                  	<td>${t.id}</td>
                                                    <td>${t.bankName}</td>
                                                    <td>${t.sortName}</td>
                                                    <td>${t.bankCode}</td>
                                                    <td><img src="${contextPath}${t.bankIcon}"/></td>
                                                    <td><fmt:formatDate value="${t.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    <td><fmt:formatDate value="${t.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                     <td>
                                                     	 <%-- <a href="${contextPath}/fund/banktoupdate/${t.id}">修改</a> --%>&nbsp;&nbsp;&nbsp;
                                                      	 <a href="${contextPath}/fund/checkPageXe/${t.id}">查看页面限额</a>  
                                                     	 <%-- <a href="${contextPath}${t.limitPage}">查看页面限额</a> --%>
                                                     </td>
                                                </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- end widget content -->
                            </form>
                        </div>
                    </div>
                </article>
            </div>

        </section>
    </div>
<%@include file= "../../../view/include/common_footer_css_js.jsp"%>
</div>


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#Form"));
    });

    //添加
    $("#btn_add").button().click(function() {
    	window.open("${contextPath}/fund/banktoadd","_self");
    });
    
</script>

<%@include file= "../../../view/include/foot.jsp"%>
</body>

</html>