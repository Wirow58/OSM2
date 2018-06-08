import javax.swing.SwingUtilities;

public class ModelTest
{

    public static void main (String[] args)
    {
        {
            Runnable thread=new Runnable()
            {
                @Override
                public void run()
                {
                    //DBConnection myDBConnection = AppLibrary.InitializeDB("jdbc:mysql://localhost:3306/?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
                    AppViewTest view = new AppViewTest();
                    AppControllerTest ctrl = new AppControllerTest(view);
                    view.setController(ctrl);
                    view.setTableControllerLek(ctrl);
                    view.setTableControllerPlacebo(ctrl);
                    view.setVisible(true);
                }

            };
            SwingUtilities.invokeLater(thread);
        }

    }

}