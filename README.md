# lezhin
팔로우, 포스팅, 뉴스 피드 기능이 포함된 타임라인 서비스를 위한 API 개발<br>

* api 테스트의 경우 intellij IDE를 사용하시는 경우 "user.http" 파일을 이용하시고, 아닌경우 postman 등을 이용해주세요.<br>

- 포스팅관련 api<br>
  조회 : GET /api/post/{id}<br>
  등록 : POST /api/post<br>
  수정 : PUT /api/post<br>
  삭제 : DELETE /api/post/{id}<br>
  
- 뉴스피드 api<br>
  조회 : GET /api/user/{id}/following/posts<br>
  
- 팔로우관련 api<br>
  팔로우 : POST /api/user/{id}/follow/{followId}<br>
  언팔로우 : POST /api/user/{id}/unfollow/{unfollowId}<br>
