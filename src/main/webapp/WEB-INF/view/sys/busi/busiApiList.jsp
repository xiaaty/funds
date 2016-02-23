<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <li>商户Api列表</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
               <div class="jarviswidget" id="wid-id-11"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                           
                                <form class="smart-form" id="busiApiForm" action="${contextPath}/fss/api/businessApiList" method="post" >
                              
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
                                                     <td class="tr" nowrap="nowrap">商户名称:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:210px" >
                                                                <input type="text" name="mchnName" value="${businessApiBean.mchnName}">
                                                            </label>
                                                        </td>
                                                     <td class="tr" nowrap="nowrap">商户号:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:210px" >
                                                                <input type="text" name="mchnNo" value="${businessApiBean.mchnNo}">
                                                            </label>
                                                        </td>
                                                     <td class="tr" nowrap="nowrap">api名称:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:210px" >
                                                                <input type="text" name="apiName" value="${businessApiBean.apiName}">
                                                            </label>
                                                        </td>
                                                     <td class="tr" nowrap="nowrap">是否回调:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="select"  style="width:150px" >
                                                                <select class="select02"  name="isReturn" id="isReturn">
								                                    <option value=""> --请选择--</option>
								                                    <option value="98010001" <c:if test="${businessApiBean.isReturn==98010001}">selected='selected' </c:if> >回调</option>
								                                    <option value="98010002" <c:if test="${businessApiBean.isReturn==98010001}">selected='selected' </c:if>>不回调</option>
								                                </select>
                                                            </label>
                                                        </td>
                                                    
                                                    </tr>
                                                   
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer>
                                            <!-- <button class="btn btn-default" onclick="window.history.back();" type="button">重&nbsp;&nbsp;&nbsp;置</button> -->
                                            <button class="btn btn-primary"  >查&nbsp;&nbsp;&nbsp;询</button>
                                        </footer>
                                    </div>
                                    <!-- end widget content -->
    							</form>
                    		</div>
                		</div>
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-30"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>商户API列表</h2>
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
                                    <table id="borrow-rep-table12" class="table table-bordered mt15" style="text-align:center;">
                                        <thead>
                                        <tr>
                                        	<th align="left"><input type="checkbox" id="checkAll"/></th>
                                            <th>商户名称</th>
                                            <th>商户号</th>
                                            <th>API名称</th>
                                            <th>API地址</th>
                                            <th>是否回调</th>
                                            <th>回调地址</th>
                                            <th>授权时间</th>
                                            <th>修改时间</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${page.list}" var="t">
                                                <tr>
                                                <td><input type="checkbox" class="checkBoxAll" value="${t.id}"/></td>
                                                    <td>${t.mchnName}</td>
                                                    <td>${t.mchnNo}</td>
                                                    <td>${t.apiName}</td>
                                                    <td>${t.apiUrl}</td>
                                                    <td>${t.isReturn=='98010001'?"回调":"不回调"}</td>
                                                    <td>${t.returnUrl}</td>
                                                    <td> <fmt:formatDate value="${t.createTime}" pattern="yyyy-MM--dd HH:mm:ss"/></td>
                                                    <td> <fmt:formatDate value="${t.modifyTime}" pattern="yyyy-MM--dd HH:mm:ss"/></td>
                                                    <td style="text-align:left;">
                                                        <a href="${contextPath}/fss/api/selectBusinessApi/${t.id}">修改</a>
                                                        <a href="javaScript:void(0)" onclick="del(${t.id})">删除</a>
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
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#busiApiForm"));
    });
    $('#checkAll').bind('click', function () {
        var that = this;
        $('.checkBoxAll').each(function () {
            this.checked = that.checked;
        });
    });
	function del(id){
		if(confirm("您确认删除本条数据吗？")){
			location.href="${contextPath}/fss/api/deleteBusinessApi/"+id;
		}else{
			return;
		}
	}
</script>

<%@include file= "../../../view/include/foot.jsp"%>
</body>

</html>