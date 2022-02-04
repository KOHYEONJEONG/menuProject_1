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
    int maxDay = 0;//요일 중 row가 가장 큰 값
    int page = 1;//페이지
    int remainder =0 ;//나머지값
    int pageBlock = 20;//보여질 범위
    ArrayList<Integer> maxList = new ArrayList<>();

    List<WeekMenuTable> weekMenuTableList =new ArrayList<>();//list 하나당 한페이지

    WeekMenuTable weekMenuTable = new WeekMenuTable();

    public MenuPage(WeekMenuTable weekMenuTable){
        this.weekMenuTable = weekMenuTable;
    }
    public MenuPage(){};
    
    public int getMaxPage() {
        return maxDay;
    }

    public void setMaxPage(int maxDay) {
        this.maxDay = maxDay;
    }

    public int getCurrentPage() {
        return page;
    }

    public void setCurrentPage(int page) {
        this.page = page;
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
        String[] mealString = new String[]{"조식","중식","석식","간식"};
        String[] dayString = new String[]{"월","화","수","목","금","토","일"};

        MenuPage menuPage = new MenuPage();
        
        // int size =  weekMenuTable.getMdList().get(0).getDayMealList().get(0).getMenuRecipeList().size();
        //logger.info("paging() : "+String.valueOf(size));

        int cntB = 0;
        //int cnt = 0;
        int max = 0;
        for(MealDivision md: weekMenuTable.getMdList()){//4번돌고
            MealDivision mdNew = new MealDivision(); // 각 식사구분에 새로 담아주려고

            //logger.info("사이즈: "+md.getDayMealList().size());//7,7,7,7
            int i = 0;
            ArrayList<Integer> arr = new ArrayList<>();
            for(DayMeal dayMeal:md.getDayMealList()){
                DayMeal dmNew = new DayMeal();
                logger.info(mealString[cntB]+"-"+dayString[i]+"요일별 재료 사이즈: "+dayMeal.getMenuRecipeList().size());
                i++;

                arr.add(dayMeal.getMenuRecipeList().size());
                //++cnt;
                //logger.info("size : "+String.valueOf(dayMeal.getMenuRecipeList().size()));
                //logger.info("max : "+String.valueOf(max));
            }

            int max2 = Collections.max(arr);
            logger.info(mealString[cntB]+"최대값 : "+max2);
            maxList.add(max2);
            logger.info("---------");

                //ArrayList<Integer> arr = new ArrayList<>();
                //arr.add(md.getDayMealList().get(i).getMenuRecipeList().size());
                //logger.info("사이즈 : " +String.valueOf(md.getDayMealList().get(i).getMenuRecipeList().size()));

                //max = Collections.max(arr);
                //logger.info("max : "+String.valueOf(max));


            //maxList.add(max);
            ++cntB;
        
        }

        logger.info("식사구분 - maxList : "+String.valueOf(maxList));

        //logger.info(String.valueOf(cntB));//4
        //logger.info(String.valueOf(cnt));//28(4*7)

        return menuPage;
    }
}
