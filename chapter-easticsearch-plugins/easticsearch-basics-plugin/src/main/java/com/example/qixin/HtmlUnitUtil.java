package com.example.qixin;

import org.jsoup.nodes.Document;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;

public class HtmlUnitUtil {

    public static Document getHtmlFromUrl(String url) throws Exception{
        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setTimeout(10000);
        HtmlPage htmlPage = null;
        try {
            htmlPage = webClient.getPage(url);
            webClient.waitForBackgroundJavaScript(10000);
            String htmlString = htmlPage.asXml();
            return Jsoup.parse(htmlString);
        } finally {
            webClient.close();
        }
    }
}
