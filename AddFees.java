/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fees_management_system;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class AddFees extends JFrame {

    /**
     * Creates new form AddFees
     */
    public AddFees() {
        initComponents();
        displayCashFirst();
        fillComboBox();
        
        int receiptNo=getReceiptNo();
        txt_receiptNo.setText(Integer.toString(receiptNo));
        
        
        
    }
    public void displayCashFirst()
    {
        lbl_DDno.setVisible(false);
        lbl_chequeNo.setVisible(false);
        lbl_bankName.setVisible(false);
        txt_bankName.setVisible(false);
        
        txt_DDNo.setVisible(false);
        txt_ChequeNo.setVisible(false);
        txt_rollNo.setVisible(true);
    }
    public boolean validation()
    {
        
        if(txt_receivedFrom.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "please enter user name");
            return false;
        }
        if(dateChooser.getDate()==null)
        {
            JOptionPane.showMessageDialog(this, "please select a date");
            return false;
            
        }
         if(txt_amount.getText().equals("") || txt_amount.getText().matches("[0-9]+")==false)
        {
            JOptionPane.showMessageDialog(this, "please enter amount(in numbers)");
            return false;
            }
         if(combo_PaymentMode.getSelectedItem().toString().equalsIgnoreCase("Cheque"))
         {
             if(txt_ChequeNo.getText().equals(""))
             {
                 JOptionPane.showMessageDialog(this, "please enter cheque number");
            return false;
             }
             if(txt_rollNo.getText().equals(""))
             {
                 JOptionPane.showMessageDialog(this, "please enter bank name");
            return false;
             }
             
         }
         if(combo_PaymentMode.getSelectedItem().toString().equalsIgnoreCase("dd"))
         {
             if(txt_DDNo.getText().equals(""))
             {
              JOptionPane.showMessageDialog(this, "please enter dd no");
            return false;
         }
              if(txt_rollNo.getText().equals(""))
             {
                 JOptionPane.showMessageDialog(this, "please enter bank name");
            return false;
             }
         }
         if(combo_PaymentMode.getSelectedItem().toString().equalsIgnoreCase("card"))
         {
              if(txt_rollNo.getText().equals(""))
             {
                 JOptionPane.showMessageDialog(this, "please enter bank name");
            return false;
             }
         }
             
        return true;
    }
    
    public void fillComboBox()
    {
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/fees_management","root","root");
            PreparedStatement pst=con.prepareStatement("select cname from course");
            ResultSet rs= pst.executeQuery();
            
            while(rs.next())
            {
                comboGames.addItem(rs.getString("cname"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
    }
public int getReceiptNo()
{
    int receiptNo=0;
    try{
        Connection con= DBConnection.getConnection();
        PreparedStatement pst=con.prepareStatement("select max(reciept_no) from fees_Details");
        ResultSet rs= pst.executeQuery();
        
        if(rs.next()== true)
        {
           receiptNo= rs.getInt(1);
        }
        
    }
    catch(Exception e)
    {
       e.printStackTrace();
    }
    return receiptNo+1;
}
    
public String insertData()
{
    String status="";
    int recieptNo=Integer.parseInt(txt_receiptNo.getText());
    String studentName=txt_receivedFrom.getText();
    String rollNo=txt_rollNo.getText();
    String paymentMode=combo_PaymentMode.getSelectedItem().toString();
    String chequeNo=txt_ChequeNo.getText();
    String bankName=txt_bankName.getText();
    String ddNo=txt_DDNo.getText();
    String courseName=txt_GamesName.getText();
    String gstin=txt_GSTNo.getText();
    Float totalAmount=Float.parseFloat(txt_total.getText());
    
    SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
    String date= dateFormat.format(dateChooser.getDate());
    
    Float initialAmount=Float.parseFloat(txt_amount.getText());
     Float cgst=Float.parseFloat(txt_cgst.getText());
      Float sgst=Float.parseFloat(txt_sgst.getText());
      String totalInWords=txt_total_words.getText();
      String remarks=txt_remark.getText();
      int year1=Integer.parseInt(txt_year1.getText());
      int year2=Integer.parseInt(txt_year2.getText());
      
      
      try
      {
        Connection con= DBConnection.getConnection();
        PreparedStatement pst=con.prepareStatement("insert into fees_details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setInt(1, recieptNo);
        pst.setString(2, studentName);
        pst.setString(3, rollNo );
        pst.setString(4, paymentMode);
        pst.setString(5, chequeNo);
        pst.setString(6, bankName);
        pst.setString(7, ddNo);
        pst.setString(8, courseName);
        pst.setString(9, gstin);
        pst.setFloat(10, totalAmount);
        pst.setString(11, date);
        pst.setFloat(12, initialAmount);
        pst.setFloat(13, cgst);
        pst.setFloat(14, sgst);
        pst.setString(15, totalInWords);
        pst.setString(16, remarks);
        pst.setInt(17, year1);
        pst.setInt(18, year2);
        
        
        int rowCount=pst.executeUpdate();
        
        if(rowCount==1)
        {
            status="Success";
        }
        else
        {
            status="failed";
        }
        
        
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    
    return status;
    
}
    






/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelParent = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_GSTNo = new javax.swing.JLabel();
        lbl_DDno = new javax.swing.JLabel();
        lbl_chequeNo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_receiptNo = new javax.swing.JTextField();
        txt_DDNo = new javax.swing.JTextField();
        combo_PaymentMode = new javax.swing.JComboBox();
        dateChooser = new com.toedter.calendar.JDateChooser();
        panelChild = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_year2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_total_words = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        comboGames = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_year1 = new javax.swing.JTextField();
        txt_GamesName = new javax.swing.JTextField();
        txt_amount = new javax.swing.JTextField();
        txt_cgst = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        txt_sgst = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_remark = new javax.swing.JTextArea();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        btn_print = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txt_receivedFrom = new javax.swing.JTextField();
        txt_rollNo = new javax.swing.JTextField();
        lbl_bankName = new javax.swing.JLabel();
        txt_ChequeNo = new javax.swing.JTextField();
        txt_bankName = new javax.swing.JTextField();
        panelsideBar = new javax.swing.JPanel();
        panelHome = new javax.swing.JPanel();
        btnHome = new javax.swing.JLabel();
        panelSearch = new javax.swing.JPanel();
        btnSearch = new javax.swing.JLabel();
        panelEdit = new javax.swing.JPanel();
        btnEdit = new javax.swing.JLabel();
        panelGamesList = new javax.swing.JPanel();
        btnGamesList = new javax.swing.JLabel();
        panelViewAllRecords = new javax.swing.JPanel();
        btnViewAllRecord = new javax.swing.JLabel();
        panelBack = new javax.swing.JPanel();
        btnBack = new javax.swing.JLabel();
        panelLogout = new javax.swing.JPanel();
        btnLogout = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelParent.setBackground(new java.awt.Color(51, 255, 204));
        panelParent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Receipt No : MMAA-");
        panelParent.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel3.setText("Mode of Payment :");
        panelParent.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        txt_GSTNo.setText("22HVSJH55");
        panelParent.add(txt_GSTNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, -1, -1));

        lbl_DDno.setText("DD No :");
        panelParent.add(lbl_DDno, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        lbl_chequeNo.setText("Cheque No :");
        panelParent.add(lbl_chequeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabel8.setText("Date :");
        panelParent.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, -1, -1));

        jLabel9.setText("GSTIN :");
        panelParent.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, -1, -1));

        txt_receiptNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_receiptNoActionPerformed(evt);
            }
        });
        panelParent.add(txt_receiptNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 120, 30));

        txt_DDNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_DDNoActionPerformed(evt);
            }
        });
        panelParent.add(txt_DDNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 120, 30));

        combo_PaymentMode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DD", "Cheque", "Cash", "Card" }));
        combo_PaymentMode.setSelectedIndex(2);
        combo_PaymentMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_PaymentModeActionPerformed(evt);
            }
        });
        panelParent.add(combo_PaymentMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 120, 30));
        panelParent.add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 140, 30));

        panelChild.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("The following payments MMAA for the year :");
        panelChild.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 290, -1));

        jLabel10.setText("Reciever Signature");
        panelChild.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 390, 170, -1));

        txt_year2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_year2ActionPerformed(evt);
            }
        });
        panelChild.add(txt_year2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 70, 30));

        jLabel11.setText("to");
        panelChild.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 40, 20));

        txt_total_words.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_total_wordsActionPerformed(evt);
            }
        });
        panelChild.add(txt_total_words, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 440, 30));

        jLabel12.setText("Received Form :");
        panelChild.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        comboGames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboGamesActionPerformed(evt);
            }
        });
        panelChild.add(comboGames, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 120, 30));

        jLabel13.setText("Head");
        panelChild.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, -1, 30));
        panelChild.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 380, 250, 20));
        panelChild.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 1180, 20));

        jLabel14.setText("Remarks :");
        panelChild.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 120, 40));

        jLabel15.setText("SL.No");
        panelChild.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabel16.setText("Roll No :");
        panelChild.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, 90, -1));

        txt_year1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_year1ActionPerformed(evt);
            }
        });
        panelChild.add(txt_year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 70, 30));

        txt_GamesName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_GamesNameActionPerformed(evt);
            }
        });
        panelChild.add(txt_GamesName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 320, 30));

        txt_amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_amountActionPerformed(evt);
            }
        });
        panelChild.add(txt_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 160, 200, 30));

        txt_cgst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cgstActionPerformed(evt);
            }
        });
        panelChild.add(txt_cgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 200, 200, 30));
        panelChild.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 1180, 20));

        txt_sgst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sgstActionPerformed(evt);
            }
        });
        panelChild.add(txt_sgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 240, 200, 30));

        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });
        panelChild.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, 200, 30));

        jLabel17.setText("Games :");
        panelChild.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jLabel18.setText("Total in words :");
        panelChild.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 120, -1));

        txt_remark.setColumns(20);
        txt_remark.setRows(5);
        jScrollPane1.setViewportView(txt_remark);

        panelChild.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 400, 90));
        panelChild.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 280, 350, 50));

        jLabel19.setText("Amount");
        panelChild.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 120, 90, 30));

        btn_print.setText("Print");
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        panelChild.add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 430, 80, 40));

        jLabel1.setText("SGST 9%");
        panelChild.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, -1, -1));

        jLabel21.setText("CGST 9%");
        panelChild.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 204, -1, 30));

        txt_receivedFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_receivedFromActionPerformed(evt);
            }
        });
        panelChild.add(txt_receivedFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 290, 30));

        txt_rollNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_rollNoActionPerformed(evt);
            }
        });
        panelChild.add(txt_rollNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, 120, 30));

        panelParent.add(panelChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 1310, 840));

        lbl_bankName.setText(" Bank Name :");
        panelParent.add(lbl_bankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        txt_ChequeNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ChequeNoActionPerformed(evt);
            }
        });
        panelParent.add(txt_ChequeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 120, 30));

        txt_bankName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bankNameActionPerformed(evt);
            }
        });
        panelParent.add(txt_bankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 120, 30));

        getContentPane().add(panelParent, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 1620, 1040));

        panelsideBar.setBackground(new java.awt.Color(0, 102, 102));
        panelsideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelHome.setBackground(new java.awt.Color(0, 102, 102));
        panelHome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelHome.setEnabled(false);
        panelHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelHomeMouseExited(evt);
            }
        });

        btnHome.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/home.png"))); // NOI18N
        btnHome.setText("  Home");
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHomeMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelHomeLayout = new javax.swing.GroupLayout(panelHome);
        panelHome.setLayout(panelHomeLayout);
        panelHomeLayout.setHorizontalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHomeLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        panelHomeLayout.setVerticalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHomeLayout.createSequentialGroup()
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        panelsideBar.add(panelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, 70));

        panelSearch.setBackground(new java.awt.Color(0, 102, 102));
        panelSearch.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelSearch.setEnabled(false);
        panelSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelSearchMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelSearchMouseExited(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/search2.png"))); // NOI18N
        btnSearch.setText("Search Record");
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSearchMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout panelSearchLayout = new javax.swing.GroupLayout(panelSearch);
        panelSearch.setLayout(panelSearchLayout);
        panelSearchLayout.setHorizontalGroup(
            panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        panelSearchLayout.setVerticalGroup(
            panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
        );

        panelsideBar.add(panelSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        panelEdit.setBackground(new java.awt.Color(0, 102, 102));
        panelEdit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelEdit.setEnabled(false);
        panelEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelEditMouseExited(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/edit2.png"))); // NOI18N
        btnEdit.setText(" Edit Games");
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout panelEditLayout = new javax.swing.GroupLayout(panelEdit);
        panelEdit.setLayout(panelEditLayout);
        panelEditLayout.setHorizontalGroup(
            panelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        panelEditLayout.setVerticalGroup(
            panelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        panelsideBar.add(panelEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        panelGamesList.setBackground(new java.awt.Color(0, 102, 102));
        panelGamesList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelGamesList.setEnabled(false);
        panelGamesList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelGamesListMouseExited(evt);
            }
        });

        btnGamesList.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnGamesList.setForeground(new java.awt.Color(255, 255, 255));
        btnGamesList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/list_1.png"))); // NOI18N
        btnGamesList.setText("Games List");
        btnGamesList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGamesListMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout panelGamesListLayout = new javax.swing.GroupLayout(panelGamesList);
        panelGamesList.setLayout(panelGamesListLayout);
        panelGamesListLayout.setHorizontalGroup(
            panelGamesListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGamesListLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnGamesList, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        panelGamesListLayout.setVerticalGroup(
            panelGamesListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnGamesList, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        panelsideBar.add(panelGamesList, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        panelViewAllRecords.setBackground(new java.awt.Color(0, 102, 102));
        panelViewAllRecords.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelViewAllRecords.setEnabled(false);
        panelViewAllRecords.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelViewAllRecordsMouseExited(evt);
            }
        });

        btnViewAllRecord.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnViewAllRecord.setForeground(new java.awt.Color(255, 255, 255));
        btnViewAllRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/view all record.png"))); // NOI18N
        btnViewAllRecord.setText("View All Record ");
        btnViewAllRecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnViewAllRecordMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout panelViewAllRecordsLayout = new javax.swing.GroupLayout(panelViewAllRecords);
        panelViewAllRecords.setLayout(panelViewAllRecordsLayout);
        panelViewAllRecordsLayout.setHorizontalGroup(
            panelViewAllRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewAllRecordsLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnViewAllRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        panelViewAllRecordsLayout.setVerticalGroup(
            panelViewAllRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnViewAllRecord, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        panelsideBar.add(panelViewAllRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, -1, -1));

        panelBack.setBackground(new java.awt.Color(0, 102, 102));
        panelBack.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelBack.setEnabled(false);
        panelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBackMouseExited(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/left-arrow.png"))); // NOI18N
        btnBack.setText("Back");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBackMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout panelBackLayout = new javax.swing.GroupLayout(panelBack);
        panelBack.setLayout(panelBackLayout);
        panelBackLayout.setHorizontalGroup(
            panelBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );
        panelBackLayout.setVerticalGroup(
            panelBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBackLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelsideBar.add(panelBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, -1, -1));

        panelLogout.setBackground(new java.awt.Color(0, 102, 102));
        panelLogout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelLogout.setEnabled(false);
        panelLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelLogoutMouseExited(evt);
            }
        });

        btnLogout.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/logout.png"))); // NOI18N
        btnLogout.setText(" Logout");
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogoutMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout panelLogoutLayout = new javax.swing.GroupLayout(panelLogout);
        panelLogout.setLayout(panelLogoutLayout);
        panelLogoutLayout.setHorizontalGroup(
            panelLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogoutLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );
        panelLogoutLayout.setVerticalGroup(
            panelLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogoutLayout.createSequentialGroup()
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        panelsideBar.add(panelLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 610, -1, -1));

        getContentPane().add(panelsideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 1040));
        panelsideBar.getAccessibleContext().setAccessibleDescription("");

        setSize(new java.awt.Dimension(2037, 1073));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseEntered

        Color clr=new Color(0,153,153);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_btnHomeMouseEntered

    private void panelHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHomeMouseExited
       
        Color clr=new Color(0,153,153);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_panelHomeMouseExited

    private void btnSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchMouseEntered

    private void panelSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchMouseExited
        
    }//GEN-LAST:event_panelSearchMouseExited

    private void btnEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditMouseEntered

    private void panelEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_panelEditMouseExited

    private void btnGamesListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGamesListMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGamesListMouseEntered

    private void panelGamesListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelGamesListMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_panelGamesListMouseExited

    private void btnViewAllRecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecordMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewAllRecordMouseEntered

    private void panelViewAllRecordsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViewAllRecordsMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_panelViewAllRecordsMouseExited

    private void btnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackMouseEntered

    private void panelBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBackMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_panelBackMouseExited

    private void btnLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLogoutMouseEntered

    private void panelLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogoutMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_panelLogoutMouseExited

    private void btnHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseExited
Color clr=new Color(0,103,103);
        panelHome.setBackground(clr);        
    }//GEN-LAST:event_btnHomeMouseExited

    private void panelSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchMouseClicked
        
    }//GEN-LAST:event_panelSearchMouseClicked

    private void txt_receivedFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_receivedFromActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_receivedFromActionPerformed

    private void txt_receiptNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_receiptNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_receiptNoActionPerformed

    private void txt_DDNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_DDNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_DDNoActionPerformed

    private void txt_year2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_year2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_year2ActionPerformed

    private void txt_total_wordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_total_wordsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_total_wordsActionPerformed

    private void txt_year1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_year1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_year1ActionPerformed

    private void txt_GamesNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_GamesNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_GamesNameActionPerformed

    private void txt_amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_amountActionPerformed
       
      Float amnt=Float.parseFloat(txt_amount.getText());
      
      Float cgst=(float)(amnt * 0.09);
      Float sgst=(float)(amnt * 0.09);
      
      txt_cgst.setText(cgst.toString());
      txt_sgst.setText(sgst.toString());
      
      
        
          float total= amnt + cgst + sgst;
          
          txt_total.setText(Float.toString(total));
          
          txt_total_words.setText(NumberToWordsConverter.convert((int)total)+"only");
    }//GEN-LAST:event_txt_amountActionPerformed

    private void txt_cgstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cgstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cgstActionPerformed

    private void txt_sgstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sgstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sgstActionPerformed

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void txt_rollNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_rollNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rollNoActionPerformed

    private void txt_ChequeNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ChequeNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ChequeNoActionPerformed

    private void combo_PaymentModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_PaymentModeActionPerformed
      if(combo_PaymentMode.getSelectedIndex()== 0)
      {
          lbl_DDno.setVisible(true);
          txt_DDNo.setVisible(true);
          lbl_chequeNo.setVisible(false);
          txt_ChequeNo.setVisible(false);
          lbl_bankName.setVisible(true);
          txt_bankName.setVisible(true);
      }
      if(combo_PaymentMode.getSelectedIndex()== 1)
      {
          lbl_DDno.setVisible(false);
          txt_DDNo.setVisible(false);
          lbl_chequeNo.setVisible(true);
          txt_ChequeNo.setVisible(true);
          lbl_bankName.setVisible(true);
          txt_bankName.setVisible(true);
      }
      if(combo_PaymentMode.getSelectedIndex()== 2)
      {
          lbl_DDno.setVisible(false);
          txt_DDNo.setVisible(false);
          lbl_chequeNo.setVisible(false);
          txt_ChequeNo.setVisible(false);
          lbl_bankName.setVisible(false);
          txt_bankName.setVisible(false);
      }
      if(combo_PaymentMode.getSelectedItem().equals("Card"))
      {
          lbl_DDno.setVisible(false);
          txt_DDNo.setVisible(false);
          lbl_chequeNo.setVisible(false);
          txt_ChequeNo.setVisible(false);
          lbl_bankName.setVisible(true);
          txt_bankName.setVisible(true);
      }
    }//GEN-LAST:event_combo_PaymentModeActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        if(validation()==true)
        {
            String result=insertData();
            if(result.equals("success"))
            {
                JOptionPane.showMessageDialog(this, "record inserted succesfully");
                
            }
            else
            {
                JOptionPane.showMessageDialog(this, "record inserted");
                PrintReceipt p= new PrintReceipt();
                p.setVisible(true);
                this.dispose();
            }
        }
    }//GEN-LAST:event_btn_printActionPerformed

    private void comboGamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboGamesActionPerformed
        txt_GamesName.setText(comboGames.getSelectedItem().toString());
    }//GEN-LAST:event_comboGamesActionPerformed

    private void txt_bankNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bankNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bankNameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddFees().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnEdit;
    private javax.swing.JLabel btnGamesList;
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnSearch;
    private javax.swing.JLabel btnViewAllRecord;
    private javax.swing.JButton btn_print;
    private javax.swing.JComboBox comboGames;
    private javax.swing.JComboBox combo_PaymentMode;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbl_DDno;
    private javax.swing.JLabel lbl_bankName;
    private javax.swing.JLabel lbl_chequeNo;
    private javax.swing.JPanel panelBack;
    private javax.swing.JPanel panelChild;
    private javax.swing.JPanel panelEdit;
    private javax.swing.JPanel panelGamesList;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelLogout;
    private javax.swing.JPanel panelParent;
    private javax.swing.JPanel panelSearch;
    private javax.swing.JPanel panelViewAllRecords;
    private javax.swing.JPanel panelsideBar;
    private javax.swing.JTextField txt_ChequeNo;
    private javax.swing.JTextField txt_DDNo;
    private javax.swing.JLabel txt_GSTNo;
    private javax.swing.JTextField txt_GamesName;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_bankName;
    private javax.swing.JTextField txt_cgst;
    private javax.swing.JTextField txt_receiptNo;
    private javax.swing.JTextField txt_receivedFrom;
    private javax.swing.JTextArea txt_remark;
    private javax.swing.JTextField txt_rollNo;
    private javax.swing.JTextField txt_sgst;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_total_words;
    private javax.swing.JTextField txt_year1;
    private javax.swing.JTextField txt_year2;
    // End of variables declaration//GEN-END:variables
}
