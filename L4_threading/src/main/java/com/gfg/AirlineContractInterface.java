package com.gfg;

import java.util.List;

public interface AirlineContractInterface {

    List<FlightDetail> getFlightDetails(String src, String dest);
}
