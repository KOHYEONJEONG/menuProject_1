package com.menu.controller;

import com.menu.service.IMenuService;
import com.menu.service.MenuServiceImpl;
import com.menu.util.MenuPage;
import com.menu.vo.MenuDBVO;
import com.menu.vo.SearchData;
import com.menu.vo.WeekMenuTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    IMenuService service;

    @RequestMapping(value = "/searchMenu.do",method = RequestMethod.GET)
    public String searchMenu(Model model){

        //ArrayList<MenuCodeVo> menuCodeVo = ;
        //logger.info("공통코드 테이블 : "+menuCodeVo.get(0).getCodeNm());//확인해보려고
        model.addAttribute("menuCodeVo",service.selectCode());

        return "searchMenu";
    }

    @RequestMapping("/printMenu.do")
    public String printMenu(Model model, SearchData vo){
        logger.info("getStartDate ---"+vo.getStartDate()+vo.getEndDate()+vo.getMealName());

        MenuServiceImpl menuServiceImpl = new MenuServiceImpl();//메소드 사용하려고

        ArrayList<MenuDBVO> menuVo = service.selectMenuList(vo);

        logger.info("menuVo : "+menuVo.get(0));

        WeekMenuTable weekMenuTable = menuServiceImpl.getTable(vo,menuVo);

        MenuPage menuPage = new MenuPage(weekMenuTable);//생성자 안에서 페이징까지함.

        model.addAttribute("weekMenuTable",weekMenuTable);//기존거

        //테스트..
        model.addAttribute("menuPage",menuPage);

        //return "printMenu";
        return  "printMenu_Test2";
    }

    @RequestMapping("/printMenuTest.do")
    public String printMenu(Model model){

      /*  MenuServiceImpl menuServiceImpl = new MenuServiceImpl();//메소드 사용하려고

        ArrayList<MenuDBVO> menuVo = service.selectMenuListTest();

        WeekMenuTable weekMenuTable = menuServiceImpl.getTable(menuVo);

        model.addAttribute("weekMenuTable",weekMenuTable);*/

        return "printMenuTest";
    }

    
}
