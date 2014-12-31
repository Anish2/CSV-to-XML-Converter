package csv_to_xml;

public class Contestant {
	
	@SuppressWarnings("unused")
	private String name, major, institution;
	@SuppressWarnings("unused")
	private Integer score;
	
	public Contestant(String name, String score, String major, String institution) {
		this.name = name;
		this.major = major;
		this.institution = institution;
		this.score = Integer.parseInt(score);
	}

}
