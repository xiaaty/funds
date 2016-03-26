<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>代付审核--借款人提现--冠群驰骋投资管理(北京)有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
    
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
    </style>
</head>
<body>
    
<%@include file="../../../../view/include/menu.jsp"%>

    <div id="main" role="main">

        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
                <li>交易管理</li>
                <li>交易审核</li>
                <li>提现审核</li>
            </ol>
            <!-- end breadcrumb -->
        </div>

        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <form id="withdrawForm" action="${contextPath}/trade/tradeApply/${tradeapply.applyType}/${tradeapply.busiType}/moneySplit" method="post">
                   <%--     <input type="hidden" value="${dict.dictId}" name="dictId"  default="0"/> --%>
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">

                            <div class="jarviswidget" id="wid-id-711" data-widget-deletebutton="false" data-widget-editbutton="false">
                               <header>
                                    <h2><i class="fa fa-edit pr10"></i>借款人提现审核信息<font class="pl10 f12 color07"></font></h2>
                                </header>
                                <div>
                                    <div class="smart-form">

                                        <!-- widget content -->
                                        <div class="widget-body no-padding">
                                            <div class="mt10 mb10 ml30">
                                                <table class="table">
                                                    <col width="112" />
                                                    <col width="367" />
                                                    <col width="112" />
                                                    <col />
                                                    <tbody>
                                                    	<input type="hidden"  name="id" value="${tradeapply.id}"/>
                                                    	<input type="hidden"  name="id" value="${tradeapply.applyNo}"/>
                                                    	<input type="hidden"  name="applyType" value="${tradeapply.applyType}"/>
                                                    	<input type="hidden"  name="busiType" value="${tradeapply.busiType}"/>
                                                        <tr>
                                                            <td align="left">客户姓名：</td>
                                                            <td>
                                                                <label class="input">
                                                               	 	<input type="text" maxlength="50" readonly="readonly" name="custName" value="${custName}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">合同号：</td>
                                                            <td>
                                                                <label class="input">
                                                                <input type="text" maxlength="50" readonly="readonly" name="contractId" value="${tradeapply.contractId}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">金额：</td>
                                                            <td>
                                                                <label class="input">
                                                                <input type="text" maxlength="50" readonly="readonly" name="tradeAmount" value="${tradeapply.tradeAmount}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                        <td align="left">预约到账日期：</td>
			                                            <td>
			                                                <section class="fl">
			                                                    <label class="input" style="width:200px;"> <i class="icon-append fa fa-calendar"></i>
			                                                        <input type="text" maxlength="10" id="bespokedate" name="bespokedate" value="${bespokedate}" class="selectdate" placeholder="请选择预约到账日期">
			                                                    </label>
			                                                </section>
			                                            </td>
                                                        </tr>
                                                        <tr>
                                                        <td align="left">审批状态：</td>
			                                                 <td colspan="5">
			                           							<span class="pl10 pr50"><input checked="checked" name="applyStatus" type="radio" value="4"><label class="ml5" for="typeofPayYes">通过</label></span>
																<span class="pl10 pr50"><input name="applyStatus" type="radio" value="3"><label class="ml5" for="checkRefuse">不通过</label></span> 
								                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <div class="mb20" id="wid-id-713">
                                                    <button class="btn btn-default table-nobg-btn" type="button" id="passbtn">提交</button>
                                                    <button class="btn btn-default table-nobg-btn" type="button" id="btn_cancel">取消</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </article>
                    </form>
                </div>
            </section>
        </div>
    </div>

