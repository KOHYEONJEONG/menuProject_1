package com.menu.dao;

import com.menu.vo.MenuCodeVo;
import com.menu.vo.MenuDBVO;
import com.menu.vo.SearchData;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface IMenuDao {

    public ArrayList<MenuDBVO> selectMenuListTest();

    public ArrayList<MenuDBVO> selectMenuList(SearchData vo);

    public ArrayList<MenuCodeVo> selectCode();
}
