package currencyconverter;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * App creates a pop up calculator for currency conversion.
 * 
 * @author Jackson Splinter
 */
public class CurrencyConverter extends javax.swing.JOptionPane
{

    public static double convert(double currency1,double curr1converter, double curr2converter)
    {
        return (currency1/curr1converter)*curr2converter;
    }
    
    public static HashMap<String,Double> readFile(File file) throws FileNotFoundException, IOException
    {
        HashMap<String,Double> ans = new HashMap<>();
        // create BufferedReader object from the File
        BufferedReader br = new BufferedReader(new FileReader(file));
  
        String line;
        
        // read file line by line
        while ((line = br.readLine()) != null) {
            // split the line by :
            String[] parts = line.split(":");
            
            System.out.println(parts[0]+" "+parts[1]);
            // first part is name, second is number
            String name = parts[0].trim();
            double number = Double.valueOf(parts[1].trim());
            
            // put name, number in HashMap if they are
            // not empty
            if (!name.equals(""))
                ans.put(name, number);
        }
        br.close();
        return ans;
    }
    
    public static String[] getTypes(HashMap<String,Double> map)
    {
        String[] ans = new String[map.size()];
        int counter= 0;
        for(String i: map.keySet())
        {
            ans[counter]=i;
            counter++;
        }
        return ans;
    }
    /**
     * 
     * @param args  Command line arguments
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        File file = new File("Currency_Rates.txt");
        HashMap<String,Double> map = readFile(file);
        String[] possibilities = getTypes(map);
        JTextField currval1 = new JTextField(10);
        JTextField currval2 = new JTextField(10);
        JComboBox currType1 = new JComboBox(possibilities);
        JComboBox currType2 = new JComboBox(possibilities);
        JPanel myPanel = new JPanel();
        JPanel row2 = new JPanel();
        JPanel row1 = new JPanel();
        myPanel.setLayout(new GridLayout(0, 1));
        row1.add(currval1);
        row1.add(currType1);
        row2.add(currval2);
        row2.add(currType2);
        myPanel.add(row1);
        myPanel.add(row2);
        currval1.setText("1");
        
        int result=JOptionPane.OK_OPTION;
        while(result!= JOptionPane.CANCEL_OPTION)
        {
        result = JOptionPane.showConfirmDialog(null, myPanel, 
                "Currency Calculator", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            currval2.setText(String.valueOf(convert(
                            Double.valueOf(currval1.getText()),
                            map.get(currType1.getSelectedItem()),
                            map.get(currType2.getSelectedItem())
                    )));
        }
        }
    }
}