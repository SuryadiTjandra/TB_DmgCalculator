package calculator;

import static enums.Area.*;
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

@RunWith(Parameterized.class)
public class HittingLogicTest {

	@Parameter(0)
	public Area area;
	
	@Parameter(1)
	public boolean hitsVertical;
	
	@Parameter(2)
	public boolean hitsHorizontal;
	
	@Parameter(3)
	public boolean hitsOut;
	
	@Parameters(name = "Area {0}")
	public static Collection<Object[]> parameters(){
		return Arrays.asList(new Object[][] {
			{ROW_1, false, true, false},
			{ROW_3, true, true, true},
			{LATERAL_1, false, true, false},
			{LATERAL_2, false, true, false},
			{COLUMN_1, true, false, false},
			{COLUMN_3, true, true, true},
			{VERTICAL_1, true, false, false},
			{VERTICAL_2, true, false, false},
			{CROSS_1, true, true, false},
			{CROSS_2, true, true, false},
			{CROSS_ALL, true, true, false},
			{AREA_1, true, true, true},
			{AREA_2, true, true, true},
			{RING, true, true, true},
			{DIAMOND, true, true, true},
			{PINCER, true, true, true},
			{PINCER_ROW, true, true, true},
			{PINCER_COLUMN, true, true, true},
			{PINCER_AREA_1, true, true, true},
			{PINCER_AREA_ROW, true, true, true},
			{PINCER_AREA_COLUMN, true, true, true},
			{PINCER_RING, true, true, true},
			{PINCER_CORNERS, true, true, true},
			{ALL, true, true, true}
		});
	}
	
	@Test
	public void verticalTest() {
		assertEquals(hitsVertical, HittingLogic.hitsVertical(area));
	}

	@Test
	public void horizontalTest() {
		assertEquals(hitsHorizontal, HittingLogic.hitsHorizontal(area));
	}
	
	@Test
	public void outTest() {
		assertEquals(hitsOut, HittingLogic.hitsOutsidePincer(area));
	}


}
