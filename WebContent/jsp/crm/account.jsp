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

<div data-role="page" id="accountpage" data-add-back-btn="true">
<header data-role="header" data-theme="b" data-position="inline">
  <a href="../menu.jsp" data-icon="arrow-l" data-rel="back" ><s:text name="link.back" /></a>  
  <h1><s:text name="entity.account.label"/>:<s:property value="account.name"/></h1>
  <a href="../../j_spring_security_logout" data-icon="delete"><s:text name="link.logout" /></a>
</header>

<div data-role="content" class="content" >
  <s:form id="addObjectForm" validate="true" namespace="/jsp/crm" method="post">
    <s:hidden id="id" name="account.id" value="%{account.id}" />
	<ul data-role="listview" data-theme="d" style="margin-bottom: 0" data-inset="false">
	  <li>
	    <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.name.label"/>:</h3>
	    <h3 style="margin-top:0"><s:property value="account.name"/></h3>
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
	     <h3 style="margin-top:0"><s:property value="account.email"/></h3>
       </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.office_phone.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.office_phone"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.website.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.website"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.fax.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.fax"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.billing_street.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.bill_street"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.billing_city.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.bill_city"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.billing_state.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.bill_state"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.billing_country.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.bill_country"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.billing_postal_code.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.bill_postal_code"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.shipping_street.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.ship_street"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.shipping_city.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.ship_city"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.shipping_state.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.ship_state"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.shipping_country.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.ship_country"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.shipping_postal_code.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.ship_postal_code"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.type.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="typeLabel"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="menu.industry.title"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="industryLabel"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="account.annual_revenue.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.annual_revenue"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="account.market_value.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.market_value"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="account.sic_code.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.sic_code"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="account.ticket_symbol.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.ticket_symbol"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="account.manager.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="managerText"/></h3>
	     
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="account.ownship.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="ownerText"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="account.rating.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.rating"/></h3>
	   </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="account.employees.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.employees"/></h3>
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
	     <h3 style="margin-top:0"><s:property value="account.description"/></h3>
       </li>
       <li>
	     <h3 class="ui-disabled" style="margin:0 inherit"><s:text name="entity.notes.label"/>:</h3>
	     <h3 style="margin-top:0"><s:property value="account.notes"/></h3>
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