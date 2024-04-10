package com.example.myjsoup.until;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jax
 * @Date: 2023/11/16/下午 03:46:36
 * @Description:
 */
@Data
public class Books {
    private long id;
    private String bookname;
    private String bookauthor;
    private String bookdate;
    private String bookpress;
    private String bookprice;
    private String bookinfo;
    private String createTime;
    private String modifyTime;
    private Integer status;
}
