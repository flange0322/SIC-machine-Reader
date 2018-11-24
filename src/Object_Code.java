import java.util.ArrayList;
import java.util.LinkedHashMap;
public class Object_Code {

	//計算Object codes
	ArrayList<String> object_Codes;
	LinkedHashMap<String,String> instruction_Operator;
	LinkedHashMap<String,String> sign_Loc;
	LinkedHashMap<String,String> instruction_Opcodes;
	public Object_Code(LinkedHashMap<String,String> instruction_Operator,LinkedHashMap<String,String> sign_Loc,LinkedHashMap<String,String> instruction_Opcodes) {
		this.instruction_Operator = instruction_Operator;
		this.sign_Loc = sign_Loc;
		this.instruction_Opcodes = instruction_Opcodes;
		object_Codes = new ArrayList<String>();
		making();
	}
	
	public void making() {
		boolean inputdata = false;
		int start_Pos = 0;
		int end_Pos = 0;
		for(String instruction : instruction_Operator.keySet()) {
			for(int i=0;i<instruction.length();i++) {
				if(instruction.charAt(i) == '.'){
					start_Pos = i+1;
					inputdata = true;
				}
				if(inputdata == true) {
					end_Pos = instruction.length();
					String indeX_word = "";
					//檢查運算元是否x = 1;
					for(int j=0;j<instruction_Operator.get(instruction).length();j++) {
						if(instruction_Operator.get(instruction).charAt(j) == ','){
							int end_Pos_x = j;
							indeX_word += instruction_Operator.get(instruction).substring(0, end_Pos_x);
						}
					}
					
					if(instruction.substring(start_Pos, end_Pos).equals("START")||instruction.substring(start_Pos, end_Pos).equals("END")||instruction.substring(start_Pos, end_Pos).equals("RESW")||instruction.substring(start_Pos, end_Pos).equals("RESB"))
						break;
					else if(instruction.substring(start_Pos, end_Pos).equals("RSUB")) {
						object_Codes.add(instruction_Opcodes.get(instruction.substring(start_Pos, end_Pos))+"0000");
						break;
					}
					else if(instruction.substring(start_Pos, end_Pos).equals("BYTE")) {
						String Oper = instruction_Operator.get(instruction);
						String word = "";
						for(int j=1;j<Oper.length();j++) {
							if(Oper.charAt(0) == 'C') {
								if(Oper.charAt(j)!='\'')
									word = word+Oper.charAt(j);
							}
							else{
								if(Oper.charAt(j)!='\'')
									word = word+Oper.charAt(j);
							}
						}
						if(Oper.charAt(0) == 'C') {
							String tmp = "";
							for(int j = 0;j<word.length();j++) {
								char c = word.charAt(j);
								int c1 = (int)c;
								tmp = tmp + Integer.toHexString(c1).toUpperCase();
							}
							word = "";
							word = tmp;
						}
						object_Codes.add(word);
						break;
					}
					else if(instruction.substring(start_Pos, end_Pos).equals("WORD")) { 
						int Int_number = Integer.valueOf(instruction_Operator.get(instruction));
						String number = "";
						//正數處理
						if(Int_number>=0) {
							number = Integer.toHexString(Int_number);
						}
						//負數處理
						else {
							String check_length = Integer.toHexString(Int_number);
							if(check_length.length() < 6) {
								number = Integer.toHexString(Int_number);
							}
							else {
								number = Integer.toHexString(Int_number).substring(2);
							}
						}
						while(number.length() < 6) {
							if(number.length() != 6) {
								number = "0"+number;
							}
						}
						object_Codes.add(number.toUpperCase());
						break;
					}
					else if(instruction_Operator.get(instruction).equals(indeX_word+",X")) {
						String number = Integer.toHexString(Integer.valueOf(Integer.valueOf((sign_Loc.get(indeX_word)),16).toString())+Integer.valueOf(Integer.valueOf("8000",16).toString())).toUpperCase();
					    object_Codes.add(instruction_Opcodes.get(instruction.substring(start_Pos, end_Pos))+number);
					    break;
					}
					else{
						object_Codes.add(instruction_Opcodes.get(instruction.substring(start_Pos, end_Pos))+sign_Loc.get(instruction_Operator.get(instruction)));

						break;
					}
				}
			}
			inputdata = false;
		}
	}
	
	public ArrayList<String> getObject(){
		return object_Codes;
	}
}