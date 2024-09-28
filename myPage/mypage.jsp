<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- 절대경로 -->
<c:set var="root" value="${pageContext.request.contextPath}/" />

<html style="font-size: 16px;" lang="ja-JP">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>Mypage</title>
    
   <!-- 탑메뉴 css -->
    <link rel="stylesheet" href="${root}css/nicepage.css" media="screen">
   <link rel="stylesheet" href="${root}css/myPage/Mypage.css" media="screen">
   
   <!-- Bootstrap CDN -->
    <script class="u-script" type="text/javascript" src="${root}js/nicepage.js" defer></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <meta name="generator" content="Nicepage 6.16.8, nicepage.com">
    
    <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,500,500i,600,600i,700,700i,800,800i">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        
    <script type="application/ld+json">{
      "@context": "http://schema.org",
      "@type": "Organization",
      "name": "SportsLink",
      "logo": "/images/default-logo.png",
      "sameAs": []
   }
</script>
   <meta name="theme-color" content="#2676c1">
    <meta property="og:title" content="Mypage">
    <meta property="og:description" content="">
    <meta property="og:type" content="website">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta data-intl-tel-input-cdn-path="intlTelInput/">
  
  <style>
    .u-textarea {
        width: 100%;
        height: 100px;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 30px;
        font-size: 12px;
        font-family: 'IBMPlexSansJP-Regular';
        src: url('../fonts/IBMPlexSansJP-Regular.ttf');
        resize: vertical; /* 사용자 정의 크기 조정 허용 (세로 방향만) */
        background-color: #f9f9f9; /* 배경 색상 */
        box-shadow: inset 0 1px 3px rgba(0,0,0,0.1); /* 내부 그림자 */
        transition: border-color 0.3s ease, box-shadow 0.3s ease; /* 포커스 시 트랜지션 */
         margin-top: 30px; /* 윗 요소와의 여백 */
    }

    .u-textarea:focus {
    
        border-color: #2676c1; /* 포커스 시 테두리 색상 변경 */
        box-shadow: 0 0 5px rgba(38, 118, 193, 0.5); /* 포커스 시 그림자 효과 */
        outline: none; /* 기본 포커스 아웃라인 제거 */
    }

    .u-textarea-1 {
        /* 추가적인 커스텀 스타일이 필요한 경우 */
    }
</style>
</head>


 <body data-path-to-root="/" data-include-products="false" class="u-body u-xl-mode">
  

    <!-- 상단메뉴 -->
   <c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>

   <!-- 왼쪽 사이드바 -->
   <c:import url="/WEB-INF/views/include/side_menu_mypage.jsp"></c:import>
      
   <!-- 프로필 영역 -->
   <section class="u-clearfix u-lightbox u-valign-middle u-section-1" id="sec-ebef" style="margin-top: 520px">
 <h1 class="u-text u-text-default u-text-palette-1-base u-text-1">マイページ </h1>
      <div class="custom-expanded data-layout-selected u-clearfix u-gutter-0 u-layout-wrap u-layout-wrap-1">
        <div class="u-layout">
          <div class="u-layout-row">
          <div class="u-align-center u-container-align-center u-container-style u-layout-cell u-left-cell u-palette-1-light-3 u-radius u-shape-round u-size-23 u-layout-cell-1">
    <div class="u-border-3 u-border-no-right u-border-palette-1-base u-container-layout u-container-layout-1" style="padding: 15px 30px;">
        
        <div   class="custom-expanded u-align-center u-image u-image-circle u-image-1"
            data-image-width="256" data-image-height="256">      
            <c:choose>
               <c:when test="${loginUserBean.profileImagePath != null}">
               <!-- 사용자가 업로드한 이미지가 있는 경우 -->
               <img id="profileImage" class="profile-image" src="${root}upload/${img}" style="width: 100%; height: 100%; object-fit: cover; border-radius: 50%;">
               </c:when>
               <c:otherwise>
               <!-- 사용자가 업로드한 이미지가 없는 경우 기본 이미지 -->
                  <img id="profileImage" class="profile-image" src="${root}images/default_image.jpg" style="width: 100%; height: auto; object-fit: cover; border-radius: 50%; ">
                  <form:errors path="profileImage" cssClass="text-danger" />
            </c:otherwise>
            </c:choose>
         </div>
      
   <!-- 프로필 이미지 변경 버튼 -->
   <button id="changeImageBtn" 
   class="u-border-1 u-border-palette-1-light-1 u-btn u-button-style u-custom-font u-text-palette-1-dark-2 u-white u-btn-1" style="margin-top: 20px">プロフィール画像変更</button>

   <!-- 파일 입력 요소 (숨김) -->
   <input type="file" id="imageFileInput" name="file"   accept="image/*" style="display: none;">
                     
   </div>
   </div>
  <div class="u-align-left u-container-align-left u-container-style u-layout-cell u-palette-1-light-3 u-radius u-right-cell u-shape-round u-size-37 u-layout-cell-2">
   <div class="u-border-3 u-border-no-left u-border-palette-1-base u-container-layout u-container-layout-2">
   <h3 class="u-align-left u-text u-text-default u-text-2"> ${loginUserBean.user_name} 様</h3>
  <p class="u-align-left u-text u-text-custom-color-1 u-text-4">" ${introduction} "</p>
       
           <!-- 기존 -->
      <form id="introEditForm" action="${root}myPage/mypage" method="post" onsubmit="return validateIntroEditForm()">
           <textarea name="introEdit" id="introEdit" class="u-textarea u-textarea-1"></textarea>
           <button class="u-border-1 u-border-palette-1-light-1 u-btn u-button-style u-custom-font u-text-palette-1-dark-2 u-white u-btn-1" type="submit">自己紹介変更</button>
         </form>
        

    </div>
    </div>
    </div>
     </div>
     </div>
      </section>
   
 <section class="u-clearfix u-section-4" id="sec-7e01" style="margin-left: 500px;"> <!-- 사이드바 공간 확보 -->
    <div class="container-fluid" style="margin-top: 40px; max-width: 72%;">
        <div class="row">
           

