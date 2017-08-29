package com.saic.baiduremote;

import java.util.ArrayList;

public class BaiduDataModel {

    private Content content = new Content();

    public class Content {
        public String address;

        public class Address_detail {
            private int    adcode;
            private String city;
            private int    city_code;
            private String country;
            private String district;

            public int getAdcode() {
                return adcode;
            }

            public void setAdcode(int adcode) {
                this.adcode = adcode;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getCity_code() {
                return city_code;
            }

            public void setCity_code(int city_code) {
                this.city_code = city_code;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

        }

        private Address_detail address_detail = new Address_detail();

        private String         business;
        private String         poi_desc;

        private ArrayList      poi_region;

        public class Point {

            private double x;
            private double y;

        }

        private Point    point = new Point();

        public ArrayList surround_poi;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Address_detail getAddress_detail() {
            return address_detail;
        }

        public void setAddress_detail(Address_detail address_detail) {
            this.address_detail = address_detail;
        }

        public String getBusiness() {
            return business;
        }

        public void setBusiness(String business) {
            this.business = business;
        }

        public String getPoi_desc() {
            return poi_desc;
        }

        public void setPoi_desc(String poi_desc) {
            this.poi_desc = poi_desc;
        }

        public ArrayList getPoi_region() {
            return poi_region;
        }

        public void setPoi_region(ArrayList poi_region) {
            this.poi_region = poi_region;
        }

        public Point getPoint() {
            return point;
        }

        public void setPoint(Point point) {
            this.point = point;
        }

        public ArrayList getSurround_poi() {
            return surround_poi;
        }

        public void setSurround_poi(ArrayList surround_poi) {
            this.surround_poi = surround_poi;
        }

    }

    public class Result {
        private int    error;
        private int    type;
        private String qt;
        private double x;
        private double y;

        public int getError() {
            return error;
        }

        public void setError(int error) {
            this.error = error;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getQt() {
            return qt;
        }

        public void setQt(String qt) {
            this.qt = qt;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

    }

    private Result result = new Result();

    public class Current_city {
        private int sup_bus;
    }

    private Current_city current_city = new Current_city();
    private ArrayList    hot_city;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Current_city getCurrent_city() {
        return current_city;
    }

    public void setCurrent_city(Current_city current_city) {
        this.current_city = current_city;
    }

    public ArrayList getHot_city() {
        return hot_city;
    }

    public void setHot_city(ArrayList hot_city) {
        this.hot_city = hot_city;
    }

}
