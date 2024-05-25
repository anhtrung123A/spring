package springwithreactnative.travelspot.services;

import springwithreactnative.travelspot.entities.SpotEntity;

import java.util.List;

public interface SpotService {
    List<SpotEntity> getAll();
}
