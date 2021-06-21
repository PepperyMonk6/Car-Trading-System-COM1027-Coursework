package coursework_question1;

import static org.junit.Assert.*;
import org.junit.Test;

public class AdvertJTest {
	Advert advert = null;
	
	public void coursework_testAdvertConstructor() {
		Car car = new Car(4321, "Nissan Gtr", 12000, Condition.NEW);
		advert = new Advert(car);
		assertEquals(car, advert.getCar());
	}
	
	@Test
	public void coursework_testGetHighestOffer() {
		Car car = new Car(4321, "Nissan Gtr", 12000, Condition.NEW);
		
		Advert advert = new Advert(car);
		
		User user1 = new User("Kanye West");
		User user2 = new User("Peter Parker");
		User user3 = new User("Tony Stark");
		advert.placeOffer(user1, 10000);
		advert.placeOffer(user2, 11000);
		advert.placeOffer(user3, 12000);
		assertEquals(advert.getOffers().get(2), advert.getHighestOffer());
	}
	
	@Test
    public void coursework_testplaceOffer() {
		User user = new User("Tony Stark");
        Car car = new Car(1234, "Nissan Gtr", 25000, Condition.NEW);
        Advert advert = new Advert(car);
        assertEquals(true, advert.placeOffer(user, 25000));
    }
	
	@Test
	public void coursework_testToString() {
		Car car = new Car(4321, "Nissan Gtr", 12000, Condition.NEW);
		Advert advert = new Advert(car);
		car.setGearbox(CarType.AUTOMATIC);
		car.setBody(CarBody.HATCHBACK);
		car.setColour("Blue");
		car.setNumberOfSeats(5);
		car.setCondition(Condition.NEW);
		assertEquals("Ad: 4321 - Nissan Gtr (£12000.00)\n" + "	Type: AUTOMATIC\n" + "	Style: HATCHBACK\n"
				+ "	Colour: Blue\n" + "	No. of Seats: 5\n" + "	Condition: NEW", advert.toString());
	}
}
