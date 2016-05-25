<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

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
                    <div class="jarviswidget jarviswidget-color-darken" id="busiLisy"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>商户列表</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="busiListForm">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                <div class="mb20" id="wid-id-713">
                                <c:if test="${parentId !=0}">
                                            <button class="btn btn-default table-nobg-btn" type="button" onclick="location.href='${contextPath}/sys/busi/list/${returnId}'" ><i class="fa fa-minus"></i>返回</button>
                                </c:if>     
                                            <button class="btn btn-default table-nobg-btn" type="button" onclick="location.href='${contextPath}/sys/busi/add/${parentId}'"><i class="fa fa-plus"></i>添加</button>
                                      </div>
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2300px;">
                                    	<col width="300" />
                                    	<col width="300" />
                                    	<col width="300" />
                                    	<col width="300" />
                                    	<col width="300" />
                                    	<col width="300" />
                                    	<col width="300" />
                                        <col width="200" />
                                        <thead>
                                        <tr>
                                            <td>商户名称</td>
                                            <td>商户号</td>
                                            <td>父商户号</td>
                                            <td>商户密钥</td>
                                            <td>状态</td>
                                            <td>创建时间</td>
                                            <td>修改时间</td>
                                            <td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${page.list}" var="t">
                                                <tr>
                                                    <td>${t.mchnName}</td>
                                                    <td>${t.mchnNo}</td>
                                                    <td>${t.parentNo}</td>
                                                    <td>${t.mchnKey}</td>
                                                    <td>${t.state=='98040002'?"未启用":"已启用"}</td>
                                                    <td> <fmt:formatDate value="${t.createTime}" pattern="yyyy-MM--dd HH:mm:ss"/></td>
                                                    <td> <fmt:formatDate value="${t.modifyTime}" pattern="yyyy-MM--dd HH:mm:ss"/></td>
                                                    <td style="text-align:left;">
                                                        <a href="${contextPath}/sys/busi/update/${t.mchnNo}?parentId=${t.parentId}">修改</a>
                                                        <a href="${contextPath}/sys/busi/list/${t.id}">查看</a>
                                                        <c:if test="${parentId !=0}">
                                                        <a href="${contextPath}/sys/busi/toBusinessApiAdd/${t.mchnNo}?mchnName=${t.mchnName}&parentId=${t.parentId}">API授权</a>
                                                   		</c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </form>
                        </div>
                    </div>
                </article>
            </div>

        </section>
    </div>
<%@include file="../../../view/include/common_footer_css_js.jsp"%>
</div>


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#busiListForm"));
    });

</script>

<%@include file= "../../../view/include/foot.jsp"%>
</body>

</html>