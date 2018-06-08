
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AppControllerTest implements ActionListener, ListSelectionListener
{

    private AppViewTest mView = null;

    public AppControllerTest(AppViewTest view)
    {
        this.mView=view;  //Pamięta klase odpowiedzialną za gui
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        String action = e.getActionCommand();
        int selectedL=0;
        int selectedP=0;
        switch (action){
            case "DodajTest":
                System.out.println("Dodanie Testu");
                //TODO: Kontrola danych
                AppLibrary.addNewExperiment(mView.getMyDBConnection().stmt, mView.getNazwaTestu(), mView.getLekarz(), mView.getDzien(), mView.getMiesiac(), mView.getRok(), mView.getBadanyLek());
                mView.getBoxTest().addItem(mView.getNazwaTestu());
                break;
            case "AnulujTest":
                System.out.println("Usun Test");
                String NameToDelete = (String) mView.getBoxTest().getSelectedItem();
                AppLibrary.deleteExperiment(mView.getMyDBConnection().stmt, NameToDelete);
                mView.getBoxTest().removeItemAt(mView.getBoxTest().getSelectedIndex());
                break;
            case "DodajPacjenta":
                //TODO: Kontrola Danych
                System.out.println("Dodanie pacjenta");
                AppLibrary.addNewPatient(mView.getMyDBConnection().stmt, mView.getPesel(), mView.getImie(), mView.getNazwisko(), mView.getWiek(), mView.getNazwaTestu());
                //TODO: Magia z wpisywaniem do tabel
                break;
            case "AktualizujLek":
                //TODO: Kontrola Danych
                AppLibrary.updatePatient(mView.getMyDBConnection().stmt, mView.getDawkaLeku(), mView.getEfektLeku(), (String) mView.getTablicaPacjentowLek().getValueAt(mView.getTablicaPacjentowLek().getSelectedRow(), 2)); //Sprawdzić czy kolumna o indeksie 2 to PESEL
                //TODO: Magia z wpisywaniem do tabel
                break;
            case "AktualizujPlacebo":
                //TODO: Kontrola danych
                AppLibrary.updatePatient(mView.getMyDBConnection().stmt, mView.getDawkaPlacebo(), mView.getEfektPlacebo(), (String) mView.getTablicaPacjentowPlacebo().getValueAt(mView.getTablicaPacjentowPlacebo().getSelectedRow(), 2)); //Sprawdzić czy ...
                //TODO: Magia z wpisywaniem do tabeli
                break;
                default:
                    System.out.println("default");
                    break;
        }

        /*if (e.getActionCommand().equals("DodajTest"))
        {
            System.out.println("Lubie placki");
        }
        else if (e.getActionCommand().equals("AnulujTest"))
        {
            System.out.println("Lubie ziemniaki");
        }
        else if (e.getActionCommand().equals("Usun"))
        {

        }
        else if (e.getActionCommand().equals("Zamknij"))
        {
            mView.dispose();
        }*/

    }

    @Override
    public void valueChanged(ListSelectionEvent event){
        System.out.println("sratatata");
        //TODO: Wypełnić pola
    }

}
