package desktop.pages;

import abstractClasses.page.AbstractPage;

public class SearchPage extends AbstractPage {

    public String searchPageUrl = "https://www.bookdepository.com/search";
    static String searchPageTitle = "Results | Book Depository";

    public SearchPage(){
        setPageUrl(searchPageUrl);
    }

}
