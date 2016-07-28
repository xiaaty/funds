<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>交易记录--线下充值--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

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
            <li>交易记录</li>
            <li>线下充值</li>
        </ol>
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget" id="moList"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <h2>快速搜索</h2>
                        </header>
                        <div>
                            <form class="smart-form" id="offrechargeForm" action="${contextPath}/trade/tradeApply/offlineRecharge"  method="post" >
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
                                                <td class="tr">申请编号：</td>
                                                <td>
                                                    <label class="input">
                                                        <input type="text" style="width:200px" name="applyNo" value="${map.applyNo}" />
                                                    </label>
                                                </td>
                                                <td class="tr">业务编号：</td>
                                                <td>
                                                    <label class="input">
                                                        <input type="text" style="width:200px" name="busiNo" value="${map.busiNo}" />
                                                    </label>
                                                </td>
                                                <td class="tr">交易状态：</td>
                                                <td>
                                                    <select id = "resultState" name = "resultState" style="width:250px;height: 30px;">
                                                        <option value="">所有</option>
                                                        <option  <c:if test="${map.resultState==10120002}"> selected="selected" </c:if> value="10120002">充值码获取成功，待客户充值</option>
                                                        <option  <c:if test="${map.resultState==10120003}"> selected="selected" </c:if> value="10120003" >充值成功</option>
                                                        <option  <c:if test="${map.resultState==10120004}"> selected="selected" </c:if> value="10120004" >充值码获取失败</option>
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
                    <div class="jarviswidget jarviswidget-color-darken" id="dictList-id-0089"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>线下充值记录</h2>
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
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2900px;">
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="200" />
                                        <col width="100" />
                                        <col width="400" />
                                        <col width="200" />
                                        <col width="400" />
                                        <col width="300" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="200" />
                                        <col width="200" />
                                        <thead>
                                        <tr>
                                            <td>申请编号</td>
                                            <td>客户编号</td>
                                            <td>客户名称</td>
                                            <td>充值金额</td>
                                            <td>充值码</td>
                                            <td>充值码时间</td>
                                            <td>入账卡号</td>
                                            <td  align="left">入账户名</td>
                                            <td  align="left">入账银行</td>
                                            <td  align="left">支行信息</td>
                                            <td>交易状态</td>
                                            <td>业务编号</td>
                                            <td>所属商户</td>
                                            <td>流水号</td>
                                            <td>创建时间</td>
                                            <td>修改时间</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t">
                                            <tr>
                                                <td>${t.applyNo}</td>
                                                <td>${t.custId}</td>
                                                <td>${t.custName}</td>
                                                <td><fss:money money="${t.amt}" /></td>
                                                <td>${t.chgCd}</td>
                                                <td>${t.chgDt}</td>
                                                <td>${t.fyAccNo}</td>
                                                <td align="left">${t.fyAccNm}</td>
                                                <td align="left">${t.fyBank}</td>
                                                <td align="left">${t.fyBankBranch}</td>
                                                <td><fss:dictView key="${t.resultState}"/></td>
                                                <td>${t.busiNo}</td>
                                                <td>${t.mchn}</td>
                                                <td>${t.seqNo}</td>
                                                <td><fss:fmtDate value="${t.createTime}"/></td>
                                                <td><fss:fmtDate value="${t.modifyTime}"/></td>
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
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#offrechargeForm"));
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
                $("#offrechargeForm").submit();
            }
        }else{
            var d = new Date();
            var str = d.getFullYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+(d.getDate()<10?"0":"")+d.getDate();
            if(a[0].value>str){
                JAlert("请检查您输入的日期","提示消息");
            }else{
                $("#offrechargeForm").submit();
            }
        }
    }
    //添加
    $("#btn_add").button().click(function() {
        window.open("${contextPath}/trade/tradeApply/createOfflineRecharge","_self");
    });
</script>

<%@include file= "../../../view/include/foot.jsp"%>
</body>

</html>