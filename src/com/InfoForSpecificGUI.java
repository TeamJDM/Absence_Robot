import javax.swing.*;

import java.awt.*;

public class MainGUI1 extends JFrame{
	
	private JPanel contentPane;
    private JPanel detailsPane;
    private JPanel buttonPane;
    
    private JLabel lName;
    private JLabel lUnit;
    private JLabel lAbsences;
    private JLabel lTel;
    private JLabel lEmail;
    private JLabel lName1;
    private JLabel lUnit1;
    private JLabel lAbsences1;
    private JLabel lTel1;
    private JLabel lEmail1;
    private JButton bOk;
    
    
    public MainGUI1(){
    	contentPane = (JPanel)this.getContentPane();
        this.setTitle("SpecificStudentWindow");
        
        detailsPane = new JPanel();
        detailsPane.setLayout(new GridLayout(5,5));
        lName = new JLabel("Student Name");
        detailsPane.add(lName);
        lName1 = new JLabel("");
        detailsPane.add(lName1);
        lUnit = new JLabel("Unit");
        detailsPane.add(lUnit);
        lUnit1 = new JLabel("");
        detailsPane.add(lUnit1);
        lAbsences = new JLabel("Absences");
        detailsPane.add(lAbsences);
        lAbsences1 = new JLabel("");
        detailsPane.add(lAbsences1);
        lTel = new JLabel("Tel Number");
        detailsPane.add(lTel);
        lTel1 = new JLabel("");
        detailsPane.add(lTel1);
        lEmail = new JLabel("Email");
        detailsPane.add(lEmail);
        lEmail1 = new JLabel("");
        detailsPane.add(lEmail1);
        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        bOk = new JButton("OK");
        buttonPane.add(bOk);

        contentPane.add(detailsPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.SOUTH);
        
    }
}

