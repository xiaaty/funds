<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <!-- <meta charset="UTF-8"> -->
    <title>资金清结算系统--交易记录--冠群驰骋投资管理(北京)有限公司</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <%@include file="../../../view/include/common_css_js.jsp" %>
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

        .button-icon i {
            line-height: 32px;
        }

        #footer {
            position: absolute;
            bottom: 10px;
            z-index: 100px;
        }

        .footer-bottom {
            font-size: 13px
        }

        .footer-bottom ul > li {
            padding: 0
        }

        .footer-bottom ul > li + li:before {
            padding: 0 10px;
            color: #ccc;
            content: "|"
        }
    </style>

</head>

<body>
<%@include file="../../../view/include/menu.jsp" %>


<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>交易记录</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

                    <div class="jarviswidget" id="backpaltList" data-widget-deletebutton="false"
                         data-widget-editbutton="false">
                        <header>
                            <h2>快速搜索</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="Form" action="${contextPath}/trade/tradeInfo/list"
                                  method="post">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body no-padding">
                                    <div class="mt10 mb10">
                                        <table class="table lh32">
                                            <col width="100"/>
                                            <col width="220"/>
                                            <col width="100"/>
                                            <col width="220"/>
                                            <col width="100"/>
                                            <col/>
                                            <tbody>
                                            <tr>
                                                <td class="tr" nowrap="nowrap">原交易流水号:</td>
                                                <td nowrap="nowrap">
                                                    <label class="input" style="width:250px">
                                                        <input type="text" name="orglSeqNo" value="${map.orglSeqNo}">
                                                    </label>
                                                </td>
                                                <td class="tr" nowrap="nowrap">流水号:</td>
                                                <td nowrap="nowrap">
                                                    <label class="input" style="width:250px">
                                                        <input type="text" name="seqNo" value="${map.seqNo}">
                                                    </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="tr" nowrap="nowrap">充值码:</td>
                                                <td nowrap="nowrap">
                                                    <label class="input" style="width:250px">
                                                        <input type="text" name="chgCd" value="${map.chgCd}">
                                                    </label>
                                                </td>
                                                <td class="tr" nowrap="nowrap">交易状态:</td>
                                                <td nowrap="nowrap">
                                                    <label class="input" style="width:250px">
                                                        <select class="select01" style="width:250px;" name="tradeSts"
                                                                id="tradeSts">
                                                            <option <c:if
                                                                    test="${map.tradeSts == ''}"> selected="selected" </c:if>
                                                                    value="">全选
                                                            </option>
                                                            <option <c:if
                                                                    test="${map.tradeSts == '已自动入账'}"> selected="selected" </c:if>
                                                                    value="已自动入账">已自动入账
                                                            </option>
                                                            <option <c:if
                                                                    test="${map.tradeSts == '未入账'}"> selected="selected" </c:if>
                                                                    value="未入账">未入账
                                                            </option>
                                                            <option <c:if
                                                                    test="${map.tradeSts == '已手动退款'}"> selected="selected" </c:if>
                                                                    value="已手动退款">已手动退款
                                                            </option>
                                                            <option <c:if
                                                                    test="${map.tradeSts == '待手工处理'}"> selected="selected" </c:if>
                                                                    value="待手工处理">待手工处理
                                                            </option>
                                                        </select>
                                                    </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="tr" nowrap="nowrap">入账户名:</td>
                                                <td nowrap="nowrap">
                                                    <label class="input" style="width:250px">
                                                        <input type="text" name="toAccNm" value="${map.toAccNm}">
                                                    </label>
                                                </td>
                                                <td class="tr" nowrap="nowrap">入账卡号:</td>
                                                <td nowrap="nowrap">
                                                    <label class="input" style="width:250px">
                                                        <input type="text" name="toAccNo" value="${map.toAccNo}">
                                                    </label>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <footer>
                                        <button class="btn btn-primary" onclick="javascript:void(0);">查&nbsp;&nbsp;&nbsp;询</button>
                                        <input type="button" id="manual" class="btn btn-primary" onclick="javascript:void(0);" value="手动抓取" />
                                    </footer>
                                </div>
                                <!-- end widget content -->
                            </form>
                        </div>
                    </div>

                    <!-- NEW WIDGET START -->
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-256"
                         data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>交易记录</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>

                                <div class="widget-body-nobg-toolbar" style="overflow:hidden;">
                                    <button type="button" class="btn btn-default fl table-nobg-btn" id="tradeImport"><i
                                            class="fa fa-plus"></i>&nbsp;交易记录导入
                                    </button>
                                    &nbsp;
                                </div>

                                <div class="widget-body">
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15"
                                           style="min-width:1450px;">
                                        <col width="50"/>
                                        <col width="100"/>
                                        <col width="100"/>
                                        <col width="100"/>
                                        <col width="100"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="100"/>
                                        <col width="100"/>
                                        <col width="100"/>
                                        <col width="100"/>
                                        <col width="100"/>
                                        <col width="100"/>
                                        <thead>
                                        <tr>
                                            <td>序号</td>
                                            <td>数据来源</td>
                                            <td>系统代码</td>
                                            <td>原交易流水号</td>
                                            <td>充值码</td>
                                            <td>入账时间</td>
                                            <td>交易时间</td>
                                            <td>入账户名</td>
                                            <td>入账卡号</td>
                                            <td>金额(元)</td>
                                            <td>交易状态</td>
                                            <td>卡号校验</td>
                                            <td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t" varStatus="vs">
                                            <tr class="">
                                                <td>${vs.index+1}</td>
                                                <td>${t.dataSource}</td>
                                                <td>${t.sysCode}</td>
                                                <td>${t.orglSeqNo}</td>
                                                <td>${t.chgCd}</td>
                                                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
                                                                    value="${t.toAccTime}"/></td>
                                                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
                                                                    value="${t.tradeTime}"/></td>
                                                <td>${t.toAccNm}</td>
                                                <td>${t.toAccNo}</td>
                                                <td>${t.amount}</td>
                                                <td>${t.tradeSts}</td>
                                                <td>${t.cardVerify}</td>
                                                <td><a href="#">操作</a></td>
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
    <%@include file="../../../view/include/common_footer_css_js.jsp" %>
    <script src="${contextPath}/js/jquery.form.js"></script>
    <script src="${contextPath}/js/jquery.alerts.js"></script>
    <script src="${contextPath}/js/jquery.blockUI.js"></script>
