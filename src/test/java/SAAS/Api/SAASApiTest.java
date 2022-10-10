package SAAS.Api;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Pages.HeaderPage;
import io.qameta.allure.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

@Epic("All APIs of Vizalys")
@Feature("Testing APIs")
class SAASApiTest {
    static String APIbodyTOkenLOgin;
    static List<Header> headerlist = new ArrayList<Header>();
    static public String token;

    //Header Reusable Variables
    static public String authorizationName;
    static public String authorizationToken;

    static public String headerType = "Content-Type";
    static public String headerMediaType = "application/json";


    HeaderPage headers = new HeaderPage();
    public SAASApiTest() {

        RestAssured.baseURI = "http://testapi.vizalys.com";

    }


    @Nested
    @DisplayName("VizaysSAAS Api's")
    class Api {


        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Story("All Latest Vizalys APIs")
        @Description("Happy Flow Login APi")
        //@Order(1)
        //(1)
        public void VizalysSAAS_ATokenLogin() {
            System.out.println("----------API_TokenLogIn----------");
//            String APIBody = "{\"email\": \"ahsan.abdullah@ratechnologies.net\",\n" +
//                    "  \"password\": \"Loss@123\",\n" +
//                    "  \"organizationId\": 2}";
//            Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/auth/login.json");

            Response r;

            //headerlist.add(new Header("Content-Type", "application/json"));
            //System.out.println(headerlist.toString());
            r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/Auth/Login");


            ResponseBody body = r.getBody();
            int statusCode = r.getStatusCode();
            System.out.println(statusCode);


            APIbodyTOkenLOgin = body.asString();    ///I COMMENTED THIS OUT FOR CHECKING response

            System.out.println("Response body " + APIbodyTOkenLOgin);


            JSONObject json = new JSONObject(body.asString());
            json = json.getJSONObject("result");
            token = json.getString("token");
            System.out.println("checking LoginToken " + token);

            authorizationName = "Authorization";
            authorizationToken = "bearer " + token;
            System.out.println(authorizationToken);
            //departmentPage.allHeaders(authorizationName,authorizationToken);
            //VizalysSAAS_Get_Department(authorizationName, authorizationToken);
        }
        @Order(1)
        @Test

