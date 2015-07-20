package com.test.zookeeper;

import java.io.IOException;

/**
 * Created by zyinyan on 2015/7/1.
 */
public class ExecTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        String exec[] = {"notepad.exe"};
        Process process = Runtime.getRuntime().exec(exec);
        process.waitFor();
    }
}
