package com.himanshu.practice.nov.nov29;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class BuilderPOC {
    public static void main(String[] args) {
        Test test = Test.builder().a(5).b(10).c(100).b(11).c(44).build();
        System.out.println(test);


    }
}


@Builder
@ToString
class Test {
    private int a;
    private int b;
    private int c;
}