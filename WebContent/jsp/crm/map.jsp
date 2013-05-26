<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java"  import="com.gcrm.domain.User"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
 <head> 
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta http-equiv="content-language" content="en" />
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <meta name="keywords" content="Google maps, jQuery, plugin, mobile, iphone, ipad, android, HTML5" />
  <link rel="schema.DC" href="http://purl.org/dc/elements/1.1/" />
  <meta name="DC.title" content="jQuery mobile with Google maps - Google maps jQuery plugin" />
  <meta name="DC.subject" content="Google maps;jQuery;plugin;mobile;iphone;ipad;android;HTML5" />
  <meta name="DC.language" content="en" />  
  <link rel="stylesheet" href="<s:url value="/css/jquery.mobile-1.0.css"/>"/>
  <link rel="stylesheet" href="<s:url value="/css/mobile.css"/>"/>
  <script src="<s:url value="/js/modernizr.min.js"/>"></script>
  <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=<%=(String)session.getAttribute("locale")%>&libraries=places"></script> 
  <script src="<s:url value="/js/jquery-1.7.1.min.js"/>"></script>
  <script src="<s:url value="/js/jquery.mobile-1.0.min.js"/>"></script>
  <script src="<s:url value="/js/jquery.ui.autocomplete.min.js"/>"></script>
  <script src="<s:url value="/js/map.js"/>"></script>
  <script src="<s:url value="/js/jquery.ui.map.js"/>"></script>
  <script src="<s:url value="/js/jquery.ui.map.services.js"/>"></script>
  <script src="<s:url value="/js/jquery.ui.map.extensions.js"/>"></script>
  <script type="text/javascript">
    var mobileDemo = { 'center': '57.7973333,12.0502107', 'zoom': 10 };
	$('#places').live('pageinit', function() {
		demo.add('places_1', function() {
			$('#map_canvas').gmap({'center': mobileDemo.center, 'zoom': mobileDemo.zoom, 'disableDefaultUI':true, 'callback': function() {
				var self = this;
				var control = self.get('control', function() {
					$(self.el).append('<div id="control"><div><input id="places-search" class="ui-bar-d ui-input-text ui-body-null ui-corner-all ui-shadow-inset ui-body-d ui-autocomplete-input" type="text"/></div></div>');
					self.autocomplete($('#places-search')[0], function(ui) {
						self.clear('markers');
						self.set('bounds', null);
						self.placesSearch({ 'location': ui.item.position, 'radius': '5000' }, function(results, status) {
							if ( status === 'OK' ) {
								$.each(results, function(i, item) {
									self.addMarker({ 'id': item.id, 'position': item.geometry.location, 'bounds':true }).click(function() {
										self.openInfoWindow({'content': '<h4>'+item.name+'</h4>'}, this);
									});
								});
							}
						});
					});
					$('#places-search').bind( "input.autocomplete", function(){ 
                        $(this).trigger('keydown.autocomplete'); 
                      })
					return $('#control')[0];
				});
				self.addControl(new control(), 1);
			}});
		}).load('places_1');
	});
	
	$('#places').live('pageshow', function() {
		demo.add('places_2', function() { $('#map_canvas_3').gmap('refresh'); }).load('places_2');
		$('#places-search').val('<%=(String)request.getParameter("place")%>');
        $('#map_canvas').gmap('search', { 'address': '<%=(String)request.getParameter("place")%>' }, function(results,isFound) {
            if (isFound) {
				$.each(results, function(i, item) {
				$('#map_canvas').gmap('addMarker', {'position': item.geometry.location, 'bounds': true}).click(function() {
						$('#map_canvas').gmap('openInfoWindow', {'content': '<h4>'+item.name+'</h4>'}, this);
					});
				});			
            }
        });		
	});
</script>  
</head> 
<body>
<div id="places" data-role="page">
<div data-role="header" data-add-back-btn="true" data-theme="b" data-position="inline">
	<a data-rel="back"><s:text name="link.back" /></a>
	<h1><s:text name="map.searchPlace.title"/></h1>
	<a href="../menu.jsp" data-icon="home" data-direction="reverse"><s:text name="link.menu" /></a>
</div>
<div data-role="content">
	<div class="ui-bar-c ui-corner-all ui-shadow" style="padding:1em;">
		<div id="map_canvas" style="height:300px;"></div>
	</div>   
</div>
<div data-role="footer">
    <h3>Copyright<span class="sign">&copy;</span><s:text name="copyright.info" /></h3>
</div>
</div>

</body>
</html>