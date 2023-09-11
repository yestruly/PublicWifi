package Dto;

import java.util.Date;

public class HistoryDto {
    private int ID;
    private double lat;
    private double lnt;
    private Date date;


    public HistoryDto(int ID, float lat, float lnt, Date date) {
        this.ID = ID;
        this.lat = lat;
        this.lnt = lnt;
        this.date = date;
    }

    public HistoryDto(){}

    public int getID() {
        return ID;
    }

    public double getLat() {
        return lat;
    }

    public double getLnt() {
        return lnt;
    }

    public Date getDate() {
        return date;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLnt(double lnt) {
        this.lnt = lnt;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
