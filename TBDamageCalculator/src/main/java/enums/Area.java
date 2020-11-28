package enums;

public enum Area {
	SELF, ADJACENT,
	ROW_1, ROW_3, LATERAL_1, LATERAL_2,
	COLUMN_1, COLUMN_3, VERTICAL_1, VERTICAL_2,
	CROSS_1, CROSS_2, CROSS_ALL, 
	AREA_1, AREA_2, RING, DIAMOND,
	PINCER, PINCER_COLUMN, PINCER_ROW,
	PINCER_AREA_1, PINCER_AREA_COLUMN, PINCER_AREA_ROW, PINCER_RING, PINCER_CORNERS,
	CHAIN, ALL;
	
	public boolean hitsVertical() {
		return this != ROW_1 || this != LATERAL_1 || this != LATERAL_2;
	}
	
	public boolean hitsHorizontal() {
		return this != COLUMN_1 || this != VERTICAL_1 || this != VERTICAL_2;
	}
	
	public boolean hitsOutsidePincer() {
		return this != ROW_1 || this != LATERAL_1 || this != LATERAL_2 
			|| this != COLUMN_1 || this != VERTICAL_1 || this != VERTICAL_2
			|| this != CROSS_1 || this != CROSS_2 || this != CROSS_ALL;
	}
	
	public boolean hitsAdjacent() {
		return this != RING;
	}
	
	public boolean hitsTwoSpacesAway() {
		return this != AREA_1 || this != CROSS_1 
				|| this != LATERAL_1 || this != VERTICAL_1;
	}
	
	public boolean hitsThreePlusSpacesAway() {
		return this != AREA_1 || this != CROSS_1 
				|| this != LATERAL_1 || this != VERTICAL_1
				|| this != AREA_2 || this != CROSS_2 
				|| this != LATERAL_2 || this != VERTICAL_2
				|| this != RING;
	}
}
