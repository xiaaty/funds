<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统管理--配置管理--交易渠道配置--限额配置</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">

    <%@include file= "../../../../../view/include/common_css_js.jsp"%>
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
        #footer{position: absolute;bottom: 10px;z-index: 100;}
        .footer-bottom{font-size:13px}
        .footer-bottom ul>li{padding:0}
        .footer-bottom ul>li+li:before{padding:0 10px;color:#ccc;content:"|"}

        .btn-my{
            margin-top:10px;
            width:80px;
            height:45px;
            font-size:23px;
          /*  color:#000000;
            background-color: #FFFFFF;*/
        }
        .btn-my1{
             float:right;
             width:75px;
             height:25px;
             margin-right: 10px;
         }
    </style>

</head>

<body>
<%@include file= "../../../../../view/include/menu.jsp"%>


<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>系统管理</li>
            <li>配置管理</li>
            <li>交易渠道配置</li>
            <li>限额配置</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
    <div id="content">
        <section id="widget-grid" class="">
          <div id="content">
                <section id="widget-grid" class="">
                    <div class="row">
                        <!-- NEW WIDGET START -->
                        <!--    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
                        <div class="jarviswidget jarviswidget-color-darken" id="menu-id-01"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                                <h2>
                                    渠道支持银行限额配置（支持银行可以多个，每种类型，银行 唯一）
                                </h2>
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
                                        <input class="btn-my" type="button" value="添 加" style="margin-top : 10px" onclick = "addRwo();"/>
                                        <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:1100px;">
                                            <col width="150" />
                                            <col width="150" />
                                            <col width="150" />
                                            <col width="150" />
                                            <col width="150" />
                                            <thead>
                                            <tr>
                                                <td>序号</td>
                                                <td>配置项</td>
                                                <td>银行</td>
                                                <td>限额类型</td>
                                                <td>值</td>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${page.list}" var="t" varStatus = "status">
                                                    <tr>
                                                        <td>
                                                            ${status.index + 1}
                                                        </td>
                                                        <td>
                                                            <fss:dictView key="${t.optionName}" />
                                                        </td>
                                                        <td>
                                                            <fss:dictView key="${t.optionBank}" />
                                                        </td>
                                                        <td>
                                                            <fss:dictView key="${t.optionType}" />
                                                        </td>
                                                        <td>
                                                            ${t.optionValue}
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <input type="button" class="btn-my1" value="取消" style="display:none" onclick="abolish()"/>
                                        <input type="button" class="btn-my1" value="保存" style="display:none" onclick="saveOption()"/>
                                    </div>
                                    <!-- end widget content -->
                                </form>
                                <form action="${contextPath}/channelDeploy/deploy/restrictions/addChannelRestrictions" method="post" id="sumRestrictions" >
                                    <input type="hidden"  name="orgId" value = "${page.list.get(0).orgId}" />
                                    <input type="hidden" name="optionName" id="nameSubm" />
                                    <input type="hidden" name="optionBank" id="bankSubm" />
                                    <input type="hidden" name="optionType" id="typeSubm" />
                                    <input type="hidden" name="optionValue" id="valueSubm" />
                                </form>
                            </div>
                        </div>
                        <!--  </article> -->
                    </div>

                </section>
            </div>
            <%@include file= "../../../../../view/include/common_footer_css_js.jsp"%>
            <script src="${contextPath}/js/jquery.form.js" ></script>
            <script src="${contextPath}/js/jquery.alerts.js" ></script>
            <script src="${contextPath}/js/jquery.blockUI.js"></script>
            <script src="${contextPath}/js/util/lock.js"></script>
    </div>


    <script type="text/javascript" charset="utf-8">
        $(document).ready(function() {
            pageSetUp();
           // DT_page("borrow-rep-table12", true, '${page.JSON}', $("#cardListForm"));
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

        //添加
        function addRwo(){
            var oTab = $("#borrow-rep-table12");
            // $('table > tbody').remove();
            if($("#optionName").get(0) == undefined){
                $('#borrow-rep-table12 > tbody').append('<tr>' +
                        '<td>' +
                        '</td>' +
                        '<td>' +
                            '<select class="select02" style="width:202px;" id="optionName" >' +
                                '<fss:dictOrder var="order" dictOrder="DictRestrictionsName">' +
                                    '<option value="${order.key}"  <c:if test="${order.key==map.status}">selected</c:if> >${order.value}</option>' +
                                '</fss:dictOrder>' +
                            '</select>' +
                        '</td>' +

                        '<td>' +
                            '<select class="select02" style="width:202px;" id="optionBank" >' +
                                '<fss:dictOrder var="order" dictOrder="standardBank">' +
                                    '<option value="${order.key}"  <c:if test="${order.key==map.status}">selected</c:if> >${order.value}</option>' +
                                '</fss:dictOrder>' +
                            '</select>' +
                        '</td>' +

                        '<td>' +
                            '<select class="select02" style="width:202px;" id="optionType" >' +
                                '<fss:dictOrder var="order" dictOrder="DictResterctionsType">' +
                                  '<option value="${order.key}"  <c:if test="${order.key==map.status}">selected</c:if> >${order.value}</option>' +
                                '</fss:dictOrder>' +
                            '</select>' +
                        '<td>' +
                        '<input id="optionValue" type="text"  />' +
                        '</td>' +
                        '</tr>');

                $("input[type=button]").css('display','block');

                $("#optionName").blur(function(){

                    var optionName = $("#optionName").val();
                    var oName = optionName != null ? optionName.replace(" ", "") : "";
                    if(oName!="" ){
                        asynchronization(oName,null);
                    }else{
                        $.unblockUI();
                        jAlert("配置项,不能为空!", '信息提示');
                        return;
                    }
                });
                /*$("#optionValue").blur(function(){
                    var optionValue = $("#optionValue").val();
                    asynchronization(null,optionValue);
                });*/
            }
        }

        //异步检查值是否存在
        function asynchronization (optionName,optionValue,isSave){
            var ajax_type = "POST";
            // var oName = optionName != null ? optionName.replace(/(^\s*)|(\s*$)/g, "") : null;
            // var oValue = optionValue != null ? optionValue.replace(/(^\s*)|(\s*$)/g, "") : null;
            var optionBank = $("#optionBank").val();
            var optionType = $("#optionType").val();

            var orgId = ${page.list.get(0).orgId};
            var oName = optionName != null ? optionName.replace(" ", "") : null;
            var oValue = optionValue != null ? optionValue.replace(" ", "") : null;
            var oBank = optionBank != null ? optionBank.replace(" ", "") : null;
            var oType = optionType != null ? optionType.replace(" ", "") : null;

            var url = "${contextPath}/channelDeploy/deploy/restrictions/channelAjaxRestrictionsEdit"; //表单提交目标
            $.ajax({
                type: ajax_type,
                url: url,
                data:{"optionName" : oName, "optionValue" : oValue, "orgId" : orgId, "optionType" : oType, "optionBank" :oBank}, //表单数据
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                dataType: "json",
                success:successFnt,
                error:errorFnt
            });
            function successFnt(data){
                if(data.message == "配置项已存在!"){
                    if(isSave){
                        $.unblockUI();
                    }
                    jAlert(data.message, '信息提示');
                    return;
                }else{
                    if(isSave){
                        //保存
                        //location.href ="${contextPath}/channelDeploy/deploy/merchant/addChannelMerchant/${page.list.get(0).orgId}";
                        $("#nameSubm").val(data.optionName);
                        $("#bankSubm").val(data.optionBank);
                        $("#typeSubm").val(data.optionType);
                        $("#valueSubm").val(data.optionValue);
                        $("#sumRestrictions").submit();
                        //setTimeout("$.unblockUI(),jAlert('保存成功', '信息提示')",100);
                        $.unblockUI();
                        jAlert('保存成功', '信息提示');
                    }else{
                    }
                };
            }
            function errorFnt() {
                setTimeout("$.unblockUI(),jAlert('保存失败', '信息提示')",100);
                $.unblockUI();
                jAlert('保存失败', '信息提示');
            }
            //return false; //阻止表单的默认提交事件

        }

        //取消
        function abolish(){
            location.href ="${contextPath}/channelDeploy/deploy/restrictions/restrictionsEdit/${page.list.get(0).orgId}";
        }

        //保存
        function saveOption(){
            bilocUtil("保存...");
            var optionName = $("#optionName").val();
            var optionValue = $("#optionValue").val();

//          var oName = optionName != null ? optionName.replace(/(^\s*)|(\s*$)/g, "") : "";
//          var oValue = optionValue != null ? optionValue.replace(/(^\s*)|(\s*$)/g, "") : "";
            var oName = optionName != null ? optionName.replace(" ", "") : "";
            var oValue = optionValue != null ? optionValue.replace(" ", "") : "";

            if(oName!="" && oValue != ""){
                asynchronization(oName,oValue,true);
            }else{
                $.unblockUI();
                jAlert("配置项/值,不能为空!", '信息提示');
                return;
            }
        }


    </script>

    <%@include file= "../../../../../view/include/foot.jsp"%>
</body>
</html>
