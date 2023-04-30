package com.practice.practice.traing;

import java.util.Comparator;
import org.apache.commons.lang3.math.NumberUtils;

public class StringComparator implements Comparator<String> {

    /**
     * 특수문자 > 숫자(0123순) > 영어(대소문자 구분, 대문자 먼저, abc순) > 한글(ㄱㄴㄷ순) 순서 정렬
     * <br>정렬 시 공백은 제외하고 정렬한다.
     * <br>해당 util은 reimaginer 블로그를 참조하여 만들어졌습니다.
     * <br>블로그 링크는 아래 참조
     *
     * @param left  - 비교할 첫 문자
     * @param right - 비교할 두번째 문자
     * @return 음수, 0, 양수
     * @throws NullPointerException – 인수가 null이고 이 비교기가 null 인수를 허용하지 않는 경우
     * @throws ClassCastException   – 인수의 유형이 이 비교기에 의해 비교되지 않는 경우.
     * @see https://www.reimaginer.me/entry/%ED%95%9C%EA%B8%80%EC%98%81%EC%96%B4%ED%8A%B9%EC%88%98%EB%AC%B8%EC%9E%90-%EC%88%9C-%EC%A0%95%EB%A0%AC%ED%95%98%EB%8A%94-java-compare-%EB%A9%94%EC%84%9C%EB%93%9C-%EB%A7%8C%EB%93%A4%EA%B8%B0
     */

    @Override
    public int compare(String left, String right) {
        left = left.replace(" ", "");
        right = right.replace(" ", "");

        // 비교 대상문자가 모두 숫자일떄
        if (!left.startsWith("0") && !right.startsWith("0")
            &&  NumberUtils.isParsable(left) && NumberUtils.isParsable(right)) {
            return makeSortedByNumber(left, right);
        }

        // 비교 대상문자가 둘중 하나라도 숫자가 아닐때
        int leftLen = left.length();
        int rightLen = right.length();
        int minLen = Math.min(leftLen, rightLen);

        for (int i = 0; i < minLen; ++i) {
            char leftChar = left.charAt(i);
            char rightChar = right.charAt(i);

            if (leftChar != rightChar) {
                return makeSortedByNotNumber(leftChar, rightChar);
            }
        }

        return leftLen - rightLen;
    }

    private int makeSortedByNumber(String left, String right) {
        double leftDouble = Double.parseDouble(left);
        double rightDouble = Double.parseDouble(right);
        if (leftDouble > rightDouble) {
            return (int) Math.ceil(leftDouble - rightDouble);
        } else {
            return (int) Math.ceil(rightDouble - leftDouble) * -1;
        }
    }

    private int makeSortedByNotNumber(char leftChar, char rightChar) {
        // 특수문자가 있는 경우
        if (isAnyMatchSpecial(leftChar, rightChar)) {
            if (isEnglish(leftChar) || isNumber(leftChar) || isKorean(leftChar)) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return leftChar - rightChar;
        }
    }

    private boolean isAnyMatchSpecial(char ch1, char ch2) {
        return isEnglishAndSpecial(ch1, ch2)
            || isNumberAndSpecial(ch1, ch2)
            || isKoreanAndSpecial(ch1, ch2);
    }
    private boolean isKoreanAndSpecial(char ch1, char ch2) {
        return (isKorean(ch1) && isSpecial(ch2))
            || (isSpecial(ch1) && isKorean(ch2));
    }

    private boolean isEnglishAndSpecial(char ch1, char ch2) {
        return (isEnglish(ch1) && isSpecial(ch2))
            || (isSpecial(ch1) && isEnglish(ch2));
    }

    private boolean isNumberAndSpecial(char ch1, char ch2) {
        return (isNumber(ch1) && isSpecial(ch2))
            || (isSpecial(ch1) && isNumber(ch2));
    }

    public boolean isEnglish(char ch) {
        return (ch >=  'A' && ch <=  'Z')
            || (ch >=  'a' && ch <=  'z');
    }

    public boolean isKorean(char ch) {
        return ch >= Integer.parseInt("AC00", 16)
            && ch <= Integer.parseInt("D7A3", 16);
    }

    public boolean isNumber(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public boolean isSpecial(char ch) {
        return (ch >= '!' && ch <= '/') // !"#$%&'()*+,-./
            || (ch >= ':' && ch <= '@') //:;<=>?@
            || (ch >= '[' && ch <= '`') //[\]^_`
            || (ch >= '{' && ch <= '~'); //{|}~
    }
}