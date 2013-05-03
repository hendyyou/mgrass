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

<div data-role="page" id="contactpage" data-add-back-btn="true">
<header data-role="header" data-theme="b" data-position="inline">
  <a href="<s:url action="listContactPage" namespace="/jsp/crm"/>" data-icon="arrow-l" data-rel="back" ><s:text name="link.back" /></a>  
  <h1><s:text name="entity.contact.label"/>:<s:property value="contact.name"/></h1>
  <a href="../menu.jsp" data-icon="home" data-direction="reverse"><s:text name="link.menu" /></a>
</header>

<div data-role="content" class="content" >
  <s:form id="addObjectForm" validate="true" namespace="/jsp/crm" method="post">
    <s:hidden id="id" name="contact.id" value="%{contact.id}" />
	<ul data-role="listview" data-theme="d" style="margin-bottom: 0" data-inset="false">
	  <li>
	    <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="menu.salutation.title"/>:</h3>
	    <h3 style="margin-top:0"><s:property value="salutationLabel"/></h3>
      </li>
	  <li>
	    <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.first_name.label"/>:</h3>
	    <h3 style="margin-top:0"><s:property value="contact.first_name"/></h3>
      </li>
	  <li>
	    <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.last_name.label"/>:</h3>
	    <h3 style="margin-top:0"><s:property value="contact.last_name"/></h3>
      </li>
	  <li>
	    <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.title.label"/>:</h3>
	    <h3 style="margin-top:0"><s:property value="contact.title"/></h3>
      </li>
	  <li>
	    <a href="getAccount.action?id=<s:property value="accountID"/>">
	      <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.account.label"/>:</h3>
	      <h3 style="margin-top:0"><s:property value="accountText"/></h3>
	    </a>
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
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.email.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="contact.email"/></h3>
       </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.office_phone.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="contact.office_phone"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.website.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="contact.website"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.fax.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="contact.fax"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.mobile.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="contact.mobile"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="contact.skype_id.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="contact.skype_id"/></h3>
	   </li>	   	   
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.primary_street.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="contact.primary_street"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.primary_city.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="contact.primary_city"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.primary_state.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="contact.primary_state"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.primary_country.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="contact.primary_country"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.primary_postal_code.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="contact.primary_postal_code"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.other_street.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="contact.other_street"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.other_city.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="contact.other_city"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.other_state.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="contact.other_state"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.other_country.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="contact.other_country"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.other_postal_code.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="contact.other_postal_code"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.department.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="contact.department"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="menu.leadSource.title"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="leadSourceLabel"/></h3>
	   </li>
       <li>
         <a href="getCampaign.action?id=<s:property value="campaignID"/>">
	       <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.campaign.label"/>:</h3>
	       <h3 style="margin-top:0"><s:property value="campaignText"/></h3>
	     </a>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.not_call.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="contact.not_call"/></h3>
	   </li>
       <li>
         <a href="getContact.action?id=<s:property value="reportToID"/>">
	       <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="contact.report_to.label"/>:</h3>
	       <h3 style="margin-top:0"><s:property value="contact.reportToText"/></h3>
	     </a>
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
	     <h3 style="margin-top:0"><s:property value="contact.description"/></h3>
       </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.notes.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="contact.notes"/></h3>
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