</div>
<div class="pop" id="inputExcel"
     style="display:none;position: absolute;z-index:9999;left:50%;top:50%;margin-left:-200px;margin-top:-200px;width: 400px;padding: 30px;border:solid 2px #008299;border-radius:2px;background: white;">
    <form id="uploadForm" method="post" enctype="multipart/form-data">
        <h1 class="f18">导入excel</h1>
        <p class="mt30 mb10">数据格式请参考导入模版</p>

        <div class="mb25 pr">
            <input type="text" id="file_pa" value="" class="input01 file_pa" style="width:280px; height:30px;"/>
            <a class="icon btn09 file_icon" href="#" title="文件夹"><span>文件夹</span></a>
            <input type="file" name="infoFile" class="input01 file_fi" style="width:400px;height: 38px;"
                   onchange="document.getElementById('file_pa').value=this.value"/>
        </div>
        <div class="mb20" id="wid-id-713">
            <button class="btn btn-primary " id="import" type="button" title="导入">导&nbsp;入</button>
            &nbsp;&nbsp;
            <button class="btn btn-default fl mr30" id="mr30" type="button" title="取消">取&nbsp;消</button>
        </div>
    </form>
</div>

<div class="pop" id="getFtp"
     style="display:none;position: absolute;z-index:9999;left:50%;top:50%;margin-left:-200px;margin-top:-200px;width: 400px;height:250px;padding: 30px;border:solid 2px #008299;border-radius:2px;background: white;">
    <form id="getFtpForms" method="post" enctype="multipart/form-data">
        <h1 class="f18">手动导入线下回盘文件</h1>
        <p class="mt30 mb10">请选择日期</p>
        <div class="mb25 pr">
            <i class="icon-append fa fa-calendar" style="width:40px;height: 38px;"></i>
            <input type="text" maxlength="10" id="createTime" name="createTime" class="selectdate input01 file_pa"  style="width:250px;height: 38px;" placeholder="请选择时间" value="${map.createFileDate}" title="请选择手动抓取时间">
        </div>
        <br>
        <div class="mb20" id="wid-id-714">
            <button class="btn btn-primary" id="subFtp" type="button" title="导入">抓&nbsp;取</button>
            &nbsp;&nbsp;
            <button class="btn btn-default fl mr30" id="mrFtp" type="button" title="取消">取&nbsp;消</button>
        </div>
    </form>
</div>

<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#Form"));
    });

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

    $(function () {
        $('#tradeImport').click(function () {
            $('#inputExcel').show();
        });

        $(".mr30").click(function () {
            $('#inputExcel').hide();
        });

        $("#import").click(function () {
            var file_pa = $("#file_pa").val();
            if (file_pa == "") {
                alert("请选择导入的文件！");
                return;
            }
            var url = "${contextPath}/trade/tradeInfo/loadExcel";
            $("#uploadForm").attr("action", url);
            $("#uploadForm").submit();
        })

    });

    $(function () {
        $('#manual').click(function () {
            $('#getFtp').show();
        });

        $("#mrFtp").click(function () {
            $('#getFtp').hide();
        });

        $("#subFtp").click(function () {
            var createTime = $("#createTime").val();
            if (createTime == "") {
                alert("请选择日期！");
                return;
            }
            var url = "${contextPath}/trade/tradeInfo/getFtp";
            $("#getFtpForms").attr("action", url);
            $("#getFtpForms").submit();
        })

    });

</script>

<%@include file="../../../view/include/foot.jsp" %>
</body>
</html>