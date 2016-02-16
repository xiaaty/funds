<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统管理--添加配置信息--冠群驰骋投资管理(北京)有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
    
   <%@include file="../../../../view/include/common_css_js.jsp"%>
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
    
<%@include file="../../../../view/include/menu.jsp"%>

    <div id="main" role="main">

        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
                <li>辅助功能列表</li>
                <li>系统配置</li>
                <li>添加系统配置</li>
            </ol>
            <!-- end breadcrumb -->
        </div>

        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <form id="settingForm" action="${contextPath}/sys/workassist/settingSave" method="post">
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">

                            <div class="jarviswidget" id="wid-id-711" data-widget-deletebutton="false" data-widget-editbutton="false">
                               <header>
                                    <h2><i class="fa fa-add pr10"></i>新增系统配置信息<font class="pl10 f12 color07"></font></h2>
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
<!--                                                         <tr> -->
<!--                                                             <td align="left">配置项：</td> -->
<!--                                                             <td> -->
<!--                                                                  <label class="select">  -->
<!--                                                                     <select disabled="disabled" style="width:256px;" id="setingType"> -->
<%--                                                                         <option value="9901" <c:if test="${setting.setingType=='9901'}">selected='selected'</c:if> >交易渠道配置</option> --%>
<%--                                                                         <option value="9902" <c:if test="${setting.setingType=='9902'}">selected='selected'</c:if>  >主商户配置</option> --%>
<%--                                                                         <option value="9903" <c:if test="${setting.setingType=='9903'}">selected='selected'</c:if>  >子商户配置</option> --%>
<!--                                                                     </select> -->
<!--                                                             </td> -->
<!--                                                         </tr> -->
                                                        <tr>
                                                            <td align="left">配置类型:</td>
                                                            <td>
                                                                <label class="input">
                                                            <input type="hidden" maxlength="50" name="mchnNo" id="mchnNo" value="${setting.mchnNo}"  />
                                                            <input type="hidden" name="setingType" value="${setting.setingType }">
                                                                    <input type="text" maxlength="50" name="type" id="type"  style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
<!--                                                         <tr> -->
<!--                                                             <td align="left">配置商户</td> -->
<!--                                                             <td> -->
<!--                                                                 <label class="input"> -->
<!--                                                                      -->
<!--                                                                     <input type="text" maxlength="50" name="mchnNo" id="mchnNo" style="width:256px;" /> -->
<!--                                                                 </label> -->
<!--                                                             </td> -->
<!--                                                         </tr> -->
                                                        <tr>
                                                            <td align="left">配置的健</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" name="setKey" id="setKey"  style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">配置的值</td>
                                                            <td>
                                                                <label class="input">
                                                                    <input type="text" maxlength="50" name="setValue" id="setValue" style="width:256px;" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <div class="mb20" id="wid-id-713">
                                                    <button class="btn btn-default table-nobg-btn" type="button" id="adddictmain">保存</button>
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

<%@include file="../../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
    <script type="text/javascript" charset="utf-8">
        $(document).ready(function() {
    	    $("#adddictmain").click(function () {
    	        if (validateCheck()) {
    	            $("#settingForm").ajaxSubmit({
    	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	                dataType: "json",
    	                success: function (data) {
    	                    if (data.code == '0000') {
    	                        jAlert("添加成功!", '信息提示');
    	                        //自动跳转
    	                    } else {
    	                    	jAlert("添加失败!", '消息提示');
    	                        return;
    	                    }
    	                }
    	            });
    	        }else{
    	        	 jAlert("您输入的值中存在空值，请检查!", '信息提示');
    	        }
    	    });
        });
    	//校验函数
    	function validateCheck() {
    		console.info($("#setingType").val()+"--------------");
    		var type=$("#type").val();
    		var mchnNo=$("#mchnNo").val();
    		var setKey=$("#setKey").val();
    		var setValue=$("#setValue").val();
    		if(type!=null&&""!=type.trim()&&mchnNo!=null&&""!=mchnNo.trim()&&setKey!=null&&""!=setKey.trim()&&setValue!=null&&""!=setValue.trim()){
    			return true;
    		}else{
    			return false;
    		}
    	}
        
        
        
        
        
        
        
        </script>
        
        
        
        
        
</body>

</html>