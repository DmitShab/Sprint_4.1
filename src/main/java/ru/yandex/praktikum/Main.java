package ru.yandex.praktikum;

import ru.yandex.praktikum.model.Apple;
import ru.yandex.praktikum.model.Food;
import ru.yandex.praktikum.model.Meat;
import ru.yandex.praktikum.model.constants.Colour;
import ru.yandex.praktikum.service.ShoppingCart;

public class Main {
    public static void main(String[] args){
        Meat meat = new Meat(5, 100);
        Apple redApple = new Apple(10,50, Colour.RED);
        Apple greenApple = new Apple(8,60,Colour.GREEN);
        Food[] items = {meat,redApple,greenApple};
        ShoppingCart shoppingCart = new ShoppingCart(items);

        System.out.println ("Общая сумма товаров без скидки:" + shoppingCart.getTotalSum());
        System.out.println ("Общая сумма товаров со скидкой:" + shoppingCart.getSumWithDiscount());
        System.out.println ("Сумма всех вегетарианских продуктов без скидки:" + shoppingCart.getSumVeggiesWithoutDiscount());
        }
    }
