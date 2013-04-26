<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" import="java.util.Map" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java"  import="com.gcrm.domain.User"%> 
<%User user = (User)session.getAttribute("loginUser");%>
<%request.setAttribute("user",user);%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
 <head> 
  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1"/> 
  <link rel="stylesheet" href="<s:url value="/css/jquery.mobile-1.3.0.min.css"/>"/>
  <script src="<s:url value="/js/jquery-1.8.3.min.js"/>"></script>
  <script src="<s:url value="/js/jquery.mobile-1.3.0.min.js"/>"></script>
  <script src="<s:url value="/js/global.js"/>"></script>
<body>

<div data-role="page" id="menupage" data-add-back-btn="true">
<header data-role="header" data-theme="b" data-position="inline">
  <a href="" data-icon="arrow-l" data-rel="back"><s:text name="link.back" /></a>  
  <h1><s:text name="link.menu"/></h1>
  <a href="../j_spring_security_logout" data-icon="delete"><s:text name="link.logout" /></a>
</header>

<div data-role="content" class="content" >

<ul data-role="listview" data-inset="true" data-divider-theme="c" style="margin-top: 0;">
   <li data-role="list-divider" data-divider-theme="a"><s:text name='menu.sales.title'/></li>
   <li><a href="<s:url action="homePage" namespace="/jsp/crm"/>"><s:text name='menu.home.title'/></a></li>
   <s:if test="#session.loginUser.view_account == 1"><li><a href="<s:url action="listAccountPage" namespace="/jsp/crm"/>"><s:text name='menu.accounts.title'/></a></li></s:if>
   <s:if test="#session.loginUser.view_contact == 1"><li><a href="<s:url action="listContactPage" namespace="/jsp/crm"/>"><s:text name='menu.contacts.title'/></a></li></s:if>
   <s:if test="#session.loginUser.view_opportunity == 1"><li><a href="<s:url action="listOpportunityPage" namespace="/jsp/crm"/>"><s:text name='menu.opportunities.title'/></a></li></s:if>
   <s:if test="#session.loginUser.view_lead == 1"><li><a href="<s:url action="listLeadPage" namespace="/jsp/crm"/>"><s:text name='menu.leads.title'/></a></li></s:if>
   <s:if test="#session.loginUser.view_targetList == 1"><li><a href="<s:url action="listTargetListPage" namespace="/jsp/crm"/>"><s:text name='menu.targetLists.title'/></a></li></s:if>
   <li data-role="list-divider" data-divider-theme="a"><s:text name='menu.marketing.title'/></li>
   <s:if test="#session.loginUser.view_account == 1"><li><a href="<s:url action="listAccountPage" namespace="/jsp/crm"/>"><s:text name='menu.accounts.title'/></a></li></s:if>
   <s:if test="#session.loginUser.view_contact == 1"><li><a href="<s:url action="listContactPage" namespace="/jsp/crm"/>"><s:text name='menu.contacts.title'/></a></li></s:if>
   <s:if test="#session.loginUser.view_campaign == 1"><li><a href="<s:url action="listCampaignPage" namespace="/jsp/crm"/>"><s:text name='menu.campaigns.title'/></a></li></s:if>
   <s:if test="#session.loginUser.view_target == 1"><li><a href="<s:url action="listTargetPage" namespace="/jsp/crm"/>"><s:text name='menu.targets.title'/></a></li></s:if>
   <s:if test="#session.loginUser.view_targetList == 1"><li><a href="<s:url action="listTargetListPage" namespace="/jsp/crm"/>"><s:text name='menu.targetLists.title'/></a></li></s:if>
   <li data-role="list-divider" data-divider-theme="a"><s:text name='menu.support.title'/></li>
   <s:if test="#session.loginUser.view_contact == 1"><li><a href="<s:url action="listContactPage" namespace="/jsp/crm"/>"><s:text name='menu.contacts.title'/></a></li></s:if>
   <s:if test="#session.loginUser.view_case == 1"><li><a href="<s:url action="listCasePage" namespace="/jsp/crm"/>"><s:text name='menu.cases.title'/></a></li></s:if>
   <s:if test="#session.loginUser.view_targetList == 1"><li><a href="<s:url action="listTargetListPage" namespace="/jsp/crm"/>"><s:text name='menu.targetLists.title'/></a></li></s:if>
   <li data-role="list-divider" data-divider-theme="a"><s:text name='menu.activities.title'/></li>
   <s:if test="#session.loginUser.view_call == 1"><li><a href="<s:url action="listCallPage" namespace="/jsp/crm"/>"><s:text name='menu.calls.title'/></a></li></s:if>
   <s:if test="#session.loginUser.view_meeting == 1"><li><a href="<s:url action="listMeetingPage" namespace="/jsp/crm"/>"><s:text name='menu.meetings.title'/></a></li></s:if>
   <s:if test="#session.loginUser.view_task == 1"><li><a href="<s:url action="listTaskPage" namespace="/jsp/crm"/>"><s:text name='menu.tasks.title'/></a></li></s:if>
   <s:if test="#session.loginUser.view_targetList == 1"><li><a href="<s:url action="listTargetListPage" namespace="/jsp/crm"/>"><s:text name='menu.targetLists.title'/></a></li></s:if>   
   <li data-role="list-divider" data-divider-theme="a"><s:text name='menu.collaboration.title'/></li>
   <s:if test="#session.loginUser.view_document == 1"><li><a href="<s:url action="listDocumentPage" namespace="/jsp/crm"/>"><s:text name='menu.documents.title'/></a></li></s:if>
   <s:if test="#session.loginUser.view_targetList == 1"><li><a href="<s:url action="listTargetListPage" namespace="/jsp/crm"/>"><s:text name='menu.targetLists.title'/></a></li></s:if>
</ul>

</div>

<footer data-role="footer" ><h3>Copyright<span class="sign">&copy;</span><s:text name="copyright.info" /></h3></footer>

</div>

</body>
</html>