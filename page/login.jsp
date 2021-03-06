<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>达内商城学子登陆页面</title>
    <link href="../css/header.css" rel="stylesheet"/>
    <link href="../css/footer.css" rel="stylesheet"/>
    <link href="../css/animate.css" rel="stylesheet"/>
    <link href="../css/login.css" rel="stylesheet"/>
</head>
<body>
<!-- 页面顶部-->
<header id="top">
    <div class="top">
        <img src="../img/header/logo.png" alt=""/>
        <span>欢迎登录</span>
    </div>
</header>
<div id="container">
    <div id="cover" class="rt">
        <form id="fm-login" name="form1" method="post" action="userLogin" >
            <div class="txt">
                <%
                   //从request对象中获取cookie对象，(cookie是数组，session是键值对)
                   Cookie[] cookies = request.getCookies();
                   Cookie remname = null;
                   if(cookies != null){//判断cookies是否为null
                	   for(Cookie cookie:cookies){
                		   if("remname".equals(cookie.getName())){
                			   remname = cookie;
                			   break;
                		   }
                	   }
                   }
                   String username="";//获取了cookie中的用户名
                   if(remname!=null){
                	   username=remname.getValue();
                	   //需要对username的编码格式进行统一解码
                	   username=URLDecoder.decode(username, "utf-8");
                	   
                   }
                %>
                <p>登录学子商城
                    <span>
                        <a href="regist.html">新用户注册</a>
                    </span>
                </p>
                <div class="text">
                    <input type="text" value="<%=username %>" placeholder="请输入您的用户名" name="uname" id="uname" required>
                    <span></span>
                </div>

                <div class="text">
                    <input type="password" id="upwd" placeholder="请输入您的密码" name="upwd" required minlength="6" maxlength="15">
                    <span></span>
                </div>
                
                <div class="text"> 
                    <input type="text" name="valistr" style="width:60px;" />
                    <image id="img" alt="图片不存在" src="../page/valiImageServlet">
                </div>
                
                <div class="chose">
					<input type="checkbox" value="true" name="remname" 
					<%=remname==null? "":"checked='checked'" %>/>记住用户名
					<input type="checkbox" name="autologin" value="true"/>30天内自动登陆
                </div>
                <input class="button_login" type="button" value="登录" id="bt-login" />
            </div>
        </form>
    </div>
</div>
<!--错误提示-->
<%
  String result = (String)request.getAttribute("showResult");
%>
<div id="showResult"><%=result==null?"":result %></div>
<!-- 品质保障，私人定制等-->
<div id="foot_box">
    <div class="icon1 lf">
        <img src="../img/footer/icon1.png" alt=""/>

        <h3>品质保障</h3>
    </div>
    <div class="icon2 lf">
        <img src="../img/footer/icon2.png" alt=""/>

        <h3>私人定制</h3>
    </div>
    <div class="icon3 lf">
        <img src="../img/footer/icon3.png" alt=""/>

        <h3>学员特供</h3>
    </div>
    <div class="icon4 lf">
        <img src="../img/footer/icon4.png" alt=""/>

        <h3>专属特权</h3>
    </div>
</div>
<!-- 页面底部-->
<div class="foot_bj">
    <div id="foot">
        <div class="lf">
            <p class="footer1"><img src="../img/footer/tedu.png" alt="" class=" footLogo"/></p>
            <p class="footer2"><img src="../img/footer/footerFont.png"alt=""/></p>
            <!-- 页面底部-备案号 #footer -->
            <div class="record">
                2001-2016 版权所有 京ICP证8000853号-56
            </div>
        </div>
        <div class="foot_left lf" >
            <ul>
                <li><a href="#"><h3>买家帮助</h3></a></li>
                <li><a href="#">新手指南</a></li>
                <li><a href="#">服务保障</a></li>
                <li><a href="#">常见问题</a></li>
            </ul>
            <ul>
                <li><a href="#"><h3>商家帮助</h3></a></li>
                <li><a href="#">商家入驻</a></li>
                <li><a href="#">商家后台</a></li>
            </ul>
            <ul>
                <li><a href="#"><h3>关于我们</h3></a></li>
                <li><a href="#">关于达内</a></li>
                <li><a href="#">联系我们</a></li>
                <li>
                    <img src="../img/footer/wechat.png" alt=""/>
                    <img src="../img/footer/sinablog.png" alt=""/>
                </li>
            </ul>
        </div>
        <div class="service">
            <p>达内商城客户端</p>
            <img src="../img/footer/ios.png" class="lf">
            <img src="../img/footer/android.png" alt="" class="lf"/>
        </div>
        <div class="download">
            <img src="../img/footer/erweima.png">
        </div>
    </div>
</div>
<script src="../js/jquery-3.1.1.min.js"></script>
<script>
    $("#uname").blur(function(){
        var data = $("#uname").val();
        if (data == null || data == "") {
            $("#showResult").text("用户名不能为空！");
            $("#showResult").css("color","red");
            return false;
        }
        $.ajax({
            type:"POST",
            url:"unameCheck.action",
            data:"uname="+data,
            beforeSend:function(XMLHttpRequest)
            {
                $("#showResult").text("正在查询");

            },
            success:function(msg)
            {
                if(msg ==="yes"){
                    $("#showResult").text("");
                }else if(msg === 'no'){
                    $("#showResult").text("该用户不存在");
                    $("#showResult").css("color","red");
                }
            },
            error:function()
            {
                //错误处理
            	$("#showResult").text("系统异常！");
                $("#showResult").css("color","red");
            }
        });
    });
    $('#bt-login').click(function(){
    	//提交form表单,根据action="url路径"提交的
    	$("#fm-login").submit();
        //window.location.href =  "index.html";
        //读取用户的输入——表单序列化
        //var inputData = $('#fm-login').serialize();
        // alert(inputData);
        //异步提交请求，进行验证
       /* $.ajax({
        	async: true,
            type: 'POST',
            url: 'login.action',
            data: inputData,
            success: function(txt, msg, xhr){
            	// alert("*"+txt+"*");
                if(txt=='yes'){  //登录成功
                    var loginName = $('[name="uname"]').val();
                    console.log(loginName);
                    window.location.href =  "index.html";
                }else{ //登录失败
                    $('#showResult').html('登录失败！');
                }
            }
        });*/
    });
    //验证码的点击事件
    $("#img").click(function(){
    	//attr：对img标签添加一个属性
    	$(this).attr("src","../page/valiImageServlet?time="+new Date().getTime());
    });
    
</script>
</body>
</html>
    