package org.bsuir.laba8;

public class LimitedStringList extends StringList {

    protected int maxSize;

    public LimitedStringList(int maxSize) {
        this.maxSize = maxSize;
    }

    public void add(String value) {
        if (maxSize > 0 && strings.size() == maxSize) {
            strings.remove(0);
        }
        strings.add(value);
    }
}
