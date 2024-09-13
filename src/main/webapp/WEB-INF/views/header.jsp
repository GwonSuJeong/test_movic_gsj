<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="top-nav">
    <div class="top-bar">
        <img alt="로고" src="images/mlogo.png" class="logo"
             onclick="location.href='/main'">
        <h2>Movic</h2>
        <ul>
            <ul><h2 onclick="location.href='/main'">영화</h2></ul>
            <ul><h2 onclick="location.href='/bookmarkPage'">찜한 영화</h2></ul>
            <ul><h2 onclick="location.href='/popularMoviePage'">인기 영화</h2></ul>
        </ul>


    </div>
    <div class="second-top-bar">
        <div class="search">
            <form onsubmit="return false;">
                <input id="sc-in" type="text" class ="sc-input" name="keyword" placeholder="제목, 배우" value="${keyword}">
                <input type="button" class="sc-btn" value="검색">
            </form>
        </div>
        <li id="mypage"><a>마이페이지 <span>▼</span></a>
            <ul >
                <li><h5>${nowUser.uname}님 환영합니다</h5></li>
                <li id="myinfo">내 정보</li>
                <li id="logout">로그아웃</li>
            </ul>
        </li>
    </div>
</div>