/**
 * 
 */
package ErrorMsg;

import static org.junit.Assert.*;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

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
@RunWith(Parameterized.class)
public class getweekappinfoTest {

	/**
	 * @throws java.lang.Exception
	 */
	static Logger logger = LogManager.getLogger(getweekappinfoTest.class.getName());
	String url;
	
	private Object caseno;
	private Object method;
	private Object tenant;
	private Object startdate;
	private Object enddate;
	private Object request_body;
	private Object response_status;
	private Object response_bodycode;
	private Object response_bodymsg;
	

	public getweekappinfoTest(Object caseno, Object method, Object tenant, Object startdate, Object enddate,
			Object request_body, Object response_status, Object response_bodycode, Object response_bodymsg) {
		this.caseno = caseno;
		this.method = method;
		this.tenant = tenant;
		this.startdate = startdate;
		this.enddate = enddate;
		this.request_body = request_body;
		this.response_status = response_status;
		this.response_bodycode = response_bodycode;
		this.response_bodymsg = response_bodymsg;

	}

	@Parameters
	public static Collection getdata() {
		String filename = new Object() {
			public String getClassName() {
				String clazzName = this.getClass().getName();
				return clazzName.substring(0, clazzName.lastIndexOf('$'));
			}
		}.getClassName();
		logger.info(filename);
		filename = filename.replace('.', '/');
		File file = new File("/Users/qulei/Documents/workspace/ONCO-TEST/bin/" + filename + ".xlsx");
		logger.info(file.getAbsolutePath());
		ArrayList<ArrayList<Object>> data = parseEXCEL.readExcel(file);
		// logger.info("data size: " + data.size());
		// logger.info("第10个数据: " + data.get(10).toString());
		Object[][] returnData = new Object[data.size() - 1][data.get(0).size()];
		int i = 0;

		ArrayList<String> header = new ArrayList<String>();
		for (ArrayList<Object> entry : data) {
			logger.info("entry" + entry);
			if (i > 0) {
				int j = 0;

				for (Object field : entry) {
					if (j < data.get(0).size())
						returnData[i - 1][j] = field;
					j++;

				}
			}
			i++;
		}
		logger.info("returnData");
		logger.info(Arrays.asList(returnData));
		return Arrays.asList(returnData);

//		return Arrays.asList(new Object[][] { { "0", "GET", "DrPeng-Dev", "N/A", "2017-06-21T10:00:00Z",
//				"2017-06-27T10:00:00Z", "", "200.00", "1005.00", "device params exception" } });

	}

	@Before
	public void setUp() throws Exception {
		RestAssured.baseURI = "http://192.168.100.43:8088";
		RestAssured.port = 8088;
		url = "/business/getweekappinfo.php";
		// RestAssured.useRelaxedHTTPSValidation();
		// RestAssured.authentication = basic("TestWH", "admin@TestWH-123");
		// RestAssured.requestSpecification=accept("application/json");
		// RestAssured.with().accept("application/json");
		// logger.info("我是info信息");
		// logger.error("我是error信息");
		// logger.info(VerifyCodeMSG.class.getResource("/API/testdata.xlsx").getFile());

	}

	@After
	public void tearDown() throws Exception {
	}

