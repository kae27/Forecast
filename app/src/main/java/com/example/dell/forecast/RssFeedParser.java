package com.example.dell.forecast;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 5/9/2017.
 *  ส่วนของการ Feed ข้อมูลจาก XML RSS
 */

public class RssFeedParser
{

    private final URL feedUrl;

    public RssFeedParser(String feedUrl) {
        try {
            this.feedUrl = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private InputStream getInputStream() {
        try {
            return feedUrl.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<MessageRSSfeedModel> parse() {
        //currentMessage ใช้เก็บข้อมูลที่กำลังดึงอยู่
        final MessageRSSfeedModel currentMessage = new MessageRSSfeedModel();

        //messages ใช้เก็บข้อมูลทั้งหมด
        final ArrayList<MessageRSSfeedModel> messagesList = new ArrayList<MessageRSSfeedModel>();

        //root ชื่อ rss
        RootElement root = new RootElement("rss");
        Element channel = root.getChild("channel"); //ดึงข้อมูลใน Tag channel
        Element item = channel.getChild("item"); //ดึงข้อมูลใน Tag item

        //ถ้ามีการจบ Tag item จะทำการ Copy currentMessage ไปเพิ่มใน messagesList
        item.setEndElementListener(new EndElementListener(){
            public void end() {
                messagesList.add(currentMessage.copy());
            }
        });

        //ถ้ามีการจบ Tag title จะนำข้อมูลไปใส่ใน currentMessage
        item.getChild("title").setEndTextElementListener(new EndTextElementListener(){
            public void end(String body) {
                currentMessage.setTitle(body);
            }
        });
        item.getChild("link").setEndTextElementListener(new EndTextElementListener(){
            public void end(String body) {
                currentMessage.setLink(body);
            }
        });
        item.getChild("description").setEndTextElementListener(new EndTextElementListener(){
            public void end(String body) {
                currentMessage.setDescription(body);
            }
        });
        item.getChild("pubDate").setEndTextElementListener(new EndTextElementListener(){
            public void end(String body) {
                currentMessage.setDate(body);
            }
        });

        //ทำการ Parse XML จาก Stream ที่ได้จาก URL
        try {
            Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8, root.getContentHandler());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //ส่งข้อมูลที่ดึงได้ทั้งหมดไปใช้งานต่อ
        return messagesList;
    }
}
