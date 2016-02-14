<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.gqhmt.core.util.GlobalConstants"%>
    <%@page import="com.gqhmt.util.RequestUtil"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>冠群驰骋资金清结算系统</title>
    <link type="image/x-icon" rel="shortcut icon" href="<%=request.getContextPath()%>/img/favicon.ico" >
    <link href="<%=request.getContextPath()%>/css/base.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css" />
    <script src="<%=request.getContextPath()%>/js/jquery-1.8.1.min.js" type="text/javascript" charset="utf-8"></script>
<!--[if IE]>
<script src="<%=request.getContextPath() %>/js/placeholder.js" type="text/javascript" charset="utf-8"></script>
<![endif]-->
<script type="text/javascript" charset="utf-8">

</script>
</head>
<body style="background:#fff;">
   <!--login-->
    <div class="login">
        <!--header-->
        <div class="header box1">
            <a href="#"><img height="44" width="164" src="<%=request.getContextPath()%>/img/login/logo_login.jpg" alt="冠群驰骋"/></a>
            <span class="txt">冠群驰骋资金清结算系统</span>
        </div>
        <!--header-->
        <!--body-->
        <div class="body">
            <div class="box2">
            <div class="box1 pr clearfix">
                <div class="login_box bg">
                    <h1>登录资金清结算系统</h1>
                    <% 
							String errorCode=RequestUtil.getStringDefault(request, "errorCode","");
							String codeMess="";
							String mess="";
							if(GlobalConstants.loginErrorMap.containsKey(errorCode)){
								if(errorCode.equalsIgnoreCase("0001")){
									codeMess=GlobalConstants.loginErrorMap.get(errorCode);
								}else{
									mess=GlobalConstants.loginErrorMap.get(errorCode);
								}
							}
							%>
                    <form action="<%=request.getContextPath()%>/login" method="post">
					<ul class="user_login mb15">
						<li><input type="text" class="input_login" value="" maxlength="20" name="loginName" id="loginid" placeholder="请输入用户名"/><span class="user_pic bg"></span><br><span class="login_error"></span></li>
						<li><input type="password" class="input_login" value="" maxlength="20" name="loginPwd" id="loginpwd" placeholder="请输入密码"/><span class="user_pw bg"></span>
						<br><span class="login_error color04">
							<%= mess %>
						</span></li>
						<li><input type="text" class="input_login" value="" maxlength="6" name="verifyCode" placeholder="验证码" id="validCode" onkeydown="if(event.keyCode==13){return false;}"/><br><span class="login_error color04"><%= codeMess %></span>
						    <span class="validcode_span"><a id="changeCode" class="ml5" href="javascript:void(0);"><img height="28" width="84" id="imgVerify" src="<%=request.getContextPath()%>/captcha"/></a></span>
						</li>
						<!-- <li class="color04">
						    <span class="fl f12"><input type="checkbox" class="mr5" name="remPwd" id="r_pwd"/><label>记住密码</label></span>
						    <span class="fr f12"><a href="#" >忘记密码？</a></span>
						</li> -->
					</ul>
					<button type="submit" class="btn100"><span>登&nbsp;&nbsp;录</span></button>
					</form>
					 <div class="clearfix codeimg">
                        <img class="fl" src="<%=request.getContextPath()%>/img/wx.gif" alt="二维码"/>
                        <p class="fl f14">扫二维码及时<br/>关注冠群动态</p>
                    </div>
				</div>
            </div>
            </div>
        </div>
        <div class="footer box1">
            <img src="<%=request.getContextPath()%>/img/login/guanqun.jpg" alt="冠群驰骋投资管理(北京)有限公司"/>
        </div>
    </div>
<script type="text/javascript" charset="utf-8">
    $(function(){
        $(".user_login li").eq(2).css('padding-bottom','10px');
    	
        if($(window).height() < 700){
            $(".login").css("margin-top","10px");
        }else{
            $(".login").css({
                    "margin-top":"-360px",
                    "top":"50%",
                    "position":"absulate"
            });
        }
    });
    $('#changeCode').click(function(){
    	$('#imgVerify').attr('src','<%=request.getContextPath()%>/captcha?t='+(new Date()).valueOf());
    });
</script>
    </body>
</html>
