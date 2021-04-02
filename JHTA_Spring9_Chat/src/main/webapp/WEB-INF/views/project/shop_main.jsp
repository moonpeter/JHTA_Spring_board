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
	<form class="d-flex">
	    <input class="form-control me-2" type="search" placeholder="검색어를 입력하세요." aria-label="Search">
	    <button class="btn btn-danger" type="submit">Search</button>
    </form>
    
    <div class="row" id="row">
    
        <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
                <a href="BoardDetailAction.bo?board_num=${board.board_num}">
                    <img class="card-img-top" src="${pageContext.request.contextPath}/resources/image/mainPage_icon.png" alt="" height="200px">
                </a>
                <div class="card-body">
                    <h4 class="card-title">
                        <a href="BoardDetailAction.bo?board_num=${board.board_num}">Test Subject${board.board_subject}</a>
                    </h4>
                    <h5 style="text-align: right"><fmt:formatNumber value="${board.board_price}" pattern="###,###,###"/> <strong class="text-danger">10,000원</strong></h5>
                    <p class="card-text" style="overflow: hidden; line-height: 1.2; height: 3.6em; display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical;">&nbsp&nbspTest Content${board.board_content}</p>
                </div>
                <div class="card-footer">
					<small class="text-danger">평점 : &#9733; &#9733; &#9733; &#9733; &#9733;</small>
                </div>
            </div>
        </div>

		        <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
                <a href="BoardDetailAction.bo?board_num=${board.board_num}">
                    <img class="card-img-top" src="${pageContext.request.contextPath}/resources/image/mainPage_icon.png" alt="" height="200px">
                </a>
                <div class="card-body">
                    <h4 class="card-title">
                        <a href="BoardDetailAction.bo?board_num=${board.board_num}">${board.board_subject}</a>
                    </h4>
                    <h5 style="text-align: right"><fmt:formatNumber value="${board.board_price}" pattern="###,###,###"/> <strong class="text-danger">10,000원</strong></h5>
                    <p class="card-text" style="overflow: hidden; line-height: 1.2; height: 3.6em; display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical;">&nbsp&nbsp${board.board_content}</p>
                </div>
                <div class="card-footer">
					<small class="text-danger">평점 : &#9733; &#9733; &#9733; &#9733; &#9733;</small>
                </div>
            </div>
        </div>
        
                <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
                <a href="BoardDetailAction.bo?board_num=${board.board_num}">
                    <img class="card-img-top" src="boardupload/${board.board_thumbnail}" alt="" height="200px">
                </a>
                <div class="card-body">
                    <h4 class="card-title">
                        <a href="BoardDetailAction.bo?board_num=${board.board_num}">${board.board_subject}</a>
                    </h4>
                    <h5 style="text-align: right"><fmt:formatNumber value="${board.board_price}" pattern="###,###,###"/> <strong class="text-danger">10,000원</strong></h5>
                    <p class="card-text" style="overflow: hidden; line-height: 1.2; height: 3.6em; display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical;">&nbsp&nbsp${board.board_content}</p>
                </div>
                <div class="card-footer">
					<small class="text-danger">평점 : &#9733; &#9733; &#9733; &#9733; &#9733;</small>
                </div>
            </div>
        </div>
        
                <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
                <a href="BoardDetailAction.bo?board_num=${board.board_num}">
                    <img class="card-img-top" src="boardupload/${board.board_thumbnail}" alt="" height="200px">
                </a>
                <div class="card-body">
                    <h4 class="card-title">
                        <a href="BoardDetailAction.bo?board_num=${board.board_num}">${board.board_subject}</a>
                    </h4>
                    <h5 style="text-align: right"><fmt:formatNumber value="${board.board_price}" pattern="###,###,###"/> <strong class="text-danger">10,000원</strong></h5>
                    <p class="card-text" style="overflow: hidden; line-height: 1.2; height: 3.6em; display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical;">&nbsp&nbsp${board.board_content}</p>
                </div>
                <div class="card-footer">
					<small class="text-danger">평점 : &#9733; &#9733; &#9733; &#9733; &#9733;</small>
                </div>
            </div>
        </div>
	</div>
	

</div>