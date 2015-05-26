import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame{
	
	private JPanel contentPane;
    private JPanel buttonPane;
    
    private JButton bTakeAbsence;
    private JButton bStudentList;
    private JButton bViewList;
    private JButton bStudentRecord;
    private JButton bExit;
    
    public MainGUI(){
    	contentPane = (JPanel)this.getContentPane();
        this.setTitle("MainWindow");
        
        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        bTakeAbsence = new JButton("Take Absence");
        buttonPane.add(bTakeAbsence);
        bStudentList = new JButton("Student List");
        buttonPane.add(bStudentList);
        bViewList = new JButton("View List");
        buttonPane.add(bViewList);
        bStudentRecord = new JButton("Student Record");
        buttonPane.add(bStudentRecord);
        bExit = new JButton("Exit");
        buttonPane.add(bExit);
        contentPane.add(buttonPane, BorderLayout.CENTER);
    }
}
