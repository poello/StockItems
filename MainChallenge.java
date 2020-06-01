package com.company;

public class MainChallenge {

    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0, 21);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50, 200);
        stockList.addStock(temp);
        temp= new StockItem("cup", 0.45,7);
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 70);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

        System.out.println(stockList);

        Basket paulsBasket = new Basket("Paul");
        Basket secondBasket = new Basket("Second");
        putOutFromBasket(paulsBasket, "cup", 5);
        putInBasket(paulsBasket, "cup", 10);
        putInBasket(paulsBasket, "cup", 190);
        putInBasket(paulsBasket, "cup", 1);

        putInBasket(secondBasket, "cake", 5);
        putInBasket(secondBasket, "cup", 6);
        putInBasket(secondBasket, "cup", 1);
        putInBasket(secondBasket, "towel", 15);

        putInBasket(paulsBasket, "vase", 22);
        putInBasket(paulsBasket, "steel", 10); //steel cannot be added (it's not on the list)

        //Check if items have been added to basket correctly
        System.out.println(stockList);
        System.out.println("========1=========");
        System.out.println(secondBasket);
        putOutFromBasket(secondBasket, "cake", 5);
        putOutFromBasket(secondBasket, "cup", 6);
        putOutFromBasket(secondBasket, "towel", 15);
        System.out.println(secondBasket); //check if unreserve works fine

        checkOut(secondBasket);
        System.out.println(stockList);
        System.out.println(secondBasket);
        System.out.println(paulsBasket);
        checkOut(paulsBasket);

        //Check if after check out stock list has been changed and if the basket is cleared
        System.out.println(stockList);
        System.out.println("========2=========");
        System.out.println(paulsBasket);
        System.out.println(secondBasket);

        System.out.println("========3=========");
        checkOut(secondBasket);
        System.out.println(stockList);
        System.out.println(secondBasket);

        //Check if after check out reserving still works correctly
        System.out.println("\n========================4=========================");
        putInBasket(paulsBasket, "cup", 7);
        putInBasket(paulsBasket, "bread", 81);
        putInBasket(secondBasket, "bread", 12);
        putInBasket(paulsBasket, "vase", 10);

        putOutFromBasket(paulsBasket, "bread", 1);

        putInBasket(paulsBasket, "phone", 33);
        putInBasket(secondBasket, "chair", 14);
        putInBasket(secondBasket, "towel", -1);

        System.out.println(stockList);
        System.out.println(paulsBasket);
        System.out.println(secondBasket);

        //another check after check out
        checkOut(paulsBasket);
        checkOut(secondBasket);

        putInBasket(secondBasket, "phone", 1);
        System.out.println("\n================LAST CHECK===================");
        System.out.println(stockList);
        System.out.println(paulsBasket);
        System.out.println(secondBasket);
    }

    public static void checkOut(Basket basket) {
        basket.checkOutBasket(stockList);
    }

    public static int putInBasket(Basket basket, String item, int quantity) {
        StockItem tmp = stockList.get(item);

        if(tmp == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }

        return basket.addToBasket(tmp, quantity);
    }

    public static int putOutFromBasket(Basket basket, String item, int quantity) {
        StockItem tmp = stockList.get(item);

        if(tmp == null) {
            System.out.println(item + " is not reserved yet");
            return 0;
        }

        return basket.removeFromBasket(tmp, quantity);
    }
}
