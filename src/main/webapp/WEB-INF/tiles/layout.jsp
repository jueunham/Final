<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.js" >
/* 모든 페이지에 적용되는 자바스크립트  CSS*/

</script>


</head>
<body>

<!-- HEADER -->
<tiles:insertAttribute name="header"/>

<!-- BODY -->
<div class="wrap">
<tiles:insertAttribute name="body"/>
</div>

<!-- FOOTER -->
<tiles:insertAttribute name="footer"/>


</body>
</html>