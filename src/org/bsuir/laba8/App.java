package org.bsuir.laba8;

import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class App {
    public static void main(String [] argv) {
        StringList list = new StringList();
        list.add("First row");
        list.add("Second row");
        list.add("Third row");
        list.add("Fourth row");
        list.add("Fifth row");
        out.println("Created list:");
        out.println(list);
        list.saveToXML("file.xml");

        Map<Character, Integer> chars = list.getCharStatistic();
        out.println("\nCharacter statistic:");
        for (Map.Entry<Character, Integer> ch : chars.entrySet()) {
            out.println("Char " + ch.getKey() + " used " + ch.getValue() + " times");
        }

        List<Integer> stringLength = list.getStringsLength();
        out.println("\nString length:");
        for (Integer len : stringLength) {
            out.println(len);
        }

        list.add("Third row");
        list.add("Second row");
        Map<String, Integer> duplicates = list.findDuplicates();
        out.println("\nDuplicates:");
        for (Map.Entry<String, Integer> dup : duplicates.entrySet()) {
            out.println("String \"" + dup.getKey() + "\" occurs " + dup.getValue() + " times");
        }



        list.loadFromFile("file.xml");
        out.println("\nLoaded from XML:");
        out.println(list);
    }
}
