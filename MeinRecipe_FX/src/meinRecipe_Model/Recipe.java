package meinRecipe_Model;

import java.util.LinkedList;
import java.util.List;

/**
 * A customized java class, used to store a recipe.
 * 
 * @author SecKona
 *
 */
public class Recipe {

	private String recipeName;
	private String recipeRegion;
	private String imageURL = "/recipeImage/default.jpg";
	private Integer recipeId;
	private Integer prepTime;
	private Integer cookTime;
	private Integer serve;
	private List<Ingredient> ingredients = new LinkedList<Ingredient>();
	private List<Instruction> instructions = new LinkedList<Instruction>();

	/**
	 * Constructor without any variables
	 * 
	 */
	public Recipe() {
		this.recipeName = null;
		this.recipeRegion = "Others";
		this.prepTime = 0;
		this.cookTime = 0;
		this.serve = 1;
	}

	/**
	 * Constructor with given recipe
	 * 
	 * @param r given recipe
	 */
	public Recipe(Recipe r) {
		this.recipeName = r.recipeName;
		this.recipeRegion = r.recipeRegion;
		this.imageURL = r.imageURL;
		this.recipeId = r.recipeId;
		this.prepTime = r.prepTime;
		this.cookTime = r.cookTime;
		this.serve = r.serve;
		for (Ingredient ing : r.getIngredients()) {
			this.addIngredient(ing);
		}
		for (Instruction ins : r.getInstructions()) {
			this.addInstruction(ins);
		}
	}

	/**
	 * Constructor with all needed variables
	 * 
	 * @param rn name of recipe
	 * @param rr region of recipe
	 * @param pt prepare time of recipe
	 * @param ct cook time of recipe
	 * @param s  server number of recipe
	 */
	public Recipe(String rn, String rr, Integer pt, Integer ct, Integer s) {
		this.recipeName = rn;
		this.recipeRegion = rr;
		this.prepTime = pt;
		this.cookTime = ct;
		this.serve = s;
	}

	/**
	 * Calculate ingredients amount according to server number
	 * 
	 * @param serveNum the typed-in serveNum to calculate
	 */
	public void calIngredients(Integer serveNum) {
		int cur = 0;
		while (cur < ingredients.size()) {
			ingredients.get(cur).setQuantity(ingredients.get(cur).getQuantity() * serveNum / this.serve);
			cur++;
		}
		this.setServe(serveNum);
	}

	/**
	 * Refresh instruction id (Step), is called when an instruction is deleted;
	 */
	public void refreshIngredientId() {
		int ingId = 1;
		for (Ingredient ing : this.ingredients) {
			ing.setIngredientId(ingId++);
		}
	}

	/**
	 * Refresh ingredient id (Order), is called when an ingredient is deleted;
	 */
	public void refreshInstructionId() {
		int insId = 1;
		for (Instruction ins : this.instructions) {
			ins.setInstructionId(insId++);
		}
	}

	/**** variable setters ****/
	public void setRecipeId(Integer rid) {
		this.recipeId = rid;
	}

	public void setImageURL(String iurl) {
		this.imageURL = iurl;
	}

	public void setRecipeName(String rn) {
		this.recipeName = rn;
	}

	public void setRecipeRegion(String rr) {
		this.recipeRegion = rr;
	}

	public void setPrepTime(Integer pt) {
		this.prepTime = pt;
	}

	public void setCookTime(Integer ct) {
		this.cookTime = ct;
	}

	public void setServe(Integer s) {
		this.serve = s;
	}

	public void setIngredients(List<Ingredient> il) {
		this.ingredients = new LinkedList<Ingredient>(il);
	}

	public void addIngredient(Ingredient ing) {
		ingredients.add(ing);
	}

	public void addIngredient(Integer iid, Integer q, String d) {
		ingredients.add(new Ingredient(iid, q, d));
	}

	public void setInstructions(List<Instruction> il) {
		this.instructions = new LinkedList<Instruction>(il);
	}

	public void addInstruction(Instruction ins) {
		instructions.add(ins);
	}

	public void addInstruction(Integer iid, String d) {
		instructions.add(new Instruction(iid, d));
	}

	public void delIngredient(int ingNum) {
		ingredients.remove(ingNum);
	}

	public void delInstruction(int insNum) {
		instructions.remove(insNum);
	}

	/**** variable getters ****/
	public Integer getRecipeId() {
		return this.recipeId;
	}

	public String getImageURL() {
		return this.imageURL;
	}

	public String getRecipeName() {
		return this.recipeName;
	}

	public String getRecipeRegion() {
		return this.recipeRegion;
	}

	public Integer getPrepTime() {
		return this.prepTime;
	}

	public Integer getCookTime() {
		return this.cookTime;
	}

	public Integer getServe() {
		return this.serve;
	}

	public List<Ingredient> getIngredients() {
		return this.ingredients;
	}

	public List<Instruction> getInstructions() {
		return this.instructions;
	}

	/**
	 * @see java.lang.Object
	 */
	@Override
	public String toString() {
		return "\nName: " + this.getRecipeName() + "\nRegion: " + this.getRecipeRegion() + "\nImageURL: "
				+ this.getImageURL() + "\nPrepTime: " + this.getPrepTime() + "\nCookTime: " + this.getCookTime()
				+ "\nServe: " + this.getServe() + "\nIngredients: " + ingredients.toString() + "\nInstructions: "
				+ this.instructions.toString();
	}
}
