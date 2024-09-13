package com.icia.movic.controller;

import com.icia.movic.dto.BookMarkDto;
import com.icia.movic.dto.MovicDto;
import com.icia.movic.dto.PaymentDto;
import com.icia.movic.dto.UserDto;
import com.icia.movic.service.AdminService;
import com.icia.movic.service.MovicService;
import com.icia.movic.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class MovicController {
    @Autowired
    private MovicService mServ;
    @Autowired
    private UserService uServ;

    @Autowired
    private AdminService aServ;

    @GetMapping("/")
    public String index(Model model) {
        return "login";
    }
    @GetMapping("main")
    public String main(
                       Integer pageNum,
                       String keyword,
                       String m_genre,
                       Model model,
                       HttpSession session){
        log.info("home() k : {} {}", keyword ,m_genre);

        mServ.getMovic( pageNum, keyword, m_genre, model,session);
        return "index";
    }

    @GetMapping("join")
    public String join(){
        log.info("join");
        return "join";
    }






    @PostMapping("search")
    public String search(Model model) {
        log.info("search");
        return null;
    }

    @PostMapping("signup")
    public String signUpProc(UserDto user,
                             HttpSession session,
                             RedirectAttributes rttr) {
        log.info("signUpProc");
        uServ.signup(user, session, rttr);
        return "redirect:/";
    }

    @PostMapping("idCheck")
    @ResponseBody
    public String idCheck(String uid) {
        log.info("idCheck() : uid - {}", uid);
        String res = uServ.checkId(uid);
        return res;
    }

    @GetMapping("searchId")
    public String searchId(){
        log.info("searchId()");
        return "searchId";
    }
    @PostMapping("idfocus")
    @ResponseBody
    public String idfocus(UserDto user){
        log.info("idfocus()");
        String uid = uServ.focusId(user);
        return uid;//uid
    }

    //    @GetMapping("login")
//    public String login(String uid, Model model){
//        log.info("login()");
//        model.addAttribute("uid", uid);
//        return "login";
//    }
    @GetMapping("movic")
    public String movic(){
        log.info("movic()");
        return "movic";
    }

    @GetMapping("login")
    public String login(){
        log.info("login()");
        return "login";
    }

    @ResponseBody
    @PostMapping ("loginProc")
    public String loginProc(UserDto user,
                            HttpSession session,
                            RedirectAttributes rttr){
        String msg = null;
        log.info("loginProc() : id - {}, pass - {}",
                user.getUid(), user.getUpass());
        msg = uServ.loginProc(user.getUid(),user.getUpass(),session);
        return msg;//
    }
    @GetMapping("logout")
    public String logout(HttpSession session){
        log.info("logout()");
        session.invalidate();
        return "redirect:/";
    }
    @GetMapping("admain")
    public String admain(Integer menu,Integer pageNum, String keyword,
                         String m_genre, Model model, HttpSession session){
        log.info("admain()");
        uServ.makeForm(menu,model);
        uServ.getUser(menu,pageNum, keyword, model);
        mServ.getAdMovic(menu ,pageNum, keyword, m_genre, model, session);
        return "admain";
    }

    @GetMapping("mypage")
    public String mypage(){
        log.info("mypage()");
        return "mypage";
    }

    @ResponseBody
    @PostMapping("streamingPage")
    public String streamingPage(Integer m_code, Model model){
        log.info("streamingPage()");
        log.info("456"+ m_code);
        mServ.getMovieDetail(m_code, model);
        return "streamingPageBox";
    }

    //관리자 페이지 관련
    @PostMapping("saveProc")
    public String saveProc(@RequestPart List<MultipartFile> files, MovicDto movic,
                           HttpSession session, RedirectAttributes rttr){
        log.info("saveProc");
        String view = mServ.saveMovic(files, movic, session, rttr);
        return "redirect:/admain";
    }

    @ResponseBody
    @GetMapping("contents")
    public ModelAndView contents(Integer m_code) {
        log.info("contents()");
        ModelAndView mav = mServ.getContents(m_code);
        return mav;
    }

    @GetMapping("payment")
    public String payment(PaymentDto payment,
                          RedirectAttributes rttr){
        log.info("로그남기기"+payment.getP_payment()+"아이디"+payment.getP_id());
        String view = aServ.payMovie(payment, rttr);
        return "redirect:/main";
    }

    @GetMapping("deleteData")
    public String deleteData(Integer m_code ,HttpSession session, RedirectAttributes rttr){
        log.info("deleteData()");
        mServ.deleteData(m_code, session, rttr);
        return "redirect:/admain?menu=3";
    }

    @GetMapping("deleteUser")
    public String deleteUser(String uid ,HttpSession session, RedirectAttributes rttr){
        log.info("deleteUser()");
        uServ.deleteUser(uid, session, rttr);
        return "redirect:/admain?menu=4";
    }

    @GetMapping("saveBookMark")
    @ResponseBody
    public String saveBookMark(BookMarkDto bookmark){
        log.info("saveBookMark()");
        String res = mServ.saveBookMark(bookmark);
        return res;
    }

    @GetMapping("bookmarkPage")
    public String bookmarkPage(Integer pageNum,
                               String keyword,
                               String m_genre,
                               Model model,
                               HttpSession session){
        log.info("bookmarkPage()");
        mServ.getMovicBookmark(pageNum, keyword, m_genre, model,session);
        return "bookmark";
    }

    @GetMapping("popularMoviePage")
    public String popularMoviePage(Integer pageNum,
                               String keyword,
                               String m_genre,
                               Model model,
                               HttpSession session){
        log.info("bookmarkPage()");
        mServ.getMovicPopular(pageNum, keyword, m_genre, model,session);
        return "index";
    }

    //북마크 삭제
    @GetMapping("bookmarkDelete")
    public String bookmarkDelete(@RequestParam("bcodes") List<Integer> bcodes,
                                 HttpSession session){
        log.info("bookmarkDelete() : {}", bcodes);
        String view = mServ.deleteBookMark(bcodes, session);
        return view;
    }



}
