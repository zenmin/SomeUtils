/*** 对数组对象的扩展*/
/**
 *在数组中获取指定值的元素索引
 */
Array.prototype.getIndexByValue= function(value)
{
    var index = -1;
    for (var i = 0; i < this.length; i++)
    {
        if (this[i] == value)
        {
            index = i;
            break;
        }
    }
    return index;
}

/**
 *在数组中获取指定位置以后的长度
 */
Array.prototype.split= function(startIndex,len)
{
	var res =[];
    var index = -1;
    var endIndex = startIndex+len;
    if(endIndex > this.length){
    	endIndex = this.length;
    }
    for (var i = startIndex; i < endIndex; i++)
    {
    	res.push(this[i])
    }
    return res;
}
/**
 *经常用的是通过遍历,重构数组.
 */
Array.prototype.remove=function(dx)
{
    if(isNaN(dx)||dx>this.length){return false;}
    for(var i=0,n=0;i<this.length;i++)
    {
        if(this[i]!=this[dx])
        {
            this[n++]=this[i]
        }
    }
    this.length-=1
}

Array.prototype.removeById=function(id)
{
    if(!id){return false;}
    for(var i=0;i<this.length;i++){
        if(this[i].id==id) {
            this.remove(i);
            break;
        }
    }
}

Array.prototype.removeByAttr=function(key,value)
{
    if(typeof(value) == "undefined"){return false;}
    for(var i=0;i<this.length;i++){
        if(this[i][key]==value) {
            this.remove(i);
            break;
        }
    }
}

/**
 * 去重复放入元素,过滤重复元素
 * @param value
 */
Array.prototype.hasPush = function (value,key) {
    var isSet = true;
    for(var i = 0; i < this.length; i ++){
        if(this[i] == value || (typeof(key)!="undefined" && this[i][key] == value[key])) {
            isSet = false; break;
        }
    }
    if(isSet){
        this.push(value);
    }
    return isSet;
}

/**
 * 判断数组是否存在改属性切属性值为value
 * @param key
 * @param vlaue
 */
Array.prototype.has = function (key,value) {
    for(var i = 0; i < this.length; i ++){
        if(this[i][key] == value) {
            return true;
        }
    }
    return false;
}

/**
 * 数组中push一个数组
 * @param arr
 */
Array.prototype.pushAll = function (arr) {

    if(!(arr instanceof Array)) throw "请传入一个数组!";

    for(var i = 0; i < arr.length; i ++){
        this.push(arr[i]);
    }

}
