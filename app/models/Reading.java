package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Reading extends Model{

    public int code;
    public float temperature;
    public float windSpeed;

    public Reading(int weatherCode, float temp, float windSpeed) {
        this.code = weatherCode;
        this.temperature = temp;
        this.windSpeed = windSpeed;
    }
}
