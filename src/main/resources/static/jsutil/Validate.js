/**
 * 该对象用来定义常用认证JS函数.
 */
function Validate() {
}

/**
 * 验证必填
 * 
 * @param {}
 *            oControl
 */
Validate.mand = function(oControl) {
	if (oControl.getValue() == "") {
		oControl.setValidate(false, "该数据项为必填项!");
	}
}

/**
 * 认证刑期录入
 * 
 * @param {}
 *            oControl
 */
Validate.term = function(oControl) {
	var iTerm = oControl.getValue();
	var sErrorText = "请输入正确的刑期格式:前两位表示年，中间两位表示月，后两位表示日。"
			+ "<br/>如：011005表示1年10个月5天。";
	if (isNaN(iTerm)) {
		oControl.setValidate(false, sErrorText);
		return;
	}
	if (iTerm.indexOf("-") != -1) {
		oControl.setValidate(false, "不能录入负数!");
		return;
	}
	while (iTerm.length < 6) {
		iTerm = "0" + iTerm;
	}
	if (iTerm.length > 6) {
		oControl.setValidate(false, sErrorText);
		return;
	}
	if ((iTerm - 0) > 900000) {
		oControl.setValidate(false, "不能大于90年！");
		return;
	}
	if (iTerm.substr(2, 2) - 0 > 11) {
		oControl.setValidate(false, "输入月数不能超过11个月!");
		return;
	}
	if (iTerm.substr(4, 2) - 0 > 30) {
		oControl.setValidate(false, "输入天数不能超过30天!");
		return;
	}
}

/**
 * 判断日期不能大于当前日期
 */
Validate.lessSysDate = function(oControl) {
    var sValue = oControl.getValue();
    if(!sValue)return;
    var sysdate = DateTime.getLocalDate();
    if(sysdate < sValue){
        oControl.setValidate(false, "不能选择大于当前的日期");
    }
}

/**
 * 判断日期不能小于当前日期
 */
Validate.moreSysDate = function(oControl) {
    var sValue = oControl.getValue();
    if(!sValue)return;
    var sysdate = DateTime.getLocalDate();
    if(sysdate > sValue){
        oControl.setValidate(false, "不能选择小于当前的日期");
    }
}

/**
 * 认证刑期录入
 * 
 * @param {}
 *            oControl
 */
Validate.termValue = function(oControl, value) {
	var iTerm = value;
	var sErrorText = "请输入正确的刑期格式:前两位表示年，中间两位表示月，后两位表示日。"
			+ "<br/>如：011005表示1年10个月5天。";
	if (isNaN(iTerm)) {
		oControl.setValidate(false, sErrorText);
		return false;
	}
	if (iTerm.indexOf("-") != -1) {
		oControl.setValidate(false, "不能录入负数!");
		return false;
	}
	while (iTerm.length < 6) {
		iTerm = "0" + iTerm;
	}
	if (iTerm.length > 6) {
		oControl.setValidate(false, sErrorText);
		return false;
	}
	if ((iTerm - 0) > 900000) {
		oControl.setValidate(false, "不能大于90年！");
		return false;
	}
	if (iTerm.substr(2, 2) - 0 > 11) {
		oControl.setValidate(false, "输入月数不能超过11个月!");
		return false;
	}
	if (iTerm.substr(4, 2) - 0 > 30) {
		oControl.setValidate(false, "输入天数不能超过30天!");
		return false;
	}
	return true;
}

Validate.len = function(oControl) {
	var sValue = oControl.getValue();
	var iMaxLength = parseInt(oControl.getAttribute().maxlength);
	if (sValue.length > iMaxLength) {
		oControl.setValidate(false, "最大文字长度不能超过 " + iMaxLength + " .");
	}
}

/**
 * 输入数字时验证不能超过某个值
 */
Validate.maxNum = function(oControl) {
	var sValue = oControl.getValue();
	if (isNaN(sValue)) {
		oControl.setValidate(false, "格式有误，请输入数字!");
	}
	var iMaxNum = parseInt(oControl.getAttribute().maxnum);
	if (sValue > iMaxNum) {
		oControl.setValidate(false, "数字最大不能超过 " + iMaxNum );
	}
}

