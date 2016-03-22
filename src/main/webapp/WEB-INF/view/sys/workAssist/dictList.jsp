<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统管理--字典表--冠群驰骋投资管理(北京)有限公司</title>

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
                                <form class="smart-form" action=""  method="post" id="dictForm">
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
                                                        <td class="tr">编号：</td>
                                                        <td>
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="dictId" value="${dictmain.dictId}" />
                                                            </label>
                                                        </td>
                                                         <td class="tr">名称：</td>
                                                         <td>
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="dictName" value="${dictmain.dictName}" />
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
                            <h2>数据字典信息</h2>
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
                                        <c:if test="${parent_id !=0}">
                                            <button type="button" class="btn btn-default fl table-nobg-btn" id="btn_return"><i class="fa fa-plus"></i>&nbsp;返回</button>
                                        </c:if>
                                        <button type="button" class="btn btn-default fl table-nobg-btn" id="btn_add"><i class="fa fa-plus"></i>&nbsp;添加</button>
                                        <input type="hidden" id="parentId" value="${dictmain.parentId}" />
                                        <%-- <button type="button" class="btn btn-default fl table-nobg-btn" id="btn_detail"><i class="fa fa-list-ul"></i>&nbsp;详情</button>--%>
                                    </div>
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2300px;">
                                    	<col width="200" />
                                    	<col width="300" />
                                    	<col width="400" />
                                    	<col width="300" />
                                    	<col width="400" />
                                    	<col width="400" />
                                    	<col width="300" />
                                        <thead>
                                        <tr>
                                              <td>编号</td>
                                              <td>名称</td>
                                              <td>上级目录</td>
                                              <td>是否有效</td>
                                              <td>创建日期</td>
                                              <td>修改日期</td>
                                              <td>操作</td> 
                                        </tr>
                                        </thead>
                                        <tbody>
                                             <c:forEach items="${page.list}" var="dict">
                                                <tr>
                                                    <td>${dict.dictId}</td>
                                                    <td>${dict.dictName}</td>
                                                    <td><c:choose >
                                                        <c:when test="${dict.parentId == 0}">
                                                            无
                                                        </c:when>
                                                        <c:otherwise>
                                                            ${dict.parentId}
                                                        </c:otherwise>
                                                        </c:choose></td>
                                                    <td> <fss:dictView key="${dict.isValid}" /></td>
                                                    <td><fss:fmtDate value="${dict.createTime}"/></td>
                                                    <td><fss:fmtDate value="${dict.modifyTime}"/></td>
                                                   <td>


                                                       <a href="${contextPath}/sys/workassist/dictToUpdate/${dict.dictId}">修改</a>

                                                       &nbsp;&nbsp;&nbsp;
                                                       <c:if test="${dict.isEnd == 98010002 }">
                                                           <a href="${contextPath}/sys/workassist/dictionary/${dict.dictId}">查看</a>
                                                       </c:if>


                                                   		<%--&nbsp;&nbsp;&nbsp;
                                                   		<a href="javascript:void(0)" onclick="deleteDict(${dict.dictId},${dict.parentId})">删除</a>
                                                   		--%>
                                                   	</td> 
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
	     DT_page("borrow-rep-table12", true, '${page.JSON}', $("#dictForm"));
	     
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
	     
	     dateCheck();
	     
	     
         $('.selectdate_time').datetimepicker({
             language: 'zh-CN',
             weekStart: 1,
             autoclose: 1,
             format: 'hh:m:00',
             todayHighlight: 1,
             startView: 1,
             minView: 1,
             forceParse: 0
         });
	 }); 
 
	 
		//日期的合法性check
	    function dateCheck() {
	    	var $selectdate = $(".selectdate");
	    	$selectdate.each(function() {
	    		//$(this).off();
	        	$(this).focus(function() {
	        		//
	        		this.select();
	        	})
	        	.blur(function() {
		        	if($(this).val() != "") {
			        	var val = $(this).val();
			        	if (val.indexOf("\-") > 0 ) {
			        	} else {
			        		if (val.length == 8) {
			        			val = val.substr(0,4) + "-" + val.substr(4,2) + "-" + val.substr(6,2);
			        			$(this).val(val);
			        		}
			        	}
			        	var msg= isDate($(this).val());
			        	if (msg != "") {
				        	alert(msg);
				        	this.focus();
			        	}

	        		}
	        	});
	    	});
	    }
	 
	    function isDate(strDate){
	    	var strSeparator = "-"; //日期分隔符 
	    	var strDateArray; 
	    	var intYear; 
	    	var intMonth; 
	    	var intDay; 
	    	var boolLeapYear; 
	    	var ErrorMsg = ""; //出错信息 
	    	strDateArray = strDate.split(strSeparator); 
	    	//没有判断长度,其实2008-8-8也是合理的//strDate.length != 10 || 
	    	if(strDateArray.length != 3) { 
	        	ErrorMsg += "日期格式必须为: 年-月-日"; 
	        	return ErrorMsg; 
	    	} 
	    	intYear = parseInt(strDateArray[0],10); 
	    	intMonth = parseInt(strDateArray[1],10); 
	    	intDay = parseInt(strDateArray[2],10); 
	    	if(isNaN(intYear)||isNaN(intMonth)||isNaN(intDay)) { 
	    		ErrorMsg += "请输入有效的日期！"; 
	        	return ErrorMsg; 
	    	} 
	    	if(intMonth>12 || intMonth<1) { 
	    		ErrorMsg += "请输入有效的日期！"; 
	        	return ErrorMsg; 
	    	} 
	    	if((intMonth==1||intMonth==3||intMonth==5||intMonth==7 
	    		||intMonth==8||intMonth==10||intMonth==12) &&(intDay>31||intDay<1)) { 
	    		ErrorMsg += "请输入有效的日期！"; 
	        	return ErrorMsg; 
	    	} 
	    	if((intMonth==4||intMonth==6||intMonth==9||intMonth==11) 
	    		&&(intDay>30||intDay<1)) { 
	    		ErrorMsg += "请输入有效的日期！";  
	        	return ErrorMsg; 
	    	} 
	    	if(intMonth==2){ 
	        	if(intDay < 1) { 
	        		ErrorMsg += "请输入有效的日期！";  
		        	return ErrorMsg; 
	    		} 
	        	boolLeapYear = false; 
	        	if((intYear%100) == 0){ 
		        	if((intYear%400) == 0) 
		        	boolLeapYear = true; 
	    		} else { 
		        	if((intYear % 4) == 0) 
		        		boolLeapYear = true; 
	        		} 
	        		if(boolLeapYear){ 
		        		if(intDay > 29) { 
		        			ErrorMsg += "请输入有效的日期！"; 
			        		return ErrorMsg; 
	        			} 
	    			} else { 
			        	if(intDay > 28) { 
				        	ErrorMsg += "请输入有效的日期！"; 
				        	return ErrorMsg; 
		        		} 
	    			} 
	    		} 
	    	return ErrorMsg; 
	    } 
	    
	  //添加按钮按下
        $("#btn_add").button().click(function() {
        	window.open("${contextPath}/sys/workassist/dictAdd/${parent_id}","_self");
        });
     //添加按钮按下
     $("#btn_return").button().click(function() {
         window.open("${contextPath}/sys/workassist/dictionary/${returnId}","_self");
     });
	    
	  
	   /**
	   	删除
	   **/
       function deleteDict(dictId,parent_id){
  	            $.ajax({
  	            	url : "${contextPath}/sys/workassist/deletedeict?dictId="+dictId,
  	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
  	                dataType: "json",
  	                success: function (data) {
  	                    if (data.code == '0000') {
  	                      jAlert("删除成功!", '确认信息');
  	                      //自动跳转
  	                      parent.location.href="${contextPath}/sys/workassist/dictionary/${parent_id}";
  	                    } else {
  	                        return;
  	                    }
  	                }
  	            });
  	        
        	
        	
        }
	  
	    
</script>

<%@include file="../../include/foot.jsp"%>
</body>

</html>