package com.example.myjsoup.foreg;

import java.io.File;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jax
 * @Date: 2023/11/16/下午 03:36:00
 * @Description:
 */
public class DownloadThread extends Thread {
    private Storage storage;
    private String url ;
    private int pageStart;
    private int pageEnd;
    private String tempDir;
    private HashMap<String,String> requestHeaders;

    public DownloadThread(Storage storage, String url, int pageStart, int pageEnd, String tempDir, HashMap<String, String> requestHeaders) {
        this.storage = storage;
        this.url = url;
        this.pageStart = pageStart;
        this.pageEnd = pageEnd;
        this.tempDir = tempDir;
        this.requestHeaders = requestHeaders;
    }

    @Override
    public void run() {
        for (int i = pageStart; i <= pageEnd;i++) {
            //  开始下载文件
            File targetFile = null;
            storage.push(targetFile);
        }
        // 最后放一个null进去，表示下载已经完全结束
        storage.push(null);
    }
}

