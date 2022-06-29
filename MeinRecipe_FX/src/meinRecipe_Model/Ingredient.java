package meinRecipe_Model;

/**
 * A customized java class, used to store an ingredient.
 * 
 * @author SecKona
 *
 */
public class Ingredient {

	private Integer ingredientId;
	private Integer quantity;
	private String description;

	/**
	 * Constructor with only id of ingredient
	 * 
	 * @param iid id of ingredient
	 */
	public Ingredient(Integer iid) {
		this.ingredientId = iid;
		this.quantity = 0;
		this.description = "Default";
	}

	/**
	 * Constructor with given ingredient
	 * 
	 * @param ing given ingredient
	 */
	public Ingredient(Ingredient ing) {
		this.ingredientId = ing.ingredientId;
		this.quantity = ing.quantity;
		this.description = ing.description;
	}

	/**
	 * Constructor with variables of ingredient
	 * 
	 * @param iid id of ingredient
	 * @param q   quantity of ingredient
	 * @param d   description of ingredient
	 */
	public Ingredient(Integer iid, Integer q, String d) {
		this.ingredientId = iid;
		this.quantity = q;
		this.description = d;
	}

	/**** variable setters ****/
	public void setIngredientId(Integer iid) {
		this.ingredientId = iid;
	}

	public void setQuantity(Integer q) {
		this.quantity = q;
	}

	public void setDescription(String d) {
		this.description = d;
	}

	/**** variable getters ****/
	public Integer getIngredientId() {
		return this.ingredientId;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public String getDescription() {
		return this.description;
	}

	/**
	 * @see java.lang.Object
	 */
	@Override
	public String toString() {
		return this.getQuantity() + " " + this.getDescription();
	}
}
