
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame{
	
    protected JPanel contentPaneAll;
    private JPanel mainGuiPane;
    private JPanel buttonPane;
    //private StudentGUI studentAddPanel;
    //private JPanel unitPanel;
    
    private JButton bTakeAbsence;
    private JButton bStudentList;
    private JButton bViewList;
    private JButton bStudentRecord;
    private JButton bExit;


    
    public MainGUI(){
<<<<<<< HEAD
        //unitPanel = new UnitGUI();
    	contentPaneAll = (JPanel)this.getContentPane();
=======
    	contentPane = (JPanel)this.getContentPane();
    	contentPane.setPreferredSize(new Dimension(350,350));
>>>>>>> origin/master
        this.setTitle("MainWindow");
        
        buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayout(5,1));
        
        bTakeAbsence = new JButton("Take Absence");
        bTakeAbsence.setBackground(Color.GRAY);
        bTakeAbsence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	bTakeAbsence_actionPerformed(e);
            }
        });
        buttonPane.add(bTakeAbsence);
        bStudentList = new JButton("Student List");
        bStudentList.setBackground(Color.CYAN);
        buttonPane.add(bStudentList);
        bStudentList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	bStudentList_actionPerformed(e);
            }
        });
        bViewList = new JButton("View List");
        bViewList.setBackground(Color.GRAY);
        buttonPane.add(bViewList);
        bViewList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	bViewList_actionPerformed(e);
            }
        });
        bStudentRecord = new JButton("Student Record");
        bStudentRecord.setBackground(Color.CYAN);
        buttonPane.add(bStudentRecord);
        bStudentRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	bStudentRecord_actionPerformed(e);
            }
        });
        bExit = new JButton("Exit");
        bExit.setBackground(Color.GRAY);
        buttonPane.add(bExit);
<<<<<<< HEAD
        mainGuiPane = new JPanel();
        mainGuiPane.add(buttonPane, BorderLayout.CENTER);
        contentPaneAll.add(mainGuiPane);
=======
        contentPane.add(buttonPane, BorderLayout.CENTER);
        bExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            }
        });
>>>>>>> origin/master
    }

    private void bTakeAbsence_actionPerformed(ActionEvent e) {
      	JFrame takeAbsenceFrame = new TakeAbsenceGUI();
        takeAbsenceFrame.pack();
        takeAbsenceFrame.setVisible(true);
    }

    public void bStudentList_actionPerformed(ActionEvent e){
        JFrame unitGui = new UnitGUI();
        unitGui.pack();
        unitGui.setVisible(true);
    }
    
    public void bViewList_actionPerformed(ActionEvent e){
      //JFrame absenceGui  = new AbsenceGUI();
      //absenceGui.pack();
      //absenceGui.setVisible(true);
    }
    
    public void bStudentRecord_actionPerformed(ActionEvent e){
        JFrame unitGui  = new StudentRecordGUI();
        unitGui.pack();
        unitGui.setVisible(true);
    }
    
    public void bExit_actionPerformed(ActionEvent e){
        JFrame unitGui  = new JFrame();
        unitGui.pack();
        unitGui.setVisible(true);
    }
}
