package utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Properties;
import java.util.Random;

public class Helper {

    public static String poNumber;
    public static String organisationName="Wunsch-Wunsch Test";
    public static String EnquiryID;
    public static String RFQ_ID;
    public static String panNumber;
    public static String organizationID="1123408495543065886";
    public static String ofbEntity_Value;
    public static String currDate;
    public static Properties prop;
    public static String prevToken;
    public static String orderID;
    public static String buyerName;
    public static String childOrgName;
    public static String childOrgAddress;
    public static String childOrgBankAccount;

    public static String changeTokenCookie(WebDriver driver, String newToken) {

        Cookie existingCookie = driver.manage().getCookieNamed("admin-auth-token");

        if (existingCookie != null) {
            // Delete the existing cookie
            driver.manage().deleteCookie(existingCookie);

            // Create a new cookie with the modified value
            Cookie newCookie = new Cookie.Builder("admin-auth-token", newToken)
                    .domain(existingCookie.getDomain())
                    .path(existingCookie.getPath())
                    .isSecure(existingCookie.isSecure())
                    .isHttpOnly(existingCookie.isHttpOnly())
                    .expiresOn(existingCookie.getExpiry())
                    .build();

            // Add the new cookie
            driver.manage().addCookie(newCookie);

            // Optional: Verify the new cookie
            Cookie updatedCookie = driver.manage().getCookieNamed("admin-auth-token");
            System.out.println("Updated token value: " + updatedCookie.getValue());
        }
        return existingCookie.getValue();
    }

    public static void tokenAuthApi(String userId, String developerKey, String apiPath) {
        String apiUrl = apiPath + "/api/v1/internal/testLogin/";
        String url = apiUrl + userId + "?key=" + developerKey;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost postRequest = new HttpPost(url);

            // Execute the request
            HttpResponse response = httpClient.execute(postRequest);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("tokenAuth API Success - StatusCode: " + "200");
            } else {
                System.out.println("Request failed with status code: " + statusCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int generateInvoiceNumber() {
        Random random = new Random();

        // Generate a random number between 100000 (inclusive) and 1000000 (exclusive)
        int randomNumber = 100000 + random.nextInt(900000);
        return randomNumber;
    }

    public static void loadPropFile() throws IOException {
        prop = new Properties();
        FileInputStream fs = new FileInputStream("src/test/java/config/config.properties");
        prop.load(fs);
    }

    public static String getDataFromClipBoard() {
        // Get the system clipboard
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // Get the clipboard contents
        Transferable contents = clipboard.getContents(null);

        // Check if clipboard has text data
        if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                // Retrieve and print the text data from the clipboard
                String clipboardText = (String) contents.getTransferData(DataFlavor.stringFlavor);
                return clipboardText;
            } catch (UnsupportedFlavorException | IOException e) {
                System.err.println("Error retrieving clipboard data: " + e.getMessage());
            }
        } else {
            System.out.println("No text data in clipboard.");
        }
        return null;
    }

    public static int getNumericValue(String str) {
        // Remove non-digit characters
        String numericString = str.replaceAll("[^\\d]", "");
        // Convert the string to an integer
        return Integer.parseInt(numericString);
    }

