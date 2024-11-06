package testobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AboutRentPage {


    private WebDriver driver;

    //Локатор поля "* Когда привезти самокат"
    private final By deliveryTime = By.xpath(".//input[@class = 'Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Когда привезти самокат']");

    //Локатор поля c выбранной датой ""
    private final By exactDateLocator = By.xpath(".//div[contains(@class, 'selected')]");

    //Локатор поля "* Срок аренды"
    private final By rentDuration = By.xpath(".//div[@class= 'Dropdown-placeholder' and text()='* Срок аренды']");

    //Локатор дропдауна с сроком аренды
    private final By durationDropdown = By.xpath(".//div[@class='Dropdown-menu']");

    //Паттерн локатора поля "Цвет самоката"
    private final String scooterColorPattern = ".//label[@for='%s' and @class = 'Checkbox_Label__3wxSf']";

    //Локатор поля "Комментарий для курьера"
    private final By commentInput = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder = 'Комментарий для курьера']");

    //Локатор кнопки "Заказать"
    private final By orderButton2 = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Заказать']");

    //Локатор подтверждения оформления заказа
    private final By orderButton3  = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");

    //Локатор pop-up с сообщение об успешном оформлении заказа
    private final By orderStatusPopup = By.xpath(".//div[@class = 'Order_ModalHeader__3FDaJ' and text() = 'Заказ оформлен']");

    public AboutRentPage(WebDriver driver) {
        this.driver = driver;
    }
    //Метод ожидания готовности элемента к взаимодействию
    public WebElement waitForThePageToGetLoaded(WebElement element){
        return new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.visibilityOf(element));
    }

    //Метод выбора рандомнного срока из дроп-дауна
    public void selectARandomOptionFromTheDropDown(WebElement element){
        element.click();
        List<WebElement> options = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(durationDropdown));

        // Генерируем случайный индекс для выбора опции
        int randomOption = ThreadLocalRandom.current().nextInt(0, options.size());

        // Кликаем по случайно выбранной опции
        options.get(randomOption).click();
    }

    //Метод заполнения поля "когда привезти самокат"
    public void deliveryTimeMethod(){
        WebElement element = driver.findElement(deliveryTime);

        //Генерация произвольной даты + форматирования под необходимую маску
        LocalDate today = LocalDate.now();
        LocalDate randomFutureDate = today.plusDays(9);
        DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate =  randomFutureDate.format(formatPattern);

        //Метод ожидания готовности элемента к взаимодействию
        waitForThePageToGetLoaded(element).click();
        //Заполнение поля сгенерированной отформатированной датой
        element.sendKeys(formattedDate);
        //Доп клкик по выбранной дате из календарика для его закрытия
        driver.findElement(exactDateLocator).click();
    }

    //Заолнение поля "Срок аренды"
    public void selectRentDuration (){
        WebElement element = driver.findElement(rentDuration);
        selectARandomOptionFromTheDropDown(element);
    }

    //Выбор опции в поле "Цвет самоката"
    public void choseColor(String color){
        String colorLocation = String.format(scooterColorPattern, color);
        WebElement element = driver.findElement(By.xpath(colorLocation));
        waitForThePageToGetLoaded(element).click();
    }

    //Метод заполнения поля "комментарий"
    public void leaveComment(String comment){
        WebElement element = driver.findElement(commentInput);
        waitForThePageToGetLoaded(element).sendKeys(comment);
    }
    //Метод клика на кнопку "Заказать"
    public void clickOrderButton(){
        WebElement element = driver.findElement(orderButton2);
        waitForThePageToGetLoaded(element).click();
    }
    //Метод клака на кнопку "Да" для пожтверждения заказа
    public void clickYesButton(){
        WebElement element = driver.findElement(orderButton3);
        waitForThePageToGetLoaded(element).click();
    }
    //Метод возврата текса с подтвеждением заказа
    public String checkOrderStatus(){
        WebElement element = driver.findElement(orderStatusPopup);
        return element.getText();
    }

        //Объединение методов для оформелния заказа на страницу "Об аренде"
    public void completeOrder(String color,String comment){
        deliveryTimeMethod();
        selectRentDuration();
        choseColor(color);
        leaveComment(comment);
        clickOrderButton();
        clickYesButton();
    }
}

