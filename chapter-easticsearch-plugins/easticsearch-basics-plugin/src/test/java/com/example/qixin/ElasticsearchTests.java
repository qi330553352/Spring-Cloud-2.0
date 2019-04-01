package com.example.qixin;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class ElasticsearchTests {

    private TransportClient client;
    private String clusterName = "elasticsearch";
    private String serverhost = "192.168.147.131";          //IP 地址
    private int serverPort = 9300;                      //端口号

    @Test
    public void testInit() throws UnknownHostException {
         TransportClient client;
         String clusterName = "elasticsearch";
         String serverhost = "192.168.147.131";          //IP 地址
         int serverPort = 9300;                      //端口号
        Settings settings = Settings.builder().put("cluster.name",clusterName).build();
        client = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(InetAddress.getByName(serverhost), serverPort));

        System.out.println("######创建了ElasticClient ##################");
        System.out.println("--------------------");
        client.close();
    }

    private static XContentBuilder getMapping(String indexType)throws Exception {
        XContentBuilder mapping = XContentFactory.jsonBuilder().startObject()
                .startObject(indexType).startObject("properties")

                //定义标题列
                .startObject("title").field("type", "string")
                .field("store", "yes").field("analyzer", "standard")
                .endObject()

                //定义内容列
                .startObject("body").field("type", "string")
                .field("store", "yes").field("analyzer", "standard")
                .endObject()

                .endObject()                             // 属性结束
                .endObject()                             // 索引类型结束
                .endObject();
        return mapping;
    }

    @Test
    public void test2()throws Exception{
        TransportClient client;
        String clusterName = "elasticsearch";
        String serverhost = "192.168.147.131";          //IP 地址
        int serverPort = 9300;                      //端口号
        Settings settings = Settings.builder().put("cluster.name",clusterName).build();
        client = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(InetAddress.getByName(serverhost), serverPort));

//        String indexName = "cms";
//
//        IndicesAdminClient ac = client.admin().indices();
//        CreateIndexRequestBuilder builder = ac.prepareCreate(indexName);
//
////设置分片数量
//        Settings.Builder setting = Settings.builder().put("number_of_shards", 1);
//        builder.setSettings(setting);
//
//// 首先创建索引库
//        CreateIndexResponse indexresponse = client.admin().indices()
//                // 这个索引库的名称不能包含大写字母
//                .prepareCreate(indexName).setSettings(setting.build()).execute()
//                .actionGet();
//        System.out.println("CreateIndex "+indexresponse.isAcknowledged());                                                     //看是否成功创建索引
////然后设定索引库结构
//        String type = "article";
//        XContentBuilder mapping = getMapping(type);
//        PutMappingRequest mappingRequest = Requests
//                .putMappingRequest(indexName).type(type).source(mapping);
//        PutMappingResponse putMappingResponse = client.admin().indices()
//                .putMapping(mappingRequest).actionGet();
////看是否成功设定索引结构
//        System.out.println("putMappingResponse " + putMappingResponse.
//                isAcknowledged());


        //删除索引
//        IndicesAdminClient admin = client.admin().indices();
//        admin.prepareDelete(indexName).execute().actionGet().isAcknowledged();


        String id="20";   //唯一列的值
        IndexRequestBuilder indexRequestBuilder = client.prepareIndex(
                "cms", "article", id);
//准备文档内容
        Map<String, String> source = new HashMap<>();
        source.put("title", "标题");
        source.put("body", "内容");
        indexRequestBuilder.setSource(source);
        IndexResponse response = indexRequestBuilder.execute().actionGet();
        System.out.println(response.status().name());  // 如果成功，
    }
}
