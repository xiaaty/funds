<%--
  Created by IntelliJ IDEA.
  User: 于泳
  Date: 2014/12/9
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>内审系统--流水管理--第三方流水管理</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <%@include file="/WEB-INF/jsp/inc/common_css_js.inc" %>
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
<body><%@include file="/WEB-INF/jsp/inc/menu.inc" %>
<div id="main" role="main">

<!-- RIBBON -->
<div id="ribbon">
    <!-- breadcrumb -->
    <ol class="breadcrumb">
        <li>流水管理</li><li>第三方流水对账</li>
    </ol>
    <!-- end breadcrumb -->
</div>

<div id="content">

<section id="widget-grid" class="">
<div class="row">
<!-- NEW WIDGET START -->
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
<div class="jarviswidget" id="wid-id-420"  data-widget-deletebutton="false" data-widget-editbutton="false">
    <header>
        <h2>快速搜索</h2>
    </header>
    <!-- widget div-->
    <div>
        <form class="smart-form"  action="${contextPath}/water/waterThird" method="post" id="accountForm">
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
                        <tr >

                            <td class="lh32 tr">流水号：</td>
                            <td>
                                <label class="input" style="width:210px"  >
                                    <input type="text" name="map[serialNumber]" value="${query.map['serialNumber']}"/>
                                </label>
                            </td>
                            <td class="lh32 tr">批次号：</td>
                            <td>
                                <label class="input" style="width:210px">
                                    <input type="text"  name="map[systemToken]" value="${query.map['systemToken']}"/>
                                </label>
                            </td>
                            <td class="lh32 tr">交易渠道：</td>
                            <td>
                                <label class="select" style="width:210px">
                                    <select name="map[orgID]">
                                        <option value="0">全部</option>
                                        <c:forEach var="map" items="${thirdAccountMap}">
                                            <option value="${map.key}" ${query.map['orgID'] == map.key?"selected":""}>${map.value}</option>
                                        </c:forEach>
                                    </select>
                                </label>
                            </td>
                        </tr>
                        <tr >
                            <td class="lh32 tr">资金类型</td>
                            <td>
                                <label class="select" style="width:210px">
                                    <select name="map[bType]">
                                        <option value="0">请选择</option>
                                        <c:forEach var="map" items="${fundMap}">
                                            <option value="${map.key}" ${map.key == query.map['bType']?"selected":""}>${map.value}</option>
                                        </c:forEach>
                                    </select>
                                </label>
                            </td>
                            <td class="lh32 tr" colspan="4"></td>

                        </tr>
                        </tbody>
                    </table>
                </div>
                <footer>
                    <button class="btn btn-primary" type="submit" id="btn-query">查&nbsp;&nbsp;&nbsp;询</button>
                </footer>
            </div>
            <!-- end widget content -->
        </form>
    </div>


</div>
<div class="alert alert-info fade in" id="wid-id-423" style="display: none">
    <button class="close" data-dismiss="alert"> × </button>
    <p class="b">提醒:</p>
    <ul class="h30 lh30">
        <li class="fl pr30">收入金额累计：<span class="color06">16,544.66 </span>元</li>
        <li class="fl pr30">支出金额累计：<span class="color06">21,116,544.66</span> 元</li>
        <li class="fl pr30">未入账：<span class="color06">2</span>条</li>
        <li class="fl">已入账：<span class="color06">100</span>条</li>
    </ul>
</div>


            <div class="jarviswidget" id="wid-id-421" data-widget-deletebutton="false" data-widget-editbutton="false">
                <header>
                    <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                    <h2>第三方流水</h2>
                </header>
                <!-- widget div-->
                <div>
                    <form class="smart-form">
                        <!-- widget edit box -->
                        <div class="jarviswidget-editbox">
                            <!-- This area used as dropdown edit box -->
                        </div>
                        <!-- end widget edit box -->
                        <div class="widget-body-nobg-toolbar" style="overflow:hidden;">

                        </div>
                        <!-- widget content -->
                        <div class="widget-body">
                        <div class="mt10 mb10">
                        <table id="borrow-rep-table" class="table table-bordered table-striped tc lh32" style="min-width:1000px;max-width: 3000px;">

                        <thead>
                        <tr class="b">
                            <!--<td>对账请勾选</td>-->
                            <td>交易渠道</td>
                            <td>导入时间/创建时间</td>
                            <td>流水编号</td>
                            <td>批次号</td>
                            <td>交易时间</td>
                            <td>财务类型</td>
                            <td>收入金额</td>
                            <td>支出金额</td>
                            <td>已匹配金额</td>
                            <td>未匹配金额</td>
                            <td>入账状态</td>
                            <td>备注</td>
                            <!--<td>合同编号</td>
                            <td>产品期限</td>
                            <td>大区</td>
                            <td>对账说明</td>-->
                        </tr>
                        </thead>
                        <tbody class="f12">
                        <c:forEach var="record" items="${page.list}">
                        <tr>
                            <!--<td><input type="checkbox" checked="checked"></td>-->
                            <td>银联</td>
                            <td><fmt:formatDate value="${record.inputDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>${record.serialNumber}</td>
                            <td>${record.systemToken}</td>
                            <td><fmt:formatDate value="${record.trans}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>代收</td>
                            <td><fmt:formatNumber value="${record.income/100}" pattern="#,###,###,###,###,##0.00"/></td>
                            <td><fmt:formatNumber value="${record.payment/100}" pattern="#,###,###,###,###,##0.00"/></td>
                            <td>0.00</td>
                            <td>0.00</td>
                            <td>${stateMap[record.billStatus]}</td>
                            <td>${fundMap[record.businessType]}</td>
                            <!--<td>
                                <label class="input">
                                    <input type="text" />
                                </label>
                            </td>
                            <td>
                                <label class="input">
                                    <input type="text" />
                                </label>
                            </td>
                            <td>
                                <label class="input">
                                    <input type="text" />
                                </label>
                            </td>
                            <td>
                                <label class="input">
                                    <input type="text" />
                                </label>
                            </td> -->
                        </tr>
                        </c:forEach>

                        </tbody>
                        </table>
                        </div>
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

