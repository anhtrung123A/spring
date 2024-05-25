package springwithreactnative.travelspot.services.Impl;

import org.springframework.stereotype.Service;
import springwithreactnative.travelspot.entities.SpotEntity;
import springwithreactnative.travelspot.repositories.SpotRepository;
import springwithreactnative.travelspot.services.SpotService;

import java.util.List;

@Service
public class SpotServiceImpl implements SpotService {
    private final SpotRepository spotRepository;
    SpotServiceImpl(SpotRepository spotRepository){
        this.spotRepository = spotRepository;
    }

    @Override
    public List<SpotEntity> getAll() {
        return spotRepository.findAll();
    }
}
