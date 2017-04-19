package custombeans;

public class KvarBean {
	private String opsiKvara;
	private int brSobe;
	public KvarBean (String opsiKvara, int brSobe){
		this.opsiKvara = opsiKvara;
		this.brSobe=brSobe;
	}
	public String getOpsiKvara() {
		return opsiKvara;
	}
	public void setOpsiKvara(String opsiKvara) {
		this.opsiKvara = opsiKvara;
	}
	public int getBrSobe() {
		return brSobe;
	}
	public void setBrSobe(int brSobe) {
		this.brSobe = brSobe;
	}
	
	
	
	
}
