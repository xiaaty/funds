<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统管理--添加商户--冠群驰骋投资管理(北京)有限公司</title>
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
               <li>商户管理</li>
            	<li>主商户修改</li>
            </ol>
            <!-- end breadcrumb -->
        </div>

        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <form id="busiUpdateForm" action="${contextPath}/sys/busi/updateConfirm" method="post">
                   <%--     <input type="hidden" value="${dict.dictId}" name="dictId"  default="0"/> --%>
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">

                            <div class="jarviswidget" id="masterBusiUpdate" data-widget-deletebutton="false" data-widget-editbutton="false">
                               <header>
                                    <h2><i class="fa fa-save pr10"></i>主商户修改<font class="pl10 f12 color07"></font></h2>
                                </header>
                                    <div class="smart-form">

                                        <!-- widget content -->
                                            <div class="mt10 mb10 ml30">
                                                <table class="table">
                                                    <col width="112" />
                                                    <col width="367" />
                                                    <col width="112" />
                                                    <col />
                                                    <tbody>
                                                        <tr>
                                                            <td align="left">商户名称：</td>
                                                            <td>
                                                             <section style="width:210px">
                                                                <label class="input">
                                                                    <input type="hidden" id="id" name="id" value="${busi.id}">
                                                                    <input type="text" id="mchnName" name="mchnName" value="${busi.mchnName}">
                                                                </label>
                                                                </section>
                                                            </td>
                                                        </tr>
                                                        <tr class="lh32">
                                                <td align="left">商户号：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" readonly="readonly" id="mchnNo" name="mchnNo" value="${busi.mchnNo}">
                                                            <input type="hidden"  name="parentId" value="0">
                                                            <input type="hidden"  name="parentNo" value="0">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                              <tr class="lh32">
                                                <td align="left">商户密钥：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="input">
                                                            <input type="text" readonly="readonly" id="mchnKey" name="mchnKey" value="${busi.mchnKey}">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            <tr class="lh32">
                                                <td align="left">状态：</td>
                                                <td>
                                                    <section style="width:210px">
                                                        <label class="text">
                                                            <input type="radio" name="state" value="98040002" <c:if test="${busi.state ==98040002}">checked</c:if> />未启用
                                                            <input type="radio" name="state" value="98040001" <c:if test="${busi.state ==98040001}">checked</c:if> />已启用
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                <!-- end widget content -->
                                      <div class="mb20" id="wid-id-713">
                                            <button class="btn btn-default table-nobg-btn" type="button" id="updateMasterBusi">保&nbsp;&nbsp;&nbsp;存</button>
                                     		<button class="btn btn-primary table-nobg-btn" type="button" onclick="location.href='${contextPath}/sys/busi/list/${parentId}'">返&nbsp;&nbsp;&nbsp;回</button>
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
	    $("#updateMasterBusi").click(function () {
	        if (validateCheck()) {
	            if (!confirm("确认 修改商户信息吗?")) {
	               return false;
	            }
	            $("#busiUpdateForm").ajaxSubmit({
	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	                dataType: "json",
	                success: function (data) {
	                    if (data.code == '0000') {
	                        jAlert("修改成功!", '确认信息');
	                        return;
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