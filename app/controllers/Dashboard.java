package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");
    List<Station> stations = Station.findAll();
    render ("dashboard.html",stations);
  }

  public static void indexStation(Long id) {
    Logger.info("Rendering Station Dashboard");
    Station station = Station.findById(id);
    render ("stationdashboard.html",station);
  }

  public static void addStation(String name){
    Station station = new Station(name);
    station.save();
    Logger.info("Adding Station" + name);
    redirect("/dashboard");
  }

  public static void addReading(String name, int code, float temperature, float windSpeed, int pressure, float windDirection){
    Reading reading = new Reading(code,temperature,windSpeed,pressure,windDirection);
    Station station = Station.findByName(name);
    station.readings.add(reading);
    station.save();
    Logger.info("Adding Reading");
    redirect("/dashboard");
  }


}
