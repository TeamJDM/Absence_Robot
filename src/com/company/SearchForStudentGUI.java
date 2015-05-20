import javax.swing.*;
import java.awt.*;

public class MainGUI1 extends JFrame{
	
	private JPanel contentPane;
    private JPanel detailsPane;
    private JPanel buttonPane;

    private JLabel lName;

    private JTextField tName;

    private JButton bCancel;
    private JButton bSearch;
    
    public MainGUI1(){
    	contentPane = (JPanel)this.getContentPane();
        this.setTitle("SearchWindow");
        
        detailsPane = new JPanel();
        detailsPane.setLayout(new GridLayout(3,3));
        lName = new JLabel("Student Name");
        detailsPane.add(lName);
        tName = new JTextField();
        detailsPane.add(tName);
        
        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        bSearch = new JButton("Search");
        buttonPane.add(bSearch);
        bCancel = new JButton("Cancel");
        buttonPane.add(bCancel);
        
        contentPane.add(detailsPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.SOUTH);
    }
}

