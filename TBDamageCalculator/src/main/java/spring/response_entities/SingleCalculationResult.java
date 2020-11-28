package spring.response_entities;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import calculators.Damage;
import calculators.Distance;
import enums.Direction;

public class SingleCalculationResult {
	private Map<Direction, Map<Distance, Damage>> damages;
	
	private SingleCalculationResult(Map<Direction, Map<Distance, Damage>> damages) {
		this.damages = damages;
	}
	
	public Map<Direction, Map<Distance, Damage>> getDamages(){
		return damages;
	}
	public Damage getDamage(Direction dir, Distance target) {
		return damages.getOrDefault(dir, Collections.emptyMap())
				.getOrDefault(target, Damage.zeroDamage());
	}
	
	public static class Builder {
		private Map<Direction, Map<Distance, Damage>> damages = new EnumMap<>(Direction.class);
		
		public Builder setDamage(Direction dir, Distance target, Damage dmg) {
			Map<Distance, Damage> map = this.damages.getOrDefault(dir, new EnumMap<>(Distance.class));
			map.put(target, dmg);
			this.damages.put(dir, map);
			return this;
		}
		public SingleCalculationResult build() {
			return new SingleCalculationResult(this.damages);
		}
	}
}
