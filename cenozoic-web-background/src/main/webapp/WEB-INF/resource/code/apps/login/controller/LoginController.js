Ext.define("am.login.controller.LoginController", {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'form button[ref=login]' : {
				click : function(button, e, eOpts) {
					var app = button.up('viewport');
					var formBase = button.up('form').getForm();
					var cc = formBase.getValues();
					if (cc.userName.length < 1 || cc.password.length < 1) {
						Ext.Msg.alert('请输入正确的帐号/密码！');
						return;
					}
					// alert(123);
					// 验证帐号
					Ext.Ajax.request({
								async : false,
								method : 'POST',
								url : 'usermanager/login.html',
								type : 'ajax',
								params : {
									userName : cc.userName,
									password : cc.password
								},
								success : function(response) {
									if (response.responseText.toUpperCase() == 'OK') {
										// app.removeAll();
										// app.add(Ext.createWidget('managermainlayout'));
										location.reload();
									}
								}
							});
				}
			}
		});
	},
	views : ['am.login.view.LoginLayout']
});