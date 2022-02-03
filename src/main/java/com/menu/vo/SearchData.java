package com.menu.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchData {//searchPrint.jsp에서 컨트롤러로 넘겨줄 때
    String restaurantName;
    String startDate;
    String endDate;
    String mealName;
}
