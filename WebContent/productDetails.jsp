<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/hibiscus.css">
<link rel="stylesheet" href="./css/product.css">
<link rel="shortcut icon" href="./images/favicon.ico">
<title>商品詳細</title>
</head>
<body>

	<jsp:include page="header.jsp" />
	<h1>商品詳細</h1>

	<div class="contents">
	<s:form action="AddCartAction">
		<div>
			<!-- 2カラム商品画像 -->
			<div class="product-detail-image">
				<img
					src='<s:property value="%{#session.imageFilePath}"/>/<s:property value="%{#session.imageFileName}"/>'
					 class="item-image-box-320"/><br>
			</div>
		</div>
		<div class="left">
			<!-- 2カラム用商品情報欄 -->
			<table class="product-info-list">
				<tr>
					<th scope="row"><s:label value="商品名" /></th>
					<td><s:property value="%{#session.productName}" /></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="商品名ふりがな" /></th>
					<td><s:property value="%{#session.productNameKana}" /></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="商品詳細情報" /></th>
					<td><s:property value="%{#session.productDescription}" /></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="値段" /></th>
					<td><s:property value="%{#session.price}" />円</td>
				</tr>
				<tr>
					<th scope="row"><s:label value="購入個数" /></th>
					<td><s:select name="productCount" list="%{#session.productCountList}" />個</td>
				</tr>
				<tr>
					<th scope="row"><s:label value="発売会社名" /></th>
					<td><s:property value="%{#session.releaseCompany}" /></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="発売年月日" /></th>
					<td><s:property value="%{#session.releaseDate}" /></td>
				</tr>
			</table>
				<s:hidden name="productId" value="%{#session.productId}"/>
				<s:hidden name="productName" value="%{#session.productName}"/>
				<s:hidden name="productNameKana" value="%{#session.productNameKana}"/>
				<s:hidden name="imageFilePath" value="%{#session.imageFilePath}"/>
				<s:hidden name="imageFileName" value="%{#session.imageFileName}"/>
				<s:hidden name="price" value="%{#session.price}"/>
				<s:hidden name="releaseCompany" value="%{#session.releaseCompany}"/>
				<s:hidden name="releaseDate" value="%{#session.releaseDate}"/>
				<s:hidden name="productDescription" value="%{#session.productDescription}"/>
			<br>
			<s:submit class="product-submit" value="カートに追加"  />
		</div>
	</s:form>
	</div>

	<!-- 関連商品 -->

	<div class="box2">
	<div class="product-details-recommend-box">
	<s:iterator value="#session.productInfoDtoList">

	<div class="recommend-box">
	<ul>
		<li>
		<a href='<s:url action="ProductDetailsAction">
		<s:param name="productId" value="%{productId}"/>
		</s:url>'>
		<img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' class="item-image-box-100"/>
		</a>
		<s:property value="productName"/><br>
		<s:property value="price"/>円<br>

		</li>
	</ul>
	</div>

	</s:iterator>
	</div>
	</div>
	<!-- フッター -->
	<%@ include file="footer.jsp" %>
</body>
</html>