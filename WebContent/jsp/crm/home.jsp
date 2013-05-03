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
<!-- begin first page -->

<div id="homepage" data-role="page">

<header data-role="header" data-theme="b" data-position="inline">
  <a href="../menu.jsp" data-icon="home" data-direction="reverse"><s:text name="link.menu" /></a>
  <h1><s:text name='title.page.home'/></h1>
  <a href="../../j_spring_security_logout" data-icon="delete"><s:text name="link.logout" /></a>
</header>

<div data-role="content" class="content" >
  <s:if test="#session.loginUser.view_task == 1">
    <ul id="mytask" data-role="listview" data-inset="true" data-divider-theme="c" style="margin-top: 0;">
      <li data-role="list-divider"><s:text name='title.grid.myTasks'/> <span class="ui-li-aside"><a href="<s:url action="listTaskPage" namespace="/jsp/crm"/>"><s:text name='link.entireRecords'/></a></span></li>
	  <s:iterator value="tasks" id="task">
	    <li><a href="getTask.action?id=<s:property value="#task.id"/>"><s:property value="#task.name"/></a></li>
	  </s:iterator>
    </ul>
  </s:if>
  <s:if test="#session.loginUser.view_account == 1">
    <ul id="myaccount" data-role="listview" data-inset="true" data-divider-theme="c" style="margin-top: 0;">
      <li data-role="list-divider" data-divider-theme="a"><s:text name='title.grid.myAccounts'/> <span class="ui-li-aside"><a href="<s:url action="listAccountPage" namespace="/jsp/crm"/>"><s:text name='link.entireRecords'/></a></span></li>
	  <s:iterator value="accounts" id="account">
	    <li><a href="getAccount.action?id=<s:property value="#account.id"/>"><s:property value="#account.name"/></a></li>
	  </s:iterator>
    </ul>
  </s:if>   
  <s:if test="#session.loginUser.view_lead == 1">
    <ul id="mylead" data-role="listview" data-inset="true" data-divider-theme="c" style="margin-top: 0;">
      <li data-role="list-divider" data-divider-theme="a"><s:text name='title.grid.myLeads'/> <span class="ui-li-aside"><a href="<s:url action="listLeadPage" namespace="/jsp/crm"/>"><s:text name='link.entireRecords'/></a></span></li>
	  <s:iterator value="leads" id="lead">
	    <li><a href="getLead.action?id=<s:property value="#lead.id"/>"><s:property value="#lead.name"/></a></li>
	  </s:iterator>
    </ul>
  </s:if>
  <s:if test="#session.loginUser.view_opportunity == 1">
    <ul id="myopportunity" data-role="listview" data-inset="true" data-divider-theme="c" style="margin-top: 0;">
      <li data-role="list-divider" data-divider-theme="a"><s:text name='title.grid.myOpportunities'/> <span class="ui-li-aside"><a href="<s:url action="listOpportunityPage" namespace="/jsp/crm"/>"><s:text name='link.entireRecords'/></a></span></li>
	  <s:iterator value="opportunities" id="opportunity">
	    <li><a href="getOpportunity.action?id=<s:property value="#opportunity.id"/>"><s:property value="#opportunity.name"/></a></li>
	  </s:iterator>
    </ul>
  </s:if> 
  <s:if test="#session.loginUser.view_meeting == 1">
    <ul id="mymeeting" data-role="listview" data-inset="true" data-divider-theme="c" style="margin-top: 0;">
      <li data-role="list-divider" data-divider-theme="a"><s:text name='title.grid.myMeetings'/> <span class="ui-li-aside"><a href="<s:url action="listMeetingPage" namespace="/jsp/crm"/>"><s:text name='link.entireRecords'/></a></span></li>
	  <s:iterator value="meetings" id="meeting">
	    <li><a href="getMeeting.action?id=<s:property value="#meeting.id"/>"><s:property value="#meeting.name"/></a></li>
	  </s:iterator>
    </ul>
  </s:if>
  <s:if test="#session.loginUser.view_call == 1">
    <ul id="mycall" data-role="listview" data-inset="true" data-divider-theme="c" style="margin-top: 0;">
      <li data-role="list-divider" data-divider-theme="a"><s:text name='title.grid.myCalls'/> <span class="ui-li-aside"><a href="<s:url action="listCallPage" namespace="/jsp/crm"/>"><s:text name='link.entireRecords'/></a></span></li>
	  <s:iterator value="calls" id="call">
	    <li><a href="getCall.action?id=<s:property value="#call.id"/>"><s:property value="#call.name"/></a></li>
	  </s:iterator>
    </ul>
  </s:if>  
</div>

<footer data-role="footer" ><h3>Copyright<span class="sign">&copy;</span><s:text name="copyright.info" /></h3></footer>

</div>

</body>
</html>