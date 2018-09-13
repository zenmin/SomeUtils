
/**
 * constructor
 */
function DateTime() {
}

/**
 * return a string representing the date of Date type argument, format:
 * yyyy-mm-dd
 */
DateTime.getDateString = function(dateval) {
	var sTempM = dateval.getMonth() + 1;
	if (sTempM < 10)
		sTempM = "0" + sTempM;
	var sTempD = dateval.getDate();
	if (sTempD < 10)
		sTempD = "0" + sTempD;
	return dateval.getFullYear() + "-" + sTempM + "-" + sTempD;

}

/**
 * return a string representing the date of Date type argument, format:
 * dd 
 */
DateTime.getDateStringDay = function(dateval) {
	var sTempM = dateval.getMonth() + 1;
	if (sTempM < 10)
		sTempM = "0" + sTempM;
	var sTempD = dateval.getDate();
	return sTempD;
}

/**
 * return a string representing the month of Date type argument, format:
 * yyyy-mm
 * @param dateval
 * @returns {string}
 */
DateTime.getDateMonth = function(dateval) {
    var sTempM = dateval.getMonth() + 1;
    if (sTempM < 10)
        sTempM = "0" + sTempM;
    return dateval.getFullYear() + "-" + sTempM;
}

/**
 * return a string representing the time of Date type argument, format: hh:mm
 */
DateTime.getHMTimeString = function(dateval) {
	var sTemp = dateval.getHours();
	if (sTemp < 10)
		sTemp = "0" + sTemp;
	var sTempM = dateval.getMinutes();
	if (sTempM < 10)
		sTempM = "0" + sTempM;
	return sTemp + ":" + sTempM;
}

DateTime.getDateTimeString = function(dateval) {
	return DateTime.getDateString(dateval) + " "
			+ DateTime.getHMTimeString(dateval);
}

DateTime.getDateTimeSecondsString = function(dateval) {
	return DateTime.getDateString(dateval) + " "
		+ DateTime.getHMSTimeString(dateval);
}

/**
 * return a string representing the time of Date type argument, format: hh:mm:ss
 */
DateTime.getHMSTimeString = function(dateval) {
	var sTemp = dateval.getSeconds();
	if (sTemp < 10)
		sTemp = "0" + sTemp;
	return DateTime.getHMTimeString(dateval) + ":" + sTemp;
}

DateTime.GetDateString = function(dateval) {
	var sTempM = dateval.getMonth() + 1;
	if (sTempM < 10)
		sTempM = "0" + sTempM;
	var sTempD = dateval.getDate();
	if (sTempD < 10)
		sTempD = "0" + sTempD;
	return dateval.getFullYear() + "-" + sTempM + "-" + sTempD;

}

/**
 * 对时间进行加减运算
 * @param dateval
 * @param seconds
 * @returns {string}
 */
DateTime.getAddSeconds = function (dateval,seconds) {
	var c_date = new Date(dateval);
	if (c_date.getFullYear() < 1900 || c_date.getFullYear() > 2099) {
		alert("Error year!");
		return;
	}
	var r_date = new Date(c_date.getTime() + seconds * 1000);

	if (r_date.getFullYear() < 1900 || r_date.getFullYear() > 2099) {
		alert("Error year!");
		return;
	}

	return DateTime.GetDateString(r_date) + " " + DateTime.getHMSTimeString(r_date);
}

DateTime.GetAddDay = function(AStartDate, Day) {
	// assert(0 == AStartDate.search(/^\d{4}\-\d{2}\-\d{2}$/),
	// "日期必须为yyyy-mm-dd的格式");

	var c_date = DateTime.StringToDate(AStartDate);
	if (c_date.getFullYear() < 1900 || c_date.getFullYear() > 2099) {
		alert("Error year!");
		return;
	}

	var r_date = new Date(c_date.getTime() + (Day - 0) * 24 * 3600 * 1000);

	if (r_date.getFullYear() < 1900 || r_date.getFullYear() > 2099) {
		alert("Error year!");
		return;
	}
	var s_y = r_date.getFullYear();

	var tmp = r_date.getMonth() + 1;
	var s_m = tmp + "";
	if (tmp < 10) {
		s_m = "0" + s_m;
	}

	var tmp = r_date.getDate();
	var s_d = tmp + "";
	if (tmp < 10) {
		s_d = "0" + s_d;
	}

	return s_y + "-" + s_m + "-" + s_d;
}

