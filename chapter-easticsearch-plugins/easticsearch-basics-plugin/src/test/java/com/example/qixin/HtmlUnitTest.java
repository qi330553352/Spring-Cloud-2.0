package com.example.qixin;

import org.jsoup.nodes.Document;

public class HtmlUnitTest {


    public static void main(String[] args)throws Exception{
        String url = "http://epub.sipo.gov.cn/patentoutline.action";

        Document document = HtmlUnitUtil.getHtmlFromUrl(url);
        System.out.println(document.body().toString());
    }
}
