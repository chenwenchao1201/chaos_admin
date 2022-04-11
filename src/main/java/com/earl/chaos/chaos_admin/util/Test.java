package com.earl.chaos.chaos_admin.util;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(NumberUtil.round((96 * RandomUtil.randomInt(1, 10) / 10), 0));
    }


}
