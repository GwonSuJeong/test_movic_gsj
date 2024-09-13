<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="member-content">
    <div class="list-title">
        <h2>회원 목록</h2>
    </div>
    <div class="data-area">
        <c:if test="${empty pList}">
            <table>
                <tr>
                    <th>ID</th>
                    <th>이름</th>
                </tr>
                <tr>
                    <td colspan="2" class="none-content">
                        <a>회원이 없습니다</a>
                      </td>
                </tr>
            </table>
            <%--                <div class="user-item">--%>
            <%--                    <span class="none-content">회원이 없습니다</span>--%>
            <%--                </div>--%>
        </c:if>
        <c:if test="${!empty pList}">
            <table cellspacing="0">
                <tr>
                    <th>번호</th>
<%--                    <th>ID</th>--%>
                    <th>회원 아이디</th>
                    <th>영화 번호</th>
                    <th>영화 이름</th>
<%--                    <th>구매 확인</th>--%>
                </tr>
                <c:forEach var="list" items="${pList}">
                    <tr>
                        <td class="memlist" id ="memnum">
                            <a id="viewnum"></a>
                        </td>
<%--                        <td class="memlist">--%>
<%--                            <a>${list.p_code}</a>--%>
<%--                        </td>--%>
                        <td class="memList">
                            <a>${list.p_id}</a>
                        </td>
                        <td class="memList">
                            <a>${list.p_payment}</a>
                        </td>
                        <td class="memlist">
                            <a>${list.m_title}</a>
                        </td>
<%--                        <td class="memList">--%>
<%--                            <img alt="삭제" src="images/delete.png" class="deletebtn"/>--%>
<%--                        </td>--%>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
    <div class="paging-area">
        <div class="paging">${paging}</div>
    </div>
</div>
