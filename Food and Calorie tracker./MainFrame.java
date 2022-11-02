import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

public class MainFrame extends JFrame {
    private static JTextField tSearch, tResult, tWeight, tTotal, textPersonWeight, textPersonHeight, textGoal,tBMI;
    protected static JFrame frame;
    private static JPanel pTop;
    private static JButton bSearch, bEnter, bClear, bClearSearch, buttonGo;
    private static JLabel lWeight, lTotal, labelPersonWeight, labelPersonHeight, labelGoal, labelBMI,lBMI;
    private static JList<String> leftList, rightList;
    private static JScrollPane leftScrollPane, rightScrollPane;
    protected static ArrayList<Edibles> EdiblesList;
    private static Scanner Reader = new Scanner(System.in);
    private static ArrayList<String> searchedList, chosenFood, foodList;
    private static Double result = 0.0;
    private static String[] allFood;

    public static void start() throws FileNotFoundException {
        bSearch = new JButton("\uD83D\uDD0D");
        frame = new JFrame("CalorieCounter");
        pTop = new JPanel();
        tSearch = new JTextField();
        tResult = new JTextField();
        tWeight = new JTextField();
        tTotal = new JTextField("0");
        textGoal = new JTextField();
        tBMI = new JTextField();
        textPersonHeight = new JTextField();
        textPersonWeight = new JTextField();
        bEnter = new JButton("Enter");
        bClear = new JButton("Clear");
        bClearSearch = new JButton("C");
        buttonGo = new JButton("Go");
        lTotal = new JLabel("Total (Kcal)");
        lWeight = new JLabel("weight in (g)");
        labelBMI = new JLabel("BMI Calculator:");
        labelGoal = new JLabel("Calorie goal");
        labelPersonHeight = new JLabel("height in (cm)");
        labelPersonWeight = new JLabel("weight in (Kg)");
        lBMI = new JLabel("BMI");
        JSeparator line = new JSeparator();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(600, 600);
        frame.setLocation((int) ((screenSize.getWidth() / 2 - frame.getSize().getWidth() / 2)), (int) (screenSize.getHeight() / 2 - frame.getSize().getHeight() / 2));
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // buttons
        bSearch.addActionListener(new CustomActionListener());
        bSearch.setLocation(325, 26);
        bSearch.setSize(50, 30);
        bSearch.setFocusable(false);
        bClearSearch.addActionListener(new CustomActionListener());
        bClearSearch.setLocation(325, 56);
        bClearSearch.setSize(50, 30);
        bClearSearch.setFocusable(false);
        bEnter.addActionListener(new CustomActionListener());
        bEnter.setLocation(250, 220);
        bEnter.setSize(65, 30);
        bEnter.setFocusable(false);
        bClear.addActionListener(new CustomActionListener());
        bClear.setLocation(500, 500);
        bClear.setSize(65, 30);
        bClear.setFocusable(false);
        buttonGo.setLocation(250,420);
        buttonGo.setSize(65,30);
        buttonGo.setFocusable(false);
        buttonGo.addActionListener(new CustomActionListener());

        // textFields
        tSearch.setLocation(206, 26);
        tSearch.setSize(120, 30);
        tResult.setLocation(206, 120);
        tResult.setSize(170, 40);
        tResult.setEditable(false);
        tWeight.setLocation(290, 180);
        tWeight.setSize(85, 30);
        tTotal.setLocation(470, 460);
        tTotal.setSize(100, 30);
        tTotal.setEditable(false);
        textPersonWeight.setLocation(290,360);
        textPersonWeight.setSize(70,25);
        textPersonHeight.setLocation(290,390);
        textPersonHeight.setSize(70,25);
        textGoal.setLocation(290,500);
        textGoal.setSize(70,25);
        textGoal.setEditable(false);
        tBMI.setLocation(290,460);
        tBMI.setSize(70,25);
        tBMI.setEditable(false);


        // labels
        lWeight.setLocation(200, 180);
        lWeight.setSize(80, 30);
        lTotal.setSize(70, 30);
        lTotal.setLocation(400, 460);
        labelBMI.setLocation(240,320);
        labelBMI.setSize(100,20);
        labelPersonWeight.setLocation(200,360);
        labelPersonWeight.setSize(110,20);
        labelPersonHeight.setLocation(200,390);
        labelPersonHeight.setSize(110,20);
        labelGoal.setLocation(200,500);
        labelGoal.setSize(110,20);
        lBMI.setLocation(200,460);
        lBMI.setSize(110,20);
        line.setLocation(140,290);
        line.setSize(300,20);
        line.setBackground(Color.BLACK);
        // lists
        addFood();
        LoadEdibles();

        // adding everything to the panel then adding the panel to the frame.
        pTop.setLayout(null);
        pTop.add(bClear);
        pTop.add(bClearSearch);
        pTop.add(bEnter);
        pTop.add(bSearch);
        pTop.add(buttonGo);
        pTop.add(tSearch);
        pTop.add(tResult);
        pTop.add(tWeight);
        pTop.add(tTotal);
        pTop.add(textGoal);
        pTop.add(tBMI);
        pTop.add(textPersonHeight);
        pTop.add(textPersonWeight);
        pTop.add(lWeight);
        pTop.add(lTotal);
        pTop.add(labelBMI);
        pTop.add(labelGoal);
        pTop.add(labelPersonHeight);
        pTop.add(labelPersonWeight);
        pTop.add(lBMI);
        pTop.add(line);
        frame.add(pTop);
    }

