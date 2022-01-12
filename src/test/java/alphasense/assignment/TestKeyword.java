package alphasense.assignment;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Reporter;
import org.testng.annotations.Test;
import alphasense.assignment.com.common.BasePage;
import alphasense.assignment.com.common.CommonUtility;
import alphasense.assignment.com.constants.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TestKeyword extends BasePage{

	//SearchKeyword("AlphaSense"), scrolling to the last result and compare the result and it's related test in document view 
	@Test (priority=1)
	public void AlphaSence_keywordSearch() throws IOException, InterruptedException {

		driver.get(Constants.WEB_PAGE);
		CommonUtility.searchKeyword("AlphaSense");
	}
	//RestAssured:SearchKeyword("AlphaSense")
	@Test (priority=2)
	public void AlphaSense_testSearchAPI() throws IOException, InterruptedException{	

		try {
			RequestSpecification httpRequest= RestAssured.given().queryParameters(buildParams()).when();
			Response response=httpRequest.get(Constants.endPoint);
			Assert.assertEquals(response.statusCode(), 200);

			test.pass("AlphaSense keyword is avaiable in document");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.fail("Search is not proper due to malformed request");

		}

	}
	//RestAssured:SearchKeyword("Dummy")
	@Test (priority=3)
	public void AlphaSense_TestEmptyResult(){
		Map<String,Object> parameters =buildParams();
		parameters.put("keyword", "dummy");
		RequestSpecification httpRequest= RestAssured.given().queryParameters(parameters).when();
		Response response=httpRequest.get(Constants.endPoint);
		Assert.assertEquals(response.statusCode(), 200);
		JSONParser parser = new JSONParser();
		try {
			org.json.simple.JSONObject  obj = (org.json.simple.JSONObject)parser.parse(response.body().asString());
			org.json.simple.JSONObject  obj1 = (org.json.simple.JSONObject)parser.parse(obj.get("searchResults").toString());
			//System.out.println("empty result is "+ obj1.get("statements").toString().length());
			Assert.assertTrue(obj1.get("statements").toString().length()<3); 
			test.pass("Dummy keyword is not avaiable in document but successful search ");

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.fail("Search is not proper due to malformed request");
		}
	}
	//RestAssured:SearchKeyword("AlphaSense") but missing object variable
	@Test (priority=4)
	public void alphaSense_testInvalidRequest(){
		try {
			Map<String,Object> parameters =buildParams();
			parameters.remove("released");
			test.fail("AlphaSense keyword is avaiable in document");
			RequestSpecification httpRequest= RestAssured.given().queryParameters(parameters).when();
			Response response=httpRequest.get(Constants.endPoint);
			Assert.assertFalse(response.statusCode() == 400);
			Reporter.log("Test Failed due to malformed request, Missing documentation");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	public Map<String,Object> buildParams(){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("keyword",Constants.keyword);
		params.put("slop", Constants.slop);
		params.put("positiveOnly", Constants.positiveOnly);
		params.put("negativeOnly", Constants.negativeOnly);
		params.put("released", Constants.releaseId);
		return params;
	}
}