    public static String buildPan() {

        StringBuilder panNumbersText = new StringBuilder();
        String possibleAlphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String randomChars = "ABCFGHLJPT";
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                panNumbersText.append(randomChars.charAt(random.nextInt(randomChars.length())));
                continue;
            }
            panNumbersText.append(possibleAlphabets.charAt(random.nextInt(possibleAlphabets.length())));
        }
        panNumbersText.append(String.format("%04d", random.nextInt(10000)));

        for (int j = 0; j < 1; j++) {
            panNumbersText.append(possibleAlphabets.charAt(random.nextInt(possibleAlphabets.length())));
        }
        return panNumbersText.toString();
    }

    public static void cachePanInRedis() throws InterruptedException {

        panNumber = buildPan();
        try {
            String url = String.format("%s/api/v1/internal/pan/details/add?key=%s", prop.getProperty("stg6-be-url"), RedisUtil.getKey("stg6"));
            String jsonBody = String.format("{\"%s\": \"{\\\"vendor\\\": \\\"KARZA\\\",\\\"verified\\\":true,\\\"errorMessage\\\":null,\\\"details\\\":{\\\"title\\\":\\\"M/s\\\",\\\"firstName\\\":\\\"\\\",\\\"middleName\\\":\\\"\\\",\\\"lastName\\\":\\\"%s\\\",\\\"lastUpdated\\\":\\\"28/01/2024\\\"}}\"}", panNumber, organisationName);

            System.out.println("**URL** "+url);
            System.out.println("** JsonBody **"+jsonBody);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            try {
                System.out.println("PAN Number is " + panNumber);
                java.net.http.HttpResponse<String> response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
                int statusCode = response.statusCode();

                if (statusCode == 200) {
                    System.out.println("API call successful. Status code: 200");
                } else {
                    System.out.println("API call failed. Status code: " + statusCode);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            System.out.println("Error is "+ e.getMessage());
        }
    }

    public static Long generateAccountNumber() {
        Random random = new Random();
        long tenDigitNumber = 1000000000L + (long) (random.nextDouble() * 8999999999L);
        return tenDigitNumber;
    }

    public static String getOtp() throws IOException {
        //loadPropFile();

        try {
            String url = String.format("%s/api/v1/internal/otp?mobile=%s&key=%s", prop.getProperty("stg6-be-url"), prop.getProperty("phone"), RedisUtil.getKey("stg6"));

            System.out.println(url);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            try {

                java.net.http.HttpResponse<String> response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
                System.out.println(response.body());

                int statusCode = response.statusCode();

                if (statusCode == 200) {
                    JSONObject jsonObject = new JSONObject(response.body());

                    // Get the value of "data" field
                    Object dataValue = jsonObject.get("data");
                    System.out.println("Current otp value: " + dataValue);
                    return dataValue.toString();
                    //System.out.println("API call successful. Status code: 200");
                } else {
                    System.out.println("API call failed. Status code: " + statusCode);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void addCookie(WebDriver driver, String authToken) throws IOException {
        loadPropFile();
        Cookie cookie = driver.manage().getCookieNamed("admin-auth-token");
        driver.manage().deleteCookie(cookie);
        System.out.println(cookie.getName());

        System.out.println(cookie.getExpiry());
        System.out.println(cookie.getPath());

        System.out.println(prop.getProperty("supplier-auth-token"));

        driver.manage().addCookie(
                new Cookie.Builder(cookie.getName(), authToken)
                        .domain(cookie.getDomain())
                        .expiresOn(cookie.getExpiry())
                        .path(cookie.getPath())
                        .isSecure(cookie.isSecure())
                        .build()
        );
        driver.navigate().refresh();
    }


    public static void cacheGST(String panNum, String gst, String orgName) throws IOException {
        loadPropFile();

        String url = String.format("%s/api/v2/informant/pan/%s", prop.getProperty("stg6-be-url"), panNum);
        System.out.println(url);

        String jsonInputString = String.format("{\n" +
                "   \"pan\": \"%s\",\n" +
                "   \"gstDetailList\": {\n" +
                "\"%s\": {\n" +
                "   \"informantGstDetail\": {\n" +
                "       \"error\": false,\n" +
                "       \"data\": {\n" +
                "           \"principalPlaceOfBusinessFields\": {\n" +
                "               \"additionalPlaceOfBusinessAddress\": {\n" +
                "                   \"buildingName\": \"VIPUL AGORA\",\n" +
                "                   \"street\": \"1ST STAGE\",\n" +
                "                   \"location\": \"BASAVESWARATOZANAR\",\n" +
                "                   \"doorNumber\": \"MIG64\",\n" +
                "                   \"stateName\": \"NAGALAND\",\n" +
                "                   \"dst\": \"\",\n" +
                "                   \"city\": \"\",\n" +
                "                   \"floorNumber\": \"\",\n" +
                "                   \"lattitude\": \"\",\n" +
                "                   \"pinCode\": \"122002\",\n" +
                "                   \"longitude\": \"\"\n" +
                "               },\n" +
                "               \"natureOfAdditionalPlaceOfBusiness\": \"Service Provision\"\n" +
                "           },\n" +
                "           \"additionalPlacePOfBusinessFields\": [],\n" +
                "           \"natureOfBusiness\": [\n" +
                "               \"Service Provision\"\n" +
                "           ],\n" +
                "           \"stateJurisdictionCode\": \"\",\n" +
                "           \"taxPayerType\": \"Regular\",\n" +
                "           \"businessName\": \"%s\",\n" +
                "           \"dateOfCancellation\": \"\",\n" +
                "           \"gstin\": \"%s\",\n" +
                "           \"lastUpdateDate\": \"15/01/2020\",\n" +
                "           \"constitutionOfBusiness\": \"Private Limited Company\",\n" +
                "           \"registrationDate\": \"01/07/2017\",\n" +
                "           \"tradeName\": \"%s\",\n" +
                "           \"centerJurisdictionCode\": \"YU0303\",\n" +
                "           \"gstinStatus\": \"Active\",\n" +
                "           \"centerJurisdiction\": \"RANGE-CWD3\"\n" +
                "       }\n" +
                "   },\n" +
                "   \"informantGstReturnDetail\": {\n" +
                "       \"error\": false,\n" +
                "       \"filedList\": {\n" +
                "           \"efiledList\": [\n" +
                "               {\n" +
                "                   \"valid\": \"Y\",\n" +
                "                   \"modeOfFiling\": \"ONLINE\",\n" +
                "                   \"dateOfFiling\": \"19-09-2020\",\n" +
                "                   \"returnType\": \"GSTR3B\",\n" +
                "                   \"taxPeriod\": \"082020\",\n" +
                "                   \"arnNumber\": \"AA2902195869759\",\n" +
                "                   \"status\": \"Filed\"\n" +
                "               },\n" +
                "               {\n" +
                "                   \"valid\": \"Y\",\n" +
                "                   \"modeOfFiling\": \"ONLINE\",\n" +
                "                   \"dateOfFiling\": \"29-08-2020\",\n" +
                "                   \"returnType\": \"GSTR3B\",\n" +
                "                   \"taxPeriod\": \"072020\",\n" +
                "                   \"arnNumber\": \"AA2902195869759\",\n" +
                "                   \"status\": \"Filed\"\n" +
                "               },\n" +
                "               {\n" +
                "                   \"valid\": \"Y\",\n" +
                "                   \"modeOfFiling\": \"ONLINE\",\n" +
                "                   \"dateOfFiling\": \"30-07-2020\",\n" +
                "                   \"returnType\": \"GSTR3B\",\n" +
                "                   \"taxPeriod\": \"062020\",\n" +
                "                   \"arnNumber\": \"AA2902195869759\",\n" +
                "                   \"status\": \"Filed\"\n" +
                "               },\n" +
                "               {\n" +
                "                   \"valid\": \"Y\",\n" +
                "                   \"modeOfFiling\": \"ONLINE\",\n" +
                "                   \"dateOfFiling\": \"10-06-2020\",\n" +
                "                   \"returnType\": \"GSTR3B\",\n" +
                "                   \"taxPeriod\": \"052020\",\n" +
                "                   \"arnNumber\": \"AA2902195869759\",\n" +
                "                   \"status\": \"Filed\"\n" +
                "               },\n" +
                "               {\n" +
                "                   \"valid\": \"Y\",\n" +
                "                   \"modeOfFiling\": \"ONLINE\",\n" +
                "                   \"dateOfFiling\": \"25-05-2020\",\n" +
                "                   \"returnType\": \"GSTR3B\",\n" +
                "                   \"taxPeriod\": \"042020\",\n" +
                "                   \"arnNumber\": \"AA2902195869759\",\n" +
                "                   \"status\": \"Filed\"\n" +
                "               },\n" +
                "               {\n" +
                "                   \"valid\": \"Y\",\n" +
                "                   \"modeOfFiling\": \"ONLINE\",\n" +
                "                   \"dateOfFiling\": \"28-04-2020\",\n" +
                "                   \"returnType\": \"GSTR3B\",\n" +
                "                   \"taxPeriod\": \"032020\",\n" +
                "                   \"arnNumber\": \"AA2902195869759\",\n" +
                "                   \"status\": \"Filed\"\n" +
                "               }\n" +
                "           ]\n" +
                "       }\n" +
                "   }\n" +
                "}\n" +
                "        }\n" +
                "}", panNum, gst, orgName, gst, orgName);

        System.out.println(jsonInputString);

        try {
            URL url1 = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoOutput(true);

            httpURLConnection.setRequestProperty("x-ofb-token", prop.getProperty("my-auth-token"));

            try (OutputStream os = httpURLConnection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpPost post = new HttpPost(url);
                post.setHeader("Content-Type", "application/json");
                post.setHeader("x-ofb-token",prop.getProperty("my-auth-token"));

                StringEntity entity = new StringEntity(jsonInputString);
                post.setEntity(entity);

                try (CloseableHttpResponse response = httpClient.execute(post)) {
                    System.out.println(EntityUtils.toString(response.getEntity()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateSwiftCode() {
        Random random = new Random();

        // Generate two random uppercase letters
        char letter1 = (char) (random.nextInt(26) + 'A');
        char letter2 = (char) (random.nextInt(26) + 'A');

        // Generate two random digits
        int digit1 = random.nextInt(10);
        int digit2 = random.nextInt(10);

        // Combine them into a single string
        return "" + letter1 + letter2 + digit1 + digit2;
    }

    public void sendReportOverEmail()
    {
        String recipient = "recipient@gmail.com";

        String sender = "sender@gmail.com";

        // using host as localhost
        String host = "127.0.0.1";

        // Getting system properties
        Properties properties = System.getProperties();

        // Setting up mail server
        properties.setProperty("mail.smtp.host", host);

        // creating session object to get properties
        Session session = Session.getDefaultInstance(properties);

        try
        {
            // MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From Field: adding senders email to from field.
            message.setFrom(new InternetAddress(sender));

            // Set To Field: adding recipient's email to from field.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set Subject: subject of the email
            message.setSubject("Tests Status Report");


            // set body of the email.
            message.setText("Please find below the report.");

            // Send email.
            Transport.send(message);
            System.out.println("Mail successfully sent");
        }
        catch (MessagingException mex)
        {
            mex.printStackTrace();
        }
    }
}