package test;

import testobject.AboutRentPage;
import testobject.HomePageOrderScooterButton;
import testobject.OrderPage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.hamcrest.CoreMatchers.containsString;


@RunWith(Parameterized.class)
public class MakingAnOrderTests extends BaseUITest {

    private final String locationPath;
    private final String nameExample;
    private final String lastNameExample;
    private final String addressExample;
    private final String phoneNumberExample;
    private final String color;
    private final String comment;
    private final String expected;

    public MakingAnOrderTests(String locationPath, String nameExample, String lastNameExample, String addressExample, String phoneNumberExample, String color, String comment, String expected) {
        this.locationPath = locationPath;
        this.nameExample = nameExample;
        this.lastNameExample = lastNameExample;
        this.addressExample = addressExample;
        this.phoneNumberExample = phoneNumberExample;
        this.color = color;
        this.comment = comment;
        this.expected = expected;
    }

    @Parameters
    public static Object[][] getData(){
        return new Object[][]{
                {HomePageOrderScooterButton.pattern1, "Дмитрий", "Шабалин", "Москва", "89109999999", "black", "Пока, курьер!", "Заказ оформлен"},
                {HomePageOrderScooterButton.pattern2, "Том", "Ян", "Нижний","+78999999911", "grey", "Привет, курьер!", "Заказ оформлен"}
        };
    }
    @Test
    public void testQAScooter (){
        HomePageOrderScooterButton homePageOrderScooter = new HomePageOrderScooterButton(driver);
        driver.get(url);
        homePageOrderScooter.orderButtonClick(locationPath);
        OrderPage orderPage = new OrderPage(driver);
        orderPage.jointStep(nameExample,lastNameExample,addressExample,phoneNumberExample);
        AboutRentPage aboutRentPage = new AboutRentPage(driver);
        aboutRentPage.completeOrder(color, comment);
        String actualResult = aboutRentPage.checkOrderStatus();
        Assert.assertThat("Фейлится заказ самоката", actualResult, containsString(expected));
    }
}
