package LineareGleichungen;
import java.util.ArrayList;

public class uebung {

	public static void main(String[] args) {
		LineareGleichung lg[] = new LineareGleichung[3];
		// TODO Auto-generated method stub
		ArrayList<Term> tl1 =new ArrayList<Term>();
		tl1.add(new Term(2.0,"x1"));
		tl1.add(new Term(1.0,"x2"));
		tl1.add(new Term(1.0,"x3"));

		lg[0] = new LineareGleichung(tl1, 1.0);

		ArrayList<Term> tl2 =new ArrayList<Term>();
		tl2.add(new Term(1.0,"x1"));
		tl2.add(new Term(-1.0,"x2"));
		tl2.add(new Term(1.0,"x3"));
		
		lg[1] = new LineareGleichung(tl2, 4.0);
		
		ArrayList<Term> tl3 =new ArrayList<Term>();
		tl3.add(new Term(3.0,"x1"));
		tl3.add(new Term(1,"x2"));
		tl3.add(new Term(2.0,"x3"));
		
		lg[2] = new LineareGleichung(tl3, -1.0);
		
		LinearesGleichungsSystem lgs = new LinearesGleichungsSystem (lg);
		
		lgs.printAll();
		lgs.Gauss();
		lgs.printAll();

		

		
		
		

	}

}
