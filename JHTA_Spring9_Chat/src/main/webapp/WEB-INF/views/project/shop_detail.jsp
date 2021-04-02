<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
	.container {
/* 		background-color : pink; */
		padding : 10px;
	}
	
	#category-bar {
		border-bottom: solid 1px #dc3545;
	}
</style>

<jsp:include page="/WEB-INF/views/project/header.jsp" ></jsp:include>

<div class="container" id="category-bar">
	<ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
      <li><a href="#" class="nav-link px-3 link-danger">사료</a></li>
      <li><a href="#" class="nav-link px-3 link-danger">간식/건강</a></li>
      <li><a href="#" class="nav-link px-3 link-danger">미용/목욕</a></li>
      <li><a href="#" class="nav-link px-3 link-danger">외출용품</a></li>
      <li><a href="#" class="nav-link px-3 link-danger">의류/악세사리</a></li>
      <li><a href="#" class="nav-link px-3 link-danger">장난감</a></li>
      <li><a href="#" class="nav-link px-3 link-danger">생활용품</a></li>
    </ul>
</div>

<div class="container">
  <div class="row">
    <div class="col-4 bg-secondary">
		<img class="card-img-top" src="${pageContext.request.contextPath}/resources/image/mainPage_icon.png" alt="" height="200px">
    </div>
    <div class="col-8 bg-primary">
    	<div class="bg-success">Test Subject</div>
    	<div class="bg-warning">판매가격 </div>
    	<div class="bg-success">원산지 </div>
    	<div class="bg-warning">브랜드 </div>
    	<div class="bg-success"><small class="text-danger">평점 : &#9733; &#9733; &#9733; &#9733; &#9733;</small></div>
    </div>
  </div>
</div>

<div class="container bg-info d-flex flex-row-reverse bd-highlight">
	<button type="button" class="btn btn-danger me-2">장바구니</button>
	<button type="button" class="btn btn-outline-danger">바로구매</button>
</div>

<div class="container">
  <div class="row">
    <div class="col-sm bg-warning">상품설명</div>
    <div class="col-sm bg-success">상품리뷰(2)</div>
    <div class="col-sm bg-warning">배송/교환/환불</div>
  </div>
</div>

<div class="container bg-secondary">
	<img class="card-img-top" src="${pageContext.request.contextPath}/resources/image/mainPage_icon.png" alt="" height="300px">
	<img class="card-img-top" src="${pageContext.request.contextPath}/resources/image/mainPage_icon.png" alt="" height="300px">
	<img class="card-img-top" src="${pageContext.request.contextPath}/resources/image/mainPage_icon.png" alt="" height="300px">
</div>

<div class="container">
  <div class="row">
    <div class="col-md-7 bg-warning">상품후기</div>
    <div class="col-sm bg-success">최신순</div>
    <div class="col-sm bg-warning">별점 높은순</div>
    <div class="col-sm bg-success">별점 낮은순</div>    
  </div>
  <div class="row bg-danger">
      <div class="col-md-2 bg-warning">Best</div><div class="col-sm bg-secondary">맛있습니다.</div>
  </div>
  <div class="row bg-danger">
      <div class="col-md-2 bg-warning">Best</div><div class="col-sm bg-secondary">맛있습니다.</div>
  </div>
  <div class="row bg-danger">
      <div class="col-md-2 bg-warning">Best</div><div class="col-sm bg-secondary">맛있습니다.</div>
  </div>

  <div class="row bg-danger">
	<p>페이징 처리할 곳</p>
  </div>

  <div class="row bg-danger">
	<div class="container bg-info d-flex flex-row-reverse bd-highlight">
		<button type="button" class="btn btn-danger me-2">리뷰작성</button>
	</div>
  </div>
  
</div>

