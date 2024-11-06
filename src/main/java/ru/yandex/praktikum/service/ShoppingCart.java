package ru.yandex.praktikum.service;

import ru.yandex.praktikum.model.Food;
public class ShoppingCart {
    private Food[] items;

    public ShoppingCart(Food[] items) {
        this.items = items;
    }

    private double totalSum = 0;
    private double sumWithDiscount = 0;
    private double sumVeggiesWithoutDiscount = 0;

    public double getTotalSum() {
        for (int i = 0; i < items.length; i++) {
            totalSum = totalSum + items[i].price * items[i].amount;
        }
        return totalSum;
    }

    public double getSumWithDiscount() {
        for (int i = 0; i < items.length; i++) {
            double discountPrice = items[i].price * (1 - items[i].getDiscount() / 100);
            sumWithDiscount = sumWithDiscount + discountPrice * items[i].amount;
        }
        return sumWithDiscount;
    }


    public double getSumVeggiesWithoutDiscount() {
            for (int i = 0; i < items.length; i++) {
                if (items[i].isVegeterian) {
                    sumVeggiesWithoutDiscount = sumVeggiesWithoutDiscount + items[i].price*items[i].amount;
                }
            }
            return sumVeggiesWithoutDiscount;
        }
    }