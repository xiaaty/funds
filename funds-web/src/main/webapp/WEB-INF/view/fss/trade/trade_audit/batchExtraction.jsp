<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>分批提现--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

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

        .button-icon i{
            line-height:32px;
        }

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
            <li>信用标放款</li>
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
                                                        <td class="tr" nowrap="nowrap">交易流水号：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="seqNo" value="${map.seqNo}">
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

    <div id="contentss">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-darken" id="borrowerLoan"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>信用标放款</h2>
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
                                    <div class="widget-body-nobg-toolbar" style="overflow:hidden;">
                                        <button type="button" class="btn btn-default fl table-nobg-btn" id="btn_add">&nbsp;设置首次提现比例&nbsp;</button>
                                        <%--<input type="hidden" id="parentId" value="${scale}" />--%>
                                    </div>
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2400px;">
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
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200"/>
                                        <col width="300"/>
                                        <thead>
                                        <tr>
                                            <td></td> 
                                            <%--<td>抵押权人资金平台账号</td>
                                            <td>借款人资金平台账号</td>--%>
                                            <td>交易流水号</td>
                                            <td>合同编号</td>
                                            <td>合同金额</td>
                                            <td>放款金额</td>
                                            <td>首次提现金额</td>
                                            <td>二次提现金额</td>
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
                                                    <%--<td>${t.mortgageeAccNo}</td>
                                                    <td>${t.accNo}</td>--%>
                                                    <td>${t.seqNo}</td>
                                                    <td>${t.contractNo}</td>
                                                    <td>
                                                        <fss:money money="${t.contractAmt}"/>
                                                    </td>
                                                    <td>
                                                        <fss:money money="${t.payAmt}"/>
                                                    </td>
                                                    <td>
                                                        <fss:money money="${t.firstAmt}"/>
                                                    </td>
                                                    <td>
                                                        <fss:money money="${t.secondAmt}"/>
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

                                                    <%--xdw  缺少交易流水号。--%>
                                                    <%--<td>${t.seqNo}</td>--%>

                                                    <td>${t.mchnParent}</td>
                                                    <td><fmt:formatDate value="${t.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    <td><fmt:formatDate value="${t.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    <td>
                                                    <a href="${contextPath}/loan/trade/${type}/${t.id}/feeList">查看</a>
                                                    &nbsp; &nbsp;
                                                    <c:if test="${t.status == '10050009'}">
                                                        <a href="javascript:void(0)" onclick="firstWithDraw(${t.payAmt},${t.id})">首次提现 </a>
                                                        &nbsp; &nbsp;
                                                        <a href="javascript:void(0)" onclick="jumpWithDraw(${t.id})">提现跳过</a>
                                                        &nbsp; &nbsp;
                                                    </c:if>
                                                    <c:if test="${t.status == '10050017'}">
                                                        <a href="javascript:void(0)" onclick="chargeWithHold(${t.id})">费用代扣 </a>
                                                        &nbsp; &nbsp;
                                                        <a href="javascript:void(0)" onclick="jumpRechargeWithHold(${t.id})">代扣跳过</a>

                                                        &nbsp; &nbsp;

                                                    </c:if>
                                                    <c:if test="${t.status == '10050019' || t.status == '10050025'}">
                                                        <a href="${contextPath}/loan/trade/${type}/charge/${t.id}" >收费 </a>
                                                        &nbsp; &nbsp;
                                                        <a href="javascript:void(0)" onclick="jumpRecharge(${t.id})">收费跳过</a>
                                                        &nbsp; &nbsp;
                                                    </c:if>
                                                    <c:if test="${t.status == '10050022'}">
                                                        <a href="javascript:void(0)" onclick="secondWithDraw(${t.payAmt},${t.firstAmt},${t.id})">二次提现 </a>
                                                        &nbsp; &nbsp;
                                                        <a href="javascript:void(0)" onclick="jumpWithDraw(${t.id})">提现跳过</a>
                                                        &nbsp; &nbsp;
                                                    </c:if>
                                                        <%--查看借款跑批--%>
                                                        <c:choose>
                                                            <c:when test="${t.orderNo!=null && t.orderNo!=''}">
                                                                <a href="${contextPath}/trade/tradeRepay/ftpField/${t.orderNo}">
                                                                    查看借款跑批
                                                                </a>
                                                            </c:when>
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

</div>
                </section>
        </div>
    </div>

<script src="${contextPath}/js/jquery.form.js" ></script>
<%@include file= "../../../../view/include/common_footer_css_js.jsp"%>
<div class="box_pop"  style="display:none;">
<div class="pop" style="display:block;position: absolute;z-index:9999;left:50%;top:50%;margin-left:-200px;margin-top:-200px;width: 400px;padding: 30px;border:solid 2px #008299;border-radius:2px;background: white;" >
<form id="uploadForm" method="post"  enctype="multipart/form-data" style="align-content: center">
    <h1 class="f18" align="center">提现百分比设置</h1>
    <hr/>
    <div class="mb25 pr" style="align-content: center">
        <table class="table  tc mt15" frame="void" >
            <col width="70" />
            <col width="150" />
            <tr style="border: none" >
                <td align="right">合同金额：</td>
                <td align="left">
                    <input type="hidden" id="id"/>
                    <input type="text"style="width:100px;height: 28px;border: none" id="contractAmount" disabled ><br/>
                </td>
            </tr>
            <tr>
                <td align="right">提现金额比例：</td>
                <td align="left">
                    <input type="text"style="width:100px;height: 28px;" id="scale" onkeyup="scales();" value="${scale}">%
                </td>
            </tr>
            <tr>
                <td align="right"> 提现金额：</td>
                <td align="left">
                    <input type="text"style="width:100px;height: 28px;border: none" name="amount" id="ampont" />
                </td>
            </tr>
        </table>

    <div class="mb20" id="wid-id-713" style="">
        <button class="btn btn-primary " id="import" type="button" title="导入">提&nbsp;现</button>&nbsp;&nbsp;
        <button class="btn btn-default fl mr30" id=""  type="button"  title="取消">取&nbsp;消</button>
        <%--<div class="mt20"><a class="btn_import fl" href="#" id="import" title="导入">导&nbsp;入</a>--%>
        <%--<a id="aaaaa" class="fl btn_cancel ml30" href="#" title="取消">取&nbsp;消</a>--%>
    </div>
</div>
</form>
</div>
</div>
<div class="pop" id="second" style="display:none;position: absolute;z-index:9999;left:50%;top:50%;margin-left:-200px;margin-top:-200px;width: 400px;padding: 30px;border:solid 2px #008299;border-radius:2px;background: white;" >
    <form id="loadForm" method="post"  enctype="multipart/form-data" style="align-content: center">
        <h1 class="f18" align="center">提现金额设置</h1>
        <hr/>
        <div class="mb25 pr" style="align-content: center">
            <table class="table  tc mt15" frame="void" >
                <col width="70" />
                <col width="150" />
                <tr style="border: none" >
                    <td align="right">合同金额：</td>
                    <td align="left">
                        <input type="hidden" id="tid"/>
                        <input type="text"style="width:100px;height: 28px;border: none" id="conAmount" disabled><br/>
                    </td>
                </tr>
                <tr>
                    <td align="right"> 二次提现金额：</td>
                    <td align="left">
                        <input type="text"style="width:100px;height: 28px;" name="amount" id="secondAmt" />
                    </td>
                </tr>
            </table>

            <div class="mb20" id="wid-id-714" style="">
                <button class="btn btn-primary " id="withDraw" type="button" title="导入">提&nbsp;现</button>&nbsp;&nbsp;
                <button class="btn btn-default" id="cancel"  type="button"  title="取消">取&nbsp;消</button>
            </div>
        </div>
    </form>
</div>
<div class="pop" id="updateScale" style="display:none;position: absolute;z-index:9999;left:50%;top:50%;margin-left:-200px;margin-top:-200px;width: 400px;padding: 30px;border:solid 2px #008299;border-radius:2px;background: white;" >
    <form id="scaleForm" method="post"  enctype="multipart/form-data" style="align-content: center">
        <h1 class="f18" align="center">提现比例设置</h1>
        <hr/>
        <div class="mb25 pr" style="align-content: center">
            <table class="table  tc mt15" frame="void" >
                <col width="70" />
                <col width="150" />
                <tr style="border: none" >
                    <td align="right">比例：</td>
                    <td align="left">
                        <input type="text"style="width:100px;height: 28px;" id="ratio" name="scale" value="${scale}"  />%
                    </td>
                </tr>
            </table>

            <div class="mb20" id="wid-id-715" style="">
                <button class="btn btn-primary " id="update" type="button" title="导入">修&nbsp;改</button>&nbsp;&nbsp;
                <button class="btn btn-default " id="cancels"  type="button"  title="取消">取&nbsp;消</button>
            </div>
        </div>
    </form>
</div>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
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
    function firstWithDraw(amount,id){
        $("#contractAmount").val(amount);
        $("#ampont").val(Number(amount*${scale}/100).toFixed(2));
        $("#id").val(id);
        $('.mask').show();
        $('.box_pop').show();
    }
    $(".mr30").click(function () {
        $('.mask').hide();
        $('.box_pop').hide();
        $("#scale").val(${scale});
    })
    $("#cancel").click(function () {
        $('.mask').hide();
        $('#second').hide();
    })
    $("#btn_add").click(function () {
        $('.mask').show();
        $('#updateScale').show();
    })
    $("#cancels").click(function () {
        $('.mask').hide();
        $('#updateScale').hide();
        $("#ratio").val(${scale});
    })
    function jumpWithDraw(id) {
        if(confirm("您确认跳过本次提现吗？")){
            location.href= "${contextPath}/loan/trade/${type}/jumpWithDraw/"+id
        }
    }
    function jumpRechargeWithHold(id) {
        if(confirm("您确认跳过费用代扣吗？")){
            location.href= "${contextPath}/loan/trade/${type}/jumpWithDraw/"+id
        }
    }
    function jumpRecharge(id) {
        if(confirm("您确认跳过收费吗？")){
            location.href= "${contextPath}/loan/trade/${type}/jumpWithDraw/"+id
        }
    }
     function  scales() {
        var scale=$("#scale").val();
         if(scale<100 && scale>0){
            var contractAmount=$("#contractAmount").val();
            var amount=contractAmount*scale/100;
             var q=Number(amount).toFixed(2);
            $("#ampont").val(Number(amount).toFixed(2));
         }else {
             $("#scale").val("");
             alert("请输入正确的提现比例");
         }

     }
    $("#import").click(function () {
        var url="${contextPath}/loan/trade/${type}/bathWithDraw/"+$("#id").val();
        $("#uploadForm").ajaxSubmit({
            url:url,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            success: function (data) {
                if (data.code == '0000') {
                    alert(data.msg);
                    $('.box_pop').hide();
                    location.reload();
                } else {
                    alert(data.msg);
                    $('.box_pop').hide();
                    location.reload();
                }

            }
        });
    });
    function chargeWithHold(id) {
        if(confirm("您确认代扣费用吗？")){
            location.href= "${contextPath}/loan/trade/${type}/chargeWithHold/"+id
        }
    }
   function secondWithDraw(payAmt,firstAmt,id) {
       $("#second").show();
       if(firstAmt==null || firstAmt==""){
           firstAmt=0;
       }
       var secondAmt=payAmt-firstAmt;
       $("#conAmount").val(payAmt);
       $("#tid").val(id);
       $("#secondAmt").val(secondAmt);
        var url="${contextPath}/loan/trade/${type}/bathWithDraw/"+id;
//        $.ajax({
//            type : "POST",
//            url:url,
//            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
//            dataType: "json",
//            success: function (data) {
//                if (data.code == '0000') {
//                    alert(data.msg);
//                    $('.box_pop').hide();
//                    location.reload();
//                } else {
//                    alert(data.msg);
//                    $('.box_pop').hide();
//                }
//
//            }
//        });
    }
    $("#withDraw").click(function () {

        var url="${contextPath}/loan/trade/${type}/bathWithDraw/"+$("#tid").val();
        $("#loadForm").ajaxSubmit({
            url:url,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            success: function (data) {
                if (data.code == '0000') {
                    alert(data.msg);
                    $('.pop').hide();
                    location.reload();
                } else {
                    alert(data.msg);
                    $('.pop').hide();
                    location.reload();
                }

            }
        });
    });
    $("#update").click(function () {
        var url="${contextPath}/loan/trade/${type}/updateScale";
        $("#scaleForm").ajaxSubmit({
            url:url,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            success: function (data) {
                if (data.code == '0000') {
                    alert(data.msg);
                    $('.pop').hide();
                    location.reload();
                } else {
                    alert(data.msg);
                    $('.pop').hide();
                    location.reload();
                }

            }
        });
    });
</script>

<%--<%@include file= "../../../../view/include/foot.jsp"%>--%>
</body>

</html>