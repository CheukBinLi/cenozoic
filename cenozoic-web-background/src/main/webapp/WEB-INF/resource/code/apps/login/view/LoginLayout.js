Ext.define('am.login.view.LoginLayout', {
			extend : 'Ext.panel.Panel',
			alias : 'widget.loginlayout',
			layout : {
				align : 'middle',
				pack : 'center',
				type : 'hbox'
			},
			items : [{
						width : 300,
						maxWidth : 300,
						height : 200,
						maxHeight : 200,
						frame : true,
						layout : {
							align : 'middle',
							pack : 'center',
							type : 'hbox'
						},
						items : [{
									xtype : 'form',
									defaults : {
										// closable : true,
										// closeAction : 'destroy',
										padding : " 30 30 30 30",
										bodyBorder : false,
										blankText : '不能为空',
										msgTarget : 'side',
										minWidth : 80
									},
									items : [{
												margin : '20 0 0 0',
												xtype : 'textfield',
												name : 'userName',
												fieldLabel : '帐号',
												labelWidth : 50,
												minLengthText : 6,
												allowBlank : false
											}, {
												xtype : 'textfield',
												name : 'password',
												fieldLabel : '密码',
												labelWidth : 50,
												inputType : 'password',
												minLengthText : 1,
												allowBlank : false
											}],
									buttonAlign : 'center',
									buttons : [{
												text : '登录',
												ref : 'login'
											}, {
												text : '重置',
												ref : 'reset'
											}]
								}],
						listeners : {
							'afterrender' : function(login, eOpts) {
//								if (null != userName && userName != 'error') {
//									var app = login.up('viewport');
//									app.removeAll();
//									app.add(Ext.createWidget('managermainlayout'));
//								}
							}
						}
					}]
		});
