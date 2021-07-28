/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalcollegeapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Tanay Thakar
 */
public class MedicalCollegeApp extends Application {

    public static String Login_Date = null;
    public static String Login_Time = null;
    public static String Logout_Date = null;
    public static String Logout_Time = null;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set Stage boundaries to visible bounds of the main screen
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        stage.setTitle("MGM Medical College - Employee Administration");
        stage.getIcons().add(new Image("file:MGMLogo.png"));
        stage.setMaximized(true);
        stage.show();
        stage.setOnCloseRequest(event -> {
            if (Login_Date == null && Login_Time == null) {
                stage.close();
            } else {
                Connection conn = db.connect();
                PreparedStatement pst = null;
                Date currentDate = GregorianCalendar.getInstance().getTime();
                DateFormat df = DateFormat.getDateInstance();
                String dateString = df.format(currentDate);

                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                String timeString = sdf.format(d);

                Logout_Time = timeString;
                Logout_Date = dateString;

                try {
                    Date temp1 = sdf.parse(Logout_Time);
                    Date temp2 = sdf.parse(Login_Time);

                    long diff = temp1.getTime() - temp2.getTime();

                    long secondsInMilli = 1000;
                    long minutesInMilli = secondsInMilli * 60;
                    long hoursInMilli = minutesInMilli * 60;
                    long daysInMilli = hoursInMilli * 24;

                    long elapsedDays = diff / daysInMilli;
                    diff = diff % daysInMilli;

                    long elapsedHours = diff / hoursInMilli;
                    diff = diff % hoursInMilli;

                    long elapsedMinutes = diff / minutesInMilli;

                    String sql = "Insert into UserLog (Login, Logout, Time) values(?,?,?)";

                    pst = conn.prepareStatement(sql);
                    pst.setString(1, Login_Date + " / " + Login_Time);
                    pst.setString(2, Logout_Date + " / " + Logout_Time);
                    pst.setString(3, elapsedHours + " Hrs " + elapsedMinutes + " Mins");

                    pst.execute();

                } catch (SQLException | ParseException e) {
                    JOptionPane.showMessageDialog(null, e);
                } finally {
                    try {
                        pst.close();
                        conn.close();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
