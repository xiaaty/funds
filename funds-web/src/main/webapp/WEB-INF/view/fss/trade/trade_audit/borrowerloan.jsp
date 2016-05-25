<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>交易审核--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

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
                           
                                <form class="smart-form" id="mortgageePayment" action="${contextPath}/loan/trade/${type}" method="post" >
                              
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
                                                        <td class="tr" nowrap="nowrap">客户编号：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="custNo" value="${map.custNo}">
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">合同编号：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input" style="width:210px" >
                                                                <input type="text" name="contractNo" value="${map.contractNo}">
                                                            </label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                    <td class="tr" nowrap="nowrap">交易状态：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="select">
							                                <select class="select02" style="width:202px;" name="status" id="status">
                                                                   <option value="">所有</option>
							                                   <fss:dictOrder var="order" dictOrder="tradeStatus">
							                                   	
                                                                   <option value="${order.key}"  <c:if test="${order.key==map.status}">selected</c:if> >${order.value}</option>
                                                               </fss:dictOrder>
							                                </select>
                                                                </label>
                                                        </td>
                                                         <td class="tr">交易日期：</td>
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
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2400px;">
                                        <col width="50" />
                                        <col width="200" />
                                        <col width="200" /> 
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
                                        <col width="300"/>
                                        <thead>
                                        <tr>
                                            <td></td> 
                                            <td>抵押权人资金平台账号</td>
                                            <td>借款人资金平台账号</td>
                                            <td>客户编号</td>
                                            <td>合同编号</td>
                                            <td>合同金额  </td>
                                            <td>放款金额   </td>
                                            <td>借款平台</td>
                                            <td>交易状态 </td> 
                                            <td>交易类型</td> 
                                            <td>所属商户 </td>
                                            <td>交易日期 </td>
                                            <td>修改日期 </td>
                                            <td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t"  varStatus="l">
                                                <tr>
                                                    <td>${l.index+1}</td>
                                                    <td>${t.mortgageeAccNo}</td>
                                                    <td>${t.accNo}</td>
                                                    <td>${t.custNo}</td>
                                                    <td>${t.contractNo}</td>
                                                    <td>
                                                        <fss:money money="${t.contractAmt}"/>
                                                    </td>
                                                    <td>
                                                        <fss:money money="${t.payAmt}"/>
                                                    </td>
                                                    <td>
                                                        <fss:dictView key="${t.loanPlatform}" />
                                                    </td>
                                                     <td>
	                                                    <fss:dictView key="${t.status}" />
                                                    </td>
                                                    <td>
                                                    <fss:dictView key="${t.tradeType}" />
                                                    </td>
                                                    <td>${t.mchnParent}</td>
                                                    <td><fmt:formatDate value="${t.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    <td><fmt:formatDate value="${t.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    <td>
                                                    <c:if test="${t.tradeType != '11092001'}">
                                                    <a href="${contextPath}/loan/trade/${type}/${t.id}/feeList">查看</a>
                                                    </c:if>
                                                    &nbsp; &nbsp;
                                                      <c:if test="${t.tradeType == '11090001' && t.status != '10050002'}">
                                                      		<c:if test="${t.status== '10050001'}">
																<a href="${contextPath}/loan/trade/${type}/toWithHold/${t.id}">代扣</a>
																&nbsp; &nbsp;
															</c:if>
															<c:if test="${t.status == '10050003'|| t.status=='10050001'}">
																<a href="${contextPath}/loan/trade/${type}/transfer/${t.id}">转给借款人</a>
																&nbsp; &nbsp;
															</c:if>
															<c:if test="${t.status == '10050005'}">
																<a href="${contextPath}/loan/trade/${type}/charge/${t.id}">收费 </a>
																&nbsp; &nbsp;
															</c:if>
													  </c:if>
                                                      <c:if test="${t.tradeType == '11090002'}">
															<c:if test="${t.status == '10050009'}">
																<a href="${contextPath}/loan/trade/${type}/charge/${t.id}">收费 </a>
																&nbsp; &nbsp;
															</c:if>
													  </c:if>
                                                      <c:if test="${t.tradeType == '11090011' || t.tradeType=='11090010'}">
                                                      		<c:if test="${t.status=='10050010'}">
																<a href="${contextPath}/loan/trade/${type}/recharge/${t.id}">退费 </a>
                                                      		</c:if>
																&nbsp; &nbsp;
															<c:if test="${t.tradeType == '11090010'}">
															<c:if test="${t.status=='10050010' ||t.status=='10050099'}">
																<a href="${contextPath}/loan/trade/${type}/retransfer/${t.id}" >转账</a>
																&nbsp; &nbsp;
																</c:if>
															</c:if>
															<c:if test="${t.tradeType == '11090011'}">
															<c:if test="${t.status!='10050100' && t.status!='11050011'}">
																<a href="${contextPath}/loan/trade/${type}/abort/${t.id}">退款 </a>
																</c:if>
																&nbsp; &nbsp;
															</c:if>
															
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