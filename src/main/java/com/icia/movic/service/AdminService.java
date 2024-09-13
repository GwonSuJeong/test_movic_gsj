package com.icia.movic.service;

import com.icia.movic.dao.AdminDao;
import com.icia.movic.dto.PaymentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
@Slf4j
public class AdminService {
    @Autowired
    private AdminDao aDao;

    public String payMovie(PaymentDto payment, RedirectAttributes rttr) {
        log.info("payMovie()");

        String msg = null;
        String view = null;

        try {
            aDao.insertPayment(payment);
            msg = "결제 성공";
//            view = "redirect:main";
        }catch (Exception e){
            e.printStackTrace();
            msg = "결제 실패";
//            view = "redirect:main";
        }
        rttr.addFlashAttribute("msg", msg);
        return view;
    }
}
