package calculator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import calculators.HittingLogic;
import enums.Area;
import enums.Direction;

import static enums.Area.*;
import static enums.Direction.*;

@RunWith(Parameterized.class)
public class HittingLogicTest2 {

	@Parameter(0)
	public Area area;
	
	@Parameter(1)
	public Direction direction;
	
	@Parameter(2)
	public boolean hitsAdjacent;
	
	@Parameter(3)
	public boolean hitsTwoSpaces;
	
	@Parameter(4)
	public boolean hitsThreeSpaces;
	
	@Parameters(name = "Area {0}, Direction {1}")
	public static Collection<Object[]> parameters(){
		return Arrays.asList(new Object[][] {
			{ROW_1, HORIZONTAL, true, true, true},
			{ROW_3, HORIZONTAL, true, true, true},
			{LATERAL_1, HORIZONTAL, true, false, false},
			{LATERAL_2, HORIZONTAL, true, true, false},
			{COLUMN_1, HORIZONTAL, false, false, false},
			{COLUMN_3, HORIZONTAL, true, false, false},
			{VERTICAL_1, HORIZONTAL, false, false, false},
			{VERTICAL_2, HORIZONTAL, false, false, false},
			{CROSS_1, HORIZONTAL, true, false, false},
			{CROSS_2, HORIZONTAL, true, true, false},
			{CROSS_ALL, HORIZONTAL, true, true, true},
			{AREA_1, HORIZONTAL, true, false, false},
			{AREA_2, HORIZONTAL, true, true, false},
			{RING, HORIZONTAL, false, true, false},
			{DIAMOND, HORIZONTAL, true, true, false},
			{PINCER, HORIZONTAL, true, true, true},
			{PINCER_ROW, HORIZONTAL, true, true, true},
			{PINCER_COLUMN, HORIZONTAL, true, true, true},
			{PINCER_AREA_1, HORIZONTAL, true, true, true},
			{PINCER_AREA_ROW, HORIZONTAL, true, true, true},
			{PINCER_AREA_COLUMN, HORIZONTAL, true, true, true},
			{PINCER_RING, HORIZONTAL, true, true, true},
			{PINCER_CORNERS, HORIZONTAL, true, true, true},
			{ALL, HORIZONTAL, true, true, true},
			
			{ROW_1, VERTICAL, false, false, false},
			{ROW_3, VERTICAL, true, false, false},
			{LATERAL_1, VERTICAL, false, false, false},
			{LATERAL_2, VERTICAL, false, false, false},
			{COLUMN_1, VERTICAL, true, true, true},
			{COLUMN_3, VERTICAL, true, true, true},
			{VERTICAL_1, VERTICAL, true, false, false},
			{VERTICAL_2, VERTICAL, true, true, false},
			{CROSS_1, VERTICAL, true, false, false},
			{CROSS_2, VERTICAL, true, true, false},
			{CROSS_ALL, VERTICAL, true, true, true},
			{AREA_1, VERTICAL, true, false, false},
			{AREA_2, VERTICAL, true, true, false},
			{RING, VERTICAL, false, true, false},
			{DIAMOND, VERTICAL, true, true, false},
			{PINCER, VERTICAL, true, true, true},
			{PINCER_ROW, VERTICAL, true, true, true},
			{PINCER_COLUMN, VERTICAL, true, true, true},
			{PINCER_AREA_1, VERTICAL, true, true, true},
			{PINCER_AREA_ROW, VERTICAL, true, true, true},
			{PINCER_AREA_COLUMN, VERTICAL, true, true, true},
			{PINCER_RING, VERTICAL, true, true, true},
			{PINCER_CORNERS, VERTICAL, true, true, true},
			{ALL, VERTICAL, true, true, true},
		});
	}
	
	@Test
	public void adjacentTest() {
		assertEquals(hitsAdjacent, HittingLogic.hitsAdjacent(area, direction));
	}
	
	@Test
	public void twoSpaceTest() {
		assertEquals(hitsTwoSpaces, HittingLogic.hitsTwoSpacesAway(area, direction));
	}
	@Test
	public void threeSpaceTest() {
		assertEquals(hitsThreeSpaces, HittingLogic.hitsThreePlusSpacesAway(area, direction));
	}

}
