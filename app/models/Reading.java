package models;

import controllers.Calculator;
import play.db.jpa.Model;

import javax.persistence.Entity;
import java.security.PublicKey;

@Entity
public class Reading extends Model{

    public int code;
    public float temperature;
    public float windSpeed;
    public int pressure;

    public Reading(int code, float temperature, float windSpeed, int pressure) {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
    }

    public static String codeToWeather(int code) {
        if (code == 100) {
            return "Clear";
        }else if (code == 200) {
            return "Partial clouds";
        }else if (code == 300) {
            return "Cloudy";
        }else if (code == 400) {
            return "Light Showers";
        }else if (code == 500) {
            return "Heavy Showers";
        }else if (code == 600) {
            return "Rain";
        }else if (code == 700) {
            return "Snow";
        }else {
            return "Thunder";
        }
    }

    public float ceToFa(float temp){
        return ((temp*9/5)+32);
    }

    public static float windInBf(float windSpeed){
        if (windSpeed == 1)
            return 0;
        if ((windSpeed > 1) && (windSpeed <= 5))
            return 1;
        if ((windSpeed > 5) && (windSpeed <= 11))
            return 2;
        if ((windSpeed > 11) && (windSpeed <= 19))
            return 3;
        if ((windSpeed > 19) && (windSpeed <= 28))
            return 4;
        if ((windSpeed > 28) && (windSpeed <= 38))
            return 5;
        if ((windSpeed > 38) && (windSpeed <= 49))
            return 6;
        if ((windSpeed > 49) && (windSpeed <= 61))
            return 7;
        if ((windSpeed > 61) && (windSpeed <= 74))
            return 8;
        if ((windSpeed > 74) && (windSpeed <= 88))
            return 9;
        if ((windSpeed > 88) && (windSpeed <= 102))
            return 10;
        if ((windSpeed > 103) && (windSpeed <= 117))
            return 11;
        else
            return 12;
    }

}