DateTime.GetAddDayTime = function (AStartDate, Day) {
	var c_date = DateTime.StringToDate(AStartDate);
	return DateTime.GetAddDay(AStartDate, Day) + " " + DateTime.getHMSTimeString(c_date);
}

DateTime.GetAddMonthTime = function (AStartDate, Month) {
	var c_date = DateTime.StringToDate(AStartDate);
	return DateTime.GetAddDay(AStartDate, Month) + " " + DateTime.getHMSTimeString(c_date);
}

DateTime.GetAddMonth = function(AStartDate, Month) {
	// assert(0 == AStartDate.search(/^\d{4}\-\d{2}\-\d{2}$/),
	// "日期必须为yyyy-mm-dd的格式");

	/* 计算月份 */
	var endMonth = ((AStartDate.substring(5, 7) - 1) + (Month - 0)) % 12 + 1;
	var s_m = endMonth;
	if (s_m <= 0) {
		s_m = 12 + s_m
	}
	if (s_m < 10) {
		s_m = "0" + s_m;
	}
	endMonth = s_m;
	s_m = s_m + "";
	/* 计算年份 */
	var endYear = (AStartDate.substring(0, 4) - 0) + parseInt((Month - 0) / 12);
	if ((AStartDate.substring(5, 7) - 0) + (Month - 0) % 12 > 12) {
		endYear++;
	} else if ((AStartDate.substring(5, 7) - 0) + (Month - 0) % 12 <= 0) {
		endYear--;
	}
	var s_y = endYear + "";

	/* 计算日期 */
	if ((AStartDate.substring(8, 10) - 0) == DateTime.GetEom(AStartDate
					.substring(0, 4), AStartDate.substring(5, 7))) {
		var BeginDateIsTheEOM = true;
	} else {
		var BeginDateIsTheEOM = false;
	}
	if (BeginDateIsTheEOM) {
		var endDay = DateTime.GetEom(endYear, endMonth);
	} else {
		if (DateTime.DayExist(s_y + "-" + s_m + "-"
				+ AStartDate.substring(8, 10))) {
			var endDay = AStartDate.substring(8, 10) - 0;
		} else {
			var endDay = DateTime.GetEom(endYear, endMonth);
		}
	}

	var s_d = endDay + "";
	if (endDay < 10) {
		s_d = "0" + s_d;
	}

	return s_y + "-" + s_m + "-" + s_d;
}

DateTime.GetAddYear = function(AStartDate, Year) {
	// assert(0 == AStartDate.search(/^\d{4}\-\d{2}\-\d{2}$/),
	// "日期必须为yyyy-mm-dd的格式");

	var endYear = (AStartDate.substring(0, 4) - 0) + (Year - 0);
	var s_endYear = endYear + "";
	var s_endDate = s_endYear + AStartDate.substring(4, 10);

	if ("02-29" == s_endDate.substring(5, 10)
			&& !DateTime.IsLeapYear(s_endDate.substring(0, 4))) {
		s_endDate = s_endDate.substring(0, 5) + "02-28";
	}

	return s_endDate;
}

DateTime.GetPrevDay = function(sDate) {
	//assert(0 == sDate.search(/^\d{4}\-\d{2}\-\d{2}$/), "日期必须为yyyy-mm-dd的格式");

	if (!DateTime.DayExist(sDate)) {
		return;
	}

	var d = DateTime.StringToDate(sDate);
	d.setTime(d.getTime() - 24 * 3600 * 1000);

	return DateTime.GetDateString(d);
}

