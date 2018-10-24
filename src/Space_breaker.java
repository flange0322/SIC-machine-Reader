import java.util.ArrayList;
public class Space_breaker {
	
	//�t�d���ΪťջPenter,�M�P�O���Ϊ��r��n����O�B�аO�B�B�⤸�����Ӧ�m�A�H�Τ��аO�ҹ���Loc��m���s��
	public ArrayList<Integer>Pos;
	public ArrayList<String>Sign;
    public ArrayList<String>Instruction;
    public ArrayList<String>Operator;
	public data_Library dl;
	public boolean is_Space = false;
	public boolean is_Instruction = false;
	public boolean is_Operator = false;
	public boolean is_Sign = false;
	public boolean input_door = true;
	public boolean byte_space = false;
	public int start_Pos = 0;
	public int end_Pos = 0;
	public int count_Space = 0;
	public int count_Point_Pass = 0;   
	public String inputStr;
	
	public Space_breaker(String inputStr) {
		this.inputStr = inputStr;
		format_handle();
	}
	
	public void format_handle() {
		Sign = new ArrayList<String>();
		Instruction = new ArrayList<String>();
		Operator = new ArrayList<String>();
		Pos = new ArrayList<Integer>();
		dl = new Instruction_Op();
		inputStr = inputStr.replaceAll("\r\n", " ");
	    
	    for(int i=0;i<inputStr.length();i++) {
	    	//�̫�@�Ӵ��ꪺ�B�z
	    	if(i == inputStr.length() - 1) {
	    		is_Space = true;
	    		end_Pos = i+1;
	    	}
	    	
	    	if(inputStr.charAt(i) == '\'') {
	    		byte_space = !byte_space;
	    	}
	    	//�J��Space���B�z
	    	if(inputStr.charAt(i) == ' ' && byte_space == false) {
	    		if(i == 0){
	    			start_Pos++;
	    			count_Space++;
	    		}
	    		else {
		    		is_Space = true;
		    		count_Space++;
		    		end_Pos = i;
	    		}
	    	}
	    	else {
	    		count_Space = 0;
	    	}
	    	
	    	//�r�꦳��ӥH�W��space�B�z��k
	    	if(count_Space > 1) {
	    		is_Space = false;
	    		start_Pos = end_Pos+1;
	    	}
	    	
	    	//�x�s���Χ����r��
	    	if(is_Space == true) {
	    		for(String instruction : dl.getMap().keySet()) {
	    			if(inputStr.substring(start_Pos, end_Pos).equals(instruction)) {
	    				is_Instruction = true;
	    			}
	    		}
	    		
	    		if(is_Instruction == true) {
	    			Instruction.add(inputStr.substring(start_Pos, end_Pos));
	    			is_Instruction = false;
	    			if(!inputStr.substring(start_Pos, end_Pos).equals("RSUB"))
	    				is_Operator = true;
	    		}
	    		else if(is_Instruction == false && is_Operator == true){
	    			Operator.add(inputStr.substring(start_Pos, end_Pos));
	    			is_Operator = false;
	    		}
	    		else{
	    			if(inputStr.substring(start_Pos, end_Pos).equals(".")) {
	    				count_Point_Pass++;
	    				input_door = false;
	    			}
	    			else if(input_door == true) {
	    				Sign.add(inputStr.substring(start_Pos, end_Pos));
	    				Pos.add(Instruction.size());
	    			}
	    			if(count_Point_Pass == 3) {
	    				input_door = true;
	    				count_Point_Pass = 0;
	    			}
	    		}
	    		start_Pos = end_Pos+1;
	    		is_Space = false;
	    	}
	    }
	}
	
	public ArrayList<String> getSignArrayList(){
		return Sign;
	}
	
	public ArrayList<String> getInstructionArrayList(){
		return Instruction;
	}
	
	public ArrayList<String> getOperatorArrayList(){
		return Operator;
	}	
	
	public ArrayList<Integer> getPosArrayList(){
		return Pos;
	}
}