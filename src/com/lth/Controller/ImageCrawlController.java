package com.lth.Controller;
import com.lth.Crawlers.ImageCrawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * 图片爬虫控制器
 * @author user
 *
 */
public class ImageCrawlController {

	  public static void main(String[] args) throws Exception {
	

	    String rootFolder =  "e:/crawl"; // 定义爬虫数据存储位置
	    int numberOfCrawlers = 7; // 定义7个爬虫，也就是7个线程
	    String storageFolder = "e:/crawl/data"; // 定义爬取的图片本地存储位置

	    CrawlConfig config = new CrawlConfig(); // 实例化爬虫配置

	    config.setCrawlStorageFolder(rootFolder); // 设置爬虫文件存储位置

	    /*
	     * 设置允许爬取二进制文件 
	     * 因为图片属于二进制文件
	     */
	    config.setIncludeBinaryContentInCrawling(true);

	    String[] crawlDomains = {"http://www.iguaji.com/"};

	    /*
	     * 实例化爬虫控制器
	     */
	    PageFetcher pageFetcher = new PageFetcher(config); // 实例化页面获取器
	    RobotstxtConfig robotstxtConfig = new RobotstxtConfig(); // 实例化爬虫机器人配置 比如可以设置 user-agent
	    
	    // 实例化爬虫机器人对目标服务器的配置，每个网站都有一个robots.txt文件 规定了该网站哪些页面可以爬，哪些页面禁止爬，该类是对robots.txt规范的实现
	    RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
	    // 实例化爬虫控制器
	    CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
	    
	    /*
         * 配置爬虫种子页面，就是规定的从哪里开始爬，可以配置多个种子页面
         */
	    for (String domain : crawlDomains) {
	      controller.addSeed(domain);
	    }

	    ImageCrawler.configure(crawlDomains, storageFolder); // 配置爬虫域名，以及本地存储位置

	    /*
	     * 启动爬虫，爬虫从此刻开始执行爬虫任务，根据以上配置
	     */
	    controller.start(ImageCrawler.class, numberOfCrawlers);
	  }
	}