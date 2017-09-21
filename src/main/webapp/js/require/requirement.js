$(document).ready(function() {

	$('#requirementform').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
			reqName: {
                validators: {
                    notEmpty: {
                        message: '需求简述不能为空'
                    },
					stringLength: {
                        min: 10,max:200,
                        message: '需求简述长度10-200个字符'
                    }
//                  ,threshold:10,
//					remote: {
//                        type: 'POST',
//                        delay:2000,
//                        url: '/ersp/api/requirements/check/reqname',
//                        message: '该需求简述已经存在（仅用于验证远程验证试验，业务系统应当不会存在）'
//                    }
                }
            }
//            ,reqDesc: {
//                validators: {
//                    notEmpty: {
//                        message: '需求详述不能为空'
//                    },
//					stringLength: {
//                        min: 10,max:2000,
//                        message: '需求详述长度10-2000个字符'
//                    }
//                }
//            }                  
        }
    });		
	$('#btn_submit').click(function() {
    	var bootstrapValidator = $("#requirementform").data('bootstrapValidator');
		bootstrapValidator.validate();
		if(bootstrapValidator.isValid()){
			var params = $("#requirementform").serialize();  
			var actionUrl = $("#requirementform").attr("action");
			//alert(params);
			$.ajax({
				type: "POST",dataType:'json',
				url: actionUrl,
				//contentType: "application/json;charset=utf-8",data:JSON.stringify(params),
				data:params,
				success: function(data){
					if(data.result){
						//$('#table').bootstrapTable('refresh', {silent:true});
						window.parent.closeModal();
					}else{
						BootstrapDialog.show({
					         title: 'xxxxxxxxxx',
					         message: '修改需求失败,请刷新页面重试,重试无效请告知系统管理员。',
					         buttons: [{
					             label: '明白了',
					             cssClass: 'btn-primary',
					             action: function(dialog) {
					            	 dialog.close();
					             }
					         }]
					     });
					}
				},
				error:function(data){
					alert(data);
				}
			});
		}
    });
});