        //(2)
        public void ZZZVizalysSAAS_Post_Roles()
        {
            System.out.println("----------***** VizalysSAAS_Post_Roles *****-----------");



            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/auth/roles.json");

            //Out-Dated-Headers
//            List<Header> headerlist = new ArrayList<Header>();
//            headerlist.add(new Header(headerType, headerMediaType));
////            headerlist.add(new Header("device-id","1"));
////            headerlist.add(new Header("user-agents","postman"));
////            headerlist.add(new Header("device-type","web")); //check this
////            headerlist.add(new Header("license-key","213DD508-876F-4DD3-BBC1-0A33CC54A6C0")); //check this
////            headerlist.add(new Header("user-host-name","salman"));
////            headerlist.add(new Header("user-language","English"));
////            headerlist.add(new Header("user-host-address","::::0"));
//            //headerlist.add(new Header("Authorization","bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiYWhzYW4uYWJkdWxsYWhAcmF0ZWNobm9sb2dpZXMubmV0IiwiRW1haWwiOiJhaHNhbi5hYmR1bGxhaEByYXRlY2hub2xvZ2llcy5uZXQiLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IjBlMGJjZGRlLTY5NjItNDYwOS1iZmU3LWJjMjQ5ZWEyNGE0YyIsIk9yZ2FuaXphdGlvbiI6IjQiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJTdXBlckFkbWluIiwiUGVybWlzc2lvbiI6WyJQZXJtaXNzaW9ucy5BdXRoQ2xhaW1zLkNyZWF0ZSIsIlBlcm1pc3Npb25zLkF1dGhDbGFpbXMuVmlldyIsIlBlcm1pc3Npb25zLkF1dGhDbGFpbXMuRWRpdCIsIlBlcm1pc3Npb25zLkF1dGhDbGFpbXMuRGVsZXRlIiwiUGVybWlzc2lvbnMuQnVzaW5lc3NQYXJ0bmVyQ2xhaW1zLkNyZWF0ZSIsIlBlcm1pc3Npb25zLkJ1c2luZXNzUGFydG5lckNsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuQnVzaW5lc3NQYXJ0bmVyQ2xhaW1zLkVkaXQiLCJQZXJtaXNzaW9ucy5CdXNpbmVzc1BhcnRuZXJDbGFpbXMuRGVsZXRlIiwiUGVybWlzc2lvbnMuT3JnYW5pemF0aW9uQ2xhaW1zLkNyZWF0ZSIsIlBlcm1pc3Npb25zLk9yZ2FuaXphdGlvbkNsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuT3JnYW5pemF0aW9uQ2xhaW1zLkVkaXQiLCJQZXJtaXNzaW9ucy5Pcmdhbml6YXRpb25DbGFpbXMuRGVsZXRlIiwiUGVybWlzc2lvbnMuRGVwYXJ0bWVudHNDbGFpbXMuQ3JlYXRlIiwiUGVybWlzc2lvbnMuRGVwYXJ0bWVudHNDbGFpbXMuVmlldyIsIlBlcm1pc3Npb25zLkRlcGFydG1lbnRzQ2xhaW1zLkVkaXQiLCJQZXJtaXNzaW9ucy5EZXBhcnRtZW50c0NsYWltcy5EZWxldGUiLCJQZXJtaXNzaW9ucy5XYXJlaG91c2VDbGFpbXMuQ3JlYXRlIiwiUGVybWlzc2lvbnMuV2FyZWhvdXNlQ2xhaW1zLlZpZXciLCJQZXJtaXNzaW9ucy5XYXJlaG91c2VDbGFpbXMuRWRpdCIsIlBlcm1pc3Npb25zLldhcmVob3VzZUNsYWltcy5EZWxldGUiLCJQZXJtaXNzaW9ucy5Mb2NhdGlvbkNsYWltcy5DcmVhdGUiLCJQZXJtaXNzaW9ucy5Mb2NhdGlvbkNsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuTG9jYXRpb25DbGFpbXMuRWRpdCIsIlBlcm1pc3Npb25zLkxvY2F0aW9uQ2xhaW1zLkRlbGV0ZSIsIlBlcm1pc3Npb25zLkJhbmtBY2NvdW50Q2xhaW1zLkNyZWF0ZSIsIlBlcm1pc3Npb25zLkJhbmtBY2NvdW50Q2xhaW1zLlZpZXciLCJQZXJtaXNzaW9ucy5CYW5rQWNjb3VudENsYWltcy5FZGl0IiwiUGVybWlzc2lvbnMuQmFua0FjY291bnRDbGFpbXMuRGVsZXRlIiwiUGVybWlzc2lvbnMuQmFua1N0YXRlbWVudENsYWltcy5DcmVhdGUiLCJQZXJtaXNzaW9ucy5CYW5rU3RhdGVtZW50Q2xhaW1zLlZpZXciLCJQZXJtaXNzaW9ucy5CYW5rU3RhdGVtZW50Q2xhaW1zLkVkaXQiLCJQZXJtaXNzaW9ucy5CYW5rU3RhdGVtZW50Q2xhaW1zLkRlbGV0ZSIsIlBlcm1pc3Npb25zLkNhc2hBY2NvdW50Q2xhaW1zLkNyZWF0ZSIsIlBlcm1pc3Npb25zLkNhc2hBY2NvdW50Q2xhaW1zLlZpZXciLCJQZXJtaXNzaW9ucy5DYXNoQWNjb3VudENsYWltcy5FZGl0IiwiUGVybWlzc2lvbnMuQ2FzaEFjY291bnRDbGFpbXMuRGVsZXRlIiwiUGVybWlzc2lvbnMuQ2F0ZWdvcmllc0NsYWltcy5DcmVhdGUiLCJQZXJtaXNzaW9ucy5DYXRlZ29yaWVzQ2xhaW1zLlZpZXciLCJQZXJtaXNzaW9ucy5DYXRlZ29yaWVzQ2xhaW1zLkVkaXQiLCJQZXJtaXNzaW9ucy5DYXRlZ29yaWVzQ2xhaW1zLkRlbGV0ZSIsIlBlcm1pc3Npb25zLlByb2R1Y3RzQ2xhaW1zLkNyZWF0ZSIsIlBlcm1pc3Npb25zLlByb2R1Y3RzQ2xhaW1zLlZpZXciLCJQZXJtaXNzaW9ucy5Qcm9kdWN0c0NsYWltcy5FZGl0IiwiUGVybWlzc2lvbnMuUHJvZHVjdHNDbGFpbXMuRGVsZXRlIiwiUGVybWlzc2lvbnMuTGV2ZWw0Q2xhaW1zLkNyZWF0ZSIsIlBlcm1pc3Npb25zLkxldmVsNENsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuTGV2ZWw0Q2xhaW1zLkVkaXQiLCJQZXJtaXNzaW9ucy5MZXZlbDRDbGFpbXMuRGVsZXRlIiwiUGVybWlzc2lvbnMuQmFua1JlY29uQ2xhaW1zLkNyZWF0ZSIsIlBlcm1pc3Npb25zLkJhbmtSZWNvbkNsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuQmFua1JlY29uQ2xhaW1zLkVkaXQiLCJQZXJtaXNzaW9ucy5CYW5rUmVjb25DbGFpbXMuRGVsZXRlIiwiUGVybWlzc2lvbnMuVHJhbnNhY3Rpb25SZWNvbkNsYWltcy5DcmVhdGUiLCJQZXJtaXNzaW9ucy5UcmFuc2FjdGlvblJlY29uQ2xhaW1zLlZpZXciLCJQZXJtaXNzaW9ucy5UcmFuc2FjdGlvblJlY29uQ2xhaW1zLkVkaXQiLCJQZXJtaXNzaW9ucy5UcmFuc2FjdGlvblJlY29uQ2xhaW1zLkRlbGV0ZSIsIlBlcm1pc3Npb25zLkludm9pY2VDbGFpbXMuQ3JlYXRlIiwiUGVybWlzc2lvbnMuSW52b2ljZUNsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuSW52b2ljZUNsYWltcy5FZGl0IiwiUGVybWlzc2lvbnMuSW52b2ljZUNsYWltcy5EZWxldGUiLCJQZXJtaXNzaW9ucy5CaWxsQ2xhaW1zLkNyZWF0ZSIsIlBlcm1pc3Npb25zLkJpbGxDbGFpbXMuVmlldyIsIlBlcm1pc3Npb25zLkJpbGxDbGFpbXMuRWRpdCIsIlBlcm1pc3Npb25zLkJpbGxDbGFpbXMuRGVsZXRlIiwiUGVybWlzc2lvbnMuUGF5bWVudENsYWltcy5DcmVhdGUiLCJQZXJtaXNzaW9ucy5QYXltZW50Q2xhaW1zLlZpZXciLCJQZXJtaXNzaW9ucy5QYXltZW50Q2xhaW1zLkVkaXQiLCJQZXJtaXNzaW9ucy5QYXltZW50Q2xhaW1zLkRlbGV0ZSIsIlBlcm1pc3Npb25zLkNyZWRpdE5vdGVDbGFpbXMuQ3JlYXRlIiwiUGVybWlzc2lvbnMuQ3JlZGl0Tm90ZUNsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuQ3JlZGl0Tm90ZUNsYWltcy5FZGl0IiwiUGVybWlzc2lvbnMuQ3JlZGl0Tm90ZUNsYWltcy5EZWxldGUiLCJQZXJtaXNzaW9ucy5EZWJpdE5vdGVDbGFpbXMuQ3JlYXRlIiwiUGVybWlzc2lvbnMuRGViaXROb3RlQ2xhaW1zLlZpZXciLCJQZXJtaXNzaW9ucy5EZWJpdE5vdGVDbGFpbXMuRWRpdCIsIlBlcm1pc3Npb25zLkRlYml0Tm90ZUNsYWltcy5EZWxldGUiLCJQZXJtaXNzaW9ucy5Kb3VybmFsRW50cnlDbGFpbXMuQ3JlYXRlIiwiUGVybWlzc2lvbnMuSm91cm5hbEVudHJ5Q2xhaW1zLlZpZXciLCJQZXJtaXNzaW9ucy5Kb3VybmFsRW50cnlDbGFpbXMuRWRpdCIsIlBlcm1pc3Npb25zLkpvdXJuYWxFbnRyeUNsYWltcy5EZWxldGUiLCJQZXJtaXNzaW9ucy5DaGFydE9mQWNjb3VudENsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuR2VuZXJhbExlZGdlckNsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuUHJvZml0TG9zc0NsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuQmFsYW5jZVNoZWV0Q2xhaW1zLlZpZXciLCJQZXJtaXNzaW9ucy5UcmlhbEJhbGFuY2VDbGFpbXMuVmlldyJdLCJleHAiOjE2NTYwMDYzMjUsImlzcyI6Imh0dHA6Ly92aXphbHlzdGVzdC5uZXQiLCJhdWQiOiJodHRwOi8vdml6YWx5c3Rlc3QubmV0In0.Ien9KLLXXmssYlSugQhy27PhV5CFecwXHbkfWHsH2Tw"));
//            //headerlist.add(new Header("Authorization","bearer "+token));
//            headerlist.add(new Header(authorizationName,authorizationToken));
//            Headers headers = new Headers(headerlist);

            //Headers
//            headerlist.add(new Header(authorizationName, authorizationToken));

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/Auth/Roles");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }

        }