DateTime.GetNextDay = function(sDate) {
	//assert(0 == sDate.search(/^\d{4}\-\d{2}\-\d{2}$/), "日期必须为yyyy-mm-dd的格式");

	if (!DateTime.DayExist(sDate)) {
		return;
	}

	var d = DateTime.StringToDate(sDate);
	d.setTime(d.getTime() + 24 * 3600 * 1000);

	return DateTime.GetDateString(d);
}

DateTime.GetEom = function(Year, Month) {
	var result;
	var i_Month = Month - 0;

	switch (i_Month) {
		case 1 :
		case 3 :
		case 5 :
		case 7 :
		case 8 :
		case 10 :
		case 12 :
			result = 31;
			break;
		case 4 :
		case 6 :
		case 9 :
		case 11 :
			result = 30;
			break;
		case 2 :
			if (DateTime.IsLeapYear(Year)) {
				result = 29;
			} else {
				result = 28;
			}
			break;
		default :
	}

	return result;
}

DateTime.IsLeapYear = function(Year) {

	var i_Year = Year - 0;

	if (i_Year % 4 != 0) {
		return false;
	} else if (0 == i_Year % 400) {
		return true;
	} else if (0 == i_Year % 100) {
		return false;
	} else {
		return true;
	}
} /* IsLeapYear */

DateTime.DayExist = function(TestDate) {
	//assert(0 == TestDate.search(/^\d{4}\-\d{2}\-\d{2}$/), "日期必须为yyyy-mm-dd的格式");

	var t_Date = DateTime.StringToDate(TestDate);

	if (DateTime.GetDateString(t_Date) == TestDate) {
		return true;
	} else {
		return false;
	}
} /* DayExists */

DateTime.StringToDate = function(sDate) {
	// assert(0 == sDate.search(/^\d{4}\-\d{2}\-\d{2}$/), "日期必须为yyyy-mm-dd的格式");
	if (sDate.length == 10) {
		var result = new Date(sDate.substring(0, 4) - 0, sDate.substring(5, 7)
						- 1, sDate.substring(8, 10) - 0);
	} else if (sDate.length == 19){
		var result = new Date(sDate.substring(0, 4) - 0, sDate.substring(5, 7)
			- 1, sDate.substring(8, 10) - 0, sDate.substring(11, 13)
			- 0, sDate.substring(14, 16) - 0, sDate.substring(17, 19) - 0);
	} else {
		var result = new Date(sDate.substring(0, 4) - 0, sDate.substring(5, 7)
			- 1, sDate.substring(8, 10) - 0, sDate.substring(11, 13)
			- 0, sDate.substring(14, 16) - 0);
	}
	return result;
} /* StringToDate */

/**
 * 得到一个日期的中文字符串
 */
DateTime.getCNString = function(sDate) {
	if(!sDate){
		return "";
	}
	return sDate.substring(0, 4) - 0 + "年" + sDate.substring(5, 7) + "月"
			+ sDate.substring(8, 10) + "日";
}

/**
 * 提供前台js获得后台当前日期,格式是:2006-09-22
 * 
 * @param format
 *            可自定义日期格式
 */
DateTime.getDate = function(format) {
	var soap = new HWSOAP("/login/date.do");
	soap.send({
				format : format
			});
	var result = soap.getResult()
	return result.date;
}

/**
 * 提供前台js获得后台当前日期前一天,格式是:2006-09-22
 * 
 * @param format
 *            可自定义日期格式
 */
DateTime.getYesterday = function(format, days) {
	var soap = new HWSOAP("/login/yesterday.do");
	soap.send({
				format : format,
				days : days
			});
	var result = soap.getResult()
	return result.date;
}

/**
 * 提供前台js获得后台当前日期,格式是:yyyy-MM-dd HH:mm:ss
 * 
 * @param format
 *            可自定义日期格式
 */
