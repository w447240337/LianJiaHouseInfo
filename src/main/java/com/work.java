package com;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class work implements PageProcessor {

	private static int size = 0;

	public static final String URL_LIST = "http://sh.lianjia.com/zufang/d\\w*";

	public static final String URL_POST = "http://sh.lianjia.com/zufang/shz\\w+\\.html";

	private Site site = Site.me().setDomain("sh.lianjia.com").setRetryTimes(3).setSleepTime(1000).setUserAgent(
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

	public void process(Page page) {
		// 列表页
		if (page.getUrl().regex(URL_LIST).match()) {
			List<String> urllist = new ArrayList<String>();
			page.addTargetRequests(page.getHtml().xpath("//div[@class=\"list-wrap\"]").links().regex(URL_POST).all());
			for (String tmp : page.getHtml().xpath("//div[@class=\"house-lst-page-box\"]").links().all()) {
				urllist.add("http://sh.lianjia.com" + tmp);
			}
			page.addTargetRequests(urllist);
			//System.out.println("列表页网址：" + urllist);
			// 文章页
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			size++;
			lianJiaHouse house = new lianJiaHouse();
			// page.putField("title",
			// page.getHtml().xpath("//div[@class='title']//h1[@class='main']"));
			// page.putField("content",
			// page.getHtml().xpath("//div[@id='articlebody']//div[@class='articalContent']"));
			// page.putField("date",page.getHtml().xpath("//div[@id='articlebody']//span[@class='time
			// SG_txtc']").regex("\\((.*)\\)"));
			house.setId(Integer.parseInt(page.getUrl().regex("\\d+").get()));
			house.setTitle(String.valueOf(page.getHtml().$("div.title").$("h1.main", "title").get()));
			house.setPrice(Integer.parseInt(page.getHtml().$("div.mainInfo", "text").get()));
			house.setCreatedate(df.format(new Date()));
			house.setArea(Integer.parseInt(page.getHtml().$("div.area").$("div.mainInfo", "text").get()));
			house.setLongitude(String.valueOf(page.getHtml().$("div.around", "longitude").get()) + ","
					+ page.getHtml().$("div.around", "latitude").get());
			new lianJiaHouseDao().add(house);
			// 把对象输出控制台
			//System.out.println(house);
			//page.putField("title", page.getHtml().$("div.title").$("h1.main","title"));
			// page.putField("longitude", page.getHtml().$("div.around",
			// "longitude"));
			// page.putField("latitude", page.getHtml().$("div.around",
			// "latitude"));
			// page.putField("price",
			// page.getHtml().$("div.price").$("div.mainInfo","text"));
			// page.putField("area",
			// page.getHtml().$("div.area").$("div.mainInfo","text"));
		}
	}

	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		long startTime, endTime;
		System.out.println("爬虫开始");
		startTime = System.currentTimeMillis();
		Spider.create(new work()).addUrl("http://sh.lianjia.com/zufang/d1").thread(5).run();
		endTime = System.currentTimeMillis();
		System.out.println("【爬虫结束】共抓取" + size + "个房源，耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库，请查收！");
	}
}
