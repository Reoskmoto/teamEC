<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="./images/favicon.ico">
<link rel="stylesheet" href="./css/hibiscus.css">
<link rel="stylesheet" href="./css/common.css">
<title>商品購入履歴</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<h1>商品購入履歴画面</h1>
		<s:if test="purchaseHistoryInfoDTOList.size()>0">
			<table>
				<thead class="thead">
					<tr>
						<th><s:label value="商品名" /></th>
						<th><s:label value="ふりがな" /></th>
						<th><s:label value="商品画像" /></th>
						<th><s:label value="値段" /></th>
						<th><s:label value="発売会社名" /></th>
						<th><s:label value="発売年月日" /></th>
						<th><s:label value="個数" /></th>
						<th><s:label value="合計金額" /></th>
					</tr>
				</thead>
				<tbody class="tbody">
					<s:iterator value="purchaseHistoryInfoDTOList">
						<tr>
							<td  class="center"><s:property value="productName" /></td>
							<td  class="center"><s:property value="productNameKana" /></td>
							<td  class="center"><img
							src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>'
								width="50px" height="50px" class="photo" /></td>
							<td  class="center"><s:property value="price" />円</td>
							<td  class="center"><s:property value="releaseCompany" /></td>
							<td  class="center"><s:property value="releaseDate" /></td>
							<td  class="center"><s:property value="productCount" /></td>
							<td  class="center"><s:property value=" subtotal" />円</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>

			<div class="submit_box">
				<s:form action="DeletePurchaseHistoryAction">
					<s:submit id="submit" value="削除"/>
				</s:form>

			</div>
		</s:if>
		<s:else>
			<div class="complete">商品購入履歴情報はありません。</div>
		</s:else>
	</div>
	<div class="margin"></div>
	<%@ include file="footer.jsp" %>
</body>
</html>