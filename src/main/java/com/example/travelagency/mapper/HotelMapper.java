package com.example.travelagency.mapper;

import com.example.travelagency.domain.Hotel;
import com.example.travelagency.model.bookingModel.HotelModel;
import org.springframework.stereotype.Service;

@Service
public class HotelMapper {
    public Hotel mapToHotel(HotelModel hotelModel) {
        return new Hotel(
                hotelModel.getZip(),
                hotelModel.getCity_in_trans(),
                hotelModel.getReview_score(),
                hotelModel.getAddress_trans(),
                hotelModel.getMin_total_price(),
                hotelModel.getHas_free_parking());
    }
}
