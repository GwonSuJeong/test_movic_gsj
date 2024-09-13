<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>My Page</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/mypage.css">
    <link rel="icon" href="images/mlogo.png">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
<c:import url="header.jsp"/>
<div class=layout>

            <br><br>
            <h1 class="info"></h1>
            <div class="sign-form" align="center" >
                <div class="">
                    <label th:for="uname">이름</label>
                    <input class="acc" type="text" placeholder="이름" name="uname" value="${nowUser.uname}">
                </div>
                <div class="" >
                    <label th:for="uid">아이디</label>
                    <input class="acc" type="text" placeholder="아이디" name="uid" value="${nowUser.uid}">
                </div>
                <div class="" >
                    <label th:for="ugender">성별</label>
                    <input class="acc" type="text" placeholder="성별" name="ugender" value="${nowUser.ugender}">
                </div>
                <div class="" >
                    <label th:for="uage">나이</label>
                    <input class="acc"  type="text" placeholder="나이" name="uage" value="${nowUser.uage}">
                </div>
                <div class="" >
                    <label th:for="uphonenum">전화번호</label>
                    <input class="acc" type="text" placeholder="전화번호" name="uphonenum" value="${nowUser.uphonenum}">
                </div>
                <div class="" >
                    <label th:for="uaddr">주소</label>
                    <input class="acc" type="text" placeholder="주소" name="uaddr" value="${nowUser.uaddr}">
                </div>
                <button type="button" class="navyBtn" onclick="location.href='main'">메인으로</button>
        </div>

</div>
<c:import url="footer.jsp"/>
</body>
<script>
    $("#logout").click(function (){
        console.log(${nowUser.uid});

        if(confirm("로그아웃 하시겠습니까?")) {
            location.href = "logout";
        }
    });

    $("#myinfo").click(function (){
        console.log(${nowUser.uid});
        location.href = "mypage";

    });
</script>
</html>
