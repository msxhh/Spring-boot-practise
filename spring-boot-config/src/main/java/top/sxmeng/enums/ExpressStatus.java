package top.sxmeng.enums;

/**
 * 快递的状态
 */

public enum ExpressStatus {
    CREATED("已揽收"), TRANSIT("在途中"), SUCCESS("已签收");

    private final String label;

    ExpressStatus(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
