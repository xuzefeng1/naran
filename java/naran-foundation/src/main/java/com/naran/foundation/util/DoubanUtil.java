package com.naran.foundation.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.naran.foundation.entity.TudouBookInfo;

/**
 * @author zefeng.xu
 * 
 * */
public class DoubanUtil {

    public static final String API_URL = "http://api.douban.com/book/subject/isbn/";

    public static final String ISBN = "9787121249679";

    public DoubanUtil(String isbn) throws ClientProtocolException, IOException {
	DefaultHttpClient client = new DefaultHttpClient();
	HttpGet get = new HttpGet(API_URL + isbn);
	HttpResponse response = client.execute(get);
	HttpEntity entity = response.getEntity();
	InputStream is = entity.getContent();
	TudouBookInfo book = new BookXMLParser(is).getBook();

	System.out.println("title:->" + book.getTitle());
	System.out.println("summary:->" + book.getSummary());
	System.out.println("price:-->" + book.getPrice());
	System.out.println("author:-->" + book.getAuthor());
	System.out.println("ImagePath:-->" + book.getImagePath());
	System.out.println("Average:-->" + book.getAverage());

    }

    public static TudouBookInfo getBook(String isbn) {
	TudouBookInfo book = null;
	try {
	    DefaultHttpClient client = new DefaultHttpClient();
	    HttpGet get = new HttpGet(API_URL + isbn);
	    HttpResponse response = client.execute(get);
	    HttpEntity entity = response.getEntity();
	    InputStream is = entity.getContent();
	    book = new BookXMLParser(is).getBook();
	} catch (Exception e) {

	}
	return book;
    }

    public static void main(String[] args) throws ClientProtocolException, IOException {
	new DoubanUtil(ISBN);
    }

}
