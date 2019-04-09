package com.example.qixin;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * 创 建 时 间: 2019/4/4
 * 版       本: V1.0
 * 作       者: qixin
 * 版 权 所 有: 版权所有(C)2019-2029
 */
public class ExampleTests {


    @Test
    public void test() throws Exception{
        String path = "D:\\我的文件\\专利数据\\WGSQGB2701.txt";
        InputStreamReader bis = new InputStreamReader(new FileInputStream(new File(path)));
        BufferedReader br = new BufferedReader(bis);
        String line = "";
        line = br.readLine();
        int i = 0;
        int idx = 1;
        String previous = "";
        StringBuffer sb = new StringBuffer();
        while (line != null) {++idx;
            line = br.readLine();
            if(StringUtils.isEmpty(line)) continue;
            ++i;

//            boolean tag = false;
//            for(String pre : list){
//                if(line.startsWith(pre)){
//                    tag = true;
//                    break;
//                }
//            }
//
//            if(!tag){
//                //sb.append(line).append("\r\n");
//                //System.out.println(i+"  "+line);
//            }
        }
        //writer(sb.toString());
    }

    private void writer(String line) {
        String path = "D:\\我的文件\\专利数据\\temp.txt";
        File writename = new File(path);
        try{
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(line);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void test2(){
        String str = "(10)授权公告号  CN  301434278 S\t\t(10)授权公告号  CN  301434280 S";
        System.out.println(str.substring(4));
    }
}
