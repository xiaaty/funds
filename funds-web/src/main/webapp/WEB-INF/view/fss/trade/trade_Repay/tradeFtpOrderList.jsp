<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <title>交易审核--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <%@include file= "../../../../view/include/common_css_js.jsp"%>
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
<%@include file= "../../../../view/include/menu.jsp"%>


<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>交易管理</li>
            <li>借款流程</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
    <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="jarviswidget" id="bloan"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <!-- widget div-->
                            <div>

                                <form class="smart-form" id="mortgageePayment" action="${contextPath}/trade/tradeRepay/ftpOrder" method="post" >

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
                                                <col width="220" />
                                                <tbody>
                                                    <tr></tr>
                                                    <tr>
                                                        <td class="tr" nowrap="nowrap">订单号：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="orderNo" value="${map.orderNo}">
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">生成待上传文件状态：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="select" style="width:210px" >
                                                                <select  name = "fileStatus" style="width:150px;height: 30px;">
                                                                    <option value="">全选</option>
                                                                    <option <c:if test="${map.fileStatus=='1'}"> selected="selected" </c:if> value="1">未生成</option>
                                                                    <option <c:if test="${map.fileStatus=='2'}"> selected="selected" </c:if> value="2">已生成</option>
                                                                </select>
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">上传状态：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="select" style="width:210px" >
                                                                <select  name = "uploadStatus" style="width:150px;height: 30px;">
                                                                    <option value="">全选</option>
                                                                    <option <c:if test="${map.uploadStatus=='1'}"> selected="selected" </c:if> value="1">新增</option>
                                                                    <option <c:if test="${map.uploadStatus=='2'}"> selected="selected" </c:if> value="2">部分上传</option>
                                                                    <option <c:if test="${map.uploadStatus=='3'}"> selected="selected" </c:if> value="3">全部上传</option>
                                                                </select>
                                                            </label>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td class="tr" nowrap="nowrap">结果状态：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="select">
                                                                <select  name = "result" style="width:150px;height: 30px;">
                                                                    <option value="">全选</option>
                                                                    <option <c:if test="${map.result=='1'}"> selected="selected" </c:if> value="1">成功</option>
                                                                    <option <c:if test="${map.result=='2'}"> selected="selected" </c:if> value="2">失败</option>
                                                                    <option <c:if test="${map.result=='2'}"> selected="selected" </c:if> value="3">部分成功</option>
                                                                </select>
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">状态：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="select" style="width:210px" >
                                                                <select  name = "type" style="width:150px;height: 30px;">
                                                                    <option value="">全选</option>
                                                                    <option <c:if test="${map.type=='1'}"> selected="selected" </c:if> value="1">满标</option>
                                                                    <option <c:if test="${map.type=='2'}"> selected="selected" </c:if> value="2">还款</option>
                                                                    <option <c:if test="${map.type=='3'}"> selected="selected" </c:if> value="3">流标</option>
                                                                </select>
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">结果返回状态：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="select" style="width:210px" >
                                                                <select  name = "retrunResultStatus" style="width:150px;height: 30px;">
                                                                    <option value="">全选</option>
                                                                    <option <c:if test="${map.retrunResultStatus=='0'}"> selected="selected" </c:if> value="0">未返回</option>
                                                                    <option <c:if test="${map.retrunResultStatus=='1'}"> selected="selected" </c:if> value="1">已返回</option>
                                                                </select>
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

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-darken" id="borrowerLoan"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>借款流程</h2>
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
                                <div class="widget-body">
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2000px;">
                                        <col width="50" />
                                        <col width="150" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200"/>
                                        <thead>
                                        <tr>
                                            <td>序号</td>
                                            <td>订单号</td>
                                            <td>产生文件数量</td>
                                            <td>生成待上传文件状态</td>
                                            <td>上传状态</td>
                                            <td>回盘文件状态</td>
                                            <td>最后处理结果</td>
                                            <td>结果状态</td>
                                            <td>状态</td>
                                            <td>结果返回状态</td>
                                            <td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t"  varStatus="l">
                                                <tr>
                                                    <td>${l.index+1}</td>
                                                    <td>${t.orderNo}</td>
                                                    <td>${t.fileSize}</td>
                                                    <td>
                                                        <c:if test="${t.fileStatus=='1'}">
                                                            未生成
                                                        </c:if>
                                                        <c:if test="${t.fileStatus=='2'}">
                                                            已生成
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${t.uploadStatus=='1'}">
                                                            新增
                                                        </c:if>
                                                        <c:if test="${t.uploadStatus=='2'}">
                                                            部分上传
                                                        </c:if>
                                                        <c:if test="${t.uploadStatus=='3'}">
                                                            全部上传
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${t.downloadStatus=='1'}">
                                                            未回盘
                                                        </c:if>
                                                        <c:if test="${t.downloadStatus=='2'}">
                                                            部分回盘
                                                        </c:if>
                                                        <c:if test="${t.downloadStatus=='4'}">
                                                            全部回盘
                                                        </c:if>
                                                        <c:if test="${t.downloadStatus=='8'}">
                                                            拒盘文件
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${t.resultStatus=='1'}">
                                                            未处理
                                                        </c:if>
                                                        <c:if test="${t.resultStatus=='2'}">
                                                            待处理
                                                        </c:if>
                                                        <c:if test="${t.resultStatus=='3'}">
                                                            全部处理完成
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${t.result=='1'}">
                                                            成功
                                                        </c:if>
                                                        <c:if test="${t.result=='2'}">
                                                            失败
                                                        </c:if>
                                                        <c:if test="${t.result=='3'}">
                                                            部分成功
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${t.type=='1'}">
                                                            满标
                                                        </c:if>
                                                        <c:if test="${t.type=='2'}">
                                                            还款
                                                        </c:if>
                                                        <c:if test="${t.type=='3'}">
                                                            流标
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${t.retrunResultStatus=='0'}">
                                                            未返回
                                                        </c:if>
                                                        <c:if test="${t.retrunResultStatus=='1'}">
                                                            已返回
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${t.orderNo!=null && t.orderNo!=''}">
                                                                <a href="${contextPath}/trade/tradeRepay/ftpField/${t.orderNo}">
                                                                    查看借款跑批
                                                                </a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a href="" title="订单号是空值">
                                                                    查看借款跑批
                                                                </a>
                                                            </c:otherwise>
                                                        </c:choose>
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
<%@include file= "../../../../view/include/common_footer_css_js.jsp"%>
</div>


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#mortgageePayment"));
    });
    $('.selectdate').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        autoclose: 1,
        format:'yyyy-mm-dd',
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    function verify(){
    	var a=document.getElementsByName("startTime");
    	var b=document.getElementsByName("endTime");
    	if(b[0].value!=null&&b[0].value!=''){
    		
    		if(a[0].value>b[0].value){
    			JAlert("请检查您输入的日期","提示消息");
    		}else{
    			$("#mortgageePayment").submit();
    		}
    	}else{
    		var d = new Date();
    		var str = d.getFullYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+(d.getDate()<10?"0":"")+d.getDate();
    		if(a[0].value>str){
    			JAlert("请检查您输入的日期","提示消息");
    		}else{
    			$("#mortgageePayment").submit();
    		}
    	}
    }
    function recharg(type,id){
    	$.ajax({
        	url : "${contextPath}/loan/trade/"+type+"/charge/"+id,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            success: function (data) {
                if (data.msg == '0000') {
                  jAlert("收费成功!", '确认信息');
                } else if(data.msg == '0001'){
                	jAlert("该条信息不存在!", '确认信息');
                    return;
                }else if(data.msg == '0002'){
                  jAlert("没有收费信息!", '确认信息');
                    return;
                }else if(data.msg == '0003'){
                  jAlert("请重新收取费用!", '确认信息');
                    return;
                }
            }
        });
    }
</script>

<%@include file= "../../../../view/include/foot.jsp"%>
</body>

</html>