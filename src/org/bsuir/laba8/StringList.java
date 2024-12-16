package org.bsuir.laba8;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class StringList {

    protected List<String> strings;

    public StringList() {
        strings = new ArrayList<>();
    }

    public void add(String value) {
        strings.add(value);
    }

    public void remove(String value) {
        strings.remove(value);
    }

    public Map<String, Integer> findDuplicates() {
        Map<String, Integer> map = new HashMap<>();
        for (String value: strings) {
            map.put(value, map.containsKey(value) ? map.get(value) + 1 : 1);
        }

        final Map<String, Integer> result = new HashMap<>();
        map.entrySet().stream().filter(e -> e.getValue() > 1).forEach(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

    public boolean saveToXML(String fileName) {
        if (!fileName.endsWith(".xml")) {
            fileName += ".xml";
        }
        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        try {
            icBuilder = icFactory.newDocumentBuilder();
            Document doc = icBuilder.newDocument();
            Element rootElement = doc.createElementNS("http://laba8.bsuir.org", "strings");
            doc.appendChild(rootElement);

            for (String item : strings) {
                Element node = doc.createElement("item");
                node.appendChild(doc.createTextNode(item));
                rootElement.appendChild(node);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult fout = new StreamResult(new FileOutputStream(fileName));
            transformer.transform(source, fout);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public void reverseAll() {
        List<String> revertedStrings = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            String reverted = new StringBuilder(strings.get(i)).reverse().toString();
            revertedStrings.add(reverted);
        }
        strings = revertedStrings;
    }

    public Map<Character, Integer> getCharStatistic() {
        Map<Character, Integer> map = new HashMap<>();
        for (String value : strings) {
            for (Character ch : value.toCharArray()) {
                map.put(ch, map.containsKey(ch) ? map.get(ch) + 1 : 1);
            }
        }
        return map;
    }

    public List<String> search(String key) {
        List<String> result = new ArrayList<>();
        for (String value: strings) {
            if (value.contains(key)) {
                result.add(value);
            }
        }
        return result;
    }

    public boolean loadFromFile(String fileName) {
        try (FileReader fin = new FileReader(fileName)) {
            BufferedReader rdr = new BufferedReader(fin);
            strings.clear();
            do {
                String line = rdr.readLine();
                if (line == null)
                    break;
                add(line);
            } while(true);
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    public int compareInnerObjects(int firstIndex, int secondIndex) {
        return strings.get(firstIndex).compareTo(strings.get(secondIndex));
    }

    public List<Integer> getStringsLength() {
        List<Integer> lens = new ArrayList<>();
        for (String value: strings) {
            lens.add(value.length());
        }
        Collections.sort(lens);
        return lens;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("");
        strings.forEach((value) -> {
            builder.append(value).append("\n");
        });
        return builder.toString();
    }
}