DateTime.getDateTime = function(format,callBack) {
	var soap = new HWSOAP("/login/datetime.do");
	if(callBack){
		debugger;
		soap.send({format : format},true,function (oResult) {
			callBack(oResult.date);
		})
	}else{
		soap.send({
			format : format
		});
		var result = soap.getResult()
		return result.date;
	}
	return null;
}


/**
 * 提供前台js获得后台当前日期,格式是:2006-09-22 hh:mm
 * 
 * @param format
 *            可自定义日期格式
 */
DateTime.getDateTime2 = function(format) {
	var time = DateTime.getDateTime();
	return time.substring(0, time.length - 3);
}

/**
 * 获得当前时间, 返回jS日期对象
 */
DateTime.getDateTimeDate = function() {
	return DateTime.StringToDate(DateTime.getDateTime());
}

// +---------------------------------------------------
// | 求两个时间的天数差 日期格式为 YYYY-MM-dd
// +---------------------------------------------------
DateTime.daysBetween = function(DateOne, DateTwo) {
	var OneMonth = DateOne.substring(5, DateOne.lastIndexOf('-'));
	var OneDay = DateOne
			.substring(DateOne.length, DateOne.lastIndexOf('-') + 1);
	var OneYear = DateOne.substring(0, DateOne.indexOf('-'));

	var TwoMonth = DateTwo.substring(5, DateTwo.lastIndexOf('-'));
	var TwoDay = DateTwo
			.substring(DateTwo.length, DateTwo.lastIndexOf('-') + 1);
	var TwoYear = DateTwo.substring(0, DateTwo.indexOf('-'));

	var cha = ((Date.parse(OneMonth + '/' + OneDay + '/' + OneYear) - Date
			.parse(TwoMonth + '/' + TwoDay + '/' + TwoYear)) / 86400000);
	return Math.abs(cha);
}

// +---------------------------------------------------
// | 比较日期差 dtEnd 格式为日期型或者 有效日期格式字符串
// +---------------------------------------------------
DateTime.DateDiff = function(strInterval, dtStart, dtEnd) {
	if (typeof dtStart == 'string')// 如果是字符串转换为日期型
	{
		dtStart = DateTime.StringToDate(dtStart);
	}
	if (typeof dtEnd == 'string')// 如果是字符串转换为日期型
	{
		dtEnd = DateTime.StringToDate(dtEnd);
	}
	switch (strInterval) {
		case 's' :
			return parseInt((dtEnd - dtStart) / 1000);
		case 'n' :
			return parseInt((dtEnd - dtStart) / 60000);
		case 'h' :
			return parseInt((dtEnd - dtStart) / 3600000);
		case 'd' :
			return parseInt((dtEnd - dtStart) / 86400000);
		case 'w' :
			return parseInt((dtEnd - dtStart) / (86400000 * 7));
		case 'm' :
			return (dtEnd.getMonth() + 1)
					+ ((dtEnd.getFullYear() - dtStart.getFullYear()) * 12)
					- (dtStart.getMonth() + 1);
		case 'y' :
			return dtEnd.getFullYear() - dtStart.getFullYear();
	}
}
// 获取本地时间
DateTime.getLocalDateTime = function() {
	var o = new Date();
	return o.getFullYear()
			+ "-"
			+ ((o.getMonth() + 1) < 10 ? ("0" + (o.getMonth() + 1)) : (o
					.getMonth() + 1)) + "-"
			+ (o.getDate() < 10 ? ("0" + o.getDate()) : o.getDate()) + " "
			+ (o.getHours() < 10 ? ("0" + o.getHours()) : o.getHours()) + ":"
			+ (o.getMinutes() < 10 ? ("0" + o.getMinutes()) : o.getMinutes())
			+ ":"
			+ (o.getSeconds() < 10 ? ("0" + o.getSeconds()) : o.getSeconds());
}
// 获取本地日期
DateTime.getLocalDate = function() {
	var o = new Date();
	return o.getFullYear()
			+ "-"
			+ ((o.getMonth() + 1) < 10 ? ("0" + (o.getMonth() + 1)) : (o
					.getMonth() + 1)) + "-"
			+ (o.getDate() < 10 ? ("0" + o.getDate()) : o.getDate());
}
// 获取本地日期
DateTime.getStrDateByDate = function(o) {
    return o.getFullYear()
        + "-"
        + ((o.getMonth() + 1) < 10 ? ("0" + (o.getMonth() + 1)) : (o
            .getMonth() + 1)) + "-"
        + (o.getDate() < 10 ? ("0" + o.getDate()) : o.getDate());
}

