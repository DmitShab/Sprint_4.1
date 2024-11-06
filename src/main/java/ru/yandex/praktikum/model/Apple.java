package ru.yandex.praktikum.model;
import ru.yandex.praktikum.model.constants.Discount;

import static ru.yandex.praktikum.model.constants.Colour.RED;


public class Apple extends Food {
    private String colour;
    public Apple(int amount, double price, String colour) {
        super(amount, price);
        this.colour = colour;
        this.isVegeterian = true;
    }
    @Override
    public double getDiscount(){
    if(colour.equals(RED)){
        return Discount.DISCOUNT1;
    } else {
        return Discount.NO_DISCOUNT;
        }
    }
}
