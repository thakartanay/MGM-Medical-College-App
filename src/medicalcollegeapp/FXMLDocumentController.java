/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalcollegeapp;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;

import java.awt.Font;
import java.awt.HeadlessException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import java.net.URL;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.FontWeight;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.util.Callback;
import javafx.util.Duration;
import javax.imageio.ImageIO;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import jdk.nashorn.internal.objects.NativeString;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author Tanay Thakar
 */
public class FXMLDocumentController implements Initializable {

    Connection conn = null;

    int rowCounted;
//    HashMap map = new HashMap();

    ObservableList<String> JPDesgnComboList = FXCollections.observableArrayList("Tutor", "Junior Resident", "Senior Resident", "Assistant Professor", "Associate Professor", "Professor");
    ObservableList<String> ntrApptComboList = FXCollections.observableArrayList("Regular", "Contractual", "Honorary");
    ObservableList<String> adrPrfList = FXCollections.observableArrayList("Aadhar Card", "Electricity Bill", "PAN Card", "Passport", "Voter ID Card");
    ObservableList<QualDetails> Qualdata = FXCollections.observableArrayList();
    ObservableList<ExpDetails> Expdata = FXCollections.observableArrayList();
    ObservableList<QualDetails> QualiData;
    ObservableList<ExpDetails> ExpeData;

    ArrayList<String> Qualification;
    ArrayList<String> College;
    ArrayList<String> University;
    ArrayList<String> PassingYear;
    ArrayList<String> Registration;
    ArrayList<String> State;

    ArrayList<String> deg = new ArrayList<String>();
    ArrayList<String> dep = new ArrayList<String>();
    ArrayList<String> inst = new ArrayList<String>();
    ArrayList<String> fd = new ArrayList<String>();
    ArrayList<String> td = new ArrayList<String>();
    ArrayList<String> epr = new ArrayList<String>();

    byte[] photo = null, adrPrf = null, DegMB = null, DegPg = null, ApptOdr = null, ExperienceCert = null, RelievingOdr = null, JngReport = null;

    String gender;
    String ID, number, num;

    String GsDesg = "Graded Specialist", GsInst = "", GsFrmPrd = "", GsToPrd = "", CsDesg = "Classified Specialist", CsInst = "", CsFrmPrd = "", CsToPrd = "", AdvDesg = "Advisor", AdvInst = "", AdvFrmPrd = "", AdvToPrd = "";

    String MCITraining = "No", InService = "Yes";

    File AdrPrfFile = null, MBBSCertFile = null, PGCertFile = null, ApptOrderFile = null, ExpCertFile = null, RelOdrFile = null, JnRepFile = null, ImgFile = null;

    FileInputStream AdrPrfInptStrm = null, MBBSInptStrm = null, PGInptStrm = null, ApptOrderInptStrm = null, ExpCertInptStrm = null, RelOdrInptStrm = null, JnRepInptStrm = null, ImgInptStrm = null;

    @FXML
    private Button LogOutBtn;
    @FXML
    private AnchorPane LoginPage;
    @FXML
    private TabPane MainPage;
    @FXML
    private TextField Usernamefield;
    @FXML
    private PasswordField Passwordfield;
    @FXML
    private Label loginMsg;
    @FXML
    private Label time;
    @FXML
    private AnchorPane AnchorForm1;
    @FXML
    private TextField fname;
    @FXML
    private TextField mname;
    @FXML
    private TextField lname;
    @FXML
    private TextField email;
    @FXML
    private TextField mobile;
    @FXML
    private TextField stdNum2;
    @FXML
    private TextField stdOff;
    @FXML
    private TextField adl1;
    @FXML
    private TextField adl2;
    @FXML
    private TextField adl3;
    @FXML
    private RadioButton rdMale;
    @FXML
    private RadioButton rdFemale;
    @FXML
    private DatePicker dob;
    @FXML
    private Label age;
    @FXML
    private TextField adl4;
    @FXML
    private TextField adl5;
    @FXML
    private TextField ad1;
    @FXML
    private TextField ad2;
    @FXML
    private TextField ad3;
    @FXML
    private TextField ad4;
    @FXML
    private TextField ad5;
    @FXML
    private TextField stdNum;
    @FXML
    private TextField stdRes;
    @FXML
    private CheckBox AdrCheck;
    @FXML
    private ImageView addImg;
    @FXML
    private Tab Tab1;
    @FXML
    private Tab Tab2;
    @FXML
    private Tab Tab3;
    @FXML
    private AnchorPane AnchorForm2;
    @FXML
    private Button CancelFormBtn;
    @FXML
    private Button BckFormBtn;
    @FXML
    private ComboBox<String> JPDesgCombo;
    @FXML
    private TextField presDesg;
    @FXML
    private TextField presDept;
    @FXML
    private DatePicker JPIDate;
    @FXML
    private DatePicker TrngDate;
    @FXML
    private RadioButton rdTrnYes;
    @FXML
    private RadioButton rdTrnNo;
    @FXML
    private TextField TrngPlc;
    @FXML
    private TextField TrngCntrName;
    @FXML
    private ComboBox<String> ntrApptCombo;
    @FXML
    private TextField IntrJrnls;
    @FXML
    private TextField St_IntJrnls;
    @FXML
    private TextField NtnlJrnls;
    @FXML
    private AnchorPane AnchorForm3;
    @FXML
    private TableView QualTable;
    @FXML
    private CheckBox ExArmyChk;
    @FXML
    private TableColumn Qual;
    @FXML
    private TableColumn QualClg;
    @FXML
    private TableColumn QualUniv;
    @FXML
    private TableColumn QualPassYr;
    @FXML
    private TableColumn QualRegNo;
    @FXML
    private TableColumn QualState;
    @FXML
    private AnchorPane AnchorForm4;
    @FXML
    private Button AdrPrfBtn;
    @FXML
    private Button DegPGCertBtn;
    @FXML
    private Button MBDegCertBtn;
    @FXML
    private Button AptOdrBtn;
    @FXML
    private Button ExpCertBtn;
    @FXML
    private Button RelOdrBtn;
    @FXML
    private Button JngRptBtn;
    @FXML
    private TableColumn Desg;
    @FXML
    private TableColumn DesgDept;
    @FXML
    private TableColumn DesgInst;
    @FXML
    private TableColumn DesgFrDt;
    @FXML
    private TableColumn DesgToDt;
    @FXML
    private TableColumn DesgYr;
    @FXML
    private TableView ExpTable;
    @FXML
    private TextField AdrPrfTxtFld;
    @FXML
    private ComboBox<String> adrPrfCombo;
    @FXML
    private Label ProceedLbl;
    @FXML
    private TextField MBDegCertTxtFld;
    @FXML
    private TextField DegPGCertTxtFld;
    @FXML
    private TextField AptOdrTxtFld;
    @FXML
    private TextField ExpCertTxtFld;
    @FXML
    private TextField RelOdrTxtFld;
    @FXML
    private TextField JngRptTxtFld;
    @FXML
    private Button cancelFormBtn;
    @FXML
    private Button addEmpBtn;
    @FXML
    private Button BckFormBtn3;
    @FXML
    private Button CancelFormBtn2;
    @FXML
    private Button BckFormBtn2;
    @FXML
    private TableView<ModelTable> Overview_Table;
    @FXML
    private TableColumn Sr;
    @FXML
    private TableColumn<ModelTable, String> appt;
    @FXML
    private TableColumn<ModelTable, String> first;
    @FXML
    private TableColumn<ModelTable, String> middle;
    @FXML
    private TableColumn<ModelTable, String> last;
    @FXML
    private TableColumn<ModelTable, String> ling;
    @FXML
    private TableColumn<ModelTable, String> birthdate;
    @FXML
    private TableColumn<ModelTable, String> mail;
    @FXML
    private TableColumn<ModelTable, String> phone;
    @FXML
    private TableColumn<ModelTable, String> post;
    @FXML
    private TableColumn<ModelTable, String> branch;
    @FXML
    private Ellipse IcSelShp1;
    @FXML
    private Ellipse IcSelShp2;
    @FXML
    private AnchorPane UpdateForm;
    @FXML
    private AnchorPane ServiceForm;
    @FXML
    private Label DFN;
    @FXML
    private TextField txt_fname;
    @FXML
    private TextField txt_mname;
    @FXML
    private TextField txt_lname;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_mobile;
    @FXML
    private TextField txt_stdRes;
    @FXML
    private TextField txt_stdNum;
    @FXML
    private TextField txt_stdOff;
    @FXML
    private TextField txt_stdNum2;
    @FXML
    private TextField txt_adl1;
    @FXML
    private TextField txt_adl2;
    @FXML
    private TextField txt_adl3;
    @FXML
    private TextField txt_adl4;
    @FXML
    private TextField txt_adl5;
    @FXML
    private CheckBox chk_AdrCheck;
    @FXML
    private TextField txt_ad1;
    @FXML
    private TextField txt_ad2;
    @FXML
    private TextField txt_ad3;
    @FXML
    private TextField txt_ad4;
    @FXML
    private TextField txt_ad5;
    @FXML
    private TextField txt_search;
    @FXML
    private TextField txt_Desg;
    @FXML
    private TextField txt_Dept;
    @FXML
    private TextField D_Search;
    @FXML
    private Label DEM;
    @FXML
    private Label DMob;
    @FXML
    private Label DDP;
    @FXML
    private Label DDG;
    @FXML
    private Label DSS;
    @FXML
    private Label updateEmpLbl;
    @FXML
    private Label updateSrvcLbl;
    @FXML
    private TableView<LogTable> Log_Table;
    @FXML
    private TableColumn<LogTable, String> col_Sr;
    @FXML
    private TableColumn<LogTable, String> col_LI;
    @FXML
    private TableColumn<LogTable, String> col_LO;
    @FXML
    private TableColumn<LogTable, String> col_AP;
    @FXML
    private ImageView f2c;
    @FXML
    private ImageView f1c;
    @FXML
    private ImageView f3c;
    @FXML
    private ImageView f4c;
    @FXML
    private ImageView f5c;
    @FXML
    private ImageView f6c;
    @FXML
    private ImageView f7c;
    @FXML
    private ImageView f2w;
    @FXML
    private ImageView f1w;
    @FXML
    private ImageView f3w;
    @FXML
    private ImageView f4w;
    @FXML
    private ImageView f5w;
    @FXML
    private ImageView f6w;
    @FXML
    private ImageView f7w;
    @FXML
    private Tab Tab4;
    @FXML
    private MenuItem DeleteMenu;
    @FXML
    private Label CLR1;
    @FXML
    private Label CLR2;
    @FXML
    private Label CLR3;
    @FXML
    private Label CLR4;
    @FXML
    private Label CLR5;
    @FXML
    private Label CLR6;
    @FXML
    private Label CLR7;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = db.connect();

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy                    hh:mm a");
            time.setText(LocalDateTime.now().format(formatter));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        updateTable();

        updateLog();

        LocalDate ld = LocalDate.now();
        ld = ld.minusYears(18).minusDays(1);
        LocalDate temp = ld;

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {

                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isAfter(temp)) {  //.isBefore(dob.getValue().plusDays(1))
                            setDisable(true);
                            setStyle("-fx-background-color: #dbdbdb;");
                        }
                    }
                };
            }
        };
        dob.setDayCellFactory(dayCellFactory);

        JPDesgCombo.setItems(JPDesgnComboList);
        ntrApptCombo.setItems(ntrApptComboList);
        adrPrfCombo.setItems(FXCollections.observableArrayList(adrPrfList));

