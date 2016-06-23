<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                      <div class="jarviswidget" id="withDrawList"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <div>
                                <form class="smart-form" action="${contextPath}/trade/tradeApply/${type}/${bus}"  method="post" id="withDrawForm">
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
                                                <tr>
                                                    <td class="tr">申请编号：</td>
                                                    <td>
                                                        <label class="input">
                                                            <input type="text" style="width:150px" name="applyNo" value="${map.applyNo}" />
                                                        </label>
                                                    </td>
                                                    <td class="tr">业务编号：</td>
                                                    <td>
                                                        <label class="input">
                                                            <input type="text" style="width:150px" name="businessNo" value="${map.businessNo}" />
                                                        </label>
                                                    </td>
                                                    <td class="tr">客户姓名：</td>
                                                    <td>
                                                        <label class="input">
                                                            <input type="text" style="width:150px" name="custName" value="${map.custName}" />
                                                        </label>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="tr">客户电话：</td>
                                                    <td>
                                                        <label class="input">
                                                            <input type="text" style="width:150px" name="custMobile" value="${map.custMobile}" />
                                                        </label>
                                                    </td>
                                                    <td class="tr">申请状态：</td>
                                                    <td>
                                                        <label>
                                                            <select id = "applyState" name = "applyState" style="width:150px;height: 30px;">
                                                                <option value="">请选择</option>
                                                                <option  <c:if test="${map.applyState==10100001}"> selected="selected" </c:if> value="10100001">新增</option>
                                                                <option  <c:if test="${map.applyState==10100002}"> selected="selected" </c:if> value="10100002" >审核成功待执行</option>
                                                                <option  <c:if test="${map.applyState==10100003}"> selected="selected" </c:if> value="10100003" >执行排队中</option>
                                                                <option  <c:if test="${map.applyState==10100004}"> selected="selected" </c:if> value="10100004" >执行中</option>
                                                                <option  <c:if test="${map.applyState==10100005}"> selected="selected" </c:if> value="10100005" >执行完成</option>
                                                                <option  <c:if test="${map.applyState==10109999}"> selected="selected" </c:if> value="10109999" >审核未通过</option>
                                                            </select>
                                                            <label>
                                                    </td>
                                                    <td class="tr">交易状态：</td>
                                                    <td>
                                                        <select id = "tradeState" name = "tradeState" style="width:150px;height: 30px;">
                                                            <option value="">请选择</option>
                                                            <option  <c:if test="${map.tradeState==10080001 || map.tradeState==null}"> selected="selected" </c:if> value="10080001">新增</option>
                                                            <option  <c:if test="${map.tradeState==10080002}"> selected="selected" </c:if> value="10080002" >交易成功</option>
                                                            <option  <c:if test="${map.tradeState==10080003}"> selected="selected" </c:if> value="10080003" >交易部分成功</option>
                                                            <option  <c:if test="${map.tradeState==10080010}"> selected="selected" </c:if> value="10080010" >交易失败</option>
                                                            <option  <c:if test="${map.tradeState==10080011}"> selected="selected" </c:if> value="10080011" >交易取消</option>
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
                                          <button class="btn btn-primary" onclick="javascript:void(0);">查&nbsp;&nbsp;&nbsp;询</button>
                                        </footer>
                                    </div>
                                    <!-- end widget content -->
    							</form>
                    		</div>
                		</div>
                
                    <!-- NEW WIDGET START -->
                    <div class="jarviswidget jarviswidget-color-darken" id="dictList-id-89"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>数据列表信息</h2>
                        </header>
                        <div class="user_operate mb10 clearfix">
                            <button class="btn btn-default" id="btn_rech">批量提现</button>
                        </div>
                        <div>
                            <form class="smart-form">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2450px;">
                                    	<col width="50" />
                                    	<col width="100" />
                                        <col width="100" />
                                        <col width="200" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="150" />
                                        <col width="150" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="150" />
                                        <col width="100" />
                                        <col width="250" />
                                        <thead>
                                        <tr>
                                             <td><input type="checkbox" id="checkAll"/></td>
                                        	 <td>客户姓名</td>
                                        	 <td>客户电话</td>
                                             <td>申请编号</td>
                                             <td>业务编号</td>
                                             <td>申请状态</td>
                                             <td>交易状态</td>
                                             <td>账户编号</td>
                                             <td>交易金额</td>
                                             <td>实际交易金额</td>
                                             <td>总条数</td>
                                             <td>执行条数</td>
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
                                                    <td><input type="checkbox" class="checkBoxAll" value="${tradeapply.applyNo}"/></td>
                                                	<td>${tradeapply.custName}</td>
                                                	<td>${tradeapply.custMobile}</td>
                                                    <td>${tradeapply.applyNo}</td>
                                                    <td>${tradeapply.businessNo}</td>
                                                    <td><fss:dictView key="${tradeapply.applyState}" /></td>
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
                                                    <input type="hidden" name="token" value="${token}"/>
                                                        <c:choose>
                                                            <c:when test="${tradeapply.applyState==10100001}">
                                                                <a href="${contextPath}/trade/tradeApply/${tradeapply.applyType}/${tradeapply.busiType}/${tradeapply.applyNo}/withdrawcheck">提现审核</a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a style="display: none;" href="${contextPath}/trade/tradeApply/${tradeapply.applyType}/${tradeapply.busiType}/${tradeapply.applyNo}/withdrawcheck">提现审核</a>
                                                                &nbsp;&nbsp;&nbsp;
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <a href="${contextPath}/trade/tradeApply/${tradeapply.applyNo}/records?id=${tradeapply.id}">查看详细</a>
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
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#withDrawForm"));
        $("#checkAll").removeAttr("checked");
    });
    $('#checkAll').bind('click', function () {
        var that = this;
        $('.checkBoxAll').each(function () {
            this.checked = that.checked;
        });
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
    			jAlert("请检查您输入的日期","提示消息");
    		}else{
    			$("#withDrawForm").submit();
    		}
    	}else{
    		var d = new Date();
    		var str = d.getFullYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+(d.getDate()<10?"0":"")+d.getDate();
    		if(a[0].value>str){
    			jAlert("请检查您输入的日期","提示消息");
    		}else{
    			$("#withDrawForm").submit();
    		}
    	}
    }
    //批量代付按钮
    $('#btn_rech').click(function () {
        var no = $('#borrow-rep-table12 tbody :checkbox:checked');
        if (no.size() == 0) {
            alert("请选择件数！");
            return false;
        }
        var param = [];
        no.each(function () {
            param.push($(this).val());
        })
//        alert(param.toString());
        if(confirm("您确认全部审核成功吗？")){
        $.post("${contextPath}/trade/tradeApply/moneySplit", {'no': param.toString()}, function (data) {
            if (data.code == '0000') {
                alert("成功", '消息提示');
                $("#withDrawForm").submit();
                $("#checkAll").removeAttr("checked");
                return false;
            }else if(data.code == '0001'){
                alert(data.message, '消息提示');
                $("#withDrawForm").submit();
                $("#checkAll").removeAttr("checked");
                return false;
            }
        }, "json");
        }
    });

 </script>

<%@include file= "../../../view/include/foot.jsp"%>
</body>

</html>