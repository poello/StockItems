package com.company;

import java.util.*;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> list;

    public Basket(String name) {
        this.name = name;
        this.list = new TreeMap<>();
    }

    public int addToBasket(StockItem item, int quantity) {
        if((item != null) && (quantity > 0)) {
            if(item.reserveStock(quantity)) { // check if there are enough items for reserve
                int inBasket = list.getOrDefault(item, 0);
                list.put(item, inBasket + quantity);
                return inBasket;
            }

            System.out.println("Not enough "+ item.getName()+"s in stock");
        }
        return 0;
    }

    public int removeFromBasket(StockItem item, int quantity) {
        if((item != null) && (quantity > 0)) {
            if(item.unreserveStock(quantity)) {
                int inBasket = list.getOrDefault(item, 0);

                if(inBasket - quantity == 0) {
                    list.remove(item);
                    return inBasket;
                }

                if(inBasket - quantity < 0) {
                    System.out.println("You unreserve too much items");
                    return 0;
                }

                list.put(item, inBasket - quantity);
                return inBasket;
            }
        }

        return 0;
    }

    public boolean checkOutBasket(StockList stockList) {
        if(!this.list.isEmpty()) {
            for(StockItem item : list.keySet()) {
                int basketReservedStock = list.get(item); // retrieve quantity of reserved stock for this basket
                item.checkOutStock(basketReservedStock); //for total items, its quantity is being reduced by the basket's stock quantity
            }
            this.list.keySet().removeIf(Objects::nonNull);
            return true;
        }

        return false;
    }

    public Map<StockItem, Integer> Item() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nShopping basket " + name + " contains " + list.size() + ((list.size() == 1) ? " item" : " items") + "\n";
        double totalCost = 0.0;
        for(Map.Entry<StockItem, Integer> item : list.entrySet()) {
            s = s + item.getKey() + "; " + item.getValue() + " purchased\n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + "Total cost " + totalCost;
    }
}