Validate.phone = function(oControl) {
	var phone = oControl.getValue();
	var flag = oControl._attr.flag;
	var isPhone = /^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/;// 手机号码
	var isPhone2 = /^\d{3}-\d{8}|\d{4}-\d{7}$/;
	var isPhone3 = /^\d{7}$/;
	var isPhone4 = /^\d{8}$/;
	if (phone != null && phone != "") {
		if(flag == "cellphone"){
			if (!isPhone.test(phone)) {
				oControl.setValidate(false, "请输入正确的手机号码！");
			}
		}else if (phone.length == 11) {
			if (!isPhone.test(phone)) {
				oControl.setValidate(false, "请输入正确的11位手机号码！");
			}
		} else if (phone.length == 8) {
			if (!isPhone4.test(phone)) {
				oControl.setValidate(false, "请输入正确格式的电话号码");
			}
		} else if (phone.length < 7 || phone.length > 16) {
			oControl.setValidate(false, "电话号码不能小于7位或者大于13位！");
		} else if (phone.length == 7 && !isPhone3.test(phone)) {
			oControl.setValidate(false, "请输入正确格式的电话号码，"
					+ "如：①、0123-1234567；②、123-12345678；③、1234567");
		} else if (!isPhone2.test(phone) && phone.length != 7) {
			oControl.setValidate(false, "请输入正确格式的电话号码，"
					+ "如：①、0123-1234567；②、123-12345678；③、1234567");
		}
	}
}

/**
 * 数字认证
 * 
 * @param {Object}
 *            oControl
 */
Validate.mandNum = function(oControl) {
	var sValue = oControl.getValue();
	if (isNaN(sValue)) {
		oControl.setValidate(false, "格式有误，请输入数字!");
	}
}

/**
 * 只能输入自然数
 * @param {} oControl
 */
Validate.mandNumber = function(oControl) {
	var str = oControl.getValue();
	if (str == "") {
		return;
	}
	var reg = /^(0|\+?[1-9][0-9]*)$/;
	if (!reg.test(str)) {
		oControl.setValidate(false, "请输入自然数！");
	}
}
/**
 * 只能输入自然数
 * @param {} oControl
 */
Validate.mandYear = function(oControl) {
    var str = oControl.getValue();
    if (str == "") {
        return;
    }
    var reg = /^\d{4}$/;
    if (!reg.test(str)) {
        oControl.setValidate(false, "请输入4位年格式！");
    }
}
/**
 * 只能输入正数
 * @param {} oControl
 */
Validate.positiveNum = function(oControl) {
	var value = oControl.getValue();
	if (isNaN(value)||value<=0) {
		oControl.setValidate(false, "格式有误，请输入正数!");
	}
}
/**
 * 非负数
 * @param oControl
 */
Validate.notNegative= function(oControl) {
	var value = oControl.getValue();
	if(""==value){
		return
	}
	if (isNaN(value)||value<0) {
		oControl.setValidate(false, "格式有误，不能输入负数!");
	}
}
/**
 * 车牌号验证
 */
Validate.checkCarID = function(oControl){
	var carID =  oControl.getValue();
	var reg=/^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
	if(!reg.test(carID)) {
		oControl.setValidate(false, "车牌号格式有误(注:字母需大写)，请校验！");
	}
}
/**
 * 比例限制，不能大于100
 * @param oControl
 */
Validate.proportionNum = function(oControl){
	var value = oControl.getValue();
	if(value>100){
		oControl.setValidate(false,"数字不能大于100！");
	}
}

/**
 * 只能输入正整数
 * @param {} oControl
 */
Validate.mandInt = function(oControl) {
	var str = oControl.getValue();
	if (str == "") {
		return;
	}
	var reg = /^[0-9]*[1-9][0-9]*$/;
	if (!reg.test(str)) {
		oControl.setValidate(false, "请输入正整数！");
	}
}

/**
 * 年龄验证
 */
Validate.mandAge = function(oControl) {
	var value = oControl.getValue();
	if (value == "") {
		return;
	}
	var age = getAgeByDateOfBorth(value);
	if (age < 16) {
		oControl.setValidate(false, "年龄不能小于16岁!");
	}
}

/**
 * 检验身份证是否合法规范new
 */
