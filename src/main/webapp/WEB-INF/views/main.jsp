<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js">
</script>
<script src="http://code.jquery.com/jquery-1.7.js"	type="text/javascript"></script>
<script	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic+Coding:400,700&display=swap&subset=korean" rel="stylesheet">  
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<script type="text/javascript">
$(document).ready(function() {
	
	// 검색 자동완성 기능
	$('#keyword').autocomplete({
		source:function(request,response){
			$.ajax({
				url: "/main/autocomplete",
				type:"post",
				dataType:"json",
				data:{ keyword : $('#keyword').val()},
				success: function(data){
					response(
							$.map(data, function(item) {
								return {
									value : item.data
								}
							})
							)
				},
				error : function(data) {
					alert("에러가 발생하였습니다.")
				}
			});
		}
	});	
    
    $('.slider').slick({
    	  infinite: true,
    	  speed:300,
    	  autoplay: true, 
    	  autoplaySpeed:3000, 
    	  slidesToShow: 1,
    	  slidesToScroll: 1, 
          prevArrow: $('.prev'),
          nextArrow: $('.next'),
    	  dots: false
    	 
    });


    $("#keyword").keydown(function(key) {
        if (key.keyCode == 13) {
            $("form").submit();
        }
    });

});

function search() {
	$("form").submit();
}

</script>

<style type="text/css">


/* 메인 검색엔진 css */
.search {

	text-align: center;
	padding: 4px;
}

.ui-menu-item, .ui-autocomplete, .ui-menu, .ui-widget, .ui-widget-content, .ui-corner-all {
	width: 420px;
	font-size: 15px;
}

#searchBtn{
	border-radius: 3px;
	margin-left: -13px;
	height: 40px;
	width: 60px;
	background-color: #011627;
	border: 2px solid #011627;
	color: white;
}


#keyword {
	border-radius: 5px;
    
    height: 40px;
    background: white;
    border: 1px double;
}



.main_container{
	float: right;
	position: relative;
	left: -50%;	
	margin-top: 45px;
}


td{

font-size: 13px;

}


/* 화살표 */ 
.slick-arrow{ 
	z-index: 2; /* prev버튼은 위치 이동시 이미지 뒤로 숨겨짐 */ 
	width: 50px; 
	height: 50px; 
	transform: translateY(-25px); 
	border: none;
	outline: none;
	background: transparent;
	cursor: pointer;

} 






/* 아래점 */ 
.slick-dots{ 
	text-align: center;


} 

.slick-dots li{ 
	display: inline-block; 
	margin: 0 5px; 

	
}



.slider_container{
	margin: 0 auto;
	width: 650px;
	min-width: 550px;
	
	height:400px;
	min-height: 300px;

}



.slider img{
	max-width: 100%;
	height: auto;
	overflow: hidden;

}




.table{

	height: 330px;
	overflow: auto;
	display: inherit;

	font-family: 'Nanum Gothic Coding', monospace; 
}


table th{

	background-color: #011627;
	color: white;
	border: 1px solid #011627;

}

.table a{

	color: black;
}


.recruit_table{
	height: 330px;
	margin-right: 5px;
	width: 450px;
	min-width: 450px;
	float: left;
	position: relative;
	left: 50%;
}

.review_table{
	
	height: 330px;
	margin-left: 5px;
	width: 450px;
	min-width: 450px;
	float: left;
	position: relative;
	left: 50%;
}





</style>

	
	<div class="search_container" style="width: 500px; margin: 0 auto;">

		<form action="/main/search" method="get" onsubmit="return false;">
			<div class="search input-group mb-1">
				<input type="text" name="keyword" class="form-control" id="keyword" width="90%" placeholder="기업을 검색해보세요♬" aria-describedby="searchBtn" style="font-size: 15px;">
				<div class="input-group-append">
				<button type="button" class="btn btn-outline-secondary" id="searchBtn" onclick="search()">검색</button>
				</div>

			</div>
		</form>
	</div>

	
<div class="slider_container">

	<div class="slider">
	  <div><img src="/resources/images/images1.png"/></div>
	  <div><img src="/resources/images/images2.png"/></div>
	  <div><img src="/resources/images/bn1.png" width="100%"/></div>
	  <div><img src="/resources/images/bn2.png" width="100%"/></div>
	</div>

</div>
	
	
	
<div class="main_container">
 
 
<div class="recruit_table">
<table class="table table-hover table-condensed table-responsive" >
	<thead class="thead-dark">
		<tr>
			<th colspan="3" style=" text-align: center;">최신 채용공고</th>	
		</tr>
	</thead>
	<tbody>
<c:forEach items="${recruit }" var="i">
	<tr onclick="location.href='/recruitment/view?recruit_no=${i.recruit_no }'">
		<td style="width:20%">${i.recruit_name }</td>
		<td style="width:60%; text-align: center;">${i.recruit_title }</td>
		<td style="width:20%">${i.recruit_position }</td>
	</tr>
</c:forEach>
</tbody>
</table>
</div>

 

<div class="review_table">
<table class="table table-hover table-condensed table-responsive" >
	<thead class="thead-dark">
		<tr >
			<th colspan="3" style="text-align: center;">최신 기업리뷰</th>	
		</tr>
	</thead>
	<tbody>
<c:forEach items="${review }" var="i">
	<tr onclick="location.href='/review/view?reviewno=${i.REVIEW_NO }&tag=${i.REVIEW_TAG }'">
		<td style="width: 20%">${i.REVIEW_TAG }</td>
		<td style="width: 60%; text-align: center;">${i.REVIEW_TITLE }</td>
		<td style="width: 20%">${i.COMPANY_NAME }</td>
	</tr>
</c:forEach>
</tbody>
</table>
</div>


</div>



 
