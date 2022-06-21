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
	 * @return if search successful
	 */
	public static boolean searchAll(List<Recipe> r) {
		r.clear();
		try {
			DBConnector.connectToDB();
			PreparedStatement psRecipe = DBConnector.con.prepareStatement("SELECT * FROM recipes ORDER BY recipe_id");
			ResultSet rsetRecipe = psRecipe.executeQuery();
			while (rsetRecipe.next()) {
				// Get basic info
				Recipe tmp = new Recipe(rsetRecipe.getString("recName"), rsetRecipe.getString("recRegion"),
						Integer.valueOf(rsetRecipe.getInt("prepTime")), Integer.valueOf(rsetRecipe.getInt("cookTime")),
						Integer.valueOf(rsetRecipe.getInt("serve")));
				tmp.setRecipeId(rsetRecipe.getInt("recipe_id"));
				tmp.setImageURL(rsetRecipe.getString("imageURL"));
				// Get ingredients
				PreparedStatement psIngredients = DBConnector.con
						.prepareStatement("SELECT * FROM ingredients where recipe_id = ? ORDER BY ingredient_id");
				psIngredients.setString(1, rsetRecipe.getString("recipe_id"));
				ResultSet rsetIngredients = psIngredients.executeQuery();
				while (rsetIngredients.next()) {
					tmp.addIngredient(Integer.valueOf(rsetIngredients.getInt("ingredient_id")),
							Integer.valueOf(rsetIngredients.getInt("quantity")),
							rsetIngredients.getString("description"));
				}
				// Get instructions
				PreparedStatement psInstructions = DBConnector.con
						.prepareStatement("SELECT * FROM instructions where recipe_id = ? ORDER BY instruction_id");
				psInstructions.setString(1, rsetRecipe.getString("recipe_id"));
				ResultSet rsetInstructions = psInstructions.executeQuery();
				while (rsetInstructions.next()) {
					tmp.addInstruction(Integer.valueOf(rsetInstructions.getInt("instruction_id")),
							rsetInstructions.getString("description"));
				}
				r.add(tmp);
			}
			DBConnector.disconnectToDB();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Search operation to DB, search recipe with given recipe name
	 * 
	 * @param r  a given list to store searched recipes
	 * @param rn name of recipe to search
	 * @return if search successful
	 */
	public static boolean searchByName(List<Recipe> r, String rn) {
		r.clear();
		try {
			DBConnector.connectToDB();
			PreparedStatement psRecipe = DBConnector.con
					.prepareStatement("SELECT * FROM recipes WHERE recName LIKE ? ORDER BY recipe_id");
			psRecipe.setString(1, "%" + rn + "%");
			ResultSet rsetRecipe = psRecipe.executeQuery();
			while (rsetRecipe.next()) {
				// Get basic info
				Recipe tmp = new Recipe(rsetRecipe.getString("recName"), rsetRecipe.getString("recRegion"),
						Integer.valueOf(rsetRecipe.getInt("prepTime")), Integer.valueOf(rsetRecipe.getInt("cookTime")),
						Integer.valueOf(rsetRecipe.getInt("serve")));
				tmp.setRecipeId(rsetRecipe.getInt("recipe_id"));
				tmp.setImageURL(rsetRecipe.getString("imageURL"));
				// Get ingredients
				PreparedStatement psIngredients = DBConnector.con
						.prepareStatement("SELECT * FROM ingredients where recipe_id = ? ORDER BY ingredient_id");
				psIngredients.setString(1, rsetRecipe.getString("recipe_id"));
				ResultSet rsetIngredients = psIngredients.executeQuery();
				while (rsetIngredients.next()) {
					tmp.addIngredient(Integer.valueOf(rsetIngredients.getInt("ingredient_id")),
							Integer.valueOf(rsetIngredients.getInt("quantity")),
							rsetIngredients.getString("description"));
				}
				// Get instructions
				PreparedStatement psInstructions = DBConnector.con
						.prepareStatement("SELECT * FROM instructions where recipe_id = ? ORDER BY instruction_id");
				psInstructions.setString(1, rsetRecipe.getString("recipe_id"));
				ResultSet rsetInstructions = psInstructions.executeQuery();
				while (rsetInstructions.next()) {
					tmp.addInstruction(Integer.valueOf(rsetInstructions.getInt("instruction_id")),
							rsetInstructions.getString("description"));
				}
				r.add(tmp);
			}
			DBConnector.disconnectToDB();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Search operation to DB, search recipe with given recipe region
	 * 
	 * @param r  a given list to store searched recipes
	 * @param rg region of recipe to search
	 * @return if search successful
	 */
	public static boolean searchByRegion(List<Recipe> r, String rg) {
		r.clear();
		try {
			DBConnector.connectToDB();
			PreparedStatement psRecipe = DBConnector.con
					.prepareStatement("SELECT * FROM recipes WHERE recRegion = ? ORDER BY recipe_id");
			psRecipe.setString(1, rg);
			ResultSet rsetRecipe = psRecipe.executeQuery();
			while (rsetRecipe.next()) {
				// Get basic info
				Recipe tmp = new Recipe(rsetRecipe.getString("recName"), rsetRecipe.getString("recRegion"),
						Integer.valueOf(rsetRecipe.getInt("prepTime")), Integer.valueOf(rsetRecipe.getInt("cookTime")),
						Integer.valueOf(rsetRecipe.getInt("serve")));
				tmp.setRecipeId(rsetRecipe.getInt("recipe_id"));
				tmp.setImageURL(rsetRecipe.getString("imageURL"));
				// Get ingredients
				PreparedStatement psIngredients = DBConnector.con
						.prepareStatement("SELECT * FROM ingredients where recipe_id = ? ORDER BY ingredient_id");
				psIngredients.setString(1, rsetRecipe.getString("recipe_id"));
				ResultSet rsetIngredients = psIngredients.executeQuery();
				while (rsetIngredients.next()) {
					tmp.addIngredient(Integer.valueOf(rsetIngredients.getInt("ingredient_id")),
							Integer.valueOf(rsetIngredients.getInt("quantity")),
							rsetIngredients.getString("description"));
				}
				// Get instructions
				PreparedStatement psInstructions = DBConnector.con
						.prepareStatement("SELECT * FROM instructions where recipe_id = ? ORDER BY instruction_id");
				psInstructions.setString(1, rsetRecipe.getString("recipe_id"));
				ResultSet rsetInstructions = psInstructions.executeQuery();
				while (rsetInstructions.next()) {
					tmp.addInstruction(Integer.valueOf(rsetInstructions.getInt("instruction_id")),
							rsetInstructions.getString("description"));
				}
				r.add(tmp);
			}
			DBConnector.disconnectToDB();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Insert operation to DB, insert a given recipe
	 * 
	 * @param r a recipe object to insert
	 * @return if insert successful
	 */
	public static boolean insert(Recipe r) {
		try {
			DBConnector.connectToDB();
			PreparedStatement psRecipe = DBConnector.con.prepareStatement(
					"INSERT INTO recipes (recName,recRegion,imageURL,prepTime,cookTime,serve) VALUES (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			PreparedStatement psIngredients = DBConnector.con.prepareStatement(
					"INSERT INTO ingredients (recipe_id,ingredient_id,quantity,description) VALUES (?, ?, ?, ?)");
			PreparedStatement psInstructions = DBConnector.con.prepareStatement(
					"INSERT INTO instructions (recipe_id,instruction_id,description) VALUES (?, ?, ?)");
			// Set basic info
			psRecipe.setString(1, r.getRecipeName());
			psRecipe.setString(2, r.getRecipeRegion());
			psRecipe.setString(3, r.getImageURL());
			psRecipe.setString(4, r.getPrepTime().toString());
			psRecipe.setString(5, r.getCookTime().toString());
			psRecipe.setString(6, r.getServe().toString());
			psRecipe.executeUpdate();
			ResultSet rsetRecipe = psRecipe.getGeneratedKeys();
			if (rsetRecipe.next()) {
				Integer rid = rsetRecipe.getInt(1);
				// Set ingredients
				for (Ingredient ing : r.getIngredients()) {
					psIngredients.setString(1, rid.toString());
					psIngredients.setString(2, ing.getIngredientId().toString());
					psIngredients.setString(3, ing.getQuantity().toString());
					psIngredients.setString(4, ing.getDescription());
					psIngredients.executeUpdate();
				}
				// Set instructions
				for (Instruction ins : r.getInstructions()) {
					psInstructions.setString(1, rid.toString());
					psInstructions.setString(2, ins.getInstructionId().toString());
					psInstructions.setString(3, ins.getDescription());
					psInstructions.executeUpdate();
				}
			}
			DBConnector.disconnectToDB();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Update operation to DB, update a recipe with given changed recipe and rid
	 * 
	 * @param r a recipe object for update
	 * @return if update successful
	 */
	public static boolean update(Recipe r) {
		try {
			DBConnector.connectToDB();
			PreparedStatement psRecipe = DBConnector.con.prepareStatement(
					"UPDATE recipes SET recName = ?, recRegion = ?, imageURL = ?, prepTime = ?, cookTime = ?, serve = ? WHERE recipe_id = ?");
			PreparedStatement psIngredients = DBConnector.con.prepareStatement(
					"INSERT INTO ingredients (recipe_id,ingredient_id,quantity,description) VALUES (?, ?, ?, ?)");
			PreparedStatement psInstructions = DBConnector.con.prepareStatement(
					"INSERT INTO instructions (recipe_id,instruction_id,description) VALUES (?, ?, ?)");
			// Set basic info
			psRecipe.setString(1, r.getRecipeName());
			psRecipe.setString(2, r.getRecipeRegion());
			psRecipe.setString(3, r.getImageURL());
			psRecipe.setString(4, r.getPrepTime().toString());
			psRecipe.setString(5, r.getCookTime().toString());
			psRecipe.setString(6, r.getServe().toString());
			psRecipe.setString(7, r.getRecipeId().toString());
			psRecipe.executeUpdate();
			// Set ingredients
			psIngredients.executeUpdate("DELETE FROM ingredients WHERE recipe_id = " + r.getRecipeId());
			for (Ingredient ing : r.getIngredients()) {
				psIngredients.setString(1, r.getRecipeId().toString());
				psIngredients.setString(2, ing.getIngredientId().toString());
				psIngredients.setString(3, ing.getQuantity().toString());
				psIngredients.setString(4, ing.getDescription());
				psIngredients.executeUpdate();
			}
			// Set instructions
			psInstructions.executeUpdate("DELETE FROM instructions WHERE recipe_id = " + r.getRecipeId());
			for (Instruction ins : r.getInstructions()) {
				psInstructions.setString(1, r.getRecipeId().toString());
				psInstructions.setString(2, ins.getInstructionId().toString());
				psInstructions.setString(3, ins.getDescription());
				psInstructions.executeUpdate();
			}
			DBConnector.disconnectToDB();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Delete operation to DB, delete a recipe with given rid
	 * 
	 * @param rid id of recipe to be deleted
	 * @return if delete successful
	 */
	public static boolean delete(int rid) {
		try {
			DBConnector.connectToDB();
			PreparedStatement psRecipe = DBConnector.con.prepareStatement("DELETE FROM recipes WHERE recipe_id = ?");
			psRecipe.setString(1, String.valueOf(rid));
			psRecipe.executeUpdate();
			DBConnector.disconnectToDB();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
