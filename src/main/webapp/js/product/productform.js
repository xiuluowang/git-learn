$(document).ready(function() {
	
	var type = $("#productForm").attr("data-opt");
	$('#productForm').bootstrapValidator({
//        live: 'disabled',
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
		
			name: {
                validators: {
                    notEmpty: {
                        message: '产品名称不能为空'
                    },
					stringLength: {
                        max: 200,
                        message: '产品名称长度不可超过200个字符'
                    },
					 remote: {
                        type: 'POST',
                        data:{
                        	id:function(){ return $("#pro_id").val()}
                        },
                        url: '/ersp/api/product/validate/name',
                        message: '当前产品名称已存在'
                    }
                }
            }
        }
    });
    $('#submit_btn').click(function() {
    	
    	submitForm();
    });
});

/**
 * 提交表单
 */
function submitForm(){
	
	var bootstrapValidator = $("#productForm").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()){
		
		var params = $("#productForm").serializeArray();  
		var pro_id = $("#pro_id").val();
		var actionUrl=$("#productForm").attr("action");
		if(pro_id != null && pro_id != "" && pro_id.length > 0){
			
			actionUrl = actionUrl+"/"+pro_id;
		}
		$.post(actionUrl,params,function(data){
			
			if(data.result){
 				
 				$("#pro_id").val(data.responseInfo);
 				window.parent.hideModal(true);
 			}else{
 				
 				BootstrapDialog.show({
 			         title: '系统提示',
 			         message: '哎呀,保存失败了错误代号：'+data.code,
 			         buttons: [{
 			             label: '明白了',
 			             cssClass: 'btn-primary',
 			             action: function(dialog) {
 			            	 dialog.close();
 			             }
 			         }]
 			     });
 			}
		});
	}
}

