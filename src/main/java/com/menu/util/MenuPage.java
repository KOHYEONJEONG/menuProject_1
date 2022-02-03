package com.menu.util;

import com.menu.controller.HomeController;
import com.menu.vo.DayMeal;
import com.menu.vo.MealDivision;
import com.menu.vo.WeekMenuTable;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuPage {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    //페이징한 값 저장하게.
    //요일별로 행수?를 구해서 max값을 기준으로 길어지면 다음페이지에 출력하게
    int maxPage = 0;//요일 중 row가 가장 큰 값
    int currentPage = 1;//페이지
    int remainder =0 ;//나머지값
    int pageBlock = 20;//보여질 범위

    List<WeekMenuTable> weekMenuTableList =new ArrayList<>();//list 하나당 한페이지

    WeekMenuTable weekMenuTable = new WeekMenuTable();

    public MenuPage(WeekMenuTable weekMenuTable){
        this.weekMenuTable = weekMenuTable;
    }
    public MenuPage(){};
    
    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageBlock() {
        return pageBlock;
    }

    public void setPageBlock(int pageBlock) {
        this.pageBlock = pageBlock;
    }

    public List<WeekMenuTable> getWeekMenuTableList() {
        return weekMenuTableList;
    }

    public void setWeekMenuTableList(List<WeekMenuTable> weekMenuTableList) {
        this.weekMenuTableList = weekMenuTableList;
    }

    public MenuPage paging(){
        MenuPage menuPage = new MenuPage();
        
        // int size =  weekMenuTable.getMdList().get(0).getDayMealList().get(0).getMenuRecipeList().size();
        //logger.info("paging() : "+String.valueOf(size));

        int cntB = 0;
        int cnt = 0;
        for(MealDivision md: weekMenuTable.getMdList()){
            MealDivision mdNew = new MealDivision(); // 각 식사구분에 새로 담아주려고
            //++cntB;

            for(DayMeal dayMeal:md.getDayMealList()){
                DayMeal dmNew = new DayMeal();
                //++cnt;

                int max = 0;

                for(int i=1; i<=dayMeal.getMenuRecipeList().size(); i++){
                    /*ArrayList<Integer> arr = new ArrayList<>();
                    arr.add(dayMeal.getMenuRecipeList().size());
                    max = Collections.max(arr);*/
                }
                logger.info("max : "+String.valueOf(max));
            }
        }

        //logger.info(String.valueOf(cntB));//4
        //logger.info(String.valueOf(cnt));//28(4*7)

        return menuPage;
    }
}
