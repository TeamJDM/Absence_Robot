import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class ViewListGUI extends JFrame{
    

    //private Absence absence;
    private StudentList studentList;
    //private ArrayList<Student> absentStudents;

    //private DefaultTableModel tableModel;

    private JPanel contentPane;
    private JPanel tablePane;
    private JPanel infoPanel;
    private JPanel buttonPane;

    //private ArrayList<JCheckBox> checkBoxes;
    private JComboBox unitsBox;
    private JButton bAdd;
    private JButton bDelete;
    private JButton bEdit;
    private JButton bBack;
    private JButton bLoad;

    public ViewListGUI(){

       // absentStudents = new ArrayList<Student>();
        //studentList = new StudentList(date.getYear());

        contentPane = (JPanel) this.getContentPane();
        contentPane.setPreferredSize(new Dimension(300,200));
        tablePane = new JPanel();
        this.setTitle("View List");

        infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout());
        unitsBox = new JComboBox();
        unitsBox.addItem("OOP");
        unitsBox.addItem("SAD");
        unitsBox.addItem("MATH");
        unitsBox.addItem("NETW");
        unitsBox.addItem("IS");
        infoPanel.add(unitsBox);
        
        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        bAdd = new JButton("Add");
        bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //bAdd_actionPerformed(e);
            }
        });
        buttonPane.add(bAdd);
        
        bDelete = new JButton("Delete");
        bDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // bDelete_actionPerformed(e);
            }
        });
        buttonPane.add(bDelete);
        
        bEdit = new JButton("Edit");
        bEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //bEdit_actionPerformed(e);
            }
        });
        buttonPane.add(bEdit);
        
        bLoad = new JButton("Load");
        bLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bLoad_actionPerformed(e);
            }
        });
        buttonPane.add(bLoad);
        
        bBack = new JButton("Back");
        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	MainGUI mg = new MainGUI();
            	mg.pack();
            	mg.setVisible(true);	
            }
        });
        buttonPane.add(bBack);

        contentPane.add(buttonPane, BorderLayout.SOUTH);
        contentPane.add(infoPanel, BorderLayout.NORTH);

    

    //public void bAdd_actionPerformed(ActionEvent e) {

    	//studentList.addElement("new");
    //}
    
    
    
    
    //*private void bSubmit_actionPerformed(ActionEvent e) {

        //for (JCheckBox jChB: checkBoxes){
          //  if (jChB.isSelected()){
        //        try {
       //             absentStudents.add(studentList.getStudentById(Integer.parseInt(studentList.getNameList().get(checkBoxes.indexOf(jChB))[0]))) ;
       //         } catch (NoStudentException e1) {
      //              e1.printStackTrace();
      //          }
     //       }
     //   }

      //  for (Student stud : studentList.getArrayOfStudents()) {
      //      if (absentStudents.contains(stud)) {
      //          if (stud.checkAbsenceLimit(this.unitsBox.getSelectedItem().toString()) < 7){
      //              stud.addAbsence(new Absence(date.toString(), unitsBox.getSelectedItem().toString()));

    //           }

       //     }
    //    }

  /*   try{
            FileOutputStream saveFile = new FileOutputStream("saveFile_" + this.unitsBox.getSelectedItem().toString()+ ".dat");
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            save.writeObject(studentList);
            save.close();
        }
        catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        JFrame f12 = new PreviewGUI(this.unitsBox.getSelectedItem().toString());
        f12.pack();
        f12.setVisible(true);
    }*/

    private void bLoad_actionPerformed(ActionEvent e) {
        try {
            FileInputStream saveFile = new FileInputStream("saveFile_" + this.unitsBox.getSelectedItem().toString()+ ".dat");
            ObjectInputStream save = new ObjectInputStream(saveFile);
            this.studentList = (StudentList) save.readObject();
            save.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
}
        tablePane.setLayout(new GridLayout(studentList.getNameList().size(), 2));
        int i = 0;
        for (Student s: studentList.getArrayOfStudents()){
            tablePane.add(new JLabel(String.valueOf(s.getId())));
            tablePane.add(new JLabel(s.getName()));
            i++;
        }

        contentPane.add(tablePane, BorderLayout.CENTER);
        //this.revalidate();
        this.repaint();
    }
}
