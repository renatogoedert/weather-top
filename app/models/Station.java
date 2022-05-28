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

  public int temperatureTrend(List<Reading> readings){
    if(readings.size() >=3) {
      Reading r1 = readings.get(readings.size() - 1);
      Reading r2 = readings.get(readings.size() - 2);
      Reading r3 = readings.get(readings.size() - 3);

      float t1 = r1.getTemperature();
      float t2 = r2.getTemperature();
      float t3 = r3.getTemperature();

      if(t1<t2 && t2<t3){
        return 1;
      }
      if(t1>t2 && t2>t3){
        return 2;
      }
    }
    return 0;
  }

  public int pressureTrend(List<Reading> readings){
    if(readings.size() >=3) {
      Reading r1 = readings.get(readings.size() - 1);
      Reading r2 = readings.get(readings.size() - 2);
      Reading r3 = readings.get(readings.size() - 3);

      int p1 = r1.getPressure();
      int p2 = r2.getPressure();
      int p3 = r3.getPressure();

      if(p1<p2 && p2<p3){
        return 1;
      }
      if(p1>p2 && p2>p3){
        return 2;
      }
    }
    return 0;
  }

  public int windTrend(List<Reading> readings){
    if(readings.size() >=3) {
      Reading r1 = readings.get(readings.size() - 1);
      Reading r2 = readings.get(readings.size() - 2);
      Reading r3 = readings.get(readings.size() - 3);

      float w1 = r1.getWindSpeed();
      float w2 = r2.getWindSpeed();
      float w3 = r3.getWindSpeed();

      if(w1<w2 && w2<w3){
        return 1;
      }
      if(w1>w2 && w2>w3){
        return 2;
      }
    }
    return 0;
  }


}
