<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%--静态包含base标签，css样式，jQuery文件--%>
<%--<%@ include file="/pages/common/head.jsp"%>--%>
	<base href="http://localhost:8080/book/">
	<link type="text/css" rel="stylesheet" href="static/css/style.css">
	<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
<script type="text/javascript">

	// 页面加载完成之后
	$(function(){

		$("#username").blur(function () {

			//1.获取用户名
			var username = this.value;
			//2.发起AJAX请求
			$.getJSON("http://localhost:8080/book/userServlet","action=ajaxExistsUsername&username="+username,function (data) {
				if (data.existsUsername){
					$("span.errorMsg").text("用户名已存在！");
				}else{
					$("span.errorMsg").text("用户名可用！");
				}

			})
		});

		//给验证码的图片绑上单击事件
		$("#code_img").click(function () {
			this.src="${basePath}kaptcha.jpg?d="+new Date();

		});
		
		// 给注册按钮添加事件
		$("#sub_btn").click(function(){
			
			// 获取用户名
			var usernameValue = $("#username").val();
			// 验证用户名是否合法,规则如下：必须由字母，数字，下划线组成，并且长度为5到15位。
			var usernameReg = /^\w{5,15}$/;
			// 验证用户信息
			if (!usernameReg.test(usernameValue)) {
				// 提示用户
				alert("用户名不合法！");
				return false;
			}
			
			// 获取密码
			var passwordValue = $("#password").val();
			// 验证密码是否合法,规则如下：必须由字母，数字，下划线组成，并且长度为5到15位。
			var passwordReg = /^\w{5,15}$/;
			// 验证用户信息
			if (!passwordReg.test(passwordValue)) {
				// 提示用户
				alert("密码不合法！");
				return false;
			}
			
			// 获取确认密码
			var repwdValue = $("#repwd").val();
			// 验证确认密码和密码一致
			if (passwordValue != repwdValue) {
				// 提示用户
				alert("确认密码和密码不一致！");
				return false;
			}
			
			// 获取用户名
			var emailValue = $("#email").val();
			// 验证邮件输入是否合法。
			var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			
			if (!emailReg.test(emailValue)) {
				// 提示用户
				alert("邮件输入不合法！");
				return false;
			}
			
			
			// 获取验证码信息
			var codeValue = $("#code").val();
			// 验证验证码不为空！
			if (codeValue == "") {
				alert("验证码不能为空！");
			}
	
			return true;
		});
		
	});

</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">

									${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" 
										tabindex="1" name="username" value="${requestScope.username}" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" 
										tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" 
										tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" 
										tabindex="1" name="email" value="${requestScope.email}" id="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 80px;" id="code" name="code"/>
									<img alt="" id="code_img" src="kaptcha.jpg" style="float: right; margin-right: 40px;width: 110px;height: 30px;">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%-- 页脚--%>
		<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>