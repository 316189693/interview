package com.interview;

/**
 * Created By willz
 * Date : 2020/2/19
 * Time: 18:09
 */
public enum ExtTypeOrder {
    USER("User", 1),
    DEPT("Dept", 2),
    AO("AO", 3),
    TMO("TMO", 4),
    OTHER("Other", 5);
    private String extType;
    private int orderNum;

    ExtTypeOrder(String extType, int orderNum) {
        this.extType = extType;
        this.orderNum = orderNum;
    }

    public static int getOrderNumByExtType(String extType) {
        if (null == extType || "".equals(extType.trim())) return -1;

        for (ExtTypeOrder extTypeOrder : ExtTypeOrder.values()) {
            if (extTypeOrder.extType.equals(extType)) {
                return extTypeOrder.orderNum;
            }
        }

        return -1;
    }
}
