<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>账户管理--旧版账户-冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
    
   <%@include file="../../../view/include/common_css_js.jsp"%>
    <style>
        .table-nobg-btn{
            font:15/29px;
            height: 31px;
            margin: 7px 7px 7px 0;
            padding: 0 22px;
        }
        .dt-wrapper {
            overflow: auto;
        }

    </style>

</head>

<body>
<%@include file="../../../view/include/menu.jsp"%>
<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>账户管理</li>
            <li>旧版账户</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
 				<!-- NEW WIDGET START -->
                      <div class="jarviswidget" id="accounttlist"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                           
                                <form class="smart-form" id="accountForm" action="${contextPath}/funds/accountBusinessList/${custId}" method="post" >
                              
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
                                                        <td class="tr" nowrap="nowrap">客户姓名:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:210px" >
                                                                <input type="text" name="customerName" value="${accMap.customerName}">
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">手机号码:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:210px" >
                                                                <input type="text" name="mobilePhone" value="${accMap.mobilePhone}">
                                                            </label>
                                                        </td>
                                                        <td class="tr">创建日期：</td>
                                                        <td colspan="3">
				                                                <section class="fl">
				                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
				                                                        <input type="text" maxlength="10" readonly="readonly" name="startTime" class="selectdate" placeholder="请选择时间" value="${accMap.startTime}">
				                                                    </label>
				                                                </section>
				                                                <span class="fl">&nbsp;至&nbsp;</span>
				                                                <section class="fl">
				                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
				                                                        <input type="text" maxlength="10" readonly="readonly"  name="endTime" class="selectdate" placeholder="请选择时间" value="${accMap.endTime}">
				                                                    </label>
				                                                </section>
                                                        </td>
                                                    </tr>
                                                   
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer>
                                            <!-- <button class="btn btn-default" onclick="window.history.back();" type="button">重&nbsp;&nbsp;&nbsp;置</button> -->
                                            <button class="btn btn-primary" type="button" onclick="verify();">查&nbsp;&nbsp;&nbsp;询</button>
                                        </footer>
                                    </div>
                                    <!-- end widget content -->
    							</form>
                    		</div>
                		</div>
                
                
                
                    <!-- NEW WIDGET START -->
                    <!-- 	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-01"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>账户列表</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:1600px;">
                                        <col width="100" />
                                    	<col width="200" />
                                    	<col width="100" />
                                    	<col width="150" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="150" />
                                    	<col width="150" />
                                    	<col width="150" />
                                    	<col width="150" />
                                    	<col width="200" />
                                        <thead>
                                        <tr>  <td>客户编号</td>
                                              <td>客户姓名</td>
                                              <td>手机号码</td>
                                              <td>账户编号</td>
                                              <td>账户类型</td>
                                              <td>业务类型</td>
                                              <td>账户余额</td>
                                              <td>冻结金额</td>
                                              <td>是否创建第三方账户</td>
                                              <td>创建时间</td>
                                              <td>操作</td> 
                                        </tr>
                                        </thead>
                                        <tbody>
                                             <c:forEach items="${page.list}" var="acc">
                                                <tr>
                                                    <td>${acc.custId}</td>
                                                    <td>${acc.customerName}</td>
                                                    <td>${acc.mobilePhone}</td>
                                                    <td>${acc.accountNo}</td>
                                                    <td>
                                                    <c:if test="${acc.accountType==1}">客户账户</c:if>
                                                    <c:if test="${acc.accountType==2}">A0 </c:if>
                                                    <c:if test="${acc.accountType==3}">AX</c:if>
                                                   </td>
                                                    <td>${acc.busiType==0?"主账户":"其他账户"}</td>
                                                    <td align="right"><fss:money money="${acc.amount}"/></td>
                                                    <td align="right"><fss:money money="${acc.freezeAmount}" /></td>
                                                    <td>${acc.hasThirdAccount==2?"已创建":"未创建"}</td>
                                                    <td><fmt:formatDate value="${acc.creatTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    <td>
                                                        <a href="${contextPath}/funds/account/accountWater/${acc.custId}">查看流水</a>
                                                     <%--   <a href="${contextPath}/trade/tradeApply/createTransfer/${acc.custId}/${acc.busiType}/${acc.customerName}/${acc.mobilePhone}/4/${accMap.custId}">转账转入</a>--%>
                                                     <%--   <a href="${contextPath}/trade/tradeApply/createTransfer/${acc.custId}/${acc.busiType}/${acc.customerName}/${acc.mobilePhone}/5/${accMap.custId}">转账转出</a>--%>
                                                <%--<a href="${contextPath}/funds/acount/custAccountWithdraw/${acc.id}">代付</a>--%>
                                                <%--<a href="${contextPath}/funds/acount/custAccountWithhold/${acc.id}">代扣</a> --%>

                                                        <!-- 是否需要在此处增加账户类型校验 -->
                                                         &nbsp;
                                                        <a href="javascript:void(0);" onclick="expBiz('${acc.id}');">导出</a>
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
    </div>

