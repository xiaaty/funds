<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>内审系统</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

   <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <%@include file="/WEB-INF/jsp/inc/common_css_js.inc" %>
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

    <%@include file="/WEB-INF/jsp/inc/menu.inc" %>
    




    <div id="main" role="main">

        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
                <li>系统管理</li>
                <li>密码修改</li>
            </ol>
            <!-- end breadcrumb -->
        </div>

        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <form id="pwdForm" action="${contextPath}/sys/uppwd" method="post">
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">
                            <div class="alert alert-block alert-success" id="wid-id-721">
                                <button class="close" data-dismiss="alert">×</button>
                                <ul class="h30 lh30">
                                    <li><span id="mess" style="color:red;">${message}</span></li>
                                </ul>
                            </div>
 							 <input type="hidden" maxlength="20" name="id" value="${id}" />
                            <div class="jarviswidget" id="wid-id-722"  data-widget-deletebutton="false" data-widget-editbutton="false">
                                <header>
                                    <h2>密码修改<font class="pl10 f12 color07"></font></h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <div class="smart-form">

                                        <!-- widget content -->
                                        <div class="widget-body no-padding">
                                            <div class="mt10 mb10 ml30">
                                                <table border="0" summary="修改密码" style="width:650px">
                                                    <col width="90" />
                                                    <col />
                                                    <tr>
                                                        <td style="line-height:50px;" >原&nbsp;&nbsp;密&nbsp;&nbsp;码：</td>
                                                        <td style="line-height:50px;">
                                                            <label class="input clearfix lh32">
                                                                <input type="password" maxlength="20" name="oldPwd" value="" style="width:248px;" class="fl mr10" /><span class="fl" style="color:red">密码长度6-20位</span>
                                                            </label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td style="line-height:50px;" >新&nbsp;&nbsp;密&nbsp;&nbsp;码：</td>
                                                        <td style="line-height:50px;" >
                                                            <label class="input clearfix lh32">
                                                                <input type="password" name="newPwd" maxlength="20" value="" style="width:248px;" class="fl mr10" /><span class="fl" style="color:red">密码长度6-20位</span>
                                                            </label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td style="line-height:50px;" >确认密码：</td>
                                                        <td style="line-height:50px;" >
                                                            <label class="input">
                                                                <input type="password" name="confirmPwd" maxlength="20" value="" style="width:248px;"  class="fl mr10" />
                                                            </label>
                                                        </td>
                                                    </tr>
                                                </table>

                                                <div class="mb20 mt20" id="wid-id-723">
                                                    <button class="btn btn-default table-nobg-btn" type="button" id="savePwd"><i class="fa  fa-clipboard mr5"></i>保存</button>
                                                    <!--<button class="btn btn-default table-nobg-btn" type="button" onclick="return btn_back()"><i class="fa  fa-clipboard mr5"></i>返回</button>-->
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

    
<%@include file="/WEB-INF/jsp/inc/common_footer_css_js.inc" %>
  <script type="text/javascript" charset="utf-8">
      	$('#savePwd').click(function(){
      		if(checkForm()){
      			if($('input[name=newPwd]').val().length<6){
      				alert('密码至少6位');
      				return false;
      			}
      			if($('input[name=newPwd]').val()!=$('input[name=confirmPwd]').val()){
      				alert('新密码不一致');
      				return false;
      			}
      			$('#pwdForm').submit();
      		}
      	});
      	function checkForm(){
        	var flag=true;
        	$('#pwdForm .fl.mr10').each(function(){
        		if(!$(this).val()){
        			$('#mess').html($(this).closest('td').prev('td').text().replace(/[a-z0-9*<>/=&;："]+/gi,'')+"不能为空");
        			$(this).focus();
        			flag=false;
        			return false;
        		}
        	});
        	return flag;
        }
      	
      	function btn_back(){
 
        	window.open("${contextPath}/sys/empmanage?","_parent");
        }
        $(document).ready(function () {
        	pageSetUp();
        });
   
      </script>

</body>

</html>