        @Order(2)
        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Story("All Latest Vizalys APIs")
        @Description("Happy Flow Get Roles API")
        //@Order(3)
        //(2)
        public void VizalysSAAS_Get_Roles()
        {
            System.out.println("----------***** VizalysSAAS_Get_Roles *****-----------");

            String APIBody = "{}";

//            headerlist.add(new Header(authorizationName, authorizationToken));
            //headerlist.add(new Header("ahsan", "xyz"));
            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/Auth/Roles");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }

        }

        @Order(3)
        @Test
        //@Order(4)
        //(3)
        public void VizalysSAAS_Post_Users()
        {
            System.out.println("----------***** VizalysSAAS_Post_Users *****-----------");


//            String APIBody = "{\"userName\": \"ali\",\n" +
//                    "  \"email\": \"ali@gmail.com\",\n" +
//                    "  \"password\": \"Loss@123\",\n" +
//                    "  \"confirmPassword\": \"Loss@123\",\n" +
//                    "  \"userRoles\": [\n" +
//                    "    {\n" +
//                    "      \"roleName\": \"SuperAdmin\",\n" +
//                    "      \"selected\": true\n" +
//                    "    }\n" +
//                    "  ]}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/auth/users.json");

            //Headers
//            headerlist.add(new Header(authorizationName, authorizationToken));

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/Auth/Users");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }

        }
        @Order(4)
        @Test
        //@Order(5)
        //(3)
        public void VizalysSAAS_Get_Users()
        {
            System.out.println("----------***** VizalysSAAS_Get_Users *****-----------");


            String APIBody = "{}";

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/Auth/Users");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }

        }

        @Test
        //@Order(6)
        //(6)
        public void ZZZVizalysSAAS_Post_Department()
        {
            System.out.println("----------***** VizalysSAAS_Post_Department *****-----------");


//            String APIBody = "{\"id\": 0,\n" +
//                    "  \"name\": \"IT Department\",\n" +
//                    "  \"country\": \"Pakistan\",\n" +
//                    "  \"state\": \"Sindh\",\n" +
//                    "  \"city\": \"Karachi\",\n" +
//                    "  \"address\": \"R - 4343\",\n" +
//                    "  \"headOfDept\": \"Mr. Farrukh Ahmed\"}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/profiling/department.json");


            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/Department");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }

        }

        @Test
        //@Order(7)
        //(6)
        public void ZZZ_VizalysSAAS_Get_Department()
        {
            System.out.println("----------***** VizalysSAAS_Get_Department *****-----------");


            String APIBody = "{}";

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/Department");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }

        }

//        @Test
//        //@Order(7)
//        //(6)
//        public void VizalysSAAS_Get_Department_ById()
//        {
//            System.out.println("----------***** VizalysSAAS_Get_Department_ById *****-----------");
//
//
//            String APIBody = "{}";
//
//
//            List<Header> headerlist = new ArrayList<Header>();
//            headerlist.add(new Header(headerType, headerMediaType));
////            headerlist.add(new Header("device-id","1"));
////            headerlist.add(new Header("user-agents","postman"));
////            headerlist.add(new Header("device-type","web")); //check this
////            headerlist.add(new Header("license-key","213DD508-876F-4DD3-BBC1-0A33CC54A6C0")); //check this
////            headerlist.add(new Header("user-host-name","salman"));
////            headerlist.add(new Header("user-language","English"));
////            headerlist.add(new Header("user-host-address","::::0"));
//            //headerlist.add(new Header("Authorization","bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiYWhzYW4uYWJkdWxsYWhAcmF0ZWNobm9sb2dpZXMubmV0IiwiRW1haWwiOiJhaHNhbi5hYmR1bGxhaEByYXRlY2hub2xvZ2llcy5uZXQiLCJPcmdhbml6YXRpb24iOiIwIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZWlkZW50aWZpZXIiOiJjZTBiMGIzNi1iYjcxLTRlZjMtYWFiMC0wY2Y1MjY5OTljYzQiLCJleHAiOjE2NTk1NDA5MTYsImlzcyI6Imh0dHA6Ly92aXphbHlzdGVzdC5uZXQiLCJhdWQiOiJodHRwOi8vdml6YWx5c3Rlc3QubmV0In0.B0btP5_Vv_J95Gn-TmT0Nwn0T2S8_5PgfGUO7C6YCGA"));
//            //headerlist.add(new Header("Authorization","bearer "+token));
//            headerlist.add(new Header(authorizationName,authorizationToken));
//            Headers headers = new Headers(headerlist);
//
//
//
//            Response r = given().body(APIBody).
//                    headers(headers).
//                    when().
//                    get("/api/Department/4");
//
//            //r.prettyPrint();
//            String body = r.getBody().asString();
//            //ResponseBody  body = r.getBody();
//            System.out.println(body);
//
//            int statusCode = r.getStatusCode();
//            System.out.println(statusCode);
//
//            // Assert that correct status code is returned.
//            //assertEquals(statusCode /*actual value*/, 200 /*expected value*/);
//
//
////            String bodyAsString = body.asString();
////            System.out.println(bodyAsString);
//
////            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
////            {
////                System.out.println("(API responded wrong)");
////                fail();
////            }
//
//        }

//        @Test
//        //@Order(7)
//        //(6)
//        public void VizalysSAAS_Put_Department_ById()
//        {
//            System.out.println("----------***** VizalysSAAS_Put_Department_ById *****-----------");
//
//
//            String APIBody = "{\"id\": 4,\n" +
//                    "  \"name\": \"IT Department\",\n" +
//                    "  \"country\": \"Pakistan\",\n" +
//                    "  \"state\": \"Sindh\",\n" +
//                    "  \"city\": \"Karachi\",\n" +
//                    "  \"address\": \"R - 4343\",\n" +
//                    "  \"headOfDept\": \"Mr. Farrukh Ahmed\"}";
//
//
//            List<Header> headerlist = new ArrayList<Header>();
//            headerlist.add(new Header(headerType, headerMediaType));
////            headerlist.add(new Header("device-id","1"));
////            headerlist.add(new Header("user-agents","postman"));
////            headerlist.add(new Header("device-type","web")); //check this
////            headerlist.add(new Header("license-key","213DD508-876F-4DD3-BBC1-0A33CC54A6C0")); //check this
////            headerlist.add(new Header("user-host-name","salman"));
////            headerlist.add(new Header("user-language","English"));
////            headerlist.add(new Header("user-host-address","::::0"));
//            //headerlist.add(new Header("Authorization","bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiYWhzYW4uYWJkdWxsYWhAcmF0ZWNobm9sb2dpZXMubmV0IiwiRW1haWwiOiJhaHNhbi5hYmR1bGxhaEByYXRlY2hub2xvZ2llcy5uZXQiLCJPcmdhbml6YXRpb24iOiIwIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZWlkZW50aWZpZXIiOiJjZTBiMGIzNi1iYjcxLTRlZjMtYWFiMC0wY2Y1MjY5OTljYzQiLCJleHAiOjE2NTk1NDA5MTYsImlzcyI6Imh0dHA6Ly92aXphbHlzdGVzdC5uZXQiLCJhdWQiOiJodHRwOi8vdml6YWx5c3Rlc3QubmV0In0.B0btP5_Vv_J95Gn-TmT0Nwn0T2S8_5PgfGUO7C6YCGA"));
//            //headerlist.add(new Header("Authorization","bearer "+token));
//            headerlist.add(new Header(authorizationName,authorizationToken));
//            Headers headers = new Headers(headerlist);
//
//
//
//            Response r = given().body(APIBody).
//                    headers(headers).
//                    when().
//                    put("/api/Department/4");
//
//            //r.prettyPrint();
//            String body = r.getBody().asString();
//            //ResponseBody  body = r.getBody();
//            System.out.println(body);
//
//            int statusCode = r.getStatusCode();
//            System.out.println(statusCode);
//
//            // Assert that correct status code is returned.
//            //assertEquals(statusCode /*actual value*/, 200 /*expected value*/);
//
//
////            String bodyAsString = body.asString();
////            System.out.println(bodyAsString);
//
////            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
////            {
////                System.out.println("(API responded wrong)");
////                fail();
////            }
//
//        }

