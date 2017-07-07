/**
 * 
 */
package API;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import API.parseEXCEL;

/**
 * @author qulei
 *
 */
public class VerifyCodeMSG {

	/**
	 * @throws java.lang.Exception
	 */
	static Logger logger = LogManager.getLogger(VerifyCodeMSG.class.getName());
	ArrayList<ArrayList<Object>> testdata;

	@Before
	public void setUp() throws Exception {
		RestAssured.baseURI = "http://192.168.100.43:8088";
		RestAssured.port = 8088;
		// RestAssured.useRelaxedHTTPSValidation();
		// RestAssured.authentication = basic("TestWH", "admin@TestWH-123");
		// RestAssured.requestSpecification=accept("application/json");
		// RestAssured.with().accept("application/json");
		// logger.info("我是info信息");
		// logger.error("我是error信息");
		// logger.info(VerifyCodeMSG.class.getResource("/API/testdata.xlsx").getFile());
		File file = new File("/Users/qulei/Documents/workspace/ONCO-TEST/bin/API/testdata.xlsx");
		// logger.info(file.getAbsolutePath());
		testdata = parseEXCEL.readExcel(file);
		ArrayList<Dictionary> temp = convert2hash(testdata);
		logger.info("testdata");
		logger.info(temp);
	}

	@After
	public void tearDown() throws Exception {
	}

	// @Ignore("忽略")
	@Test
	public void VerifyResponseCode() {
//		for (ArrayList<Object> testdata_entry : testdata) {
//			if (!testdata_entry.get(0).toString().equals("#")) {
//
//				// logger.info(testdata_entry.get(0));
//				String method = testdata_entry.get(1).toString();
//				Double case_no = new Double(testdata_entry.get(0).toString());
//				logger.info("Test Case " + case_no.toString() + " start.");
//				logger.info(testdata_entry);
//				logger.info("method: " + method);
//				String url = "/business/gettenantlinkinfo.php?tenantname=" + testdata_entry.get(2).toString();
//				logger.info("url: " + url);
//				String requestBody = testdata_entry.get(3).toString().replace('"', '\"');
//				logger.info("requestBody: " + requestBody);
//				Response response = null;
//				switch (method) {
//				case "GET":
//					response = given().header("Accept", "application/json").get(url);
//					break;
//				case "POST":
//					response = given().contentType(ContentType.JSON).body(requestBody).when().post(url);
//					break;
//				}
//				int expected_status = new Double(testdata_entry.get(4).toString()).intValue();
//				logger.info("Expected status Code: " + expected_status);
//				int actual_status = response.statusCode();
//				logger.info("Actual status Code: " + actual_status);
//				assertThat(actual_status, equalTo(expected_status));
//
//				int expected_code = new Double(testdata_entry.get(5).toString()).intValue();
//				logger.info("Expected Code: " + expected_code);
//				int actual_Code = JsonPath.from(response.getBody().asString()).getInt("code");
//				logger.info("Actual Code: " + actual_Code);
//				assertThat(actual_Code, equalTo(expected_code));
//
//				String expected_msg = testdata_entry.get(6).toString();
//				logger.info("Expected Msg: " + expected_msg);
//				String actual_Msg = JsonPath.from(response.getBody().asString()).getString("message");
//				logger.info("Actual Msg: " + actual_Msg);
//				assertThat(actual_Msg, equalTo(expected_msg));
//
//				logger.info("Test Case " + case_no.toString() + " start.");
//			}
//		}

	}

	private ArrayList<Dictionary> convert2hash(ArrayList<ArrayList<Object>> data) {
		ArrayList<Dictionary> returnData = new ArrayList<Dictionary>();
		int count = 1;
		for (ArrayList<Object> entry : data) {
			logger.info("here"+entry);
			ArrayList<String> header = new ArrayList<String>();
			if (count == 1) {
				for (Object field : entry) {
					header.add(field.toString());
				}
			} else {
				int index = 1;
				Dictionary<String, Object> hashTable = new Hashtable<String, Object>();
				for (Object field : entry) {
					hashTable.put(header.get(index), field);
					index++;
				}
				returnData.add(hashTable);
			}

		}
		logger.info(returnData);
		return returnData;
	}
}
