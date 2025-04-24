package de.tu_darmstadt.informatik.fop.donkeykong.map;

/**
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 */
public enum Identifier {

	// characters/enemies
	MARIO,
	DONKEY_KONG,
	PAULINE,
	FIREBALL,
	SPRING,
	
	// items
	HAMMER,
	HAT,
	PURSE,
	UMBRELLA,
	
	// map elements
	
	// barrels
	BARREL,
	BARREL_STACK,
	OIL_BARREL_UNLIT,
	
	// ladders
	LADDER,
	LADDER_BROKEN,
	
	// map parts
	// different beams, accounting for the height difference between each beam
	// number indicates how much pixels it is pushed up out of the chunk border
	METAL_BEAM,
	METAL_BEAM_1,
	METAL_BEAM_2,
	METAL_BEAM_3,
	METAL_BEAM_4,
	METAL_BEAM_5,
	METAL_BEAM_6,
	METAL_BEAM_7,
	METAL_BEAM_CLIMBABLE,
	METAL_BEAM_CLIMBABLE_1,
	METAL_BEAM_CLIMBABLE_2,
	METAL_BEAM_CLIMBABLE_3,
	METAL_BEAM_CLIMBABLE_4,
	METAL_BEAM_CLIMBABLE_5,
	METAL_BEAM_CLIMBABLE_6,
	METAL_BEAM_CLIMBABLE_7,

	BUTTON_ON,
	ELEVATOR_UPPER,
	ELEVATOR_LOWER,
	ELEVATOR_BEAM,
	ELEVATOR_PLATFORM,
	BORDER;
}
