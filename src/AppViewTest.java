
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class AppViewTest extends JFrame //implements ActionListener
{

    private JMenuBar menuBar;
    private JMenu menuAplikacja;
    private JMenuItem apZamknij;
    private JPanel badanie, infoTesty, pacjent, pLek, pPlacebo;
    private JTextField tNazwaTestu, tLekarzProwadzacy, tLiczbaPacjentow, tBadanyLek, tImie, tNazwisko, tPesel, tWiek, tDawkaLeku, tEfektLeku, tDawkaPlacebo, tEfektPlacebo;
    private JLabel lNazwaTestu, lLekarzProwadzacy, lPoczatekTestu, lLiczbaPacjentow, lBadanyLek, lTest, lImie, lNazwisko, lPesel, lWiek, lDawkaLeku, lEfektLeku, lDawkaPlacebo, lEfektPlacebo;
    private JComboBox<String> boxTest;
    private JSpinner SDzien, SMiesiac,SRok;
    private JButton bDodajTest, bAnulujTest, bDodajPacjenta, bUsunPacjenta, bAktualizujDawkeLeku, bAktualizujDawkePlacebo, bUsunDawkeLeku, bUsunDawkePlacebo;
    private JTable tablicaPacjentowLek, tablicaPacjentowPlacebo ;
    private JScrollPane suwak, suwakPlacebo;
    DBConnection myDBConnection = AppLibrary.InitializeDB("jdbc:mysql://localhost:3306/?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");

    public AppViewTest()
    {
        this.setTitle("Testy kliniczne leków");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(1600,700));
        this.setMinimumSize(new Dimension(1600,700));
        this.setMaximumSize(new Dimension(1600,700));

        //********pasek narzędzi******

        menuBar = new JMenuBar(); //tworzenie paska menu
        menuAplikacja = new JMenu("Aplikacja");

        apZamknij = new JMenuItem("Zamknij");

        menuAplikacja.add(apZamknij);

        apZamknij.setAccelerator(KeyStroke.getKeyStroke("alt F4"));  //dodanie skrótu klawiszowego

        setJMenuBar(menuBar); //dodanie menue Bar
        menuBar.add(menuAplikacja);  //dodanie do paska opcji aplikacja

        //******** wybór badania ******


        badanie = new JPanel();
        GridBagConstraints ulozenie = new GridBagConstraints();
        ulozenie.fill = GridBagConstraints.HORIZONTAL;

        badanie.setBorder(BorderFactory.createTitledBorder("Wybór Testu"));
        ulozenie.gridx = 0;
        ulozenie.gridy = 0;
        this.getContentPane().add(badanie,ulozenie); //dodanie kontenera do okna głównego


        GroupLayout layoutBadanie = new GroupLayout(badanie);
        badanie.setLayout(layoutBadanie);

        lTest = new JLabel("Nazwa testu:");

        boxTest=new JComboBox();
        boxTest.addItem("brak");



        layoutBadanie.setAutoCreateGaps(true);
        layoutBadanie.setHorizontalGroup(layoutBadanie.createSequentialGroup()
                .addGroup(layoutBadanie.createParallelGroup(GroupLayout.Alignment.LEADING )
                        .addGroup(layoutBadanie.createSequentialGroup()
                                .addComponent(lTest)
                                .addComponent(boxTest))));

        layoutBadanie.setVerticalGroup(layoutBadanie.createSequentialGroup()
                .addGroup(layoutBadanie.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lTest)
                        .addComponent(boxTest)));


        //****** Informacje o teście ******

        infoTesty= new JPanel();
        infoTesty.setBorder(BorderFactory.createTitledBorder("Badanie"));

        ulozenie.gridx = 0;
        ulozenie.gridy = 1;
        this.getContentPane().add(infoTesty,ulozenie); //dodanie kontenera do okna głównego
        this.pack();

        GroupLayout layoutiInfoTesty = new GroupLayout(infoTesty);
        infoTesty.setLayout(layoutiInfoTesty);

        lNazwaTestu = new JLabel("Nazwa testu:");

        tNazwaTestu=new JTextField(15);

        lLekarzProwadzacy = new JLabel("Lekarz prowadzący:");

        tLekarzProwadzacy=new JTextField(15);

        lPoczatekTestu = new JLabel("Początek testu:");

        SDzien = new JSpinner();
        SDzien.setValue(20);

        SMiesiac = new JSpinner();
        SMiesiac.setValue(12);

        SRok = new JSpinner();
        SRok.setValue(1996);

        lLiczbaPacjentow = new JLabel("Liczba pacjentów:");

        tLiczbaPacjentow = new JTextField(15);

        lBadanyLek = new JLabel("Badany lek:");

        tBadanyLek = new JTextField(15);

        bDodajTest = new JButton("Dodaj");
        bDodajTest.setActionCommand("DodajTest");
        bAnulujTest = new JButton("Usuń");
        bDodajTest.setActionCommand("UsunTest");


        layoutiInfoTesty.setAutoCreateGaps(true);
        layoutiInfoTesty.setHorizontalGroup(layoutiInfoTesty.createSequentialGroup()
                .addGroup(layoutiInfoTesty.createParallelGroup(GroupLayout.Alignment.LEADING )
                        .addComponent(lNazwaTestu)
                        .addComponent(lLekarzProwadzacy)
                        .addComponent(lPoczatekTestu)
                        .addComponent(lLiczbaPacjentow)
                        .addComponent(lBadanyLek)
                        .addGroup(layoutiInfoTesty.createSequentialGroup()
                                .addComponent(bDodajTest)
                                .addComponent(bAnulujTest)))
                .addGroup(layoutiInfoTesty.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tNazwaTestu)
                        .addComponent(tLekarzProwadzacy)
                        .addGroup(layoutiInfoTesty.createSequentialGroup()
                                .addComponent(SDzien)
                                .addComponent(SMiesiac)
                                .addComponent(SRok))
                        .addComponent(tLiczbaPacjentow)
                        .addComponent(tBadanyLek)));


        layoutiInfoTesty.setVerticalGroup(layoutiInfoTesty.createSequentialGroup()
                .addGroup(layoutiInfoTesty.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lNazwaTestu)
                        .addComponent(tNazwaTestu))
                .addGroup(layoutiInfoTesty.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lLekarzProwadzacy)
                        .addComponent(tLekarzProwadzacy))
                .addGroup(layoutiInfoTesty.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lPoczatekTestu)
                        .addComponent(SDzien)
                        .addComponent(SMiesiac)
                        .addComponent(SRok))
                .addGroup(layoutiInfoTesty.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lLiczbaPacjentow)
                        .addComponent(tLiczbaPacjentow))
                .addGroup(layoutiInfoTesty.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lBadanyLek)
                        .addComponent(tBadanyLek))
                .addGroup(layoutiInfoTesty.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(bDodajTest)
                        .addComponent(bAnulujTest)));


        //******** Informacje o Pacjencie **********

        pacjent = new JPanel();
        pacjent.setBorder(BorderFactory.createTitledBorder("Dane pacjenta"));
        ulozenie.gridx = 0;
        ulozenie.gridy = 2;

        this.getContentPane().add(pacjent,ulozenie); //dodanie kontenera do okna głównego
        this.pack();

        GroupLayout layoutPacjent = new GroupLayout(pacjent);
        pacjent.setLayout(layoutPacjent);



        lImie = new JLabel("Imię:");

        tImie=new JTextField(15);

        lNazwisko = new JLabel("Nazwisko:");

        tNazwisko=new JTextField(15);

        lPesel = new JLabel("PESEL:");

        tPesel=new JTextField(15);

        lWiek = new JLabel("Wiek:");

        tWiek = new JTextField(15);

        bDodajPacjenta = new JButton("Dodaj");
        bDodajPacjenta.setActionCommand("DodajPacjenta");
        bUsunPacjenta = new JButton("Usuń");
        bUsunPacjenta.setActionCommand("UsunPacjenta");


        layoutPacjent.setAutoCreateGaps(true);
        layoutPacjent.setHorizontalGroup(layoutPacjent.createSequentialGroup()
                .addGroup(layoutPacjent.createParallelGroup(GroupLayout.Alignment.LEADING )
                        .addComponent(lImie)
                        .addComponent(lNazwisko)
                        .addComponent(lPesel)
                        .addComponent(lWiek)
                        .addGroup(layoutPacjent.createSequentialGroup()
                                .addComponent(bDodajPacjenta)
                                .addComponent(bUsunPacjenta)))
                .addGroup(layoutPacjent.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tImie)
                        .addComponent(tNazwisko)
                        .addComponent(tPesel)
                        .addComponent(tWiek)));


        layoutPacjent.setVerticalGroup(layoutPacjent.createSequentialGroup()
                .addGroup(layoutPacjent.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lImie)
                        .addComponent(tImie))
                .addGroup(layoutPacjent.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lNazwisko)
                        .addComponent(tNazwisko))
                .addGroup(layoutPacjent.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lPesel)
                        .addComponent(tPesel))
                .addGroup(layoutPacjent.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lWiek)
                        .addComponent(tWiek))
                .addGroup(layoutPacjent.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(bDodajPacjenta)
                        .addComponent(bUsunPacjenta)));



        //******* tabela z pacjentami otrzymującymi lek ******

        pLek = new JPanel();
        pLek.setBorder(BorderFactory.createTitledBorder("Pacjenci otrzymujący lek"));

        ulozenie.gridx = 1;
        ulozenie.gridy = 0;
        ulozenie.gridheight = 4;
        ulozenie.fill = GridBagConstraints.VERTICAL;

        this.getContentPane().add(pLek,ulozenie); //dodanie kontenera do okna głównego
        this.pack();

        GroupLayout layoutListaPL = new GroupLayout(pLek);
        pLek.setLayout(layoutListaPL);

        suwak = new JScrollPane();
        tablicaPacjentowLek = new JTable();
        tablicaPacjentowLek.setModel(new DefaultTableModel(new String[] {"Imię", "Nazwisko", "PESEL", "Wiek", "Dawka leku", "Efekt"}, 25){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        });

        tablicaPacjentowLek.setRowHeight(20);
        suwak.setViewportView(tablicaPacjentowLek);


        bAktualizujDawkeLeku = new JButton("Aktualizuj");
        bAktualizujDawkeLeku.setActionCommand("AktualizujLek");
        bUsunDawkeLeku = new JButton("Usuń");
        bUsunDawkeLeku.setActionCommand("UsunLek");
        lDawkaLeku = new JLabel("Dawka leku:");

        tDawkaLeku =new JTextField(15);

        lEfektLeku = new JLabel("Efekt leku:");

        tEfektLeku =new JTextField(15);


        layoutListaPL.setAutoCreateGaps(true);
        layoutListaPL.setHorizontalGroup(layoutListaPL.createSequentialGroup()
                .addGroup(layoutListaPL.createParallelGroup(GroupLayout.Alignment.LEADING )
                        .addComponent(suwak))
                .addGroup(layoutListaPL.createParallelGroup(GroupLayout.Alignment.LEADING )
                        .addComponent(lDawkaLeku)
                        .addComponent(lEfektLeku))
                .addGroup(layoutListaPL.createParallelGroup(GroupLayout.Alignment.LEADING )
                        .addComponent(tDawkaLeku)
                        .addComponent(tEfektLeku)
                        .addGroup(layoutListaPL.createSequentialGroup()
                                .addComponent(bAktualizujDawkeLeku)
                                .addComponent(bUsunDawkeLeku))));




        layoutListaPL.setVerticalGroup(layoutListaPL.createSequentialGroup()
                .addGroup(layoutListaPL.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(suwak))
                .addGroup(layoutListaPL.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(tDawkaLeku)
                        .addComponent(lDawkaLeku))
                .addGroup(layoutListaPL.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lEfektLeku)
                        .addComponent(tEfektLeku))
                .addGroup(layoutListaPL.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(bAktualizujDawkeLeku)
                        .addComponent(bUsunDawkeLeku)));




        //******* tabela z pacjentami otrzymującymi placebo ******


        pPlacebo = new JPanel();
        pPlacebo.setBorder(BorderFactory.createTitledBorder("Pacjenci otrzymujący Placebo"));

        ulozenie.gridx = 3;
        ulozenie.gridy = 0;
        ulozenie.gridheight = 4;
        ulozenie.fill = GridBagConstraints.VERTICAL;

        this.getContentPane().add(pPlacebo,ulozenie); //dodanie kontenera do okna głównego
        this.pack();

        GroupLayout layoutListaPP = new GroupLayout(pPlacebo);
        pPlacebo.setLayout(layoutListaPP);

        suwakPlacebo = new JScrollPane();
        tablicaPacjentowPlacebo = new JTable();
        tablicaPacjentowPlacebo.setModel(new DefaultTableModel(new String[] {"Imię", "Nazwisko", "PESEL", "Wiek", "Dawka placebo", "Efekt"}, 25){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        });

        tablicaPacjentowPlacebo.setRowHeight(20);
        suwakPlacebo.setViewportView(tablicaPacjentowPlacebo);


        bAktualizujDawkePlacebo = new JButton("Aktualizuj");
        bAktualizujDawkePlacebo.setActionCommand("AktualizujPlacebo");
        bUsunDawkePlacebo = new JButton("Usuń");
        bUsunDawkePlacebo.setActionCommand("UsunPlacebo");
        lDawkaPlacebo = new JLabel("Dawka placebo:");

        tDawkaPlacebo =new JTextField(15);

        lEfektPlacebo = new JLabel("Efekt placebo:");

        tEfektPlacebo =new JTextField(15);


        layoutListaPP.setAutoCreateGaps(true);
        layoutListaPP.setHorizontalGroup(layoutListaPP.createSequentialGroup()
                .addGroup(layoutListaPP.createParallelGroup(GroupLayout.Alignment.LEADING )
                        .addComponent(suwakPlacebo))
                .addGroup(layoutListaPP.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lDawkaPlacebo)
                        .addComponent(lEfektPlacebo))
                .addGroup(layoutListaPP.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tDawkaPlacebo)
                        .addComponent(tEfektPlacebo)
                        .addGroup(layoutListaPP.createSequentialGroup()
                                .addComponent(bAktualizujDawkePlacebo)
                                .addComponent(bUsunDawkePlacebo))));



        layoutListaPP.setVerticalGroup(layoutListaPP.createSequentialGroup()
                .addGroup(layoutListaPP.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(suwakPlacebo))
                .addGroup(layoutListaPP.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lDawkaPlacebo)
                        .addComponent(tDawkaPlacebo))
                .addGroup(layoutListaPP.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lEfektPlacebo)
                        .addComponent(tEfektPlacebo))
                .addGroup(layoutListaPP.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(bAktualizujDawkePlacebo)
                        .addComponent(bUsunDawkePlacebo)));


    }

    public void setController(ActionListener c)  //pozwala śledzić zewnętrznemu kontrolerowi zdarzenia generowane przez swoje kontrolki
    {
        this.bDodajTest.addActionListener(c);
        this.bAnulujTest.addActionListener(c);
        this.bDodajPacjenta.addActionListener(c);
        this.bUsunPacjenta.addActionListener(c);
        this.bUsunDawkeLeku.addActionListener(c);
        this.bAktualizujDawkeLeku.addActionListener(c);
        this.bAktualizujDawkePlacebo.addActionListener(c);
        this.bUsunDawkePlacebo.addActionListener(c);
        this.boxTest.addActionListener(c);

    }

    public String getImie() {
        return tImie.getText();
    }

    public String getNazwisko() {
        return tNazwisko.getText();
    }

    public String getPesel() {
        return tPesel.getText();
    }

    public int getWiek() {return Integer.parseInt(tWiek.getText());}

    public String getNazwaTestu() {return tNazwaTestu.getText();}

    public String getLekarz() {return tLekarzProwadzacy.getText();}

    public int getDzien() {return (int) SDzien.getValue();}

    public int getMiesiac() {return (int) SMiesiac.getValue();}

    public int getRok() {return (int) SRok.getValue();}

    public int getLiczbaPacjentow() {return Integer.parseInt(tLiczbaPacjentow.getText());}

    public String getBadanyLek() {return tBadanyLek.getText();}

    public String getDawkaLeku() {return tDawkaLeku.getText();}

    public String getEfektLeku() {return tEfektLeku.getText();}

    public String getDawkaPlacebo() {return tDawkaPlacebo.getText();}

    public String getEfektPlacebo() {return tEfektPlacebo.getText();}

    public JComboBox getBoxTest() {return boxTest;}

    public DBConnection getMyDBConnection() {return myDBConnection;}


    //public DefaultTableModel getMedicineTable() {/*TODO func body*/}
    //public DefaultTableModel getPlaceboTable() {/*TODO func body*/}
    public JTable getTablicaPacjentowLek() {
        return tablicaPacjentowLek;}

    public JTable getTablicaPacjentowPlacebo() {
        return tablicaPacjentowPlacebo;}


    public void setTableControllerLek(ListSelectionListener lsl) {
        this.tablicaPacjentowLek.getSelectionModel().addListSelectionListener(lsl);
    }

    public void setTableControllerPlacebo(ListSelectionListener lsl) {
        this.tablicaPacjentowPlacebo.getSelectionModel().addListSelectionListener(lsl);
    }

}