//        Table Population Logic for Qualification Data
        QualiData = FXCollections.observableArrayList(
                new QualDetails("MBBS/MSC", "", "", "", "", ""),
                new QualDetails("MD/MS/DNB/PhD", "", "", "", "", ""),
                new QualDetails("DM/M Ch", "", "", "", "", "")
        );

        Qual.setCellValueFactory(
                new PropertyValueFactory<QualDetails, String>("Qualification")
        );
        QualClg.setCellValueFactory(
                new PropertyValueFactory<QualDetails, String>("College")
        );
        QualUniv.setCellValueFactory(
                new PropertyValueFactory<QualDetails, String>("University")
        );
        QualPassYr.setCellValueFactory(
                new PropertyValueFactory<QualDetails, String>("PassingYear")
        );
        QualRegNo.setCellValueFactory(
                new PropertyValueFactory<QualDetails, String>("RegNo")
        );
        QualState.setCellValueFactory(
                new PropertyValueFactory<QualDetails, String>("State")
        );

        QualTable.setItems(QualiData);

        //        Table Population Logic for Expeience Data
        ExpeData = FXCollections.observableArrayList(
                new ExpDetails("TUTOR", "", "", null, null, ""),
                new ExpDetails("JUNIOR RESIDENT", "", "", null, null, ""),
                new ExpDetails("SENIOR RESIDENT", "", "", null, null, ""),
                new ExpDetails("ASSISTANT PROFESSOR", "", "", null, null, ""),
                new ExpDetails("ASSOCIATE PROFESSOR", "", "", null, null, ""),
                new ExpDetails("PROFESSOR", "", "", null, null, "")
        );

        Desg.setCellValueFactory(
                new PropertyValueFactory<ExpDetails, String>("Designation")
        );
        DesgDept.setCellValueFactory(
                new PropertyValueFactory<ExpDetails, String>("Department")
        );
        DesgInst.setCellValueFactory(
                new PropertyValueFactory<ExpDetails, String>("Institute")
        );
        DesgFrDt.setCellValueFactory(
                new PropertyValueFactory<ExpDetails, String>("FromDate")
        );
        DesgToDt.setCellValueFactory(
                new PropertyValueFactory<ExpDetails, String>("ToDate")
        );
        DesgYr.setCellValueFactory(
                new PropertyValueFactory<ExpDetails, String>("ExpYears")
        );

        ExpTable.setItems(ExpeData);

        Sr.setCellValueFactory(new PropertyValueFactory<ModelTable, String>("Sr"));
        first.setCellValueFactory(new PropertyValueFactory("first"));
        middle.setCellValueFactory(new PropertyValueFactory("middle"));
        last.setCellValueFactory(new PropertyValueFactory("last"));
        ling.setCellValueFactory(new PropertyValueFactory("ling"));
        birthdate.setCellValueFactory(new PropertyValueFactory("birthdate"));
        mail.setCellValueFactory(new PropertyValueFactory("mail"));
        phone.setCellValueFactory(new PropertyValueFactory("phone"));
        post.setCellValueFactory(new PropertyValueFactory("post"));
        branch.setCellValueFactory(new PropertyValueFactory("branch"));
        appt.setCellValueFactory(new PropertyValueFactory("appt"));

        col_Sr.setCellValueFactory(new PropertyValueFactory("col_Sr"));
        col_LI.setCellValueFactory(new PropertyValueFactory("col_LI"));
        col_LO.setCellValueFactory(new PropertyValueFactory("col_LO"));
        col_AP.setCellValueFactory(new PropertyValueFactory("col_AP"));

    }

    void updateTable() {
        int o = 1;
        ResultSet rs = null;
        ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
        try {
            rs = conn.createStatement().executeQuery("Select * FROM Employee_details where InService = 'Yes'");
            while (rs.next()) {
                oblist.add(new ModelTable(Integer.toString(o), rs.getString("FNAME"), rs.getString("MNAME"), rs.getString("LNAME"), rs.getString("GENDER"), rs.getString("DOB"), rs.getString("EMAIL"), rs.getString("MOBILE"), rs.getString("PRESDESG"), rs.getString("JNDEPT"), rs.getString("NTRAPNT")));
                o++;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        Overview_Table.setItems(oblist);
    }

    void updateLog() {
        int o = 1;
        ResultSet rs = null;
        ObservableList<LogTable> oblist = FXCollections.observableArrayList();
        try {
            rs = conn.createStatement().executeQuery("Select * FROM UserLog");
            while (rs.next()) {
                oblist.add(new LogTable(Integer.toString(o), rs.getString("Login"), rs.getString("Logout"), rs.getString("Time")));
                o++;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        Log_Table.setItems(oblist);
    }

    @FXML
    private void logOut(ActionEvent event) {
        int n;
        if (Tab2.isSelected() && AnchorForm2.isVisible() || AnchorForm3.isVisible() || AnchorForm4.isVisible()) {
            n = JOptionPane.showConfirmDialog(null, "Are You Sure You want to Log Out...!\nYou have an Unsaved Form.\nUnsaved Data will be Erased Permanently.", "Unsaved Data - LogOut Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

            if (n == 0) {

                Tab1.setDisable(false);
                Tab3.setDisable(false);
                Tab4.setDisable(false);
                AnchorForm1.setVisible(true);
                AnchorForm2.setVisible(false);
                AnchorForm3.setVisible(false);
                AnchorForm4.setVisible(false);
                resetElements();

            }
        }

        resetElements();
        AnchorForm1.setVisible(true);
        AnchorForm2.setVisible(false);
        AnchorForm3.setVisible(false);
        AnchorForm4.setVisible(false);
        Tab1.setDisable(false);
        Tab3.setDisable(false);
        Tab4.setDisable(false);
        MainPage.getSelectionModel().select(Tab1);
        MainPage.setVisible(false);
        LoginPage.setVisible(true);
        LogOutBtn.setVisible(false);
        Usernamefield.clear();
        Passwordfield.clear();

        Date currentDate = GregorianCalendar.getInstance().getTime();
        DateFormat df = DateFormat.getDateInstance();
        String dateString = df.format(currentDate);

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        String timeString = sdf.format(d);

        MedicalCollegeApp.Logout_Time = timeString;
        MedicalCollegeApp.Logout_Date = dateString;

    }

    @FXML
    private void login(ActionEvent event) {

//        map.clear();
//        map.put("admin", "admin@123");
        String name = Usernamefield.getText();
        String pwd = Passwordfield.getText().trim();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "Select Password from Users where Username = 'admin'";

        if (name.equals("admin")) {
            try {
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                if (pwd.equals(rs.getString(1))) {
                    MainPage.setVisible(true);
                    LoginPage.setVisible(false);
                    loginMsg.setText("");
                    LogOutBtn.setVisible(true);

                    Date currentDate = GregorianCalendar.getInstance().getTime();
                    DateFormat df = DateFormat.getDateInstance();
                    String dateString = df.format(currentDate);

                    Date d = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                    String timeString = sdf.format(d);

                    MedicalCollegeApp.Login_Time = timeString;
                    MedicalCollegeApp.Login_Date = dateString;

                    resetElements();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Password");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Username and Password is Incorrect");
            } finally {
                try {
                    pst.close();
                    rs.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Username");
        }
    }

    @FXML
    private void AdrsChk(ActionEvent event) {
        if (event.getSource() == AdrCheck) {
            if (AdrCheck.isSelected()) {
                ad1.setText(adl1.getText());
                ad2.setText(adl2.getText());
                ad3.setText(adl3.getText());
                ad4.setText(adl4.getText());
                ad5.setText(adl5.getText());
            } else {
                ad1.clear();
                ad2.clear();
                ad3.clear();
                ad4.clear();
                ad5.clear();
            }
        } else if (event.getSource() == chk_AdrCheck) {
            if (chk_AdrCheck.isSelected()) {
                txt_ad1.setText(txt_adl1.getText());
                txt_ad2.setText(txt_adl2.getText());
                txt_ad3.setText(txt_adl3.getText());
                txt_ad4.setText(txt_adl4.getText());
                txt_ad5.setText(txt_adl5.getText());
            } else {
                txt_ad1.clear();
                txt_ad2.clear();
                txt_ad3.clear();
                txt_ad4.clear();
                txt_ad5.clear();
            }
        }

    }

    @FXML
    private void generateAge(ActionEvent event) {

        LocalDate date = dob.getValue();
        LocalDate currDate = LocalDate.now();
        int currAge = Period.between(date, currDate).getYears();
        age.setText(currAge + " Years");
    }

    @FXML
    private void upldImg(ActionEvent event) {

        Image image;

        JFileChooser j = new JFileChooser();
        j.setAcceptAllFileFilterUsed(false);
        j.setDialogTitle("Select an Image to Upload");
        j.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
        int r = j.showOpenDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {

            try {
                image = new Image(j.getSelectedFile().toURI().toString(), 120, 150, true, true);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];

                addImg.setImage(image);
                addImg.setFitWidth(120);
                addImg.setFitHeight(150);
                addImg.setPreserveRatio(false);

                ImgFile = new File(j.getSelectedFile().getAbsolutePath());
                ImgInptStrm = new FileInputStream(ImgFile);

                for (int readNum; (readNum = ImgInptStrm.read(buffer)) != -1;) {
                    baos.write(buffer, 0, readNum);
                }
                photo = baos.toByteArray();

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "File Not Found..!!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Image Byte Array Error..!!");
            }
        }
    }

    @FXML
    private void proceedForm(MouseEvent event) {
//        Tab1.setDisable(true);
//        Tab3.setDisable(true);
//        AnchorForm1.setVisible(false);
//        AnchorForm2.setVisible(true);
//        AnchorForm3.setVisible(false);
//        AnchorForm4.setVisible(false);

//     **********    ************     Enable for final use       *************      ***********
        Image image = addImg.getImage();
        if (email.getText().matches("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")) {
            if ((fname.equals("") || mname.equals("") || lname.equals("") || dob.getValue().toString().isEmpty() || age.equals("") || email.equals("") || mobile.equals("") || adl1.equals(null) || adl2.equals("") || adl3.equals("") || adl4.equals("") || adl5.equals("") || ad1.equals("") || ad2.equals("") || ad3.equals("") || ad4.equals("") || ad5.equals("") || image == null) || !(rdMale.isSelected() || rdFemale.isSelected())) {
                JOptionPane.showMessageDialog(null, "Please Fill Up all the Fields & Select Proper Options and Image..!!");
            } else {
                Tab1.setDisable(true);
                Tab3.setDisable(true);
                Tab4.setDisable(true);
                AnchorForm1.setVisible(false);
                AnchorForm2.setVisible(true);
                AnchorForm3.setVisible(false);
                AnchorForm4.setVisible(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please Enter valid Email, Fill Up all the Fields & Select Proper Options and Image..!!");
        }
    }

    @FXML
    private void radioButtonMaleAction(ActionEvent event) {
        gender = "Male";
        rdMale.setSelected(true);
        rdFemale.setSelected(false);
    }

    @FXML
    private void radioButtonFemaleAction(ActionEvent event) {
        gender = "Female";
        rdFemale.setSelected(true);
        rdMale.setSelected(false);
    }

    @FXML
    private void CancelForm(ActionEvent event) {
        int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel adding a new employee ? \n All the Credentials will be Erased Permanently !", "Cancel Empoyee Registration", JOptionPane.YES_NO_OPTION);

        if (x == 0) {

            Tab1.setDisable(false);
            Tab3.setDisable(false);
            Tab4.setDisable(false);
            AnchorForm1.setVisible(true);
            AnchorForm2.setVisible(false);
            AnchorForm3.setVisible(false);
            AnchorForm4.setVisible(false);
            resetElements();
        }
    }

    @FXML
    private void BackForm(ActionEvent event) {
        Tab1.setDisable(true);
        Tab3.setDisable(true);
        Tab4.setDisable(true);
        AnchorForm1.setVisible(true);
        AnchorForm2.setVisible(false);
        AnchorForm3.setVisible(false);
        AnchorForm4.setVisible(false);
    }

    @FXML
    private void trainingYes(ActionEvent event) {
        MCITraining = "Yes";
        rdTrnYes.setSelected(true);
        rdTrnNo.setSelected(false);
        TrngCntrName.setDisable(false);
        TrngPlc.setDisable(false);
        TrngDate.setDisable(false);
    }

    @FXML
    private void trainingNo(ActionEvent event) {
        MCITraining = "No";
        rdTrnYes.setSelected(false);
        rdTrnNo.setSelected(true);
        TrngCntrName.setDisable(true);
        TrngPlc.setDisable(true);
        TrngDate.setDisable(true);
        TrngCntrName.clear();
        TrngPlc.clear();
        TrngDate.setValue(null);
    }

    @FXML
    private void proceedForm2(MouseEvent event) {
//        Tab1.setDisable(true);
//        Tab3.setDisable(true);
//        AnchorForm2.setVisible(false);
//        AnchorForm3.setVisible(true);
        if (rdTrnYes.isSelected()) {
            if (TrngCntrName.getText().isEmpty() || TrngPlc.getText().isEmpty() || TrngDate.getValue().toString().isEmpty() || JPIDate.getValue().toString().isEmpty() || JPDesgCombo.equals("Select a Designation") || presDesg.getText().isEmpty() || presDept.getText().isEmpty() || ntrApptCombo.equals("Select an Option")) {
                JOptionPane.showMessageDialog(null, "Please Fill Up all the Fields..!!");
            } else {
                Tab1.setDisable(true);
                Tab3.setDisable(true);
                Tab4.setDisable(true);
                AnchorForm1.setVisible(false);
                AnchorForm2.setVisible(false);
                AnchorForm3.setVisible(true);
                AnchorForm4.setVisible(false);
            }
        } else if (rdTrnNo.isSelected()) {
            if (JPIDate.getValue().toString().isEmpty() || JPDesgCombo.equals("Select a Designation") || presDesg.getText().isEmpty() || presDept.getText().isEmpty() || ntrApptCombo.equals("Select an Option")) {
                JOptionPane.showMessageDialog(null, "Please Fill Up all the Fields..!!");
            } else {
                Tab1.setDisable(true);
                Tab3.setDisable(true);
                Tab4.setDisable(true);
                AnchorForm1.setVisible(false);
                AnchorForm2.setVisible(false);
                AnchorForm3.setVisible(true);
                AnchorForm4.setVisible(false);
            }
        } else if (!(rdTrnYes.isSelected() && rdTrnNo.isSelected())) {
            JOptionPane.showMessageDialog(null, "Please Fill Up all the Fields & Select Proper Option..!!");
        }
    }

    @FXML
    private void exArmyData(ActionEvent event) {

        if (ExArmyChk.isSelected()) {

            GsInst = "";
            GsFrmPrd = "";
            GsToPrd = "";
            CsInst = "";
            CsFrmPrd = "";
            CsToPrd = "";
            AdvInst = "";
            AdvFrmPrd = "";
            AdvToPrd = "";

            final Stage dialog = new Stage();
            dialog.setTitle("For Ex Army Personnel");
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.initStyle(StageStyle.UTILITY);
            dialog.initOwner((Stage) MainPage.getScene().getWindow());

            // create a grid for the data entry.
            GridPane grid = new GridPane();

            AnchorPane anchorPane = new AnchorPane(grid);

            ObservableList<String> DesgChoiceList = FXCollections.observableArrayList("Graded Specialist", "Classified Specialist", "Advisor");
            final ChoiceBox<String> desgChoice = new ChoiceBox<String>();
            desgChoice.setValue("Select a Designation");
            desgChoice.setItems(DesgChoiceList);
            final TextField Institution = new TextField();
            final TextField frmPrd = new TextField();
            final TextField toPrd = new TextField();

            grid.addRow(0, new Label("Designation"), desgChoice);
            grid.addRow(1, new Label("Institution"), Institution);
            grid.addRow(2, new Label("From Period"), frmPrd);
            grid.addRow(3, new Label("To Period"), toPrd);

            grid.setHgap(10);
            grid.setVgap(10);

            GridPane.setHgrow(desgChoice, Priority.ALWAYS);
            GridPane.setHgrow(Institution, Priority.ALWAYS);
            GridPane.setHgrow(frmPrd, Priority.ALWAYS);
            GridPane.setHgrow(toPrd, Priority.ALWAYS);

            // create action buttons for the dialog.
            Button ok = new Button("Add");
            Button cancel = new Button("Finish");
            ok.setDefaultButton(true);
            cancel.setCancelButton(true);

            anchorPane.getChildren().add(ok);
            anchorPane.getChildren().add(cancel);

            AnchorPane.setTopAnchor(grid, 20.0);
            AnchorPane.setLeftAnchor(grid, 20.0);
            AnchorPane.setRightAnchor(grid, 20.0);
            AnchorPane.setBottomAnchor(grid, 80.0);

            AnchorPane.setTopAnchor(ok, 240.0);
            AnchorPane.setLeftAnchor(ok, 100.0);
            AnchorPane.setRightAnchor(ok, 214.0);
            AnchorPane.setBottomAnchor(ok, 30.0);

            AnchorPane.setTopAnchor(cancel, 240.0);
            AnchorPane.setLeftAnchor(cancel, 230.0);
            AnchorPane.setRightAnchor(cancel, 95.0);
            AnchorPane.setBottomAnchor(cancel, 30.0);

            dialog.setScene(new Scene(anchorPane, 400, 310));

            dialog.show();
            // only enable the ok button when there has been some text entered.
            ok.disableProperty().bind(Institution.textProperty().isEqualTo("").or(frmPrd.textProperty().isEqualTo("")).or(toPrd.textProperty().isEqualTo("")));

            // add action handlers for the dialog buttons.
            ok.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    if (desgChoice.getValue().equals("Graded Specialist")) {
                        GsDesg = desgChoice.getValue();
                        GsInst = Institution.getText();
                        GsFrmPrd = frmPrd.getText();
                        GsToPrd = toPrd.getText();
                    } else if (desgChoice.getValue().equals("Classified Specialist")) {
                        CsDesg = desgChoice.getValue();
                        CsInst = Institution.getText();
                        CsFrmPrd = frmPrd.getText();
                        CsToPrd = toPrd.getText();
                    } else if (desgChoice.getValue().equals("Advisor")) {
                        AdvDesg = desgChoice.getValue();
                        AdvInst = Institution.getText();
                        AdvFrmPrd = frmPrd.getText();
                        AdvToPrd = toPrd.getText();
                    }
                    JOptionPane.showMessageDialog(null, "Details Added Successfully");
                }

            });
            cancel.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    dialog.close();
                }
            });
        } else {
            int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to Clear the Ex Army Data ?", "Clear Ex-Army Personnel Data", JOptionPane.YES_NO_OPTION);

            if (x == 0) {
                ExArmyChk.setSelected(false);
                GsInst = "";
                GsFrmPrd = "";
                GsToPrd = "";
                CsInst = "";
                CsFrmPrd = "";
                CsToPrd = "";
                AdvInst = "";
                AdvFrmPrd = "";
                AdvToPrd = "";
            } else {
                ExArmyChk.setSelected(true);
            }
        }
    }

    @FXML
    private void DocUpld(ActionEvent event) {

        JFileChooser j = new JFileChooser();
        j.setAcceptAllFileFilterUsed(false);
        j.setDialogTitle("Select Document to Upload");
        j.addChoosableFileFilter(new FileNameExtensionFilter("PDF File", "pdf"));
        int r = j.showOpenDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {

            if (event.getSource() == AdrPrfBtn) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                AdrPrfFile = new File(j.getSelectedFile().getAbsolutePath());
                try {
                    AdrPrfInptStrm = new FileInputStream(AdrPrfFile);
                    for (int readNum; (readNum = AdrPrfInptStrm.read(buffer)) != -1;) {
                        baos.write(buffer, 0, readNum);
                    }
                    adrPrf = baos.toByteArray();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "File Input Stream Error !");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "File Input Stream Closing Error !");
                }
                if (AdrPrfTxtFld.getText().isEmpty()) {
                    f1c.setVisible(false);
                    f1w.setVisible(true);
                } else if (AdrPrfTxtFld.getText().isEmpty() && AdrPrfFile == null) {
                    f1c.setVisible(false);
                    f1w.setVisible(false);
                } else {
                    f1c.setVisible(true);
                    f1w.setVisible(false);
                }
                if (AdrPrfFile != null) {
                    CLR1.setVisible(true);
                    JOptionPane.showMessageDialog(null, "File Uploaded !");
                } else if (AdrPrfFile == null) {
                    CLR1.setVisible(false);
                }
            } else if (event.getSource() == MBDegCertBtn) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                MBBSCertFile = new File(j.getSelectedFile().getAbsolutePath());
                try {
                    MBBSInptStrm = new FileInputStream(MBBSCertFile);
                    for (int readNum; (readNum = MBBSInptStrm.read(buffer)) != -1;) {
                        baos.write(buffer, 0, readNum);
                    }
                    DegMB = baos.toByteArray();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "File Input Stream Error !");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "File Input Stream Closing Error !");
                }
                if (MBDegCertTxtFld.getText().isEmpty()) {
                    f2c.setVisible(false);
                    f2w.setVisible(true);
                } else if (MBDegCertTxtFld.getText().isEmpty() && MBBSCertFile == null) {
                    f2c.setVisible(false);
                    f2w.setVisible(false);
                } else {
                    f2c.setVisible(true);
                    f2w.setVisible(false);
                }
                if (MBBSCertFile != null) {
                    CLR2.setVisible(true);
                    JOptionPane.showMessageDialog(null, "File Uploaded !");
                } else if (MBBSCertFile == null) {
                    CLR2.setVisible(false);
                }
            } else if (event.getSource() == DegPGCertBtn) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                PGCertFile = new File(j.getSelectedFile().getAbsolutePath());
                try {
                    PGInptStrm = new FileInputStream(PGCertFile);
                    for (int readNum; (readNum = PGInptStrm.read(buffer)) != -1;) {
                        baos.write(buffer, 0, readNum);
                    }
                    DegPg = baos.toByteArray();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "File Input Stream Error !");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "File Input Stream Closing Error !");
                }
                if (DegPGCertTxtFld.getText().isEmpty()) {
                    f3c.setVisible(false);
                    f3w.setVisible(true);
                } else if (DegPGCertTxtFld.getText().isEmpty() && PGCertFile == null) {
                    f3c.setVisible(false);
                    f3w.setVisible(false);
                } else {
                    f3c.setVisible(true);
                    f3w.setVisible(false);
                }
                if (PGCertFile != null) {
                    CLR3.setVisible(true);
                    JOptionPane.showMessageDialog(null, "File Uploaded !");
                } else if (PGCertFile == null) {
                    CLR3.setVisible(false);
                }
            } else if (event.getSource() == AptOdrBtn) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                ApptOrderFile = new File(j.getSelectedFile().getAbsolutePath());
                try {
                    ApptOrderInptStrm = new FileInputStream(ApptOrderFile);
                    for (int readNum; (readNum = ApptOrderInptStrm.read(buffer)) != -1;) {
                        baos.write(buffer, 0, readNum);
                    }
                    ApptOdr = baos.toByteArray();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "File Input Stream Error !");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "File Input Stream Closing Error !");
                }
                if (AptOdrTxtFld.getText().isEmpty()) {
                    f4c.setVisible(false);
                    f4w.setVisible(true);
                } else if (AptOdrTxtFld.getText().isEmpty() && ApptOrderFile == null) {
                    f4c.setVisible(false);
                    f4w.setVisible(false);
                } else {
                    f4c.setVisible(true);
                    f4w.setVisible(false);
                }
                if (ApptOrderFile != null) {
                    CLR4.setVisible(true);
                    JOptionPane.showMessageDialog(null, "File Uploaded !");
                } else if (ApptOrderFile == null) {
                    CLR4.setVisible(false);
                }
            } else if (event.getSource() == ExpCertBtn) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                ExpCertFile = new File(j.getSelectedFile().getAbsolutePath());
                try {
                    ExpCertInptStrm = new FileInputStream(ExpCertFile);
                    for (int readNum; (readNum = ExpCertInptStrm.read(buffer)) != -1;) {
                        baos.write(buffer, 0, readNum);
                    }
                    ExperienceCert = baos.toByteArray();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "File Input Stream Error !");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "File Input Stream Closing Error !");
                }
                if (ExpCertTxtFld.getText().isEmpty()) {
                    f5c.setVisible(false);
                    f5w.setVisible(true);
                } else if (ExpCertTxtFld.getText().isEmpty() && ExpCertFile == null) {
                    f5c.setVisible(false);
                    f5w.setVisible(false);
                } else {
                    f5c.setVisible(true);
                    f5w.setVisible(false);
                }
                if (ExpCertFile != null) {
                    CLR5.setVisible(true);
                    JOptionPane.showMessageDialog(null, "File Uploaded !");
                } else if (ExpCertFile == null) {
                    CLR5.setVisible(false);
                }
            } else if (event.getSource() == RelOdrBtn) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                RelOdrFile = new File(j.getSelectedFile().getAbsolutePath());
                try {
                    RelOdrInptStrm = new FileInputStream(RelOdrFile);
                    for (int readNum; (readNum = RelOdrInptStrm.read(buffer)) != -1;) {
                        baos.write(buffer, 0, readNum);
                    }
                    RelievingOdr = baos.toByteArray();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "File Input Stream Error !");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "File Input Stream Closing Error !");
                }
                if (RelOdrTxtFld.getText().isEmpty()) {
                    f6c.setVisible(false);
                    f6w.setVisible(true);
                } else if (RelOdrTxtFld.getText().isEmpty() && RelOdrFile == null) {
                    f6c.setVisible(false);
                    f6w.setVisible(false);
                } else {
                    f6c.setVisible(true);
                    f6w.setVisible(false);
                }
                if (RelOdrFile != null) {
                    CLR6.setVisible(true);
                    JOptionPane.showMessageDialog(null, "File Uploaded !");
                } else if (RelOdrFile == null) {
                    CLR6.setVisible(false);
                }
            } else if (event.getSource() == JngRptBtn) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                JnRepFile = new File(j.getSelectedFile().getAbsolutePath());
                try {
                    JnRepInptStrm = new FileInputStream(JnRepFile);
                    for (int readNum; (readNum = JnRepInptStrm.read(buffer)) != -1;) {
                        baos.write(buffer, 0, readNum);
                    }
                    JngReport = baos.toByteArray();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "File Input Stream Error !");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "File Input Stream Closing Error !");
                }
                if (JngRptTxtFld.getText().isEmpty()) {
                    f7c.setVisible(false);
                    f7w.setVisible(true);
                } else if (JngRptTxtFld.getText().isEmpty() && JnRepFile == null) {
                    f7c.setVisible(false);
                    f7w.setVisible(false);
                } else {
                    f7c.setVisible(true);
                    f7w.setVisible(false);
                }
                if (JnRepFile != null) {
                    CLR7.setVisible(true);
                    JOptionPane.showMessageDialog(null, "File Uploaded !");
                } else if (JnRepFile == null) {
                    CLR7.setVisible(false);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "File Uploading Cancelled..!!");
        }
    }

    @FXML
    private void proceedForm3(MouseEvent event) {

        Qualification = new ArrayList<String>();
        College = new ArrayList<String>();
        University = new ArrayList<String>();
        PassingYear = new ArrayList<String>();
        Registration = new ArrayList<String>();
        State = new ArrayList<String>();
    
        try {

            for (QualDetails bean : QualiData) {

                Qualdata.add(bean);
                if (bean.getQualification().isEmpty()) {
                    Qualification.add(null);
                } else if (!bean.getQualification().isEmpty()) {
                    System.out.println("Data :" + bean.getQualification()+"Ends");
                    Qualification.add(bean.getQualification());
                }
                if (bean.getCollege().getText().isEmpty()) {
                    College.add(null);
                } else if (!bean.getCollege().getText().isEmpty()) {
                    System.out.println(bean.getCollege().getText());
                    College.add(bean.getCollege().getText());
                }
                if (bean.getUniversity().getText().isEmpty()) {
                    University.add(null);
                } else if (!bean.getUniversity().getText().isEmpty()) {
                    System.out.println(bean.getUniversity().getText());
                    University.add(bean.getUniversity().getText());
                }
                if (bean.getPassingYear().getText().isEmpty()) {
                    PassingYear.add(null);
                } else if (!bean.getPassingYear().getText().isEmpty()) {
                    System.out.println(bean.getPassingYear().getText());
                    PassingYear.add(bean.getPassingYear().getText());
                }
                if (bean.getRegNo().getText().isEmpty()) {
                    Registration.add(null);
                } else if (!bean.getRegNo().getText().isEmpty()) {
                    System.out.println(bean.getRegNo().getText());
                    Registration.add(bean.getRegNo().getText());
                }
                if (bean.getState().getText().isEmpty()) {
                    State.add(null);
                } else if (!bean.getState().getText().isEmpty()) {
                    System.out.println(bean.getState().getText());
                    State.add(bean.getState().getText());
                }
            }

            for (ExpDetails bean : ExpeData) {

                Expdata.add(bean);

                if (bean.getDesignation().isEmpty()) {
                    deg.add(null);
                } else if (!bean.getDesignation().isEmpty()) {
                    deg.add(bean.getDesignation());
                }
                if (bean.getDepartment().getText().isEmpty()) {
                    dep.add(null);
                } else if (!bean.getDepartment().getText().isEmpty()) {
                    dep.add(bean.getDepartment().getText());
                }
                if (bean.getInstitute().getText().isEmpty()) {
                    inst.add(null);
                } else if (!bean.getInstitute().getText().isEmpty()) {
                    inst.add(bean.getInstitute().getText());
                }
                if (bean.getFromDate().getValue() == null) {
                    fd.add(null);
                } else if (bean.getFromDate().getValue() != null) {
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date d1 = Date.from(bean.getFromDate().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    fd.add(df.format(d1));
                }
                if (bean.getToDate().getValue() == null) {
                    td.add(null);
                } else if (bean.getToDate().getValue() != null) {
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date d1 = Date.from(bean.getToDate().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    td.add(df.format(d1));
                }
                if (bean.getExpYears().getText().isEmpty()) {
                    epr.add(null);
                } else if (!bean.getExpYears().getText().isEmpty()) {
                    epr.add(bean.getExpYears().getText());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, e);
        }

        AnchorForm3.setVisible(false);
        AnchorForm4.setVisible(true);
    }

    @FXML
    private void UpdtAdrPrfTxtFld(ActionEvent event) {
        if (adrPrfCombo.getValue().equals("Aadhar Card")) {
            AdrPrfTxtFld.setPromptText("12 Digit Aadhar Number");
        } else if (adrPrfCombo.getValue().equals("Passport")) {
            AdrPrfTxtFld.setPromptText("Passport Number");
        } else if (adrPrfCombo.getValue().equals("Voter ID Card")) {
            AdrPrfTxtFld.setPromptText("Voter ID Number / Elector's Photo ID Number");
        } else if (adrPrfCombo.getValue().equals("Electricity Bill")) {
            AdrPrfTxtFld.setPromptText("Electricity Bill Consumer Number");
        } else if (adrPrfCombo.getValue().equals("PAN Card")) {
            AdrPrfTxtFld.setPromptText("10 Digit Permanent Account Number (PAN)");
        }
    }

    @FXML
    private void onlyUCLCChar(KeyEvent event) {
        String c = event.getCharacter();
        if (!c.matches("[A-Za-z]")) {
            event.consume();
        }
    }

    @FXML
    private void mob10Dig(KeyEvent event) {
        String c = event.getCharacter();
        if (mobile.getLength() > 9) {
            event.consume();
        } else {
            if (!c.matches("[0-9]")) {
                event.consume();
            }
        }
    }

    @FXML
    private void tel8Dig(KeyEvent event) {
        String c = event.getCharacter();
        TextField src = (TextField) event.getSource();
        if (src.getLength() > 7) {
            event.consume();
        } else {
            if (!c.matches("[0-9]")) {
                event.consume();
            }
        }
    }

    @FXML
    private void telSTD(KeyEvent event) {
        String c = event.getCharacter();
        TextField src = (TextField) event.getSource();
        if (src.getLength() > 4) {
            event.consume();
        } else {
            if (!c.matches("[0-9]")) {
                event.consume();
            }
        }
    }

    @FXML
    private void onlyUCLCCharSpc(KeyEvent event) {
        String c = event.getCharacter();
        KeyCode k = event.getCode();
        if (!(c.matches("[A-Za-z\\s*]") || k == KeyCode.SPACE)) {
            event.consume();
        }
    }

    @FXML
    private void pinType(KeyEvent event) {
        String c = event.getCharacter();
        TextField src = (TextField) event.getSource();
        if (src.getLength() > 5) {
            event.consume();
        } else {
            if (!c.matches("[0-9]")) {
                event.consume();
            }
        }
    }

    @FXML
    private void jrCntFltr(KeyEvent event) {
        String c = event.getCharacter();
        TextField src = (TextField) event.getSource();
        if (src.getLength() > 2) {
            event.consume();
        } else {
            if (!c.matches("[0-9]")) {
                event.consume();
            }
        }
    }

    private void cancelReg(ActionEvent event) {
        int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel adding a new employee ? \n All the Credentials will be Erased Permanently !", "Cancel Empoyee Registration", JOptionPane.YES_NO_OPTION);

        if (x == 0) {

            Tab1.setDisable(false);
            Tab3.setDisable(false);
            Tab4.setDisable(false);
            AnchorForm1.setVisible(true);
            AnchorForm2.setVisible(false);
            AnchorForm3.setVisible(false);
            AnchorForm4.setVisible(false);
            resetElements();
        }
    }

    @FXML
    private void BackForm3(ActionEvent event) {
        Tab1.setDisable(true);
        Tab3.setDisable(true);
        Tab4.setDisable(true);
        AnchorForm1.setVisible(false);
        AnchorForm2.setVisible(false);
        AnchorForm3.setVisible(true);
        AnchorForm4.setVisible(false);
    }

    @FXML
    private void BackForm2(ActionEvent event) {
        Tab1.setDisable(true);
        Tab3.setDisable(true);
        Tab4.setDisable(true);
        AnchorForm1.setVisible(false);
        AnchorForm2.setVisible(true);
        AnchorForm3.setVisible(false);
        AnchorForm4.setVisible(false);
    }

    @FXML
    private void addEmployee(ActionEvent event) {

        String FName = fname.getText().trim();
        String mob = mobile.getText().trim();

        ResultSet rs = null;
        PreparedStatement pst = null, pst2 = null, pst3 = null, pst4 = null, pst5 = null, pst6 = null;
        String rowCount = null;

        try {
            String sql = "select count(MOBILE) from Employee_Details where fname = '" + FName + "' and mobile = '" + mob + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                rowCount = rs.getString(1);
            }
            rowCounted = Integer.parseInt(rowCount);

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {

            try {
                rs.close();
                pst.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
            updateTable();
        }

        if (rowCounted > 0) {
            System.out.println(rowCounted);
            JOptionPane.showMessageDialog(null, "Cannot add an Employee multiple times ..!!");
        } else {
            int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to add a New Employee Record", "Add New Employee Record", JOptionPane.YES_NO_OPTION);

            if (AdrPrfFile == null || MBBSCertFile == null || ApptOrderFile == null || RelOdrFile == null || JnRepFile == null || AdrPrfTxtFld.getText().isEmpty() || MBDegCertTxtFld.getText().isEmpty() || AptOdrTxtFld.getText().isEmpty() || RelOdrTxtFld.getText().isEmpty() || JngRptTxtFld.getText().isEmpty()) {
                if (AdrPrfFile == null || AdrPrfTxtFld.getText().isEmpty()) {
                    f1c.setVisible(false);
                    f1w.setVisible(true);
                }
                if (MBBSCertFile == null || MBDegCertTxtFld.getText().isEmpty()) {
                    f2c.setVisible(false);
                    f3w.setVisible(true);
                }
                if (PGCertFile == null || DegPGCertTxtFld.getText().isEmpty()) {
                    f3c.setVisible(false);
                    f3w.setVisible(true);
                }
                if (ApptOrderFile == null || AptOdrTxtFld.getText().isEmpty()) {
                    f4c.setVisible(false);
                    f4w.setVisible(true);
                }
                if (ExpCertFile == null || ExpCertTxtFld.getText().isEmpty()) {
                    f5c.setVisible(false);
                    f5w.setVisible(true);
                }
                if (RelOdrFile == null || RelOdrTxtFld.getText().isEmpty()) {
                    f6c.setVisible(false);
                    f6w.setVisible(true);
                }
                if (JnRepFile == null || JngRptTxtFld.getText().isEmpty()) {
                    f7c.setVisible(false);
                    f7w.setVisible(true);
                }
                JOptionPane.showMessageDialog(null, "Please Upload an Address Proof, MBBS Degree Certificate, Appointment Order, Relieving Order from Previous Institution & Joining Report at the Present Institution !");
                x = 1;
            } else {
                if (x == 0) {
                    try {
                        String EmpDetails = "Insert into Employee_Details (FNAME, MNAME, LNAME, GENDER, DOB, AGE, EMAIL, MOBILE, TELRESCODE, TELRES, TELOFFCODE, TELOFF, PRADL1, PRADL2, PRADL3, PRADL4, PRADL5, PEADL1, PEADL2, PEALD3, PEALD4, PEALD5, JNDATE, JNDESG, PRESDESG, JNDEPT, NTRAPNT, INTRJRNLS, NTLJRNLS, STJRNLS, InService, Photo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        String EmpMETMCI = "Insert into MET_MCI (ID, CNNAME, CNPLACE, CNDATE) values(?,?,?,?)";
                        String EmpQualification = "Insert into Qualification (ID, Qualification, College, University, PsngYr, RegNo, StName) values(?,?,?,?,?,?,?)";
                        String EmpExperience = "insert into Experience (ID, Designation, Department, InstName, FromDT, ToDT, Exp) values(?,?,?,?,?,?,?)";
                        String EmpExArmy = "insert into ExArmy (ID, Designation, Institution, FromDT, ToDT) values(?,?,?,?,?)";
                        String EmpDocs = "insert into Emp_Docs (ID, AdrPrf, DGMB, DGPG, ApptOdr, ExpCert, RelOdr, JngRpt,AdrNo, MBNo, PGNo, ApptNo, ExpNo, RelNo, JngNo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                        //   **********      Employee_Details Insertion      *************   //
                        pst = conn.prepareStatement(EmpDetails);

                        pst.setString(1, upperCase(NativeString.toLowerCase(fname.getText())).trim());
                        pst.setString(2, upperCase(NativeString.toLowerCase(mname.getText())).trim());
                        pst.setString(3, upperCase(NativeString.toLowerCase(lname.getText())).trim());
                        pst.setString(4, gender);
                        pst.setString(5, dob.getValue().toString());
                        pst.setString(6, age.getText().substring(0, 2).trim());
                        pst.setString(7, email.getText().trim());
                        pst.setString(8, mobile.getText().trim());
                        pst.setString(9, stdRes.getText().trim());
                        pst.setString(10, stdNum.getText().trim());
                        pst.setString(11, stdOff.getText().trim());
                        pst.setString(12, stdNum2.getText().trim());
                        pst.setString(13, adl1.getText().trim());
                        pst.setString(14, adl2.getText().trim());
                        pst.setString(15, adl3.getText().trim());
                        pst.setString(16, adl4.getText().trim());
                        pst.setString(17, adl5.getText().trim());
                        pst.setString(18, ad1.getText().trim());
                        pst.setString(19, ad2.getText().trim());
                        pst.setString(20, ad3.getText().trim());
                        pst.setString(21, ad4.getText().trim());
                        pst.setString(22, ad5.getText().trim());
                        pst.setString(23, JPIDate.getValue().toString());
                        pst.setString(24, JPDesgCombo.getValue());
                        pst.setString(25, upperCase(NativeString.toLowerCase(presDesg.getText())).trim());
                        pst.setString(26, upperCase(NativeString.toLowerCase(presDept.getText())).trim());
                        pst.setString(27, ntrApptCombo.getValue());
                        pst.setString(28, IntrJrnls.getText().trim());
                        pst.setString(29, NtnlJrnls.getText().trim());
                        pst.setString(30, St_IntJrnls.getText().trim());
                        pst.setString(31, InService);
                        pst.setBytes(32, photo);

                        pst.execute();
                        System.out.println("Emp details");
                        getEmployeeID();
                        System.out.println("Employe ID");

                        //   **********      MET MCI Insertion      *************   //
                        if (rdTrnYes.isSelected()) {
                            pst2 = conn.prepareStatement(EmpMETMCI);

                            pst2.setString(1, ID);
                            pst2.setString(2, TrngCntrName.getText());
                            pst2.setString(3, TrngPlc.getText());
                            pst2.setString(4, TrngDate.getValue().toString());

                            pst2.execute();
                        }
                        System.out.println("METMCI");

                        //   **********      Ex-Army Data Insertion      *************   //
                        System.out.println("Exarmy Data");
                        if (ExArmyChk.isSelected()) {

                            pst3 = conn.prepareStatement(EmpExArmy);
                            pst3.setString(1, ID);
                            pst3.setString(2, GsDesg);
                            pst3.setString(3, GsInst);
                            pst3.setString(4, GsFrmPrd);
                            pst3.setString(5, GsToPrd);
                            pst3.execute();
                            pst3.clearParameters();

                            pst3 = conn.prepareStatement(EmpExArmy);
                            pst3.setString(1, ID);
                            pst3.setString(2, CsDesg);
                            pst3.setString(3, CsInst);
                            pst3.setString(4, CsFrmPrd);
                            pst3.setString(5, CsToPrd);
                            pst3.execute();
                            pst3.clearParameters();

                            pst3 = conn.prepareStatement(EmpExArmy);
                            pst3.setString(1, ID);
                            pst3.setString(2, AdvDesg);
                            pst3.setString(3, AdvInst);
                            pst3.setString(4, AdvFrmPrd);
                            pst3.setString(5, AdvToPrd);
                            pst3.execute();
                            pst3.clearParameters();
                        }

                        //   **********      Qualification Insertion      *************   //
                        System.out.println("Qualification");
                        pst4 = conn.prepareStatement(EmpQualification);

                        for (int i = 0; i <= 2; i++) {
                            pst4.setString(1, ID);
                            pst4.setString(2, Qualification.get(i));
                            pst4.setString(3, College.get(i));
                            pst4.setString(4, University.get(i));
                            pst4.setString(5, PassingYear.get(i));
                            pst4.setString(6, Registration.get(i));
                            pst4.setString(7, State.get(i));

                            pst4.execute();
//                            pst4.clearParameters();
                        }

                        //   **********      Experience Insertion      *************   //
                        System.out.println("Experience");
                        pst5 = conn.prepareStatement(EmpExperience);

                        for (int i = 0; i <= 5; i++) {
                            pst5.setString(1, ID);
                            pst5.setString(2, deg.get(i));
                            pst5.setString(3, dep.get(i));
                            pst5.setString(4, inst.get(i));
                            pst5.setString(5, fd.get(i));
                            pst5.setString(6, td.get(i));
                            pst5.setString(7, epr.get(i));

                            pst5.execute();
                        }

                        //   **********      Document Insertion      *************   //
                        System.out.println("DOcs");
                        pst6 = conn.prepareStatement(EmpDocs);
                        pst6.setString(1, ID);
                        pst6.setBytes(2, adrPrf);
                        pst6.setBytes(3, DegMB);
                        pst6.setBytes(4, DegPg);
                        pst6.setBytes(5, ApptOdr);
                        pst6.setBytes(6, ExperienceCert);
                        pst6.setBytes(7, RelievingOdr);
                        pst6.setBytes(8, JngReport);
                        pst6.setString(9, AdrPrfTxtFld.getText().trim());
                        pst6.setString(10, MBDegCertTxtFld.getText().trim());
                        pst6.setString(11, DegPGCertTxtFld.getText().trim());
                        pst6.setString(12, AptOdrTxtFld.getText().trim());
                        pst6.setString(13, ExpCertTxtFld.getText().trim());
                        pst6.setString(14, RelOdrTxtFld.getText().trim());
                        pst6.setString(15, JngRptTxtFld.getText().trim());
                        pst6.execute();

                        JOptionPane.showMessageDialog(null, "New Employee Record Added Sucessfully");

                        printRegistrationForm();

                        Tab1.setDisable(false);
                        Tab3.setDisable(false);
                        Tab4.setDisable(false);
                        AnchorForm1.setVisible(true);
                        AnchorForm2.setVisible(false);
                        AnchorForm3.setVisible(false);
                        AnchorForm4.setVisible(false);

                    } catch (SQLException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, e);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        //JOptionPane.showMessageDialog(null, e);
                    } finally {
                        try {
                            pst.close();
                            pst2.close();
                            pst3.close();
                            pst4.close();
                            pst5.close();
                            pst6.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, ex);
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            //JOptionPane.showMessageDialog(null, e);
                        }
                        updateTable();
                        resetElements();
                    }
                }
            }
        }
    }

    //   ******************      Logic for Converting for First Charachter of words with Space     ***************************   //
    public static String upperCase(String data) {
        String newString = "";
        String[] splitString = data.split(" ");
        for (int i = 0; i < splitString.length; i++) {
            newString = newString + splitString[i].substring(0, 1).toUpperCase()
                    + splitString[i].substring(1, splitString[i].length()).toLowerCase() + " ";
        }
        return newString;
    }

    public void resetElements() {

        //    Clear First Page    //
        fname.clear();
        mname.clear();
        lname.clear();
        dob.setValue(null);
        age.setText("");
        email.clear();
        mobile.clear();
        adl1.clear();
        adl2.clear();
        adl3.clear();
        adl4.clear();
        adl5.clear();
        ad1.clear();
        ad2.clear();
        ad3.clear();
        ad4.clear();
        ad5.clear();
        stdRes.clear();
        stdNum.clear();
        stdOff.clear();
        stdNum2.clear();
        AdrCheck.setSelected(false);
        addImg.setImage(null);
        rdMale.setSelected(false);
        rdFemale.setSelected(false);

        //    Clear Second Page    //
        JPIDate.setValue(null);
        JPDesgCombo.setValue("Select a Designation");
        presDesg.clear();
        presDept.clear();
        ntrApptCombo.setValue("Select an Option");
        rdTrnYes.setSelected(false);
        rdTrnNo.setSelected(true);
        TrngCntrName.clear();
        TrngPlc.clear();
        TrngDate.setValue(null);
        IntrJrnls.clear();
        NtnlJrnls.clear();
        St_IntJrnls.clear();
        ExArmyChk.setSelected(false);

        //    Clear Third Page    // 
        Qualification = null;
        College = null;
        University = null;
        PassingYear = null;
        Registration = null;
        State = null;

        deg.clear();
        dep.clear();
        inst.clear();
        fd.clear();
        td.clear();
        epr.clear();

        //    Clear Fourth Page    //
        adrPrfCombo.setValue("Select a Document");
        AdrPrfTxtFld.clear();
        MBDegCertTxtFld.clear();
        DegPGCertTxtFld.clear();
        AptOdrTxtFld.clear();
        ExpCertTxtFld.clear();
        RelOdrTxtFld.clear();
        JngRptTxtFld.clear();

        //    Setting Byte Arrays to Null    //
        photo = null;
        adrPrf = null;
        DegMB = null;
        DegPg = null;
        ApptOdr = null;
        ExperienceCert = null;
        RelievingOdr = null;
        JngReport = null;

        //    Setting Files to Null    //
        AdrPrfFile = null;
        MBBSCertFile = null;
        PGCertFile = null;
        ApptOrderFile = null;
        ExpCertFile = null;
        RelOdrFile = null;
        JnRepFile = null;
        ImgFile = null;

        //    Setting Input Streams to Null    //
        AdrPrfInptStrm = null;
        MBBSInptStrm = null;
        PGInptStrm = null;
        ApptOrderInptStrm = null;
        ExpCertInptStrm = null;
        RelOdrInptStrm = null;
        JnRepInptStrm = null;
        ImgInptStrm = null;
    }

    //   ********      Fetching ID from Employee_Details on Inserted Employee Data     **********  //
    public void getEmployeeID() {
        String sql = "select ID from Employee_Details where fname = '" + upperCase(NativeString.toLowerCase(fname.getText())).trim() + "' and mobile = '" + mobile.getText().trim() + "'";
        ResultSet resultset = null;
        try {
            Statement statement = conn.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                ID = resultset.getString("ID");
            }

            System.out.println("Employee ID : " + ID);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                resultset.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    //   ******************      Image Retrieving Logic      ***************************   //
    private void loadIMG(ActionEvent event) {
        File OutFile = null;
        Image img = null;
        FileOutputStream ImgOptStrm = null;
        try {
            byte[] buffer = new byte[1024];

            PreparedStatement pst = conn.prepareStatement("select photo from Employee_Details where id = 3");
            ResultSet rs = pst.executeQuery();

            ImgOptStrm = new FileOutputStream(OutFile);

            while (rs.next()) {
                InputStream input = rs.getBinaryStream("PHOTO");
                while (input.read(buffer) > 0) {
                    ImgOptStrm.write(buffer);
                }
            }
            img = new Image(OutFile.toURI().toString(), 120, 150, true, true);
            addImg.setImage(img);
            addImg.setFitWidth(120);
            addImg.setFitHeight(150);
            addImg.setPreserveRatio(true);
        } catch (IOException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void searchEmployee(ActionEvent event) {
        String sql = "select * from Employee_Details where fname = '" + txt_search.getText().trim() + "' or mobile = '" + txt_search.getText().trim() + "'";
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            number = rs.getString("ID");
            txt_fname.setText(rs.getString("FNAME"));
            txt_mname.setText(rs.getString("MNAME"));
            txt_lname.setText(rs.getString("LNAME"));
            txt_email.setText(rs.getString("EMAIL"));
            txt_mobile.setText(rs.getString("MOBILE"));
            txt_Dept.setText(rs.getString("JNDEPT"));
            txt_Desg.setText(rs.getString("PRESDESG"));
            txt_stdRes.setText(rs.getString("TELRESCODE"));
            txt_stdNum.setText(rs.getString("TELRES"));
            txt_stdOff.setText(rs.getString("TELOFFCODE"));
            txt_stdNum2.setText(rs.getString("TELOFF"));
            txt_adl1.setText(rs.getString("PRADL1"));
            txt_adl2.setText(rs.getString("PRADL2"));
            txt_adl3.setText(rs.getString("PRADL3"));
            txt_adl4.setText(rs.getString("PRADL4"));
            txt_adl5.setText(rs.getString("PRADL5"));
            txt_ad1.setText(rs.getString("PEADL1"));
            txt_ad2.setText(rs.getString("PEADL2"));
            txt_ad3.setText(rs.getString("PEALD3"));
            txt_ad4.setText(rs.getString("PEALD4"));
            txt_ad5.setText(rs.getString("PEALD5"));

            if (txt_ad1.equals(txt_adl1) && txt_ad2.equals(txt_adl2) && txt_ad3.equals(txt_adl3) && txt_ad4.equals(txt_adl4) && txt_ad5.equals(txt_adl5)) {
                chk_AdrCheck.setSelected(true);
            } else {
                chk_AdrCheck.setSelected(false);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No such Faculty Record Found ...!!");
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    @FXML
    private void changeService(ActionEvent event) {
        PreparedStatement pst = null;
        int x = JOptionPane.showConfirmDialog(null, "Are You sure you want to change the Service Status of the Selected Employee ..?", "Change Service Status of Employee", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (x == 0) {
            String setActive = "UPDATE Employee_Details set InService = 'Yes' where ID = '" + num + "'";
            String setInactive = "UPDATE Employee_Details set InService = 'No' where ID = '" + num + "'";
            try {
                int a = 0;
                if (DSS.getText().equals("In Service")) {
                    pst = conn.prepareStatement(setInactive);
                    a = pst.executeUpdate();
                } else if (DSS.getText().equals("Retired")) {
                    pst = conn.prepareStatement(setActive);
                    a = pst.executeUpdate();
                }
                if (a > 0) {
                    JOptionPane.showMessageDialog(null, "Service Status Updated ..!");
                    if (DSS.getText().equals("In Service")) {
                        DSS.setText("Retired");
                    } else if (DSS.getText().equals("Retired")) {
                        DSS.setText("In Service");
                    }
                }
            } catch (HeadlessException | SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex);
            } finally {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    ex.printStackTrace();
                }
            }
        }
        updateTable();
    }

    @FXML
    private void updtEmpDet(ActionEvent event) {
        PreparedStatement pst = null;
        if (txt_search.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No Faculty Selected..!!!\nPlease Enter a Valid First Name or Mobile Number of Faculty");
        } else {
            int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to Update Faculty Record ?", "Update Faculty Record", JOptionPane.YES_NO_OPTION);

            if (txt_email.getText().matches("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")) {
                if (txt_fname.getText().isEmpty() || txt_mname.getText().isEmpty() || txt_lname.getText().isEmpty() || txt_email.getText().isEmpty() || txt_mobile.getText().isEmpty() || txt_Dept.getText().isEmpty() || txt_Desg.getText().isEmpty() || txt_adl1.getText().isEmpty() || txt_adl2.getText().isEmpty() || txt_adl3.getText().isEmpty() || txt_adl4.getText().isEmpty() || txt_adl5.getText().isEmpty() || txt_ad1.getText().isEmpty() || txt_ad2.getText().isEmpty() || txt_ad3.getText().isEmpty() || txt_ad4.getText().isEmpty() || txt_ad5.getText().isEmpty() || !chk_AdrCheck.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Please Fill up all Fields\n\n(Telephone Numbers are not Mandatory)...!!");
                    x = 1;
                } else {
                    if (x == 0) {
                        try {
                            String sql = "UPDATE Employee_Details set FNAME = '" + upperCase(NativeString.toLowerCase(txt_fname.getText())).trim() + "', MNAME = '" + upperCase(NativeString.toLowerCase(txt_mname.getText())).trim() + "', LNAME = '" + upperCase(NativeString.toLowerCase(txt_lname.getText())).trim() + "', EMAIL = '" + txt_email.getText().trim() + "', MOBILE = '" + txt_mobile.getText().trim() + "', JNDEPT = '" + txt_Dept.getText().trim() + "', TELRESCODE = '" + txt_stdRes.getText().trim() + "', TELRES = '" + txt_stdNum.getText().trim() + "', TELOFFCODE = '" + txt_stdOff.getText().trim() + "', TELOFF = '" + txt_stdNum2.getText().trim() + "', PRESDESG = '" + txt_Desg.getText().trim() + "', PRADL1 = '" + txt_adl1.getText().trim() + "', PRADL2 = '" + txt_adl2.getText().trim() + "', PRADL3 = '" + txt_adl3.getText().trim() + "', PRADL4 = '" + txt_adl4.getText().trim() + "', PRADL5 = '" + txt_adl5.getText().trim() + "', PEADL1 = '" + txt_ad1.getText().trim() + "', PEADL2 = '" + txt_ad2.getText().trim() + "', PEALD3 = '" + txt_ad3.getText().trim() + "', PEALD4 = '" + txt_ad4.getText().trim() + "', PEALD5 = '" + txt_ad5.getText().trim() + "' where ID = '" + number + "'";

                            pst = conn.prepareStatement(sql);

                            int a = pst.executeUpdate();

                            if (a > 0) {
                                JOptionPane.showMessageDialog(null, "Faculty Record Updated Sucessfully");
                            }

                        } catch (HeadlessException | SQLException e) {
                            JOptionPane.showMessageDialog(null, e);
                        } finally {
                            try {
                                pst.close();
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, ex);
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please Enter valid Email..!!");
            }
        }
        updateTable();
    }

    @FXML
    private void searchFilter(KeyEvent event
    ) {
        String c = event.getCharacter();
        if (!c.matches("[A-Za-z0-9]")) {
            event.consume();
        }
    }

    @FXML
    private void switchPane(MouseEvent event
    ) {
        if (event.getSource() == updateEmpLbl) {
            IcSelShp1.setVisible(true);
            IcSelShp2.setVisible(false);
            UpdateForm.setVisible(true);
            ServiceForm.setVisible(false);
        } else if (event.getSource() == updateSrvcLbl) {
            IcSelShp2.setVisible(true);
            IcSelShp1.setVisible(false);
            UpdateForm.setVisible(false);
            ServiceForm.setVisible(true);
        }
    }

    private void showChildDialog(MouseEvent event) {
        ButtonGroup bg = new ButtonGroup();

        Object[] buttons = new Object[]{"Print Form", "Cancel"};

        JRadioButton Yes = new JRadioButton("In Service");
        JRadioButton No = new JRadioButton("Retired");

        Yes.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        No.setFont(new Font(Font.DIALOG, Font.BOLD, 15));

        bg.add(Yes);
        bg.add(No);

        Object[] Elements = {"Select an Option", " ", Yes, No};

        int result = JOptionPane.showOptionDialog(null, Elements, "Choose Employee Type", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
    }

    @FXML
    private void backupDB(MouseEvent event
    ) {
        String currentDir = System.getProperty("user.dir");
        currentDir = currentDir.replace("\\", "/");
        File sourceFile = new File(currentDir + "/Database/MedicalClgDB.sqlite");
        String destLoc;
        File desFile;
        JFileChooser j = new JFileChooser();

        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int r = j.showSaveDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {
            destLoc = j.getSelectedFile().getAbsolutePath();
            destLoc = destLoc.replace("\\", "/");
            desFile = new File(destLoc + "/MedicalClgDB.sqlite");
            Path abc = sourceFile.toPath();
            Path xyz = desFile.toPath();

            try {
                Files.copy(abc, xyz, StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(null, "Database Backup Successfully Completed\n\nLocation : " + xyz);
            } catch (IOException io) {
                JOptionPane.showMessageDialog(null, "Error Backing-Up Database..!!\n\nFile Not Found" + destLoc);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Unexpected Error encountered while Backing-Up Database..!!");
        }
        try {
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @FXML
    private void restoreDB(MouseEvent event
    ) {
        File srcFile, destFile;
        Path src, dest;
        String currentDir = System.getProperty("user.dir");

        JFileChooser j = new JFileChooser();
        j.setAcceptAllFileFilterUsed(false);
        j.setDialogTitle("Select Database File to Restore Backup");
        FileNameExtensionFilter restrict = new FileNameExtensionFilter(".sqlite", "sqlite");
        j.addChoosableFileFilter(restrict);
        int r = j.showOpenDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {
            srcFile = new File(j.getSelectedFile().getAbsolutePath());
            destFile = new File(currentDir + "/Database/MedicalClgDB.sqlite");
            src = srcFile.toPath();
            dest = destFile.toPath();

            try {
                Files.deleteIfExists(dest);
                Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(null, "Database Backup Successfully Completed\n\nLocation : " + dest);
            } catch (IOException io) {
                JOptionPane.showMessageDialog(null, "Error Restoring Backup Database..!!\n\nFile Not Found at : " + src);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Unexpected Error encountered while Restoring Backup Database..!!");
        }
    }

    @FXML
    private void searchEmp(ActionEvent event) {

        String sql = "select * from Employee_Details where fname = '" + D_Search.getText().trim() + "' or mobile = '" + D_Search.getText().trim() + "'";
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            num = rs.getString("ID");
            DFN.setText(rs.getString("FNAME") + " " + rs.getString("MNAME") + " " + rs.getString("LNAME"));
            DEM.setText(rs.getString("EMAIL"));
            DMob.setText(rs.getString("MOBILE"));
            DDP.setText(rs.getString("JNDEPT"));
            DDG.setText(rs.getString("PRESDESG"));
            if (rs.getString("InService").equals("Yes")) {
                DSS.setText("In Service");
            } else if (rs.getString("InService").equals("No")) {
                DSS.setText("Retired");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No such Faculty Record Found ...!!");
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    @FXML
    private void changePassword(MouseEvent event) {
        Object[] buttons = new Object[]{"Change Password", "Cancel"};

        JLabel l1 = new JLabel("Enter Current Password");
        JLabel l2 = new JLabel("Enter New Password");

        JPasswordField curPass = new JPasswordField();
        JPasswordField newPass = new JPasswordField();

        l1.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
        l2.setFont(new Font(Font.DIALOG, Font.BOLD, 13));

        curPass.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
        newPass.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));

        Object[] Elements = {"Change Admin Password", " ", l1, curPass, " ", l2, newPass};

        int result = JOptionPane.showOptionDialog(null, Elements, "Change Admin Password", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);

        if (result == 0) {
            String sql = "Select Password from Users where Username = 'admin'";
            String sql2 = "UPDATE Users set Password = ? where Username = 'admin'";

            PreparedStatement past = null, past1 = null;
            ResultSet rus = null;

            if (curPass.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Current Password");
            }
            if (newPass.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter New Password");
            }
            try {
                past = conn.prepareStatement(sql);
                rus = past.executeQuery();

                if (curPass.getText().trim().equals(rus.getString("Password"))) {
                    past1 = conn.prepareStatement(sql2);
                    past1.setString(1, newPass.getText().trim());
                    int y = past1.executeUpdate();
                    if (y > 0) {
                        JOptionPane.showMessageDialog(null, "Admin Password Changed");
                    } else {
                        JOptionPane.showMessageDialog(null, "Admin Password not Changed");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Current Password\n\nUnable to Change Password");
                }
            } catch (HeadlessException | SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error Changing the Admin Password");
            } finally {
                try {
                    rus.close();
                    past.close();
                    past1.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
    }

    public void printRegistrationForm() {
        JFileChooser saveDialog = new JFileChooser();
        saveDialog.setSelectedFile(new File(ID + " - " + upperCase(NativeString.toLowerCase(fname.getText())).trim() + " " + upperCase(NativeString.toLowerCase(lname.getText()).trim()) + ".pdf"));
//        saveDialog.setSelectedFile(new File("Grant.pdf"));
        int dialogResult = saveDialog.showSaveDialog(null);
        String filePath = saveDialog.getSelectedFile().getPath();
        if (dialogResult == 0) {
            try {
                int i = 1;
                String LinGa = "", Judge = "";
                DateFormat df = new SimpleDateFormat("dd MMM yyyy");
                DateFormat df1 = new SimpleDateFormat("dd MMMM yyyy");
                Document myDoc = new Document(PageSize.A4);
                myDoc.addAuthor("Admin - MGM Medical College");
                myDoc.addCreator("Admin - MGMMC Employee Registration");
                PdfWriter myWriter = PdfWriter.getInstance(myDoc, new FileOutputStream(filePath));

                myDoc.open();

                PdfPTable LogoName = new PdfPTable(2);
                float[] column = new float[]{2, 10};
                LogoName.setWidths(column);
                LogoName.setWidthPercentage(100);

                com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("MGMLogo.png");
                PdfPCell logo = new PdfPCell(img, true);
                logo.setRowspan(2);

                PdfPCell Head1 = new PdfPCell(new Paragraph("           MAHATMA GANDHI MISSION'S\n                    MEDICAL COLLEGE", FontFactory.getFont(FontFactory.TIMES_ROMAN, 21, Font.BOLD)));
                PdfPCell Head2 = new PdfPCell(new Paragraph("                         NAVI MUMBAI", FontFactory.getFont(FontFactory.TIMES_ROMAN, 21, Font.BOLD)));

                LogoName.addCell(logo).setBorderWidth(0);
                LogoName.addCell(Head1).setBorderWidth(0);
                LogoName.addCell(Head2).setBorderWidth(0);

                myDoc.add(LogoName);

                myDoc.add(new Paragraph("            FACULTY REGISTRATION FORM", FontFactory.getFont(FontFactory.COURIER, 18, Font.BOLD)));
                myDoc.add(new Paragraph("       "));
                myDoc.add(new Paragraph("       "));

                //   ***********   Geting the Uploaded Image from ImageView & Converting to IText Image    ***********//
                ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
                ImageIO.write(SwingFXUtils.fromFXImage(addImg.getImage(), null), "png", byteOutput);
                com.itextpdf.text.Image Photo = com.itextpdf.text.Image.getInstance(byteOutput.toByteArray());

                PdfPTable Basic = new PdfPTable(3);
                Basic.setWidths(new float[]{3, 6, 6});
                Basic.setWidthPercentage(100);

//                com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance("Photo.jpg");
                PdfPCell pht = new PdfPCell(Photo, true);
                pht.setRowspan(5);

                PdfPCell nm = new PdfPCell(new Paragraph("    Full Name          :  " + upperCase(NativeString.toLowerCase(fname.getText())).trim() + " " + upperCase(NativeString.toLowerCase(mname.getText())).trim() + " " + upperCase(NativeString.toLowerCase(lname.getText())).trim()));
                nm.setColspan(2);
                nm.setPaddingTop(6.5f);
                nm.setPaddingBottom(6.5f);

                PdfPCell db = new PdfPCell(new Paragraph("    Date of Birth      :  " + df.format(Date.from(dob.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))));
                db.setPaddingTop(6.5f);
                db.setPaddingBottom(6.5f);

                PdfPCell ag = new PdfPCell(new Paragraph("  Age             :  " + age.getText()));
                ag.setPaddingTop(6.5f);
                ag.setPaddingBottom(6.5f);

                PdfPCell mo = new PdfPCell(new Paragraph("    Mobile No.         :  " + mobile.getText().trim()));
                mo.setPaddingTop(6.5f);
                mo.setPaddingBottom(6.5f);

                if (rdMale.isSelected()) {
                    LinGa = "Male";
                } else if (rdFemale.isSelected()) {
                    LinGa = "Female";
                }

                PdfPCell gn = new PdfPCell(new Paragraph("  Gender       :  " + LinGa));
                gn.setPaddingTop(6.5f);
                gn.setPaddingBottom(6.5f);

                PdfPCell tr = new PdfPCell(new Paragraph("    Tel (Residence) :  " + stdRes.getText().trim() + "-" + stdNum.getText().trim()));
                tr.setPaddingTop(6.5f);
                tr.setPaddingBottom(6.5f);

                PdfPCell to = new PdfPCell(new Paragraph("  Tel (Office) :  " + stdOff.getText().trim() + "-" + stdNum2.getText().trim()));
                to.setPaddingTop(6.5f);
                to.setPaddingBottom(6.5f);

                PdfPCell em = new PdfPCell(new Paragraph("    Email Address   :  " + email.getText().trim()));
                em.setColspan(2);
                em.setPaddingTop(6.5f);
                em.setPaddingBottom(6.5f);

                Basic.addCell(pht).setBorderWidth(0);
                Basic.addCell(nm).setBorderWidth(0);
                Basic.addCell(db).setBorderWidth(0);
                Basic.addCell(ag).setBorderWidth(0);
                Basic.addCell(mo).setBorderWidth(0);
                Basic.addCell(gn).setBorderWidth(0);
                Basic.addCell(tr).setBorderWidth(0);
                Basic.addCell(to).setBorderWidth(0);
                Basic.addCell(em).setBorderWidth(0);

                myDoc.add(Basic);

                PdfPTable Address = new PdfPTable(2);
                Address.setWidthPercentage(100);

                PdfPCell adr1 = new PdfPCell(new Paragraph("Present Residential Address :"));
                adr1.setPaddingTop(15);
                adr1.setPaddingBottom(5);

                PdfPCell adr2 = new PdfPCell(new Paragraph("Permanent Residential Address :"));
                adr2.setPaddingTop(15);
                adr2.setPaddingBottom(5);

                PdfPCell adrl1 = new PdfPCell(new Paragraph(adl1.getText().trim()));
                adrl1.setPaddingTop(5);
                adrl1.setPaddingBottom(2);
                adrl1.setNoWrap(false);

                PdfPCell adrl_1 = new PdfPCell(new Paragraph(ad1.getText().trim()));
                adrl_1.setPaddingTop(5);
                adrl_1.setPaddingBottom(2);
                adrl_1.setNoWrap(false);

                PdfPCell adrl2 = new PdfPCell(new Paragraph(adl2.getText().trim()));
                adrl2.setPaddingTop(2);
                adrl2.setPaddingBottom(2);
                adrl2.setNoWrap(false);

                PdfPCell adrl_2 = new PdfPCell(new Paragraph(ad2.getText().trim()));
                adrl_2.setPaddingTop(2);
                adrl_2.setPaddingBottom(2);
                adrl_2.setNoWrap(false);

                PdfPCell adrl3 = new PdfPCell(new Paragraph(adl3.getText().trim()));
                adrl3.setPaddingTop(2);
                adrl3.setPaddingBottom(2);

                PdfPCell adrl_3 = new PdfPCell(new Paragraph(ad3.getText().trim()));
                adrl_3.setPaddingTop(2);
                adrl_3.setPaddingBottom(2);

                PdfPCell adrl4 = new PdfPCell(new Paragraph(adl4.getText().trim()));
                adrl4.setPaddingTop(2);
                adrl4.setPaddingBottom(2);
                adrl4.setNoWrap(false);

                PdfPCell adrl_4 = new PdfPCell(new Paragraph(ad4.getText().trim()));
                adrl_4.setPaddingTop(2);
                adrl_4.setPaddingBottom(2);
                adrl_4.setNoWrap(false);

                PdfPCell adrl5 = new PdfPCell(new Paragraph(adl5.getText().trim()));
                adrl5.setPaddingTop(2);
                adrl5.setPaddingBottom(2);

                PdfPCell adrl_5 = new PdfPCell(new Paragraph(ad5.getText().trim()));
                adrl_5.setPaddingTop(2);
                adrl_5.setPaddingBottom(2);

                Address.addCell(adr1).setBorderWidth(0);
                Address.addCell(adr2).setBorderWidth(0);
                Address.addCell(adrl1).setBorderWidth(0);
                Address.addCell(adrl_1).setBorderWidth(0);
                Address.addCell(adrl2).setBorderWidth(0);
                Address.addCell(adrl_2).setBorderWidth(0);
                Address.addCell(adrl3).setBorderWidth(0);
                Address.addCell(adrl_3).setBorderWidth(0);
                Address.addCell(adrl4).setBorderWidth(0);
                Address.addCell(adrl_4).setBorderWidth(0);
                Address.addCell(adrl5).setBorderWidth(0);
                Address.addCell(adrl_5).setBorderWidth(0);

                myDoc.add(Address);

                myDoc.add(new Paragraph("       "));

                PdfPTable Details = new PdfPTable(2);
                Details.setWidthPercentage(100);

                PdfPCell JnDate = new PdfPCell(new Paragraph("Date of Joining Present Institution"));
                JnDate.setPaddingTop(8);
                JnDate.setPaddingBottom(6);

                PdfPCell JnDesg = new PdfPCell(new Paragraph("Joining Designation in Present Institution"));
                JnDesg.setPaddingTop(6);
                JnDesg.setPaddingBottom(6);

                PdfPCell PresDesg = new PdfPCell(new Paragraph("Present Designation"));
                PresDesg.setPaddingTop(6);
                PresDesg.setPaddingBottom(6);

                PdfPCell PresDept = new PdfPCell(new Paragraph("Department"));
                PresDept.setPaddingTop(6);
                PresDept.setPaddingBottom(6);

                PdfPCell NtrApt = new PdfPCell(new Paragraph("Nature of Appointment"));
                NtrApt.setPaddingTop(6);
                NtrApt.setPaddingBottom(8);

                PdfPCell JnDate1 = new PdfPCell(new Paragraph(":  " + df1.format(Date.from(JPIDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))));
                JnDate1.setPaddingTop(8);
                JnDate1.setPaddingBottom(6);

                PdfPCell JnDesg1 = new PdfPCell(new Paragraph(":  " + JPDesgCombo.getValue()));
                JnDesg1.setPaddingTop(6);
                JnDesg1.setPaddingBottom(6);

                PdfPCell PresDesg1 = new PdfPCell(new Paragraph(":  " + upperCase(NativeString.toLowerCase(presDesg.getText())).trim()));
                PresDesg1.setPaddingTop(6);
                PresDesg1.setPaddingBottom(6);

                PdfPCell PresDept1 = new PdfPCell(new Paragraph(":  " + upperCase(NativeString.toLowerCase(presDept.getText())).trim()));
                PresDept1.setPaddingTop(6);
                PresDept1.setPaddingBottom(6);

                PdfPCell NtrApt1 = new PdfPCell(new Paragraph(":  " + ntrApptCombo.getValue()));
                NtrApt1.setPaddingTop(6);
                NtrApt1.setPaddingBottom(8);

                Details.addCell(JnDate).setBorderWidth(0);
                Details.addCell(JnDate1).setBorderWidth(0);
                Details.addCell(JnDesg).setBorderWidth(0);
                Details.addCell(JnDesg1).setBorderWidth(0);
                Details.addCell(PresDesg).setBorderWidth(0);
                Details.addCell(PresDesg1).setBorderWidth(0);
                Details.addCell(PresDept).setBorderWidth(0);
                Details.addCell(PresDept1).setBorderWidth(0);
                Details.addCell(NtrApt).setBorderWidth(0);
                Details.addCell(NtrApt1).setBorderWidth(0);

                myDoc.add(Details);

                myDoc.add(new Paragraph("       "));

                PdfPTable mt = new PdfPTable(2);
                mt.setWidths(new float[]{3, 6});
                mt.setWidthPercentage(100);

                PdfPCell mt1 = new PdfPCell(new Paragraph("Have you undergone Training in MET at MCI Regional Centre or in your college under Regional Centre observation ?"));
                mt1.setColspan(2);
                mt1.setPaddingTop(5);
                mt1.setPaddingBottom(8);
                mt1.setNoWrap(false);

                PdfPCell mt2 = new PdfPCell(new Paragraph("Yes / No"));
                mt2.setPaddingTop(5);
                mt2.setPaddingBottom(5);

                if (rdTrnYes.isSelected()) {
                    Judge = "Yes";
                } else if (rdTrnNo.isSelected()) {
                    Judge = "No";
                }

                PdfPCell mt3 = new PdfPCell(new Paragraph("->  " + Judge));
                mt3.setPaddingTop(5);
                mt3.setPaddingBottom(5);

                PdfPCell mt4 = new PdfPCell(new Paragraph("Name of MCI Regional Centre"));
                mt4.setPaddingTop(5);
                mt4.setPaddingBottom(5);

                PdfPCell mt5 = new PdfPCell(new Paragraph(":  " + TrngCntrName.getText().trim()));
                mt5.setPaddingTop(5);
                mt5.setPaddingBottom(5);
                mt5.setNoWrap(false);

                PdfPCell mt6 = new PdfPCell(new Paragraph("Place of Training"));
                mt6.setPaddingTop(5);
                mt6.setPaddingBottom(5);

                PdfPCell mt7 = new PdfPCell(new Paragraph(":  " + TrngPlc.getText().trim()));
                mt7.setPaddingTop(5);
                mt7.setPaddingBottom(5);

                PdfPCell mt8 = new PdfPCell(new Paragraph("Date of Training"));
                mt8.setPaddingTop(5);
                mt8.setPaddingBottom(5);

                PdfPCell mt9;
                if (TrngDate.getValue() == null) {
                    mt9 = new PdfPCell(new Paragraph(":  "));
                    mt9.setPaddingTop(5);
                    mt9.setPaddingBottom(5);
                } else {
                    mt9 = new PdfPCell(new Paragraph(":  " + df1.format(Date.from(TrngDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()))));
                    mt9.setPaddingTop(5);
                    mt9.setPaddingBottom(5);
                }

                mt.addCell(mt1).setBorderWidth(0);
                mt.addCell(mt2).setBorderWidth(0);
                mt.addCell(mt3).setBorderWidth(0);
                mt.addCell(mt4).setBorderWidth(0);
                mt.addCell(mt5).setBorderWidth(0);
                mt.addCell(mt8).setBorderWidth(0);
                mt.addCell(mt9).setBorderWidth(0);
                mt.addCell(mt6).setBorderWidth(0);
                mt.addCell(mt7).setBorderWidth(0);

                myDoc.add(mt);

                myDoc.newPage();

                myDoc.add(new Paragraph("    "));
                myDoc.add(new Paragraph("    "));

                PdfPTable quli = new PdfPTable(6);
                quli.setWidthPercentage(100);
                quli.setWidths(new float[]{4, 6, 5, 3, 6, 5});

                PdfPCell h1 = new PdfPCell(new Paragraph("Qualification", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD)));
                h1.setPaddingBottom(5);
                h1.setNoWrap(false);
                h1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                h1.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell h2 = new PdfPCell(new Paragraph("College", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD)));
                h2.setPaddingBottom(5);
                h2.setNoWrap(false);
                h2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                h2.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell h3 = new PdfPCell(new Paragraph("University", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD)));
                h3.setPaddingBottom(5);
                h3.setNoWrap(false);
                h3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                h3.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell h4 = new PdfPCell(new Paragraph("Passing Year", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD)));
                h4.setPaddingBottom(5);
                h4.setNoWrap(false);
                h4.setVerticalAlignment(Element.ALIGN_MIDDLE);
                h4.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell h5 = new PdfPCell(new Paragraph("Registration", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD)));
                h5.setPaddingBottom(5);
                h5.setNoWrap(false);
                h5.setVerticalAlignment(Element.ALIGN_MIDDLE);
                h5.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell h6 = new PdfPCell(new Paragraph("Name of the State Medical Council", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD)));
                h6.setPaddingBottom(5);
                h6.setNoWrap(false);
                h6.setVerticalAlignment(Element.ALIGN_MIDDLE);
                h6.setHorizontalAlignment(Element.ALIGN_CENTER);

                quli.addCell(h1).setBorderWidth(2);
                quli.addCell(h2).setBorderWidth(2);
                quli.addCell(h3).setBorderWidth(2);
                quli.addCell(h4).setBorderWidth(2);
                quli.addCell(h5).setBorderWidth(2);
                quli.addCell(h6).setBorderWidth(2);
                quli.completeRow();

                for (int c = 0; c <= 2; c++) {
                    quli.addCell(new PdfPCell(new Paragraph(Qualification.get(c), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                    System.out.println("Qual : " + c + " - " + Qualification.get(c));
                    quli.addCell(new PdfPCell(new Paragraph(College.get(c), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                    System.out.println("Qual Clg : " + c + " - " + College.get(c));
                    quli.addCell(new PdfPCell(new Paragraph(University.get(c), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                    System.out.println("Qual Univ : " + c + " - " + University.get(c));
                    quli.addCell(new PdfPCell(new Paragraph(PassingYear.get(c), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                    System.out.println("Qual PsngYr : " + c + " - " + PassingYear.get(c));
                    quli.addCell(new PdfPCell(new Paragraph(Registration.get(c), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                    System.out.println("Qual Reg : " + c + " - " + Registration.get(c));
                    quli.addCell(new PdfPCell(new Paragraph(State.get(c), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                    System.out.println("Qual State : " + c + " - " + State.get(c));
//                    quli.completeRow();
                }

                myDoc.add(quli);
                myDoc.add(new Paragraph("    "));

                PdfPTable xpr = new PdfPTable(6);
                xpr.setWidthPercentage(100);
                xpr.setWidths(new float[]{4, 5, 6, 3, 3, 4});

                PdfPCell he1 = new PdfPCell(new Paragraph("Designation", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD)));
                he1.setPaddingBottom(5);
                he1.setNoWrap(false);
                he1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                he1.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell he2 = new PdfPCell(new Paragraph("Department", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD)));
                he2.setPaddingBottom(5);
                he2.setNoWrap(false);
                he2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                he2.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell he3 = new PdfPCell(new Paragraph("Name of Institution", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD)));
                he3.setPaddingBottom(5);
                he3.setNoWrap(false);
                he3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                he3.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell he4 = new PdfPCell(new Paragraph("From\nDate", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD)));
                he4.setPaddingBottom(5);
                he4.setNoWrap(false);
                he4.setVerticalAlignment(Element.ALIGN_MIDDLE);
                he4.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell he5 = new PdfPCell(new Paragraph("To\nDate", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD)));
                he5.setPaddingBottom(5);
                he5.setNoWrap(false);
                he5.setVerticalAlignment(Element.ALIGN_MIDDLE);
                he5.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell he6 = new PdfPCell(new Paragraph("Total Exprience\n(Years & Months)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD)));
                he6.setPaddingBottom(5);
                he6.setNoWrap(false);
                he6.setVerticalAlignment(Element.ALIGN_MIDDLE);
                he6.setHorizontalAlignment(Element.ALIGN_CENTER);

                xpr.addCell(he1).setBorderWidth(2);
                xpr.addCell(he2).setBorderWidth(2);
                xpr.addCell(he3).setBorderWidth(2);
                xpr.addCell(he4).setBorderWidth(2);
                xpr.addCell(he5).setBorderWidth(2);
                xpr.addCell(he6).setBorderWidth(2);
                xpr.completeRow();

                for (int m = 0; m <= 5; m++) {
                    xpr.addCell(new PdfPCell(new Paragraph(deg.get(m), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                    xpr.addCell(new PdfPCell(new Paragraph(dep.get(m), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                    xpr.addCell(new PdfPCell(new Paragraph(inst.get(m), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                    xpr.addCell(new PdfPCell(new Paragraph(fd.get(m), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                    xpr.addCell(new PdfPCell(new Paragraph(td.get(m), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                    xpr.addCell(new PdfPCell(new Paragraph(epr.get(m), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
//                    xpr.completeRow();
                }

                myDoc.add(xpr);
                myDoc.add(new Paragraph("    "));
                myDoc.add(new Paragraph("    "));

                myDoc.add(new Paragraph("            Number of Research Publications in Index Journals :"));
                myDoc.add(new Paragraph("         (NOTE :  Only Original Research Papers)", FontFactory.getFont(FontFactory.COURIER, 11, Font.PLAIN)));

                PdfPTable jnl = new PdfPTable(3);
                jnl.setWidthPercentage(70);
                jnl.setWidths(new float[]{1, 5, 4});

                PdfPCell a1 = new PdfPCell(new Paragraph("1)"));
                a1.setPaddingTop(10);
                a1.setPaddingBottom(5);

                PdfPCell l1 = new PdfPCell(new Paragraph("International Journals"));
                l1.setPaddingTop(10);
                l1.setPaddingBottom(5);

                PdfPCell b1 = new PdfPCell(new Paragraph(":  " + IntrJrnls.getText()));
                b1.setPaddingTop(10);
                b1.setPaddingBottom(5);

                PdfPCell a2 = new PdfPCell(new Paragraph("2)"));
                a2.setPaddingTop(5);
                a2.setPaddingBottom(5);

                PdfPCell l2 = new PdfPCell(new Paragraph("National Journals"));
                l2.setPaddingTop(5);
                l2.setPaddingBottom(5);

                PdfPCell b2 = new PdfPCell(new Paragraph(":  " + NtnlJrnls.getText()));
                b2.setPaddingTop(5);
                b2.setPaddingBottom(5);

                PdfPCell a3 = new PdfPCell(new Paragraph("3)"));
                a3.setPaddingTop(5);
                a3.setPaddingBottom(5);

                PdfPCell l3 = new PdfPCell(new Paragraph("State / Institutional Journals"));
                l3.setPaddingTop(5);
                l3.setPaddingBottom(5);

                PdfPCell b3 = new PdfPCell(new Paragraph(":  " + St_IntJrnls.getText()));
                b3.setPaddingTop(5);
                b3.setPaddingBottom(5);

                jnl.addCell(a1).setBorderWidth(0);
                jnl.addCell(l1).setBorderWidth(0);
                jnl.addCell(b1).setBorderWidth(0);
                jnl.completeRow();
                jnl.addCell(a2).setBorderWidth(0);
                jnl.addCell(l2).setBorderWidth(0);
                jnl.addCell(b2).setBorderWidth(0);
                jnl.completeRow();
                jnl.addCell(a3).setBorderWidth(0);
                jnl.addCell(l3).setBorderWidth(0);
                jnl.addCell(b3).setBorderWidth(0);
                jnl.completeRow();

                myDoc.add(jnl);

//                myDoc.newPage();
                myDoc.add(new Paragraph("    "));
                myDoc.add(new Paragraph("    "));
                myDoc.add(new Paragraph("    "));

                myDoc.add(new Paragraph("    For Ex Army Personnel Only :"));
                myDoc.add(new Paragraph("  "));

                PdfPTable ar = new PdfPTable(5);
                ar.setWidthPercentage(90);
                ar.setWidths(new float[]{2, 6, 10, 5, 5});

                PdfPCell ar1 = new PdfPCell(new Paragraph("Sr. No.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
                ar1.setPaddingTop(10);
                ar1.setPaddingBottom(5);
                ar1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ar1.setHorizontalAlignment(Element.ALIGN_CENTER);
                ar1.setRowspan(2);

                PdfPCell ar2 = new PdfPCell(new Paragraph("Designation", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
                ar2.setPaddingTop(10);
                ar2.setPaddingBottom(5);
                ar2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ar2.setHorizontalAlignment(Element.ALIGN_CENTER);
                ar2.setRowspan(2);

                PdfPCell ar3 = new PdfPCell(new Paragraph("Institution", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
                ar3.setPaddingTop(10);
                ar3.setPaddingBottom(5);
                ar3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ar3.setHorizontalAlignment(Element.ALIGN_CENTER);
                ar3.setRowspan(2);

                PdfPCell ar4 = new PdfPCell(new Paragraph("Period", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
                ar4.setPaddingTop(10);
                ar4.setVerticalAlignment(Element.ALIGN_TOP);
                ar4.setHorizontalAlignment(Element.ALIGN_CENTER);
                ar4.setColspan(2);

                PdfPCell ar5 = new PdfPCell(new Paragraph("From", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
                ar5.setPaddingBottom(5);
                ar5.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ar5.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell ar6 = new PdfPCell(new Paragraph("To", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
                ar6.setPaddingBottom(5);
                ar6.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ar6.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell sr1 = new PdfPCell(new Paragraph("1)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                sr1.setPaddingTop(5);
                sr1.setPaddingBottom(5);
                sr1.setHorizontalAlignment(Element.ALIGN_CENTER);
                sr1.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell d1 = new PdfPCell(new Paragraph(GsDesg, FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                d1.setPaddingTop(5);
                d1.setPaddingBottom(5);
                d1.setHorizontalAlignment(Element.ALIGN_LEFT);
                d1.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell i1 = new PdfPCell(new Paragraph(GsInst, FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                i1.setPaddingTop(5);
                i1.setPaddingBottom(5);
                i1.setHorizontalAlignment(Element.ALIGN_LEFT);
                i1.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell f1 = new PdfPCell(new Paragraph(GsFrmPrd, FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                f1.setPaddingTop(5);
                f1.setPaddingBottom(5);
                f1.setHorizontalAlignment(Element.ALIGN_CENTER);
                f1.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell t1 = new PdfPCell(new Paragraph(GsToPrd, FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                t1.setPaddingTop(5);
                t1.setPaddingBottom(5);
                t1.setHorizontalAlignment(Element.ALIGN_CENTER);
                t1.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell sr2 = new PdfPCell(new Paragraph("2)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                sr2.setPaddingTop(5);
                sr2.setPaddingBottom(5);
                sr2.setHorizontalAlignment(Element.ALIGN_CENTER);
                sr2.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell d2 = new PdfPCell(new Paragraph(CsDesg, FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                d2.setPaddingTop(5);
                d2.setPaddingBottom(5);
                d2.setHorizontalAlignment(Element.ALIGN_LEFT);
                d2.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell i2 = new PdfPCell(new Paragraph(CsInst, FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                i2.setPaddingTop(5);
                i2.setPaddingBottom(5);
                i2.setHorizontalAlignment(Element.ALIGN_LEFT);
                i2.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell f2 = new PdfPCell(new Paragraph(CsFrmPrd, FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                f2.setPaddingTop(5);
                f2.setPaddingBottom(5);
                f2.setHorizontalAlignment(Element.ALIGN_CENTER);
                f2.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell t2 = new PdfPCell(new Paragraph(CsToPrd, FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                t2.setPaddingTop(5);
                t2.setPaddingBottom(5);
                t2.setHorizontalAlignment(Element.ALIGN_CENTER);
                t2.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell sr3 = new PdfPCell(new Paragraph("3)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                sr3.setPaddingTop(5);
                sr3.setPaddingBottom(5);
                sr3.setHorizontalAlignment(Element.ALIGN_CENTER);
                sr3.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell d3 = new PdfPCell(new Paragraph(AdvDesg, FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                d3.setPaddingTop(5);
                d3.setPaddingBottom(5);
                d3.setHorizontalAlignment(Element.ALIGN_LEFT);
                d3.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell i3 = new PdfPCell(new Paragraph(AdvInst, FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                i3.setPaddingTop(5);
                i3.setPaddingBottom(5);
                i3.setHorizontalAlignment(Element.ALIGN_LEFT);
                i3.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell f3 = new PdfPCell(new Paragraph(AdvFrmPrd, FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                f3.setPaddingTop(5);
                f3.setPaddingBottom(5);
                f3.setHorizontalAlignment(Element.ALIGN_CENTER);
                f3.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell t3 = new PdfPCell(new Paragraph(AdvToPrd, FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                t3.setPaddingTop(5);
                t3.setPaddingBottom(5);
                t3.setHorizontalAlignment(Element.ALIGN_CENTER);
                t3.setVerticalAlignment(Element.ALIGN_MIDDLE);

                ar.addCell(ar1).setBorderWidth(2);
                ar.addCell(ar2).setBorderWidth(2);
                ar.addCell(ar3).setBorderWidth(2);
                ar.addCell(ar4).setBorderWidth(2);
                ar.addCell(ar5).setBorderWidth(2);
                ar.addCell(ar6).setBorderWidth(2);

                ar.addCell(sr1).setBorderWidth(1);
                ar.addCell(d1).setBorderWidth(1);
                ar.addCell(i1).setBorderWidth(1);
                ar.addCell(f1).setBorderWidth(1);
                ar.addCell(t1).setBorderWidth(1);

                ar.addCell(sr2).setBorderWidth(1);
                ar.addCell(d2).setBorderWidth(1);
                ar.addCell(i2).setBorderWidth(1);
                ar.addCell(f2).setBorderWidth(1);
                ar.addCell(t2).setBorderWidth(1);

                ar.addCell(sr3).setBorderWidth(1);
                ar.addCell(d3).setBorderWidth(1);
                ar.addCell(i3).setBorderWidth(1);
                ar.addCell(f3).setBorderWidth(1);
                ar.addCell(t3).setBorderWidth(1);

                myDoc.add(ar);

                myDoc.newPage();

                myDoc.add(new Paragraph("  "));
                myDoc.add(new Paragraph("    "));

                myDoc.add(new Paragraph("         Documents Enclosed :"));
                PdfPTable docs = new PdfPTable(4);
                docs.setWidthPercentage(90);
                docs.setWidths(new float[]{3, 10, 8, 1});

                myDoc.add(new Paragraph("  "));

                PdfPCell blank = new PdfPCell(new Paragraph(" "));
                blank.setPaddingTop(5);
                blank.setPaddingBottom(5);

                com.itextpdf.text.Image tick = com.itextpdf.text.Image.getInstance("src/Images/check-mark.png");
                PdfPCell chk = new PdfPCell(tick, true);
                chk.setPaddingTop(5);
                chk.setPaddingBottom(5);
                chk.setPaddingLeft(5);
                chk.setVerticalAlignment(Element.ALIGN_MIDDLE);
                chk.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell head1 = new PdfPCell(new Paragraph("Sr. No.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.BOLD)));
                head1.setPaddingTop(10);
                head1.setPaddingBottom(5);
                head1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                head1.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell head2 = new PdfPCell(new Paragraph("Document", FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.BOLD)));
                head2.setPaddingTop(10);
                head2.setPaddingBottom(5);
                head2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                head2.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell head3 = new PdfPCell(new Paragraph("Document Number", FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.BOLD)));
                head3.setPaddingTop(10);
                head3.setPaddingBottom(5);
                head3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                head3.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell head4 = new PdfPCell(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.BOLD)));
                head4.setPaddingTop(10);
                head4.setPaddingBottom(5);
                head4.setVerticalAlignment(Element.ALIGN_MIDDLE);
                head4.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell sn1 = new PdfPCell(new Paragraph("1)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
                sn1.setPaddingTop(5);
                sn1.setPaddingBottom(5);
                sn1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                sn1.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell do1 = new PdfPCell(new Paragraph("Address Proof - " + adrPrfCombo.getValue(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                do1.setPaddingTop(5);
                do1.setPaddingBottom(5);
                do1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                do1.setHorizontalAlignment(Element.ALIGN_LEFT);

                PdfPCell dn1 = new PdfPCell(new Paragraph(AdrPrfTxtFld.getText(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                dn1.setPaddingTop(5);
                dn1.setPaddingBottom(5);
                dn1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                dn1.setHorizontalAlignment(Element.ALIGN_LEFT);

                PdfPCell sn2 = new PdfPCell(new Paragraph("2)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
                sn2.setPaddingTop(5);
                sn2.setPaddingBottom(5);
                sn2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                sn2.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell do2 = new PdfPCell(new Paragraph("MBBS Certificate", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                do2.setPaddingTop(5);
                do2.setPaddingBottom(5);
                do2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                do2.setHorizontalAlignment(Element.ALIGN_LEFT);

                PdfPCell dn2 = new PdfPCell(new Paragraph(MBDegCertTxtFld.getText(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                dn2.setPaddingTop(5);
                dn2.setPaddingBottom(5);
                dn2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                dn2.setHorizontalAlignment(Element.ALIGN_LEFT);

                PdfPCell sn3 = new PdfPCell(new Paragraph("3)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
                sn3.setPaddingTop(5);
                sn3.setPaddingBottom(5);
                sn3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                sn3.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell do3 = new PdfPCell(new Paragraph("PG Certificate", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                do3.setPaddingTop(5);
                do3.setPaddingBottom(5);
                do3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                do3.setHorizontalAlignment(Element.ALIGN_LEFT);

                PdfPCell dn3 = new PdfPCell(new Paragraph(DegPGCertTxtFld.getText(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                dn3.setPaddingTop(5);
                dn3.setPaddingBottom(5);
                dn3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                dn3.setHorizontalAlignment(Element.ALIGN_LEFT);

                PdfPCell sn4 = new PdfPCell(new Paragraph("4)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
                sn4.setPaddingTop(5);
                sn4.setPaddingBottom(5);
                sn4.setVerticalAlignment(Element.ALIGN_MIDDLE);
                sn4.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell do4 = new PdfPCell(new Paragraph("Appointment Order", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                do4.setPaddingTop(5);
                do4.setPaddingBottom(5);
                do4.setVerticalAlignment(Element.ALIGN_MIDDLE);
                do4.setHorizontalAlignment(Element.ALIGN_LEFT);

                PdfPCell dn4 = new PdfPCell(new Paragraph(AptOdrTxtFld.getText(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                dn4.setPaddingTop(5);
                dn4.setPaddingBottom(5);
                dn4.setVerticalAlignment(Element.ALIGN_MIDDLE);
                dn4.setHorizontalAlignment(Element.ALIGN_LEFT);

                PdfPCell sn5 = new PdfPCell(new Paragraph("5)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
                sn5.setPaddingTop(5);
                sn5.setPaddingBottom(5);
                sn5.setVerticalAlignment(Element.ALIGN_MIDDLE);
                sn5.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell do5 = new PdfPCell(new Paragraph("Experience Certificate", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                do5.setPaddingTop(5);
                do5.setPaddingBottom(5);
                do5.setVerticalAlignment(Element.ALIGN_MIDDLE);
                do5.setHorizontalAlignment(Element.ALIGN_LEFT);

                PdfPCell dn5 = new PdfPCell(new Paragraph(ExpCertTxtFld.getText(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                dn5.setPaddingTop(5);
                dn5.setPaddingBottom(5);
                dn5.setVerticalAlignment(Element.ALIGN_MIDDLE);
                dn5.setHorizontalAlignment(Element.ALIGN_LEFT);

                PdfPCell sn6 = new PdfPCell(new Paragraph("6)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
                sn6.setPaddingTop(5);
                sn6.setPaddingBottom(5);
                sn6.setVerticalAlignment(Element.ALIGN_MIDDLE);
                sn6.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell do6 = new PdfPCell(new Paragraph("Relieving Order from Previous Institution", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                do6.setPaddingTop(5);
                do6.setPaddingBottom(5);
                do6.setVerticalAlignment(Element.ALIGN_MIDDLE);
                do6.setHorizontalAlignment(Element.ALIGN_LEFT);
                do6.setNoWrap(false);

                PdfPCell dn6 = new PdfPCell(new Paragraph(RelOdrTxtFld.getText(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                dn6.setPaddingTop(5);
                dn6.setPaddingBottom(5);
                dn6.setVerticalAlignment(Element.ALIGN_MIDDLE);
                dn6.setHorizontalAlignment(Element.ALIGN_LEFT);

                PdfPCell sn7 = new PdfPCell(new Paragraph("7)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
                sn7.setPaddingTop(5);
                sn7.setPaddingBottom(5);
                sn7.setVerticalAlignment(Element.ALIGN_MIDDLE);
                sn7.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell do7 = new PdfPCell(new Paragraph("Joining Report at Present Institution", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                do7.setPaddingTop(5);
                do7.setPaddingBottom(5);
                do7.setVerticalAlignment(Element.ALIGN_MIDDLE);
                do7.setHorizontalAlignment(Element.ALIGN_LEFT);
                do7.setNoWrap(false);

                PdfPCell dn7 = new PdfPCell(new Paragraph(JngRptTxtFld.getText(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                dn7.setPaddingTop(5);
                dn7.setPaddingBottom(5);
                dn7.setVerticalAlignment(Element.ALIGN_MIDDLE);
                dn7.setHorizontalAlignment(Element.ALIGN_LEFT);

                docs.addCell(head1).setBorderWidth(1.5f);
                docs.addCell(head2).setBorderWidth(1.5f);
                docs.addCell(head3).setBorderWidth(1.5f);
                docs.addCell(head4).setBorderWidth(0);

                docs.addCell(sn1).setBorderWidth(1);
                docs.addCell(do1).setBorderWidth(1);
                docs.addCell(dn1).setBorderWidth(1);
                docs.addCell(chk).setBorderWidth(0);

                docs.addCell(sn2).setBorderWidth(1);
                docs.addCell(do2).setBorderWidth(1);
                docs.addCell(dn2).setBorderWidth(1);
                docs.addCell(chk).setBorderWidth(0);

                docs.addCell(sn3).setBorderWidth(1);
                docs.addCell(do3).setBorderWidth(1);
                docs.addCell(dn3).setBorderWidth(1);
                if (PGCertFile != null) {
                    docs.addCell(chk).setBorderWidth(0);
                } else if (PGCertFile == null) {
                    docs.addCell(blank).setBorderWidth(0);
                }

                docs.addCell(sn4).setBorderWidth(1);
                docs.addCell(do4).setBorderWidth(1);
                docs.addCell(dn4).setBorderWidth(1);
                docs.addCell(chk).setBorderWidth(0);

                docs.addCell(sn5).setBorderWidth(1);
                docs.addCell(do5).setBorderWidth(1);
                docs.addCell(dn5).setBorderWidth(1);
                if (ExpCertFile != null) {
                    docs.addCell(chk).setBorderWidth(0);
                } else if (ExpCertFile == null) {
                    docs.addCell(blank).setBorderWidth(0);
                }

                docs.addCell(sn6).setBorderWidth(1);
                docs.addCell(do6).setBorderWidth(1);
                docs.addCell(dn6).setBorderWidth(1);
                docs.addCell(chk).setBorderWidth(0);

                docs.addCell(sn7).setBorderWidth(1);
                docs.addCell(do7).setBorderWidth(1);
                docs.addCell(dn7).setBorderWidth(1);
                docs.addCell(chk).setBorderWidth(0);

                myDoc.add(docs);

                myDoc.newPage();

                myDoc.add(new Paragraph("    "));
                myDoc.add(new Paragraph("                                                      DECLARATION", FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.BOLD)));
                myDoc.add(new Paragraph("    "));

                List orderedList = new List(List.ORDERED);
                
                String a= upperCase(NativeString.toLowerCase(fname.getText()));
                String b= upperCase(NativeString.toLowerCase(mname.getText()));
                String c= upperCase(NativeString.toLowerCase(lname.getText()));
                String f= upperCase(NativeString.toLowerCase(presDesg.getText()));
                String e= upperCase(NativeString.toLowerCase(presDept.getText()));

                ListItem p1 = new ListItem("I, Dr. " + a + " " + b + " " + c + ", am working as " + f + " in the Department of " + e + " at Mahatma Gandhi Mission Medical College and do hereby give an undertaking that I am a full time teacher in _________________________________ , working from __________ A. M. to __________ P.M. daily at this Institute.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.PLAIN));
                ListItem p2 = new ListItem("I have not presented myself to any other Medical College / Institution as a Faculty / Resident in the current academic year for the purpose of MCI Assessment.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.PLAIN));
                ListItem p3 = new ListItem("I am not having private practice anywhere OR I am practicing at ______________________________________________ in the city of _____________________________________ and my hours of practice are ____________ to ____________ . Further I state that I am not doing any private practice or not working in any other hospital during college hours.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.PLAIN));
                ListItem p4 = new ListItem("Complete details with regard to Work Experience has been provided and nothing has been concealed by me.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.PLAIN));
                ListItem p5 = new ListItem("I am not working in any other Medical College / Dental College in the state OR outside the state in any capacity : Regular / Contractual / Adhoc -- Full Time / Part Time / Honorary.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.PLAIN));
                ListItem p6 = new ListItem("It is declared that each statement and / or contents of this declaration and / or documents, certificates submitted along with the declaration form, by the undersigned are absolutely true, correct and authentic. In the event of any statement made in this declaration subsequently turning out to be incorrect or false the undersigned has understood and accepted that such misdeclaration in respect to any content of this declaration shall also be treated as a gross misconduct thereby rendering the undersigned liable for necessary disciplinary action (including removal of his name from Indian Medical Register).", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.PLAIN));

                p1.setAlignment(Element.ALIGN_JUSTIFIED);
                p2.setAlignment(Element.ALIGN_JUSTIFIED);
                p3.setAlignment(Element.ALIGN_JUSTIFIED);
                p4.setAlignment(Element.ALIGN_JUSTIFIED);
                p5.setAlignment(Element.ALIGN_JUSTIFIED);
                p6.setAlignment(Element.ALIGN_JUSTIFIED);

                orderedList.add(p1);
                orderedList.add(p2);
                orderedList.add(p3);
                orderedList.add(p4);
                orderedList.add(p5);
                orderedList.add(p6);

                myDoc.add(orderedList);

                myDoc.add(new Paragraph(" "));
                myDoc.add(new Paragraph(" "));
                myDoc.add(new Paragraph(" "));
                myDoc.add(new Paragraph(" "));
                myDoc.add(new Paragraph("                                                                                                         Signature of Employee", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.PLAIN)));

                myDoc.add(new Paragraph("       Date :", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.PLAIN)));
                myDoc.add(new Paragraph("       Place : Navi Mumbai", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.PLAIN)));
                myDoc.close();

                try {
                    Desktop d = Desktop.getDesktop();
                    d.open(new File(filePath));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

//                PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
//                PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
//                PrintRequestAttributeSet attrib = new HashPrintRequestAttributeSet();
//                PrintService selectedPrintService = ServiceUI.printDialog(null, 150, 150, printServices, defaultPrintService, null, attrib);
//                if (selectedPrintService != null) {
//                    DocPrintJob printJob = selectedPrintService.createPrintJob();
//
//                    // Optional fancy listener
//                    printJob.addPrintJobListener(new PrintJobAdapter() {
//                        @Override
//                        public void printJobCompleted(PrintJobEvent pje) {
//                            System.out.println("PDF printing completed");
//                            super.printJobCompleted(pje);
//                        }
//
//                        @Override
//                        public void printJobFailed(PrintJobEvent pje) {
//                            System.out.println("PDF printing failed");
//                            super.printJobFailed(pje);
//                        }
//                    });
//
//                    // Check the PDF file and get a byte array
//                    try {
////                        File pdfFile = new File("C:/Users/Tanay Thakar/Desktop/ML Flow.jpg");
////                        if (pdfFile.exists() && pdfFile.isFile()) {
////                            byte[] PDFByteArray = Files.readAllBytes(FileSystems.getDefault().getPath(pdfFile.getAbsolutePath()));
////
////                            // Create a doc and print it
//                            Doc doc = new SimpleDoc(myDoc, DocFlavor.STRING.TEXT_PLAIN, null);
//                            printJob.print(doc, null);
////                        }
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                } else {
//                    System.out.println("Selection cancelled");
//                }
                JOptionPane.showMessageDialog(null, "Form Printed\n    \nFile Location : " + filePath);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
//            finally
//            {
//                try {
//                    rs.close();
//                    rs1.close();
//                    pst.close();
//                    pst1.close();
//                } catch (SQLException ex) {
//                    JOptionPane.showMessageDialog(null,ex);
//                }
//            }
        }
    }

    @FXML
    public void dbToXL() {
        JFileChooser saveDialog = new JFileChooser();
        saveDialog.setSelectedFile(new File("Employee Data - Exported.xlsx"));

        int dialogResult = saveDialog.showSaveDialog(null);

        String filePath = saveDialog.getSelectedFile().getPath();

        if (dialogResult == 0) {
            Statement statement = null;
            ResultSet resultSet = null;
            String query = "Select * from Employee_Details";
            DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy", Locale.US);
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

//            SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
            Date d1, d2;
            int sr = 1;
            int rc = 1;
            int cc = 0;

            try {
                statement = conn.createStatement();
                resultSet = statement.executeQuery(query);

                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Employee-Data");

                Row headerRow = sheet.createRow(0);

                Cell headerCell = headerRow.createCell(0);
                headerCell.setCellValue("Sr. No.");

                headerCell = headerRow.createCell(1);
                headerCell.setCellValue("First Name");

                headerCell = headerRow.createCell(2);
                headerCell.setCellValue("Middle Name");

                headerCell = headerRow.createCell(3);
                headerCell.setCellValue("Last Name");

                headerCell = headerRow.createCell(4);
                headerCell.setCellValue("Gender");

                headerCell = headerRow.createCell(5);
                headerCell.setCellValue("Date Of Birth");

                headerCell = headerRow.createCell(6);
                headerCell.setCellValue("Age");

                headerCell = headerRow.createCell(7);
                headerCell.setCellValue("Email");

                headerCell = headerRow.createCell(8);
                headerCell.setCellValue("Mobile");

                headerCell = headerRow.createCell(9);
                headerCell.setCellValue("Date Of Joining");

                headerCell = headerRow.createCell(10);
                headerCell.setCellValue("Designation");

                headerCell = headerRow.createCell(11);
                headerCell.setCellValue("Department");

                headerCell = headerRow.createCell(12);
                headerCell.setCellValue("Nature of Appointment");

                headerCell = headerRow.createCell(13);
                headerCell.setCellValue("Service Status");

                makeRowBold(workbook, headerRow);

                while (resultSet.next()) {
                    String f = resultSet.getString("FNAME");
                    String m = resultSet.getString("MNAME");
                    String l = resultSet.getString("LNAME");
                    String g = resultSet.getString("GENDER");
                    d1 = inputFormat.parse(resultSet.getString("DOB"));
                    String d = outputFormat.format(d1);
                    String a = resultSet.getString("AGE");
                    String e = resultSet.getString("EMAIL");
                    String mo = resultSet.getString("MOBILE");
                    d2 = inputFormat.parse(resultSet.getString("JNDATE"));
                    String doj = outputFormat.format(d2);
                    String ds = resultSet.getString("PRESDESG");
                    String dp = resultSet.getString("JNDEPT");
                    String ap = resultSet.getString("NTRAPNT");
                    String i = resultSet.getString("InService");

                    Row row = sheet.createRow(rc++);

                    Cell cell = row.createCell(cc++);
                    cell.setCellValue(sr);

                    cell = row.createCell(cc++);
                    cell.setCellValue(f);

                    cell = row.createCell(cc++);
                    cell.setCellValue(m);

                    cell = row.createCell(cc++);
                    cell.setCellValue(l);

                    cell = row.createCell(cc++);
                    cell.setCellValue(g);

                    cell = row.createCell(cc++);
                    cell.setCellValue(d);

                    cell = row.createCell(cc++);
                    cell.setCellValue(a);

                    cell = row.createCell(cc++);
                    cell.setCellValue(e);

                    cell = row.createCell(cc++);
                    cell.setCellValue(mo);

                    cell = row.createCell(cc++);
                    cell.setCellValue(doj);

                    cell = row.createCell(cc++);
                    cell.setCellValue(ds);

                    cell = row.createCell(cc++);
                    cell.setCellValue(dp);

                    cell = row.createCell(cc++);
                    cell.setCellValue(ap);

                    cell = row.createCell(cc++);
                    cell.setCellValue(i);

                    cc = 0;
                    sr++;
                }

                FileOutputStream outputStream = new FileOutputStream(filePath);
                workbook.write(outputStream);

                workbook.close();
                JOptionPane.showMessageDialog(null, "Employee Details Exported !\n    \nFile Location : " + filePath);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    resultSet.close();
                    statement.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
    }

    public static void makeRowBold(Workbook wb, Row row) {
        CellStyle style = wb.createCellStyle();//Create style
        org.apache.poi.ss.usermodel.Font font = wb.createFont();//Create font
        font.setBold(true);//Make font bold
        style.setFont(font);//set it to bold

        for (int i = 0; i < row.getLastCellNum(); i++) {//For each cell in the row 
            row.getCell(i).setCellStyle(style);//Set the style
        }
    }

    @FXML
    private void xlToDB(MouseEvent event) {
        File srcFile;

        JFileChooser j = new JFileChooser();
        j.setAcceptAllFileFilterUsed(false);
        j.setDialogTitle("Select Excel File to Insert Bulk Employee Data");
        j.addChoosableFileFilter(new FileNameExtensionFilter("Excel File", "xlsx", "xls"));

        int r = j.showOpenDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {
            srcFile = new File(j.getSelectedFile().getAbsolutePath());
            String query1 = "Insert into Employee_Details (FNAME, MNAME, LNAME, GENDER, DOB, AGE, EMAIL, MOBILE, TELRESCODE, TELRES, TELOFFCODE, TELOFF, PRADL1, PRADL2, PRADL3, PRADL4, PRADL5, PEADL1, PEADL2, PEALD3, PEALD4, PEALD5, JNDATE, JNDESG, PRESDESG, JNDEPT, NTRAPNT, INTRJRNLS, NTLJRNLS, STJRNLS, InService, Photo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement prest = null;

            try {
                FileInputStream inputStream = new FileInputStream(srcFile);
                Workbook workbook = new XSSFWorkbook(inputStream);
                Sheet firstSheet = workbook.getSheetAt(0);
                prest = conn.prepareStatement(query1);
                Row row;
                Date date4, date22;
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                for (int i = 1; i <= firstSheet.getLastRowNum(); i++) {

                    row = firstSheet.getRow(i);
                    prest.setString(1, row.getCell(0).getStringCellValue());
                    prest.setString(2, row.getCell(1).getStringCellValue());
                    prest.setString(3, row.getCell(2).getStringCellValue());
                    prest.setString(4, row.getCell(3).getStringCellValue());
                    date4 = row.getCell(4).getDateCellValue();
                    prest.setString(5, df.format(date4));
                    prest.setString(6, Integer.toString((int) row.getCell(5).getNumericCellValue()));
                    prest.setString(7, row.getCell(6).getStringCellValue());
                    prest.setString(8, BigDecimal.valueOf(row.getCell(7).getNumericCellValue()).toBigInteger().toString());
                    if (row.getCell(8) == null) {
                        prest.setString(9, null);
                    } else {
                        prest.setString(9, Integer.toString((int) row.getCell(8).getNumericCellValue()));
                    }
                    if (row.getCell(9) == null) {
                        prest.setString(10, null);
                    } else {
                        prest.setString(10, Integer.toString((int) row.getCell(9).getNumericCellValue()));
                    }
                    if (row.getCell(10) == null) {
                        prest.setString(11, null);
                    } else {
                        prest.setString(11, Integer.toString((int) row.getCell(10).getNumericCellValue()));
                    }
                    if (row.getCell(11) == null) {
                        prest.setString(12, null);
                    } else {
                        prest.setString(12, Integer.toString((int) row.getCell(11).getNumericCellValue()));
                    }
                    prest.setString(13, row.getCell(12).getStringCellValue());
                    prest.setString(14, row.getCell(13).getStringCellValue());
                    prest.setString(15, row.getCell(14).getStringCellValue());
                    prest.setString(16, row.getCell(15).getStringCellValue());
                    prest.setString(17, Integer.toString((int) row.getCell(16).getNumericCellValue()));
                    prest.setString(18, row.getCell(17).getStringCellValue());
                    prest.setString(19, row.getCell(18).getStringCellValue());
                    prest.setString(20, row.getCell(19).getStringCellValue());
                    prest.setString(21, row.getCell(20).getStringCellValue());
                    prest.setString(22, Integer.toString((int) row.getCell(21).getNumericCellValue()));
                    date22 = row.getCell(22).getDateCellValue();
                    prest.setString(23, df.format(date22));
                    prest.setString(24, row.getCell(23).getStringCellValue());
                    prest.setString(25, row.getCell(24).getStringCellValue());
                    prest.setString(26, row.getCell(25).getStringCellValue());
                    prest.setString(27, row.getCell(26).getStringCellValue());
                    if (row.getCell(27) == null) {
                        prest.setString(28, null);
                    } else {
                        prest.setString(28, Integer.toString((int) row.getCell(27).getNumericCellValue()));
                    }
                    if (row.getCell(28) == null) {
                        prest.setString(29, null);
                    } else {
                        prest.setString(29, Integer.toString((int) row.getCell(28).getNumericCellValue()));
                    }
                    if (row.getCell(29) == null) {
                        prest.setString(30, null);
                    } else {
                        prest.setString(30, Integer.toString((int) row.getCell(29).getNumericCellValue()));
                    }
                    prest.setString(31, row.getCell(30).getStringCellValue());
                    prest.setBytes(32, null);

                    prest.execute();
                }
                inputStream.close();
                workbook.close();
                JOptionPane.showMessageDialog(null, "Employee Details Imported Sucessfully");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex);
            } finally {
                try {
                    prest.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                updateTable();
            }
        }
    }

    @FXML
    private void helpAndSupport(MouseEvent event) {
        final Stage child = new Stage();
        child.setTitle("Help & Support");
        child.initModality(Modality.WINDOW_MODAL);
        child.initStyle(StageStyle.UTILITY);
        child.initOwner((Stage) MainPage.getScene().getWindow());

        // create a grid for the data entry.
        GridPane grid = new GridPane();
        GridPane grid2 = new GridPane();
        GridPane grid3 = new GridPane();
        GridPane grid4 = new GridPane();

        AnchorPane anchorPane = new AnchorPane(grid);
        final ImageView img = new ImageView();
        try {
            img.setImage(new Image(new FileInputStream("src/Images/user-guide.png")));
            img.setFitHeight(230);
            img.setFitWidth(220);
        } catch (Exception E) {
            E.printStackTrace();
        }
        final Label cpr = new Label("      Copyright ©  MGMMC 2020");
        final Label ver = new Label("Version 1.01\t\tRelease 1.0.0.1");
        final Label nmae = new Label("MGM Medical College - Employee Administration");
        final Label sft = new Label("                                               Software");
        final Label dsg = new Label("Designed & Developed by");
        final Label self = new Label("   Tanay Thakar\n   Dip. Engineer\n   B. E. (Computer)");
        final Label gd = new Label("Under the Guidance of");
        final Label gud = new Label("   Prof. Abhijit Patil\n   Professor\n   MGMCET");
        final Label hlp = new Label("                       Help & Support");
        final Label mb = new Label("   Call : 8291793875 / 9082392175");
        final Label em = new Label("   Email : thakartanay@gmail.com\n               abhijit.jayprakash@gmail.com");

        javafx.scene.text.Font font = javafx.scene.text.Font.font("Algerian", FontWeight.NORMAL, 29);
        javafx.scene.text.Font font1 = javafx.scene.text.Font.font("Algerian", FontWeight.NORMAL, 25);
        javafx.scene.text.Font font2 = javafx.scene.text.Font.font("Helvetica", FontWeight.BOLD, 16);
        javafx.scene.text.Font font3 = javafx.scene.text.Font.font("Arial", FontWeight.MEDIUM, 15);
        javafx.scene.text.Font font4 = javafx.scene.text.Font.font("Calibri", FontWeight.BOLD, 16);
        javafx.scene.text.Font font5 = javafx.scene.text.Font.font("Arial", FontWeight.MEDIUM, 15);
        javafx.scene.text.Font font6 = javafx.scene.text.Font.font("Times New Roman", FontWeight.BLACK, 17);
        javafx.scene.text.Font font7 = javafx.scene.text.Font.font("Times New Roman", FontWeight.BLACK, 16);
        nmae.setFont(font);
        sft.setFont(font1);
        dsg.setFont(font2);
        self.setFont(font3);
        gd.setFont(font2);
        gud.setFont(font3);
        hlp.setFont(font4);
        mb.setFont(font5);
        em.setFont(font5);
        ver.setFont(font6);
        cpr.setFont(font7);

        grid.setHgap(10);
        grid.setVgap(7);

        grid2.setHgap(60);
        grid2.setVgap(5);

        grid3.setVgap(5);

        grid4.setVgap(7);

        grid.addRow(0, nmae);
        grid.addRow(1, sft);

        grid2.addRow(0, dsg, gd);
        grid2.addRow(1, self, gud);

        grid3.addRow(0, hlp);
        grid3.addRow(1, mb);
        grid3.addRow(2, em);

        grid4.addRow(0, ver);
        grid4.addRow(1, cpr);

        GridPane.setHgrow(nmae, Priority.ALWAYS);
        GridPane.setHgrow(sft, Priority.ALWAYS);
        GridPane.setHgrow(dsg, Priority.ALWAYS);
        GridPane.setHgrow(self, Priority.ALWAYS);
        GridPane.setHgrow(gd, Priority.ALWAYS);
        GridPane.setHgrow(gud, Priority.ALWAYS);
        GridPane.setHgrow(hlp, Priority.ALWAYS);
        GridPane.setHgrow(mb, Priority.ALWAYS);
        GridPane.setHgrow(em, Priority.ALWAYS);
        GridPane.setHgrow(cpr, Priority.ALWAYS);
        GridPane.setHgrow(ver, Priority.ALWAYS);

        anchorPane.getChildren().add(img);
        anchorPane.getChildren().add(grid2);
        anchorPane.getChildren().add(grid3);
        anchorPane.getChildren().add(grid4);

        AnchorPane.setTopAnchor(grid, 20.0);
        AnchorPane.setLeftAnchor(grid, 20.0);
        AnchorPane.setRightAnchor(grid, 20.0);
        AnchorPane.setBottomAnchor(grid, 300.0);

        AnchorPane.setTopAnchor(img, 120.0);
        AnchorPane.setLeftAnchor(img, 20.0);
        AnchorPane.setRightAnchor(img, 700.0);
        AnchorPane.setBottomAnchor(img, 180.0);

        AnchorPane.setTopAnchor(grid2, 130.0);
        AnchorPane.setLeftAnchor(grid2, 250.0);
        AnchorPane.setRightAnchor(grid2, 20.0);
        AnchorPane.setBottomAnchor(grid2, 170.0);

        AnchorPane.setTopAnchor(grid3, 230.0);
        AnchorPane.setLeftAnchor(grid3, 330.0);
        AnchorPane.setRightAnchor(grid3, 20.0);
        AnchorPane.setBottomAnchor(grid3, 75.0);

        AnchorPane.setTopAnchor(grid4, 340.0);
        AnchorPane.setLeftAnchor(grid4, 280.0);
        AnchorPane.setRightAnchor(grid4, 20.0);
        AnchorPane.setBottomAnchor(grid4, 20.0);

        child.setScene(new Scene(anchorPane, 780, 410));

        child.show();

        img.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent actionEvent) {
                try {
                    Desktop d = Desktop.getDesktop();
                    d.open(new File("src/Resources/User Guide.pdf"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        img.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent actionEvent) {
                img.setStyle("-fx-opacity : 0.8");
            }
        });

        img.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent actionEvent) {
                img.setStyle("-fx-opacity : 1");
            }
        });
    }

    @FXML
    private void deleteEmp(ActionEvent event) {

        if (Overview_Table.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "No Faculty Selected..!!!");
        } else {
            int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to Permanently Delete this Faculty Record ?", "Delete Faculty Record", JOptionPane.YES_NO_OPTION);

            if (x == 0) {
                ModelTable mt = Overview_Table.getSelectionModel().getSelectedItem();
                String fn = mt.getFirst();
                String ml = mt.getPhone();
                PreparedStatement pr = null;
                int a = 0;

                String del = "Delete from Employee_Details where FNAME='" + fn + "' and MOBILE='" + ml + "'";

                try {
                    pr = conn.prepareStatement(del);
                    a = pr.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex);
                } finally {
                    if (a == 0) {
                        JOptionPane.showMessageDialog(null, "No Faculty Record Deleted..!");
                    } else if (a > 0) {
                        JOptionPane.showMessageDialog(null, a + " Faculty Record Deleted Sucessfully..!");
                    }
                    updateTable();
                    try {
                        pr.close();
                    } catch (SQLException E) {
                        JOptionPane.showMessageDialog(null, E);
                    }
                }
            }
        }
    }

    @FXML
    private void clearFile(MouseEvent event) {
        if (event.getSource() == CLR1) {
            AdrPrfFile = null;
            CLR1.setVisible(false);
            f1c.setVisible(false);
            f1w.setVisible(false);
        } else if (event.getSource() == CLR2) {
            MBBSCertFile = null;
            CLR2.setVisible(false);
            f2c.setVisible(false);
            f2w.setVisible(false);
        } else if (event.getSource() == CLR3) {
            PGCertFile = null;
            CLR3.setVisible(false);
            f3c.setVisible(false);
            f3w.setVisible(false);
        } else if (event.getSource() == CLR4) {
            ApptOrderFile = null;
            CLR4.setVisible(false);
            f4c.setVisible(false);
            f4w.setVisible(false);
        } else if (event.getSource() == CLR5) {
            ExpCertFile = null;
            CLR5.setVisible(false);
            f5c.setVisible(false);
            f5w.setVisible(false);
        } else if (event.getSource() == CLR6) {
            RelOdrFile = null;
            CLR6.setVisible(false);
            f6c.setVisible(false);
            f6w.setVisible(false);
        } else if (event.getSource() == CLR7) {
            JnRepFile = null;
            CLR7.setVisible(false);
            f7c.setVisible(false);
            f7w.setVisible(false);
        }
    }

    @FXML
    private void prntBlnkRegFrm(MouseEvent event) {
        try {
            Desktop d = Desktop.getDesktop();
            d.open(new File("src/Resources/Blank Registration Form.pdf"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void prntBlnkDeclFrm(MouseEvent event) {
        try {
            Desktop d = Desktop.getDesktop();
            d.open(new File("src/Resources/Blank Declaration Form.pdf"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
