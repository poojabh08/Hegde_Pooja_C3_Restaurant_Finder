
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    public Restaurant restaurant;
    public void mockRestaurant() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //REFACTOR ALL THE REPEATED LINES OF CODE
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        mockRestaurant();
        LocalTime mockTime = LocalTime.parse("12:30:00");
        Restaurant mockedRes = Mockito.spy(restaurant);
        Mockito.when(mockedRes.getCurrentTime()).thenReturn(mockTime);
        assertTrue(mockedRes.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        mockRestaurant();
        LocalTime mockTime = LocalTime.parse("23:30:00");
        Restaurant mockedRes = Mockito.spy(restaurant);
        Mockito.when(mockedRes.getCurrentTime()).thenReturn(mockTime);
        assertFalse(mockedRes.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        mockRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        mockRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        mockRestaurant();
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<<ORDER COST>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void calculate_amount_should_return_zero_when_there_is_no_selected_items() {
        mockRestaurant();
        List<String> mockItems = new ArrayList<>();
        assertEquals(0, restaurant.calculateAmount(mockItems));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<ORDER COST>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}