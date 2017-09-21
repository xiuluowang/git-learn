$(document).ready(function() {
	$(document).keydown(function(event){ 
		if(event.keyCode==13){ 
			$('#btn-register').click(); 
		} 
	});
	
	$('#registerForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
			uname: {
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
					stringLength: {
						min: 2,
                        max: 20,
                        message: '用户名长度为2~20个字符'
                    },
                    regexp: {
	                    regexp: /^\w{2,10}$/,
	                    message: '用户名只允许为英文字母、数字和下画线，长度为2-10位'
	                },
	                remote: {
                        type: 'POST',
                        data:{
                        	id:function(){return $("#userId").val();}
                        },
                        url: '/ersp/sso/valUname',
                        delay :  1000,
                        message: '用户名已经存在'
                    }
                }
            },
            name: {
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    },
					stringLength: {
                        max: 200,
                        message: '姓名长度不超过200个字符'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
					stringLength: {
                        max: 200,
                        message: '邮箱长度不超过200个字符'
                    },
                    regexp: {
	                    regexp: /^[a-z0-9]+@([a-z0-9]+\.)+[a-z]{2,4}$/i,
	                    message: '邮箱格式错误'
	                },
	                remote: {
                        type: 'POST',
                        data:{
                        	id:function(){return $("#userId").val();}
                        },
                        url: '/ersp/sso/valEmail',
                        delay :  1000,
                        message: '邮箱已经存在'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
					stringLength: {
                        max: 200,
                        message: '密码长度不超过200个字符'
                    }
                }
            },
            telephone: {
                validators: {
                    notEmpty: {
                        message: '联系电话不能为空'
                    },
                    regexp: {
	                    regexp: /^1(3|4|5|7|8)\d{9}$/,
	                    message: '联系电话格式错误，请输入正确的11位号码'
	                },
	                remote: {
                        type: 'POST',
                        data:{
                        	id:function(){return $("#userId").val();}
                        },
                        url: '/ersp/sso/valPhone',
                        delay :  1000,
                        message: '联系电话已经存在'
                    }
                }
            },
            caddress: {
                validators: {
					stringLength: {
                        max: 500,
                        message: '公司地址长度不超过500个字符'
                    }
                }
            },
            company: {
                validators: {
					stringLength: {
                        max: 500,
                        message: '公司名称长度不超过500个字符'
                    }
                }
            }
        }
    });
		
    $('#btn-register').click(function() {
    	var bootstrapValidator = $("#registerForm").data('bootstrapValidator');
		bootstrapValidator.validate();
		if(bootstrapValidator.isValid()){
			registerForm();  
		}
    });
});

function registerForm() {
	var params = $("#registerForm").serializeArray();  
	var actionUrl = $("#registerForm").attr("action");
	$.post(actionUrl,params,function(data){
		if (data.result) {
			window.location.href = "/ersp/index.html";
		} else {
			return false;
		}
	}); 
}