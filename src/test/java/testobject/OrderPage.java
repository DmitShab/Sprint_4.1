package testobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OrderPage {


    private WebDriver driver;
    //Имя
    private final By firstName = By.xpath(".//input[@class = 'Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder = '* Имя']");

    //Фамилия
    private final By lastName = By.xpath(".//input[@class = 'Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder = '* Фамилия']");

    //Адрес куда привезти самокат
    private final By address = By.xpath(".//input[@class = 'Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder = '* Адрес: куда привезти заказ']");

    //Станция метро
    private final By metro = By.xpath(".//input[@class = 'select-search__input' and @placeholder = '* Станция метро']");

    //телефон куда позвонит курьер
    private final By phoneNumber = By.xpath(".//input[@class = 'Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder = '* Телефон: на него позвонит курьер']");

    //Кнопка Далее
    private final By proceedButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Далее']");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }
    // Метод для ожидания готовности элемента к взаимодействию
    public WebElement waitForTheFieldToBeInteractable(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        return new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(element));
    }


    //Метод для выбора рандомного значения из дропдауна
    public void selectARandomOptionFromTheDropDown(WebElement element){
        element.click();
    List<WebElement> options = new WebDriverWait(driver, 3)
            .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//div[@class = 'select-search__select']")));

    // Генерируем случайный индекс для выбора опции
    int randomOption = ThreadLocalRandom.current().nextInt(0, options.size());

    // Кликаем по случайно выбранной опции
    options.get(randomOption).click();
}

    //Метод заполнения поля Имя
    public void firstNameFillIn (String nameExample){
        WebElement element = driver.findElement(firstName);
        waitForTheFieldToBeInteractable(element);
        element.click();
        element.sendKeys(nameExample);
    }

    //Метод заполнения поля Фамилия
    public void lastNameFillIn (String lastNameExample){
        WebElement element = driver.findElement(lastName);
        waitForTheFieldToBeInteractable(element);
        element.click();
        element.sendKeys(lastNameExample);
    }

    //Метод заполнения поля Адрес
    public void addressFillIn (String addressExample){
        WebElement element = driver.findElement(address);
        waitForTheFieldToBeInteractable(element);
        element.click();
        element.sendKeys(addressExample);
    }

    //Метод заполнения поля Станция Метро
    public void metroFillIn (){
        WebElement element = driver.findElement(metro);
        selectARandomOptionFromTheDropDown(element);
    }

    //Метод заполнения поля Телефон
    public void phoneFillIn (String phoneNumberExample){
        WebElement element = driver.findElement(phoneNumber);
        waitForTheFieldToBeInteractable(element);
        element.click();
        element.sendKeys(phoneNumberExample);
    }

    //Метод нажатия на кнопку Далее
    public void proceedButtonClick(){
        WebElement element = driver.findElement(proceedButton);
        waitForTheFieldToBeInteractable(element);
        element.click();
    }
// Метод - общий шаг для первой старницы процесса заказа самоката
    public void jointStep (String nameExample,String lastNameExample,String addressExample,String phoneNumberExample){
        firstNameFillIn(nameExample);
        lastNameFillIn(lastNameExample);
        addressFillIn(addressExample);
        metroFillIn();
        phoneFillIn(phoneNumberExample);
        proceedButtonClick();
    }
}
