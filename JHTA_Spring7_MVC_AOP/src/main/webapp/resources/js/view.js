function getList(currentPage) {
	$
			.ajax({
				type : "post",
				url : "../comment/list",
				data : {
					"board_num" : $("#board_num").val(),
					"page" : currentPage
				},
				dataType : "json",
				success : function(rdata) {
					$("#count").text(rdata.listcount);
					if (rdata.listcount > 0) {
						$("#comment table").show();
						$("#comment tbody").empty();
						$(rdata.list)
								.each(
										function() {
											output = '';
											img = '';
											if ($("#loginid").val() == this.id) {
												img = "<img src='../resources/image/pencil2.png' width='15px' class='update'>"
														+ "<img src='../resources/image/delete.png' width='15px' class='delete'>"
														+ "<input type='hidden' value='"
														+ this.num + "' >";
											}
											output += "<tr><td>" + this.id
													+ "</td>";
											output += "<td></td>";
											output += "<td>" + this.reg_date
													+ img + "</td></tr>";
											$("#comment tbody").append(output);
											$("#comment tbody tr:last").find(
													"td:nth-child(2)").text(
													this.content);
										}); // each end

						if (rdata.listcount > rdata.list.length) {
							$("#message").text("더보기")
						} else {
							$("#message").text("")
						}
					} else {
						$("#message").text("등록된 댓글이 없습니다.")
						$("#comment tbody").empty();
						$("#comment table").hide()
					}
				}
			})
}

$(function() {
	$("#comment table").hide(); // 1
	var page = 1; // 더보기에서 보여줄 페이지를 기억하는 변수
	count = parseInt($("#count").text());
	if (count != 0) { // 댓글 갯수가 0이 아니
		getList(1); // 첫 페이지의 댓글을 구해옵니다.(한페이지에 3개씩 가져옵니다.)
	} else { // 댓글이 없는 경우
		$("#message").text("등록된 댓글이 없습니다.")
	}

	$("#message").click(function() {
		getList(page++)
	});

	// 글자수 50개 제한하는 이벤트
	$('#content').on('keyup', function() {
		var length = $(this).val().length;
		if (length > 50) {
			length = 50;
			content = content.substring(0, length);
		}
		$(".float-left").text(length + "/50");
	});

//	 $("#write").click(function() {
//	 var content = $('#content').val();
//	 if (content == null) {
//	 alert("댓글을 입력하세요.")
//	 return;
//	 }})
	//
	// $.ajax({
	// url : 'commentAdd',
	// data : {
	// id : $("#loginid").val(),
	// content : content,
	// board_num : $("#board_re_ref").val()
	// },
	// type : 'post',
	// success : function(rdata) {
	// if (rdata == 1) {
	// getList(page);
	// }
	// }
	// })
	// $('#content').val(''); // textarea 초기화
	// $('.float_left').text(''); // 입력한 글 카운트 초기화
	// })

	// 등록 또는 수정완료 버튼을 클릭한 경우
	// 버튼의 라벨이 '등록'인 경우는 댓글을 추가하는 경우
	// 버튼의 라벨이 '수정완료'인 경우는 댓글을 수정하는 경우
	$("#write").click(function() { // id가 write인 버튼을 클릭시 함수 실행
		buttonText = $("#write").text(); // 버튼의 라벨로 add할지 update할지를 결정
		content = $("#content").val(); // 작성한 댓글을 가져옴
		$(".float-left").text('총 50자까지 가능합니다.'); // 댓글수를 알려주는 버튼을 50자제한 안내문구로
													// 변경

		if (buttonText == "등록") { // 댓글을 추가하는 경우
			url = "../comment/add";
			data = { // 댓글 등록 시 넘어가는 데이터
				"id" : $("#loginid").val(), // 댓글 작성자
				"content" : content, // 댓글 내용
				"board_num" : $("#board_num").val()
			// 댓글을 단 게시글의 번호
			};
		} else { // 댓글을 수정하는 경우
			url = "../comment/update";
			// 댓글 수정 시 넘어가는 데이터
			data = {
				// 댓글 번호?
				"num" : num,
				// 수정된 댓글 내용
				"content" : content
			};
			// 다시 등록으로 변경
			$("#write").text("등록"); 
		}

		$.ajax({
			type : "post",
			url : url,
			data : data,
			success : function(result) {
				$("#content").val('')
				if(result ==1) {
					getList(page);	
				}
			}
		})
	})
	
	// pencil2.png를 클릭하는 경우(수정)
	$("#comment").on('click', '.update', function() {
		// 선택한 내용을 가져옴
		before = $(this).parent().prev().text();
		// textarea에 수정전 내용을 보여줌
		$("#content").focus().val(before);
		//수정할 댓글 번호를 저장
		num = $(this).next().next().val();
		//등록버튼의 라벨을 '수정완료'로 변경
		$("#write").text("수정완료");
		
		//not(this) : 테이블의 <tr>중에서 현재 선택한 <tr>을 제외한 <tr>에 배경색을 흰색으로 설정
		//즉, 선택한 수정(.update)만 'lightgray'의 배경색으로 나타나도록 하고 선택하지 않은 수정의 <tr>엘리먼트는 흰색으로 설정
		$("#comment .update").parent().parent().not(this).css('backgrount', 'white');
		
		// 수정할 행의 배경색을 변경함.
		$(this).parent().parent().css('backgrount', 'lightgray');
	})
	
	$("#comment").on('click', '.delete', function() {
		//수정할 댓글 번호를 저장
		num = $(this).next().val();
		alert("정말 삭제하시겠습니까?")
		$.ajax({
			type : "post",
			url : "../comment/delete",
			data : {"num" : num},
			success : function(result) {
				if(result ==1) {
					getList(page);	
				}
			}
		})
	})
})
