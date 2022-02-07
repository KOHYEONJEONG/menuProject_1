package com.menu.util;

import com.menu.controller.HomeController;
import com.menu.vo.DayMeal;
import com.menu.vo.MealDivision;
import com.menu.vo.MenuRecipe;
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
    int totalPage = 0;
    int remainder = 0;//나머지값
    int pageBlock = 20;//보여질 범위
    ArrayList<Integer> maxList = new ArrayList<>();

    WeekMenuTable weekMenuTable = new WeekMenuTable();

    ArrayList<WeekMenuTable> weekMenuTableList = new ArrayList<>();//list 하나당 한페이지

    public MenuPage(WeekMenuTable weekMenuTable) {
        this.weekMenuTable = weekMenuTable;
    }

    public MenuPage() {}

    public WeekMenuTable getWeekMenuTable() {
        return weekMenuTable;
    }

    public void setWeekMenuTable(WeekMenuTable weekMenuTable) {
        this.weekMenuTable = weekMenuTable;
    }

    public ArrayList<WeekMenuTable> getWeekMenuTableList() {
        return weekMenuTableList;
    }

    public void setWeekMenuTableList(ArrayList<WeekMenuTable> weekMenuTableList) {
        this.weekMenuTableList = weekMenuTableList;
    }

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


    public ArrayList<WeekMenuTable> paging() {
        /*
     # WeekMenuTable
    [
        # MealDivision
        {
            #요일
            {
                #DayMeal
                {
                    "음식명": "재료명",
                    "음식명": "재료명",
                    "음식명": "재료명",
                    "음식명": "재료명",
                } # ... _ DayMeal
            } # * 7 _ 요일
            } # * 4 _ MealDivision
    ] # * 1 _ WeekMenuTable
* */
        String[] mealString = new String[]{"조식", "중식", "석식", "간식"};//확인-테스트
        String[] dayString = new String[]{"월", "화", "수", "목", "금", "토", "일"};//확인-테스트

        WeekMenuTable weekMenuTable2 = new WeekMenuTable();

        int cntB = 0;
        for (MealDivision md : weekMenuTable.getMdList()) {//4번(조식,중식,석식,간식)
            ArrayList<Integer> arr = new ArrayList<>();

            //월-일 : 최대값(size) 구하려고
            int i = 0;
            for (DayMeal dayMeal : md.getDayMealList()) {//각 식사구분마다 7(월-일)번씩 돈다.
                logger.info(mealString[cntB] + "-" + dayString[i] + "요일별 재료 사이즈: " + dayMeal.getMenuRecipeList().size());
                i++;
                arr.add(dayMeal.getMenuRecipeList().size());//(음식,재료명)
            }

            int max = Collections.max(arr);//각 식사구분에 요일 리스트중 최대길이를 찾음
            logger.info(mealString[cntB] + " - 최대값 : " + max);

            page = max / pageBlock;//페이지
            remainder = max % pageBlock;//나머지 행(이월시켜주려고)

            if (max % pageBlock > 0) { //20보다 적은 행들도 출력해야해서
                page++;
            }
            
            logger.info(mealString[cntB] + " - page 수 : " + page);

            totalPage += page;

            cntB++;//확인하려고 만듬(테스트)
        }//md * 4


        int nowPage = 1;//현재페이지를 갖는 페이지변수 필요 21~40( BLOCK*1)

     /*   WeekMenuTable weekMenuTableFor;
        MealDivision breakFast;
        MealDivision lunch;
        MealDivision dinner;
        MealDivision snack;

        for(int i=0; i<totalPage; i++){
            weekMenuTableFor = new WeekMenuTable();
            breakFast = new MealDivision();
            lunch = new MealDivision();
            dinner = new MealDivision();
            snack = new MealDivision();

            weekMenuTableFor.getMdList().add(breakFast);
            weekMenuTableFor.getMdList().add(lunch);
            weekMenuTableFor.getMdList().add(dinner);
            weekMenuTableFor.getMdList().add(snack);
        }*/

        for(int i=0; i<totalPage; i++){
            //나머지 행들은 다음페이지에서 출력.
             for (MealDivision md : weekMenuTable.getMdList()) {//한페이지
                MealDivision mealDivisionNew = new MealDivision();

                int j = 0;//월-일 인덱스(위치중요)

                int k;
                for (DayMeal dayMeal : md.getDayMealList()) {
                    DayMeal dayMealNew = new DayMeal();//레시피 리스트

                    for (k = 0; k < Math.min(dayMeal.getMenuRecipeList().size(), pageBlock); k++) {
                        //20개
                        //mealDivisionNew.getDayMealList().get(j).getMenuRecipeList().add(new MenuRecipe(dayMeal.getMenuRecipeList().get(k).getFoodName(),dayMeal.getMenuRecipeList().get(k).getIngredientsName()));
                        dayMealNew.getMenuRecipeList().add(new MenuRecipe(dayMeal.getMenuRecipeList().get(nowPage*k).getFoodName(), dayMeal.getMenuRecipeList().get(nowPage*k).getIngredientsName()));
                    }

                    if(dayMeal.getMenuRecipeList().size()>nowPage*pageBlock){
                        nowPage++;//현재페이지를 확인하기 위해서 (ex) 2*20 <-- 인덱스값에다가 더 하려고)
                        //nowpage*pageBlock break 사용해서 다음 페이지에 남은 row를 넘기려고.
                        break;
                    }


                    mealDivisionNew.getDayMealList().set(j, dayMealNew);//set(인덱스,값) 사용. - add() 사용하면 옆으로 늘어남.
                    j = j+1;//월-일 인덱스

                }
                 weekMenuTable2.getMdList().add(mealDivisionNew);
            }//md * 4

            weekMenuTableList.add(weekMenuTable2);//페이지 하나당 (조식,중식,석식,간식)-weekMenuTable2
        }//page 수 만큼 weekMenuTable2 생성


        //weekMenuTableList.add(weekMenuTable2);

        return weekMenuTableList;
        }//큰 for문


        
    }

