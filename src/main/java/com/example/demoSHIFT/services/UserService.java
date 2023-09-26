package com.example.demoSHIFT.services;

import com.example.demoSHIFT.models.Interval;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final String emptyJson = "[]";
    private final String emptyString = "";
    private final String commaString = ",";
    private final String selectMinFromNumberQuery = "SELECT * FROM INTERVALS_INT " +
            "ORDER BY (ABS(START_INTERVAL - INTERVALS_INT.END_INTERVAL)) " +
            "LIMIT 1";
    private final String selectMinFromLetterQuery = "SELECT * FROM INTERVALS_CHAR " +
            "ORDER BY ABS(ASCII(END_INTERVAL) - ASCII(START_INTERVAL)) " +
            "LIMIT 1";
    private final String digitString = "digits";
    private final String letterString = "letters";
    private final String qMarkString = "'";
    private final String tableNameNumbers = "INTERVALS_INT";
    private final String tableNameLetters = "INTERVALS_CHAR";

    private String json = emptyJson;
    public void addInterval(Interval interval) {
        System.out.println(interval + " added to json");
        putInJson(interval);
    }

    private void putInJson(Interval interval) {
        String comma = emptyString;
        if (!json.equals(emptyJson)) {
            comma = commaString;
        }
        json = json.replace("]", comma + "{\"start\":\"" + interval.getStart() +
                "\", \"end\": \"" + interval.getEnd() + "\"}]");
    }

    public String getMinIntervalNumber() {
        return intervalToJson(DataBase.selectInterval(selectMinFromNumberQuery));
    }
    public String getMinIntervalLetter() {
        return intervalToJson(DataBase.selectInterval(selectMinFromLetterQuery));
    }

    public List<Interval> createSubList(String kind) {
        List<Interval> intervalList = jsonToInterval();
        List<Interval> subList = new ArrayList<>();
        for (Interval interval: intervalList) {
            if ((isNumber(interval.getStart().toString()) && kind.equals(digitString))
                    || (!isNumber(interval.getStart().toString()) && kind.equals(letterString))) {
                subList.add(interval);
            }
        }
        return subList;
    }

    public String intervalToJson(Interval interval) {
        return new Gson().toJson(interval).toString();
    }

    public List<Interval> jsonToInterval() {
        return new Gson().fromJson(json, new TypeToken<List<Interval>>(){}.getType());
    }

    public List<Interval> mergeIntervals(List<Interval> intervals)
    {
        List<Interval> result = new ArrayList<>();
        Collections.sort(intervals, Comparator.comparing(a -> a.getStartIndex()));
        Stack<Interval> stack = new Stack<>();
        for (Interval interval: intervals)
        {
            if (stack.empty() || interval.getStartIndex() > stack.peek().getEndIndex()) {
                stack.push(interval);
            }
            if (stack.peek().getEndIndex() < interval.getEndIndex()) {
                stack.peek().setEnd(interval.getEnd());
            }
        }
        while (!stack.empty()) {
            result.add(stack.pop());
        }
        System.out.println("Merged interval: ");
        System.out.println(result);
        return result;
    }

    public boolean isValidInterval(Interval interval) {
        if (isNumber(interval.getStart().toString()) != isNumber(interval.getEnd().toString())) {
            System.err.println("Different types");
            return false;
        }
        if (!isNumber(interval.getEnd().toString()) && !isLetter(interval.getEnd().toString())) {
            System.err.println("Wrong input for End");
            return false;
        }
        if (!isNumber(interval.getStart().toString()) && !isLetter(interval.getStart().toString())) {
            System.err.println("Wrong input for Start");
            return false;
        }
        return true;
    }

    public boolean isNumber(String str) {
        try {
            int res = Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    public boolean isLetter(String str) {
        return str.length() == 1;
    }

    public String json() {
        return json;
    }

    public void clear() {
        System.out.println("Json is cleared");
        json = emptyJson;
    }

    public void sendToDB(List<Interval> intervals, String kind) {
        String tableName;
        String specialSym;
        if (kind.equals(digitString)) {
            tableName = tableNameNumbers;
            specialSym = emptyString;
        }
        else {
            tableName = tableNameLetters;
            specialSym = qMarkString;
        }
        for (Interval interval : intervals) {
            DataBase.insert(interval, tableName, specialSym);
        }
    }

}
