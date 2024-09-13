<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="wrap">
    <div class="content">
        <form action="saveProc" method="post" enctype="multipart/form-data">
            <h2 class="form-header">영화 등록</h2>
            <div class="filebox">
                <!-- 파일 입력 처리 영역 -->
                <label for="file">포스터 선택</label>
                <input type="file" name="files" id="file">
                <input type="text" class="upload-name" value="파일 이름" readonly>
            </div>
            <input type="text" class="save-input" name="m_title"
                   autofocus placeholder="제목" required>
            <input type="text" class="save-input" name="m_director"
                   placeholder="감독" required>
            <input type="text" class="save-input" name="m_nation"
                   placeholder="국가" required>
            <input type="text" class="save-input" name="m_language"
                   placeholder="언어" required>
            <select class="save-input" name="m_genre">
                <option value="드라마" selected>드라마</option>
                <option value="액션">액션</option>
                <option value="공포">공포</option>
                <option value="SF">SF</option>
            </select>

            <input type="text" class="save-input" name="m_actor"
                   placeholder="주연배우" required>
            <input type="date" class="save-input" name="m_open"
                   placeholder="개봉일" required>
            <textarea rows="10" class="save-input ta" name="m_synopsis"
                      placeholder="영화 개요"></textarea>
            <div class="btn-area">
                <input type="submit" class="btn-write" value="등록">
                <input type="reset" class="btn-write" value="수정">
                <input type="button" class="btn-write" value="돌아가기" id="backbtn">
            </div>
        </form>
    </div>

</div>