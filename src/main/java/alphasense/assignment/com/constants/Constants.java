package alphasense.assignment.com.constants;

public class Constants {
	final public static Integer DEFAULT_TIMEOUT = 10;
	final public static String WEB_PAGE = "https://rc.alpha-sense.com/doc/PR-386ea743f2a90399fb0e4300ddf37d0697abc743";
	final public static String endPoint = "https://rc.alpha-sense.com/services/i/public-document-data/document/PR-386ea743f2a90399fb0e4300ddf37d0697abc743/keyword-search/";
	final public static String keyword ="AlphaSense";
	final public static int slop =15;
	final public static boolean  positiveOnly =false;
	final public static boolean negativeOnly =false;
	final public static String releaseId ="1633003200000";
	final public static String Search_path ="//div[contains(@class,'CodeMirror')]//div//textarea";
	final public static String Search_class ="CodeMirror";
	
	final public static String Search_container ="//div[@class='ReactVirtualized__Grid__innerScrollContainer']";
	final public static String Last_search_result ="//div[@class='ReactVirtualized__Grid__innerScrollContainer']//div[@class='snippetItem-wrapper']";
	final public static String Document_id ="content-1";
	final public static String Highlighted_path ="//span[contains(@class,'x-grid3-row-blue')]";
}
