
function getWebPath(){
    var strFullPath=window.document.location.href;
    var strPath=window.document.location.pathname;
    var pos=strFullPath.indexOf(strPath);
    var prePath=strFullPath.substring(0,pos);
    var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
    return (prePath+postPath);
 }


function openPage(href) {
	href = getWebPath() + "/jsp" + href;
	window.open(href, "_self");
}

function openPageInNewWindow(href) {
	href = getWebPath() + "/jsp" + href;
	window.open(href, "_blank");
}

function openPopupPage(href) {
	href = getWebPath() + "/jsp" + href
	window.open(href, "_blank",
			"height=300,width=500,scrollbars=no,location=no");
}

function openwindow(url) {
	openwindow2(url,500,300);
}

function openwindow2(url, iWidth,iHeight) {
	var url;
	var name;
	var iTop = (window.screen.height-iHeight)/2;
	var iLeft = (window.screen.width-iWidth)/2;
	url = getWebPath() + "/jsp" + url;
	window
			.open(
					url,
					'',
					'height='
							+ iHeight
							+ ',,innerHeight='
							+ iHeight
							+ ',width='
							+ iWidth
							+ ',innerWidth='
							+ iWidth
							+ ',top='
							+ iTop
							+ ',left='
							+ iLeft
							+ ',toolbar=no,menubar=no,scrollbars=no,resizeable=no,location=no,status=no');
}

function disableBtn(){
	$('#save_accept_btn').linkbutton('disable');
	$('#save_go_btn').linkbutton('disable');
	$('#cancel_btn').linkbutton('disable');
}

function baseSave(name){
	disableBtn();
	var addObjectForm = document.getElementById('addObjectForm');
	if ($("#seleteIDs").val()!= ""){
	   addObjectForm.action = 'massUpdate' + name + '.action';
	}else{
	   addObjectForm.action = 'save' + name + '.action';
	}		
	addObjectForm.submit();
}

function baseSaveClose(name){
	disableBtn();
	var addObjectForm = document.getElementById('addObjectForm');
	if ($("#seleteIDs").val()!= ""){
	   addObjectForm.action = 'massUpdateClose' + name + '.action';
	}else{
	   addObjectForm.action = 'saveClose' + name + '.action';
	}		
	addObjectForm.submit();
}

function baseCancel(name){
	disableBtn();
	var addObjectForm = document.getElementById('addObjectForm');
	addObjectForm.action = 'list' + name + 'Page.action';
	addObjectForm.submit();
}
 

