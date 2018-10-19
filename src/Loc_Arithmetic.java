import java.util.ArrayList;
import java.util.LinkedHashMap;
public class Loc_Arithmetic{
	
	LinkedHashMap<String,String>instruction_Operator;
	ArrayList<String> Loc;
	String start_Loc;
	public Loc_Arithmetic(String start_Loc,LinkedHashMap<String,String>instruction_Operator) {
		this.start_Loc = start_Loc;
		this.instruction_Operator = instruction_Operator;
		Loc = new ArrayList<String>();
		Arithmetic();
	}
	
	public void Arithmetic() {
		String now_Loc = "";
		for(String instruction : instruction_Operator.keySet()) {
			for(int i=0;i<instruction_Operator.size();i++) {
				if(instruction.equals((i+1)+".START")) {
					now_Loc = start_Loc;
					Loc.add(now_Loc);
					break;
				}
				else if(instruction.equals((i+1)+".END")){
					break;
				}
				else if(instruction.equals((i+1)+".BYTE")) {
					int count = 0;
					boolean x = false;
					for(int j=1;j<instruction_Operator.get(instruction).length();j++) {
						if(instruction_Operator.get(instruction).charAt(0)=='C') {
							if(instruction_Operator.get(instruction).charAt(j)!='\'')
								count++;
						}
						else {
							x = true;
							break;
						}
					}
					
					if(x == true) {
						now_Loc = Integer.toHexString(Integer.valueOf(Integer.valueOf(now_Loc,16).toString())+1).toUpperCase();
					}
					else {
						now_Loc = Integer.toHexString(Integer.valueOf(Integer.valueOf(now_Loc,16).toString())+count).toUpperCase();
					}
					Loc.add(now_Loc);
					break;
				}
				else if(instruction.equals((i+1)+".RESW")) {
					int number = Integer.valueOf(instruction_Operator.get(instruction));
					now_Loc = Integer.toHexString(Integer.valueOf(Integer.valueOf(now_Loc,16).toString())+3*number).toUpperCase();
					Loc.add(now_Loc);
					break;
				}
				else if(instruction.equals((i+1)+".RESB")) {
					int number = Integer.valueOf(instruction_Operator.get(instruction));
					now_Loc = Integer.toHexString(Integer.valueOf(Integer.valueOf(now_Loc,16).toString())+number).toUpperCase();
					Loc.add(now_Loc);
					break;
				}
				else if(i == instruction_Operator.size()-1){
					now_Loc = Integer.toHexString(Integer.valueOf(Integer.valueOf(now_Loc,16).toString())+3).toUpperCase();
					Loc.add(now_Loc);
					break;
				}
			}
		}
	}
	
	public ArrayList<String> getLoc() {
		return Loc;
	}
}
