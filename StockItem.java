package com.company;


public class StockItem implements Comparable<StockItem> {
    private final String name;
    private double price;
    private int quantityStock;
    private int reservedStock = 0;

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityStock = 0;
    }

    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityStock() {
        return this.quantityStock;
    }

    public int availableQuantity() {
        return quantityStock - reservedStock;
    }

    public void adjustStock(int quantity) {
        int newQuantity = this.quantityStock + quantity;
        if(newQuantity >= 0) {
            this.quantityStock = newQuantity;
        }
    }

    public boolean reserveStock(int quantity) {
        if(quantity <= availableQuantity()) {
            this.reservedStock += quantity;
            return true;
        }

        return false;
    }

    public boolean unreserveStock(int quantity) {
        if(this.reservedStock >= quantity) {
            this.reservedStock -= quantity;
            return true;
        }

        System.out.println("Haven't reserved items yet");
        return false;
    }

    public void checkOutStock(int quantity) {
        if(quantity <= this.reservedStock) {
            this.quantityStock -= quantity;
            this.reservedStock -= quantity;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }

        if((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }

        String objName = ((StockItem)obj).getName();
        return this.name.equals(objName);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 31;
    }

    @Override
    public int compareTo(StockItem o) {
        if(this == o) {
            return 0;
        }

        if(o != null) {
            return this.name.compareTo(o.getName());
        }
        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.name + " : price " + this.price + " Reserved: " + this.reservedStock
                + ", Available: " + this.availableQuantity();
    }
}
