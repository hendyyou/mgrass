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

<div data-role="page" id="opportunitypage" data-add-back-btn="true">
<header data-role="header" data-theme="b" data-position="inline">
  <a href="<s:url action="listOpportunityPage" namespace="/jsp/crm"/>" data-icon="arrow-l" data-rel="back" ><s:text name="link.back" /></a>  
  <h1><s:text name="entity.opportunity.label"/>:<s:property value="opportunity.name"/></h1>
  <a href="../menu.jsp" data-icon="home" data-direction="reverse"><s:text name="link.menu" /></a>
</header>

<div data-role="content" class="content" >
  <s:form id="addObjectForm" validate="true" namespace="/jsp/crm" method="post">
    <s:hidden id="id" name="opportunity.id" value="%{opportunity.id}" />
	<ul data-role="listview" data-theme="d" style="margin-bottom: 0" data-inset="false">
	  <li>
	    <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.name.label"/>:</h3>
	    <h3 style="margin-top:0"><s:property value="opportunity.name"/></h3>
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
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.currency.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="currencyText"/></h3>
       </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="opportunity.expect_close_date.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="expectCloseDate"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="opportunity.opportunity_amount.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="opportunity.opportunity_amount"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.type.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="typeLabel"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="menu.salesStage.title"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="salesStageLabel"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="menu.leadSource.title"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="sourceLabel"/></h3>
	   </li>	   	   
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="opportunity.probability.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="opportunity.probability"/></h3>
	   </li>
       <li>
         <a href="getCampaign.action?id=<s:property value="campaignID"/>">
	       <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.campaign.label"/>:</h3>
	       <h3 style="margin-top:0"><s:property value="campaignText"/></h3>
	     </a>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="opportunity.next_step.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="opportunity.next_step"/></h3>
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
	     <h3 style="margin-top:0"><s:property value="opportunity.description"/></h3>
       </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.notes.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="opportunity.notes"/></h3>
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
	  <h3><s:text name='tab.related.lead'/></h3>
	  <ul id="leadList" data-role="listview" data-filter="true" data-filter-placeholder="<s:text name="title.listLead" />..." data-inset="true">
	  <s:iterator value="leads" id="lead">
	    <li><a href="getLead.action?id=<s:property value="#lead.id"/>"><s:property value="#lead.name"/></a></li>
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