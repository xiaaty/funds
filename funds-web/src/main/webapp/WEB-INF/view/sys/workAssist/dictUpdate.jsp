<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统管理--修改字典信息--冠群驰骋投资管理(北京)有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                <li>字典表</li>
            </ol>
            <!-- end breadcrumb -->
        </div>

        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <form id="updateDictForm" action="${contextPath}/sys/dictionary/${dictId}/update/save" method="post">
                       <input type="hidden" value="${dict.careateUserId}" name="careateUserId"  default="0"/> 
                       <input type="hidden" value="${dict.createTime}" name="createTime"  default="0"/> 
                       <input type="hidden" value="${dict.modifyUserId}" name="careateUserId"  default="0"/> 
                       <input type="hidden" value="${dict.modifyTime}" name="createTime"  default="0"/> 
                       <input type="hidden" value="${dict.parentId}" name="parentId"  id="parentId" default="0"/> 
                       <input type="hidden" value="${dict.sort}" name="sort"  default="0"/> 
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">

                            <div class="jarviswidget" id="dictUpdate" data-widget-deletebutton="false" data-widget-editbutton="false">
                               <header>
                                    <h2><i class="fa fa-edit pr10"></i>修改字典信息<font class="pl10 f12 color07"></font></h2>
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
                                                            <td align="left">编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" name="dictId" value="${dict.dictId}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" name="dictName" value="${dict.dictName}" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">是否有效：</td>
                                                            <td>
                                                                <label class="select">
                                                                    <select style="width:256px;" name="isValid" value="${dict.isValid}">
                                                                        <fss:dictOrder var="order" dictOrder="isValid">
                                                                            <option value="${order.key}">${order.value}</option>
                                                                        </fss:dictOrder>
                                                                    </select>
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">是否最后一级：</td>
                                                            <td>
                                                                <label class="select">
                                                                    <fss:dictOrder var="order" dictOrder="yesOrNo">
                                                                        <%--<option value="${order.key}">${order.value}</option>--%>
                                                                        <input type="radio" name="isEnd" value="${order.key}" id="t_${order.key}" <c:if test="${order.key == dict.isEnd}">checked</c:if> ><label for="t_${order.key}">${order.value}</label>
                                                                        &nbsp;&nbsp;&nbsp;
                                                                    </fss:dictOrder>
                                                                </label>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <div class="mb20" id="wid-id-713">
                                                    <button class="btn btn-default table-nobg-btn" type="button" id="btn_success">保存</button>
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
    	    $("#btn_success").click(function () {
    	        if (validateCheck()) {
    	            /*if (!confirm("确认 修改商户信息吗?")) {
    	               return false;
    	            }*/
    	            $("#updateDictForm").ajaxSubmit({
    	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	                dataType: "json",
    	                success: function (data) {
    	                    if (data.code == '0000') {
    	                        jAlert("修改成功!", '确认信息');
    	                        var parent_id=$("#parentId").val();
    	                        //自动跳转
    	                      parent.location.href="${contextPath}/sys/dictionary/${dict.parentId}";
    	                      //  parent.location.href="${contextPath}/sys/workassist/dictionary/0";
    	                    } else {
    	                        return;
    	                    }
    	                }
    	            });
    	        }
    	    });
        });
    	//校验函数
    	function validateCheck() {
    		return true;
    	}
        
     </script>
        
</body>

</html>