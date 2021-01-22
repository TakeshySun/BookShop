package desktop.pages;

import abstractClasses.page.AbstractPage;

public class HomePage extends AbstractPage {
    private static final String homePageUrl = "https://www.bookdepository.com/";
    private final String homePageTitle = "Book Depository: Free delivery worldwide on over 20 million books";

    public HomePage(){
        setPageUrl(homePageUrl);
    }

}
