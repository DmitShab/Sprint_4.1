package testobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageScooter {
private WebDriver driver;
// Паттерн локатора FAQ
private String listOfFAQPattern =".//div[contains (@id, 'accordion__heading') and contains (text(), '%s')]";
// Паттерн локатора ответов на FAQ
private String listOfAnswersPattern =".//div[contains (@id, 'accordion__panel') and contains (., '%s')]";
    public HomePageScooter(WebDriver driver){
    this.driver = driver;




}
    //Метод ожидания прогрузки FAQ и возврат элемента для последующего клика по нему в методе clickListOfFAQ
    public WebElement waitForFAQcodeClickable(WebElement element){
        return new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(element));
    }



    //Метод для клика на элемент FAQ
    public void clickListOfFAQ(String question){

    //Конкатенация через String.format для получения гибкого локатора
    String questingLocation = String.format(listOfFAQPattern, question);

    //Скролл до поля с "вопросом" из параметра запроса
    WebElement element = driver.findElement(By.xpath(questingLocation));
    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

    //Ожидание для кликабельности + клик по элементу
    waitForFAQcodeClickable(element).click();
}



    //Метод ожидания прогрузки ответов на FAQ и возврат текста ответа в методе getTextOfTheAnswer
public WebElement waitForFAQtoBeVisible(WebElement element){
     return new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(element));
}

//Метод для получения ответа на вопрос
public String getTextOfTheAnswer(String answer){

    //Конкатенация через String.format для получения гибкого локатора
    String answerLocation = String.format(listOfAnswersPattern, answer);

    // Кладем элемет в переменную, чтоб не дублировать "By.xpath"
    WebElement element = driver.findElement(By.xpath(answerLocation));

    // Ожидание видимости ответа + получение текста ответа
   return waitForFAQtoBeVisible(element).getText();
}

    //Общий шаг
    public String getAnswerJoinStep(String question, String answer){
        clickListOfFAQ(question);
       return getTextOfTheAnswer(answer);


    }
}
