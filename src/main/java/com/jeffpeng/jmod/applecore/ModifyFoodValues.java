package com.jeffpeng.jmod.applecore;

import java.util.HashMap;
import java.util.Optional;

import net.minecraft.item.Item;
import squeek.applecore.api.food.FoodEvent;
import squeek.applecore.api.food.FoodValues;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;


public class ModifyFoodValues {

    private static ModifyFoodValues instance = new ModifyFoodValues();

    
    
    private ModifyFoodValues(){
    	
    }

    public static ModifyFoodValues getInstance() {
    	return instance;
    }
	
	private static HashMap<String, FoodValues> modifiedFoodItems = new HashMap<>();
	

	public void addModifedFoodValue(String foodName, FoodValues foodValues) {
		modifiedFoodItems.put(foodName, foodValues);
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
    public void getModifiedFoodValues(FoodEvent.GetFoodValues event) {
		FoodValuesLookup(event.food.getItem()).ifPresent(foodValue -> {
			   event.foodValues = foodValue;
		});
    }
	
	private Optional<FoodValues> FoodValuesLookup(Item item) {
		return Optional.ofNullable(GameRegistry.findUniqueIdentifierFor(item))
		        	   .map(id -> id.modId + ":" + id.name)
		        	   .filter(itemStr -> modifiedFoodItems.containsKey(itemStr))
		               .flatMap(itemStr -> Optional.ofNullable(modifiedFoodItems.get(itemStr)) );
	};
	
}
