package com.conference.calender;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
class CalenderApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	public void tracktest() {
		List<Integer> track = new ArrayList<>();
		track.add(1);
		track.add(2);
		track.add(4);
		track.add(5);

		List<Integer> deneme = track.stream()
				.sorted(Comparator.reverseOrder())
				.limit(track.size() - 1)
				.collect(Collectors.toList());

		for (Integer den : deneme) {
			System.out.println("sorted" + den);
		}
	}



	@Test
	public void dateTest() {
		LocalDateTime today = LocalDateTime.parse("2024-01-20T10:00:00");
		LocalDateTime pastDate = LocalDateTime.parse("2024-01-20T12:00:00");

		LocalDateTime conferenceDateTime = LocalDateTime.parse("2024-01-20T10:00:00");
		conferenceDateTime = conferenceDateTime.plusHours(2L);
		System.out.println(conferenceDateTime);
		System.out.println("is Conference Date Time range of 12 and 13 : " + conferenceDateTime);
		System.out.println(conferenceDateTime.isBefore(LocalDateTime.parse("2024-01-20T12:00:00")));

		boolean isBefore = today.isBefore(pastDate);	//false

		boolean isAfter = today.isAfter(pastDate);		//true

		boolean isEqual = today.isEqual(LocalDateTime.of(2022, 1, 9,12,00,00));	//false


		System.out.println("isbefore : " +isBefore);
		System.out.println("isAfter : " + isAfter);
		System.out.println("isEqual : " + isEqual);

	}
	@Test
	public void dateMethodTest() {
		//LocalDateTime date = LocalDateTime.parse("2024-02-14T13:30:00");
		LocalDateTime date = LocalDateTime.parse("2024-02-14T11:30:00");

		System.out.println(date);
		LocalDateTime blockedDateRangeStart = LocalDateTime.of(date.toLocalDate(), LocalTime.of(12,00,00));
		LocalDateTime blockedDateRangeEnd = LocalDateTime.of(date.toLocalDate(), LocalTime.of(14,00,00));

		if(date.isBefore(blockedDateRangeStart) || date.isAfter(blockedDateRangeEnd)){
			System.out.println( true);
		}else {
			System.out.println( false);

		}
	}

}
