package testers;

import meinRecipe_Model.Recipe;

public class RecipeModelTester {
	public static void main(String[] args) {
		Recipe test = new Recipe("test", "Others", 0, 0, 1);
		test.addIngredient(1,500, "g SchweinBlauch");
		test.addIngredient(2,20, "g Sternanis");
		test.addInstruction(1,"Grillen Sie die SchweinBlauch");
		test.addInstruction(2,"Dann fiertig");
		
		System.out.println(test.toString());
		test.calIngredients(2);
		System.out.println(test.toString());
	}
}
