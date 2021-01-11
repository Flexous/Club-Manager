package Gui;

import javax.swing.JDialog;
import javax.swing.plaf.DimensionUIResource;

public class ClubManagerDialog extends JDialog
{
    private static final long serialVersionUID = 3325463766607869978L;
 
    public ClubManagerDialog()
    {
        setMinimumSize(new DimensionUIResource(520, 520));
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(false);
    }
}
