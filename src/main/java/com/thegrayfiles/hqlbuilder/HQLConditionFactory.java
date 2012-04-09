package com.thegrayfiles.hqlbuilder;

/**
 * Created by IntelliJ IDEA. User: gavin Date: 4/8/12 Time: 12:17 PM To change this template use File | Settings | File
 * Templates.
 */
public class HQLConditionFactory {

    public static HQLCondition isEqual(final String name, final String value) {
        return new HQLCondition() {
            public String toHqlString() {
                return name + "='" + value + "'";
            }
        };
    }
}
