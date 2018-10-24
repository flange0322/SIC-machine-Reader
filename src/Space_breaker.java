import java.util.ArrayList;
public class Space_breaker {
	
	//負責切割空白與enter,和判別切割的字串要丟指令、標記、運算元的哪個位置，以及日後標記所對應Loc位置做存取
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
	    	//最後一個測資的處理
	    	if(i == inputStr.length() - 1) {
	    		is_Space = true;
	    		end_Pos = i+1;
	    	}
	    	
	    	if(inputStr.charAt(i) == '\'') {
	    		byte_space = !byte_space;
	    	}
	    	//遇到Space的處理
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
	    	
	    	//字串有兩個以上的space處理方法
	    	if(count_Space > 1) {
	    		is_Space = false;
	    		start_Pos = end_Pos+1;
	    	}
	    	
	    	//儲存切割完的字串
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