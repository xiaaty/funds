<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资金管理--提现管理--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
    
   <%@include file="../../../../view/include/common_css_js.jsp"%>
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

</head>
    <body>
<%@include file="../../../../view/include/menu.jsp"%>
<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>资金管理</li>
            <li>提现管理</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
 				<!-- NEW WIDGET START -->
                      <div class="jarviswidget" id="wid-id-11"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                                <form class="smart-form" id="withdrawApplyForm" action="${contextPath}/withdrawApply/queryWithdrawList" method="post" >
                              
                                    <!-- widget edit box -->
                                    <div class="jarviswidget-editbox">
                                        <!-- This area used as dropdown edit box -->
                                    </div>
                                    <!-- end widget edit box -->
                                    <!-- widget content -->
                                    <div class="widget-body no-padding">
                                        <div class="mt10 mb10">
                                            <table class="table lh32">
                                                <col />
                                                <tbody>
                 								  <tr></tr>
                                                    <tr>
                                                     <td class="tr" nowrap="nowrap">客户姓名:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:210px" >
                                                                <input type="text" name="custName" value="${withdrawBean.custName}">
                                                            </label>
                                                        </td>
                                                     <td class="tr" nowrap="nowrap">手机号码:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:150px" >
                                                                <input type="text" name="cellPhone" value="${withdrawBean.cellPhone}">
                                                            </label>
                                                        </td>
	                                                        <td class="tr">账户类型：</td>
			                                                <td>
			                                                 <input type="hidden" id="hiddencustType" value="${withdrawBean.custType}"/>
				                                                <section style="width:100px">
		                                                    		<label class="select">
													                <select name="custType" id = "custType">
										                            <option value="">--不限--</option>
										                            <option value="1">借款账户</option>
										                            <option value="2">线下出借账户</option>
										                            <option value="3">线上出借账户</option>
										                            <option value="96">应付款账户</option>
										                        </select>
													            </label>
													             </section>
	                                                </td>
                                                        </tr>
                                                        <tr>
                                                    	    <td class="tr">业务类型：</td>
			                                                <td>
			                                                 <input type="hidden" id="hiddenbussinessType" value="${withdrawBean.bussinessType}"/>
				                                                <section style="width:100px">
		                                                    		<label class="select">
													                <select id="bussinessType" name ="bussinessType">
													                 <option value="">--不限--</option>
										                            <option value="1">满标提现</option>
										                            <option value="2">月月通代付(线下)</option>
										                            <option value="3">还款归还保证金</option>
										                            <option value="4">债权赎回提现</option>
										                            <option value="5">抵押标借款人提现</option>
													                </select>
													            </label>
													             </section>
	                                                </td>
                                                 <td class="tr">提现状态：</td>
		                                                <td>
	                                                    <input type="hidden" id="hiddenapplyStatus" value="${withdrawBean.applyStatus}"/>
			                                                <section style="width:100px">
	                                                    		<label class="select">
												                <select id="applyStatus" name ="applyStatus">
																<option value="">--不限--</option>
									                            <option value="1">审核中</option>
									                            <option value="2">已提现</option>
									                            <option value="3">取消</option>
									                            <option value="4">转账中</option>
									                            <option value="5">失败</option>
									                            <option value="99">人工对账</option> </select>
												            </label>
												             </section>
                                                </td>
                                                 <td class="tr">结算方式:</td>
		                                                <td>
	                                                   <input type="hidden" id="hiddensettleType" value="${withdrawBean.settleType}"/>
			                                                <section style="width:100px">
	                                                    		<label class="select">
												                <select id="settleType" name ="settleType">
												                 <option value="">--不限--</option>
										                           <option value="0">T+0</option>
                          										  <option value="1">T+1</option>
												                </select>
												            </label>
												             </section>
                                                </td>
                                                </tr>
                                                	<tr>
                                                         <td class="tr" width="90px">开户日期：</td>
                                            <td colspan="3">
                                                <section class="fl">
                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                        <input type="text" maxlength="10" readonly="readonly" name="startTime" class="selectdate" placeholder="请选择时间" value="${withdrawBean.startTime}">
                                                    </label>
                                                </section>
                                                <span class="fl">&nbsp;至&nbsp;</span>
                                                <section class="fl">
                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                        <input type="text" maxlength="10" readonly="readonly"  name="endTime" class="selectdate" placeholder="请选择时间" value="${withdrawBean.endTime}">
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
                            <h2>提现管理</h2>
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
                                    <table id="borrow-rep-table12" class="table table-bordered mt15" style="text-align:center;">
                                       <%--  <col width="200" />
                                        <col /> --%>
                                        <thead>
                                        <tr>
                        <th align="left"><input type="checkbox" id="checkAll"/></th>
                        <%--<th>客户编号</th>--%>
                        <th style="text-align:center;">客户名称</th>
                        <th style="text-align:center;">业务类型</th>
                        <th style="text-align:center;">账户类型</th>
                        <th style="text-align:center;">账户编号</th>
                        <th style="text-align:center;">合同编号</th>
	                    <th style="text-align:center;">分公司名称</th>
                        <th style="text-align:center;">手机号码</th>
                        <th style="text-align:center;" class='tr'>申请金额</th>
                        <th style="text-align:center;" class='tr' style="padding-right:40px;">实际提现金额</th>
                        <th style="text-align:center;">提现状态</th>
                        <th style="text-align:center;">申请时间</th>
                        <th style="text-align:center;">审核时间</th>
                        <th style="text-align:center;">结算方式</th>
                        <th style="text-align:center;">操作</th>
                    </tr>
                </thead>
                <tbody>
							<c:forEach items="${page.list}" var="wd" varStatus="status">
								<tr>
									<td><input type="checkbox" class="checkBoxAll" value="${wd.id}"/></td>
									<td style="text-align:center;">${wd.custName}</td>
									<td style="text-align:center;">
									    <c:if test="${wd.bussinessType == 1}">满标提现</c:if>
									    <c:if test="${wd.bussinessType == 2}">月月通代付(线下)</c:if>
									    <c:if test="${wd.bussinessType == 3}">还款归还保证金</c:if>
									    <c:if test="${wd.bussinessType == 4}">债权赎回提现</c:if>
									    <c:if test="${wd.bussinessType == 5}">抵押标借款人提现</c:if>
                                    </td>
									<td style="text-align:center;">
									    <c:if test="${wd.custType == 1}">借款账户</c:if>
									    <c:if test="${wd.custType == 2}">线下出借账户</c:if>
									    <c:if test="${wd.custType == 3}">线上出借账户</c:if>
									    <c:if test="${wd.custType == 96}">应付款账户</c:if>
                                    </td>
									<td style="text-align:center;">${wd.accountId}</td>
	                                <td style="text-align:center;">${wd.bussinessContractNo}</td>
	                                <td style="text-align:center;">${wd.bussinessCompany}</td>
									<td style="text-align:center;">${wd.cellPhone}</td>
									<td style="text-align:center;" class='tr'><fmt:formatNumber value="${wd.drawAmount}" pattern="#,#00.00"/></td>
									<td style="text-align:center;" class='tr' style="padding-right:40px;">
										<fmt:formatNumber value="${wd.factDrawAmount}" pattern="#,#00.00"/>
									</td>
									
									<td style="text-align:center;">
									    <c:if test="${wd.applyStatus == 1}"><span style="color:olive">审核中</span></c:if>
									    <c:if test="${wd.applyStatus == 2}"><span style="color: green">已提现</span></c:if>
									    <c:if test="${wd.applyStatus == 3}"><span style="color:gray;">取消</span></c:if>
									    <c:if test="${wd.applyStatus == 4}"><span style="color: green">提现中</span></c:if>
									    <c:if test="${wd.applyStatus == 5}"><span style="color: red">失败</span></c:if>
									    <c:if test="${wd.applyStatus == 6}"><span style="color: red">部分成功</span></c:if>
									    <c:if test="${wd.applyStatus == 99}"><span style="color: red">人工对账</span></c:if>
                                    </td>
									<td style="text-align:center;"><fmt:formatDate value="${wd.applyTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td style="text-align:center;">
										<fmt:formatDate value="${wd.reviewTime}" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td style="text-align:center;">
										<c:if test="${wd.settleType == 0}">T+0</c:if>
										<c:if test="${wd.settleType == 1}">T+1</c:if>
										<c:if test="${wd.settleType==null}">T+1</c:if>
									</td>
									<td style="text-align:center;">
											<c:if test="${wd.applyStatus == 1}">
	                                                <a href="${cxt}/web/redirectReview/${wd.id}">审核</a>
											</c:if>
											<c:if test="${wd.applyStatus == 6}">
	                                           <a href="${cxt}/web/redirectReviewGoon?id=${wd.id}">继续提现</a>
											</c:if>
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
<%@include file="../../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
       <script type="text/javascript">
      
       $(document).ready(function () {
  	     pageSetUp();
  	     DT_page("borrow-rep-table12", true, '${page.JSON}', $("#withdrawApplyForm"));
  		 selectedInit();
  		 $("#checkAll").removeAttr("checked");
  	 }); 
       $('#checkAll').bind('click', function () {
           var that = this;
           $('.checkBoxAll').each(function () {
               this.checked = that.checked;
           });
       });
       

            
	       function selectedInit() {
			
	    	   $("#bussinessType").val($("#hiddenbussinessType").val());
	           $("#applyStatus").val( $("#hiddenapplyStatus").val());
	           $("#custType").val($("#hiddencustType").val());
	           $("#settleType").val($("#hiddensettleType").val());
	           
	       }
	
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
		    			$("#withdrawApplyForm").submit();
		    		}
		    	}else{
		    		var d = new Date();
		    		var str = d.getFullYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+(d.getDate()<10?"0":"")+d.getDate();
		    		if(a[0].value>str){
		    			alert("请检查您输入的日期");
		    		}else{
		    			$("#withdrawApplyForm").submit();
		    		}
		    	}
		    }
	       
       </script>
       <%@include file="../../../../view/include/foot.jsp"%>
    </body>
</html>
