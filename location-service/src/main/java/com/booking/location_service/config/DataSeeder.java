package com.booking.location_service.config;

import com.booking.location_service.dto.LocationDto;
import com.booking.location_service.entity.Location;
import com.booking.location_service.service.LocationService;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {
    String[] data= DataLocation.locations;

    @Autowired
    private LocationService locationService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
       if(locationService.getAll().size()<=0){
            for(int i=0;i<data.length;i++){
                LocationDto locationDto =new LocationDto();
                locationDto.setName(data[i]);
                locationService.add(locationDto);
            }
       }
    }
}