        @Test
        //@Order(8)
        //(7)
        public void VizalysSAAS_Post_Warehouse()
        {
            System.out.println("----------***** VizalysSAAS_Post_Warehouse *****-----------");


//            String APIBody = "{\"id\": 0,\n" +
//                    "  \"name\": \"FIM\",\n" +
//                    "  \"country\": \"Pakistan\",\n" +
//                    "  \"state\": \"Sindh\",\n" +
//                    "  \"city\": \"Hyderabad\",\n" +
//                    "  \"address\": \"E - 3434\",\n" +
//                    "  \"manager\": \"Mr. Faisal\",\n" +
//                    "  \"departmentId\": 5}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/profiling/warehouse.json");


            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/Warehouse");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        //@Order(9)
        //(7)
        public void VizalysSAAS_Get_Warehouse()
        {
            System.out.println("----------***** VizalysSAAS_Get_Warehouse *****-----------");


            String APIBody = "{}";

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/Warehouse");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

//        @Test
//        //@Order(9)
//        //(7)
//        public void VizalysSAAS_Get_Warehouse_ById()
//        {
//            System.out.println("----------***** VizalysSAAS_Get_Warehouse_ById *****-----------");
//
//
//            String APIBody = "{}";
//
//
//            List<Header> headerlist = new ArrayList<Header>();
//            headerlist.add(new Header(headerType, headerMediaType));
////            headerlist.add(new Header("device-id","1"));
////            headerlist.add(new Header("user-agents","postman"));
////            headerlist.add(new Header("device-type","web")); //check this
////            headerlist.add(new Header("license-key","213DD508-876F-4DD3-BBC1-0A33CC54A6C0")); //check this
////            headerlist.add(new Header("user-host-name","salman"));
////            headerlist.add(new Header("user-language","English"));
////            headerlist.add(new Header("user-host-address","::::0"));
//            //headerlist.add(new Header("Authorization","bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiYWhzYW4uYWJkdWxsYWhAcmF0ZWNobm9sb2dpZXMubmV0IiwiRW1haWwiOiJhaHNhbi5hYmR1bGxhaEByYXRlY2hub2xvZ2llcy5uZXQiLCJPcmdhbml6YXRpb24iOiIwIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZWlkZW50aWZpZXIiOiJjZTBiMGIzNi1iYjcxLTRlZjMtYWFiMC0wY2Y1MjY5OTljYzQiLCJleHAiOjE2NTkzNzc0NTUsImlzcyI6Imh0dHA6Ly92aXphbHlzdGVzdC5uZXQiLCJhdWQiOiJodHRwOi8vdml6YWx5c3Rlc3QubmV0In0.N8U69NcUcOkIA6VsGrfXBjwf_lL4DceSWShm-i6LqQM"));
//            //headerlist.add(new Header("Authorization","bearer "+token));
//            headerlist.add(new Header(authorizationName,authorizationToken));
//            Headers headers = new Headers(headerlist);
//
//
//
//            Response r = given().body(APIBody).
//                    headers(headers).
//                    when().
//                    get("/api/Warehouse/31");
//
//            //r.prettyPrint();
//            String body = r.getBody().asString();
//            //ResponseBody  body = r.getBody();
//            System.out.println(body);
//
//            int statusCode = r.getStatusCode();
//            System.out.println(statusCode);
//
//            // Assert that correct status code is returned.
//            //assertEquals(statusCode /*actual value*/, 200 /*expected value*/);
//
//
////            String bodyAsString = body.asString();
////            System.out.println(bodyAsString);
//
////            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
////            {
////                System.out.println("(API responded wrong)");
////                fail();
////            }
//        }

//        @Test
//        //@Order(9)
//        //(7)
//        public void VizalysSAAS_Put_Warehouse_ById()
//        {
//            System.out.println("----------***** VizalysSAAS_Put_Warehouse_ById *****-----------");
//
//
//            String APIBody = "{\"id\": 31,\n" +
//                    "  \"name\": \"FIM\",\n" +
//                    "  \"country\": \"Pakistan\",\n" +
//                    "  \"state\": \"Sindh\",\n" +
//                    "  \"city\": \"Hyderabad\",\n" +
//                    "  \"address\": \"E - 3434\",\n" +
//                    "  \"manager\": \"Mr. Faisal\",\n" +
//                    "  \"departmentId\": 5}";
//
//
//            List<Header> headerlist = new ArrayList<Header>();
//            headerlist.add(new Header(headerType, headerMediaType));
////            headerlist.add(new Header("device-id","1"));
////            headerlist.add(new Header("user-agents","postman"));
////            headerlist.add(new Header("device-type","web")); //check this
////            headerlist.add(new Header("license-key","213DD508-876F-4DD3-BBC1-0A33CC54A6C0")); //check this
////            headerlist.add(new Header("user-host-name","salman"));
////            headerlist.add(new Header("user-language","English"));
////            headerlist.add(new Header("user-host-address","::::0"));
//            //headerlist.add(new Header("Authorization","bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiYWhzYW4uYWJkdWxsYWhAcmF0ZWNobm9sb2dpZXMubmV0IiwiRW1haWwiOiJhaHNhbi5hYmR1bGxhaEByYXRlY2hub2xvZ2llcy5uZXQiLCJPcmdhbml6YXRpb24iOiIwIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZWlkZW50aWZpZXIiOiJjZTBiMGIzNi1iYjcxLTRlZjMtYWFiMC0wY2Y1MjY5OTljYzQiLCJleHAiOjE2NTkzNzc0NTUsImlzcyI6Imh0dHA6Ly92aXphbHlzdGVzdC5uZXQiLCJhdWQiOiJodHRwOi8vdml6YWx5c3Rlc3QubmV0In0.N8U69NcUcOkIA6VsGrfXBjwf_lL4DceSWShm-i6LqQM"));
//            //headerlist.add(new Header("Authorization","bearer "+token));
//            headerlist.add(new Header(authorizationName,authorizationToken));
//            Headers headers = new Headers(headerlist);
//
//
//
//            Response r = given().body(APIBody).
//                    headers(headers).
//                    when().
//                    put("/api/Warehouse/31");
//
//            //r.prettyPrint();
//            String body = r.getBody().asString();
//            //ResponseBody  body = r.getBody();
//            System.out.println(body);
//
//            int statusCode = r.getStatusCode();
//            System.out.println(statusCode);
//
//            // Assert that correct status code is returned.
//            //assertEquals(statusCode /*actual value*/, 200 /*expected value*/);
//
//
////            String bodyAsString = body.asString();
////            System.out.println(bodyAsString);
//
////            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
////            {
////                System.out.println("(API responded wrong)");
////                fail();
////            }
//        }

        @Test
        //@Order(10)
        //(8)
        public void ZZZVizalysSAAS_Post_Location()
        {
            System.out.println("----------***** VizalysSAAS_Post_Location *****-----------");


//            String APIBody = "{\"id\": 0,\n" +
//                    "  \"name\": \"Location 01\",\n" +
//                    "  \"dimensions\": \"456\",\n" +
//                    "  \"supervisor\": \"Mr. Kashif\",\n" +
//                    "  \"warehouseId\": 7}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/profiling/location.json");

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/Location");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        //@Order(11)
        //(8)
        public void VizalysSAAS_Get_Location()
        {
            System.out.println("----------***** VizalysSAAS_Get_Location *****-----------");


            String APIBody = "{}";


            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/Location");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.

            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);

//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        //@Order(12)
        //(9)
        public void VizalysSAAS_Post_BusinessPartner()
        {
            System.out.println("----------***** VizalysSAAS_Post_BusinessPartner *****-----------");


//            String APIBody = "{\"businessPartnerType\": 0,\n" +
//                    "    \"entity\": \"Company\",\n" +
//                    "    \"country\": \"Pakistan\",\n" +
//                    "    \"state\": \"Sindh\",\n" +
//                    "    \"city\": \"Karachi\",\n" +
//                    "    \"name\": \"Qiyam Gah\",\n" +
//                    "    \"address\": \"W - 4343\",\n" +
//                    "    \"phone\": \"02136547988\",\n" +
//                    "    \"mobile\": \"0225665662\",\n" +
//                    "    \"incomeTaxId\": \"1\",\n" +
//                    "    \"salesTaxId\": \"1\",\n" +
//                    "    \"bankAccountTitle\": \"MCB\",\n" +
//                    "    \"bankAccountNumber\": \"5555555555555\",\n" +
//                    "    \"accountPayableId\": \"31210000-5566-7788-99AA-BBCCDDEEFF00+4\",\n" +
//                    "    \"accountReceivableId\": \"31210000-5566-7788-99AA-BBCCDDEEFF00+4\",\n" +
//                    "    \"cnic\": \"4246565656985\"}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/profiling/businesspartner.json");


            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/BusinessPartner");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        //@Order(13)
        //(9)
        public void ZZZVizalysSAAS_Get_BusinessPartner()
        {
            System.out.println("----------***** VizalysSAAS_Get_BusinessPartner *****-----------");


            String APIBody = "{}";

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/BusinessPartner");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        //@Order(14)
        //(10)
        public void VizalysSAAS_Post_Category()
        {
            System.out.println("----------***** VizalysSAAS_Post_Category *****-----------");


//            String APIBody = "{\"name\": \"Electronic Appliances\",\n" +
//                    "    \"inventoryAccountId\": \"31210000-5566-7788-99AA-BBCCDDEEFF00+4\",\n" +
//                    "    \"revenueAccountId\": \"31210000-5566-7788-99AA-BBCCDDEEFF00+4\",\n" +
//                    "    \"costAccountId\": \"31210000-5566-7788-99AA-BBCCDDEEFF00+4\"}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/profiling/category.json");

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/Category");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        //@Order(15)
        //(10)
        public void ZZZVizalysSAAS_Get_Category()
        {
            System.out.println("----------***** VizalysSAAS_Get_Category *****-----------");


            String APIBody = "{}";

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/Category");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        //@Order(16)
        //(11)
        public void VizalysSAAS_Post_Product()
        {
            System.out.println("----------***** VizalysSAAS_Post_Product *****-----------");


//            String APIBody = "{\"productName\": \"Printer\",\n" +
//                    "    \"purchasedOrSold\": 1,\n" +
//                    "    \"productType\": 1,\n" +
//                    "    \"categoryId\": 2,\n" +
//                    "    \"salesPrice\": \"4500\",\n" +
//                    "    \"cost\": \"3500\",\n" +
//                    "    \"salesTax\": \"5\",\n" +
//                    "    \"barcode\": \"BB-44\"}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/profiling/product.json");

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/Product");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        //@Order(17)
        //(11)
        public void VizalkysSAAS_Get_Product()
        {
            System.out.println("----------***** VizalysSAAS_Get_Product *****-----------");


            String APIBody = "{}";

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/Product");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        //@Order(18)
        //(12)
        public void VizalysSAAS_Post_CashAccount()
        {
            System.out.println("----------***** VizalysSAAS_Post_CashAccount *****-----------");


//            String APIBody = "{\"cashAccountName\": \"Sohaib(cash account)\",\n" +
//                    "    \"handler\": \"Mr. Ali\",\n" +
//                    "    \"openingBalance\": \"560\",\n" +
//                    "    \"OBDate\": \"Jun 23, 2022\",\n" +
//                    "    \"currency\": \"PKR\"}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/finance/cashaccount.json");


            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/CashAccount");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        //@Order(19)
        //(12)
        public void ZZZ_VizalysSAAS_Get_CashAccount()
        {
            System.out.println("----------***** VizalysSAAS_Get_CashAccount *****-----------");


            String APIBody = "{}";

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/CashAccount");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        //@Order(20)
        //(13)
        public void VizalysSAAS_Post_BankAccount()
        {
            System.out.println("----------***** VizalysSAAS_Post_BankAccount *****-----------");


//            String APIBody = "{ \"accountNumber\": \"25457896562\",\n" +
//                    "    \"bankName\": \"HBL\",\n" +
//                    "    \"branch\": \"SMCH\",\n" +
//                    "    \"openingBalance\": \"1200\",\n" +
//                    "    \"accountTitle\": \"Farhan Saeed\",\n" +
//                    "    \"openingBalanceDate\": \"2022-06-23\",\n" +
//                    "    \"currency\": \"PKR\"}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/finance/bankaccount.json");


            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/BankAccount");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        //@Order(21)
        //(13)
        public void VizalysSAAS_Get_BankAccount()
        {
            System.out.println("----------***** VizalysSAAS_Get_BankAccount *****-----------");


            String APIBody = "{}";


            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/BankAccount");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        //---------- This Payment Voucher API is not Working -------------//
        @Test
        //@Order(22)
        //(14)
        public void VizalysSAAS_Post_PaymentVoucher()
        {
            System.out.println("----------***** VizalysSAAS_Post_PaymentVoucher *****-----------");


//            String APIBody = "{ \"paymentRegisterType\": 2,\n" +
//                    "    \"paymentType\": 0,\n" +
//                    "    \"businessPartnerId\": 2,\n" +
//                    "    \"accountId\": \"31210000-5566-7788-99AA-BBCCDDEEFF00+4\",\n" +
//                    "    \"paymentDate\": \"2022-06-24\",\n" +
//                    "    \"paymentRegisterId\": \"850e6d6b-895d-473f-92e6-7afa908fc995+4\",\n" +
//                    "    \"description\": \"vvv\",\n" +
//                    "    \"grossPayment\": \"1200\",\n" +
//                    "    \"discount\": \"5\",\n" +
//                    "    \"salesTax\": \"5\",\n" +
//                    "    \"incomeTax\": \"10\",\n" +
//                    "    \"isSubmit\": false}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/finance/paymentvoucher.json");


            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/Payment");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        //---------- This Payment Voucher API is not Working -------------//
        @Test
        //@Order(23)
        //(14)
        public void ZZZ_VizalysSAAS_Get_PaymentVoucher()
        {
            System.out.println("----------***** VizalysSAAS_Get_PaymentVoucher *****-----------");


            String APIBody = "{}";

            //Headers
//            headerlist.add(new Header(authorizationName, authorizationToken));

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/Payment");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }


        //---------- This Receipt Voucher API is not Working -------------//
        @Test
        //@Order(22)
        //(15)
        public void ZZZ_VizalysSAAS_Post_ReceiptVoucher()
        {
            System.out.println("----------***** VizalysSAAS_Post_ReceiptVoucher *****-----------");


//            String APIBody = "{ \"paymentRegisterType\": 2,\n" +
//                    "    \"paymentType\": 0,\n" +
//                    "    \"businessPartnerId\": 2,\n" +
//                    "    \"accountId\": \"31210000-5566-7788-99AA-BBCCDDEEFF00+4\",\n" +
//                    "    \"paymentDate\": \"2022-06-24\",\n" +
//                    "    \"paymentRegisterId\": \"850e6d6b-895d-473f-92e6-7afa908fc995+4\",\n" +
//                    "    \"description\": \"vvv\",\n" +
//                    "    \"grossPayment\": \"1200\",\n" +
//                    "    \"discount\": \"5\",\n" +
//                    "    \"salesTax\": \"5\",\n" +
//                    "    \"incomeTax\": \"10\",\n" +
//                    "    \"isSubmit\": false}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/finance/receiptvoucher.json");


            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/Receipt");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        //---------- This Receipt Voucher API is not Working -------------//
        @Test
        //@Order(22)
        //(15)
        public void ZZZVizalysSAAS_Get_ReceiptVoucher()
        {
            System.out.println("----------***** VizalysSAAS_Get_ReceiptVoucher *****-----------");


            String APIBody = "{}";

            //Getting API Post Body from the External File
            //File APIBody = new File(System.getProperty("user.dir") + "/allapis/finance/receiptvoucher.json");


            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/Receipt");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        //---------- This Journal Entry API is not Working -------------//
        @Test
        //@Order(24)
        //(16)
        public void ZZZVizalysSAAS_Post_JournalEntry()
        {
            System.out.println("----------***** VizalysSAAS_Post_JournalEntry *****-----------");


//            String APIBody = "{\"date\": \"2022-06-24\",\n" +
//                    "    \"description\": \"ggg\",\n" +
//                    "    \"journalEntryLines\": [\n" +
//                    "        {\n" +
//                    "            \"accountId\": \"31210000-5566-7788-99AA-BBCCDDEEFF00+4\",\n" +
//                    "            \"businessPartnerId\": 2,\n" +
//                    "            \"description\": \"eee\",\n" +
//                    "            \"credit\": \"250\",\n" +
//                    "            \"locationId\": 4\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"accountId\": \"31210000-5566-7788-99AA-BBCCDDEEFF00+4\",\n" +
//                    "            \"businessPartnerId\": 2,\n" +
//                    "            \"description\": \"www\",\n" +
//                    "            \"debit\": \"250\",\n" +
//                    "            \"locationId\": 4\n" +
//                    "        }\n" +
//                    "    ],\n" +
//                    "    \"isSubmit\": false}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/finance/journalentry.json");

            //Headers
//            headerlist.add(new Header(authorizationName, authorizationToken));

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/JournalEntry");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        //---------- This Journal Entry API is not Working -------------//
        @Test
        //@Order(25)
        //(16)
        public void VizalysSAAS_Get_JournalEntry()
        {
            System.out.println("----------***** VizalysSAAS_Get_JournalEntry *****-----------");


            String APIBody = "{}";

            //Headers
            headerlist.add(new Header(authorizationName, authorizationToken));

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/JournalEntry");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }


        @Test
        //@Order(26)
        //(17)
        public void VizalysSAAS_Post_Invoice()
        {
            System.out.println("----------***** VizalysSAAS_Post_Invoice *****-----------");


//            String APIBody = "{\"customerId\": 2,\n" +
//                    "    \"invoiceDate\": \"2022-06-27\",\n" +
//                    "    \"dueDate\": \"2022-06-28\",\n" +
//                    "    \"contact\": \"0345796456\",\n" +
//                    "    \"invoiceLines\": [\n" +
//                    "        {\n" +
//                    "            \"itemId\": 2,\n" +
//                    "            \"description\": \"Invoice Against Qiyam Gah\",\n" +
//                    "            \"price\": 4500,\n" +
//                    "            \"quantity\": \"2\",\n" +
//                    "            \"tax\": 5,\n" +
//                    "            \"accountId\": \"31210000-5566-7788-99AA-BBCCDDEEFF00+4\",\n" +
//                    "            \"locationId\": null\n" +
//                    "        }\n" +
//                    "    ],\n" +
//                    "    \"isSubmit\": false}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/sales/invoice.json");

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/Invoice");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }


        @Test
        //@Order(27)
        //(17)
        public void ZZZ_VizalysSAAS_Get_Invoice()
        {
            System.out.println("----------***** VizalysSAAS_Get_Invoice *****-----------");


            String APIBody = "{}";

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/Invoice");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        //@Order(27)
        //(17)
        public void ZZZ_VizalysSAAS_Get_Invoice_AgingReport()
        {
            System.out.println("----------***** VizalysSAAS_Get_Invoice_AgingReport *****-----------");


            String APIBody = "{}";


            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/Invoice/getAgingReport");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }


        @Test
        //@Order(28)
        //(18)
        public void VizalysSAAS_Post_CreditNote()
        {
            System.out.println("----------***** VizalysSAAS_Post_CreditNote *****-----------");


//            String APIBody = "{\"customerId\": 2,\n" +
//                    "    \"noteDate\": \"2022-06-27\",\n" +
//                    "    \"documentLedgerId\": null,\n" +
//                    "    \"creditNoteLines\": [\n" +
//                    "        {\n" +
//                    "            \"itemId\": 2,\n" +
//                    "            \"description\": \"www\",\n" +
//                    "            \"price\": 4500,\n" +
//                    "            \"quantity\": \"2\",\n" +
//                    "            \"tax\": 5,\n" +
//                    "            \"accountId\": \"3d810fef-0169-405d-8a97-05d2a8a906c4+4\",\n" +
//                    "            \"locationId\": 4\n" +
//                    "        }\n" +
//                    "    ],\n" +
//                    "    \"isSubmit\": false}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/sales/creditnote.json");

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/CreditNote");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }


        @Test
        //@Order(29)
        //(18)
        public void VizalysSAAS_Get_CreditNote()
        {
            System.out.println("----------***** VizalysSAAS_Get_CreditNote *****-----------");


            String APIBody = "{}";

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/CreditNote");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }


        @Test
        //@Order(30)
        //(19)
        public void ZZZVizalysSAAS_Post_VendorBill()
        {
            System.out.println("----------***** VizalysSAAS_Post_VendorBill *****-----------");


//            String APIBody = "{    \"vendorId\": 2,\n" +
//                    "    \"billDate\": \"2022-06-27\",\n" +
//                    "    \"dueDate\": \"2022-06-28\",\n" +
//                    "    \"contact\": \"035647895\",\n" +
//                    "    \"billLines\": [\n" +
//                    "        {\n" +
//                    "            \"itemId\": 2,\n" +
//                    "            \"description\": \"ccc\",\n" +
//                    "            \"cost\": 3500,\n" +
//                    "            \"quantity\": \"2\",\n" +
//                    "            \"tax\": 5,\n" +
//                    "            \"accountId\": \"31210000-5566-7788-99AA-BBCCDDEEFF00+4\",\n" +
//                    "            \"locationId\": 4\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"itemId\": 2,\n" +
//                    "            \"description\": \"zzz\",\n" +
//                    "            \"cost\": 3500,\n" +
//                    "            \"quantity\": \"2\",\n" +
//                    "            \"tax\": 5,\n" +
//                    "            \"accountId\": \"31210000-5566-7788-99AA-BBCCDDEEFF00+4\",\n" +
//                    "            \"locationId\": 4\n" +
//                    "        }\n" +
//                    "    ],\n" +
//                    "    \"isSubmit\": false}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/purchase/vendorbill.json");

            //Headers
//            headerlist.add(new Header(authorizationName, authorizationToken));

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/Bill");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }


        @Test
        //@Order(31)
        //(19)
        public void VizalysSAAS_Get_VendorBill()
        {
            System.out.println("----------***** VizalysSAAS_Get_VendorBill *****-----------");


            String APIBody = "{}";

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/Bill");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        //@Order(31)
        //(19)
        public void VizalysSAAS_Get_VendorBill_AgingReport()
        {
            System.out.println("----------***** VizalysSAAS_Get_VendorBill_AgingReport *****-----------");


            String APIBody = "{}";

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/Bill/getAgingReport");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }


        @Test
        //@Order(32)
        //(20)
        public void VizalysSAAS_Post_DebitNote()
        {
            System.out.println("----------***** VizalysSAAS_Post_DebitNote *****-----------");


//            String APIBody = "{ \"vendorId\": 2,\n" +
//                    "    \"noteDate\": \"2022-06-27\",\n" +
//                    "    \"debitNoteLines\": [\n" +
//                    "        {\n" +
//                    "            \"itemId\": 2,\n" +
//                    "            \"description\": \"aaa\",\n" +
//                    "            \"cost\": \"1500\",\n" +
//                    "            \"quantity\": \"2\",\n" +
//                    "            \"tax\": 5,\n" +
//                    "            \"accountId\": \"31210000-5566-7788-99AA-BBCCDDEEFF00+4\",\n" +
//                    "            \"locationId\": 4\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"itemId\": 2,\n" +
//                    "            \"description\": \"bbb\",\n" +
//                    "            \"cost\": \"3200\",\n" +
//                    "            \"quantity\": \"2\",\n" +
//                    "            \"tax\": 5,\n" +
//                    "            \"accountId\": \"31210000-5566-7788-99AA-BBCCDDEEFF00+4\",\n" +
//                    "            \"locationId\": 4\n" +
//                    "        }\n" +
//                    "    ],\n" +
//                    "    \"isSubmit\": false}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/purchase/debitnote.json");


            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/DebitNote");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        //---------- This Debit Note API is not Working Due to Workflow -------------//
        @Test
        @Order(33)
        //(20)
        public void VizalysSAAS_Get_DebitNote()
        {
            System.out.println("----------***** VizalysSAAS_Get_DebitNote *****-----------");


            String APIBody = "{}";


            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/DebitNote");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        //---------- This Bank Statement API is not Working -------------//
        @Test
        //@Order(32)
        //(21)
        public void ZZZVizalysSAAS_Post_BankStatement()
        {
            System.out.println("----------***** VizalysSAAS_Post_BankStatement *****-----------");


//            String APIBody = "{\"bankAccountId\": 2,\n" +
//                    "    \"description\": \"rrr\",\n" +
//                    "    \"openingBalance\": 250,\n" +
//                    "    \"bankStmtLines\": [\n" +
//                    "        {\n" +
//                    "            \"id\": 0,\n" +
//                    "            \"reference\": 1,\n" +
//                    "            \"stmtDate\": \"2022-08-04\",\n" +
//                    "            \"label\": \"Label\",\n" +
//                    "            \"debit\": 540,\n" +
//                    "            \"credit\": 0\n" +
//                    "        }\n" +
//                    "    ]}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/finance/bankstatement.json");

            //Headers
//            headerlist.add(new Header(authorizationName, authorizationToken));

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/BankStmt");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        //@Order(32)
        //(21)
        public void VizalysSAAS_Get_BankStatement()
        {
            System.out.println("----------***** VizalysSAAS_Get_BankStatement *****-----------");


            String APIBody = "{}";

            //Getting API Post Body from the External File
            //File APIBody = new File(System.getProperty("user.dir") + "/allapis/finance/bankstatement.json");


            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/BankStmt");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        //@Order(32)
        //(22)
        public void VizalysSAAS_Post_BankReconciliation()
        {
            System.out.println("----------***** VizalysSAAS_Post_BankReconciliation *****-----------");


//            String APIBody = "{[\n" +
//                    "  {\n" +
//                    "    \"amount\": 17952032.288707588,\n" +
//                    "    \"bankStmtId\": -4830799,\n" +
//                    "    \"paymentId\": -16604375\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"amount\": 19336861.3184269,\n" +
//                    "    \"bankStmtId\": -7121773,\n" +
//                    "    \"paymentId\": -19538912\n" +
//                    "  }\n" +
//                    "]}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/finance/bankreconciliation.json");


            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/BankRecon");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        @Order(32)
        //(23)
        public void ZZZVizalysSAAS_Get_COA()
        {
            System.out.println("----------***** VizalysSAAS_Get_COA *****-----------");


            String APIBody = "{}";

            //Getting API Post Body from the External File
            //File APIBody = new File(System.getProperty("user.dir") + "/allapis/finance/bankstatement.json");

            //Headers
//            headerlist.add(new Header(authorizationName, authorizationToken));

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/COA");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        //@Order(32)
        //(24)
        public void VizalysSAAS_Post_GeneralLedger()
        {
            System.out.println("----------***** VizalysSAAS_Post_GeneralLedger *****-----------");


//            String APIBody = "{\"docDate\": \"2022-01-01\",\n" +
//                    "    \"docDate2\": \"2022-08-04\",\n" +
//                    "    \"accountName\": \"\",\n" +
//                    "    \"businessPartnerName\": \"\",\n" +
//                    "    \"locationName\": \"\",\n" +
//                    "    \"departmentName\": \"\",\n" +
//                    "    \"warehouseName\": \"\"}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/report/generalledger.json");

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/GeneralLedger");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        //@Order(32)
        //(25)
        public void VizalysSAAS_Post_BalanceSheet()
        {
            System.out.println("----------***** VizalysSAAS_Post_BalanceSheet *****-----------");


//            String APIBody = "{\"docDate\": \"2022-08-04\",\n" +
//                    "    \"businessPartner\": null,\n" +
//                    "    \"department\": null,\n" +
//                    "    \"warehouse\": null,\n" +
//                    "    \"location\": null,\n" +
//                    "    \"organization\": null,\n" +
//                    "    \"transactional\": null}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/report/balancesheet.json");

            //Headers
//            headerlist.add(new Header(authorizationName, authorizationToken));

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/BalanceSheet");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        @Order(32)
        //(26)
        public void VizalysSAAS_Post_ProfitAndLoss()
        {
            System.out.println("----------***** VizalysSAAS_Post_ProfitAndLoss *****-----------");


//            String APIBody = "{\n" +
//                    "    \"docDate\": \"2022-01-01\",\n" +
//                    "    \"docDate2\": \"2022-08-04\",\n" +
//                    "    \"transactional\": \"\",\n" +
//                    "    \"businessPartner\": \"\",\n" +
//                    "    \"warehouse\": \"\",\n" +
//                    "    \"department\": \"\",\n" +
//                    "    \"location\": \"\",\n" +
//                    "    \"organization\": \"\"\n" +
//                    "}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/report/profitandloss.json");


            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/PNL");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        @Order(32)
        //(27)
        public void ZZZVizalysSAAS_Post_TrailBalance()
        {
            System.out.println("----------***** VizalysSAAS_Post_TrailBalance *****-----------");


//            String APIBody = "{ \"docDate\": \"2022-01-01\",\n" +
//                    "    \"docDate2\": \"2022-08-04\",\n" +
//                    "    \"accountName\": null,\n" +
//                    "    \"organization\": null,\n" +
//                    "    \"warehouse\": null,\n" +
//                    "    \"department\": null,\n" +
//                    "    \"location\": null}";

            //Getting API Post Body from the External File
            File APIBody = new File(System.getProperty("user.dir") + "/allapis/report/trialbalance.json");

            //Headers
//            headerlist.add(new Header(authorizationName, authorizationToken));

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    post("/api/TrialBalance");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        //---------- Dashboard APIs --------------//
        @Test
        @Order(32)
        //(28)
        public void ZZZVizalysSAAS_Get_Dashboard_GetUnpaidInvoicesAndReceivables()
        {
            System.out.println("----------***** VizalysSAAS_Get_Dashboard_GetUnpaidInvoicesAndReceivables *****-----------");


            String APIBody = "{}";

            //Getting API Post Body from the External File
            //File APIBody = new File(System.getProperty("user.dir") + "/allapis/report/trialbalance.json");


            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/Dashboard/GetUnpaidInvoicesAndReceivables");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        @Order(32)
        //(28)
        public void ZZZVizalysSAAS_Get_Dashboard_GetUnpaidBillsAndPayables()
        {
            System.out.println("----------***** VizalysSAAS_Get_Dashboard_GetUnpaidBillsAndPayables *****-----------");


            String APIBody = "{}";

            //Getting API Post Body from the External File
            //File APIBody = new File(System.getProperty("user.dir") + "/allapis/report/trialbalance.json");

            //Headers
            headerlist.add(new Header(authorizationName, authorizationToken));

            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/Dashboard/GetUnpaidBillsAndPayables");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

        @Test
        @Order(32)
        //(28)
        public void VizalysSAAS_Get_Dashboard_GetBankAccountsSummary()
        {
            System.out.println("----------***** VizalysSAAS_Get_Dashboard_GetBankAccountsSummary *****-----------");


            String APIBody = "{}";

            //Getting API Post Body from the External File
            //File APIBody = new File(System.getProperty("user.dir") + "/allapis/report/trialbalance.json");


            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/Dashboard/GetBankAccountsSummary");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }
        @Order(32)
        @Test

        //(28)
        public void ZZZ_VizalysSAAS_Get_Dashboard_GetExpenseAndIncomeSummary()
        {
            System.out.println("----------***** VizalysSAAS_Get_Dashboard_GetExpenseAndIncomeSummary *****-----------");


            String APIBody = "{}";

            //Getting API Post Body from the External File
            //File APIBody = new File(System.getProperty("user.dir") + "/allapis/report/trialbalance.json");


            Response r = given().body(APIBody).
                    headers(headers.allHeaders(headerlist)).
                    when().
                    get("/api/Dashboard/GetExpenseAndIncomeSummary");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            // Assert that correct status code is returned.
            assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }
    }
}
