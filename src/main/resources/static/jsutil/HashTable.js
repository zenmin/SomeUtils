/**
 *模拟实现hashtable
 */
function HashTable(){
    this.m_hash  = new Object();//存放hash值
    this.m_array = new Array();//存放对象
    this.m_count = 0;//哈希表
}
/**
 * 放入一个对象。
 * @param key	对象的键值
 * @param obj	要放入的对象
 */
HashTable.prototype.put = function (key, obj) {
    var idx = this.m_array.length;
    this.m_hash[key] = idx;
    this.m_array[idx] = obj;
    this.m_count ++;
}
/**
 * 根据键值取得一个对象。
 * @param key	要取得的对象键值
 * @return	符合条件的对象，如果没有，返回null
 */
HashTable.prototype.get = function (key) {
    var idx = this.m_hash[key];
    if (idx != null) {
        return this.m_array[idx];
    }
    return null;
}
/**
 * 测试是否某个键值的对象是否存在。
 * @param key	要测试的键值
 * @return	如果存在返回true,否则返回false
 */
HashTable.prototype.containsKey = function (key) {
    return typeof(this.m_hash[key]) != "undefined";
}
/**
 * 将某个键值的对象删除。
 * @param key	要删除的对象键值
 * @return 删除的对象，如果没有对象符合条件，返回null
 */
HashTable.prototype.remove = function (key) {
    var idx = this.m_hash[key];
    if (idx != null) {
        var obj = this.m_array[idx];
        this.m_array[idx] = null;
        delete this.m_hash[key];
        this.m_count --;
        return obj;
    }
    return null;
}
/**
 * 清空所有的对象。
 */
HashTable.prototype.clear = function () {
    this.m_count = 0;
    this.m_hash  = new Object();
    this.m_array = new Array();
}
/**
 * 将所有对象按照顺序作为数组返回。
 * @return	包含的所有对象组成的数组。
 */
HashTable.prototype.elements = function () {
    var res = new Array();
    var a;
    for (var i = 0; i<this.m_array.length; i++) {
        if ((a=this.m_array[i]) != null)
            res[res.length] = a;
    }
    return res;
}
/**
 *获取哈希表元素的个数
 */
HashTable.prototype.getCount=function(){
    return this.m_count;
}