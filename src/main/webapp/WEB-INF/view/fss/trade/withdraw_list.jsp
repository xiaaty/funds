<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>交易管理--交易审核--代付审核--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
   <%@include file="../../../view/include/common_css_js.jsp"%>
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
<%@include file="../../../view/include/menu.jsp"%>


<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">
        <ol class="breadcrumb">
            <li>交易管理</li>
            <li>交易审核</li>
            <li>代付审核</li>
        </ol>
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                      <div class="jarviswidget" id="dictList-id-01"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <div>
                                <form class="smart-form" action=""  method="post" id="mortForm">
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
                                                       <td class="tr">客户账号：</td>
                                                         <td>
                                                            <label class="input">
                                                                <input type="text" style="width:300px" name="accNo" value="${tradeapply.accNo}" />
                                                            </label>
                                                        </td>
                                                        <td class="tr">业务编号：</td>
                                                        <td>
                                                             <label class="input">
                                                                <input type="text" style="width:300px" name="businessNo" value="${tradeapply.businessNo}" />
                                                            </label>
                                                        </td> 
                                                        <td class="tr">创建日期：</td>
			                                             <td colspan="5">
			                                                <section class="fl">
			                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
			                                                        <input type="text" maxlength="10" id="startime" name="startime" value="${startime}" class="selectdate" placeholder="请选择时间">
			                                                    </label>
			                                                </section>
			                                                <span class="fl">&nbsp;至&nbsp;</span>
			                                                <section class="fl">
			                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
			                                                        <input type="text" maxlength="10" id="endtime" name="endtime" value="${endtime}" class="selectdate" placeholder="请选择时间" >
			                                                    </label>
			                                                </section>
			                                            </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer>
                                          <button type="submit" class="btn btn-primary" onclick="tijiao()">查&nbsp;&nbsp;&nbsp;询</button>
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
                            <h2>数据列表信息</h2>
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
                                    	<col width="100" />
                                        <col width="100" />
                                        <col width="200" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="250" />
                                        <col width="250" />
                                        <col width="100" />
                                        <col width="150" />
                                        <col width="150" />
                                        <thead>
                                        <tr>
                                        	 <td>客户姓名</td>
                                        	 <td>客户电话</td>
                                             <td>申请编号</td>
                                             <td>业务编号</td>
                                             <td>交易状态</td>
                                             <td>账户编号</td>
                                             <td>交易金额</td>
                                             <td>实际交易金额</td>
                                             <td>执行条数</td>
                                             <td>成功条数</td>
                                             <td>创建时间</td>
                                             <td>修改时间</td>
                                             <td>预约到账日期</td>
                                             <td>商户号</td>
                                             <td>交易渠道</td>
                                             <td>操作</td>
                                        </tr>
                                        </thead>
                                         <tbody>
                                             <c:forEach items="${page.list}" var="tradeapply">
                                                <tr>
                                                	<td>${tradeapply.custName}</td>
                                                	<td>${tradeapply.custMobile}</td>
                                                    <td>${tradeapply.applyNo}</td>
                                                    <td>${tradeapply.businessNo}</td>
                                                    <td><fss:dictView key="${tradeapply.tradeState}" /></td>
                                                    <td>${tradeapply.accNo}</td>
                                                    <td>${tradeapply.tradeAmount}</td>
                                                    <td>${tradeapply.realTradeAmount}</td>
                                                    <td>${tradeapply.count}</td>
                                                    <td>${tradeapply.successCount}</td>
                                                    <td><fss:fmtDate value="${tradeapply.createTime}"/></td>
                                                    <td><fss:fmtDate value="${tradeapply.modifyTime}"/></td>
                                                    <td><fss:fmtDate value="${tradeapply.bespokedate}"/></td>
                                                    <td>${tradeapply.mchnChild}</td>
                                                    <td><fss:dictView key="${tradeapply.channelNo}" /></td>
                                                    <td>
                                                    	<a href="${contextPath}/trade/tradeApply/${tradeapply.applyType}/${tradeapply.busiType}/${tradeapply.applyNo}/withdrawcheck">提现审核</a>
                                                       |<a href="${contextPath}/trade/tradeApply/${tradeapply.applyType}/${tradeapply.busiType}/${tradeapply.applyNo}/records">查看详细</a>
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
<%@include file="../../../view/include/common_footer_css_js.jsp"%>
</div>
 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#mortForm"));
        
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
	 
	    //验证输入的开户开始日期与结束日期 
	    function tijiao(){
	    	var startime=$("#startime").val();
	    	var endtime=$("#endtime").val();
	    	var d=new Date();
	    	var nowday=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
	    	var d1 = new Date(startime.replace(/\-/g, "\/"));  
   		var d2 = new Date(endtime.replace(/\-/g, "\/"));  
   		var d3 = new Date(nowday.replace(/\-/g, "\/"));  
	    	
	    	if(startime!="" && endtime.length==0){
	    		if(d1>d3){
	    			alert('查询开始时间不能早于当前时间！');
	    			return false;
	    		}
	    	}else
	    	if(startime!="" && endtime!=""){
	    		if(d1>d3 && d1<=d2){
	    			alert('您查询时间范围超前了！');
	    			return false;
	    		}else
	    		if(d1>d2){
	    			alert('查询开始时间不能早于结束时间！');
	    			return false;
	    		}
	    	}
	    }
	 

</script>

<%@include file= "../../../view/include/foot.jsp"%>
</body>

</html>