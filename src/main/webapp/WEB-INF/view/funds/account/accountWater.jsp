<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <%@include file= "../../../view/include/common_css_js.jsp"%>
     <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
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
            <li>资金管理</li>
            <li>账户管理</li>
            <li>查看流水</li>
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
                           
                                <form class="smart-form" id="waterDetailForm" action="${contextPath}/funds/account/accountWater/${id}" method="post" >
                              
                                    <!-- widget edit box -->
                                    <div class="jarviswidget-editbox">
                                        <!-- This area used as dropdown edit box -->
                                    </div>
                                    <!-- end widget edit box -->
                                    <!-- widget content -->
                                    <div class="widget-body no-padding">
                                        <div class="mt10 mb10">
                                            <table class="table lh32">
                                                <tbody>
                                                    <tr>
                                                    
                                                    <td class="tr" nowrap="nowrap">客户姓名:</td>
                                                    
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:150px" >
                                                                <input type="text" name="customerName" value="${fasMap.customerName}">
                                                            </label>
                                                        </td>
                                                    <td class="tr">账户类型：</td>
		                                                <td>
			                                                <section style="width:100px">
	                                                    		<label class="select">
												                <select id="applyType" name ="accountType">
												                <option value=""  >--不限--</option>
												                    <option value="1" <c:if test="${fasMap.accountType==1}">selected='selected'</c:if> >客户账户</option>
												                    <option value="2" <c:if test="${fasMap.accountType==2}">selected='selected'</c:if> >AO</option>
												                    <option value="3" <c:if test="${fasMap.accountType==3}">selected='selected'</c:if> >AX</option>
												                </select>
												            </label>
												             </section>
                                                </td>
                                                  <td class="tr">业务类型：</td>
		                                                <td>
			                                                <section style="width:100px">
	                                                    		<label class="select">
												                <select id="busiType" name ="busiType">
												                    <option value="">--不限--</option>
												                    <option value="0" <c:if test="${fasMap.busiType==0}">selected='selected'</c:if> >主帐户</option>
												                    <option value="1" <c:if test="${fasMap.busiType==1}">selected='selected'</c:if> >借款客户</option>
												                    <option value="2" <c:if test="${fasMap.busiType==2}">selected='selected'</c:if> >线下出借客户</option>
												                    <option value="3" <c:if test="${fasMap.busiType==3}">selected='selected'</c:if> >线上出借客户</option>
												                    <option value="96" <c:if test="${fasMap.busiType==96}">selected='selected'</c:if> >线下用应付款账户 </option>
												                    <option value="99" <c:if test="${fasMap.busiType==99}">selected='selected'</c:if> >冻结金账户</option>
												                </select>
												            </label>
												             </section>
                                                </td>
                                                  <td class="tr">流水类型：</td>
		                                                <td>
			                                                <section style="width:150px">
	                                                    		<label class="select">
												                <select id="actionType" name ="actionType">
												              		  <option value="" >--不限--</option>
												                    <option value="1" <c:if test="${fasMap.actionType==1}">selected='selected'</c:if> >充值</option>
												                    <option value="2" <c:if test="${fasMap.actionType==2}">selected='selected'</c:if> >提现</option>
												                    <option value="3" <c:if test="${fasMap.actionType==3}">selected='selected'</c:if> >转账</option>
												                    <option value="4" <c:if test="${fasMap.actionType==4}">selected='selected'</c:if> >冻结</option>
												                    <option value="5" <c:if test="${fasMap.actionType==5}">selected='selected'</c:if> >解冻</option>
												                    <option value="6" <c:if test="${fasMap.actionType==6}">selected='selected'</c:if> >投标成功</option>
												                    <option value="7" <c:if test="${fasMap.actionType==7}">selected='selected'</c:if> >还款</option>
												                    <option value="8" <c:if test="${fasMap.actionType==8}">selected='selected'</c:if> >债权转让</option>
												                </select>
												            </label>
												             </section>
                                                </td>
                                                    
                                                    
                                                         
                                                    </tr>
                                                    <tr>
                                                         <td class="tr">创建日期：</td>
                                            <td colspan="3">
                                                <section class="fl">
                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                        <input type="text" maxlength="10" readonly="readonly" name="startDate" class="selectdate" placeholder="请选择时间" value="${fasMap.startDate}">
                                                    </label>
                                                </section>
                                                <span class="fl">&nbsp;至&nbsp;</span>
                                                <section class="fl">
                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                        <input type="text" maxlength="10" readonly="readonly"  name="endDate" class="selectdate" placeholder="请选择时间" value="${fasMap.endDate}">
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

                        </div>
    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-30"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>流水详情</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="water">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                     <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:950px;">
                                    	<col width="150" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="150" />
                                    	<col width="150" />
                                    	<col width="100" />
                                        <thead>
                                        <tr>
                                            <td>账户编号 </td>
                                            <td>客户姓名 </td>
                                            <td>账户类型</td>
                                            <td>业务类型 </td>
                                            <td>流水类型</td>
                                            <td>流水金额 </td>
                                            <td>创建日期 </td>
                                            <td>所属机构 </td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t">
                                                <tr>
                                                    <td>${t.accountNo}</td>                  
                                                    <td>${t.customerName}</td>
                                                    <td>
                                                    <c:if test="${t.accountType==1}">客户账户</c:if>
                                                    <c:if test="${t.accountType==2}">A0 </c:if>
                                                    <c:if test="${t.accountType==3}">AX</c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${t.busiType==0}">主帐户 </c:if> 
								                     <c:if test="${t.busiType==1}">借款客户 </c:if> 
								                     <c:if test="${t.busiType==2}">线下出借客户 </c:if> 
								                     <c:if test="${t.busiType==3}">线上出借客户 </c:if> 
								                   <c:if test="${t.busiType==96}">线下用应付款账户</c:if>  
								                    <c:if test="${t.busiType==99}">冻结金账户 </c:if> 												               
                                                    </td>
                                                    <td>
                                                     <c:if test="${t.actionType==1}">充值</c:if> 
									                 <c:if test="${t.actionType==2}">提现</c:if> 
									                 <c:if test="${t.actionType==3}">转账</c:if> 
									                 <c:if test="${t.actionType==4}">冻结</c:if> 
									                 <c:if test="${t.actionType==5}">解冻 </c:if> 
									                 <c:if test="${t.actionType==6}">投标成功 </c:if>
									                 <c:if test="${t.actionType==7}">还款 </c:if>
									                 <c:if test="${t.actionType==8}">债权转让 </c:if>
												               
                                                    </td>
                                                    <td>${t.amount}</td>
                                                    <td> <fmt:formatDate value="${t.creatTime}" pattern="yyyy-MM--dd HH:mm:ss"/></td>
                                                    <td>${t.thirdPartyType==2?"富友":"其他"}</td>
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
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#waterDetailForm"));
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
    	var a=document.getElementsByName("startDate");
    	var b=document.getElementsByName("endDate");
    	if(b[0].value!=null&&b[0].value!=''){
    		if(a[0].value>b[0].value){
    			alert("请检查您输入的日期");
    		}else{
    			$("#waterDetailForm").submit();
    		}
    	}else{
    		var d = new Date();
    		var str = d.getFullYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+(d.getDate()<10?"0":"")+d.getDate();
    		if(a[0].value>str){
    			alert("请检查您输入的日期");
    		}else{
    			$("#waterDetailForm").submit();
    		}
    	}
    }
</script>

<%@include file= "../../../view/include/foot.jsp"%>
</body>

</html>