Validate.checkID = function(oControl) {
	var idcard = oControl.getValue();
	if (idcard == "") {
		return;
	}
	var errors = new Array("验证通过。", "身份证号码位数不对！", "身份证含有非法字符！", "身份证号码校验错误！",
			"身份证地区非法！");
	// 身份号码位数及格式检验
	var re;
	var len = idcard.length;
	// 身份证位数检验
	if (len != 15 && len != 18) {
		oControl.setValidate(false, errors[1]);
		return false;
	} else if (len == 15) {
		re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);
	} else {
		re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})([0-9xX])$/);
	}

	var area = {
		11 : "北京",
		12 : "天津",
		13 : "河北",
		14 : "山西",
		15 : "内蒙古",
		21 : "辽宁",
		22 : "吉林",
		23 : "黑龙江",
		31 : "上海",
		32 : "江苏",
		33 : "浙江",
		34 : "安徽",
		35 : "福建",
		36 : "江西",
		37 : "山东",
		41 : "河南",
		42 : "湖北",
		43 : "湖南",
		44 : "广东",
		45 : "广西",
		46 : "海南",
		50 : "重庆",
		51 : "四川",
		52 : "贵州",
		53 : "云南",
		54 : "西藏",
		61 : "陕西",
		62 : "甘肃",
		63 : "青海",
		64 : "宁夏",
		65 : "新疆",
		71 : "台湾",
		81 : "香港",
		82 : "澳门",
		91 : "国外"
	}

	var idcard_array = new Array();
	idcard_array = idcard.split("");

	// 地区检验
	if (area[parseInt(idcard.substr(0, 2))] == null) {
		oControl.setValidate(false, errors[4]);
		return false;
	}

	// 出生日期正确性检验
	var a = idcard.match(re);

	if (a != null) {
		if (len == 15) {
			var DD = new Date("19" + a[3] + "/" + a[4] + "/" + a[5]);
			var flag = DD.getYear() == a[3] && (DD.getMonth() + 1) == a[4]
					&& DD.getDate() == a[5];
		} else if (len == 18) {
			var DD = new Date(a[3] + "/" + a[4] + "/" + a[5]);
			var flag = DD.getFullYear() == a[3] && (DD.getMonth() + 1) == a[4]
					&& DD.getDate() == a[5];
		}

		if (!flag) {
			oControl.setValidate(false, "身份证出生日期不对!");
			return false;
		}

		// 检验校验位
		if (len == 18) {
			S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
					+ (parseInt(idcard_array[1]) + parseInt(idcard_array[11]))
					* 9
					+ (parseInt(idcard_array[2]) + parseInt(idcard_array[12]))
					* 10
					+ (parseInt(idcard_array[3]) + parseInt(idcard_array[13]))
					* 5
					+ (parseInt(idcard_array[4]) + parseInt(idcard_array[14]))
					* 8
					+ (parseInt(idcard_array[5]) + parseInt(idcard_array[15]))
					* 4
					+ (parseInt(idcard_array[6]) + parseInt(idcard_array[16]))
					* 2 + parseInt(idcard_array[7]) * 1
					+ parseInt(idcard_array[8]) * 6 + parseInt(idcard_array[9])
					* 3;

			Y = S % 11;
			M = "F";
			JYM = "10X98765432";
			M = JYM.substr(Y, 1);// 判断校验位

			// 检测ID的校验位
			if (M == idcard_array[17].toUpperCase()) {
				return true;
			} else {
				oControl.setValidate(false, errors[3]);
				return false;
			}
		}

	} else {
		oControl.setValidate(false, errors[2]);
		return false;
	}
	return true;
}

/**
 * 验证是否是金额（可以输入0）
 */
Validate.isMoney = function(oControl) {
	var str = oControl.getValue();
	if (str == "") {
		return;
	}
	//var reg = /^[1-9]{1}\d*(\.\d{1,2})?$/;
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	if (!reg.test(str)) {
		oControl.setValidate(false, "请输入正确的金额格式!");
	}
}

// 身高判断
Validate.validateHeight = function(oControl) {
	var str = oControl.getValue();
	if (str == "") {
		return;
	}
	var reg = /^[1-9]{1}\d*(\.\d{1,2})?$/;
	if (!reg.test(str)) {
		oControl.setValidate(false, "!请输入正确的身高");
	}

	var height = parseFloat(str);
	if (height < 0.0 || height > 249.99) {
		oControl.setValidate(false, "!请输入正确的身高");
	}
}

// 身高判断
Validate.validateWeight = function(oControl) {
	var str = oControl.getValue();
	if (str == "") {
		return;
	}
	var reg = /^[1-9]{1}\d*(\.\d{1,2})?$/;
	if (!reg.test(str)) {
		oControl.setValidate(false, "!请输入正确的身高");
	}

}

/**
 * 根据出生日期计算年龄
 */
function getAgeByDateOfBorth(dateOfBorth) {
	var r = dateOfBorth.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
	if (r == null)
		return false;
	var d = new Date(r[1], r[3] - 1, r[4]);
	if (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3]
			&& d.getDate() == r[4]) {
		var Y = new Date().getFullYear();
		return ((Y - r[1]));
	}
	return ("输入的日期格式错误！");
}

/**
 * 验证密码的复杂度:
 * 			文本长度大于6
 * 			字母数字组合
 */
Validate.passComplexity = function(oControl) {
	var str = oControl.getValue();
	if (str == "") {
		return;
	}
	var reg = /^(?![^a-zA-Z]+$)(?!\D+$).{6,50}$/;
	if (!reg.test(str)) {
		oControl.setValidate(false, "!密码必须有字母和数字且长度必须大于6位");
	}
}
/**
 * 验证年份:
 * 			文本长度4
 */
Validate.isYear = function(oControl) {
	var str = oControl.getValue();
	if (str == "") {
		return;
	}
	var reg = /^\d{4}$/;
	if (!reg.test(str)) {
		oControl.setValidate(false, "请输入正确的4位年份!");
	}
}