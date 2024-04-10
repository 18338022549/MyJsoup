package com.example.myjsoup;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jax
 * @Date: 2023/11/20/上午 10:51:17
 * @Description:
 */
public class test {

    public static void main(String[] args) {
    /*    ok:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println("i=" + i + ",j=" + j);
                if (j == 5) {
                    break ok;
                }
            }
        }
    }
    private static final ExecutorService CA = Executors.newFixedThreadPool(10);*/
    int n = 10;
    n += (n++) + (++n);
    System.out.print(n);


    }
}