DateTime.getBirthdayByBorn = function(born) {
	var tmpStr = born.substring(0, 4) + "-" + born.substring(4, 6) + "-"
			+ born.substring(6);
	return tmpStr;
}
/**
 * 根据日期获取是星期几
 */
DateTime.getWeekDayOfDate = function(date) {
	if (!date)
		return;
	var weeks = new Array("日", "一", "二", "三", "四", "五", "六");
	return weeks[date.getDay()];
}


/**
 * 判断时间是否为今天
 * @param time
 */
DateTime.isTodayTime = function(time) {
    var now = DateTime.getDateTimeDate().getTime();
    var daydiff = DateTime.DateDiff("d", DateTime.GetDateString(new Date(now)),
        DateTime.GetDateString(new Date(time)));
    if (daydiff == 0) {
        return true;
    } else {
        return false;
    }
}

/**
 * 获取2个日期的间隔时间
 * @param beginDate 开始日期
 * @param endDate 结束日期
 * @returns {*}
 */
DateTime.dateDiffByDate = function(beginDate, endDate, isDay) {
    if (beginDate == null || endDate == null){
        return null;
    }
    diff  = new Date();
    diff.setTime(Math.abs(beginDate.getTime() - endDate.getTime()));
    timediff = diff.getTime();
    /*weeks = Math.floor(timediff / (1000 * 60 * 60 * 24 * 7));
    timediff -= weeks * (1000 * 60 * 60 * 24 * 7);

    days = Math.floor(timediff / (1000 * 60 * 60 * 24));
    timediff -= days * (1000 * 60 * 60 * 24);*/
    if (isDay){
        days = Math.floor(timediff / (1000 * 60 * 60 * 24));
        timediff -= days * (1000 * 60 * 60 * 24);
    }

    hours = Math.floor(timediff / (1000 * 60 * 60));
    timediff -= hours * (1000 * 60 * 60);
    if (hours <= 9){
        hours = "0" + hours;
    }

    mins = Math.floor(timediff / (1000 * 60));
    timediff -= mins * (1000 * 60);
    if  (mins <= 9){
        mins = "0" + mins;
    }

    secs = Math.floor(timediff / 1000);
    timediff -= secs * 1000;
    if (secs <= 9){
        secs = "0" + secs;
    }
    var diffStr = "";
    if (isDay){
        diffStr = days + "天 ";
        diffStr += hours + "时" + mins + "分" + secs + "秒";
    	return diffStr;
    }
    diffStr += hours + ":" + mins + ":" + secs;
    return diffStr;
}


/**
 * 获取2个日期的间隔时间
 * @param beginDate 开始日期
 * @param endDate 结束日期
 * @returns {*}
 */
