import java.io.*;
public class Main {
	public static void main(String args[]) throws Exception{
		FileInputStream fileStream = new FileInputStream ("questions.txt");  
	    DataInputStream dis = new DataInputStream (fileStream);  
	  
	    byte[] datainBytes = new byte[dis.available()];  
	    dis.readFully(datainBytes);  
	    dis.close();  
	    
	    String inputStr = new String(datainBytes);
	    Space_breaker SB = new Space_breaker(inputStr);
	    Instruction_Operator IO = new Instruction_Operator(SB.getInstructionArrayList(),SB.getOperatorArrayList());
	    Loc_Arithmetic LA = new Loc_Arithmetic(IO.getMap().get(IO.getFirst_Loc()),IO.getMap());
	    Sign_Loc SL = new Sign_Loc(IO.getMap().get(IO.getFirst_Loc()),SB.getSignArrayList(),LA.getLoc(),SB.getPosArrayList());
	    for(int i=0;i<LA.getLoc().size();i++) {
	    	System.out.println(LA.getLoc().get(i));
	    }
	    
	    /*for(int i=0;i<SB.getSignArrayList().size();i++) {
    	System.out.println(SB.getSignArrayList().get(i));
    	}*/
	    
	    /*for(int i=0;i<SB.getInstructionArrayList().size();i++) {
	    	System.out.println(SB.getInstructionArrayList().get(i));
	    }*/
	    
	    /*for(int i=0;i<SB.getOperatorArrayList().size();i++) {
	    	System.out.println(SB.getOperatorArrayList().get(i));
        }*/
	    
	    /*for(String key : IO.getMap().keySet()) {
    	System.out.println(key+" "+IO.getMap().get(key));
        }*/
	    
	    /*for(String key : SL.getMap().keySet()) {
	    	System.out.println(key+" "+SL.getMap().get(key));
	    }*/
	    
	}
}
