<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <%@include file= "../../../view/include/common_css_js.jsp"%>
    <style>
        table .title {
            background-color: #ddd;
            border-top-width: 1px;
            border-right-width: 1px;
            border-bottom-width: 1px;
            border-left-width: 1px;
            border-top-style: solid;
            border-top-color: #bfc4ca;
            border-right-color: #bfc4ca;
            border-bottom-color: #bfc4ca;
            border-left-color: #bfc4ca;
        }
        table.data{margin:3px auto;text-align:center;border-collapse: collapse; line-height:22px;font-size:12px; font-weight:normal}
        table.data th{border:1px solid #bfc4ca;background:#eee;color:#284355}
        table.data td{border:1px solid #bfc4ca;color:#344b50}
    </style>

</head>

<body>
<span id="0803090000_0803090000">

<table  width="790" border="0" align="center" cellpadding="0" cellspacing="0">
    <tbody>
    <tr class="title">
        <td width="120" height="31" align="center"><img src="images/013.gif" alt="兴业银行" width="120" height="30">
        </td>
        <td align="center">支付限额</td>
    </tr>
    <tr>
        <td colspan="2">
            <table class="data"  width="100%" border="0" cellpadding="0" cellspacing="0" autosortcol="0">
                <thead>
                <tr>
                    <th colspan="2" style="border-top:0;">兴业</th>
                    <th colspan="4">手机动态密码版</th>
                    <th colspan="4">U盾</th>
                </tr>
                <tr>
                    <th colspan="2">支付卡类型</th>
                    <th colspan="2">单笔限额</th>
                    <th colspan="2">每日限额</th>
                    <th colspan="2">单笔限额</th>
                    <th colspan="2">每日限额</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td colspan="2" width="10%">借记卡</td>
                    <td colspan="2" width="20%">10000</td>
                    <td colspan="2" width="20%">初始5000可至网点加大</td>
                    <td colspan="2" width="20%">100万</td>
                    <td colspan="2" width="20%">100万</td>
                </tr>
                </tbody>
            </table>
        </td>
    </tr>
    </tbody>
</table>
</span>
</body>

</html>