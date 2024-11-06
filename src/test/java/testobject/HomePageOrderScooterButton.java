package testobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageOrderScooterButton {
    private WebDriver driver;
    public static String pattern1 = "Button_Button__ra12g";
    public static String pattern2 = "Button_Button__ra12g Button_Middle__1CSJM";


    //Локатор кнопки "Заказать" в верхней части лендинга
    private final String orderButton = ".//div/button[@class = '%s' and contains(text(),'Заказать')]";

    public HomePageOrderScooterButton(WebDriver driver){
        this.driver= driver;
    }

    //Метод ожидает пока кнопка станет кликабельной + скролла до кнопки
    public WebElement waitOrderButtonToBeClickable(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
       return new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    //Метод клика на кнопку "Заказать"
    public void orderButtonClick(String locationPath){
        String orderButtonLocation = String.format(orderButton, locationPath);
        WebElement element = driver.findElement(By.xpath(orderButtonLocation));
        waitOrderButtonToBeClickable(element).click();
    }
}
