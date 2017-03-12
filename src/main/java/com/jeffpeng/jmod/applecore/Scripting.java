package com.jeffpeng.jmod.applecore;

import com.jeffpeng.jmod.JMODRepresentation;
import com.jeffpeng.jmod.primitives.ModScriptObject;
import com.jeffpeng.jmod.applecore.actions.ModifyFoodValue;

import cpw.mods.fml.common.event.FMLInterModComms;

public class Scripting extends ModScriptObject {

	public Scripting(JMODRepresentation owner){
		super(owner);
	}
	
	public void modifyFoodValue(String food, int hunger, float saturationModifier) {
		if(owner.testForMod("AppleCore")) new ModifyFoodValue(owner, food, hunger, saturationModifier);
	}
	
	public void blackListFoodFromHungerOverhaulChanges(String foodName) {
		if(owner.testForMod("AppleCore") && owner.testForMod("HungerOverhaul")) {
			FMLInterModComms.sendMessage("HungerOverhaul", "BlacklistFood", foodName);
		}
	}
}