<!--look contract  -->
<div id="uplook" title="<div class='widget-header'><h4 class='b'>查看合同</h4></div>">
    <form>
        <fieldset>
            <!-- <input name="authenticity_token" type="hidden"> -->
            <div class="mt20 mb20 ofh pr">
                <table class="table table-bordered table-striped tc lh32">
                    <thead>
                    <tr class="b">
                        <td>操作</td>
                        <td>合同编号</td>
                        <td>产品期限</td>
                        <td>大区</td>
                        <td>对账说明</td>
                    </tr>
                    </thead>
                    <tbody class="f12">
                    <tr>
                        <td><a href="">删除</a></td>
                        <td>11111</td>
                        <td>12</td>
                        <td>北京</td>
                        <td>填写对账备注信息</td>
                    </tr>
                    <tr>
                        <td><a href="">删除</a></td>
                        <td>11111</td>
                        <td>12</td>
                        <td>北京</td>
                        <td>填写对账备注信息</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </fieldset>
    </form>
</div>
<!--add contract  -->
<div id="upload" title="<div class='widget-header'><h4 class='b fl pr20'>添加合同</h4><button class='btn btn-success table-nobg-btn fl add_ht'><i class='fa fa-plus mr5'></i>添加</button></div>">
    <form>
        <fieldset>
            <div class="mt20 mb20 ofh pr">
                <table class="table table-bordered table-striped tc lh32">
                    <thead>
                    <tr class="b">
                        <td>操作</td>
                        <td>合同编号</td>
                        <td>产品期限</td>
                        <td>大区</td>
                        <td>对账说明</td>
                    </tr>
                    </thead>
                    <tbody class="f12" id="ht">
                    <tr>
                        <td><a href="">删除</a></td>
                        <td>
                            <label class="input">
                                <input type="text" style="width:100px;" value="1111" />
                            </label>
                        </td>
                        <td>
                            <label class="input">
                                <input type="text" style="width:100px;" value="12" />
                            </label>
                        </td>
                        <td>
                            <label class="input">
                                <input type="text" style="width:100px;" value="北京" />
                            </label>
                        </td>
                        <td>
                            <label class="input">
                                <input type="text" style="width:170px;" value="填写对账备注信息" />
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td><a href="">删除</a></td>
                        <td>
                            <label class="input">
                                <input type="text" style="width:100px;" value="1111" />
                            </label>
                        </td>
                        <td>
                            <label class="input">
                                <input type="text" style="width:100px;" value="12" />
                            </label>
                        </td>
                        <td>
                            <label class="input">
                                <input type="text" style="width:100px;" value="北京" />
                            </label>
                        </td>
                        <td>
                            <label class="input">
                                <input type="text" style="width:170px;" value="填写对账备注信息" />
                            </label>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </fieldset>
    </form>
</div>
<%@include file="/WEB-INF/jsp/inc/common_footer_css_js.inc" %>

<script>
    $(document).ready(function() {
        pageSetUp();
        $('#third_tabs').tabs();

        DT_page("borrow-rep-table",true,'${page.JSON}',$("#accountForm"));

        /*
         * CONVERT DIALOG TITLE TO HTML
         * REF: http://stackoverflow.com/questions/14488774/using-html-in-a-dialogs-title-in-jquery-ui-1-10
         */

        var add_uplook = $("#uplook").dialog({
            autoOpen : false,
            width : 600,
            resizable : false,
            modal : true,
            buttons : [{
                html : "确&nbsp;&nbsp;&nbsp;&nbsp;认",
                "class" : "btn btn-default",
                click : function() {
                    $(this).dialog("close");

                }
            }, {

                html : "取&nbsp;&nbsp;&nbsp;&nbsp;消",
                "class" : "btn btn-default",
                click : function() {
                    $(this).dialog("close");
                }
            }]
        });


        $(".btn_uplook").click(function() {
            add_uplook.dialog("open");
            return false;
        });
        var add_contract = $("#upload").dialog({
            autoOpen : false,
            width : 600,
            resizable : false,
            modal : true,
            buttons : [{
                html : "确&nbsp;&nbsp;&nbsp;&nbsp;认",
                "class" : "btn btn-default",
                click : function() {
                    $(this).dialog("close");

                }
            }, {

                html : "取&nbsp;&nbsp;&nbsp;&nbsp;消",
                "class" : "btn btn-default",
                click : function() {
                    $(this).dialog("close");
                }
            }]
        });

        $("#btn-query").click(function() {
            $("#accountForm").submit();
           });
        $(".btn_add").click(function() {
            add_contract.dialog("open");
            return false;
        });

        $(".add_ht").on("click",function(){


            $("#ht").append('<tr><td><a href="">删除</a></td><td><label class="input"><input type="text" style="width:100px;" value="1111" /></label></td><td><label class="input"><input type="text" style="width:100px;" value="12" /></label></td><td><label class="input"><input type="text" style="width:100px;" value="北京" /></label></td><td><label class="input"><input type="text" style="width:170px;" value="填写对账备注信息" /></label></td> </tr>');
            return false;

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

    });
</script>

</body>
</html>
