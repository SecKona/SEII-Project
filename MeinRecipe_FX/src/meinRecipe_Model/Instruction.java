package meinRecipe_Model;

/**
 * A customized java class, used to store an instruction.
 * 
 * @author SecKona
 *
 */
public class Instruction {

	private Integer instructionId;
	private String description;

	/**
	 * Constructor with only id of instruction
	 * 
	 * @param iid id of instruction
	 */
	public Instruction(Integer iid) {
		this.instructionId = iid;
		this.description = "Default";
	}

	/**
	 * Constructor with given instruction
	 * 
	 * @param ins given instruction
	 */
	public Instruction(Instruction ins) {
		this.instructionId = ins.instructionId;
		this.description = ins.description;
	}

	/**
	 * Constructor with variables of instruction
	 * 
	 * @param iid id of instruction (step)
	 * @param d   description of instruction
	 */
	public Instruction(Integer iid, String d) {
		this.instructionId = iid;
		this.description = d;
	}

	/**** variable setters ****/
	public void setInstructionId(Integer iid) {
		this.instructionId = iid;
	}

	public void setDescription(String d) {
		this.description = d;
	}

	/**** variable getters ****/
	public Integer getInstructionId() {
		return this.instructionId;
	}

	public String getDescription() {
		return this.description;
	}

	/**
	 * @see java.lang.Object
	 */
	@Override
	public String toString() {
		return this.instructionId + ". " + this.getDescription();
	}
}
