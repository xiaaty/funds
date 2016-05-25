<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>交易管理--交易审核--代扣审核--借款代扣--借款代扣明细--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
    
   <%@include file="../../include/common_css_js.jsp"%>
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
<%@include file="../../include/menu.jsp"%>
<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">
        <ol class="breadcrumb">
            <li>交易管理</li>
            <li>交易审核</li>
            <li>代扣审核</li>
            <li>借款代扣</li>
            <li>借款代扣明细</li>
        </ol>
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                      <div class="jarviswidget" id="repayDetail"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <div>
                                <form class="smart-form" action=""  method="post" id="repaymentForm">
                                    <div class="jarviswidget-editbox">
                                    </div>
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
                                                        <td class="tr">账号：</td>
                                                         <td>
                                                            <label class="input">
                                                                <input type="text" style="width:200px" name="accNo" value="${repayment.accNo}" />
                                                            </label>
                                                        </td>
                                                        <td class="tr">合同号：</td>
                                                         <td>
                                                            <label class="input">
                                                                <input type="text" style="width:200px" name="contractId" value="${repayment.contractId}" />
                                                            </label>
                                                        </td>
                                                        <td class="tr">序列号：</td>
                                                        <td>
                                                            <label class="input">
                                                                <input type="text" style="width:200px" name="serialNumber" value="${repayment.serialNumber}" />
                                                            </label>
                                                        </td>
                                                        <td class="tr">执行状态：</td>
                                                        <td>
                                                            <select id = "state" name = "state" style="width:150px;height: 30px;">
										                    	<option value="">请选择</option>
										                    	<option  <c:if test="${repayment.state==10090001}"> selected="selected" </c:if> value="10090001">新增</option>
										                    	<option  <c:if test="${repayment.state==10090002}"> selected="selected" </c:if> value="10090002" >划扣中</option>
										                    	<option  <c:if test="${repayment.state==10090003}"> selected="selected" </c:if> value="10090003" >划扣完成</option>
										                    </select>
                                                        </td>
                                                        <td class="tr">执行结果：</td>
                                                        <td>
                                                           <select id = "resultState" name = "resultState" style="width:150px;height: 30px;" >
										                    	<option value="">请选择</option>
										                    	<option  <c:if test="${repayment.resultState==10080001}"> selected="selected" </c:if> value="10080001">新增</option>
										                    	<option  <c:if test="${repayment.resultState==10080002}"> selected="selected" </c:if> value="10080002" >成功</option>
										                    	<option  <c:if test="${repayment.resultState==10080003}"> selected="selected" </c:if> value="10080003" >部分成功</option>
										                    	<option  <c:if test="${repayment.resultState==10080010}"> selected="selected" </c:if> value="10080010" >失败</option>
										                    </select>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer>
                                          <button type="submit" class="btn btn-primary">查&nbsp;&nbsp;&nbsp;询</button>
                                        </footer>
                                    </div>
                                    <!-- end widget content -->
    							</form>
                    		</div>
                		</div>
                
                    <!-- NEW WIDGET START -->
                    <div class="jarviswidget jarviswidget-color-darken" id="dictList-id-02"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>借款代扣明细</h2>
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
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2300px;">
                                    	<col width="50" />
                                        <col width="200" />
                                        <col width="150" />
                                        <col width="200" />
                                        <col width="150" />
                                        <col width="100" />
                                        <col width="200" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="200" />
                                        <col width="150" />
                                        <col width="150" />
                                        <col width="150" />
                                        <col width="150" />
                                        <col width="150"/>
                                        <col width="100"/>
                                        <thead>
                                        <tr>
                                        	  <td>编号</td>
                                              <td>资金账号</td>
                                              <td>交易类型</td>
                                              <td>流水号</td>
                                              <td>合同号</td>
                                              <td>执行状态</td>
                                              <td>执行结果</td>
                                              <td>还款金额</td>
                                              <td>创建时间</td>
                                              <td>修改时间</td>
                                              <td>序列号</td>
                                              <td>父商户号</td>
                                              <td>子商户号</td>
                                              <td>交易代码</td>
                                              <td>返回信息</td>
                                              <td>备注</td>
                                        </tr>
                                        </thead>
                                         <tbody>
                                             <c:forEach items="${page.list}" var="repayment">
                                                <tr>
                                                    <td>${repayment.id}</td>
                                                    <td>${repayment.accNo}</td>
                                                    <td><fss:dictView key="${repayment.tradeType}" /></td>
                                                    <td>${repayment.seqNo}</td>
                                                    <td>${repayment.contractId}</td>
	                                                <td><fss:dictView key="${repayment.state}" /></td>
	                                                <td><fss:dictView key="${repayment.resultState}" /></td>
                                                    <td>${repayment.amt}</td>
                                                    <td><fss:fmtDate value="${repayment.createTime}"/></td>
                                                    <td><fss:fmtDate value="${repayment.motifyTime}"/></td>
                                                    <td>${repayment.serialNumber}</td>
                                                    <td>${repayment.mchnParent}</td>
                                                    <td>${repayment.mchnChild}</td>
                                                    <td>${repayment.respCode}</td>
                                                    <td>${repayment.respMsg}</td>
                                                    <td>${repayment.remark}</td>
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
<%@include file="../../include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
 <script type="text/javascript" charset="utf-8">
	 $(document).ready(function () {
	     pageSetUp();
	     DT_page("borrow-rep-table12", true, '${page.JSON}', $("#repaymentForm"));
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
	    
	/*   //添加按钮按下
        $("#btn_add").button().click(function() {
        	window.open("${contextPath}/sys/workassist/dictAdd/${parent_id}","_self");
        });
     //添加按钮按下
     $("#btn_return").button().click(function() {
         window.open("${contextPath}/sys/workassist/dictionary/${returnId}","_self");
     });
	     */
</script>

<%@include file="../../include/foot.jsp"%>
</body>

</html>