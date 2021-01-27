package abstractClasses.page;

import org.openqa.selenium.JavascriptExecutor;
import static driver.SingletonDriver.getDriver;

public abstract class AbstractPage {

    private String pageUrl;

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void openPage(){
        getDriver().get(getPageUrl());
        waitForPageLoaded();
    }

    public void waitForPageLoaded(){
        JavascriptExecutor js = (JavascriptExecutor)getDriver();

        //Initially bellow given if condition will check ready state of page.
//        if (js.executeScript("return document.readyState").toString().equals("complete")){
////            System.out.println("Page Is loaded.");
//            return;
//        }

        //This loop will rotate for 25 times to check If page Is ready after every 1 second.
        //You can replace your value with 25 If you wants to Increase or decrease wait time.
        for (int i=0; i<10; i++){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e) {}
            //To check page ready state.
            if (js.executeScript("return document.readyState").toString().equals("complete")){
                break;
            }
        }
    }

    public String getPageTitle() {
        return null;
    }
}
