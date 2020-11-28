package spring.request_entities;

public class AdventurerSetup {
	private String advName;
	private int activeJob;
	private String companionName;
	private int[][] equippedSkillNo;
	
	public String getAdvName() {
		return advName;
	}
	public int getActiveJob() {
		return activeJob;
	}
	public String getCompanionName() {
		return companionName;
	}
	public int[][] getEquippedSkillNo() {
		return equippedSkillNo;
	}
	
	//setters are private, only for Jackson serialization
	@SuppressWarnings("unused")
	private void setAdvName(String advName) {
		this.advName = advName;
	}
	@SuppressWarnings("unused")
	private void setActiveJob(int activeJob) {
		this.activeJob = activeJob;
	}
	@SuppressWarnings("unused")
	private void setCompanionName(String companionName) {
		this.companionName = companionName;
	}
	@SuppressWarnings("unused")
	private void setEquippedSkillNo(int[][] equippedSkillNo) {
		this.equippedSkillNo = equippedSkillNo;
	}
	
	
}
