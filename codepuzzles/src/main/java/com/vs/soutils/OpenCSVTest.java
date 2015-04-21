package com.vs.soutils;

import java.io.*;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;


public class OpenCSVTest {

    //location of csv file
    public static final String csvFile = "/tmp/cars.csv";

    /**
     * Contents of cars.csv - 4 lines
     "CarManufacturer","Model","Color","Owner","MPG","LicensePlate","CountryOfOrigin","VIN"
     Ford,Focus,Red,John Doe,20,ABC-1234,USA,1234-1234-1234-1234
     Honda,Civic,Blue,Jane Learner,12,DEF-2345,USA,2345-2345-2345-2345
     Toyota,Camry,Green,Alice Cooper,15,GHI-3456,UK,3456-3456-3456-3456
     */

    public static void main(String[] args) {
        try {
            readBean(csvFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void readBean(String csvFile)
            throws IOException {

        CSVReader csvReader = new CSVReader(new FileReader(csvFile));
        csvReader.readNext(); //Skip header
        ColumnPositionMappingStrategy<Car> strategy = new ColumnPositionMappingStrategy<Car>();
        strategy.setType(Car.class);
        String[] columns = new String[]{"CarManufacturer", "Model", "Color", "Owner", "MPG", "LicensePlate", "CountryOfOrigin", "VIN"};
        strategy.setColumnMapping(columns);

        CsvToBean<Car> csv = new CsvToBean<Car>();
        List<Car> list = csv.parse(strategy, csvReader);

        System.out.println(list.get(2).getModel());
        System.out.println(list);
    }

    /**
     * This will typically be a top level class not an inner class
     */
    public static class Car {

        private String carManufacturer;
        private String model;
        private String color;
        private String owner;
        private int mpg;
        private String licensePlate;
        private String country;
        private String vin;

        public String getCarManufacturer() {
            return carManufacturer;
        }

        public void setCarManufacturer(String carManufacturer) {
            this.carManufacturer = carManufacturer;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public int getMpg() {
            return mpg;
        }

        public void setMpg(int mpg) {
            this.mpg = mpg;
        }

        public String getLicensePlate() {
            return licensePlate;
        }

        public void setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getVin() {
            return vin;
        }

        public void setVin(String vin) {
            this.vin = vin;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "carManufacturer='" + carManufacturer + '\'' +
                    ", model='" + model + '\'' +
                    ", color='" + color + '\'' +
                    ", owner='" + owner + '\'' +
                    ", mpg=" + mpg +
                    ", licensePlate='" + licensePlate + '\'' +
                    ", country='" + country + '\'' +
                    ", vin='" + vin + '\'' +
                    '}';
        }
    }


}
