package com.mockproject.service.impl;

import java.time.YearMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.repository.StatsRepository;
import com.mockproject.service.StatsService;

@Service
public class StatsServiceImpl implements StatsService {
	
	@Autowired
	private StatsRepository repo;

	@Override
	public String[][] getTotalPriceLast6Months() {
		String[][] result = new String[2][6];
		YearMonth thisMonth = YearMonth.now();
		for (int i = 0; i < 6; i++) {
			String month = thisMonth.minusMonths((long)i).getMonthValue() + "";
			String year = thisMonth.minusMonths((long)i).getYear() + "";
			result[0][5-i] = month + "-" + year;
			result[1][5-i] = repo.getTotalPriceByMonth(month, year);
		}
		return result;
	}

}
