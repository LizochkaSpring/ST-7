package org.example;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task2 {


    public static String Ip() {
        String ip = null;
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://api.ipify.org/?format=json");
            WebElement webElement = webDriver.findElement(By.tagName("pre"));

            String json_str = webElement.getText();
            JSONParser parser = new JSONParser();

            ip = (String)((JSONObject)parser.parse(json_str)).get("ip");

        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }
        return ip;
    }

}
