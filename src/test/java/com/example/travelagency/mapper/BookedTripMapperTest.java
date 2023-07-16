package com.example.travelagency.mapper;

import com.example.travelagency.model.dto.BookedTripDto;
import com.example.travelagency.model.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookedTripMapperTest {
    private BookedTripMapper bookedTripMapper;

    @BeforeEach
    void setUp() {
        bookedTripMapper = new BookedTripMapper();
    }

    @Test
    void mapToBookTripDto() {
        //Given
        BookedTrip bookedTrip = new BookedTrip(1L,new Trip(),new User(),new Flight(),new Hotel());

        //When
        BookedTripDto bookedTripDto = bookedTripMapper.mapToBookedTripDto(bookedTrip);

        //Then
       assertThat(bookedTripDto.getFlight()).isEqualTo(bookedTrip.getFlight());
    }

    @Test
    void mapToBookTripDtoList() {
        //Given
        BookedTrip bookedTrip1 = new BookedTrip(1L,new Trip(),new User(),new Flight(),new Hotel());
        BookedTrip bookedTrip2 = new BookedTrip(2L,new Trip(),new User(),new Flight(),new Hotel());

        bookedTrip2.getUser().setUsername("user2");
        bookedTrip2.getUser().setId(2L);

        List<BookedTrip> allBookedTrips = asList(bookedTrip1, bookedTrip2);

        //When
        List<BookedTripDto> bookedTripDtoList = bookedTripMapper.mapToBookedTripDtoList(allBookedTrips);

        //Then
        assertEquals(2, bookedTripDtoList.size());
        assertEquals("user2", bookedTripDtoList.get(1).getUsername());
        assertEquals(2L, bookedTripDtoList.get(1).getUserId());
    }
}