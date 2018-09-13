/**
 * 该对象用来定义常用认证JS函数.
 */
function Format() {
}

/**
 * 刑期类型初始化事件
 */
Format.OnInitImprisonmentTerm = function(oControl, value, oUnit, oData) {
	var xq = value;
	var rs = Format.formatDate(xq);
	return rs;
}

/**
 * 格式化刑期显示
 */
Format.formatDate = function(sDate) {
	if (sDate == null) {
		return "";
	}
	while (sDate.length < 6) {
		sDate = "0" + sDate;
	};
	var sDay = sDate.substr(4, 2) - 0;
	if (sDay == 0) {
		sDay = "";
	} else {
		sDay = sDay + "天";
	};
	var sMon = sDate.substr(2, 2) - 0;
	if (sMon == 0) {
		sMon = "";
	} else {
		sMon = sMon + "个月";
	};
	var sYear = sDate.substr(0, 2) - 0;
	if (sYear == 0) {
		sYear = "";
	} else {
		sYear = sYear + "年";
	};
	var rs = sYear + sMon + sDay;
	if (rs == "")
		rs = "";
	return rs;
}

/**
 * 将剩余时间转换为字符串. 如: 2 * 60 * 1000 为 2分
 * 
 * @param time
 * @param bsimple
 *            是否简写
 */
Format.leaveTimeToString = function(time, bsimple) {
	var daynum = 24 * 60 * 60 * 1000;
	var hournum = 60 * 60 * 1000;
	var minutenum = 60 * 1000;
	var day, hour, minute;
	var ltime = time;
	if (ltime >= daynum) {
		day = parseInt(ltime / daynum);
		ltime = ltime - day * daynum;
	}
	if (ltime >= hournum) {
		hour = parseInt(ltime / hournum);
		ltime = ltime - hour * hournum;
	}
	if (ltime >= minutenum) {
		minute = parseInt(ltime / minutenum);
	}
	var sValue = "";
	if (bsimple) {
		if (typeof(day) != "undefined")
			return (day + "天");
		if (typeof(hour) != "undefined")
			return (hour + "小时");
		if (typeof(minute) != "undefined")
			return (minute + "分钟");
	} else {
		if (typeof(day) != "undefined")
			sValue += (day + "天");
		if (typeof(hour) != "undefined")
			sValue += (hour + "小时");
		if (typeof(minute) != "undefined")
			sValue += (minute + "分钟");
	}
	return sValue;
}

/**
 * 和当前时间比较, 当天返回时间点，1 - n天前，1-n天后,
 * 
 * @param time
 *            timestamp 时间戳
 */
Format.getSimpleTime = function(time) {
	var now = DateTime.getDateTimeDate().getTime();
	var daydiff = DateTime.DateDiff("d", DateTime.GetDateString(new Date(now)),
			DateTime.GetDateString(new Date(time)));
	var be = "";
	var num = 0;
	if (daydiff == 0) {
		return DateTime.getHMTimeString(new Date(time));
	} else if (daydiff < 0) {
		//return Math.abs(daydiff) + "天前";
		be = "前";
	} else if (daydiff > 0) {
		be = "后";
		//return daydiff + "天后";
	}
	daydiff = Math.abs(daydiff);
	if (daydiff > 30 && daydiff < 365) {
		return parseInt(daydiff/30) + "月" + be;
	} else if (daydiff >= 365) {
		return parseInt(daydiff/365) + "年" + be;
	} else {
		return daydiff + "天" + be;
	}
}


/**
 * 根据出生日期计算年龄
 * 
 * @param {Object}
 *            brith 出生日期 return 年龄
 */
Format.getAge = function(birth) {
	var age;
	var aDate = new Date();
	var thisYear = aDate.getFullYear();
	var thisMonth = aDate.getMonth() + 1;
	var thisDay = aDate.getDate();
	var birthDate = birth.split("-");
	brithy = birthDate[0] - 0;
	brithm = birthDate[1] - 0;
	brithd = birthDate[2] - 0;
	if (thisYear - brithy < 0) {
		alert("输入错误!");
		age = 0;
	} else {
		if (thisMonth - brithm < 0) {
			age = thisYear - brithy - 1;
		} else if (thisMonth - brithm == 0) {
			if (thisDay - brithd >= 0) {// alert(thisDay+'-'+brithd+"_ddd");
				age = thisYear - brithy;
			} else {
				age = thisYear - brithy - 1;
			}
		} else {
			age = thisYear - brithy;
		}
	}
	return age;
}