<div class="pop" id="exportExcel"
     style="display:none;position: absolute;z-index:9999;left:50%;top:50%;margin-left:-200px;margin-top:-200px;width: 450px;padding: 30px;border:solid 2px #008299;border-radius:2px;background: white;">
    <form id="uploadForm" method="post" action="">

        <input id="popAccId" type="hidden">
        <div class="mb25 pr">
            <table class="table lh32">
                <col width="100" />
                <col width="100" />
                <col width="100" />
                <col width="100" />
                <col />
                <tbody>
                <tr></tr>
                <tr>
                    <td  colspan="4">
            <section class="fl">
                <label class="input" style="width:140px;">
                    <input id="popStartTime" type="text" maxlength="20" readonly="readonly" name="startTime"  class="selectdate" placeholder="开始时间" value="${accMap.startTime}">
                </label><i class="icon-append fa fa-calendar"></i>
            </section>
            <span class="fl">&nbsp;&nbsp;至&nbsp;&nbsp;</span>
            <section class="fl">
                <label class="input" style="width:140px;">
                    <input id="popEndTime" type="text" maxlength="20" readonly="readonly" name="endTime" class="selectdate" placeholder="结束时间" value="${accMap.endTime}">
                </label><i class="icon-append fa fa-calendar"></i>
            </section>
                </td>
                </tr>
                </tbody>
                </table>
        </div>
        <div class="mb20" id="wid-id-713">
            <button class="btn btn-primary " id="export" type="button" title="导入">导&nbsp;出</button>
            &nbsp;&nbsp;
            <button class="btn btn-default fl mr30" id="mr30" type="button" title="取消">取&nbsp;消</button>
        </div>
    </form>
</div>

<%@include file="../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
 <script type="text/javascript" charset="utf-8">
	 $(document).ready(function () {
	     pageSetUp();
	     DT_page("borrow-rep-table12", true, '${page.JSON}', $("#accountForm"));
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
	    			alert("请检查您输入的日期");
	    		}else{
	    			$("#accountForm").submit();
	    		}
	    	}else{
	    		var d = new Date();
	    		var str = d.getFullYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+(d.getDate()<10?"0":"")+d.getDate();
	    		if(a[0].value>str){
	    			alert("请检查您输入的日期");
	    		}else{
	    			$("#accountForm").submit();
	    		}
	    	}
	    }

     $(function () {
         $(".mr30").click(function () {
             $('.pop').hide();
             $("#popAccId").val("");
         });

         $("#export").click(function () {
             var accId = $("#popAccId").val();
             var url = "${contextPath}/account/export/" + accId;

             $("#uploadForm").attr("action", url);
             $("#uploadForm").submit();

             $('.pop').hide();
         })

     });



     function expBiz(accId){
         $("#popAccId").val(accId);
         $('.pop').show();
     }

 </script>

<%@include file="../../../view/include/foot.jsp"%>
</body>

</html>