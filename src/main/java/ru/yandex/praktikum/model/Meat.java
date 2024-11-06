package ru.yandex.praktikum.model;

import ru.yandex.praktikum.model.constants.Discount;

public class Meat extends Food{


    public Meat(int amount, double price){
        super(amount, price);
        this.isVegeterian=false;
    }
    @Override
    public double getDiscount(){
        return Discount.NO_DISCOUNT;
    }

}
