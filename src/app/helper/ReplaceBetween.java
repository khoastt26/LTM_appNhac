/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.helper;

import java.util.regex.Pattern;

public class ReplaceBetween {

    public static String replaceBetween(String input,
            String start, String end,
            String replaceWith) {
        return replaceBetween(input, start, end, false, false, replaceWith);
    }

    public static String replaceBetween(String input,
            String start, String end,
            boolean startInclusive,
            boolean endInclusive,
            String replaceWith) {
        start = Pattern.quote(start);
        end = Pattern.quote(end);
        return input.replaceAll("(" + start + ")" + ".*" + "(" + end + ")",
                (startInclusive ? "" : "$1") + replaceWith + (endInclusive ? "" : "$2"));
    }

    //without regex
    public static String replaceBetweenWithoutRegex(String str,
            String start, String end,
            boolean startInclusive,
            boolean endInclusive,
            String replaceWith) {
        int i = str.indexOf(start);
        while (i != -1) {
            int j = str.indexOf(end, i + 1);
            if (j != -1) {
                String data = (startInclusive ? str.substring(0, i) : str.substring(0, i + start.length()))
                        + replaceWith;
                String temp = (endInclusive ? str.substring(j + end.length()) : str.substring(j));
                data += temp;
                str = data;
                i = str.indexOf(start, i + replaceWith.length() + end.length() + 1);
            } else {
                break;
            }
        }
        return str;
    }

    public static void main(String[] args) {
        String input = "<div>this is a <b>good</b> apple</div>";
        System.out.println(input);
        String s = replaceBetween(input, "<b>", "</b>", "big");
        System.out.println(s);
        s = replaceBetween(input, "<b>", "</b>", true, true, "big");
        System.out.println(s);

        String input2 = "there's more than one way to skin a cat";
        System.out.println(input2);
        String s2 = replaceBetween(input2, "more", "skin a", " to ");
        System.out.println(s2);
        s2 = replaceBetween(input2, "more", "skin a", true, true, "no");
        System.out.println(s2);

        System.out.println("-- without regex --");
        replaceBetweenWithoutRegex(input2, "more", "skin a", true, true, "no");
        System.out.println(s2);
    }
}
