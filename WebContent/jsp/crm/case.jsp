<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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

<div data-role="page" id="casepage" data-add-back-btn="true">
<header data-role="header" data-theme="b" data-position="inline">
  <a href="<s:url action="listCasePage" namespace="/jsp/crm"/>" data-icon="arrow-l" data-rel="back" ><s:text name="link.back" /></a>  
  <h1><s:text name="entity.case.label"/>:<s:property value="caseInstance.name"/></h1>
  <a href="../menu.jsp" data-icon="home" data-direction="reverse"><s:text name="link.menu" /></a>
</header>

<div data-role="content" class="content" >
  <s:form id="addObjectForm" validate="true" namespace="/jsp/crm" method="post">
    <s:hidden id="id" name="caseInstance.id" value="%{caseInstance.id}" />
	<ul data-role="listview" data-theme="d" style="margin-bottom: 0" data-inset="false">
	  <li>
	    <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.subject.label"/>:</h3>
	    <h3 style="margin-top:0"><s:property value="caseInstance.subject"/></h3>
      </li>
      <li>
	    <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.owner.label"/>:</h3>
	    <h3 style="margin-top:0"><s:property value="ownerText"/></h3>
	  </li>	  
	</ul>
	
   <div data-role="collapsible" data-collapsed="false" data-inset="false" data-mini="true" data-theme="b" data-content-theme="d" >
     <h3><s:text name='tab.overview'/></h3>
	 <ul data-role="listview" data-theme="d" style="margin-bottom: 0" data-inset="false">
	   <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.priority.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="priorityLabel"/></h3>
       </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.status.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="statusLabel"/></h3>
	   </li>
       <li>
         <a href="getAccount.action?id=<s:property value="accountID"/>">
	       <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.account.label"/>:</h3>
	       <h3 style="margin-top:0"><s:property value="accountText"/></h3>
	     </a>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="case.type.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="typeLabel"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="case.origin.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="originLabel"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="case.reason.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="reasonLabel"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="case.resolution.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="caseInstance.resolution"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.assigned_to.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="assignedToText"/></h3>
	   </li>	   
	 </ul>
	</div>
	<div data-role="collapsible" data-collapsed="true" data-inset="false" data-mini="true" data-theme="b" data-content-theme="d" >
	  <h3><s:text name='tab.details'/></h3>
	  <ul data-role="listview" data-theme="d" style="margin-bottom: 0" data-inset="false">
	   <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.description.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="caseInstance.description"/></h3>
       </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.notes.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="caseInstance.notes"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.createdBy.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="createdBy"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.createdOn.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="createdOn"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.updatedBy.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="updatedBy"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.updatedOn.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="updatedOn"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.id.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="id"/></h3>
	   </li>		   	   	   
	 </ul>
	</div>
	<div data-role="collapsible" data-collapsed="true" data-inset="false" data-mini="true" data-theme="b" data-content-theme="d" >
	  <h3><s:text name='tab.related.contact'/></h3>
	  <ul id="contactList" data-role="listview" data-filter="true" data-filter-placeholder="<s:text name="title.listContact" />..." data-inset="true">
	  <s:iterator value="contacts" id="contact">
	    <li><a href="getContact.action?id=<s:property value="#contact.id"/>"><s:property value="#contact.name"/></a></li>
	  </s:iterator>
	 </ul>
	</div>
	<div data-role="collapsible" data-collapsed="true" data-inset="false" data-mini="true" data-theme="b" data-content-theme="d" >
	  <h3><s:text name='tab.related.document'/></h3>
	  <ul id="documentList" data-role="listview" data-filter="true" data-filter-placeholder="<s:text name="title.listDocument" />..." data-inset="true">
	  <s:iterator value="documents" id="document">
	    <li><a href="getDocument.action?id=<s:property value="#document.id"/>"><s:property value="#document.name"/></a></li>
	  </s:iterator>
	 </ul>
	</div>
	<div data-role="collapsible" data-collapsed="true" data-inset="false" data-mini="true" data-theme="b" data-content-theme="d" >
	  <h3><s:text name='tab.related.task'/></h3>
	  <ul id="taskList" data-role="listview" data-filter="true" data-filter-placeholder="<s:text name="title.listTask" />..." data-inset="true">
	  <s:iterator value="tasks" id="task">
	    <li><a href="getTask.action?id=<s:property value="#task.id"/>"><s:property value="#task.name"/></a></li>
	  </s:iterator>
	 </ul>
	</div>			
  </s:form>
</div>

<footer data-role="footer" ><h3>Copyright<span class="sign">&copy;</span><s:text name="copyright.info" /></h3></footer>

</div>

</body>
</html>