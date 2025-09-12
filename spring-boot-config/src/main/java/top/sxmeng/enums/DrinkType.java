package top.sxmeng.enums;

/**
 * 饮料
 */
public enum DrinkType {
    COFFEE("咖啡", 10.0), TEA("奶茶", 8.0), JUICE("果汁", 6.0);

    private final String label;
    private final double price;

    DrinkType(String label, double price){
        this.label = label;
        this.price = price;
    }

    public String getLabel() {
        return label;
    }

    public double getPrice() {
        return price;
    }
}