<!-- 세 번째 카드: 신청 자원봉사 -->
   <div class="col-lg-6 col-md-6 col-sm-12 mb-4">
    <div class="card shadow-sm" style="border-radius: 50px; overflow: hidden; transition: transform 0.3s ease; border: 3px solid #2980b9; margin-bottom: 50px;">
        <div class="card-header" style="background-color: #EDF6FF; padding: 20px; text-align: center;">
            <h4 class="card-title" style="color: #2c3e50; font-size: 1.8rem; font-weight: bold;">申請したボランティア</h4>
        </div>
        <div class="card-body" style="padding-bottom: 20px;"> <!-- margin-bottom 제거 -->
            <table class="table table-hover" style="width: 100%; text-align: center;">
                <thead style="background-color: #2980b9; color: white;">
                    <tr>
                        <th>ボランティアID</th>
                        <th>大会ID</th>
                        <th>大会名</th>
                        <th>開催日</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- 자원봉사 5개까지만 출력 -->
                    <c:set var="count" value="0" />
                    <c:forEach var="volunteer" items="${myVolunteerList}">
                        <c:if test="${count < 5}">
                            <tr>
                                <td>${volunteer.volunteer_id}</td>
                                <td>${volunteer.contest_id}</td>
                                <td>
                                    <a href="${root}board/volunteer_viewMore?contestName=${volunteer.contest_name}" style="color: #2980b9; text-decoration: none;">
                                        ${volunteer.contest_name}
                                    </a>
                                </td>
                                <td>${volunteer.start_date}</td>
                            </tr>
                            <c:set var="count" value="${count + 1}" />
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
            <c:set var="user_idx" value="${user_idx}" />
            <!-- 더보기 버튼 -->
            <c:if test="${fn:length(myVolunteerList) > 5}">
                <div style="text-align: center; margin-top: 20px;">
                    <a href="${root}myPage/volunteerList?user_idx=${user_idx}" class="btn btn-outline-primary" style="color:white; background-color: #2980b9; padding: 10px 20px; border-radius: 25px;">もっと見る</a>
                </div>
            </c:if>
        </div>
    </div>
</div>