DateTime.timeDiffByDate = function(beginDate, endDate) {
    var begin = 0;
    var end = 0;
    if (beginDate){
        begin = beginDate.getTime();
    }
    if (endDate){
        end = endDate.getTime();
    }
    var diff  = new Date();
    diff.setTime(Math.abs(begin - end));
    var timediff = diff.getTime();
    var weeks = Math.floor(timediff / (1000 * 60 * 60 * 24 * 7));
    timediff -= weeks * (1000 * 60 * 60 * 24 * 7);

    var days = Math.floor(timediff / (1000 * 60 * 60 * 24));
    timediff -= days * (1000 * 60 * 60 * 24);

    var hours = Math.floor(timediff / (1000 * 60 * 60));
    timediff -= hours * (1000 * 60 * 60);

    var mins = Math.floor(timediff / (1000 * 60));
    timediff -= mins * (1000 * 60);

    var secs = Math.floor(timediff / 1000);
    var datediff = {};
    datediff.weeks = weeks;
    datediff.days = days ;
    datediff.hours = hours;
    datediff.mins = mins;
    datediff.secs = secs;
    return datediff;
}

/**
 * 获取当前日期的第一天是几号（星期一算第一天）
 * @param oDate
 */
DateTime.getFirstDayOfWeek=function(oDate){
    var iCurDay=oDate.getDay();
    switch(iCurDay){
        case 0://星期天
            oDate.setDate(oDate.getDate()-6);
            break;
        case 1://星期一
            break;
        case 2://星期二
            oDate.setDate(oDate.getDate()-1);
            break;
        case 3://星期三
            oDate.setDate(oDate.getDate()-2);
            break;
        case 4://星期四
            oDate.setDate(oDate.getDate()-3);
            break;
        case 5://星期五
            oDate.setDate(oDate.getDate()-4);
            break;
        case 6://星期六
            oDate.setDate(oDate.getDate()-5);
            break;
    }
    return oDate;
}

//获得某月的天数  
DateTime.getMonthDays = function(year, month) {
	var monthStartDate = new Date(year, month, 1);  
	var monthEndDate = new Date(year, month + 1, 1);  
	var days = (monthEndDate - monthStartDate)/(1000 * 60 * 60 * 24);  
	return days;  
}
 
//获得本月的开端日期  
DateTime.getMonthStartDate = function(year, month){  
	var monthStartDate = new Date(year, month, 1);  
	return DateTime.GetDateString(monthStartDate);  
}  

//获得本月的停止日期  
DateTime.getMonthEndDate = function(year, month){  
	var monthEndDate = new Date(year, month, DateTime.getMonthDays(year, month));
	return DateTime.GetDateString(monthEndDate);
}

//计算两个日期之间的年月日
DateTime.computerSyxq = function (currentDate,zxxqzr) {
	var flag = 0;
	var qr = currentDate;
	var zr = zxxqzr;
	if(qr==""||zr==""){
		return;
	}
	if(currentDate >= zxxqzr){
		return "0天"
	}
	var sqr=qr.split("-");
	var zqr=zr.split("-");
	sqr[0]=parseInt(sqr[0]);
	sqr[1]=DateTime.convert(sqr[1]);
	sqr[2]=DateTime.convert(sqr[2]);
	zqr[0]=DateTime.convert(zqr[0]);
	zqr[1]=DateTime.convert(zqr[1]);
	zqr[2]=DateTime.convert(zqr[2]);
	var years = zqr[0] - sqr[0] - 1;
	var qdays = 365 - DateTime.computerdays(sqr[1],sqr[2]);
	var zdays = DateTime.computerdays(zqr[1],zqr[2]);
	days = qdays + zdays + 1;
	if(days>=365){
		years =years + 1;
		days=days - 365;
	}
	if(years !=0){
		var SS =years+"年"+DateTime.formatDays(days);
	}else{
		var SS =DateTime.formatDays(days);
	}
	return SS;
}

/**
 * 将月数天数转换成数字
 */
DateTime.convert = function (str) {
	if(str.substring(0,1)=="0"){
		var str1 = parseInt(str.substring(1,2));
	}else{
		str1 = parseInt(str);
	}
	return str1;
}

/**
 *根据月数天数计算总天数 
 */
