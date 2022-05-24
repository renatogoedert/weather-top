package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Station extends Model {

  public String name;
  public float latitude;
  public float longitude;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();

  public Station(String name, float latitude, float longitude) {
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public Reading getLastReading(){
    Reading last = readings.get(readings.size() - 1);
    return last;
  }

  public static Station findByName(String name)
  {
    return find("name", name).first();
  }

  public float getMaxWindSpeed(List<Reading> readings){
    float maxWindSpeed = 0;
    for(Reading reading:readings){
      if(reading.windSpeed>maxWindSpeed) {
        maxWindSpeed = reading.windSpeed;
      }
    }
    return maxWindSpeed;
  }
  public float getMinWindSpeed(List<Reading> readings){
    float minWindSpeed = 10000;
    for(Reading reading:readings){
      if(reading.windSpeed<minWindSpeed) {
        minWindSpeed = reading.windSpeed;
      }
    }
    return minWindSpeed;
  }
  public float getMaxPressure(List<Reading> readings){
    float MaxPressure = 0;
    for(Reading reading:readings){
      if(reading.pressure>MaxPressure) {
        MaxPressure = reading.pressure;
      }
    }
    return MaxPressure;
  }
  public float getMinPressure(List<Reading> readings){
    float MinPressure = 10000;
    for(Reading reading:readings){
      if(reading.pressure<MinPressure) {
        MinPressure = reading.pressure;
      }
    }
    return MinPressure;
  }
}
