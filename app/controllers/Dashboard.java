package controllers;

import models.Member;
import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;
import java.util.*;

import java.util.List;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
    render ("dashboard.html",stations);
  }

  public static void indexStation(Long id) {
    Logger.info("Rendering Station Dashboard");
    Station station = Station.findById(id);
    render ("stationdashboard.html",station);
  }

  public static void addStation(String name, float latitude,float longitude){
    Member member = Accounts.getLoggedInMember();
    if(name.length()>0) {
      Station station = new Station(name,latitude,longitude);
      member.stations.add(station);
      member.save();
      Logger.info("Adding Station" + name);
    } else{
      Logger.info("Station need a name");
    }
    redirect("/dashboard");
  }

  public static void deleteStation (Long id)
  {
    Logger.info("Deleting a Station");
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    member.stations.remove(station);
    member.save();
    station.delete();
    redirect ("/dashboard");
  }

  public static void addReading(String name, int code, float temperature, float windSpeed, int pressure, float windDirection){
    Date date = new Date();
    Reading reading = new Reading(code,temperature,windSpeed,pressure,windDirection,date);
    Station station = Station.findByName(name);
    station.readings.add(reading);
    station.save();
    Logger.info("Adding Reading");
    indexStation(station.id);
  }

  public static void deleteReading (Long id, Long readingid)
  {
    Logger.info("Deleting Reading");
    Station station = Station.findById(id);
    Reading reading = Reading.findById(readingid);
    station.readings.remove(reading);
    station.save();
    reading.delete();
    indexStation(station.id);
  }


}
