package team.uptech.motionviews.enums;

/**
 * Created by asus on 2/8/2018.
 */

public enum enumHeight {

    UNDEFINE("UNDEFINE", -1),
    HIDE("HIDE", 0),
    LOW("LOW", 1),
    MEDIUM("MEDIUM", 2),
    HIGH("HIGH", 3),
    SHOW("SHOW", 4);

    private String valueStr;
    private Integer value;

    enumHeight(String valueStr, Integer value) {
        this.valueStr = valueStr;
        this.value = value;
    }

    public static enumHeight get(String value) {
        if (value == null) {
            return UNDEFINE;
        }

        enumHeight[] arr$ = values();
        for (enumHeight val : arr$) {
            if (val.valueStr.equalsIgnoreCase(value.trim())) {
                return val;
            }
        }

        return UNDEFINE;
    }

    public static enumHeight get(Integer value) {

        if (value == null) {
            return UNDEFINE;
        }

        enumHeight[] arr$ = values();
        for (enumHeight val : arr$) {
            if (val.value == value) {
                return val;
            }
        }

        return UNDEFINE;
    }

    public String getValueStr() {
        return valueStr;
    }

    public Integer getValue() {
        return value;
    }

    public void setValueStr(String valueStr) {
        this.valueStr = valueStr;
    }
}
