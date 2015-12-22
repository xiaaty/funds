<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <#include "../../include/common_css_js.ftl">
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
<#include "../../include/menu.ftl">


<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>商户管理</li>
            <li>商户列表</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-30"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>商户列表</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                    <table id="borrow-rep-table12" class="table table-bordered mt15" style="text-align:center;">
                                        <col width="200" />
                                        <col />
                                        <thead>
                                        <tr>
                                            <td>商户名称</td>
                                            <td>商户标识</td>
                                            <td>IP校验方式</td>
                                            <td>API校验方式</td>
                                            <td>创建时间</td>
                                            <td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <#list page.list as t>
                                                <tr>
                                                    <td>${t.busiName}</td>
                                                    <td>${t.busiCode}</td>
                                                    <td>
                                                    	<#if t.authIpType == '0'>
                                                    		IP不校验
                                                    	<#elseif t.authIpType == '1'>
                                                    		IP段校验
                                                    	</#if>
                                                    </td>
                                                    <td>
                                                    	<#if t.authApiType == '0'>
                                                    		API不校验
                                                    	<#elseif t.authApiType == '1'>
                                                    		API校验
                                                    	</#if>
                                                    </td>
                                                    <td>${(t.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                                                    <td>
                                                        <a href="${contextPath}/sys/busi/update/${t.busiCode}">修改</a>
                                                        <#if t.authIpType != '0'>
                                                        	<a href="${contextPath}/sys/busi/ipupdate/${t.busiCode}">IP地址管理</a>
                                                        </#if>
                                                        <#if t.authApiType != '0'>
                                                        	<a href="${contextPath}/sys/busi/apiupdate/${t.busiCode}">API地址管理</a>
                                                        </#if>
                                                    </td>
                                                </tr>
                                            </#list>


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
<#include "../../include/common_footer_css_js.ftl">
</div>


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#logListForm"));
    });





</script>

<#include "../../include/foot.ftl">
</body>

</html>