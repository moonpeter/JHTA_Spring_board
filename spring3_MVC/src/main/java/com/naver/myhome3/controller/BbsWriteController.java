package com.naver.myhome3.controller;

import com.naver.myhome3.model.BbsBean2;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BbsWriteController extends AbstractController {
    private BbsBean2 b;
    public void setBbsBean(BbsBean2 b) {
        this.b = b;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id").trim();
        String pass = request.getParameter("pass").trim();

        b.setId(id);
        b.setPass(pass);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");

        mv.addObject("bkey", b);

        return mv;
    }
}
