$(function(){
	
	$('#form-login input').on('focus',function(){
		$('#error_login').html('');
	});
	
	$(document).keydown(function(event){ 
		if(event.keyCode==13){ 
			$('#form-login input[type="button"]').click(); 
		} 
	});
	
	$('#form-login input[type="button"]').on('click',function(event) {
		var username = $('#form-login').find('input[name="username"]'),
			password = $('#form-login').find('input[name="password"]');
		if($.trim(username.val()) == ''){
			$('#error_login').html('<span>请填写账号名</span>');
			return false;
		}
		if($.trim(password.val()) == ''){
			$('#error_login').html('<span>请填写账号密码</span>');
			return false;
		}
		login();
	});
});

function login() {
	$("#login").val("正在登录");
	$("#login").attr("disabled","disabled");
	var username = $("#username").val();
	var password = $("#password").val();
	
	var params = {};   
	params.data = username;
	params.password = password;
	$.ajax({
		type: 'post',
		dataType:'json',
		data:params,
		url:'/ersp/sso/login',
		success:function(data){
			if (data.result) {
	            if (data.msg=="1") {
	            	window.location.href = "/ersp/site/index/admin.html";
	            	//window.location.href = GetQueryString("url");
	            } else {
	            	window.location.href = "/ersp/index.html";
	            }
	        } else {
	        	$('#error_login').html('<span>账号名或密码错误</span>');
	        	$("#login").val("登录");
	     		$("#login").removeAttr("disabled");
				return false;
	        }
		}
	 });
}

function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}