DateTime.computerdays = function (month,day) {
	switch(month){
		case 1 :days = day;break;
		case 2 :days = day + 31;break;
		case 3 :days = day + 28 + 31;break;
		case 4 :days = day + 28 + 31 * 2;break;
		case 5 :days = day + 28 + 31 * 2 + 30;break;
		case 6 :days = day + 28 + 31 * 3 + 30;break;
		case 7 :days = day + 28 + 31 * 3 + 30 * 2;break;
		case 8 :days = day + 28 + 31 * 4 + 30 * 2;break;
		case 9 :days = day + 28 + 31 * 5 + 30 * 2;break;
		case 10 :days = day + 28 + 31 * 5 + 30 * 3;break;
		case 11 :days = day + 28 + 31 * 6 + 30 * 3;break;
		case 12 :days = day + 28 + 31 * 6 + 30 * 4;break;
		default:days = 0;break;
	}
	return days;
}

/**
 * 根据总天数换化成XX月XX日形式
 */
DateTime.formatDays = function (days) {
	if(days<31){
		var month = 0;
		var day = days;
	}else if(days>=31&&days<59){
		var month = 1;
		var day = days - 31;
	}else if(days>=59&&days<90){
		var month = 2;
		var day = days - 59;
	}else if(days>=90&&days<120){
		var month = 3;
		var day = days - 90;
	}else if(days>=120&&days<151){
		var month = 4;
		var day = days - 120;
	}else if(days>=151&&days<181){
		var month = 5;
		var day = days - 151;
	}else if(days>=181&&days<212){
		var month = 6;
		var day = days - 181;
	}else if(days>=212&&days<243){
		var month = 7;
		var day = days - 212;
	}else if(days>=243&&days<273){
		var month = 8;
		var day = days - 243;
	}else if(days>=273&&days<304){
		var month = 9;
		var day = days - 273;
	}else if(days>=304&&days<334){
		var month = 10;
		var day = days - 304;
	}else{
		var month = 11;
		var day = days - 334;
	}
	var ss = "";
	if(month != 0){
		ss = month + "个月";
	}
	if(day != 0){
		ss = ss+day+"天";
	}
	return ss;
}

/*
 * 获取某年某月总共有多少天
 */
DateTime.getDaysInMonth = function(year, month) {
	var temp = new Date(year, month, "0");
	return temp.getDate();
}

DateTime.getTodayStartTime = function (currentTime) {
	if(!currentTime) currentTime = DateTime.getDateTime("yyyy-MM-dd HH:mm:ss");
	return currentTime.split(" ")[0] + " 00:00:00";
}

DateTime.getWeekStartTime = function (format) {
	var soap = new HWSOAP("/login/firstWeekTime.do");
	soap.send({
		format : format
	});
	var result = soap.getResult();
	return result.date;
}

DateTime.getNowYearFirstDay = function () {
	debugger;
	var nowTime = new Date();
	var year = nowTime.getFullYear();
	return new Date(year+"-01-01 00:00:00");
}

DateTime.getNowMonthFirstDay = function () {
	var nowTime = new Date();
	var year = nowTime.getFullYear();
	var month = nowTime.getMonth()+1;
	return new Date(year+"-"+month+"-01 00:00:00");
}

DateTime.getNowYearFirstDay = function () {
	var nowTime = new Date();
	var year = nowTime.getFullYear();
	return new Date(year+"-01-01 00:00:00");
}

/**
 * 计算两个日期之间相差满多少月份
 */
DateTime.getComputerMonth = function (date1 , date2) {
	//用-分成数组    
	date1 = date1.split("-");    
	date2 = date2.split("-");    
	//获取年,月数    
	var year1 = parseInt(date1[0]) ,         
	month1 = parseInt(date1[1]) ,         
	year2 = parseInt(date2[0]) ,         
	month2 = parseInt(date2[1]) ,         
	//通过年,月差计算月份差        
	months = (year2 - year1) * 12 + (month2-month1);    
	return months;    
}
