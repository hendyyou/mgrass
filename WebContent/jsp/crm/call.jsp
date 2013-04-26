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

<div data-role="page" id="callpage" data-add-back-btn="true">
<header data-role="header" data-theme="b" data-position="inline">
  <a href="../menu.jsp" data-icon="arrow-l" data-rel="back" ><s:text name="link.back" /></a>  
  <h1><s:text name="entity.call.label"/>:<s:property value="call.name"/></h1>
  <a href="../../j_spring_security_logout" data-icon="delete"><s:text name="link.logout" /></a>
</header>

<div data-role="content" class="content" >
  <s:form id="addObjectForm" validate="true" namespace="/jsp/crm" method="post">
    <s:hidden id="id" name="call.id" value="%{call.id}" />
	<ul data-role="listview" data-theme="d" style="margin-bottom: 0" data-inset="false">
	  <li>
	    <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.subject.label"/>:</h3>
	    <h3 style="margin-top:0"><s:property value="call.subject"/></h3>
      </li>
	  <li>
	    <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="call.direction.label"/>:</h3>
	    <h3 style="margin-top:0"><s:property value="directionLabel"/></h3>
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
	    <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.status.label"/>:</h3>
	    <h3 style="margin-top:0"><s:property value="statusLabel"/></h3>
      </li>
	   <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.start_date.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="startDate"/></h3>
       </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.related_object.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="relatedObjectText"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.related_record.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="relatedRecordText"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.reminder_email.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="call.reminder_email"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.reminder_option_email_name.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="reminderOptionEmailLabel"/></h3>
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
	     <h3 style="margin-top:0"><s:property value="call.description"/></h3>
       </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.notes.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="call.notes"/></h3>
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
  </s:form>
</div>

<footer data-role="footer" ><h3>Copyright<span class="sign">&copy;</span><s:text name="copyright.info" /></h3></footer>

</div>

</body>
</html>