/**
 * 将字符串转换为日期
 * 
 * @param oControl
 * @param value
 * @param oUnit
 * @param oData
 * @returns
 */
Format.getCNDate = function(oControl, value, oUnit, oData) {
	if (!value || value == "")
		return "";
	return DateTime.getCNString(value);
}

/**
 * 初始化意见签名
 * 
 * @param oControl
 * @param value
 * @param oUnit
 * @param oData
 */
Format.signName = function(oControl, value, oUnit, oData) {
	if (!value || value == "")
		return "";
	return "签字：" + value;
}

/**
 * 初始化签名日期
 * 
 * @param oControl
 * @param value
 * @param oUnit
 * @param oData
 * @returns {String}
 */
Format.signDate = function(oControl, value, oUnit, oData) {
	if (!value || value == "")
		return "";
	return "日期：" + DateTime.getCNString(value);
}

/*
 * 功能:实现VBScript的DateAdd功能. 参数:interval,字符串表达式，表示要添加的时间间隔.
 * 参数:number,数值表达式，表示要添加的时间间隔的个数. 参数:date,时间对象. 返回:新的时间对象. var now = new Date();
 * var newDate = DateAdd( "d", 5, now); ---------------
 * DateAdd(interval,number,date) -----------------
 */
Format.DateAdd = function(interval, number, date) {
	var date = new Date(date[0] + "/" + date[1] + "/" + date[2]);
	switch (interval) {
		case "y" : {
			date.setFullYear(date.getFullYear() + number);
			return date;
			break;
		}
		case "q" : {
			date.setMonth(date.getMonth() + number * 3);
			return date;
			break;
		}
		case "m" : {
			date.setMonth(date.getMonth() + number);
			return date;
			break;
		}
		case "w" : {
			date.setDate(date.getDate() + number * 7);
			return date;
			break;
		}
		case "d" : {
			date.setDate(date.getDate() + number);
			return date;
			break;
		}
		case "h" : {
			date.setHours(date.getHours() + number);
			return date;
			break;
		}
		case "m" : {
			date.setMinutes(date.getMinutes() + number);
			return date;
			break;
		}
		case "s" : {
			date.setSeconds(date.getSeconds() + number);
			return date;
			break;
		}
		default : {
			date.setDate(date.getDate() + number);
			return date;
			break;
		}
	}
}

Format.addTerm = function(term, beginDate) {
	date = beginDate.split("-");
	var termlength = term.length;
	var addDay = term.substr(termlength - 2, 2);
	var addMonth = null;
	var addYear = null;
	if (termlength == 3)
		addMonth = term.substr(termlength - 3, 1);
	if (termlength >= 4)
		addMonth = term.substr(termlength - 4, 2);
	if (termlength == 5)
		addYear = term.substr(0, 1);
	if (termlength == 6)
		addYear = term.substr(0, 2);
	date = this.DateAdd("d", parseInt(addDay), date);
	if (addMonth)
		date = this.DateAdd("m", parseInt(addMonth), this.getDate(date)
						.split("-"));
	if (addYear)
		date = this.DateAdd("y", parseInt(addYear), this.getDate(date)
						.split("-"));
	date = this.DateAdd("d", -1, this.getDate(date).split("-"));//需要在计算出的时间-1天
	return this.getDate(date);

}

Format.getDate = function(date) {
    var sTempM = date.getMonth() + 1;
    if (sTempM < 10)
        sTempM = "0" + sTempM;
    var sTempD = date.getDate();
    if (sTempD < 10)
        sTempD = "0" + sTempD;
    return date.getFullYear() + "-" + sTempM + "-" + sTempD;
}

Format.initIOTPDate = function(oControl, value, oUnit, oData) {
	return DateTime.GetAddDay("1970-01-01", value);
}


/**
 * 保留小数点后几位
 * @param num
 * @param precision 位数
 * @returns {*}
 */
Format.numFix = function(num, precision) {
    if (num == null){
        return null;
    }
    if (!isNaN(num)){
        num = num.toFixed(precision);
    }
    return num;
}
