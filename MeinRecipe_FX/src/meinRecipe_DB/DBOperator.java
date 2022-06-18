package meinRecipe_DB;

import java.sql.*;
import java.util.List;

import meinRecipe_Model.Recipe;
import meinRecipe_Model.Ingredient;
import meinRecipe_Model.Instruction;

/**
 * Operator class to encapsulate operation of tables in data base
 * 
 * @author SecKona
 *
 */
public class DBOperator {

	/**
	 * Search operation to DB, search all of the recipes
	 * 
	 * @param r a given list to store searched recipes
	 */
	public static boolean searchAll(List<Recipe> r) {
		r.clear();
		try {
			DBConnector.connectToDB();
			Statement sRecipe = DBConnector.con.createStatement();
			ResultSet rsetRecipe = sRecipe.executeQuery("SELECT * FROM recipes ORDER BY recipe_id");
			while (rsetRecipe.next()) {
				// Get basic info
				Recipe tmp = new Recipe(rsetRecipe.getString("recName"), rsetRecipe.getString("recRegion"),
						Integer.valueOf(rsetRecipe.getInt("prepTime")), Integer.valueOf(rsetRecipe.getInt("cookTime")),
						Integer.valueOf(rsetRecipe.getInt("serve")));
				tmp.setRecipeId(rsetRecipe.getInt("recipe_id"));
				tmp.setImageURL(rsetRecipe.getString("imageURL"));
				// Get ingredients
				Statement sIngredients = DBConnector.con.createStatement();
				ResultSet rsetIngredients = sIngredients.executeQuery("SELECT * FROM ingredients where recipe_id = "
						+ rsetRecipe.getString("recipe_id") + " ORDER BY ingredient_id");
				while (rsetIngredients.next()) {
					tmp.addIngredient(Integer.valueOf(rsetIngredients.getInt("ingredient_id")),
							Integer.valueOf(rsetIngredients.getInt("quantity")),
							rsetIngredients.getString("description"));
				}
				// Get instructions
				Statement sInstructions = DBConnector.con.createStatement();
				ResultSet rsetInstructions = sInstructions.executeQuery("SELECT * FROM instructions where recipe_id = "
						+ rsetRecipe.getString("recipe_id") + " ORDER BY instruction_id");
				while (rsetInstructions.next()) {
					tmp.addInstruction(Integer.valueOf(rsetInstructions.getInt("instruction_id")),
							rsetInstructions.getString("description"));
				}
				r.add(tmp);
			}
			DBConnector.disconnectToDB();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Search operation to DB, search recipe with given recipe name
	 * 
	 * @param r  a given list to store searched recipes
	 * @param rn name of recipe to search
	 */
	public static boolean searchByName(List<Recipe> r, String rn) {
		r.clear();
		try {
			DBConnector.connectToDB();
			Statement sRecipe = DBConnector.con.createStatement();
			ResultSet rsetRecipe = sRecipe
					.executeQuery("SELECT * FROM recipes WHERE recName LIKE '" + rn + "' ORDER BY recipe_id");
			while (rsetRecipe.next()) {
				// Get basic info
				Recipe tmp = new Recipe(rsetRecipe.getString("recName"), rsetRecipe.getString("recRegion"),
						Integer.valueOf(rsetRecipe.getInt("prepTime")), Integer.valueOf(rsetRecipe.getInt("cookTime")),
						Integer.valueOf(rsetRecipe.getInt("serve")));
				tmp.setRecipeId(rsetRecipe.getInt("recipe_id"));
				tmp.setImageURL(rsetRecipe.getString("imageURL"));
				// Get ingredients
				Statement sIngredients = DBConnector.con.createStatement();
				ResultSet rsetIngredients = sIngredients.executeQuery("SELECT * FROM ingredients where recipe_id = "
						+ rsetRecipe.getString("recipe_id") + " ORDER BY ingredient_id");
				while (rsetIngredients.next()) {
					tmp.addIngredient(Integer.valueOf(rsetIngredients.getInt("ingredient_id")),
							Integer.valueOf(rsetIngredients.getInt("quantity")),
							rsetIngredients.getString("description"));
				}
				// Get instructions
				Statement sInstructions = DBConnector.con.createStatement();
				ResultSet rsetInstructions = sInstructions.executeQuery("SELECT * FROM instructions where recipe_id = "
						+ rsetRecipe.getString("recipe_id") + " ORDER BY instruction_id");
				while (rsetInstructions.next()) {
					tmp.addInstruction(Integer.valueOf(rsetInstructions.getInt("instruction_id")),
							rsetInstructions.getString("description"));
				}
				r.add(tmp);
			}
			DBConnector.disconnectToDB();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Search operation to DB, search recipe with given recipe region
	 * 
	 * @param r  a given list to store searched recipes
	 * @param rg region of recipe to search
	 */
	public static boolean searchByRegion(List<Recipe> r, String rg) {
		r.clear();
		try {
			DBConnector.connectToDB();
			Statement sRecipe = DBConnector.con.createStatement();
			ResultSet rsetRecipe = sRecipe
					.executeQuery("SELECT * FROM recipes WHERE recRegion = '" + rg + "' ORDER BY recipe_id");
			while (rsetRecipe.next()) {
				// Get basic info
				Recipe tmp = new Recipe(rsetRecipe.getString("recName"), rsetRecipe.getString("recRegion"),
						Integer.valueOf(rsetRecipe.getInt("prepTime")), Integer.valueOf(rsetRecipe.getInt("cookTime")),
						Integer.valueOf(rsetRecipe.getInt("serve")));
				tmp.setRecipeId(rsetRecipe.getInt("recipe_id"));
				tmp.setImageURL(rsetRecipe.getString("imageURL"));
				// Get ingredients
				Statement sIngredients = DBConnector.con.createStatement();
				ResultSet rsetIngredients = sIngredients.executeQuery("SELECT * FROM ingredients where recipe_id = "
						+ rsetRecipe.getString("recipe_id") + " ORDER BY ingredient_id");
				while (rsetIngredients.next()) {
					tmp.addIngredient(Integer.valueOf(rsetIngredients.getInt("ingredient_id")),
							Integer.valueOf(rsetIngredients.getInt("quantity")),
							rsetIngredients.getString("description"));
				}
				// Get instructions
				Statement sInstructions = DBConnector.con.createStatement();
				ResultSet rsetInstructions = sInstructions.executeQuery("SELECT * FROM instructions where recipe_id = "
						+ rsetRecipe.getString("recipe_id") + " ORDER BY instruction_id");
				while (rsetInstructions.next()) {
					tmp.addInstruction(Integer.valueOf(rsetInstructions.getInt("instruction_id")),
							rsetInstructions.getString("description"));
				}
				r.add(tmp);
			}
			DBConnector.disconnectToDB();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Insert operation to DB, insert a given recipe
	 * 
	 * @param r a recipe object to insert
	 */
	public static boolean insert(Recipe r) {
		try {
			DBConnector.connectToDB();
			int ingCount = 1;
			int insCount = 1;
			Statement sRecipe = DBConnector.con.createStatement();
			Statement sIngredients = DBConnector.con.createStatement();
			Statement sInstructions = DBConnector.con.createStatement();
			// Set basic info
			sRecipe.executeUpdate(
					"INSERT INTO recipes (recName,recRegion,imageURL,prepTime,cookTime,serve) VALUES ('"
							+ r.getRecipeName() + "','" + r.getRecipeRegion() + "','" + r.getImageURL() + "',"
							+ r.getPrepTime() + "," + r.getCookTime() + "," + r.getServe() + ")",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rsetRecipe = sRecipe.getGeneratedKeys();
			if (rsetRecipe.next()) {
				int rid = rsetRecipe.getInt(1);
				// Set ingredients
				for (Ingredient ing : r.getIngredients()) {
					sIngredients.executeUpdate(
							"INSERT INTO ingredients (recipe_id,ingredient_id,quantity,description) VALUES (" + rid
									+ "," + ingCount++ + "," + ing.getQuantity() + ",'" + ing.getDescription() + "')");
				}
				// Set instructions
				for (Instruction ins : r.getInstructions()) {
					sInstructions
							.executeUpdate("INSERT INTO instructions (recipe_id,instruction_id,description) VALUES ("
									+ rid + "," + insCount++ + ",'" + ins.getDescription() + "')");
				}
			}
			DBConnector.disconnectToDB();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Update operation to DB, update a recipe with given changed recipe and rid
	 * 
	 * @param r   a recipe object for update
	 * @param rid id of recipe to be updated
	 */
	public static boolean update(Recipe r) {
		try {
			DBConnector.connectToDB();
			Statement sRecipe = DBConnector.con.createStatement();
			Statement sIngredients = DBConnector.con.createStatement();
			Statement sInstructions = DBConnector.con.createStatement();
			// Set basic info
			sRecipe.executeUpdate(
					"UPDATE recipes SET recName = '" + r.getRecipeName() + "',recRegion = '" + r.getRecipeRegion()
							+ "',imageURL = '" + r.getImageURL() + "',prepTime = " + r.getPrepTime() + ",cookTime = "
							+ r.getCookTime() + ",serve = " + r.getServe() + " WHERE recipe_id = " + r.getRecipeId());
			// Set ingredients
			sIngredients.executeUpdate("DELETE FROM ingredients WHERE recipe_id = " + r.getRecipeId());
			for (Ingredient ing : r.getIngredients()) {
				sIngredients
						.executeUpdate("INSERT INTO ingredients (recipe_id,ingredient_id,quantity,description) VALUES ("
								+ r.getRecipeId() + "," + ing.getIngredientId() + "," + ing.getQuantity() + ",'"
								+ ing.getDescription() + "')");
			}
			// Set instructions
			sInstructions.executeUpdate("DELETE FROM instructions WHERE recipe_id = " + r.getRecipeId());
			for (Instruction ins : r.getInstructions()) {
				sInstructions.executeUpdate("INSERT INTO instructions (recipe_id,instruction_id,description) VALUES ("
						+ r.getRecipeId() + "," + ins.getInstructionId() + ",'" + ins.getDescription() + "')");
			}
			DBConnector.disconnectToDB();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Delete operation to DB, delete a recipe with given rid
	 * 
	 * @param rid id of recipe to be deleted
	 */
	public static boolean delete(int rid) {
		try {
			DBConnector.connectToDB();
			Statement sRecipe = DBConnector.con.createStatement();
			sRecipe.executeUpdate("DELETE FROM recipes WHERE recipe_id = " + rid);
			DBConnector.disconnectToDB();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

}
