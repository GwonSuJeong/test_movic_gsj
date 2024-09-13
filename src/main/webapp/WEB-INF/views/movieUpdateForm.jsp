<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="member-content">
    <div class="list-title">
        <h2>영화 수정</h2>
    </div>
    <div class="data-area">
        <c:if test="${empty mlist}">
            <table>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>감독</th>
                    <th>배우</th>
                    <th>국가</th>
                    <th>언어</th>
                    <th>개봉일</th>
                    <th>수정</th>
                </tr>
                <tr>
                    <td colspan="7" class="none-content">
                        <a>영화가 없습니다</a>
                    </td>
                </tr>
            </table>
            <%--                <div class="user-item">--%>
            <%--                    <span class="none-content">회원이 없습니다</span>--%>
            <%--                </div>--%>
        </c:if>
        <c:if test="${!empty mlist}">
            <table cellspacing="0">
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>감독</th>
                    <th>배우</th>
                    <th>국가</th>
                    <th>언어</th>
                    <th>개봉일</th>
                    <th>수정</th>
                </tr>
                <c:forEach var="list" items="${mlist}">
                    <tr>
                        <td class="memlist">
                            <a>${list.m_code}</a>
                        </td>
                        <td class="memlist">
                            <a>${list.m_title}</a>
                        </td>
                        <td class="memList">
                            <a>${list.m_director}</a>
                        </td>
                        <td class="memList">
                            <a>${list.m_actor}</a>
                        </td>
                        <td class="memlist">
                            <a>${list.m_nation}</a>
                        </td>
                        <td class="memlist">
                            <a>${list.m_language}</a>
                        </td>
                        <td class="memlist">
                            <a>${list.m_open}</a>
                        </td>
                        <td class="memList">
                            <img alt="삭제" src="images/delete.png" class="deletebtn"
                                 onclick="updateMovie(${list.m_code})"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
    <div class="paging-area">
        <div class="paging">${paging}</div>
    </div>
</div>
