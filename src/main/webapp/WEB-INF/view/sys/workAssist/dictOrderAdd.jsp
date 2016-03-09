<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统管理--添加字典类型--冠群驰骋投资管理(北京)有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
    
   <%@include file="../../include/common_css_js.jsp"%>
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
    </style>
</head>
<body>
    
<%@include file="../../include/menu.jsp"%>

    <div id="main" role="main">

        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
                <li>系统管理</li>
                <li>字典类型</li>
            </ol>
            <!-- end breadcrumb -->
        </div>

        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <form id="dictorderForm" action="${contextPath}/sys/workassist/dictOrderSave" method="post">
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">

                            <div class="jarviswidget" id="wid-id-711" data-widget-deletebutton="false" data-widget-editbutton="false">
                               <header>
                                    <h2><i class="fa fa-edit pr10"></i>新增字典类型<font class="pl10 f12 color07"></font></h2>
                                </header>
                                <div>
                                    <div class="smart-form">

                                        <!-- widget content -->
                                        <div class="widget-body no-padding">
                                            <div class="mt10 mb10 ml30">
                                                <table class="table">
                                                    <col width="112" />
                                                    <col width="367" />
                                                    <col width="112" />
                                                    <col />
                                                    <tbody>
                                                        <tr>
                                                            <td align="left">类型名称：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" id="orderName" name="orderName" value="${dictorder.orderName}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">唯一标识：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" id="orderDict" name="orderDict" value="${dictorder.orderDict}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                           <td align="left">字典列表：</td>
                                                           <td>
			                                                    <section style="width:210px">
			                                                    <label class="select">
													                <select id="role_list"  name ="role_list" multiple="true" style="width:200px;height:300px;">
													                    <!-- <option  value="">--请选择--</option> -->
													                	<c:forEach items="${dictlist}" var="dict">
													                    	<option value="${dict.dictId}"> ${dict.dictName} </option>
													                	</c:forEach>
													                </select>
													                <input type="button"  value="<<<<" onclick="moveOptions('role_list_to','role_list')"/>
																	<input type="button"  value=">>>>" onclick="moveOptions('role_list','role_list_to')"/>
													                <SELECT id="role_list_to" name ="role_list_to" multiple="true" style="width:200px;height:300px;">
																	     
																	</SELECT>
															                     
															     </label>
			                                                        
			                                                    </section>
			                                                </td>
			                                         		<input type="hidden" id="orderList" name="orderList" />
			                                         		
			                                                
                                                        </tr>
                                                        <tr>
                                                            <td align="left">备注：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" id="memo" name="memo" value="${dictorder.memo}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <div class="mb20" id="wid-id-713">
                                                    <button class="btn btn-default table-nobg-btn" type="button" id="dictorderadd">保存</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </article>
                    </form>

                </div>
            </section>
        </div>
    </div>

<%@include file="../../include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
    <script type="text/javascript" charset="utf-8">
        $(document).ready(function() {
    	    $("#dictorderadd").click(function () {
    	    	var t = document.getElementById("role_list_to"); 
    	    	var str="";
    	    	for(var i = 0 ;i<t.length;i++){
    	    		t[i].selected=true;
    	    		str+=t[i].value+",";
    	    	} 	
    	    	
    	    	if(str.length>0){
    	    		str = str.substr(0, str.length - 1);
    	    	}
    	    	var orderName=$("#orderName").val();
        		var orderDict=$("#orderDict").val();
        		var orderList=$("#orderList").val(str);
        		if(orderName == ""){
        			jAlert("类型名称不能为空!", '消息提示');
        			return;
        		}else if( orderDict==""){
        			jAlert("标识不能为空!", '消息提示');
        			return;
        		}else if(orderList==""){
        			jAlert("字典列表不能为空!", '消息提示');
        			return;
        		}
        		else{
		        		$("#dictorderForm").ajaxSubmit({
		 	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		 	                dataType: "json",
		 	                success: function (data) {
		 	                    if (data.code == '0000') {
		 	                        jAlert("保存成功!", '信息提示');
		 	                        //自动跳转
		 	                        parent.location.href="${contextPath}/sys/workassist/dictorder";
		 	                    } else {
		 	                    	jAlert("保存失败!", '消息提示');
		 	                        return;
		 	                    }
		 	                }
		 	            });
        		}
    	    });
        });
        
    
      //把一个select 中的项移到另一个select中
        function moveOptions(from,to){
            var oldname=$("#"+from+"  option:selected");
            if(oldname.length==0){
                return;
            }
            var valueOb = {};
            $("#" + to).find("option").each(function(){
                valueOb[String($(this).val())] = $(this);
            });
            
            for( var i =0;i< oldname.length; i++){
               if(valueOb[String($(oldname[i]).val())] == undefined){
                       $(oldname[i]).clone().appendTo($("#"+to))
                       $(oldname[i]).remove();
               }        
            }
            
        }
        
        
        
        
        
        
  </script>
        
        
        
        
        
        
</body>

</html>