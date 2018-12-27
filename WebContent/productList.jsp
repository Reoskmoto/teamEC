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
<title>商品一覧</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<h1>商品一覧</h1>
	<s:if test="!keywordsErrorMessageList.isEmpty()">
		<div class="error">
			<div class="error-message">
				<s:iterator value="keywordsErrorMessageList">
					<s:property />
					<br>
				</s:iterator>
			</div>
		</div>
	</s:if>
	<s:elseif test="#session.productInfoDtoList.isEmpty()">
		<div class="info">検索結果がありません。</div>
	</s:elseif>
	<s:else>
		<div class="product-list">
			<s:iterator value="#session.productInfoDtoList">
				<div class="product-list-box">
					<ul>
						<li><a
							href='<s:url action="ProductDetailsAction">
	<s:param name="productId" value="%{productId}"/>
	</s:url>'><img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>'
								class="product-list-image" /></a><br> <s:property
								value="productName" /><br> <s:property
								value="productNameKana" /><br> <s:property value="price" />円<br>
						</li>
					</ul>
				</div>
			</s:iterator>
		</div>
	</s:else>

	<div class="hafcss-clear-left"></div>
	<%@ include file="footer.jsp" %>
</body>
</html>