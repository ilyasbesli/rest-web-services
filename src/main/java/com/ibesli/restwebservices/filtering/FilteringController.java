package com.ibesli.restwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	private SomeBean filterSomeBean() {
		return new SomeBean("value1", "value2", "value3");
	}

	@GetMapping("/filtering-list")
	private List<SomeBean> filterListOfSomeBean() {
		return Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value11", "value22", "value33"));
	}
}