<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统管理--配置管理--交易渠道配置--渠道配置页</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">

    <%@include file="../../../../view/include/common_css_js.jsp"%>
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
        .tab-btn {
            width : 70px;
            height: 35px;
            margin-top: 0px;
            margin-bottom: 10px;
            float: right;
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
            <li>系统管理</li>
            <li>配置管理</li>
            <li>交易渠道配置</li>
            <li>渠道配置页</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <!-- NEW WIDGET START -->
                    <div class="jarviswidget jarviswidget-color-darken" id="customerInfoMsg"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <h2>渠道信息</h2>
                        </header>
                        <div>
                            <table class="table table-bordered tc mt15" style="min-width:1100px;">
                                <col width="10" />
                                <col width="110" />
                                <col width="110" />
                                <col width="110" />
                                <col width="110" />
                                <col width="110" />
                                <col width="110" />
                                <col width="110" />
                                <thead>
                                <tr>
                                    <td>渠道编码 </td>
                                    <td>渠道名称 </td>
                                    <td>渠道状态 </td>
                                    <td>渠道方式 </td>
                                    <td>渠道支付模式 </td>
                                    <td>创建日期 </td>
                                    <td>修改日期 </td>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>
                                        ${channelOrgEntity.channelCode}
                                    </td>
                                    <td>
                                        <%--${channelOrgEntity.channelName}--%>
                                        <fss:dictView key="${channelOrgEntity.channelCode}"/>
                                    </td>
                                    <td>
                                        <select class="select02" style="width:202px;" name="channelCondition" id="channelCondition">
                                            <fss:dictOrder var="order" dictOrder="isValid">
                                                <option value="${order.key}"
                                                    <c:if test="${order.key== channelOrgEntity.channelCondition}">selected</c:if>>
                                                    ${order.value}
                                                </option>
                                            </fss:dictOrder>
                                        </select>

                                    </td>
                                    <td>
                                        <select class="select02" style="width:202px;" name="channelType" id="channelType">
                                            <fss:dictOrder var="order" dictOrder="DictChannelType">
                                                <option value="${order.key}"
                                                <c:if test="${order.key== channelOrgEntity.channelType}">selected</c:if>>
                                                ${order.value}
                                                </option>
                                            </fss:dictOrder>
                                        </select>
                                    </td>
                                    <td>
                                        <select class="select02" style="width:202px;" name="channelPaymentMode" id="channelPaymentMode">
                                            <fss:dictOrder var="order" dictOrder="channelPaymentMode">
                                                <option value="${order.key}"
                                                <c:if test="${order.key== channelOrgEntity.channelPaymentMode}">selected</c:if>>
                                                    ${order.value}
                                                </option>
                                            </fss:dictOrder>
                                        </select>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${channelOrgEntity.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${channelOrgEntity.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <input class = "tab-btn" type="button" onclick="sumList()" value="保存" />
                        </div>
                    </div>



                    <!-- NEW WIDGET START -->
                    <!-- 	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-33"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>渠道参数配置</h2>
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
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:1100px;">
                                            <col width="300" />
                                            <col width="300" />
                                            <col width="300" />
                                        <thead>
                                        <tr>
                                            <td>配置项</td>
                                            <td align="left">配置参数控制</td>
                                            <td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>渠道商户</td>
                                            <td>唯一</td>
                                            <td><a href="${contextPath}/channelDeploy/deploy/merchant/channelMerchantEdit/${channelOrgEntity.id}">配置</a></td>
                                        </tr>
                                       <tr>
                                           <td>支持银行</td>
                                           <td>多项，银行唯一</td>
                                           <td><a href="${contextPath}/channelDeploy/deploy/supportBank/bankEdit/${channelOrgEntity.id}">配置</a></td>
                                       </tr>
                                       <tr>
                                           <td>限额配置</td>
                                           <td>多项</td>
                                           <td>
                                               <a href="${contextPath}/channelDeploy/deploy/restrictions/restrictionsEdit/${channelOrgEntity.id}">配置</a>
                                           </td>
                                       </tr>
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
<%@include file="../../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
<script src="${contextPath}/js/jquery.blockUI.js"></script>
<script src="${contextPath}/js/util/lock.js"></script>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#custForm"));
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

   /*function sumList(){
       asynchronization();
   }*/

    //异步检查值是否存在
    function sumList (){
        bilocUtil("保存...");
        var ajax_type = "POST";
        var id = ${channelOrgEntity.id};
        var channelCondition = $("#channelCondition").val();
        var channelType = $("#channelType").val();
        var channelPaymentMode = $("#channelPaymentMode").val();

        var url = "${contextPath}/channelDeploy/deploy/Org/channelAjaxOrgEdit"; //表单提交目标
        $.ajax({
            type: ajax_type,
            url: url,
            data:{"id" : id, "channelCondition" : channelCondition, "channelType" : channelType, "channelPaymentMode" : channelPaymentMode}, //表单数据
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            async : false,
            success:successFnt,
            error:errorFnt
        });
        function successFnt(data){
            //setTimeout("$.unblockUI(),jAlert('保存成功', '信息提示')",2000);

            jAlert('保存成功', '信息提示')
        }
        function errorFnt() {
           // setTimeout("$.unblockUI(),jAlert('保存失败', '信息提示')",100);

            $.unblockUI();
            jAlert('保存失败', '信息提示')

        }
    }

</script>

<%@include file="../../../../view/include/foot.jsp"%>
</body>

</html>