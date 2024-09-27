package edu.vt.cs5044;

/**
 * CS 5044 Project 1
 *
 * @author Shannon Smith (shae1223)
 * @version 2022.05.28
 */
public class ShuttleBatteryMonitorTester {

    public static void main(String... args) {
        System.out.println(" ---------------------------------------------------------------------------------------"       );
        System.out.println("                              P1 Tester (Summer 2022)                                   "       );
        System.out.println(" ---------------------------------------------------------------------------------------"       );

        ShuttleBatteryMonitor sbm = new ShuttleBatteryMonitor(9, 8, 12, 4500);
        sbm.travelTo(5);
        sbm.loadPassengers(3);
        sbm.travelTo(9);
        sbm.loadPassengers(1);
        sbm.loadPassengers(1);
        sbm.travelTo(18);

        System.out.println("                           Sample Test Case - Part 1 Results                            "       );
        System.out.println("                   Scenario: sbm(9,8,12,4500)t(5)l(3)t(9)l(1)l(1)t(18)                  "       );
        System.out.println(" ---------------------------------------------------------------------------------------"       );
        System.out.println("                        Loc     Expected: 18;       Actual: " + sbm.getLocation()               );
        System.out.println("                        #Pass   Expected: 5;        Actual: " + sbm.getPassengerCount()         );
        System.out.println("                        %Charge Expected: 88.2;     Actual: " + sbm.getChargeRemaining()        );
        System.out.println("                        U/T     Expected: 176.0;    Actual: " + sbm.getAverageUsagePerTrip()    );
        System.out.println("                        #Trips  Expected: 22;       Actual: " + sbm.getEstimatedTripsRemaining());
        System.out.println(" ---------------------------------------------------------------------------------------"       );


        sbm.loadPassengers(-4);
        sbm.travelTo(-3,5,9);
        sbm.recharge();
        sbm.travelTo(-3);
        sbm.loadPassengers(-1);

        System.out.println("                           Sample Test Case - Part 2 Results                            "       );
        System.out.println("                  Scenario: sbm(9,8,12,4500)l(-4)t(-3,5,9)r()t(-3)l(-1)                 "       );
        System.out.println(" ---------------------------------------------------------------------------------------"       );
        System.out.println("                        Loc     Expected: -3;       Actual: " + sbm.getLocation()               );
        System.out.println("                        #Pass   Expected: 0;        Actual: " + sbm.getPassengerCount()         );
        System.out.println("                        %Charge Expected: 99.4;     Actual: " + sbm.getChargeRemaining()        );
        System.out.println("                        U/T     Expected: 128.5;    Actual: " + sbm.getAverageUsagePerTrip()    );
        System.out.println("                        #Trips  Expected: 34;       Actual: " + sbm.getEstimatedTripsRemaining());
        System.out.println(" ---------------------------------------------------------------------------------------"       );


        sbm.loadPassengers(3);
        sbm.travelTo(-12,5,9);
        sbm.loadPassengers(1);
        sbm.travelTo(9);
        sbm.loadPassengers(-1);
        sbm.loadPassengers(-2);
        sbm.travelTo(0);

        System.out.println("                           Sample Test Case - Part 3 Results                            "       );
        System.out.println("              Scenario: sbm(9,8,12,4500)l(3)t(-12,5,9)l(1)t(9)l(-1)l(-2)t(0)            "       );
        System.out.println(" ---------------------------------------------------------------------------------------"       );
        System.out.println("                        Loc     Expected: 0;        Actual: " + sbm.getLocation()               );
        System.out.println("                        #Pass   Expected: 1;        Actual: " + sbm.getPassengerCount()         );
        System.out.println("                        %Charge Expected: 71.8;     Actual: " + sbm.getChargeRemaining()        );
        System.out.println("                        U/T     Expected: 223.3;    Actual: " + sbm.getAverageUsagePerTrip()    );
        System.out.println("                        #Trips  Expected: 14;       Actual: " + sbm.getEstimatedTripsRemaining());
        System.out.println(" ---------------------------------------------------------------------------------------"       );

        sbm.travelTo(-12);
        sbm.loadPassengers(7);
        sbm.loadPassengers(-2);
        sbm.recharge();
        sbm.travelTo(9,5,9);
        sbm.loadPassengers(-3);
        sbm.loadPassengers(2);
        sbm.travelTo(13);

        System.out.println("                           Sample Test Case - Part 4 Results                            "       );
        System.out.println("            Scenario: sbm(9,8,12,4500)t(-12)l(7)l(-2)r()t(9,5,9)l(-3)l(2)t(13)          "       );
        System.out.println(" ---------------------------------------------------------------------------------------"       );
        System.out.println("                        Loc     Expected: 13;       Actual: " + sbm.getLocation()               );
        System.out.println("                        #Pass   Expected: 5;        Actual: " + sbm.getPassengerCount()         );
        System.out.println("                        %Charge Expected: 85.2;     Actual: " + sbm.getChargeRemaining()        );
        System.out.println("                        U/T     Expected: 270.4;    Actual: " + sbm.getAverageUsagePerTrip()    );
        System.out.println("                        #Trips  Expected: 14;       Actual: " + sbm.getEstimatedTripsRemaining());
        System.out.println(" ---------------------------------------------------------------------------------------"       );

        sbm.travelTo(9);
        sbm.loadPassengers(-3);
        sbm.travelTo(5);
        sbm.loadPassengers(1);
        sbm.travelTo(-3);

        System.out.println("                           Sample Test Case - Part 5 Results                            "       );
        System.out.println("                     Scenario: sbm(9,8,12,4500)t(9)l(-3)t(5)l(1)t(-3)                   "       );
        System.out.println(" ---------------------------------------------------------------------------------------"       );
        System.out.println("                        Loc     Expected: -3;       Actual: " + sbm.getLocation()               );
        System.out.println("                        #Pass   Expected: 3;        Actual: " + sbm.getPassengerCount()         );
        System.out.println("                        %Charge Expected: 74.8;     Actual: " + sbm.getChargeRemaining()        );
        System.out.println("                        U/T     Expected: 249.0;    Actual: " + sbm.getAverageUsagePerTrip()    );
        System.out.println("                        #Trips  Expected: 13;       Actual: " + sbm.getEstimatedTripsRemaining());
        System.out.println(" ---------------------------------------------------------------------------------------"       );

        sbm.travelTo(-18);
        sbm.loadPassengers(3);
        sbm.travelTo(18,11,7);
        sbm.loadPassengers(-1);
        sbm.recharge();
        sbm.travelTo(5);
        sbm.loadPassengers(2);
        sbm.travelTo(-3);
        sbm.loadPassengers(4);
        sbm.travelTo(12);
        sbm.loadPassengers(-1);
        sbm.travelTo(0);

        System.out.println("                           Sample Test Case - Part 6 Results                            "       );
        System.out.println("  Scenario: sbm(9,8,12,4500)t(-18)l(3)t(18,11,7)l(-1)r()t(5)l(2)t(-3)l(4)t(12)l(-1)t(0) "       );
        System.out.println(" ---------------------------------------------------------------------------------------"       );
        System.out.println("                        Loc     Expected: 0;        Actual: " + sbm.getLocation()               );
        System.out.println("                        #Pass   Expected: 10;       Actual: " + sbm.getPassengerCount()         );
        System.out.println("                        %Charge Expected: 19.0;     Actual: " + sbm.getChargeRemaining()        );
        System.out.println("                        U/T     Expected: 465.2;    Actual: " + sbm.getAverageUsagePerTrip()    );
        System.out.println("                        #Trips  Expected: 1;        Actual: " + sbm.getEstimatedTripsRemaining());
        System.out.println(" ---------------------------------------------------------------------------------------"       );

    }
}

