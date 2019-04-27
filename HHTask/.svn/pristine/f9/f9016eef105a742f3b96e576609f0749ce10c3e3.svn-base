/**
 * 将form表单元素的值序列化成对象
 * @param {Object} JQuery的表单对象
 * @requires jQuery
 * @return object 返回表单序列化的一个对象
 */
serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

/**
 * 是否为手机号
 * @param {string} telphone
 * @return {boolean} true为真
 */
function isTelephone(telphone){
	var reg = /0?(13|14|15|18)[0-9]{9}/;
	return reg.test(telphone);
}

/**
 * 是否为真实姓名
 * @param {string} name
 * @return {boolean} true为真 
 */
function isRealName(name){
	var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+$/;
	return reg.test(name);
}