    public static void LoadEdibles() throws FileNotFoundException {
        EdiblesList = new ArrayList<>();
        leftList = new JList<String>();
        searchedList = new ArrayList<String>();
        File food = new File("C:\\Users\\abraa\\Desktop\\intro\\TermProject\\csci141-project-Abraam00\\res\\food.txt");
        foodList = new ArrayList<String>();
        leftList.addListSelectionListener(new ListSelectionListener());
        leftScrollPane = new JScrollPane();
        leftScrollPane.setViewportView(leftList);
        leftList.setLayoutOrientation(JList.VERTICAL);
        leftScrollPane.setLocation(5, 5);
        leftScrollPane.setSize(150, 540);
        pTop.add(leftScrollPane);
        Reader = new Scanner(food);
        int i = 0;
        String name = "";
        Double cal;
        while (Reader.hasNextLine()) {
            if (i % 2 == 0) {
                name = Reader.nextLine();
                foodList.add(name);
            } else {
                cal = Double.valueOf(Reader.nextLine());
                EdiblesList.add(new Edibles(name, cal));
            }
            i++;
        }
        allFood = new String[foodList.size()];
        leftList.setListData(foodList.toArray(allFood));
    }

    public static void addFood() {
        chosenFood = new ArrayList<>();
        rightList = new JList<String>();
        rightScrollPane = new JScrollPane();
        rightList.setLayoutOrientation(JList.VERTICAL);
        rightScrollPane.setLocation(430, 10);
        rightScrollPane.setSize(150, 440);
        pTop.add(rightScrollPane);
        pTop.add(rightList);
    }

    static class CustomActionListener extends Component implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonGo){
                try{
                    Double.valueOf(textPersonWeight.getText());
                    Double.valueOf(textPersonHeight.getText());
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Please enter numbers!", "Error Message", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Double BMI = (Math.abs(Double.valueOf(textPersonWeight.getText())) / Math.abs(Math.pow(Double.valueOf(textPersonHeight.getText()),2))) * 10000;
                String res = String.format("%.2f",BMI);
                tBMI.setText(res);
                if(BMI < 18.5) {
                    JOptionPane.showMessageDialog(null, "Based on your BMI, you are underweight! With no exercising required, a 1800 calorie per day diet is recommended to help you maintain your weight.");
                    textGoal.setText("1800");
                }
                else if (BMI > 18.5 && BMI < 24.9){
                    JOptionPane.showMessageDialog(null, "Based on your BMI, you are normal weight! With no exercising required, a 2100 calorie per day diet is recommended to help you maintain your weight.");
                    textGoal.setText("2100");
                }
                else if (BMI > 24.9 && BMI < 29.9){
                    JOptionPane.showMessageDialog(null, "Based on your BMI, you are overweight! With no exercising required, a 2300 calorie per day diet is recommended to help you maintain your weight.");
                    textGoal.setText("2300");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Based on your BMI, you are obese! With no exercising required, a 2600 calorie per day diet is recommended to help you maintain your weight.");
                    textGoal.setText("2600");
                }
            }
            if (e.getSource() == bClear) {
                try {
                    File file = new File("records.txt");
                    FileWriter fw = new FileWriter(file, true);
                    PrintWriter pw = new PrintWriter(fw);
                    pw.print(chosenFood+ " = (" + tTotal.getText() + ") Kcal" + "\n");
                    pw.close();
                } catch (IOException ex) {
                }
                tTotal.setText("0");
                chosenFood.removeAll(chosenFood);
                String[] selection = new String[chosenFood.size()];
                rightList.setListData(chosenFood.toArray(selection));
                result = 0.0;

            }
            if (e.getSource() == bClearSearch) {
                tSearch.setText("");
                leftList.setListData(foodList.toArray(allFood));
            }
            if (e.getSource() == bSearch) {
                if (!tSearch.getText().trim().equals("")) {
                    searchedList.removeAll(searchedList);
                    for (int i = 0; i < EdiblesList.size(); i++) {
                        if (EdiblesList.get(i).getName().contains(tSearch.getText().toLowerCase().trim())) {
                            searchedList.add(EdiblesList.get(i).getName());
                        }
                    }
                    String[] arr = new String[searchedList.size()];
                    leftList.setListData(searchedList.toArray(arr));
                }
            }
            Edibles temp = null;
            if (e.getSource() == bEnter) {
                if (leftList.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(null, "Please select a food item!", "Error Message", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Double.valueOf(tWeight.getText());
                } catch (Exception Ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a number!", "Error Message", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                for (int i = 0; i < EdiblesList.size(); i++) {
                    if (EdiblesList.get(i).getName().equals(leftList.getSelectedValue())) {
                        temp = EdiblesList.get(i);
                        result += temp.getCalories() * Math.abs(Double.valueOf(tWeight.getText()));
                    }
                }
                chosenFood.add(leftList.getSelectedValue() + "  " + String.format("%.2f", temp.getCalories() * Math.abs((Double.valueOf(tWeight.getText()))))+" Kcal");
                String[] selection = new String[chosenFood.size()];
                rightList.setListData(chosenFood.toArray(selection));
                rightScrollPane.setViewportView(rightList);
                tTotal.setText(String.valueOf(String.format("%.2f", result)));
                tWeight.setText("");
                tResult.setText("");
                leftList.setListData(foodList.toArray(allFood));
            }
        }
    }

    static class ListSelectionListener implements javax.swing.event.ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getSource() == (leftList)) {
                String choice = leftList.getSelectedValue();
                int index = leftList.getSelectedIndex();
                if (index != -1) {
                    for (int i = 0; i < EdiblesList.size(); i++) {
                        if (EdiblesList.get(i).getName().equals(choice)) {
                            tResult.setText(String.valueOf(EdiblesList.get(i).getCalories()) + "  cal/g");
                        }
                    }
                }
            }
        }
    }
}
