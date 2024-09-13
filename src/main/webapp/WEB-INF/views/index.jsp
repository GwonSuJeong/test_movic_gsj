<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>MOVIE INFO</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="icon" href="images/mlogo.png">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
    <c:import url="header.jsp"/>
    <div class="content">
        <select id="category" name="columName">
            <option value="%" <c:if test="${empty genre}">
                selected</c:if>
            >전체</option>
            <option value="액션" <c:if test="${genre eq '액션'}">
                selected</c:if>
            >액션</option>
            <option value="드라마" <c:if test="${genre eq '드라마'}">
                selected</c:if>
            >드라마</option>
            <option value="스릴러" <c:if test="${genre eq '스릴러'}">
                selected</c:if>
            >스릴러</option>
            <option value="로맨스" <c:if test="${genre eq '로맨스'}">
                selected</c:if>
            >로맨스</option>
            <option value="SF" <c:if test="${genre eq 'SF'}">
                selected</c:if>
            >SF</option>
        </select>



        <div class="layout">
            <c:if test="${empty mlist}">
                <div class="movie-item">
                    <span class="none-content">등록된 영화가 없습니다.</span>
                </div>
            </c:if>
            <c:if test="${!empty mlist}">
                <c:forEach var="mitem" items="${mlist}">
<%--                    <div class="movie-item">--%>
<%--                        <a href="streamingPage?m_code=${mitem.m_code}">--%>

                        <div class="lightboxbtn" onclick="articleView(${mitem.m_code})">

<%--                            <input id="hidecode" type="hidden" value="${mitem.m_code}">--%>
                            <c:if test="${empty mitem.p_sysname}">
                                <img src="images/no-image.jpg" class="poster-pre">
                            </c:if>
                            <c:if test="${!empty mitem.p_sysname}">
                                <img src="upload/${mitem.p_sysname}" class="poster-pre">
                            </c:if>
                            <div class="info-pre">
                                <div class="title-pre">
                                    <a href="detail?m_code=${mitem.m_code}">
                                    </a>
                                </div>
                            </div>

                        </div>

<%--                    </div>--%>
                </c:forEach>
            </c:if>

        </div>
        <div class="paging-area">
            <div class="paging">${paging}</div>
        </div>
    </div>

    <div id="bg_layer">
        <div id="articleView_layer"></div>
        <div id="contents_layer"></div>
    </div>
    <c:import url="footer.jsp"/>


</body>
<script>
    function saveBookMark(m_code){
        let conf = confirm("북마크 하시겠습니까?");
        if(conf){
            let sendObj = {"b_uid": "${nowUser.uid}", "b_mcode": m_code};
            console.log(sendObj);

            $.ajax({
                url: './saveBookMark',
                type: 'get',
                data: sendObj,
                success: function (res) {
                    if(res == "ok"){
                        let bcnum = {"b_mcode": "${bitem.b_mcode}"};
                        let mcunm = {"m_code": m_code};
                        console.log(bcnum);
                        console.log(mcunm);

                        if (bcnum==null | bcnum != mcunm){
                            let move = confirm("북마크 페이지로 이동하시겠습니까?");
                            if (move){
                                location.href=`./bookmarkPage`;
                                //user01 고칠것!
                            }else {
                                location.href=`./streamingPageBox?m_code=${movic.m_code}`;
                            }
                        }else {
                            alert("이미 북마크 되었습니다.");
                        }
                    } else {
                        alert("북마크 실패");
                    }

                    <%--let move = confirm("북마크 페이지로 이동하시겠습니까?");--%>
                    <%--if (move){--%>
                    <%--    location.href=`./bookmarkPage`;--%>
                    <%--    //user01 고칠것!--%>
                    <%--}else {--%>
                    <%--    location.href=`./streamingPageBox?m_code=${movic.m_code}`;--%>
                    <%--}--%>
                },
                error: function (err) {
                    alert("전송 오류");
                    console.log(err);
                }
            })
        }
    }

    function payment(m_code){
        let conf = confirm("결제할까요?");
        if(conf){
            location.href="./payment?p_payment="+m_code+"&p_id=${nowUser.uid}";
        }
    }


    $(window).scroll(function () {
        scrollValue = $(document).scrollTop();
        console.log(scrollValue);
    });

    $("#articleView_layer").click(function (){
        $("#bg_layer").css("display","none");
        // $('body').css("overflow", "auto");
        // window.scrollY(scrollValue);
    });


    function articleView(num){

        $('#bg_layer').addClass('open');
        $.ajax({
            type:'get',
            url:'contents',
            data:{"m_code":num},
            dataType:'html',
            success:function(data){
                $('#contents_layer').html(data);
                $("#bg_layer").css("display","flex");
                // $('body').css("overflow", "hidden");
                // $('body').css("-ms-overflow-style", "none");
                // document.body.style.overflow = 'hidden';
            },
            error:function(error){
                alert('error');
                console.log(error);
            }
        }); //ajax End
    }

    <%-- 라이트박스    --%>
    $(".movie-item").click(function (){
        let hidecode = $("#hidecode").val();
        console.log(hidecode);
        $.ajax({
            url:"streamingPage",
            type:"post",
            data:{
                "m_code":hidecode
            },
            dataType:'html',
            success:function (result){

                $("#lightbox").css("display","block");
            },
            error:function (){

            }
        });
    });



    $("#category").on("change",function (){
        <%--let pnum = `${pageNum}`;--%>
        <%--let name = './?m_genre=' + $("#category").val();--%>
        <%--if($("#sc-in").val()!=null && $("#sc-in").val()!=""){--%>
        <%--    name += "&keyword=" + $("#sc-in").val();--%>
        <%--}--%>
        <%--console.log(name);--%>

        <%--location.href=name;--%>
        let keyword = $("#sc-in").val();
        let cate = $("#category").val();
        let query = `./main?pageNum=${pageNum}&keyword=\${keyword}&m_genre=\${cate}`;

        location.href=query;
    });

    $(".sc-btn").click(function () {
        let keyword = $("#sc-in").val();
        let cate = $("#category").val();
        let query = `./main?pageNum=${pageNum}&keyword=\${keyword}&m_genre=\${cate}`;
        //alert(query);
        location.href=query;
    });

    $("#sc-in").on('keypress', function (e){
        if(e.keyCode === 13){
            let keyword = $("#sc-in").val();
            let cate = $("#category").val();
            let query = `./main?pageNum=${pageNum}&keyword=\${keyword}&m_genre=\${cate}`;
            //alert(query);
            location.href=query;
        }
    });

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
