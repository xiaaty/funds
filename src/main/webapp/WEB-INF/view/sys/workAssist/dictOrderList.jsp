<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统管理--字典类型--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
    
   <%@include file="../../include/common_css_js.jsp"%>
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

<body>
<%@include file="../../include/menu.jsp"%>
<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">
        <ol class="breadcrumb">
            <li>系统管理</li>
            <li>数据字典</li>
        </ol>
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                      <div class="jarviswidget" id="dictList-id-01"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <div>
                           <%--  ${contextPath}/sys/workassist/dictionary --%>
                                <form class="smart-form" action=""  method="post" id="Form">
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
                                                        <td class="tr">标识：</td>
                                                        <td>
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="orderDict" value="${dictorder.orderDict}" />
                                                            </label>
                                                        </td>
                                                        <td class="tr">类型名称：</td>
                                                         <td>
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="orderName" value="${dictorder.orderName}" />
                                                            </label>
                                                        </td> 
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer>
                                          <button type="submit" class="btn btn-primary">查&nbsp;&nbsp;&nbsp;询</button>
                                        </footer>
                                    </div>
                                    <!-- end widget content -->
    							</form>
                    		</div>
                		</div>
                
                    <!-- NEW WIDGET START -->
                    <div class="jarviswidget jarviswidget-color-darken" id="dictList-id-02"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>字典类型信息</h2>
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
                                    <div class="widget-body-nobg-toolbar" style="overflow:hidden;">
                                        <button type="button" class="btn btn-default fl table-nobg-btn" id="btn_add"><i class="fa fa-plus"></i>&nbsp;添加</button>
                                    </div>
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2300px;">
                                    	<col width="200" />
                                    	<col width="300" />
                                    	<col width="300" />
                                    	<col width="300" />
                                    	<col width="1000" />
                                    	<col width="200" />
                                        <thead>
                                        <tr>	
                                        	  <td>序号</td>
                                        	  <td>类型名称</td>
                                              <td>标识</td>
                                              <td>字典列表</td>
                                              <td>备注</td>
                                              <td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                             <c:forEach items="${page.list}" var="dictorder">
                                                <tr>
                                                   <td>	${dictorder.id}</td> 
                                                   <td>	${dictorder.orderName}</td> 
                                                   <td> ${dictorder.orderDict}</td>
                                                   <td>
														<fss:dictOrder var="order" dictOrder="${dictorder.orderDict}">
                                                              ${order.value}
                                                        </fss:dictOrder>
													</td>
                                                   <td>	${dictorder.memo}</td> 
                                                   <td>	<a href="${contextPath}/sys/workassist/dictorderToUpdate/${dictorder.id}">修改</a></td>
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
<%@include file="../../include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
 <script type="text/javascript" charset="utf-8">
	 $(document).ready(function () {
	     pageSetUp();
	     DT_page("borrow-rep-table12", true, '${page.JSON}', $("#Form"));
	 }); 
	 
	 //添加
     $("#btn_add").button().click(function() {
     	window.open("${contextPath}/sys/workassist/dictOrderAdd","_self");
     });
	 
	 
	 
	 
	 
	 
</script>

<%@include file="../../include/foot.jsp"%>
</body>

</html>