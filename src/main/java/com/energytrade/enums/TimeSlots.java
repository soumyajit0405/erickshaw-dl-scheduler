package com.energytrade.enums;
public enum TimeSlots {
	_0000("1"),
	_0015("2"),
	_0030("3"),
	_0045("4"),
	_0100("5"),
	_0115("6"),
	_0130("7"),
	_0145("8"),
	_0200("9"),
	_0215("10"),
	_0230("11"),
	_0245("12"),
	_0300("13"),
	_0315("14"),
	_0330("15"),
	_0345("16"),
	_0400("17"),
	_0415("18"),
	_0430("19"),
	_0445("20"),
	_0500("21"),
	_0515("22"),
	_0530("23"),
	_0545("24"),
	_0600("25"),
	_0615("26"),
	_0630("27"),
	_0645("28"),
	_0700("29"),
	_0715("30"),
	_0730("31"),
	_0745("32"),
	_0800("33"),
	_0815("34"),
	_0830("35"),
	_0845("36"),
	_0900("37"),
	_0915("38"),
	_0930("39"),
	_0945("40"),
	_1000("41"),
	_1015("42"),
	_1030("43"),
	_1045("44"),
	_1100("45"),
	_1115("46"),
	_1130("47"),
	_1145("48"),
	_1200("49"),
	_1215("50"),
	_1230("51"),
	_1245("52"),
	_1300("53"),
	_1315("54"),
	_1330("55"),
	_1345("56"),
	_1400("57"),
	_1415("58"),
	_1430("59"),
	_1445("60"),
	_1500("61"),
	_1515("62"),
	_1530("63"),
	_1545("64"),
	_1600("65"),
	_1615("66"),
	_1630("67"),
	_1645("68"),
	_1700("69"),
	_1715("70"),
	_1730("71"),
	_1745("72"),
	_1800("73"),
	_1815("74"),
	_1830("75"),
	_1845("76"),
	_1900("77"),
	_1915("78"),
	_1930("79"),
	_1945("80"),
	_2000("81"),
	_2015("82"),
	_2030("83"),
	_2045("84"),
	_2100("85"),
	_2115("86"),
	_2130("87"),
	_2145("88"),
	_2200("89"),
	_2215("90"),
	_2230("91"),
	_2245("92"),
	_2300("93"),
	_2315("94"),
	_2330("95"),
	_2345("96");
 
	private String timeSlot; 
	  
    // getter method 
    public String getTimeSlot() 
    { 
        return this.timeSlot; 
    } 
 
    private TimeSlots(String timeSlot) {
        this.timeSlot = timeSlot;
    }
}