<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <!-- <meta charset="UTF-8"> -->
    <title>资金清结算系统--回盘信息列表--冠群驰骋投资管理(北京)有限公司</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <%@include file= "../../../view/include/common_css_js.jsp"%>
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
            <li>系统配置</li>
            <li>回盘信息</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <div class="jarviswidget" id="backpaltList"  data-widget-deletebutton="false" data-widget-editbutton="false">
                    <header>
                        <h2>快速搜索</h2>
                    </header>
                    <!-- widget div-->
                    <div>
                        <form class="smart-form" id="Form" action="" method="post" >
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
                                        <col/>
                                        <tbody>
                                        <tr>
                                            <td class="tr" nowrap="nowrap">流水号:</td>
                                            <td nowrap="nowrap">
                                                <label class="input"  style="width:250px" >
                                                    <input type="text" name="seqNo" value="${fssBackplateEntity.seqNo}">
                                                </label>
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
            </div>
            <div id="content">
                <section id="widget-grid" class="">
                    <div class="row">
                        <!-- NEW WIDGET START -->
                        <div class="jarviswidget jarviswidget-color-darken" id="menu-id-256"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                                <h2>回盘信息列表</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                                <form class="smart-form" id="">
                                    <!-- widget edit box -->
                                    <div class="jarviswidget-editbox">
                                        <!-- This area used as dropdown edit box -->
                                    </div>
                                    <div class="widget-body">
                                        <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:900px;">
                                            <col width="100" />
                                            <col width="100" />
                                            <col width="100" />
                                            <col width="200" />
                                            <col width="200" />
                                            <col width="100" />
                                            <col width="100" />
                                            <thead>
                                                <tr>
                                                    <td>流水号</td>
                                                    <td>商户号</td>
                                                    <td>交易类型</td>
                                                    <td>创建时间</td>
                                                    <td>修改时间</td>
                                                    <td>回盘次数</td>
                                                    <td>回盘结果</td>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${page.list}" var="fssBackplateEntity">
                                                <tr>
                                                    <td>${fssBackplateEntity.seqNo}</td>
                                                    <td>${fssBackplateEntity.mchn}</td>
                                                    <td><fss:dictView key="${fssBackplateEntity.tradeType}"/></td>
                                                    <td><fss:fmtDate value="${fssBackplateEntity.createTime}"/></td>
                                                    <td><fss:fmtDate value="${fssBackplateEntity.modifyTime}"/></td>
                                                    <td>${fssBackplateEntity.repayCount}</td>
                                                    <td><fss:dictView key="${fssBackplateEntity.repayResult}"/></td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- end widget content -->
                                </form>
                            </div>
                        </div>
                    </div>

                </section>
            </div>
            <%@include file= "../../../view/include/common_footer_css_js.jsp"%>
    </div>


    <script type="text/javascript" charset="utf-8">
        $(document).ready(function() {
            pageSetUp();
            DT_page("borrow-rep-table12", true, '${page.JSON}', $("#Form"));
        });
    </script>

    <%@include file= "../../../view/include/foot.jsp"%>
</body>
</html>