package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;

public class Task3 {

    public static String Table() {
        String table = "|№\t|Дата/время\t|Температура\t|Осадки (мм)\t|\n";
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms");
            WebElement webElement = webDriver.findElement(By.tagName("pre"));

            String json_str = webElement.getText();
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(json_str);
            JSONObject hourly = (JSONObject) obj.get("hourly");

            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray rains = (JSONArray) hourly.get("rain");
            JSONArray temperaturs = (JSONArray) hourly.get("temperature_2m");


            for (int i = 0; i < times.size(); ++i) {
                String time = times.get(i).toString();
                String rain = rains.get(i).toString();
                String temperatura = temperaturs.get(i).toString();
                table += "| " + (i + 1) + "\t|" + time + "\t|" + rain + "\t|" + temperatura + "\t|\n";
            }



        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }
        return table;
    }
}
