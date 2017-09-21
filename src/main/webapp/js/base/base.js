$(function(){
	
	//日期时间选择器 当前为年-月-日类型时间选择器
	var dateObj = $(".date");
	if(dateObj != null 
			&& typeof(dateObj)!="undefined"
				&& dateObj.length>0){
		
		dateObj.datetimepicker({
			minView:'month',
			format:'yyyy-mm-dd',
			autoclose:'true',
			pickerPosition:'auto-left',
			todayHighlight:true,
			language:'zh-CN'
		});
	}
});

function dataFormatter(cellvalue){
	
	if(cellvalue!=null && typeof(cellvalue) != "undefined"){

		var date = new Date();  
		date.setTime(cellvalue); 
		var year = 1900+date.getYear();
		var month = 1+date.getMonth();
		var day = date.getDate();
		return year+"-"+month+"-"+day;
	}
	return "未记录";
};