	// @Ignore("忽略")
	@Test
	public void VerifyResponse() {
		// given().auth().basic("TestWH", "admin@TestWH-123")
		// .header("Accept", "application/json")
		// .get("/operational/appliances/Branch1-Hub-WH-DEV/live-status/orgs/org/Customer1/sd-wan/sla-monitor/path/metrics/last")
		// .then().body("title", equalTo("满月之夜白鲸现"));

		// Response response = given().auth().basic("TestWH",
		// "admin@TestWH-123")
		// .header("Accept", "application/json")
		// .get("/operational/appliances/Branch1-Hub-WH-DEV/live-status/orgs/org/Customer1/sd-wan/sla-monitor/path/metrics/last");
		// for (Dictionary testdata_entry : testdata) {
		// logger.info("test");

		Double case_no = new Double(this.caseno.toString());
		logger.info("----------------Test Case " + case_no.toString() + " start.----------------");
		logger.info("Test Data: " + this.method + "," + this.tenant +  "," + this.startdate + ","
				+ this.enddate + "," + this.request_body + "," + this.response_status + "," + this.response_bodycode
				+ "," + this.response_bodymsg);
		String method = this.method.toString();
		logger.info("method: " + method);
		String tenantname = this.tenant.toString();
		String paramlist;
		switch (tenantname) {
		case "null":
			paramlist = "tenantname=";
			break;
		case "blank":
			paramlist = "tenantname=   ";
			break;
		case "N/A":
			paramlist = "";
			break;
		default:
			paramlist = "tenantname=" + tenantname;
		}

		String startdate = this.startdate.toString();
		if (paramlist.length() > 0 && !paramlist.endsWith("&"))
			paramlist = paramlist + "&";

		switch (startdate) {
		case "null":
			paramlist = paramlist + "startdate=";
			break;
		case "blank":
			paramlist = paramlist + "startdate=   ";
			break;
		case "N/A":
			break;
		default:
			paramlist = paramlist + "startdate=" + startdate;
		}

		String enddate = this.enddate.toString();
		if (paramlist.length() > 0 && !paramlist.endsWith("&"))
			paramlist = paramlist + "&";

		switch (enddate) {
		case "null":
			paramlist = paramlist + "enddate=";
			break;
		case "blank":
			paramlist = paramlist + "enddate=   ";
			break;
		case "N/A":
			break;
		default:
			paramlist = paramlist + "enddate=" + enddate;
		}

		url = url + "?" + paramlist;
		logger.info("url: " + url);

		String requestBody = this.request_body.toString().replace('"', '\"');
		logger.info("requestBody: " + requestBody);

		Response response = null;
		switch (method) {
		case "GET":
			response = given().header("Accept", "application/json").get(url);
			break;
		case "POST":
			response = given().contentType(ContentType.JSON).body(requestBody).when().post(url);
			break;
		case "PUT":
			response = given().contentType(ContentType.JSON).body(requestBody).when().put(url);
			break;
		case "DELETE":
			response = given().header("Accept", "application/json").delete(url);
			break;
		}
		int expected_status = new Double(this.response_status.toString()).intValue();
		logger.info("Expected status Code: " + expected_status);
		int actual_status = response.statusCode();
		logger.info("Actual status Code: " + actual_status);
		assertThat(actual_status, equalTo(expected_status));

		int expected_code = new Double(this.response_bodycode.toString()).intValue();
		logger.info("Expected Code: " + expected_code);
		int actual_Code = JsonPath.from(response.getBody().asString()).getInt("code");
		logger.info("Actual Code: " + actual_Code);
		assertThat(actual_Code, equalTo(expected_code));

		String expected_msg = this.response_bodymsg.toString();
		logger.info("Expected Msg: " + expected_msg);
		String actual_Msg = JsonPath.from(response.getBody().asString()).getString("message");
		logger.info("Actual Msg: " + actual_Msg);
		assertThat(actual_Msg, equalTo(expected_msg));

		logger.info("----------------Test Case " + case_no.toString() + " end.----------------");
		// }
	}

	private static Object[][] convert2array(ArrayList<ArrayList<Object>> data) {
		Object[][] returnData = new Object[][] {};
		logger.info("data" + data);
		int count = 0;
		ArrayList<String> header = new ArrayList<String>();
		for (ArrayList<Object> entry : data) {
			// logger.info("entry" + entry);

			if (count == 0) {

				for (Object field : entry) {
					header.add(field.toString());
				}

			} else {
				// logger.info("header" + header);
				int index = 0;
				Dictionary<String, Object> hashTable = new Hashtable<String, Object>();
				for (Object field : entry) {
					hashTable.put(header.get(index), field);
					index++;
				}
				returnData[count][0] = hashTable;
			}
			count++;
		}
		// logger.info(returnData);
		return returnData;
	}

}
