package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void scrollToElement(WebElement element) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        Thread.sleep(500);
    }

    public void sleep(Integer second) throws InterruptedException {
        Thread.sleep(1000*second);
    }

    public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='2px solid red'", element);
        try {
            Thread.sleep(1000); // Highlight for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript("arguments[0].style.border=''", element); // Remove the border
    }

//    public static void loadPropFile() throws IOException {
//        prop = new Properties();
//        FileInputStream fs = new FileInputStream("/home/yash/IdeaProjects/TMP/src/test/java/config/config.properties");
//        prop.load(fs);
//    }

//    public void addCookie(WebDriver driver, String authToken) throws IOException {
//        loadPropFile();
//        Cookie cookie= driver.manage().getCookieNamed("admin-auth-token");
//        driver.manage().deleteCookie(cookie);
//        System.out.println(cookie.getName());
//
//        System.out.println(cookie.getExpiry());
//        System.out.println(cookie.getPath());
//
//        System.out.println(prop.getProperty("supplier-auth-token"));
//
//        driver.manage().addCookie(
//                new Cookie.Builder(cookie.getName(), authToken)
//                        .domain(cookie.getDomain())
//                        .expiresOn(cookie.getExpiry())
//                        .path(cookie.getPath())
//                        .isSecure(cookie.isSecure())
//                        .build()
//        );
//        driver.navigate().refresh();
//    }
//
//    public static String getPoNumber() {
//        return poNumber;
//    }
//
//    public static int generateInvoiceNumber()
//    {
//        Random random = new Random();
//
//        // Generate a random number between 100000 (inclusive) and 1000000 (exclusive)
//        int randomNumber = 100000 + random.nextInt(900000);
//        return randomNumber;
//    }
//
//    public static String getDataFromClipBoard()
//    {
//        // Get the system clipboard
//        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//
//        // Get the clipboard contents
//        Transferable contents = clipboard.getContents(null);
//
//        // Check if clipboard has text data
//        if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
//            try {
//                // Retrieve and print the text data from the clipboard
//                String clipboardText = (String) contents.getTransferData(DataFlavor.stringFlavor);
//                return clipboardText;
//            } catch (UnsupportedFlavorException | IOException e) {
//                System.err.println("Error retrieving clipboard data: " + e.getMessage());
//            }
//        } else {
//            System.out.println("No text data in clipboard.");
//        }
//        return null;
//    }
//
//    public static int getNumericValue(String str) {
//        // Remove non-digit characters
//        String numericString = str.replaceAll("[^\\d]", "");
//        // Convert the string to an integer
//        return Integer.parseInt(numericString);
//    }
//
//    public static String buildPan() {
//
//        StringBuilder panNumbersText = new StringBuilder();
//        String possibleAlphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        String randomChars = "ABCFGHLJPT";
//        Random random = new Random();
//
//        for (int i = 0; i < 5; i++) {
//            if (i == 3) {
//                panNumbersText.append(randomChars.charAt(random.nextInt(randomChars.length())));
//                continue;
//            }
//            panNumbersText.append(possibleAlphabets.charAt(random.nextInt(possibleAlphabets.length())));
//        }
//        panNumbersText.append(String.format("%04d", random.nextInt(10000)));
//
//        for (int j = 0; j < 1; j++) {
//            panNumbersText.append(possibleAlphabets.charAt(random.nextInt(possibleAlphabets.length())));
//        }
//        return panNumbersText.toString();
//    }
//
//    public void cachePanInRedis() throws InterruptedException {
//
//        panNumber = buildPan();
//
//        String url = String.format("%s/api/v1/internal/pan/details/add?key=%s", prop.getProperty("uat-be-url"), prop.getProperty("uat-redis-key"));
//        String jsonBody = String.format("{\"%s\": \"{\\\"vendor\\\": \\\"KARZA\\\",\\\"verified\\\":true,\\\"errorMessage\\\":null,\\\"details\\\":{\\\"title\\\":\\\"M/s\\\",\\\"firstName\\\":\\\"\\\",\\\"middleName\\\":\\\"\\\",\\\"lastName\\\":\\\"%s\\\",\\\"lastUpdated\\\":\\\"28/01/2024\\\"}}\"}", panNumber, organisationName);
//
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
//                .build();
//
//        try {
//            System.out.println("PAN Number is "+ panNumber);
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            int statusCode = response.statusCode();
//
//            if (statusCode == 200) {
//                System.out.println("API call successful. Status code: 200");
//            } else {
//                System.out.println("API call failed. Status code: " + statusCode);
//            }
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static Long generateAccountNumber()
//    {
//        Random random = new Random();
//        long tenDigitNumber = 1000000000L + (long)(random.nextDouble() * 8999999999L);
//        return tenDigitNumber;
//    }
//
//    public String getOtp() throws IOException {
//        //loadPropFile();
//        String url = String.format("%s/api/v1/internal/otp?mobile=%s&key=%s", prop.getProperty("uat-be-url"), prop.getProperty("phone"),prop.getProperty("uat-redis-key"));
//
//        System.out.println(url);
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .header("Content-Type", "application/json")
//                .GET()
//                .build();
//
//        try {
//
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.body());
//
//            int statusCode = response.statusCode();
//
//            if (statusCode == 200) {
//                JSONObject jsonObject = new JSONObject(response.body());
//
//                // Get the value of "data" field
//                Object dataValue = jsonObject.get("data");
//                System.out.println("Current otp value: "+ dataValue);
//                return dataValue.toString();
//                //System.out.println("API call successful. Status code: 200");
//            } else {
//                System.out.println("API call failed. Status code: " + statusCode);
//            }
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}