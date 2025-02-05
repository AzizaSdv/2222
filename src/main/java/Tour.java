package com.example.tourmanagement;

// Tour class
class Tour {
    private int id;
    private String name;
    private String location;
    private double price;
    private String dates;

    public Tour(String name, String location, double price, String dates) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.dates = dates;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public double getPrice() { return price; }
    public String getDates() { return dates; }

    @Override
    public String toString() {
        return "Tour: " + name + " in " + location + " for $" + price + " on " + dates;
    }
}
