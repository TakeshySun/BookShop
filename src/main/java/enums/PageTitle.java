package enums;

import desktop.pages.SearchPage;

public enum PageTitle {

    HOME_PAGE("Book Depository: Free delivery worldwide on over 20 million books"),
    SEARCH_PAGE("Results"),
    BASKET_PAGE("Your basket");

    private String pageName;

    PageTitle(String pageName){
        this.pageName = pageName;
    }

    public String getValue(){
        return pageName;
    }
}
