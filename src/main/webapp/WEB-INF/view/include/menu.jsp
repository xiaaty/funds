<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.gqhmt.core.util.ResourceUtil"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header id="header">
			<div id="logo-group">

				<!-- PLACE YOUR LOGO HERE -->
				<span id="logo"> <img src="${contextPath}/img/logo.png" alt="SmartAdmin"> </span>
				<!-- END LOGO PLACEHOLDER -->

            </div>

			<!-- pulled right: nav area -->
			<div class="pull-right">

				<!-- collapse menu button -->
				<div id="hide-menu" class="btn-header pull-right">
					<span> <a href="javascript:void(0);" title="收放菜单"><i class="fa fa-reorder"></i></a> </span>
				</div>
				<!-- end collapse menu -->

				<!-- logout button -->
				<div id="logout" class="btn-header transparent pull-right">
					<span> <a href="/logout" title="退出登录"><i class="fa fa-sign-out"></i></a> </span>
				</div>
				<!-- end logout button -->

				<!-- search mobile button (this is hidden till mobile view port) -->
				<div id="search-mobile" class="btn-header transparent pull-right">
					<span> <a href="javascript:void(0)" title="Search"><i class="fa fa-search"></i></a> </span>
				</div>
				<!-- end search mobile button -->

			</div>
			<!-- end pulled right: nav area -->

		</header>


    <aside id="left-panel">
        <!-- User info -->
        <div class="login-info">
                    <span> <!-- User image size is adjusted inside CSS, it should stay as it -->
                        <a href="javascript:void(0);" id="show-shortcut">
                            <img src="${contextPath}/img/avatars/sunny.png" alt="me" class="online" />
                            <span>
                         	    ${sessionScope.loginName}
                            </span>
                            <i class="fa fa-angle-down"></i>
                        </a>

                    </span>
        </div>
        <!-- end user info -->
        <nav>
        ${sessionScope.menu}
        </nav>
        <span class="minifyme"> <i class="fa fa-arrow-circle-left hit"></i> </span>
        
    </aside>