<%@include file="../../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
    <script type="text/javascript" charset="utf-8">
         $(document).ready(function () {
        	/***************************审核通过*********************************************/ 
        	  $("#passbtn").click(function () {
    	        if (validateCheck()) {
    	            $("#withdrawForm").ajaxSubmit({
    	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	                dataType: "json",
    	                success: function (data) {
    	                    if (data.code == '0000') {
    	                       jAlert("审核完成!", '信息提示');
    	                        //自动跳转
    	                        //  window.history.back();
    	                    	parent.location.href="${contextPath}/trade/tradeApply/${tradeapply.applyType}/${tradeapply.busiType}/${tradeapply.applyNo}/${tradeapply.id}/records";
    	                    } else {
    	                    	jAlert("添加失败,该编号已经存在,请勿重复添加!", '消息提示');
    	                        return;
    	                    }
    	                }
    	            });
    	        }
    	    });
        	 
        	//校验函数
          	function validateCheck() {
          		return true;
          	}
        	/************************************************************************/ 
    	    // pageSetUp();
    	    // DT_page("borrow-rep-table12", true, '${page.JSON}', $("#Form"));
    	     $('.selectdate').datetimepicker({
                 language: 'zh-CN',
                 weekStart: 1,
                 autoclose: 1,
                 format: 'yyyy-mm-dd',
                 todayHighlight: 1,
                 startView: 2,
                 minView: 2,
                 forceParse: 0
             });
    	     
    	     dateCheck();
    	     
    	     
             $('.selectdate_time').datetimepicker({
                 language: 'zh-CN',
                 weekStart: 1,
                 autoclose: 1,
                 format: 'hh:m:00',
                 todayHighlight: 1,
                 startView: 1,
                 minView: 1,
                 forceParse: 0
             });
    	 }); 
     
    	 
    		//日期的合法性check
    	    function dateCheck() {
    	    	var $selectdate = $(".selectdate");
    	    	$selectdate.each(function() {
    	    		//$(this).off();
    	        	$(this).focus(function() {
    	        		//
    	        		this.select();
    	        	})
    	        	.blur(function() {
    		        	if($(this).val() != "") {
    			        	var val = $(this).val();
    			        	if (val.indexOf("\-") > 0 ) {
    			        	} else {
    			        		if (val.length == 8) {
    			        			val = val.substr(0,4) + "-" + val.substr(4,2) + "-" + val.substr(6,2);
    			        			$(this).val(val);
    			        		}
    			        	}
    			        	var msg= isDate($(this).val());
    			        	if (msg != "") {
    				        	alert(msg);
    				        	this.focus();
    			        	}

    	        		}
    	        	});
    	    	});
    	    }
    	 
    	    function isDate(strDate){
    	    	var strSeparator = "-"; //日期分隔符 
    	    	var strDateArray; 
    	    	var intYear; 
    	    	var intMonth; 
    	    	var intDay; 
    	    	var boolLeapYear; 
    	    	var ErrorMsg = ""; //出错信息 
    	    	strDateArray = strDate.split(strSeparator); 
    	    	//没有判断长度,其实2008-8-8也是合理的//strDate.length != 10 || 
    	    	if(strDateArray.length != 3) { 
    	        	ErrorMsg += "日期格式必须为: 年-月-日"; 
    	        	return ErrorMsg; 
    	    	} 
    	    	intYear = parseInt(strDateArray[0],10); 
    	    	intMonth = parseInt(strDateArray[1],10); 
    	    	intDay = parseInt(strDateArray[2],10); 
    	    	if(isNaN(intYear)||isNaN(intMonth)||isNaN(intDay)) { 
    	    		ErrorMsg += "请输入有效的日期！"; 
    	        	return ErrorMsg; 
    	    	} 
    	    	if(intMonth>12 || intMonth<1) { 
    	    		ErrorMsg += "请输入有效的日期！"; 
    	        	return ErrorMsg; 
    	    	} 
    	    	if((intMonth==1||intMonth==3||intMonth==5||intMonth==7 
    	    		||intMonth==8||intMonth==10||intMonth==12) &&(intDay>31||intDay<1)) { 
    	    		ErrorMsg += "请输入有效的日期！"; 
    	        	return ErrorMsg; 
    	    	} 
    	    	if((intMonth==4||intMonth==6||intMonth==9||intMonth==11) 
    	    		&&(intDay>30||intDay<1)) { 
    	    		ErrorMsg += "请输入有效的日期！";  
    	        	return ErrorMsg; 
    	    	} 
    	    	if(intMonth==2){ 
    	        	if(intDay < 1) { 
    	        		ErrorMsg += "请输入有效的日期！";  
    		        	return ErrorMsg; 
    	    		} 
    	        	boolLeapYear = false; 
    	        	if((intYear%100) == 0){ 
    		        	if((intYear%400) == 0) 
    		        	boolLeapYear = true; 
    	    		} else { 
    		        	if((intYear % 4) == 0) 
    		        		boolLeapYear = true; 
    	        		} 
    	        		if(boolLeapYear){ 
    		        		if(intDay > 29) { 
    		        			ErrorMsg += "请输入有效的日期！"; 
    			        		return ErrorMsg; 
    	        			} 
    	    			} else { 
    			        	if(intDay > 28) { 
    				        	ErrorMsg += "请输入有效的日期！"; 
    				        	return ErrorMsg; 
    		        		} 
    	    			} 
    	    		} 
    	    	return ErrorMsg; 
    	    } 
    	 
    	    $("#btn_cancel").button().click(function() {
            	window.history.back();
            });
    	    
    	    
        </script>
</body>

</html>