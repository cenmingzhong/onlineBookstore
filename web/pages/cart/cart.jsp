<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<base href="http://localhost:8080/book/">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
	<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%--静态包含登陆成功之后的菜单--%>
		<%@ include file="/pages/common/login_success_menu.jsp"%>
		<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
		<script type="text/javascript">
			$(function () {
				//给删除绑定单击事件
				$("#a.deleteItem").click(function () {
					return  confirm("你确定要删除【"+$(this).parent().parent().find("td:first").text()+"】");

				});

				//给清空购物车绑定单击事件
				$("#clearCart").click(function () {
					return confirm("你确定要清空购物车吗？");

				});
				//给输入框绑定失去焦点事件
				$(".updateCount").change(function () {
					//获取商品名称
					var name = $(this).parent().parent().find("td:first").text();
					//
					var id = $(this).attr('bookId');
					//获取商品数量
					var count = this.value;

					if(confirm("你确定要把【"+name+"】商品数量修改为"+count+"吗？")){
						//发起请求，给服务器保存修改
						location.href="http://localhost:8080/book/cartServlet?action=updateCount&count="+count+"&id="id;

					}else{
						//defaultValue属性是表单项Dom对象的属性，它表示默认的value属性值
						this.value=this.defaultValue;
					}

				});
			});


		</script>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>






		<c:if test="${empty sessionScope.cart.items}">
			<%--如果购物车是空的情况--%>

			<tr>
				<td colspan="5"><a href="index.jsp">亲，当前购物车为空</a></td>

			</tr>

		</c:if>
		<c:if test="${not empty sessionScope.cart.items}">
			<%--如果购物车是非空的情况--%>
			<c:forEach items="${sessionScope.cart.items}" var="entry">
				<tr>
					<td>${entry.value.name}</td>
					<td>
						<input class="updateCount" bookId="${entry.value.id}" style="width: 80px" type="text" value="${entry.value.count}">
					</td>
					<td>${entry.value.price}</td>
					<td>${entry.value.totalPrice}</td>
					<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
				</tr>
			</c:forEach>



		</c:if>

		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	
	</div>

	<%-- 页脚--%>
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>