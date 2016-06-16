<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
    
    <%@include file= "../../../../view/include/common_css_js.jsp"%>
    <style>
        .borrow-rep-table12 {
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
<%@include file= "../../../../view/include/menu.jsp"%>


<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>系统管理</li>
            <li>系统配置</li>
            <li>银行卡bin配置</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
    <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->

                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="jarviswidget" id="cardBinList"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                                <form class="smart-form" id="cardBinForm" action="${contextPath}/sys/customer/cardBinList" method="post" >
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
                                                    <tr>
                                                        <td class="tr" nowrap="nowrap">发卡行名称:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:210px" >
                                                                <input type="text" name="bankName" value="${cardBin.bankName}">
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">机构代码：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="organCode" value="${cardBin.organCode}">
                                                            </label>
                                                        </td>
                                                        </tr>
                                                     <tr>
                                                        <td class="tr" nowrap="nowrap">取值：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="takeValue" value="${cardBin.takeValue}">
                                                            </label>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer><button type="button" class="btn btn-default " id="btn_add">添&nbsp;&nbsp;&nbsp;加</button>
                                             <button class="btn btn-primary" onclick="verify();">查&nbsp;&nbsp;&nbsp;询</button>
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
             <!--    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
                    <div class="jarviswidget jarviswidget-color-darken" id="card_bin_list"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>银行卡bin列表</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="">
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                    <!-- widget edit box -->
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:1400px;">
                                    	<col width="50" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="150" />
                                        <col width="50" />
                                        <col width="100" />
                                        <col width="150" />
                                        <col width="150" />
                                        <col width="150" />
                                        <col width="200" />
                                        <thead>
                                        <tr>
                                        	<td>编号</td>
                                            <td>发卡行名称</td>
                                            <td>机构代码</td>
                                            <td>卡  名</td>
                                            <td>长  度</td>
                                            <td>取  值</td>
                                            <td>卡  种</td>
                                            <td>创建日期</td>
                                            <td>修改日期</td>
                                            <td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t">
                                           <tr>
                                               <td>${t.id}</td>
                                               <td>${t.bankName}</td>
                                               <td>${t.organCode}</td>
                                               <td>${t.cardName}</td>
                                               <td>${t.length} </td>
                                               <td>${t.takeValue}</td>
                                               <td>${t.cardType}</td>
                                               <td><fss:fmtDate value="${t.createTime}" /></td>
                                               <td><fss:fmtDate value="${t.modifyTime}" /></td>
                                              <td>
                                               	<a href="${contextPath}/sys/customer/toUpdateCardBin/${t.id}">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                                              	<a href="javascript:void(0)" onclick="deleteBankCard(${t.id})">删除</a>
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
               <!--  </article> -->
            </div>

        </section>
    </div>
<%@include file= "../../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
</div>


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#cardBinForm"));
    });
    function verify(){
    			$("#cardListForm").submit();
    }
    //添加按钮按下
    $("#btn_add").button().click(function() {
        window.open("${contextPath}/sys/customer/toAddCardBin","_self");
    });

//   	删除
   function deleteBankCard(id){
                if(confirm("您确认删除本条信息吗？")){
	            $.ajax({
	            	url : "${contextPath}/sys/customer/deleteCardBin/"+id,
	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	                dataType: "json",
	                success: function (data) {
	                    if (data.code == '0000') {
	                      jAlert("删除成功!", '确认信息');
                            location.reload();
	                      //自动跳转
	                      <%--parent.location.href="${contextPath}/sys/customer/cardBinList";--%>
	                    } else {
	                        return;
	                    }
	                }
	            });
                }
    }

    
    
</script>

<%@include file= "../../../../view/include/foot.jsp"%>
</body>

</html>