package ch.hslu.ad.sw14.adjamatrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RailwayNetwork {
    private Map<Station, Integer> stations;
    private List<Route> routes;

    public RailwayNetwork() {
        stations = new HashMap<>();
        routes = new ArrayList<>();
    }

    public boolean[][] generateMatrix() {
        boolean[][] matrix = new boolean[stations.size()][stations.size()];
        for (Route route : routes) {
            matrix[stations.get(route.from())][stations.get(route.to())] = true;
            matrix[stations.get(route.to())][stations.get(route.from())] = true;
        }
        return matrix;
    }

    public boolean addStation(Station station) {
        if (!stations.containsKey(station)) {
            stations.put(station, stations.size());
            return true;
        }
        return false;
    }

    public boolean addRoute(Route route, int weight) {
        if (stations.containsKey(route.from()) && stations.containsKey(route.to())) {
            routes.add(new Route(route.from(), route.to(), weight));
            return true;
        }
        return false;
    }

    public boolean removeStation(Station station) {
        return stations.remove(station) != null;
    }

    public boolean removeRoute(Route route) {
        return routes.remove(route);
    }

    public int numberOfStations() {
        return stations.size();
    }

    public int numberOfRoutes() {
        return routes.size();
    }
}
