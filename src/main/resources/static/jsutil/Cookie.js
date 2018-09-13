/**
 *负责cookie的相关操作
 */
function Cookie(){
}
/**
 *获取指定名称的cookie
 */
Cookie.getCookie=function(sName) {
	var arg = sName + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while (i < clen) {
		var j = i + alen;
		if (document.cookie.substring(i, j) == arg)
			return Cookie.getCookieVal (j);
		i = document.cookie.indexOf(" ", i) + 1;
		if (i == 0) break;
	}
	return null;
}
/**
 *保存cookie
 */
Cookie.setCookie = function(sName, sValue) {
	var argv = Cookie.setCookie.arguments;
	var argc = Cookie.setCookie.arguments.length;
	var expires = (argc > 2) ? argv[2] : null;
	var path = (argc > 3) ? argv[3] : null;
	var domain = (argc > 4) ? argv[4] : null;
	var secure = (argc > 5) ? argv[5] : false;
	document.cookie = sName + "=" + escape (sValue) +
	((expires == null) ? "" : ("; expires=" + expires.toString())) +
	((path == null) ? ";path=/" : ("; path=" + path)) +
	((domain == null) ? "" : ("; domain=" + domain)) +
	((secure == true) ? "; secure" : "");
}
/**
 *删除cookie
 */
Cookie.deleteCookie=function(sName) {
	var sValue = Cookie.getCookie (sName);	
	var exp=new Date();
	exp.setTime(exp.getTime()-1);
	var sCookie=sName+"="+sValue+";path=/;expires="+exp.toGMTString();
	document.cookie=sCookie;
}
/**
 *清除cookie
 */
Cookie.clearCookie=function(){
	var oCookieList=document.cookie.split(";");
	for(var i=0;i<oCookieList.length;i++){
		Cookie.deleteCookie(oCookieList[i].split("=")[0]);
	}
}
/**
 *获取cookie的值
 */
Cookie.getCookieVal=function(offset) {
	var endstr = document.cookie.indexOf (";", offset);
	if (endstr == -1)
	endstr = document.cookie.length;
	return unescape(document.cookie.substring(offset, endstr));
}
