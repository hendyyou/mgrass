<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java"  import="com.gcrm.domain.User"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
 <head> 
  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1"/> 
  <link rel="stylesheet" href="<s:url value="/css/jquery.mobile-1.3.0.min.css"/>"/>
  <script src="<s:url value="/js/jquery-1.8.3.min.js"/>"></script>
  <script src="<s:url value="/js/jquery.mobile-1.3.0.min.js"/>"></script>
  <script src="<s:url value="/js/global.js"/>"></script>
</head> 
<body>

<div id="callspage" data-role="page">

<header data-role="header" data-theme="b" data-position="inline">
  <a href="../menu.jsp" data-icon="arrow-l" data-direction="reverse"><s:text name="link.menu" /></a>
  <h1><s:text name='title.grid.calls'/></h1>
  <a href="../../j_spring_security_logout" data-icon="delete"><s:text name="link.logout" /></a>
</header>

<div data-role="content" class="content" >

<ul id="callList" data-role="listview" data-filter="true" data-filter-placeholder="<s:text name="title.listCall" />..." data-inset="true">
  <s:iterator value="calls" id="call">
    <li><a href="getCall.action?id=<s:property value="#call.id"/>"><s:property value="#call.name"/></a></li>
  </s:iterator>
</ul>

</div>

<footer data-role="footer" ><h3>Copyright<span class="sign">&copy;</span><s:text name="copyright.info" /></h3></footer>
</div>

</body>
</html>