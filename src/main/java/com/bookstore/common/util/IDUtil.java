package com.bookstore.common.util;

import java.util.Random;

public class IDUtil {

    //id 生成
    public static int getId(){

        Random random = new Random();
        int end2 = random.nextInt(999999);
        String str = String.format("%06d",end2);
        Integer id = new Integer(str);
        return id;
    }

    //图片id 文件id 生成

    public static String generateDocumentName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);

        return str;
    }

    public static void main(String[] args) {
        System.out.println(getId());
        System.out.println(generateDocumentName());
    }
}
