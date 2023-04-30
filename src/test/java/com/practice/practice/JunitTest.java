package com.practice.practice;

import static org.junit.jupiter.api.Assertions.*;

import com.practice.practice.traing.dto.ComparatorTestDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_METHOD)
class JunitTest {

    @BeforeAll
    static void testBeforeAll() {
        log.info("BeforeAll");
    }

    @AfterAll
    static void testAfterAll() {
        log.info("AfterAll");
    }

    @DisplayName("출력되는 테스트 명을 입력한다.")
    @Order(1)
    @Timeout(1)
    @Test
    void testAnnotation() throws InterruptedException {
        log.info("testAnnotation");
        assertEquals("test","test");
        Thread.sleep(2000);
    }

    @Test
    @Tag("tagTest")
    @Order(3)
    void test_under_scores() {
        log.info("test_under_scores");
        assertEquals("test","test");
    }

    @DisplayName("출력되는_테스트_명을_입력한다.")
    @Order(2)
    @TagTest
    void test_display_name_under_scores() {
        log.info("test_display_name_under_scores");
        assertEquals("test","test");
    }

    @Test
    void streamMapReturnObject() {
        List<String> stringList = List.of("test1", "test2", "test3");
        List<ComparatorTestDto> collect = stringList.stream()
            .map(s ->
            {
                return ComparatorTestDto.builder()
                    .num(1)
                    .name(s)
                    .info("test : " + s)
                    .build();
            })
            .collect(Collectors.toList());

        collect.forEach(System.out::println);
    }
}