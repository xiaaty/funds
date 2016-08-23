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
                                <h2>ftp记录表</h2>
                            </header>
                            <!-- widget div-->
                            <div>

                                <form class="smart-form" id="mortgageePayment" method="post" >

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
                                                    <c:if test="${fuiouFtpOrder!=null && fuiouFtpOrder!=''}">
                                                        <tr>
                                                            <td class="tr" nowrap="nowrap">订单号：</td>
                                                            <td nowrap="nowrap">
                                                                <label class="input">
                                                                    ${fuiouFtpOrder.orderNo}
                                                                </label>
                                                            </td>
                                                            <td class="tr" nowrap="nowrap">产生文件数量：</td>
                                                            <td nowrap="nowrap">
                                                                <label class="input" style="width:210px" >
                                                                    ${fuiouFtpOrder.fileSize}
                                                                </label>
                                                            </td>
                                                            <td class="tr" nowrap="nowrap">生成待上传文件状态：</td>
                                                            <td nowrap="nowrap">
                                                                <label class="input" style="width:210px" >
                                                                    <c:if test="${fuiouFtpOrder.fileStatus == '1'}">未生成</c:if>
                                                                    <c:if test="${fuiouFtpOrder.fileStatus == '2'}">已生成</c:if>
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="tr" nowrap="nowrap">上传状态：</td>
                                                            <td nowrap="nowrap">
                                                                <label class="input">
                                                                    <c:if test="${fuiouFtpOrder.uploadStatus == '1'}">新增</c:if>
                                                                    <c:if test="${fuiouFtpOrder.uploadStatus == '2'}">部分上传</c:if>
                                                                    <c:if test="${fuiouFtpOrder.uploadStatus == '3'}">全部上传</c:if>
                                                                </label>
                                                            </td>
                                                            <td class="tr" nowrap="nowrap">回盘文件状态：</td>
                                                            <td nowrap="nowrap">
                                                                <label class="input" style="width:210px" >
                                                                    <c:if test="${fuiouFtpOrder.downloadStatus == '1'}">未回盘</c:if>
                                                                    <c:if test="${fuiouFtpOrder.downloadStatus == '2'}">部分回盘</c:if>
                                                                    <c:if test="${fuiouFtpOrder.downloadStatus == '4'}">全部回盘</c:if>
                                                                    <c:if test="${fuiouFtpOrder.downloadStatus == '8'}">拒盘文件</c:if>
                                                                </label>
                                                            </td>
                                                            <td class="tr" nowrap="nowrap">最后处理结果：</td>
                                                            <td nowrap="nowrap">
                                                                <label class="input" style="width:210px" >
                                                                    <c:if test="${fuiouFtpOrder.resultStatus == '1'}">未处理</c:if>
                                                                    <c:if test="${fuiouFtpOrder.resultStatus == '2'}">已处理</c:if>
                                                                    <c:if test="${fuiouFtpOrder.resultStatus == '3'}">全部处理完成</c:if>
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="tr" nowrap="nowrap">结果：</td>
                                                            <td nowrap="nowrap">
                                                                <label class="input">
                                                                    <c:if test="${fuiouFtpOrder.result == '1'}">成功</c:if>
                                                                    <c:if test="${fuiouFtpOrder.result == '2'}">失败</c:if>
                                                                    <c:if test="${fuiouFtpOrder.result == '3'}">部分成功</c:if>
                                                                </label>
                                                            </td>
                                                            <td class="tr" nowrap="nowrap">类型：</td>
                                                            <td nowrap="nowrap">
                                                                <label class="input" style="width:210px" >
                                                                    <c:if test="${fuiouFtpOrder.type == '1'}">满标</c:if>
                                                                    <c:if test="${fuiouFtpOrder.type == '2'}">还款</c:if>
                                                                    <c:if test="${fuiouFtpOrder.type == '3'}">流标</c:if>
                                                                </label>
                                                            </td>
                                                            <td class="tr" nowrap="nowrap">创建时间：</td>
                                                            <td nowrap="nowrap">
                                                                <label class="input" style="width:210px" >
                                                                    <fmt:formatDate value="${fuiouFtpOrder.inputDate}" pattern="yyyy-MM-dd HH:mm:ss" />
                                                                </label>
                                                            </td>
                                                        </tr>

                                                        <tr>
                                                            <td class="tr" nowrap="nowrap">结果返回状态：</td>
                                                            <td nowrap="nowrap">
                                                                <label class="input">
                                                                    <c:if test="${fuiouFtpOrder.retrunResultStatus == '0'}">未返回</c:if>
                                                                    <c:if test="${fuiouFtpOrder.retrunResultStatus == '1'}">已返回</c:if>
                                                                </label>
                                                            </td>
                                                        </tr>


                                                    </c:if>
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer>
                                        	<c:if test="${fuiouFtpOrder.type == '2'}">
                                               	<c:choose>
									           		<c:when test="${map.failureFlag eq '1'}">
									           			<td>
									           				<button class="btn btn-primary" onclick="failureRetry('${fuiouFtpOrder.id}','${fuiouFtpOrder.orderNo}');">失败重试</button>
									           			</td>
									           		</c:when>
									           		<c:otherwise>
									           			<td>
									           				<button class="btn" disabled="disabled" onclick="javascript:void(0);">失败重试</button>
									           			</td>
									       			</c:otherwise>
									           </c:choose>
                                        	</c:if>
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
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:3600px;">
                                        <col width="50" />
                                        <col width="150" />
                                        <col width="100" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <thead>
                                        <tr>
                                            <td>序号</td>
                                            <td>原用户名</td>
                                            <td>原中文名称</td>
                                            <td>目标用户名</td>
                                            <td>目标中文名称</td>
                                            <td>结果状态</td>
                                            <td>交易金额</td>
                                            <td>备注</td>
                                            <td>预授权合同号</td>
                                            <td>类型</td>
                                            <td>file文件表id</td>
                                            <td>文件序号</td>
                                            <td>order表id</td>
                                            <td>交易流水号</td>
                                            <td>返回结果</td>
                                            <td>返回消息</td>
                                            <td>业务代码</td>
                                            <td>创建日期</td>
                                            <td>业务流水号(提供给富有)</td>
                                            <td>备份</td>
                                            <td>投标id</td>
                                            <td>投标人/回款人id</td>
                                            <td>出借编号，线上客户</td>
                                            <td>借款人id</td>
                                            <td>借款合同编号</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t"  varStatus="l">
                                            <tr>
                                                <td>${l.index+1}</td>
                                                <td>${t.fromUserName}</td>
                                                <td>${t.fromCnUserName}</td>
                                                <td>${t.toUserName}</td>
                                                <td>${t.toCnUserName}</td>
                                                <td>
                                                    <c:if test="${t.state == '1'}">新增</c:if>
                                                    <c:if test="${t.state == '2'}">提交中</c:if>
                                                    <c:if test="${t.state == '3'}">已提交</c:if>
                                                    <c:if test="${t.state == '4'}">结果已处理</c:if>
                                                    <c:if test="${t.state == '5'}">结果返回</c:if>
                                                </td>
                                                <td>${t.amt}</td>
                                                <td>${t.rem}</td>
                                                <td>${t.contractNo}</td>
                                                <td>
                                                    <c:if test="${t.type == '1'}">批量开户</c:if>
                                                    <c:if test="${t.type == '2'}">批量扣款</c:if>
                                                    <c:if test="${t.type == '3'}">批量委托充值</c:if>
                                                    <c:if test="${t.type == '4'}">批量付款</c:if>
                                                    <c:if test="${t.type == '5'}">批量预授权</c:if>
                                                    <c:if test="${t.type == '6'}">批量划拨</c:if>
                                                    <c:if test="${t.type == '7'}">批量查余</c:if>
                                                    <c:if test="${t.type == '8'}">批量冻结</c:if>
                                                    <c:if test="${t.type == '9'}">批量解冻</c:if>
                                                </td>
                                                <td>${t.fileId}</td>
                                                <td>${t.seqNo}</td>
                                                <td>${t.orderId}</td>
                                                <td>${t.orderNo}</td>
                                                <td>
                                                    <c:if test="${t.returnCode == '0000'}">
                                                        成功
                                                    </c:if>
                                                    <c:if test="${t.returnCode != '0000'}">
                                                        失败
                                                    </c:if>
                                                </td>
                                                <td>
                                                    ${t.returnMsg}
                                                </td>
                                                <td>${t.businessCode}</td>
                                                <td><fmt:formatDate value="${t.inputDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                <td>${t.feildOrderNo}</td>
                                                <td>${t.feildOrderNoHis}</td>
                                                <td>${t.tenderId}</td>
                                                <td>${t.lendCustId}</td>
                                                <td>${t.lendNo}</td>
                                                <td>${t.loanCustId}</td>
                                                <td>${t.loanNo}</td>
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
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#mortgageePayment1"));
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
    
 	// 失败重试
    function failureRetry(orderId,orderNo){
    	if(window.confirm("您确定要执行此操作吗？")){
    		$.ajax({
    		 	url:"${contextPath}/trade/tradeRepay/ftpField/failureRetry",
    		 	type:"post",
    		 	async: false,
    			data:{
    				orderId:orderId,
    				orderNo:orderNo
    			},
    			success:function(data){
    				if (data == 'success') {
    					alert("操作成功!");
    					location.reload();
    				} else {
    					alert("操作失败!");
    				}
    			},
    			error:function(){
    				alert("操作异常!");
    			}
    		});
    	}
    }  
    
</script>

<%@include file= "../../../../view/include/foot.jsp"%>
</body>

</html>