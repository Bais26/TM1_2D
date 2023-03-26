package Connect;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Farmasi extends JFrame implements ActionListener {
    private JTextField searchField;
    private JButton searchButton;
    private JLabel resultLabel;

    public Farmasi() {
        super("Search Stok Obat");

        // set up components
        searchField = new JTextField(10);
        searchButton = new JButton("Cari");
        searchButton.addActionListener(this);
        resultLabel = new JLabel();

        // add components to content panel
        JPanel contentPane = new JPanel();
        contentPane.add(new JLabel("Masukkan Nama Obat:"));
        contentPane.add(searchField);
        contentPane.add(searchButton);
        contentPane.add(resultLabel);
        setContentPane(contentPane);

        // set window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String searchStok = searchField.getText();

        ConnectURI koneksisaya = new ConnectURI();
        URL myAddress = koneksisaya.buildURL("https://farmasi.mimoapps.xyz/mimoqss2auyqD1EAlkgZCOhiffSsFl6QqAEIGtM");
        String response = null;
        try {
            response = koneksisaya.getRespondFrontHttpUrl(myAddress);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        assert response != null;
        JSONArray responJSON = new JSONArray(response);
        ArrayList<RespondModel> respondModel = new ArrayList<>();
        for (int i = 0; i < responJSON.length(); i++) {
            RespondModel resmodel = new RespondModel();
            JSONObject myJSONObject = responJSON.getJSONObject(i);
            resmodel.setI_qty(myJSONObject.getString("i_qty"));
            respondModel.add(resmodel);
        }

        int count = 0;
        for (RespondModel responsee : respondModel) {
            if (responsee.getI_name().equals(searchStok)) {
                count++;
            }
        }

        resultLabel.setText("Jumlah Stok  " + searchStok + ": " + count);
    }

    public static void main(String[] args) {
        new Farmasi();
    }
}