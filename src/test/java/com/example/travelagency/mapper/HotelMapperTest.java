package com.example.travelagency.mapper;

import com.example.travelagency.model.dto.bookingModel.HotelModel;
import com.example.travelagency.model.persistence.Hotel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelMapperTest {
    private HotelMapper hotelMapper;

    @BeforeEach
    void setUp() {
        hotelMapper = new HotelMapper();
    }

    @Test
    void mapToHotel() {
        // Given
        HotelModel hotelModel = new HotelModel();
        hotelModel.setZip("12345");
        hotelModel.setCity_in_trans("Example City");
        hotelModel.setReview_score(4.5);
        hotelModel.setAddress_trans("Example Address");
        hotelModel.setMin_total_price("100 USD");
        hotelModel.setHas_free_parking("Yes");

        // When
        Hotel hotel = hotelMapper.mapToHotel(hotelModel);

        // Then
        assertEquals("12345", hotel.getZip());
        assertEquals(4.5, hotel.getReviewScore());
        assertEquals("100 USD", hotel.getMinTotalPrice());
        assertEquals("Yes", hotel.getHasFreeParking());
    }
}