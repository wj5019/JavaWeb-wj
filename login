<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
<link rel="stylesheet" href="../css/top.css" />
<link rel="stylesheet" href="../css/login.css" />
<link rel="stylesheet" href="../css/foot.css" />
</head>
<body>
	<!-- 页面顶部 ：logo部分-->
	<div id="top">
		<img alt="图片错误" src="../image/logo.png"> <span>欢迎登录</span>
	</div>
	<!-- 页面中部：内容部分 -->
	<div id="center">
		<div id="context">
			<form id="loginform" action="">
				<div id="txt">
					<p>
						登录学子商城 <span> <a href="regist.html">新用户注册</a>
						</span>
					</p>
					<div class="text">
						<input type="text" placeholder="请输入您的用户名" name="uname" id="uname"
							required> <span></span>
					</div>
					<div class="text">
						<input type="password" id="upwd" placeholder="请输入您的密码" name="upwd"
							required maxlength="15"> <span></span>
					</div>
					<input class="button_login" type="button" value="登录" id="bt-login" />
				</div>
			</form>
		</div>
	</div>
	<!-- 页面底部：广告部分 -->
	<div id="foot">
		<div id="icon1" class="lf">
			<img alt="图片错误" src="../image/icon1.png">
			<h3>品质保障</h3>
		</div>
		<div id="icon2" class="lf">
			<img alt="图片错误" src="../image/icon2.png">
			<h3>私人订制</h3>
		</div>
		<div id="icon3" class="lf">
			<img alt="图片错误" src="../image/icon3.png">
			<h3>学员特供</h3>
		</div>
		<div id="icon4" class="lf">
			<img alt="图片错误" src="../image/icon4.png">
			<h3>专属特权</h3>
		</div>
	</div>
</body>
</html>
------------------------------------------------------------------
@charset "UTF-8";
#foot {
	height:180px;
	text-align: center;
	font-size: 10px;
}
.lf {
	float: left;
	padding-top: 50px;
	width:65px;
	height:80px;
}
#icon1 {
	margin-left: 270px;
}
#icon2 , #icon3 , #icon4 {
	margin-left:190px;
}
----------------------------------------------------------------
@charset "UTF-8";
#center {
	height:560px;
	background: url("../image/login.jpg");
}
#context {
	width:300px;
	height:300px;
	float:right;
	margin-right: 125px;
	margin-top: 125px;
	background-color: rgba(0,0,0,0.3);
}
#txt {
	/* border: 1px solid blue; */
	width:280px;
	height:280px;
	margin-left: 10px;
}
#loginform p {
	line-height:40px;
    border-bottom: 1px solid #f0f0f0;
    color:#fff;
}
#loginform a {
	float:right;
	color:#fff;
	text-decoration:none;
}
.text input{
	border:1px solid #ddd;
    padding:10px 20px;
    width:223px;
    margin-top: 26px;
    box-shadow:inset 0 1px 1px rgba(0 0 0 .075);
}
.button_login {
	color:#fff;
    text-align:center;
    width:263px;
    line-height:35px;
    font-weight:bold;
    margin-top: 30px;
    background-color: #16A8F1;
}
----------------------------------------------------
@charset "UTF-8";
body {
	background-color: #f5f5f5;
}
div {
	/* border: 1px solid red; */
}
#top {
	height:60px;
	padding-top: 20px;
	padding-bottom: 20px;
	/* //padding-left: 200px; */
	font-size: 30px;
}
