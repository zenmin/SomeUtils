/**
 *  Description: String类的扩展方法集合
 */
String.prototype.Trim = function(){
    var m = this.match(/^\s*(\S+(\s+\S+)*)\s*$/);
    return (m == null) ? "" : m[1];
}

String.prototype.isMobile = function(){
    var isM = (/^(?:13\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/.test(this.Trim()));
    return isM;
}

String.prototype.isTel = function(){
    //"兼容格式: 国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"
    //return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/.test(this.Trim()));
    var isT = (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(this.Trim()));
    return isT;
}

String.prototype.isNum = function(){
    var isNum = (/^(\d{7,8})?$/.test(this.Trim()));
    return isNum;
}

String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
}

/**
 * 返回字符串的长度,支持中文字符串.
 * 对中文的计算方法是每个中文字符为2.
 * 例如: "hello".getLength() ==> 5
 *      "你好".getLength() ==> 4
 *      "hello你好".getLength() ==> 9
 * @return {int} - 字符串的长度
 */
String.prototype.getLength = function(){
    return this.replace(/[^\x00-\xff]/g, "**").length;
}

/**
 * 获得字符串显示长度, 默认14号字体
 */
String.prototype.visualLength = function(size) {
	if (!size) size = "14px";
	if (size == "") size = "14px";
	var oText = $("<span></span>");
	$(oText).css({
		fontSize:size,
		whiteSpace:"nowrap",
		visibility:"hidden"
	});
	oText.text(this);
	$("body").append(oText);
	var width = oText[0].offsetWidth;
	oText.empty();
	return width;
}

/**
 * 替换特殊字符为普通字符并返回替换后的结果字符串
 * 替换列表:
 * >   &gt;
 * <   &lt;
 * '   ''
 * &   &amp;
 * @param s 要替换的字符串
 * @return 返回替换后的字符串
 */
function replaceSpecialChar(s){
    if (s == null) {
        return;
    }
    var _text = s;
    var s_chars = [];
    s_chars[0] = ["&","&amp;"];
    s_chars[1] = [">","&gt;"];
    s_chars[2] = ["<","&lt;"];
    s_chars[3] = ["'","''"];
    
    for(var i = 0; i < s_chars.length; i++){
        var pattern = new RegExp(s_chars[i][0], "g");
        _text = _text.replace(pattern, s_chars[i][1]);
    }
    return _text;
}
/**
 * 还原特殊字符并返回替换后的结果字符串。
 * 注意：
 *    该函数不是replaceSpecialChar函数的绝对逆操作。
 *    如：var s = "5&gt;3"，替换特殊字符后，再替换回来，会变成5>3。
 * 转换列表:
 * &gt;   >
 * &lt;   <
 * ''     '
 * &amp;  &
 * @param s 要转化的特殊字符串
 * @return 返回转化后的字符串
 */
function reverseSpecialChar(s){
    if (s == null) {
        return;
    }
    
    var _text = s;
    
    var s_chars = [];
    s_chars[3] = ["'","''"];
    s_chars[2] = ["<","&lt;"];
    s_chars[1] = [">","&gt;"];
    s_chars[0] = ["&","&amp;"];
    
    for(var i = 0; i < s_chars.length; i++){
        var pattern = new RegExp(s_chars[i][1], "g");
        _text = _text.replace(pattern, s_chars[i][0]);
    }
    return _text;
}

