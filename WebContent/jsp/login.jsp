<!DOCTYPE html> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html> 
 <head> 
  <title><s:text name='title.page.login'/></title> 
  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" /> 
  <link rel="stylesheet" href="<s:url value="/css/jquery.mobile-1.3.0.min.css"/>"/>
  <script src="<s:url value="/js/jquery-1.8.3.min.js"/>"></script>
  <script src="<s:url value="/js/jquery.mobile-1.3.0.min.js"/>"></script>
  <script src="<s:url value="/js/global.js"/>"></script>
	<script type="text/javascript">	
		$(document).ready(function() {
			$(".logininput").blur(function() {
				if ($(this).val() == "") {
					$(this).css("border-color", "red");
				} else
					$(this).css("border-color", "#D9D6C4");
			})
			document.f.j_username.focus();
			var URLParams = new Array();
			var aParams = document.location.search.substr(1).split('&');
			for (i=0; i < aParams.length ; i++){
			   var aParam = aParams[i].split('=');
			   URLParams[aParam[0]] = aParam[1];
			}
			locale=URLParams["request_locale"];
			if (locale != null && locale == "zh_CN"){
				$("#j_language").val("zh_CN");
			} else {
				$("#j_language").val("en_US");
			}
		});

		function keypressTab(){
			var ev=window.event.keyCode;
			if(ev == 13){
				document.f.j_password.focus();
			}
		}

		function keypress(){
			var ev=window.event.keyCode;
			if(ev == 13){
				f.submit();
			}
		}
		
		function switchLanguage(language){
			f.action= getWebPath() + "/jsp/login.jsp?request_locale="+language;
			f.submit();
		}		
	</script>  
</head> 
<body>
<!-- begin first page -->

<section id="page1" data-role="page">

<header data-role="header" data-theme="b" ><h1><s:text name='head.login'/></h1></header>

<div data-role="content" class="content">

<form name="f" action="<c:url value='/j_spring_security_check'/>" method="POST">
  <div data-role="fieldcontain">
    <label for="j_username" data-inline="true"><s:text name='login.username.label'/>:</label>
    <input type="text" id="j_username" name="j_username" onkeypress="keypressTab();" class="logininput" value="<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>"/><br>
  </div>	
  <div data-role="fieldcontain">
    <label for="j_password" data-inline="true"><s:text name='login.password.label'/>:</label>
    <input type="password" name="j_password" id="j_password" onkeypress="keypress();" class="logininput"/>
  </div>	
  <div data-role="fieldcontain">
    <label for="j_language"><s:text name='login.language.label'/>:</label>
    <select id="j_language" name="j_language" onchange="switchLanguage(this.value)">
      <option selected="true" value="en_US">English (US)</option>
      <option value="zh_CN">简体中文</option></select>
    </select>
  </div>
<style>
.ui-grid-a .ui-block-a{
     width: 22%; text-align: right;
}
.ui-grid-a .ui-block-b{
     width: 78%; text-align: left;
}
</style>
  <div class="ui-grid-a">
    <div class="ui-block-a"></div>
    <div class="ui-block-b"><a data-role="button" id="submit" name="submit" data-theme="b" data-inline="true" onclick="f.submit();"><s:text name='login.button.label'/></a></div>
  </div>
</form>

</div>

<footer data-role="footer" ><h1>Grass CRM</h1></footer>

</section>

</body>
</html>