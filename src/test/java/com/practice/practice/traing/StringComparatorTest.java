package com.practice.practice.traing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.practice.practice.traing.dto.ComparatorTestDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StringComparatorTest {

    public static List<ComparatorTestDto> resultDtos = new ArrayList<>();
    public static List<String> testArray = new ArrayList<>();
    public static List<String> resultArray = new ArrayList<>();

//    @BeforeAll
    static void injectionDto() {
        // given
        testArray = Arrays.asList("1234", "832", "123.123", "832.523", "한글", "가나다라", "hello",
            "check", "ch_ck", "[중괄호]", "[eng]", "{대괄호}", "1623", "넘버아님", "/특수/", "832", "가나다라1",
            "Aad", "Check", "634.123", "634.31123", "01234", "08561", "<eng>", "^test^", "&대괄호&",
            "#shap]", "#java]", "#대괄호}");
        resultArray = Arrays.asList("#java]", "#shap]", "#대괄호}", "&대괄호&", "/특수/", "01234", "08561",
            "123.123", "1234", "1623", "634.123", "634.31123", "832", "832", "832.523", "<eng>",
            "Aad", "Check", "[eng]", "[중괄호]", "^test^", "ch_ck", "check", "hello", "{대괄호}", "가나다라",
            "가나다라1", "넘버아님", "한글");

        resultDtos = IntStream.range(0, resultArray.size())
            .mapToObj(index -> ComparatorTestDto.builder()
                .num(index)
                .name(resultArray.get(index))
                .info("testArray index : " + index)
                .build())
            .collect(Collectors.toList());

    }

//    @Test
    void comparatorTest() {
        // given
        List<ComparatorTestDto> testDtos = IntStream.range(0, testArray.size())
            .mapToObj(index -> ComparatorTestDto.builder()
                .num(index)
                .name(testArray.get(index))
                .info("testArray index : " + index)
                .build())
            .collect(Collectors.toList());

        // when
        List<String> testNameDtos = testDtos.stream()
            .sorted((o1, o2) -> new StringComparator().compare(o1.getName(), o2.getName()))
            .map(ComparatorTestDto::getName)
            .collect(Collectors.toList());
        List<String> resultNameDtos = resultDtos.stream()
            .sorted((o1, o2) -> new StringComparator().compare(o1.getName(), o2.getName()))
            .map(ComparatorTestDto::getName)
            .collect(Collectors.toList());

        //then
        assertIterableEquals(testNameDtos, resultNameDtos);
    }

    @DisplayName("출력되는 테스트 명을 입력한다.")
    @Test
    void testAnnotation() {
        assertEquals("test","test");
    }

    @Test
    void test_under_scores() {
        assertEquals("test","test");
    }

    @DisplayName("출력되는_테스트_명을_입력한다.")
    @Test
    void test_display_name_under_scores() {
        assertEquals("test","test");
    }
}