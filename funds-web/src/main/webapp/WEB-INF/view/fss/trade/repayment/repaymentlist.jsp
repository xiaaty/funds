<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>交易管理--借款流程--借款代扣--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
    
   <%@include file="../../../include/common_css_js.jsp"%>
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
<%@include file="../../../include/menu.jsp"%>
<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">
        <ol class="breadcrumb">
            <li>交易管理</li>
            <li>借款流程</li>
            <li>借款代扣</li>
        </ol>
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                      <div class="jarviswidget" id="RepaymentList"  data-widget-deletebutton="false" data-widget-editbutton="false">
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
                                                    <td class="tr">手机号：</td>
                                                    <td>
                                                        <label class="input">
                                                            <input type="text" style="width:150px" name="toMobile" value="${map.toMobile}" />
                                                        </label>
                                                    </td>
                                                    <td class="tr">客户姓名：</td>
                                                    <td>
                                                        <label class="input">
                                                            <input type="text" style="width:150px" name="toCustName" value="${map.toCustName}" />
                                                        </label>
                                                    </td>

                                                </tr>
                                                <tr>
                                                    <td class="tr">交易状态：</td>
                                                    <td>
                                                        <label>
                                                            <select id = "status" name = "status" style="width:150px;height: 30px;">
                                                                <option value="">所有</option>
                                                                <option  <c:if test="${map.status==10030001}"> selected="selected" </c:if> value="10030001">交易提交</option>
                                                                <option  <c:if test="${map.status==10030005}"> selected="selected" </c:if> value="10030005">交易执行中</option>
                                                                <option  <c:if test="${map.status==10030002}"> selected="selected" </c:if> value="10030002" >交易成功</option>
                                                                <option  <c:if test="${map.status==10030003}"> selected="selected" </c:if> value="10030003" >交易失败</option>
                                                                <option  <c:if test="${map.status==10030006}"> selected="selected" </c:if> value="10030006" >交易部分成功</option>
                                                                <option  <c:if test="${map.status==10030004}"> selected="selected" </c:if> value="10030004" >交易关闭</option>
                                                            </select>
                                                        </label>
                                                    </td>
                                                    <td class="tr">流程状态：</td>
                                                    <td>
                                                        <select id = "processState" name = "processState" style="width:150px;height: 30px;">
                                                            <option value="">所有</option>
                                                            <fss:dictOrder var="order" dictOrder="processState">
                                                                <option value="${order.key}"  <c:if test="${order.key==map.processState}">selected</c:if> >${order.value}</option>
                                                            </fss:dictOrder>
                                                        </select>
                                                    </td>

                                                    <td class="tr">创建日期：</td>
                                                    <td colspan="3">
                                                        <section class="fl">
                                                            <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                                <input type="text" maxlength="10" readonly="readonly" name="startTime" class="selectdate" placeholder="请选择时间" value="${map.startTime}">
                                                            </label>
                                                        </section>
                                                        <span class="fl">&nbsp;至&nbsp;</span>
                                                        <section class="fl">
                                                            <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                                <input type="text" maxlength="10" readonly="readonly"  name="endTime" class="selectdate" placeholder="请选择时间" value="${map.endTime}">
                                                            </label>
                                                        </section>
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
                            <h2>借款代扣</h2>
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
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:3250px;">
                                        <col width="50" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <thead>
                                        <tr>
                                            <td></td>
                                            <td>交易流水号</td>
                                            <td>订单号</td>
                                            <td>客户姓名</td>
                                            <td>客户电话</td>
                                            <td>客户账户</td>
                                            <td>交易金额</td>
                                            <td>交易成功金额</td>
                                            <td>交易类型</td>
                                            <td>代扣类型</td>
                                            <td>进程类型</td>
                                            <td>交易状态</td>
                                            <td>流程状态</td>
                                            <td>创建时间</td>
                                            <td>修改时间</td>
                                            <td>备注</td>
                                            <td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t">
                                            <tr>
                                                <td>${l.index+1}</td>
                                                <td>${t.seqNo}</td>
                                                <td>${t.orderNo}</td>
                                                <td>${t.toCustName}</td>
                                                <td>${t.toCustMobile}</td>
                                                <td>${t.toAccId}</td>
                                                <td><fss:money money="${t.amt}"/></td>
                                                <td><fss:money money="${t.realTradeAmount}"/></td>
                                                <td><fss:dictView key="${t.tradeType}" /></td>
                                                <td><fss:dictView key="${t.withHoldType}" /></td>
                                                <td><fss:dictView key="${t.fundType}" /></td>
                                                <td><fss:dictView key="${t.status}" /></td>
                                                <td><fss:dictView key="${t.processState}" /></td>
                                                <td><fss:fmtDate value="${t.createTime}"/></td>
                                                <td><fss:fmtDate value="${t.modifyTime}"/></td>
                                                <td>${t.memo}</td>
                                                <td>
                                                    <a href="${contextPath}/repayment/processChild/${t.id}">查看详细</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
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
<%@include file="../../../include/common_footer_css_js.jsp"%>
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
	    

</script>

<%@include file="../../../include/foot.jsp"%>
</body>

</html>