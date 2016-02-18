<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统管理--系统配置表--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
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

    </style>

</head>

<body>
<%@include file="../../../../view/include/menu.jsp"%>
<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">
        <ol class="breadcrumb">
            <li>辅助功能列表</li>
            <li>系统配置</li>
        </ol>
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                      <div class="jarviswidget" id="wid-id-11"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <div>
                           <%--  ${contextPath}/sys/workassist/dictionary --%>
                                <form class="smart-form" action=""  method="post" id="settingForm">
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
                                                         <td class="tr">配置类型：</td>
                                                         <td>
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="type" value="${setting.type}" />
                                                            </label>
                                                        </td>
                                                          <td class="tr">配置主键：</td>
                                                         <td>
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="setKey" value="${setting.setKey}" />
                                                            </label>
                                                        </td>
                                                          <td class="tr">配置的值：</td>
                                                         <td>
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="setValue" value="${setting.setValue}" />
                                                            </label>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer>
                                        	<input type="hidden" value="${setting.setingType}" id="setingType"/>
                                        	<input type="hidden" value="${setting.mchnNo}" id="mchnNo"/>
                                          <button type="button" class="btn btn-primary"  id="btn_add" >添&nbsp;&nbsp;&nbsp;加</button>
                                          <button type="submit" class="btn btn-primary">查&nbsp;&nbsp;&nbsp;询</button> 
                                        </footer>
                                    </div>
                                    <!-- end widget content -->
    							</form>
                    		</div>
                		</div>
                
                    <!-- NEW WIDGET START -->
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-01"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>系统配置信息列表</h2>
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
                                    <table id="borrow-rep-table12" class="table table-bordered mt15" style="text-align:center;">
                                        <thead>
                                        <tr>
                                          
                                              <td>配置类型</td>
                                              <td>配置商户</td>
                                              <td>配置主键</td> 
                                              <td>配置的值</td>
                                              <td>操作</td> 
                                        </tr>
                                        </thead>
                                        <tbody>
                                             <c:forEach items="${page.list}" var="set">
                                                <tr>
                                                   
                                                    <td>${set.type}</td>
                                                    <td>${set.mchnNo}</td>
                                                     <td> ${set.setKey}</td>
                                                    <td> ${set.setValue}</td>
                                                    <td>
                                                   		<a href="${contextPath}/sys/workassist/settingToUpdate/${set.id}">修改</a>
                                                   		&nbsp;&nbsp;&nbsp;&nbsp;
                                                   		<a href="javascript:void(0)" onclick="deleteSet(${set.id})">删除</a>
                                                   		
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
    </div>
<%@include file="../../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
 <script type="text/javascript" charset="utf-8">
	 $(document).ready(function () {
	     pageSetUp();
	     DT_page("borrow-rep-table12", true, '${page.JSON}', $("#settingForm"));
	     
	 })
	     
     
	  //添加按钮按下
        $("#btn_add").button().click(function() {
        	var setingType=$("#setingType").val();
        	var mchnNo=$("#mchnNo").val();
        	window.open("${contextPath}/sys/workassist/settingAdd/"+mchnNo+"?setingType="+setingType,"_self");
        });
	    
	  
	   /**
	   	删除
	   **/
       function deleteSet(id){
		   if(confirm("您确认删除本条记录吗？")){
			   
  	            $.ajax({
  	            	url : "${contextPath}/sys/workassist/deleteSetting?id="+id,
  	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
  	                dataType: "json",
  	                success: function (data) {
  	                    if (data.code == '0000') {
  	                      jAlert("删除成功!", '确认信息');
  	                      //自动跳转
  	                      parent.location.reload();
  	                    } else {
  	                    	 jAlert("删除失败!", '确认信息');
  	                        return;
  	                    }
  	                }
  	            });
		   }
  	        
        	
        	
        }
	  
	    
</script>

<%@include file="../../../../view/include/foot.jsp"%>
</body>

</html>