<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="outer-stream">
    <div class="streaming-sub">
        <div class="streaming-title"></div>
        <div class="streaming-content">
            <c:if test="${empty movic.p_sysname}">
                <img class="poster" src="images/no-image.jpg">
            </c:if>
            <c:if test="${!empty movic.p_sysname}">
                <a href="download?m_code=${movic.m_code}">
                    <img class="poster" src="upload/${movic.p_sysname}">
                </a>
            </c:if>
        </div>
    </div>

    <div class="in-stream">
        <div class="streaming-sub">
            <div class="streaming-content2">${movic.m_title}</div>
        </div>
        <div class="sbtn-area">
            <!-- 로그인 완료 후 if문으로 버튼 활성화,비활성화 조정 -->
            <button class="btn-streaming" id="bookmark" onclick="saveBookMark(${movic.m_code})">찜하기</button>
            <button class="btn-streaming" id="payment" onclick="payment(${movic.m_code})">결제</button>
            <button class="btn-streaming" id="play" onclick="location.href='images/testmovie.mp4'">재생
            </button>
        </div>
    </div>
    <hr>
    <div class="detail-sub">
        <div class="detail-title">감독 :</div>
        <div class="detail-content" style="display: inline-block">${movic.m_director}</div>
    </div>
    <div class="detail-sub">
        <div class="detail-title">주연 :</div>
        <div class="detail-content">${movic.m_actor}</div>
    </div>
    <div class="detail-sub">
        <div class="detail-title">국가 :</div>
        <div class="detail-content">${movic.m_nation}</div>
    </div>
    <div class="detail-sub">
        <div class="detail-title">장르 :</div>
        <div class="detail-content">${movic.m_genre}</div>
    </div>
    <div class="detail-sub">
        <div class="detail-title">개봉일 :</div>
        <div class="detail-content">${movic.m_open}</div>
    </div>
    <div class="detail-synopsis">
        <div class="synopsis-title">시놉시스 :</div>
        <div class="synopsis-content">${movic.m_synopsis}</div>
    </div>
</div>