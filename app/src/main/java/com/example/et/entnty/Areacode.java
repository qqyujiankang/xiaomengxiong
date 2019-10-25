package com.example.et.entnty;

public class Areacode {

        /**
         * country : 中国
         * mobile_prefix : 86
         * area : 亚洲
         */

        private String country;
        private String mobile_prefix;
        private String area;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getMobile_prefix() {
            return mobile_prefix;
        }

        public void setMobile_prefix(String mobile_prefix) {
            this.mobile_prefix = mobile_prefix;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

    @Override
    public String toString() {
        return "Areacode{" +
                "country='" + country + '\'' +
                ", mobile_prefix='" + mobile_prefix + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
