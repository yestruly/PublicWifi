package Dto;

public class WifiDto {
    private double distance;    //거리
    private String wifi_number; //관리번호
    private String borough;     //자치구
    private String wifiName;    //와이파이명
    private String street;      //도로명주소
    private String detailAddress; //상세주소
    private String installFloar;//설치위치(층)
    private String installType; //설치유형
    private String installAgancy;//설치기관
    private String service;      //서비스 구분
    private String net;          //망종류
    private int year;           //설치년도
    private String inOut;         //실내외 구분
    private String connect;     //와이파이 접속 환경
    private double lat;         //x좌표
    private double lnt;         //y좌표
    private String workingDate;  //작업일자
    public WifiDto(double distance, String wifi_number, String borough, String wifiName, String street, String detailAddress, String installFloar, String installType, String installAgancy, String service, String net, int year, String inOut, String connect, double lnt, double lat, String workingDate) {
        this.distance = distance;
        this.wifi_number = wifi_number;
        this.borough = borough;
        this.wifiName = wifiName;
        this.street = street;
        this.detailAddress = detailAddress;
        this.installFloar = installFloar;
        this.installType = installType;
        this.installAgancy = installAgancy;
        this.service = service;
        this.net = net;
        this.year = year;
        this.inOut = inOut;
        this.connect = connect;
        this.lat = lat;
        this.lnt = lnt;
        this.workingDate = workingDate;
    }
    public WifiDto(){}
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getWifi_number() {
        return wifi_number;
    }

    public void setWifi_number(String wifi_number) {
        this.wifi_number = wifi_number;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getInstallFloar() {
        return installFloar;
    }

    public void setInstallFloar(String installFloar) {
        this.installFloar = installFloar;
    }

    public String getInstallType() {
        return installType;
    }

    public void setInstallType(String installType) {
        this.installType = installType;
    }

    public String getInstallAgancy() {
        return installAgancy;
    }

    public void setInstallAgancy(String installAgancy) {
        this.installAgancy = installAgancy;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getInOut() {
        return inOut;
    }

    public void setInOut(String inOut) {
        this.inOut = inOut;
    }

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }

    public double getLnt() {
        return lnt;
    }

    public void setLnt(double lnt) {
        this.lnt = lnt;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(String workingDate) {
        this.workingDate = workingDate;
    }

}
