import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Instruction_Operator extends data_Library{
	//�إ߫��O�P�B�⤸��Table
	LinkedHashMap<String,String> instruction_Operator;
	public ArrayList<String>Instruction;
    public ArrayList<String>Operator;
	public Instruction_Operator(ArrayList<String>Instruction,ArrayList<String>Operator) {
		this.Instruction = Instruction;
		this.Operator = Operator;
		instruction_Operator = new LinkedHashMap<String, String>();
		Combination();
	}
	
	public void Combination() {
		int j = 0;
		for(int i = 0;i<Instruction.size();i++) {
			if(!Instruction.get(i).equals("RSUB")/*&&!Instruction.get(i).equals("END")*/) {
				instruction_Operator.put((i+1)+"."+Instruction.get(i),Operator.get(j));
				j++;
			}
			else 
				instruction_Operator.put((i+1)+"."+Instruction.get(i),"null");
		}
	}
	
	public LinkedHashMap<String, String> getMap() {
		return instruction_Operator;
	}
	
	//�������LOC
	public String getFirst_Loc() {
		String real_Instruction = "";
		for(String start : instruction_Operator.keySet()) {
			for(int i=0;i<start.length();i++) {
				if(start.equals(i+1+".START")) {
					real_Instruction = start;
					break;
				}
			}
			if(!real_Instruction.equals("")) 
				break;
		}
		return real_Instruction;
	}
}