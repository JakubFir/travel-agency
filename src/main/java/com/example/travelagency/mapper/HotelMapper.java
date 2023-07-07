package com.example.travelagency.mapper;

import com.example.travelagency.model.persistence.Hotel;
import com.example.travelagency.model.dto.bookingModel.HotelModel;
import org.springframework.stereotype.Service;

@Service
public class HotelMapper {
    public Hotel mapToHotel(HotelModel hotelModel) {
        return new Hotel(
                hotelModel.getZip(),
                hotelModel.getCityInTrans(),
                hotelModel.getReviewScore(),
                hotelModel.getAddressTrans(),
                hotelModel.getMinTotalPrice(),
                hotelModel.isHasFreeParking());
    }
}