<!-- 네 번째 카드: 내가 쓴 게시물 -->
   <div class="col-lg-6 col-md-6 col-sm-12 mb-4">
    <div class="card shadow-sm" style="border-radius: 50px; overflow: hidden; transition: transform 0.3s ease; border: 3px solid #2980b9; margin-bottom: 50px;">
        <div class="card-header" style="background-color: #EDF6FF; padding: 20px; text-align: center;">
            <h4 class="card-title" style="color: #2c3e50; font-size: 1.8rem; font-weight: bold;">書いた投稿</h4>
        </div>
        <div class="card-body" style="padding-bottom: 20px;">
            <table class="table table-hover" style="width: 100%; text-align: center;">
                <thead style="background-color: #2980b9; color: white;">
                    <tr>
                        <th>NO.</th>
                        <th>投稿のタイトル</th>
                        <th>登録日</th>
                    </tr>
                </thead>
                <tbody>
             <c:if test="${empty myPostList}">
    <tr>
        <td colspan="3" class="text-center">投稿がありません。</td>
    </tr>
</c:if>

<c:set var="count" value="0" />

<!-- 게시물 5개까지만 출력 -->
<c:forEach var="content" items="${myPostList}">
    <c:if test="${count < 5}">
        <tr>
            <td>${content.post_id}</td>
            <td>
                <!-- board_info_idx와 post_id를 올바르게 출력하도록 수정 -->
                <a href="${root}board/read?post_id=${content.post_id}&board_info_idx=${content.board_info_idx}" style="color: #2980b9; text-decoration: none;">
                    ${content.title}
                </a>
            </td>
            <td>${content.writing_time}</td>
        </tr>
        <c:set var="count" value="${count + 1}" />
    </c:if>
</c:forEach>



                </tbody>
            </table>
            <c:set var="board_info_idx" value="${content.board_info_idx}" />
         <!-- 더보기 버튼 -->
         <c:if test="${fn:length(myPostList) > 5}">
             <div style="text-align: center; margin-top: 20px;">
                 <a href="${root}myPage/MyBoardList?user_idx=${user_idx}&page=${currentPage + 1}" class="btn btn-outline-primary" style="color:white; background-color: #2980b9; padding: 10px 20px; border-radius: 25px;">もっと見る</a>
             </div>
         </c:if>

        </div>
    </div>
</div>

        </div>
    </div>
</section>

   <!-- 하단정보 -->
   <c:import url="/WEB-INF/views/include/bottom_info.jsp" />
   
   
    <script>
    function validateIntroEditForm() {
          var introEdit = document.getElementById("introEdit").value.trim();

          // 한줄 소개가 비어있으면 경고 메시지 표시 후 false 반환
          if (introEdit === "") {
              alert("自己紹介を入力してください。");
              return false;
          }

          // 한줄 소개는 최대 100자 제한 (필요에 따라 조정 가능)
          if (introEdit.length > 100) {
              alert("自己紹介は100文字以内で作成してください。");
              return false;
          }

          return true; // 유효성 검사를 통과하면 폼을 제출
      }

      $(document).ready(function() {
          $('#changeImageBtn').click(function() {
              $('#imageFileInput').click();
          });

          $('#imageFileInput').change(function(event) {
              var file = event.target.files[0];

              // 이미지 파일이 선택된 경우에만 유효성 검사 실행
              if (file) {
                  // 파일 크기 검사 (2MB 이하)
                  var maxSize = 2 * 1024 * 1024; // 2MB
                  if (file.size > maxSize) {
                      alert("画像ファイルのサイズは2MB以下のみ可能です。");
                      return;
                  }

                  // 이미지 파일 형식 검사 (jpg, jpeg, png 형식만 허용)
                  var validFileTypes = ['image/jpeg', 'image/png', 'image/jpg'];
                  if (!validFileTypes.includes(file.type)) {
                      alert("jpg、jpeg、png形式の画像ファイルのみアップロード可能です。");
                      return;
                  }

                  var formData = new FormData();
                  formData.append('profileImage', file);

                  $.ajax({
                      url: '${root}myPage/uploadProfileImage',
                      type: 'POST',
                      data: formData,
                      contentType: false,
                      processData: false,
                      success: function(response) {
                          alert('画像が正常に変更されました。');
                          location.reload();
                      },
                      error: function() {
                          alert('画像の変更に失敗しました。');
                      }
                  });
              }
          });
      });
   </script>
</body>
</html>