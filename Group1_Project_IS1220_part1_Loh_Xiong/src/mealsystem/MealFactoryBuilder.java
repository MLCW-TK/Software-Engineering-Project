package mealsystem;

import java.util.HashMap;
import java.util.Map;

public class MealFactoryBuilder {
	public static Map<String,AbstractMealFactory> buildFactory() {
		Map<String, AbstractMealFactory> m = new HashMap<String, AbstractMealFactory>();
		m.put("Appertizer", new AppertizerFactory());
		m.put("MainCourse", new MainCourseFactory());
		m.put("Dessert", new DessertFactory());
		//only 1 line of client code to be added here when new class of items are needed to add new factory into client repertoire
		
		return m;
	}
}
