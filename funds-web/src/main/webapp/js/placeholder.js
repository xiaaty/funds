function isPlaceholder(){
	var input = document.createElement('input');
	return 'placeholder' in input;
}

if (!isPlaceholder()) {//��֧��placeholder ��jquery�����
	$(document).ready(function() {
	    if(!isPlaceholder()){
	        $("input").not("input[type='password']").each(//��input���¼� �ų�password��
	            function(){
	                if($(this).val()=="" && $(this).attr("placeholder")!=""){
	                    $(this).val($(this).attr("placeholder"));
	                    $(this).focus(function(){
	                        if($(this).val()==$(this).attr("placeholder")) $(this).val("");
	                    });
	                    $(this).blur(function(){
	                        if($(this).val()=="") $(this).val($(this).attr("placeholder"));
	                    });
	                }
	        });
	        //��password������⴦��1.����һ��text�� 2��ȡ�����ʧȥ�����ʱ���л�
	        var pwdField	= $("input[type=password]:first");
	        var pwdVal		= pwdField.attr('placeholder');
	        pwdField.after('<input id="pwdPlaceholder" type="text" class="input_login" value='+pwdVal+' autocomplete="off" />');
	        var pwdPlaceholder = $('#pwdPlaceholder');
	        pwdPlaceholder.show();
	        pwdField.hide();
	        
	        pwdPlaceholder.focus(function(){
	        	pwdPlaceholder.hide();
	        	pwdField.show();
	        	pwdField.focus();
	        });
	        
	        pwdField.blur(function(){
	        	if(pwdField.val() == '') {
	        		pwdPlaceholder.show();
	        		pwdField.hide();
	        	}
	        });
	        
	    }
	});
}