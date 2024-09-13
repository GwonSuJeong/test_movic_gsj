package com.icia.movic.service;

import com.icia.movic.dao.MovicDao;
import com.icia.movic.dto.BookMarkDto;
import com.icia.movic.dto.MovicDto;
import com.icia.movic.dto.UserDto;
import com.icia.movic.util.PagingUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MovicService {
    @Autowired
    private MovicDao mDao;

    private int listCnt = 20;
    private String listTemp;

    public void getMovic(Integer pageNum,
                         String keyword,
                         String m_genre,
                         Model model,
                         HttpSession session) {
        log.info("getMovic()" + pageNum+keyword);


        if(pageNum == null){
            pageNum = 1;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("pageNum", (pageNum - 1) * listCnt);
        map.put("listCnt", listCnt);
        map.put("keyword", keyword);
        map.put("genre", m_genre);
        listTemp = "./main?";
        if(keyword!=null && keyword != ""){
            listTemp += "keyword=" + keyword;
            if(m_genre != null && m_genre != ""){
                listTemp += "&m_genre=" + m_genre +"&";
            } else {
                listTemp += "&";
            }
        } else{
            if(m_genre != null && m_genre != ""){
                listTemp += "m_genre=" + m_genre +"&";
            }
        }
        map.put("listName", listTemp);
        log.info("map={}", map);


        List<MovicDto> movicList = mDao.selectMovicList(map);

        map.put("pageNum", pageNum);//
        String pageHtml = getPaging(map);
        log.info("pageHtml={}", pageHtml);

        model.addAttribute("mlist", movicList);
        model.addAttribute("paging", pageHtml);
        session.setAttribute("genre", m_genre);
        session.setAttribute("keyword", keyword);

    }

    public void getAdMovic(Integer menu,
                         Integer pageNum,
                         String keyword,
                         String m_genre,
                         Model model,
                         HttpSession session) {
        log.info("getMovic()" + pageNum+keyword);
        if(menu==null){
            return;
        }

        if(menu == 4 || menu == 5){
            return;
        }
        if(pageNum == null){
            pageNum = 1;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("pageNum", (pageNum - 1) * listCnt);
        map.put("listCnt", listCnt);
        map.put("keyword", keyword);
        map.put("genre", m_genre);
        listTemp = "./admain?menu="+menu+"&";
        if(keyword!=null && keyword != ""){
            listTemp += "keyword=" + keyword;
            if(m_genre != null && m_genre != ""){
                listTemp += "&m_genre=" + m_genre +"&";
            } else {
                listTemp += "&";
            }
        } else{
            if(m_genre != null && m_genre != ""){
                listTemp += "m_genre=" + m_genre +"&";
            }
        }
        map.put("listName", listTemp);
        log.info("map={}", map);


        List<MovicDto> movicList = mDao.selectMovicList(map);

        map.put("pageNum", pageNum);//
        String pageHtml = getPaging(map);
        log.info("pageHtml={}", pageHtml);

        model.addAttribute("mlist", movicList);
        model.addAttribute("paging", pageHtml);
        session.setAttribute("genre", m_genre);
        session.setAttribute("keyword", keyword);

    }


    public void getMovicBookmark(Integer pageNum,
                         String keyword,
                         String m_genre,
                         Model model,
                         HttpSession session) {
        log.info("getMovicBookmark()" + pageNum+keyword);
        if(session.getAttribute("nowUser") == null) {
            return;
        }
        UserDto nowUser = (UserDto) session.getAttribute("nowUser");
        log.info("아이디" + nowUser.getUid());

        if(pageNum == null){
            pageNum = 1;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("pageNum", (pageNum - 1) * listCnt);
        map.put("listCnt", listCnt);
        map.put("keyword", keyword);
        map.put("genre", m_genre);
        map.put("uid", nowUser.getUid());
        listTemp = "./bookmarkPage?";

        if(keyword!=null && keyword != ""){
            listTemp += "keyword=" + keyword;
            if(m_genre != null && m_genre != ""){
                listTemp += "&m_genre=" + m_genre +"&";
            } else {
                listTemp += "&";
            }
        } else{
            if(m_genre != null && m_genre != ""){
                listTemp += "m_genre=" + m_genre +"&";
            }
        }
        map.put("listName", listTemp);
        log.info("map={}", map);


        List<BookMarkDto> bList = mDao.selectBookmarkList(map);

        map.put("pageNum", pageNum);//
        String pageHtml = getPagingBookmark(map);
        log.info("pageHtml={}", pageHtml);

        model.addAttribute("bList", bList);
        model.addAttribute("paging", pageHtml);
        session.setAttribute("genre", m_genre);
        session.setAttribute("keyword", keyword);

    }

    private String getPagingBookmark(Map<String, Object> map) {
        String pageHtml = null;
        log.info("map={}", map);
        int maxNum = mDao.selectMovicBookmarkCnt(map);
        int pageCnt = 5;

        PagingUtil paging =
                new PagingUtil(maxNum, (int)map.get("pageNum"), listCnt, pageCnt
                        ,(String)map.get("listName"));
        pageHtml = paging.makePaging();

        return pageHtml;
    }


    public void getMovicPopular(Integer pageNum,
                         String keyword,
                         String m_genre,
                         Model model,
                         HttpSession session) {
        log.info("getMovicPopular()" + pageNum+keyword);


        if(pageNum == null){
            pageNum = 1;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("pageNum", (pageNum - 1) * listCnt);
        map.put("listCnt", listCnt);
        map.put("keyword", keyword);
        map.put("genre", m_genre);
        listTemp = "./popularMoviePage?";
        if(keyword!=null && keyword != ""){
            listTemp += "keyword=" + keyword;
            if(m_genre != null && m_genre != ""){
                listTemp += "&m_genre=" + m_genre +"&";
            } else {
                listTemp += "&";
            }
        } else{
            if(m_genre != null && m_genre != ""){
                listTemp += "m_genre=" + m_genre +"&";
            }
        }
        map.put("listName", listTemp);
        log.info("map={}", map);


        List<MovicDto> movicList = mDao.selectMovicListPopular(map);

        map.put("pageNum", pageNum);//
        String pageHtml = getPagingPopular(map);
        log.info("pageHtml={}", pageHtml);

        model.addAttribute("mlist", movicList);
        model.addAttribute("paging", pageHtml);
        session.setAttribute("genre", m_genre);
        session.setAttribute("keyword", keyword);

    }

    private String getPagingPopular(Map<String, Object> map) {
        String pageHtml = null;
        log.info("map={}", map);
        int maxNum = mDao.selectMovicPopularCnt(map);
        int pageCnt = 5;

        PagingUtil paging =
                new PagingUtil(maxNum, (int)map.get("pageNum"), listCnt, pageCnt
                        ,(String)map.get("listName"));
        pageHtml = paging.makePaging();

        return pageHtml;
    }


    private String getPaging(Map<String, Object> map) {
        String pageHtml = null;
        log.info("map={}", map);
        int maxNum = mDao.selectMovicCnt(map);
        int pageCnt = 5;

        PagingUtil paging =
                    new PagingUtil(maxNum, (int)map.get("pageNum"), listCnt, pageCnt
                            ,(String)map.get("listName"));
        pageHtml = paging.makePaging();

        return pageHtml;
    }

    public String saveMovic(List<MultipartFile> files,
                            MovicDto movic,
                            HttpSession session,
                            RedirectAttributes rttr) {
        log.info("saveMovic");

        String msg = null;
        String view = null;

        try {
            if (!files.get(0).isEmpty()) {
                fileUpload(files, session, movic);
            }
            mDao.insertMovic(movic);
            msg = "저장 성공";
            view = "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "저장 실패";
            view = "redirect:saveForm";
        }
        rttr.addFlashAttribute("msg", msg);
        return view;
    }

    private void fileUpload(List<MultipartFile> files,
                            HttpSession session,
                            MovicDto movic) throws IOException {
        log.info("fileUpload");
        String sysname = null;
        String oriname = null; // 원래 파일명

        String realPath = session.getServletContext().getRealPath("/");
        log.info(realPath);
        realPath += "upload/";
        File folder = new File(realPath);
        if (folder.isDirectory() == false) {
            folder.mkdir();
        }

        MultipartFile mf = files.get(0);
        oriname = mf.getOriginalFilename(); // 원래 파일명
        // 변경할 이름에는 밀리초 값을 사용
        sysname = System.currentTimeMillis() +
                oriname.substring(oriname.lastIndexOf("."));

        File file = new File(realPath + sysname);

        mf.transferTo(file);

        movic.setP_sysname(sysname);
//        movic.setP_oriname(oriname);
    }


    public void getMovieDetail(Integer m_code, Model model) {
        log.info("getMovic()");
        MovicDto movie = mDao.selectMovieDetail(m_code);
        model.addAttribute("movic", movie);
    }

    public ModelAndView getContents(Integer m_code) {
        ModelAndView mav = new ModelAndView();
        String view = null;
        MovicDto board = mDao.selectMovieDetail(m_code);
        mav.addObject("movic", board);

        view = "streamingPageBox"; // jsp

        mav.setViewName(view);
        return mav;
    }

    public void deleteData(Integer m_code, HttpSession session, RedirectAttributes rttr) {
        String msg = null;
        String view = null;

        //DB 내용, 파일
        MovicDto movie = mDao.selectMovieDetail(m_code);
        String poster = movie.getP_sysname();

        try {
            if(poster != null){
                fileDelete(poster, session);
            }
            mDao.deleteMovie(m_code);
            msg = "삭제 성공";
            view = "redirect:/";
        } catch (Exception e){
            e.printStackTrace();
            msg = "삭제 실패";
            view = "redirect:/detail?m_code="+m_code;
        }
        rttr.addFlashAttribute("msg",msg);

    }

    private void fileDelete(String poster,
                            HttpSession session) throws Exception {
        log.info("fileDelete()");
        String realPath = session.getServletContext().getRealPath("/");
        realPath += "upload/" + poster;
        File file = new File(realPath);
        if(file.exists()){
            file.delete();
        }
    }

    public String saveBookMark(BookMarkDto bookmark) {
        String res = null;
        try {
            mDao.insertBookMark(bookmark);
            res = "ok";
        }catch (Exception e){
            e.printStackTrace();
            res = "fail";
        }
        return res;
    }

    public String deleteBookMark(List<Integer> bcodes, HttpSession session) {
        String view = null;
        String uid = session.getId(); //session에서 아이디 꺼내기
        try {
            for (int b : bcodes){
                mDao.deleteBookMark(b);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        view = "redirect:/bookmarkPage";
        return view;
    }
}
