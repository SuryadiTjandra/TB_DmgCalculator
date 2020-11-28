package spring.response_entities;

import java.util.EnumMap;
import java.util.Map;

import calculators.Damage;
import enums.Direction;

public class DoubleCalculationResult {
	private Map<Direction, Damage> damages;
	
	private DoubleCalculationResult(Map<Direction, Damage> damages) {
		this.damages = damages;
	}
	
	public Map<Direction, Damage> getDamages(){
		return damages;
	}
	public Damage getDamage(Direction dir) {
		return damages.getOrDefault(dir, Damage.zeroDamage());
	}
	
	public static class Builder {
		private Map<Direction, Damage> damages = new EnumMap<>(Direction.class);
		
		public Builder setDamage(Direction dir, Damage dmg) {
			this.damages.put(dir, dmg);
			return this;
		}
		public DoubleCalculationResult build() {
			return new DoubleCalculationResult(this.damages);
		}
	}
}
