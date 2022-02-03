package com.menu.service;

import com.menu.vo.MenuCodeVo;
import com.menu.vo.MenuDBVO;
import com.menu.vo.SearchData;

import java.util.ArrayList;

public interface IMenuService {

    public ArrayList<MenuDBVO> selectMenuListTest();

    public ArrayList<MenuDBVO> selectMenuList(SearchData vo);

    public ArrayList<MenuCodeVo> selectCode();

}
