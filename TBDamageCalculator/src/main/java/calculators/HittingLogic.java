package calculators;

import static enums.Area.*;
import static enums.Direction.*;

import enums.Area;
import enums.Direction;

public class HittingLogic {
	
	public static boolean hitsSelfRowOnly(Area area) {
		return area == ROW_1 || area == LATERAL_1 || area == LATERAL_2;
	}
	
	public static boolean hitsSelfColumnOnly(Area area) {
		return area == COLUMN_1 || area == VERTICAL_1 || area == VERTICAL_2;
	}
	
	public static boolean hitsHorizontal(Area area) {
		return !hitsSelfColumnOnly(area);
	}
	
	public static boolean hitsVertical(Area area) {
		return !hitsSelfRowOnly(area);
	}
	
	public static boolean hitsOutsidePincer(Area area) {
		return !hitsSelfRowOnly(area) && !hitsSelfColumnOnly(area)
			&& area != CROSS_1 && area != CROSS_2 && area != CROSS_ALL;
	}
	
	public static boolean hitsAdjacent(Area area, Direction direction) {
		return area != RING
			&& (direction == VERTICAL ? !hitsSelfRowOnly(area) : !hitsSelfColumnOnly(area));
	}
	
	public static boolean hitsTwoSpacesAway(Area area, Direction direction) {
		return area != AREA_1 && area != CROSS_1 
				&& area != LATERAL_1 && area != VERTICAL_1 
				&& (direction == VERTICAL ? 
						!hitsSelfRowOnly(area) && area != ROW_3 : 
						!hitsSelfColumnOnly(area) && area != COLUMN_3);
	}
	
	public static boolean hitsThreePlusSpacesAway(Area area, Direction direction) {
		return hitsTwoSpacesAway(area, direction)
				&& area != AREA_2 && area != CROSS_2 
				&& area != LATERAL_2 && area != VERTICAL_2
				&& area != RING && area != DIAMOND;
	}
}
